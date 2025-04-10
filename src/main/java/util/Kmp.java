package util;


import java.util.Arrays;
import java.util.HashSet;

public class Kmp {
    public static void main(String[] args) {
        String s = "aabaac";
        System.out.println("next is " + Arrays.toString(getNextArray(s)));
        System.out.println("nextVal is " + Arrays.toString(getNextValArray(s)));
    }


    /**
     * 求一个字符串的next数组
     * @param s 传入字符串
     * @return 返回int[]类型的next数组
     */
    public static int[] getNextArray(String s) {
        char[] charArray = s.toCharArray();
        int len = charArray.length;
        int[] ret = new int[len];
        if (len == 1){
            return new int[]{0};
        }
        ret[0] = 0;
        ret[1] = 1;
        for (int i = 2; i < len; i++) {
            //后缀
            HashSet<String> suffix = new HashSet<>();
            int temp = 1;
            for (int j = 0; j < i - 1; j++) {
                suffix.add(s.substring(i-j-1, i-j-1+temp ++));
            }
            int valOnI = 1;
            int temp1 = 1;
            for (int j = 0; j < i - 1; j++) {
                if (suffix.contains(s.substring(0, temp1++))) {
                    valOnI++;
                }
            }
            ret[i] = valOnI;
        }
        return ret;
    }

    /**
     * 根据输入的字符串求出其nextVal数组
     * @param s 输入的字符串
     * @return
     */
    private static int[] getNextValArray(String s) {
        int[] nextArray = getNextArray(s);
        int len = nextArray.length;
        int[] ret = new int[len];
        if (len == 1){
            return new int[]{0};
        }
        ret[0] = 0;
        ret[1] = 1;
        for (int i = 2; i < len; i++) {
            if (s.charAt(i) == s.charAt(nextArray[i]-1)) {
                ret[i] = ret[nextArray[i] - 1];
            } else {
                ret[i] = nextArray[i];
            }
        }
        return ret;
    }
}
