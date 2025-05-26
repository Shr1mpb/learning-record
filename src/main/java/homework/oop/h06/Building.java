package homework.oop.h06;

import java.util.ArrayList;
import java.util.List;

public class Building extends WarItem {
    private List<WarItem> list = new ArrayList<>();

    public Building(int health) {
        super(health);
    }

    public Building(int health, int x, int y) {
        super(health, x, y);
    }

    protected void itemAdd(WarItem item) {
        list.add(item);
    }
}
