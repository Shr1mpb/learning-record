package LeetCodeHot;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache extends LinkedHashMap<Integer,Integer>{
    //神了 我自己写了几个瞎实现的，没想到LinkedHashMap这个数据结构(hashmap+双向链表)
    //而且它自己还有removeEldestEntry方法 神中神
    /**
     * LRU 缓存
     * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
     * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     */

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
    //    private final HashMap<Integer, Integer> myCache;
//    private final PriorityQueue<QueueNode> cacheUsedCount;
//    private int cacheStoredCount;
//    private final int capacity;
//    private int recentCount;//最近使用的数字 越近越大
//    private int formalCount = -1;
//    private final ReentrantLock lock = new ReentrantLock();
//    public LRUCache(int capacity) {
//        this.myCache = new HashMap<>(capacity);
//        this.cacheUsedCount = new PriorityQueue<>(Comparator.comparingInt(node -> node.count));
//        this.cacheStoredCount = 0;
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        if (myCache.containsKey(key)) {
//            if (formalCount == -1) {
//                formalCount = recentCount;
//            }
//            cacheUsedCount.removeIf(queueNode -> queueNode.key == key);
//            cacheUsedCount.add(new QueueNode(key, recentCount++));
//            return myCache.get(key);
//        }else{
//            return -1;
//        }
//    }
//
//    public void put(int key, int value) {
//        //加锁以保证不会超出容量
//        lock.lock();
//        try{
//            if (myCache.containsKey(key)) {//有该元素 直接改
//                myCache.put(key, value);
//                cacheUsedCount.removeIf(queueNode -> queueNode.key == key);
//                cacheUsedCount.add(new QueueNode(key, recentCount++));
//            }else{//无该元素 要添加
//                if (cacheStoredCount == capacity) {//超容量了
//                    //先删除
//                    myCache.remove(cacheUsedCount.poll().key);
//                    cacheStoredCount--;
//                }
//                //添加
//                myCache.put(key, value);
//                if (formalCount == -1) {
//                    formalCount = recentCount;
//                }
//                cacheUsedCount.removeIf(queueNode -> queueNode.key == key);
//                cacheUsedCount.add(new QueueNode(key, recentCount++));
//                cacheStoredCount++;
//            }
//        }finally {
//            lock.unlock();
//        }
//    }
//    private class QueueNode{
//        private int key;
//        private int count;
//        private QueueNode(int key,int count){
//            this.key = key;
//            this.count = count;
//        }
//    }
//
//
//    public static void main(String[] args) {
//        LRUCache lruCache = new LRUCache(2);
//        lruCache.put(1, 1);
//        lruCache.put(2, 2);
//        int i = lruCache.get(1);
//        lruCache.put(3, 3);
//        int i1 = lruCache.get(2);
//        lruCache.put(4, 4);
//        int i2 = lruCache.get(1);
//        int i3 = lruCache.get(3);
//        int i4 = lruCache.get(4);
//        System.out.println(i);
//        System.out.println(i1);
//        System.out.println(i2);
//        System.out.println(i3);
//        System.out.println(i4);
//    }
}
