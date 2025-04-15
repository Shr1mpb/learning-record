package homework.h19.q03;

public class ArrayUtil {
    public int getMin(int[] array) {
        int min = array[0];
        for (int cur : array) {
            min = Math.min(min, cur);
        }
        return min;
    }
}
