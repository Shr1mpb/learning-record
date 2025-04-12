package homework.h17;


public class Tank extends GameObject {

    public Tank(int health, int damage) {
        super(health, damage);
    }

    public Tank(int health, int damage, int x, int y, int attackRange) {
        super(health, damage, x, y, attackRange);
    }

    public Tank(int health, int damage, int attackRange, WarFactory warFactory) {
        super(health, damage, warFactory.getX(), warFactory.getY(), attackRange);
    }
}
