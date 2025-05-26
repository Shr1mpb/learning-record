/**
 * 
 */
package homework.frontend.h52;

/**
 *  兔子繁殖问题,难度系数3星
 *  
 * @author cjy
 *
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 兔子繁殖问题
		// 此题目结果不要求精确匹配
		// 兔子早上生 还是晚上生  对结果会有影响,此题目要求误差不超过20% 
		//result.txt供参考，不需要同学们输出result.txt
		// 1对兔子出生以后经过180天可以生出一窝（2对）兔子，以后每隔90天繁殖一次生出一窝（2对）兔子
		// 每对兔子的寿命是700天 
		// 日期不会超过2000
		// 写一个程序计算初始n对兔子，经过m天以后，会有多少对存活的兔子
		// 要求在n<=5 m<=2000情况下 ，计算时间不超过5000ms

		RabbitCount    home = new RabbitCount    ();
		int target=41324;
		long l=System.currentTimeMillis();
		int count = home.getLivingRabbit(1, 1500);
		System.out.println(count);
		if(Math.abs( 1-count*1.0/target)<0.2) {
			System.out.println("pass ");
		}
		System.out.println((System.currentTimeMillis()-l)+" cost");
		 
		 
	}

}
