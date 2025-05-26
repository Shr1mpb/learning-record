package homework.oop.h11;

import java.util.HashMap;
import java.util.Map;

public class Home11 {

	public Home11() {
		// TODO Auto-generated constructor stub
	}

 
	/**
	 * 字符串content是一个超市的历次购物小票的合计，每次购物的明细之间用分号分割，每个商品之间用半角逗号分开
	 * 请找出   哪两个商品被同时购买的频率最高，将这2个商品名称返回，名称之间用逗号分隔
	 * 测试的时候，商品名称可能增加新的商品，例如方便面、面包...
	 * @param content，历次购物的明细，例如：炸鸡,可乐,啤酒;薯片,啤酒,炸鸡;啤酒,雪碧,炸鸡
	 * @return 哪两个商品被同时购买的频率最高，将这2个商品名称返回，名称之间用逗号分隔
	 */
	public String getFrequentItem(String content) {
		String[] receipts = content.split(";");
		Map<String, Integer> itemPairCount = new HashMap<>();

		// 遍历每一笔购物明细
		for (String receipt : receipts) {
			String[] items = receipt.split(",");
			// 找出所有商品对子
			for (int i = 0; i < items.length - 1; i++) {
				for (int j = i + 1; j < items.length; j++) {
					// 将商品名进行排序，避免因顺序不同而产生不同的键
					// 调用trim防止有空格出现
					String item1 = items[i].trim();
					String item2 = items[j].trim();
					String pair = item1.compareTo(item2) < 0 ? item1 + "," + item2 : item2 + "," + item1;

					// 增加该商品对的计数
					itemPairCount.put(pair, itemPairCount.getOrDefault(pair, 0) + 1);
				}
			}
		}

		// 找出频率最高的商品对
		String mostFrequentPair = null;
		int maxCount = 0;

		for (Map.Entry<String, Integer> entry : itemPairCount.entrySet()) {
			if (entry.getValue() > maxCount) {
				maxCount = entry.getValue();
				mostFrequentPair = entry.getKey();
			}
		}
		// 如果没有找到，返回空字符串
		return mostFrequentPair != null ? mostFrequentPair : "";
	}


}
