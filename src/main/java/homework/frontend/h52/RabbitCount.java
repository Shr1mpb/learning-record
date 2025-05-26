/**
 * 
 */
package homework.frontend.h52;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class RabbitCount {

	/**
	 * 1对兔子出生以后经过180天可以生出一窝（2对）兔子，以后每隔90天繁殖一次生出一窝（2对）兔子
	 * 每对兔子的寿命是700天 
	 * @param startCount 第0天 开始的时候初生的兔子对数
	 * @return 目前系统中存活的兔子的对数
	 */
	public int getLivingRabbit(int startCount, int days) {
		if (startCount <= 0 || days < 0) {
			return 0;
		}

		// 兔子对象，记录出生时间和繁殖时间
		class Rabbit {
			int birthDay;
			int lastBreedDay;

			Rabbit(int birthDay) {
				this.birthDay = birthDay;
				this.lastBreedDay = birthDay - 180; // 初始设置为可以立即繁殖
			}

			boolean canBreed(int currentDay) {
				int age = currentDay - birthDay;
				if (age < 180) return false; // 未到第一次繁殖年龄

				if (lastBreedDay == birthDay - 180) {
					// 第一次繁殖
					return true;
				} else {
					// 后续繁殖检查是否间隔90天
					return (currentDay - lastBreedDay) >= 90;
				}
			}

			boolean isAlive(int currentDay) {
				return (currentDay - birthDay) < 700;
			}
		}

		List<Rabbit> rabbits = new ArrayList<>();

		// 初始化起始兔子
		for (int i = 0; i < startCount; i++) {
			rabbits.add(new Rabbit(0));
		}

		// 模拟每一天
		for (int day = 1; day <= days; day++) {
			List<Rabbit> newRabbits = new ArrayList<>();

			// 检查每只兔子是否可以繁殖
			for (Rabbit rabbit : rabbits) {
				if (rabbit.canBreed(day)) {
					// 繁殖2对新兔子
					newRabbits.add(new Rabbit(day));
					newRabbits.add(new Rabbit(day));
					rabbit.lastBreedDay = day;
				}
			}

			// 添加新出生的兔子
			rabbits.addAll(newRabbits);

			// 移除死亡的兔子（使用迭代器安全删除）
			int finalDay = day;
			rabbits.removeIf(rabbit -> !rabbit.isAlive(finalDay));
		}

		return rabbits.size();
	}
}
