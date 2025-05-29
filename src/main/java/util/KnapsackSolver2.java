package util;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * 动态规划算法求0/1背包问题的解
 * 确保problemInstances2目录位于jar包/class文件运行目录下
 * 输入：  pxx文件夹下的pxx_c.txt表示容量、w重量、p价值、s参考答案
 * 输出： 目录下的out文件夹，每一行代表一样物品，0表示不选，1表示选
 *       最后一行附有执行所用的纳秒数(因为windows的LocalDateTime和currentTimeMillis只精确到ms，数据集较小)
 *       结果会比对参考答案并输出在控制台中
 */
public class KnapsackSolver2 {

    static class Item {
        int id;
        int value;
        int weight;

        public Item(int id, int value, int weight) {
            this.id = id;
            this.value = value;
            this.weight = weight;
        }
    }

    static class TestCase {
        String name;        // 测试用例名称，如p01
        int capacity;       // 背包容量
        List<Item> items;   // 物品列表
        List<Integer> solution; // 参考答案

        public TestCase(String name) {
            this.name = name;
            this.items = new ArrayList<>();
            this.solution = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        Path problemDir = Paths.get(userDir, "problemInstances2");
        Path outDir = Paths.get(userDir, "out");

        try {
            // 创建输出目录
            Files.createDirectories(outDir);

            // 获取所有测试用例目录
            List<Path> testCaseDirs = Files.list(problemDir)
                    .filter(Files::isDirectory)
                    .sorted()
                    .collect(Collectors.toList());

            // 处理每个测试用例
            for (Path testCaseDir : testCaseDirs) {
                String testCaseName = testCaseDir.getFileName().toString();
                System.out.println("处理测试用例: " + testCaseName);

                // 读取测试用例数据
                TestCase testCase = readTestCase(testCaseDir, testCaseName);

                // 解决背包问题
                Long start = System.nanoTime();
                KnapsackResult result = solveKnapsack(testCase.items, testCase.capacity);
                Long end = System.nanoTime();
                long duration = end - start;

                // 写入输出文件
                Path outFile = outDir.resolve(testCaseName + "_out.txt");
                writeOutputFile(outFile, result, testCase.items, duration);

                // 验证答案
                boolean isCorrect = verifySolution(result.selectedItems, testCase.solution);
                System.out.println("测试用例 " + testCaseName + " 结果: " +
                        (isCorrect ? "正确" : "错误"));
            }

        } catch (IOException e) {
            System.err.println("处理文件时出错: " + e.getMessage());
        }
    }

    /**
     * 读取测试用例数据
     */
    private static TestCase readTestCase(Path testCaseDir, String testCaseName) throws IOException {
        TestCase testCase = new TestCase(testCaseName);

        // 读取容量文件
        Path capacityFile = testCaseDir.resolve(testCaseName + "_c.txt");
        testCase.capacity = Integer.parseInt(Files.readAllLines(capacityFile).get(0));

        // 读取重量文件
        Path weightFile = testCaseDir.resolve(testCaseName + "_w.txt");
        List<String> weightLines = Files.readAllLines(weightFile);

        // 读取价值文件
        Path valueFile = testCaseDir.resolve(testCaseName + "_p.txt");
        List<String> valueLines = Files.readAllLines(valueFile);

        // 创建物品列表
        for (int i = 0; i < weightLines.size(); i++) {
            int weight = Integer.parseInt(weightLines.get(i));
            int value = Integer.parseInt(valueLines.get(i));
            testCase.items.add(new Item(i + 1, value, weight));
        }

        // 读取参考答案
        Path solutionFile = testCaseDir.resolve(testCaseName + "_s.txt");
        List<String> solutionLines = Files.readAllLines(solutionFile);
        for (String line : solutionLines) {
            testCase.solution.add(Integer.parseInt(line));
        }

        return testCase;
    }

    /**
     * 解决0/1背包问题
     */
    private static KnapsackResult solveKnapsack(List<Item> items, int capacity) {
        int n = items.size();
        int[][] dp = new int[n + 1][capacity + 1];
        boolean[][] keep = new boolean[n + 1][capacity + 1];

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

        List<Integer> selectedItems = new ArrayList<>();
        int remainingWeight = capacity;
        for (int i = n; i > 0; i--) {
            if (keep[i][remainingWeight]) {
                selectedItems.add(items.get(i - 1).id);
                remainingWeight -= items.get(i - 1).weight;
            }
        }

        // 转换为选择标记列表 (1表示选，0表示不选)
        List<Integer> selection = new ArrayList<>(Collections.nCopies(n, 0));
        for (int id : selectedItems) {
            selection.set(id - 1, 1);
        }

        return new KnapsackResult(dp[n][capacity], selection);
    }

    /**
     * 写入输出文件
     */
    private static void writeOutputFile(Path outFile, KnapsackResult result,
                                        List<Item> items, long duration) throws IOException {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(outFile))) {
            // 写入选择结果
            for (int select : result.selectedItems) {
                writer.println(select);
            }
            // 写入执行时间
            writer.println("# Execution time: " + duration + "ns");
        }
    }

    /**
     * 验证答案是否正确
     */
    private static boolean verifySolution(List<Integer> computed, List<Integer> reference) {
        if (computed.size() != reference.size()) {
            return false;
        }
        for (int i = 0; i < computed.size(); i++) {
            if (!computed.get(i).equals(reference.get(i))) {
                return false;
            }
        }
        return true;
    }

    static class KnapsackResult {
        int maxValue;
        List<Integer> selectedItems; // 这里改为1/0的选择列表

        public KnapsackResult(int maxValue, List<Integer> selectedItems) {
            this.maxValue = maxValue;
            this.selectedItems = selectedItems;
        }
    }
}