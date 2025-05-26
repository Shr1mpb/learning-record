package homework.oop.h06;

public class WarItem {
    private int health;
    private int damage;
    private boolean destroyed;
    private int x;
    private int y;
    private int attackRange;

    public WarItem(int health, int damage, int x, int y, int attackRange) {
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.attackRange = attackRange;
    }

    protected WarItem(int health, int damage) {
        this.health = health;
        this.damage = damage;
    }

    protected WarItem(int health, int x, int y) {
        this.health = health;
        this.x = x;
        this.y = y;
    }

    protected void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public WarItem(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        return this.damage;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void attack(WarItem opponent) {
        attack(opponent, this.damage);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected void attack(WarItem opponent, int damage) {
        //看看在不在攻击范围内
        if (!isInAttackRange(opponent)) {
            return;
        }
        //检查对手有没有死亡
        if (opponent.isDestroyed()) {
            return;
        }
        opponent.health -= damage;
        //对手血量低于0 置死
        if (opponent.health <= 0) {
            opponent.destroyed = true;
            opponent.health = -1;
            //如果是士兵 让士兵的存活数量-1 死亡数量+1
            if (opponent.getClass().getSuperclass() == Soldier.class) {
                Soldier.setLivingSoldierCount(Soldier.getLivingSoldierCount() - 1);
                Soldier.setDeadedSoldierCount(Soldier.getDeadedSoldierCount() + 1);
            }
        }
    }

    private boolean isInAttackRange(WarItem opponent) {
        //范围内true 范围外false
        return Math.pow(opponent.x - x, 2) + Math.pow(opponent.y - y, 2) <= Math.pow(this.attackRange, 2);
    }
}
