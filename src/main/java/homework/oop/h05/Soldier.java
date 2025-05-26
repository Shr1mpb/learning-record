package homework.oop.h05;

public class Soldier extends WarItem{
    private static int livingSoldierCount;
    private static int deadedSoldierCount;
    {
        //普通代码块
        //每当有对象创建时，Soldier的livingCount++
        livingSoldierCount++;
    }
    public Soldier(int health, int damage) {
        super(health, damage);
    }

    public Soldier(int health) {
        super(health);
    }

    public static int getLivingSoldierCount() {
        return livingSoldierCount;
    }

    public static int getDeadedSoldierCount() {
        return deadedSoldierCount;
    }

    public static void setDeadedSoldierCount(int deadedSoldierCount) {
        Soldier.deadedSoldierCount = deadedSoldierCount;
    }

    public static void setLivingSoldierCount(int livingSoldierCount) {
        Soldier.livingSoldierCount = livingSoldierCount;
    }

    @Override
    public void attack(WarItem opponent) {
        //打狗一下死
        if (opponent.getClass() == Dog.class && this.getClass() == RifleSoldier.class) {
            super.attack(opponent, Integer.MAX_VALUE);
        }else {
            super.attack(opponent);
        }
    }
}
