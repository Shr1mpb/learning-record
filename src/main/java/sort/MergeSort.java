package sort;

/*
    归并排序 O(nlog2 n) 空间O(n)
        将两个或以上的有序表合成一个新的有序表
        两两归并 然后最后合成新的序列 稳定
 */
public class MergeSort {

    // 主函数
    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return; // 如果数组长度小于2则直接返回
        }
        int mid = array.length / 2; // 找到中间索引

        // 分割数组
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        // 拷贝到左右子数组
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        // 递归排序左右子数组
        mergeSort(left);
        mergeSort(right);

        // 合并两部分
        merge(array, left, right);
    }

    // 合并函数
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // 合并左右子数组
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // 拷贝剩余的元素
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

}
