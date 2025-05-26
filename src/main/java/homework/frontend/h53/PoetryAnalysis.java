package homework.frontend.h53;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PoetryAnalysis {

	/**
	 * 
	 * @param pathFilename 包含诗歌内容的源文件
	 * @param chars 需要统计的字 以半角分号分割 
	 * 统计  
	 * 
	 */
	public void analysis(String pathFilename, String chars) {
		try {
			// 读取诗歌文件内容
			String content = new String(Files.readAllBytes(Paths.get(pathFilename)));

			// 获取要分析的汉字列表
			List<String> targetChars = Arrays.asList(chars.split(";"));

			// 统计双字词频率
			Map<String, Integer> wordFrequency = new HashMap<>();

			// 处理诗歌内容，提取所有双字词
			for (int i = 0; i < content.length() - 1; i++) {
				String currentChar = String.valueOf(content.charAt(i));
				String nextChar = String.valueOf(content.charAt(i + 1));

				// 如果有字符在目标字符列表中 并且不是标点符号
				if ((targetChars.contains(currentChar) || targetChars.contains(nextChar)) && !isChinesePunctuation(currentChar.charAt(0))
				&& !isChinesePunctuation(nextChar.charAt(0))) {
					String word = currentChar + nextChar;
					wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
				}
			}

			// 按频率排序
			List<Map.Entry<String, Integer>> sortedEntries = wordFrequency.entrySet().stream()
					.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
					.collect(Collectors.toList());

			// 打印结果
			System.out.println("分析文件: " + pathFilename);
			System.out.println("目标字符: " + chars);
			System.out.println("词汇\t频率");
			for (Map.Entry<String, Integer> entry : sortedEntries) {
				System.out.println(entry.getKey() + "\t" + entry.getValue());
			}
			System.out.println();

		} catch (IOException e) {
			System.err.println("无法读取文件: " + pathFilename);
			e.printStackTrace();
		}


	}
	private boolean isChinesePunctuation(char c) {
		// 使用前面提供的任一实现
		// 例如：
		String chinesePunctuations = "。，、；：？！「」『』（）【】《》〈〉｛｝［］…—～·";
		return chinesePunctuations.indexOf(c) != -1;
	}
}
