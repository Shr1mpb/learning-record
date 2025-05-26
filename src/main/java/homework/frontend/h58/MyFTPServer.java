package homework.frontend.h58;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyFTPServer extends Thread {
    private final String ip;
    private final int port;
    private final String remoteHome;
    private ServerSocket serverSocket;
    private boolean running = true;

    public MyFTPServer(String ip, int port, String remoteHome) throws Exception {
        this.ip = ip;
        this.port = port;
        this.remoteHome = remoteHome.endsWith("/") ? remoteHome : remoteHome + "/";
        this.serverSocket = new ServerSocket(port);
    }

    public String getRemoteHome() {
        return remoteHome;
    }

    @Override
    public void run() {
        try {
            while (running) {
                Socket clientSocket = serverSocket.accept();
                // 简单处理客户端连接，实际应用中应该创建新线程处理每个连接
                clientSocket.close();
            }
        } catch (IOException e) {
            if (running) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        super.start();
    }

    public void stopServer() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        // 简化版，不验证用户
        return true;
    }
}