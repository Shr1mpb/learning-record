package sort;

import java.util.Arrays;

public class InsertionSort {
    /*
        插入排序分几种
          *稳定排序： 一样的数据 和原先的顺序保持不变
            直接插入排序O(n^2)
            折半插入排序
                引入 low、mid、high
            2路插入排序
                最开始定一个变量 比它小的放左边 大的右边 然后两边分别插入排序
            表插入排序
                遍历静态链表 然后置指针 最后形成闭环 (0位置存放起始)
                静态链表重新排序方法： 从左到右一个位置一个位置放，放的时候交换两个元素，并在把当前位置的元素的指针改为之前位置的序号
                                    这里的指针就是为了实现重排，所以重排后指针就没啥用了
            希尔排序O(n（log2 n）^2)  空间O(1)
                不稳定，增量序列必须是递减的
                思路： 指定一个gap 两两比较 直到gap=1
                比如 1 2 3 4
                    gap = 2
                    就是 1和3比较 2和4比较
     */
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 9, 11, 56, 31, 20, 4, 22, 88, 96, 15, 18, 62, 34};
//        insertSort1(nums);
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    //直接插入排序 类似于手牌整排 从右到左比较
    public static void insertSort1(int[] nums) {
        if (nums.length <= 1) {
            System.out.println(Arrays.toString(nums));
        }

        for (int i = 1; i <nums.length; i++) {
            int current = nums[i];
            int j = i-1;
            for (; j >= 0; j--) {
                if (current < nums[j]) {
                    nums[j+1] = nums[j];
                }else {
                    break;
                }
            }
            nums[j+1] = current;
        }
        System.out.println(Arrays.toString(nums));
    }

    //快排思路：
    public static void quickSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        quickSortHelper(nums, 0, nums.length - 1);
    }
    private static void quickSortHelper(int[] nums, int low, int high) {
        if (low < high) {
            int pivot = partition(nums, low, high);
            quickSortHelper(nums, low, pivot - 1);
            quickSortHelper(nums, pivot + 1, high);
        }
    }
    private static int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        int left = low + 1;
        int right = high;
        while (left <= right) {
            //找到左边第一个大于
            while(left <= right && nums[left] < pivot) {
                left++;
            }
            //找到右边第一个小于
            while(left <= right && nums[right] > pivot) {
                right--;
            }
            //左<=右 交换
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        //基准放在正确的位置
        swap(nums, low, right);
        return right;

    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
