package homework.oop.h17;

public class MediumTank extends Tank {

    public MediumTank(WarFactory warFactory) {
        super(PropLoadConfig.getMediumTankHealth(), PropLoadConfig.getMediumTankStrength(), PropLoadConfig.getMediumTankRange(), warFactory);
    }

    public MediumTank(int x, int y) {
        super(PropLoadConfig.getMediumTankHealth(), PropLoadConfig.getMediumTankStrength(), x, y, PropLoadConfig.getMediumTankRange());
    }
}
