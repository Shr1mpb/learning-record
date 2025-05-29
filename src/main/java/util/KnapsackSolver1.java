package util;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 确保test.in输入文件位于jar包/class文件运行的目录下
 * 动态规划算法求0/1背包问题的解
 * 输入文件格式如下：
 *      物品数量
 *      物品id 物品价值 物品重量
 *      ...
 *      背包容量(最后一行)
 * 输出文件格式如下：
 *      最大价值
 *      物品id 物品价值 物品重量
 *      ...
 * 此外，还会输出time.out文件 里面记录了计算耗时(单位：ms)
 */
public class KnapsackSolver1 {

    /**
     * 物品类，表示背包问题中的一个物品
     */
    static class Item {
        int id;      // 物品编号
        int value;   // 物品价值
        int weight;  // 物品重量

        public Item(int id, int value, int weight) {
            this.id = id;
            this.value = value;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        String inputFile = userDir + "\\test.in";
        String outputFile = userDir + "\\outp.out";
        String timeFile = userDir + "\\time.out";

        try {
            LocalDateTime before = LocalDateTime.now();
            // 读取输入文件
            List<Item> items = new ArrayList<>();
            int capacity = readInputFile(inputFile, items);

            // 解决背包问题
            KnapsackResult result = solveKnapsack(items, capacity);

            // 写入输出文件
            writeOutputFile(outputFile, result, items);
            LocalDateTime after = LocalDateTime.now();
            Duration duration = Duration.between(before, after);
            long millis = duration.toMillis();
            writeTimeDiffFile(timeFile,millis);

            System.out.println("计算完成，结果已写入 " + outputFile);
            System.out.println("处理时间已写入" + timeFile);

        } catch (IOException e) {
            System.err.println("处理文件时出错: " + e.getMessage());
        }
    }

    /**
     * 输出耗时文件
     * @param timeFile 文件路径
     * @param millis 耗时 单位ms
     */
    private static void writeTimeDiffFile(String timeFile, long millis) throws IOException {
        try(FileWriter fw = new FileWriter(timeFile)){
            fw.write(String.valueOf(millis));
        }

    }

    /**
     * 从文件读取输入数据
     * @param filename 输入文件名
     * @param items 用于存储物品的列表
     * @return 背包容量
     * @throws IOException 如果文件读取失败
     */
    private static int readInputFile(String filename, List<Item> items) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // 读取物品数量
            int n = Integer.parseInt(reader.readLine().trim());

            // 读取每个物品的信息
            for (int i = 0; i < n; i++) {
                String[] parts = reader.readLine().trim().split("\\s+");
                int id = Integer.parseInt(parts[0]);
                int value = Integer.parseInt(parts[1]);
                int weight = Integer.parseInt(parts[2]);
                items.add(new Item(id, value, weight));
            }

            // 读取背包容量
            return Integer.parseInt(reader.readLine().trim());
        }
    }

    /**
     * 解决0/1背包问题
     * @param items 物品列表
     * @param capacity 背包容量
     * @return 包含最大价值和选中物品ID的结果对象
     */
    private static KnapsackResult solveKnapsack(List<Item> items, int capacity) {
        int n = items.size();
        int[][] dp = new int[n + 1][capacity + 1];
        boolean[][] keep = new boolean[n + 1][capacity + 1];// 是否选中物品 [id][容量] 背包容量为w时是否选中第i个物品

        // 动态规划填表
        for (int i = 1; i <= n; i++) {
            Item item = items.get(i - 1);
            for (int w = 0; w <= capacity; w++) {
                if (item.weight <= w &&
                        dp[i - 1][w - item.weight] + item.value > dp[i - 1][w]) {
                    dp[i][w] = dp[i - 1][w - item.weight] + item.value;
                    keep[i][w] = true;
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // 找出选中的物品
        List<Integer> selectedItems = new ArrayList<>();
        int remainingWeight = capacity;
        for (int i = n; i > 0; i--) {
            if (keep[i][remainingWeight]) {
                selectedItems.add(items.get(i - 1).id);
                remainingWeight -= items.get(i - 1).weight;
            }
        }
        // 返回用于生成文件的结果
        return new KnapsackResult(dp[n][capacity], selectedItems);
    }

    /**
     * 将结果写入输出文件
     * @param filename 输出文件名
     * @param result 背包问题结果
     * @param items 物品列表（用于获取物品详细信息）
     * @throws IOException 如果文件写入失败
     */
    private static void writeOutputFile(String filename, KnapsackResult result,
                                        List<Item> items) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // 写入最大价值
            writer.println(result.maxValue);

            // 创建物品ID到物品对象的映射
            Map<Integer, Item> itemMap = new HashMap<>();
            for (Item item : items) {
                itemMap.put(item.id, item);
            }

            // 写入选中的物品详细信息
            for (int id : result.selectedItems) {
                Item item = itemMap.get(id);
                writer.println(id + " " + item.value + " " + item.weight);
            }
        }
    }

    /**
     * 背包问题结果类
     */
    static class KnapsackResult {
        int maxValue;              // 最大价值
        List<Integer> selectedItems; // 选中的物品ID列表

        public KnapsackResult(int maxValue, List<Integer> selectedItems) {
            this.maxValue = maxValue;
            this.selectedItems = selectedItems;
        }
    }
}