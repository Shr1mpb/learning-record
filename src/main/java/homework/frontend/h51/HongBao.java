package homework.frontend.h51;

import java.util.Arrays;
import java.util.Random;

public class HongBao {

	/**
	 * 
	 * @param total  红包总金额，以元为单位，精确到分，系统测试的时候保证总金额至少够每人分得1分钱
	 * @param personCount 分红包的总人数>0
	 * @return 每个人分得的钱数
	 * 规则遵循微信分红包规则 例如：
	 * 要求 每人分得的钱数总和=total
	 * 每个人分得钱数必须是正数，且不能少于1分
	 * 
	 */
	public double[] getHongbao(double total, int personCount) {
		// 将金额转换为分，避免浮点数精度问题
		int totalInCents = (int) Math.round(total * 100);

		// 验证参数
		if (personCount <= 0) {
			return new double[0];
		}
		if (totalInCents < personCount) {
			throw new IllegalArgumentException("总金额不足以每人分得1分钱");
		}

		// 特殊情况：总金额刚好等于每人1分钱
		if (totalInCents == personCount) {
			double[] result = new double[personCount];
			Arrays.fill(result, 0.01);
			return result;
		}

		// 初始化结果数组
		double[] result = new double[personCount];

		// 特殊情况：只有一个人
		if (personCount == 1) {
			result[0] = total;
			return result;
		}

		// 随机分配算法
		Random random = new Random();
		int remainingCents = totalInCents;
		int remainingPeople = personCount;

		// 为前n-1个人分配金额
		for (int i = 0; i < personCount - 1; i++) {
			// 计算当前可分配的最大金额（确保剩下的人每人至少1分钱）
			int max = remainingCents - remainingPeople + 1;
			// 随机分配1分到max分
			int amount = random.nextInt(max) + 1;

			result[i] = amount / 100.0;
			remainingCents -= amount;
			remainingPeople--;
		}

		// 最后一个人得到剩余的所有金额
		result[personCount - 1] = remainingCents / 100.0;

		// 打乱顺序（微信红包顺序是随机的）
		shuffleArray(result);

		return result;
	}

	private void shuffleArray(double[] array) {
		Random rnd = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// 交换
			double temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}
}
