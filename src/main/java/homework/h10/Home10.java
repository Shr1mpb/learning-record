package homework.h10;


import java.util.*;

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
public class Home10 {
	public Home10() {
	} 
	/**
	 * 将一个字符串中字符按出现频率的高到低排序返回，如果两个字符出现的频率一样，则将最先出现的字符排在前面
	 * 例如：orderChar(“abcdefg”)返回 “abcdefg” 
	 * orderChar(“abcdefgg”)返回 “gabcdef”
	 * orderChar(“abcdefgge”)返回 “egabcdf”
	 * orderChar(“天津大学软件学院”)返回 “学天津大软件院”
	 * @param content
	 * @return
	 */
	public String orderChar(String content) {
		if (content.isEmpty()) {
			return "";
		}
		Map<Character, Integer> freqMap = new LinkedHashMap<>();
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
		}
		List<Character> orderList = new ArrayList<>(freqMap.keySet());
		PriorityQueue<Character> pq = new PriorityQueue<>(
				(c1, c2) -> {
					int freqCompare = Integer.compare(freqMap.get(c2), freqMap.get(c1));//降序
					if (freqCompare != 0) {
						return freqCompare;
					} else {
						return Integer.compare(orderList.indexOf(c1), orderList.indexOf(c2));
					}
				}
		);

		pq.addAll(freqMap.keySet());
		StringBuilder ret= new StringBuilder();
		while (!pq.isEmpty()) {
			char c = pq.poll();
			ret.append(c);
		}
		return ret.toString();
	}

	
}
