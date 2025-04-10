package homework.h08;

public class Bank {
    private int money;
    public void save(int i) {
        money += i;
    }
    public void get(int i) {
        if (money - i < 0) {
            throw new NoMoneyException();
        }
        money -= i;
    }
}
