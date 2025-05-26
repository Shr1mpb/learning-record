package homework.frontend.h56;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
	private final int port;
	private final Map<String, String> userCredentials;
	private final Map<String, PrintWriter> connectedUsers;
	private final BlockingQueue<String> messageQueue;
	private ServerSocket serverSocket;
	private ExecutorService executor;

	public ChatServer(int port, String passwordFilename) throws IOException {
		this.port = port;
		this.userCredentials = loadUserCredentials(passwordFilename);
		this.connectedUsers = new ConcurrentHashMap<>();
		this.messageQueue = new LinkedBlockingQueue<>();
	}

	private Map<String, String> loadUserCredentials(String filename) throws IOException {
		Map<String, String> credentials = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\t");
				if (parts.length == 2) {
					credentials.put(parts[0], parts[1]);
				}
			}
		}
		return credentials;
	}

	public void startListen() {
		executor = Executors.newCachedThreadPool();
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server started on port " + port);

			// 启动接收消息线程
			executor.execute(this::dispatchMessages);

			// 启动接收连接线程
			executor.execute(this::handleConnection);
		} catch (IOException e) {
			System.err.println("Server error: " + e.getMessage());
		}
	}

	private void dispatchMessages() {
		try {
			while (true) {
				String message = messageQueue.take();
				synchronized (connectedUsers) {
					// 使用迭代器安全地遍历
					Iterator<Map.Entry<String, PrintWriter>> iterator = connectedUsers.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<String, PrintWriter> entry = iterator.next();
						try {
							entry.getValue().println(message);
						} catch (Exception e) {
							// 如果发送失败，移除该用户
							iterator.remove();
						}
					}
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void handleConnection() {
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				executor.execute(new ClientHandler(clientSocket));
			}

		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
	}

	private class ClientHandler implements Runnable {
		private final Socket socket;
		private String username;
		private PrintWriter out;

		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

				this.out = out;

				// 认证
				String credentials = in.readLine();
				if (credentials == null) return;

				String[] parts = credentials.split(":");
				if (parts.length != 2 || !authenticate(parts[0], parts[1])) {
					out.println("LOGIN_FAILED");
					return;
				}

				this.username = parts[0];
				out.println("LOGIN_SUCCESS");
				connectedUsers.put(username, out);

				String input;
				while ((input = in.readLine()) != null) {
					if ("LOGOUT".equals(input)) {
						break;
					}
//					messageQueue.add(username + ": " + input);
					messageQueue.add(input);
				}

			} catch (IOException e) {
				System.err.println("Client handler error: " + e.getMessage());
			} finally {
				if (username != null) {
					connectedUsers.remove(username);
					System.out.println(username + " disconnected");
				}
				try {
					socket.close();
				} catch (IOException e) {
					// Ignore
				}
			}
		}

		private boolean authenticate(String username, String password) {
			String storedPassword = userCredentials.get(username);
			return storedPassword != null && storedPassword.equals(password);
		}
	}
}