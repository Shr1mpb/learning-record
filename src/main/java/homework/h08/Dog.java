package homework.h08;

public class Dog {
    private int feedCount;

    public void feed() {
        if (feedCount >= 3) {
            throw new RuntimeException("I can not eat more!");
        }
        feedCount++;
    }
}
