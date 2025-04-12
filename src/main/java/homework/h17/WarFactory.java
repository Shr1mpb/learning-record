package homework.h17;

import java.util.ArrayList;
import java.util.List;

public class WarFactory extends Building {
    private List<Tank> tanks = new ArrayList<>();
    public WarFactory(int x,int y) {
        super(x, y, PropLoadConfig.getWarFactoryHealth());
    }

    public GameObject building(EnumObjectType type) {
        if (type == EnumObjectType.mediumTank) {
            Tank mediumTank = new MediumTank(this);
            mediumTank.setGameBase(this.getGameBase());
            tanks.add(mediumTank);
            this.getGameBase().getItems().add(mediumTank);
            return mediumTank;
        }else if (type == EnumObjectType.heavyTank) {
            HeavyTank heavyTank = new HeavyTank(this);
            heavyTank.setGameBase(this.getGameBase());
            tanks.add(heavyTank);
            this.getGameBase().getItems().add(heavyTank);
            return heavyTank;
        }else{
            throw new RuntimeException("创建的类型不存在！");
        }
    }
}
