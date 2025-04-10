package sort;

public class InverseNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{6, 2, 3, 1};
        System.out.println(countInversions(nums));
    }
        // 自定义类，保存排序后的数组和逆序数
        static class Result {
            int[] sortedArray;
            int inversionCount;
            Result(int[] sortedArray, int inversionCount) {
                this.sortedArray = sortedArray;
                this.inversionCount = inversionCount;
            }
        }

        // 主入口方法
        public static int countInversions(int[] array) {
            Result result = mergeSortAndCount(array, 0, array.length - 1);
            return result.inversionCount;
        }

        // 递归分治 + 合并统计
        private static Result mergeSortAndCount(int[] array, int start, int end) {
            if (start >= end) {
                return new Result(new int[]{array[start]}, 0);
            }

            int mid = start + (end - start) / 2;

            // 递归处理左半和右半
            Result leftResult = mergeSortAndCount(array, start, mid);
            Result rightResult = mergeSortAndCount(array, mid + 1, end);

            // 合并左右并统计跨逆序数
            Result mergedResult = mergeAndCount(
                    leftResult.sortedArray,
                    rightResult.sortedArray
            );

            // 总逆序数 = 左 + 右 + 合并时的跨逆序数
            int totalInversions = leftResult.inversionCount
                    + rightResult.inversionCount
                    + mergedResult.inversionCount;

            return new Result(mergedResult.sortedArray, totalInversions);
        }

        // 合并两个有序数组，并统计跨逆序数
        private static Result mergeAndCount(int[] left, int[] right) {
            int[] merged = new int[left.length + right.length];
            int i = 0, j = 0, k = 0;
            int invCount = 0;

            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    merged[k++] = left[i++];
                } else {
                    merged[k++] = right[j++];
                    // 左半剩余元素均与 right[j] 构成逆序对
                    invCount += left.length - i;
                }
            }

            // 处理剩余元素
            while (i < left.length) merged[k++] = left[i++];
            while (j < right.length) merged[k++] = right[j++];

            return new Result(merged, invCount);
        }

}
