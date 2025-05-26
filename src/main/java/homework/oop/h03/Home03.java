/**
 * 
 */
package homework.oop.h03;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * 作业  完成本类的方法
 */
public class Home03 {

	/**
	 * 判断一个数是否是水仙花数，
	 * 所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。例如：153是一个 "水仙花数 "，因为153=1的三次方＋5的三次方＋3的三次方。
	 * 难度系数2星
	 * @param i
	 * @return
	 */
	public static boolean isNarcissisticNum(int i) {
		// 完成本函数里面的代码
		//个位
		int index1 = i % 10;
		//十位
		int index2 = i % 100 / 10;
		//百位
		int index3 = i / 100;
        return index3 * index3 * index3 + index2 * index2 * index2 + index1 * index1 * index1 == i;
    }
	
	/**
	 * 判断一个数是否是完全数，如果不清楚完全数，可以百度
	 * 一个数如果恰好等于它的因子之和，这个数就称为 "完全数 "。
	 * 难度系数2星
	 * @param i
	 * @return
	 */
	public static boolean Perfectnumber(int i) {
		// 完成本函数里面的代码
		List<Integer> list = new ArrayList<>();
		for (int j = 1; j <= i / 2; j++) {
			if (i % j == 0) {
				list.add(j);
			}
		}
		int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
        return sum == i;
    }
	
	/**
	 * 计算n的阶乘(n>=0，且结果不会超出整数范围)
	 * @param n
	 * @return
	 */
	public static int  factorial(int n) {
		int ret = 1;
		for (int i = 1; i <= n; i++) {
			ret *= i;
		}
		return ret;
	}
	
	/**
	 * 将一个二进制的数变成10进制的数字 
	 * 例如将 1000 转换为 8，将10000 转换为16
	 * @return
	 */
	public static String binToDec(String bin) {
//		//如何把一个字符串转换为整数，供参考
//		int i=Integer.valueOf("1");
//		//如何把一个整数转换为字符串，供参考
//		String s=String.valueOf(i);
		char[] charArray = bin.toCharArray();
		int ret = 0;
		for (int i = charArray.length - 1; i >= 0; i--) {
			if (charArray[i] == '1') {
				int addNum = 1;
				for (int j = 0; j < charArray.length - i - 1; j++) {
					addNum *= 2;
				}
				ret += addNum;
			}
		}
		// 完成本函数里面的代码
		return String.valueOf(ret);
	}
	
	/**
	 * 设二元一次方程 a*x*x+bx+c=0;
	 * 的系数分别为a,b,c
	 * 请将这个方程的两个实数根放在数组中返回,如果没有实数解则返回null
	 * 例如 getRoot(1,-2,1)返回 {1,1}
	 * 例如 getRoot(1,-2,2)返回 null
	 * 难度系数1星
	 * @return
	 */
	public static double[] getRoot(double a,double b,double c) {
		double delta = b * b - 4 * a * c;
		if (delta < 0) {
			return null;
		}
		//一个解
		if (delta == 0) {
			double ret = -b / (2 * a);
			return new double[]{ret};
		}
		//两个解
		double ret1 = (-b + Math.sqrt(delta)) / (2 * a);
		double ret2 = (-b - Math.sqrt(delta)) / (2 * a);
		return new double[]{ret1, ret2};
	}

	/**
	 * 返回杨辉三角( Pascal  triangle,请百度 杨辉三角 或者 Pascal  triangle )第i行的系数，杨辉三角第一行定义为1
	 * 提示：从顶部的单个1开始，下面一行中的每个数字都是上面两个数字的和
	 * 例如getPascalTriangle(1)返回{1}，
	 * getPascalTriangle(2)返回{1,1}
	 * 测试的时候，系数肯定不会超过int的范围
	 * 难度系数3星
	 *
	 * @param i
	 * @return
	 */

	public static int[] getPascalTriangle(int i) {
		if (i == 1){
			return new int[]{1};
		}
		i--;
		// 创建一个列表来存储当前行的系数
		List<Integer> row = new ArrayList<>();

		// 计算第i行的系数，C(i-1, j) = (i-1)! / (j! * (i-1-j)!)
		// 可以通过递推公式 C(i-1, j) = C(i-1, j-1) * (i-j) / j 来计算
		int value = 1; // 首个元素C(i-1, 0) 总是1
		for (int j = 0; j <= i; j++) {
			row.add(value); // 将当前元素加入当前行
			// 使用递推公式计算下一个元素的值
			value = value * (i - j) / (j + 1);
		}

		// 将列表转换为数组并返回
		return row.stream().mapToInt(Integer::intValue).toArray();
	}


	
	/**
	 * 去掉一个字符串中所有的空格
	 * 难度系数1星
	 * 例如：zipSpace(" a b  c ")返回 "abc"
	 * @return
	 */
	public static String zipSpace(String str) {
		// 完成本函数里面的代码
		char[] charArray = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : charArray) {
			if (c != ' ') {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
