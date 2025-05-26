package homework.oop.h06;

public class Tank extends WarItem {

    public Tank(int health, int damage) {
        super(health, damage);
    }

    public Tank(int health, int damage, int x, int y, int attackRange) {
        super(health, damage, x, y, attackRange);
    }
}
