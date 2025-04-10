package LeetCodeHot;

import java.util.PriorityQueue;

public class MedianFinder {
    /**
     * 数据流的中位数
     * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
     * 例如 arr = [2,3,4] 的中位数是 3 。
     * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
     * 实现 MedianFinder 类:
     * MedianFinder() 初始化 MedianFinder 对象。
     * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
     * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
     */
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>(); // 小顶堆，存大的那一半（那么堆顶元素就是大半里面最小的那个，即最接近中心的）
        maxHeap = new PriorityQueue<Integer>((x, y) -> y - x);// 大顶堆，存小的那一半（那么堆顶元素就是小半里面最大的那个，即最接近中心的）
    }

    public void addNum(int num) {
        if (minHeap.isEmpty() || num > minHeap.peek()) {
            minHeap.offer(num);
        } else {
            maxHeap.offer(num);
        }
        // -- 维护中位数数量，大堆和小堆堆顶元素是有序的，那堆元素数量超出预期，就移除并放到另一个堆里面
        // 这里确保minHeap 和 maxHeap要么一样多 要么是minHeap多一个
        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        } else if (maxHeap.size() > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else {
            return minHeap.peek();
        }
    }

    /*时间超限
    List<Integer> minHeap;
    public MedianFinder() {
        minHeap = new ArrayList<>();
    }

    public void addNum(int num) {
        minHeap.add(num);
    }

    public double findMedian() {
        if (minHeap.isEmpty()) {
            return 0.0;
        }
        minHeap.sort(Comparator.comparingInt(a -> a));// 底层与Arrays.sort一致，基本数据类型使用双轴快排 引用类型使用归并排序
        Integer[] array = minHeap.toArray(Integer[]::new);
        if (array.length % 2 == 0) {// 偶数
            return (array[array.length / 2] + array[array.length / 2 - 1]) / 2.0;
        } else {
            return array[array.length / 2];
        }
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(-1);
        mf.findMedian();
        mf.addNum(-2);
        mf.findMedian();
        mf.addNum(-3);
        mf.findMedian();
        mf.addNum(-4);
        mf.findMedian();
        mf.addNum(-5);
        mf.findMedian();
    }*/
}
