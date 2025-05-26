package homework.oop.h05;

public class WarItem {
    private int health;
    private int damage;
    private boolean destroyed;

    public WarItem(int health, int damage) {
        this.health = health;
        this.damage = damage;
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

    protected void attack(WarItem opponent, int damage) {
        //检查对手是否死亡
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
}
