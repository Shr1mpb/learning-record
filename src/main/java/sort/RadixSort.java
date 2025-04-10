package sort;

/*
    基数排序 O(nlog2 n) 稳定 空间：n
        使用多关键字排序方法：
            LSD（Least Significant Digit first） 最低位优先法
            和MSD（Most Significant Digit first） 最高位优先法
 */

    /*
        最低位优先：例如一组数字被链表串起来
            设置十个队列
            第一次分配 第一次收集： 遍历链表，把个位数从小到大串起来 (先在队列中摆放，即遍历一次即可，然后把十个队列串起来)
            第二次分配 第二次收集： 遍历第一次收集的结果，把十位数从小到大串起来
            第三次分配 第三次手机： 遍历第二次收集的结果，把百位数从小到大串起来
                即得有序序列

     */
public class RadixSort {
}
