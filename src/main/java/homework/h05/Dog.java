package homework.h05;

public class Dog extends WarItem{

    public Dog() {
        super(50, 5);
    }

    @Override
    public void attack(WarItem opponent) {
        //咬人一下死
        if (opponent.getClass().getSuperclass() == Soldier.class) {
            super.attack(opponent, Integer.MAX_VALUE);
        }else{
            super.attack(opponent);
        }

    }
}
