package homework.h09;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 把你作业的代码写到这个类里面
 * 不可以修改类的名字、包名、和固有的几个方法名以及方法的可见性
 * 可以增加其他方法、属性、类
 * 可以引用jdk的类
 * 不要引用jdk1.8以外第三方的包
 * 
 * @author cjy
 *
 */
public class Home09 {
	public Home09() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 计算出一段文字中不重复的字符的个数，例如“天津市天津大学 ”不重复字符为5
	 * 提示：使用java.util.HashSet 
	 * 难度系数1星
	 * @param s
	 * @return
	 */
	public int getDistinctCharCount(String s) {
		char[] charArray = s.toCharArray();
		if (charArray.length == 0 || charArray.length == 1) {
			return charArray.length;
		}
		Set<Character> set = new HashSet<>();
        for (char c : charArray) {
            set.add(c);
        }
		return set.size();
	}
	/**
	 * 返回一段文字中，出现频率最高的字符（不考虑并列第一的情况） 例如：getFrequentChar("好好学习") 返回'好'
	 * 例如：getFrequentChar("我是天津大学软件学院学生") 返回'学'
	 * 提示：使用一个长度为65535的数组，或者使用HashMap   
	 * 难度系数2星
	 * @param s
	 * @return
	 */
	public char getFrequentChar(String s) {
		if (s.isEmpty()) {
			return ' ';
		}
		HashMap<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		AtomicReference<Character> ret = new AtomicReference<>(s.charAt(0));
		AtomicReference<Integer> count = new AtomicReference<>(Integer.MIN_VALUE);
		map.entrySet().stream().forEach(entry -> {
			if (entry.getValue() > count.get()) {
				ret.set(entry.getKey());
				count.set(entry.getValue());
			}
		});
		return ret.get();
	}
	

	
	/**
	 * 返回一段文字中，出现频率最高的词（每个词由2个字符构成，任意两个相邻的字符称为一个词，例如“天津大学，你好”由“天津”“津大”“大学”“学，”“，你”“你好” 6个词构成)
	 * 不会出现频率最高并列的情况
	 * 提示：使用HashMap 
	 * 难度系数2星
	 * @param content
	 * @return
	 */
	public String getFrequentWord(String content){
		if (content.isEmpty()) {
			return "";
		}
		if (content.length() == 2) {
			return content;
		}
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < content.length() - 1; i++) {
			String substring = content.substring(i, i + 2);
			map.put(substring, map.getOrDefault(substring, 0) + 1);
		}
		AtomicReference<String> ret = new AtomicReference<>();
		AtomicReference<Integer> count = new AtomicReference<>(Integer.MIN_VALUE);
		map.entrySet().stream().forEach(entry -> {
			if (entry.getValue() > count.get()) {
				ret.set(entry.getKey());
				count.set(entry.getValue());
			}
		});
		return ret.get();
	}
	 
	 
	
	/**
	 * 把一个StringBufer中所有的空格去掉
	 * 提示：不能新建StringBuffer对象，必须在原来的基础上删掉原来字符串
	 * 难度系数1星
	 * @param buf
	 */
	public void zipStringBufer(StringBuffer buf) {
		int index = 0;
		while ((index = buf.indexOf(" ", index)) != -1) {
			buf.delete(index, index + 1);
		}
	}

 
}

