package homework.h17;

import java.util.List;

public abstract class GameObject {
	private int range;
	private int x;
	private int strength;

	private int y;

	private int health = 0;

	private int defaultStrength;
	private GameBase gameBase;

	public GameBase getGameBase() {
		return gameBase;
	}

	public void setGameBase(GameBase gameBase) {
		this.gameBase = gameBase;
	}

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GameObject(int x, int y, int health) {
		this.x = x;
		this.y = y;
		this.health = health;
	}

	public GameObject(int health, int damage, int x, int y, int range) {
		this.health = health;
		this.x = x;
		this.y = y;
		this.range = range;
		this.strength = damage;
	}

	@Override
	public String toString() {
		// [A.heavyTank live=true x=90 y=90 health=500]
		return this.getGameBase().getPlayer().getName() + "." + this.getClass().getSimpleName() +
				" live=" + !isDestroyed() + " x=" + this.x + " y=" + this.y + " health=" + this.health;
	}

	// 寻找距离最近的、非己方、存活的对象进行攻击
	public void attack() {
		if (this.getStrength() <= 0 || this.getRange() <= 0 || this.isDestroyed()) {
			return;// 攻击力小于0或者没有攻击距离、已死亡 没必要攻击
		}
		List<GameBase> gameBases = BattleField.getGameBases();
		double minDistance = Double.MAX_VALUE;// 最小距离
		GameObject opponent = null;// 要攻击的对象
		for (GameBase gameBase : gameBases) {
			// 同一基地/同一玩家的基地
			if (gameBase == this.getGameBase() || gameBase.getPlayer() == this.getGameBase().getPlayer()) {
				continue;
			}
			// 不同玩家 说明是对立的
			// 遍历这个基地下的所有存活对象 并找出距离最近的
			List<GameObject> items = gameBase.getItems();
			for (GameObject item : items) {
				if (!item.isDestroyed()) {// 存活
					double distance = getDistance(this, item);
					if (distance < minDistance) {// 更新最小
						opponent = item;
						minDistance = distance;
					}
				}
			}
		}
		// 没有 不做动作
		if (opponent == null) {
			return;
		}
		// 距离外 不攻击
		if (minDistance > this.getRange()) {
			return;
		}
		// 可以攻击
		if (opponent instanceof Soldier) {
			this.attack((Soldier) opponent);
		}else {
			this.attack((GameObject) opponent);
		}
		System.out.println(this.toString() + " 攻击 " + opponent.toString() + " 攻击后 health=" + opponent.getHealth());

	}

	private double getDistance(GameObject gameObject, GameObject item) {
		int dx = gameObject.x - item.x;
		int dy = gameObject.y - item.y;
		double dis = Math.pow(dx * dx + dy * dy, 0.5);
		return dis;
	}

	public void attack(GameObject obj) {
		if (this.isDestroyed()) {
			return;
		}
		int dx = this.x - obj.x;
		int dy = this.y - obj.y;
		double dis = Math.pow(dx * dx + dy * dy, 0.5);
		if (this.range < dis) {
			return;
		}

		obj.health = obj.health - this.strength;
		obj.health = Math.max(obj.health, 0);
	}

	public void attack(Soldier soldier) {
		if (this.isDestroyed()) {
			return;
		}
		int dx = this.x - soldier.getX();
		int dy = this.y - soldier.getY();
		double dis = Math.pow(dx * dx + dy * dy, 0.5);
		if (this.range < dis) {
			return;
		}

		soldier.setHealth(soldier.getHealth() - this.strength);
		if (soldier.getHealth() <= 0) {
			soldier.setHealth(0);
			Soldier.livingSoldierCount--;
			Soldier.deadedSoldierCount++;
		}
	}

	public void changeHealth(int strength) {
		int beforeHealth = this.getHealth();
		this.health = this.health - strength;
		if (beforeHealth > 0 && this.health <= 0) {
			this.dead();
		}
	}

	public void dead() {

	}

	public int getHealth() {
		return health;
	}
	public int getRange() {
		return range;
	}
	public int getStrength() {
		return strength;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isDestroyed() {
		return health <= 0;
	}

	public void move(int dx, int dy) {
		this.x = this.x + dx;
		this.y = this.y + dy;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
