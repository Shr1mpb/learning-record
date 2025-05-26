package homework.frontend.h58;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FTPFile {
    private final MyFTPServer server;
    private final String pathFileName;
    private final File file;

    public FTPFile(MyFTPServer server, String pathFileName) throws Exception {
        this.server = server;
        this.pathFileName = normalizePath(pathFileName);
        this.file = new File(server.getRemoteHome(), this.pathFileName.substring(1));
    }

    private String normalizePath(String path) {
        path = path.replace('\\', '/');

        // 确保路径以/开头
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        // 处理目录路径的结尾斜杠
        if (path.endsWith("/") && path.length() > 1) {
            path = path.substring(0, path.length() - 1);
        }

        return path;
    }

    public FTPFile[] listFiles() throws Exception {
        if (!isDirectory()) {
            throw new Exception("Not a directory");
        }

        File[] children = file.listFiles();
        if (children == null) {
            return new FTPFile[0];
        }

        // 排序：先目录后文件，按名称升序
        Arrays.sort(children, Comparator.comparing(File::isDirectory).reversed()
                .thenComparing(File::getName));

        List<FTPFile> result = new ArrayList<>();
        for (File child : children) {
            String childPath = pathFileName + (pathFileName.equals("/") ? "" : "/") + child.getName();
            if (child.isDirectory()) {
                childPath += "/";  // 为目录添加结尾斜杠
            }
            result.add(new FTPFile(server, childPath));
        }

        return result.toArray(new FTPFile[0]);
    }

    public boolean isDirectory() throws Exception {
        return file.isDirectory();
    }

    public boolean isFile() throws Exception {
        return file.isFile();
    }

    public String getPathFileName() throws Exception {
        // 如果是目录，确保返回的路径以/结尾
        if (isDirectory()) {
            return pathFileName.equals("/") ? "/" : pathFileName + "/";
        }
        return pathFileName;
    }

    public long length() throws Exception {
        if (!isFile()) {
            throw new Exception("Not a file");
        }
        return file.length();
    }

    public byte[] readToBytes() throws Exception {
        if (!isFile()) {
            throw new Exception("Not a file");
        }
        return Files.readAllBytes(file.toPath());
    }
}