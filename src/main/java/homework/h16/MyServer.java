package homework.h16;

import java.io.*;
import java.net.*;

public class MyServer {
    private ServerSocket serverSocket;
    private boolean isRunning;

    public void startListen(int port) {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                isRunning = true;
                while (isRunning) {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                }
            } catch (IOException e) {
                if (!isRunning) {
//                    System.out.println("Server stopped");
                } else {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void handleClient(Socket clientSocket) {
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if ("bye".equalsIgnoreCase(inputLine.trim())) {
                        isRunning = false;
                        serverSocket.close();
                        break;
                    }
                    out.println(inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stop() {
        isRunning = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean testEcho(BufferedReader in, PrintWriter out, String testStr) throws IOException {
        out.write(testStr + "\r\n");
        out.flush();
        String echo = in.readLine();
        return testStr.equals(echo);
    }
}