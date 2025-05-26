package homework.oop.h17;

public class HeavyTank extends Tank {

    public HeavyTank(WarFactory warFactory) {
        super(PropLoadConfig.getHeavyTankHealth(), PropLoadConfig.getHeavyTankStrength(), PropLoadConfig.getHeavyTankRange(), warFactory);
    }

    public HeavyTank(int x, int y) {
        super(PropLoadConfig.getHeavyTankHealth(), PropLoadConfig.getHeavyTankStrength(), x, y, PropLoadConfig.getHeavyTankRange());
    }
}
