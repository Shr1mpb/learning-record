package homework.h12;

import java.util.*;

public class Home12 {
	public Home12() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 字符串content是一个超市的历次购物小票的合计，每次购物的明细之间用分号分割，每个商品之间用半角逗号分开
	 * 请找出   哪n(n>=1)个商品被同时购买的频率最高，将这n个商品名称的集合（set)返回 
	 * 
	 * @param content，历次购物的明细，例如：炸鸡,可乐,啤酒;薯片,啤酒,炸鸡;啤酒,雪碧,炸鸡
	 * @param n
	 * @return 哪n个商品被同时购买的频率最高，将这n个商品名称的集合（set)返回 
	 *  不会出现并列的情况
	 */
	public Set<String> getFrequentItem(String content, int n) {
		String[] receipts = content.split(";");
		Map<Set<String>, Integer> itemCombinationCount = new HashMap<>();

		// 遍历每一笔购物明细
		for (String receipt : receipts) {
			String[] items = receipt.split(",");
			// 找出所有可能的商品组合 分类讨论n>2和n<=2
			int itemCount = items.length;
			if (n == 1) {
                for (String item : items) {
                    Set<String> pair = new HashSet<>();
					pair.add(item.trim());
                    itemCombinationCount.put(pair, itemCombinationCount.getOrDefault(pair, 0) + 1);
                }
			}
			// n==2 与home11思路一样
			if (n == 2) {
				for (int i = 0; i < itemCount - 1; i++) {
					for (int j = i + 1; j < itemCount; j++) {
						// 这里用Set装pair，可以确保不同的商品顺序每个pair的内容相同
						// HashSet默认重写了equals方法，如果引用不同，就会对比它的内容
						Set<String> pair = new HashSet<>();
						pair.add(items[i].trim());
						pair.add(items[j].trim());
						itemCombinationCount.put(pair, itemCombinationCount.getOrDefault(pair, 0) + 1);
					}
				}
			}
			// 如果 n > 2，需要生成更大的组合，这里用回溯实现
			if (n > 2) {
				generateCombinations(items, new HashSet<>(), itemCombinationCount, n);
			}
		}

		// 找出频率最高的商品组合
		Set<String> mostFrequentSet = null;
		int maxCount = 0;

		for (Map.Entry<Set<String>, Integer> entry : itemCombinationCount.entrySet()) {
			if (entry.getValue() > maxCount) {
				maxCount = entry.getValue();
				mostFrequentSet = entry.getKey();
			}
		}
		// 如果没有找到，返回空集合
		return mostFrequentSet != null ? mostFrequentSet : Collections.emptySet();
	}

	//回溯 用于生成n个组合的pair
	private void generateCombinations(String[] items, Set<String> currentCombination, Map<Set<String>, Integer> itemCombinationCount, int n) {
		if (currentCombination.size() == n) {//放够了 添加到结果中返回
			itemCombinationCount.put(new HashSet<>(currentCombination), itemCombinationCount.getOrDefault(new HashSet<>(currentCombination), 0) + 1);
			return;
		}
		//开始循环
		for (String item : items) {
			if (!currentCombination.contains(item.trim())) {
				currentCombination.add(item.trim());//添加
				generateCombinations(items, currentCombination, itemCombinationCount, n);//递归
				currentCombination.remove(item.trim());//回溯

			}
		}
	}



}
