package homework.h06;

public class Barrack extends Building {

    public Barrack() {
        super(100);
    }
    public Barrack(int x, int y) {
        super(100, x, y);
    }

    public Object traing(EnumObjectType type){
        if (type == EnumObjectType.rifleSoldier) {
            RifleSoldier rifleSoldier = new RifleSoldier(getX(), getY());
            itemAdd(rifleSoldier);
            return rifleSoldier;
        } else if (type == EnumObjectType.RPGSoldier) {
            RPGSoldier rpgSoldier = new RPGSoldier(getX(), getY());
            itemAdd(rpgSoldier);
            return rpgSoldier;
        } else if (type == EnumObjectType.dog) {
            Dog dog = new Dog(getX(), getY());
            itemAdd(dog);
            return dog;
        }else {
            throw new RuntimeException("创建的类型不存在！");
        }
    }

}
