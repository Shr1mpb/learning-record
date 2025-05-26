/**
 * 
 */
package homework.frontend.h54;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Administrator
 *
 */
public class FileTool {
	 
	/**
	 * 将homeDir 目录下（包括子目录）所有的文本文件（扩展名为.txt，扩展名不是.txt的文件不要动，扩展名区分大小写) 文件中，orgStr替换为 targetStr
	 * 所有文本文件均为UTF-8编码
	 * 例如将某个目录中所有文本文件中的 南开大学 替换为 天津大学
	 * @param homeDir
	 * @param orgStr
	 * @param targetStr
	 */
	public void replaceTxtFileContent(String homeDir, String orgStr, String targetStr) {
		if (homeDir == null || orgStr == null || targetStr == null) {
			throw new IllegalArgumentException("参数不能为null");
		}

		Path startDir = Paths.get(homeDir);
		if (!Files.exists(startDir) || !Files.isDirectory(startDir)) {
			throw new IllegalArgumentException("目录不存在或不是有效目录: " + homeDir);
		}

		try (Stream<Path> paths = Files.walk(startDir)) {
			paths.filter(this::isTxtFile)
					.forEach(path -> replaceInFile(path, orgStr, targetStr));
		} catch (IOException e) {
			System.err.println("处理目录时出错: " + e.getMessage());
			e.printStackTrace();
		}
    }

	private boolean isTxtFile(Path path) {
		if (!Files.isRegularFile(path)) {
			return false;
		}
		String fileName = path.getFileName().toString();
		return fileName.endsWith(".txt"); // 区分大小写
	}

	private void replaceInFile(Path filePath, String orgStr, String targetStr) {
		try {
			// 读取文件内容
			String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);

			// 执行替换
			String newContent = content.replace(orgStr, targetStr);

			// 如果内容有变化才写入文件
			if (!content.equals(newContent)) {
				Files.write(filePath, newContent.getBytes(StandardCharsets.UTF_8));
				System.out.println("已处理文件: " + filePath);
			}
		} catch (IOException e) {
			System.err.println("处理文件出错: " + filePath);
			e.printStackTrace();
		}
	}

}
