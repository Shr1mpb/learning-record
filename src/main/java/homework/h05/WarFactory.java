package homework.h05;


public class WarFactory extends Building{

    public WarFactory() {
        super(100);
    }

    public Object building(EnumObjectType type) {
        if (type == EnumObjectType.mediumTank) {
            MediumTank mediumTank = new MediumTank();
            itemAdd(mediumTank);
            return mediumTank;
        }else if (type == EnumObjectType.heavyTank) {
            HeavyTank heavyTank = new HeavyTank();
            itemAdd(heavyTank);
            return heavyTank;
        }else{
            throw new RuntimeException("创建的类型不存在！");
        }
    }
}
