package homework.oop.h06;


public class WarFactory extends Building {

    public WarFactory() {
        super(100);
    }

    public WarFactory(int x, int y) {
        super(100, x, y);
    }

    public Object building(EnumObjectType type) {
        if (type == EnumObjectType.mediumTank) {
            MediumTank mediumTank = new MediumTank(getX(), getY());
            itemAdd(mediumTank);
            return mediumTank;
        }else if (type == EnumObjectType.heavyTank) {
            HeavyTank heavyTank = new HeavyTank(getX(), getY());
            itemAdd(heavyTank);
            return heavyTank;
        }else{
            throw new RuntimeException("创建的类型不存在！");
        }
    }
}
