package homework.h05;

import java.util.ArrayList;
import java.util.List;

public class Building extends WarItem {
    private List<WarItem> list = new ArrayList<>();

    public Building(int health, int damage) {
        super(health, damage);
    }

    public Building(int health) {
        super(health);
    }

    protected void itemAdd(WarItem item) {
        list.add(item);
    }
}
