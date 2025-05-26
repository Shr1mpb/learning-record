package homework.frontend.h56;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ChatClient {
	private final String ip;
	private final int port;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private BlockingQueue<String> messageQueue;
	private boolean loggedIn = false;
	private ExecutorService executor;

	public ChatClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
		this.messageQueue = new LinkedBlockingQueue<>();
	}

	public boolean login(String userName, String password) {
		try {
			socket = new Socket(ip, port);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Send credentials
			out.println(userName + ":" + password);

			// Read response
			String response = in.readLine();
			if ("LOGIN_SUCCESS".equals(response)) {
				loggedIn = true;
				executor = Executors.newSingleThreadExecutor();
				executor.execute(this::listenForMessages);
				return true;
			}
		} catch (IOException e) {
			// Ignore
		}
		return false;
	}

	public void logout() {
		if (loggedIn) {
			out.println("LOGOUT");
			loggedIn = false;
			cleanup();
		}
	}

	public void speak(String str) throws IllegalStateException {
		if (!loggedIn) {
			throw new IllegalStateException("Not logged in");
		}
		out.println(str);
	}

	public String read() {
		if (!loggedIn) {
			return null;
		}
		try {
			// 等待一小段时间以防消息尚未到达
			return messageQueue.poll(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			return null;
		}
	}

	private void listenForMessages() {
		try {
			String message;
			while (loggedIn && (message = in.readLine()) != null) {
				messageQueue.put(message);
			}
		} catch (IOException e) {
			if (loggedIn) { // 只有在登录状态下才处理异常
				System.err.println("Error reading message: " + e.getMessage());
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} finally {
			loggedIn = false;
			cleanup();
		}
	}

	private void cleanup() {
		try {
			if (executor != null) {
				executor.shutdownNow();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			// Ignore
		}
	}
}