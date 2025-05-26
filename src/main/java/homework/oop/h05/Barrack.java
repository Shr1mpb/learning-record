package homework.oop.h05;

public class Barrack extends Building{

    public Barrack() {
        super(100);
    }

    public Object traing(EnumObjectType type){
        if (type == EnumObjectType.rifleSoldier) {
            RifleSoldier rifleSoldier = new RifleSoldier();
            itemAdd(rifleSoldier);
            return rifleSoldier;
        } else if (type == EnumObjectType.RPGSoldier) {
            RPGSoldier rpgSoldier = new RPGSoldier();
            itemAdd(rpgSoldier);
            return rpgSoldier;
        } else if (type == EnumObjectType.dog) {
            Dog dog = new Dog();
            itemAdd(dog);
            return dog;
        }else {
            throw new RuntimeException("创建的类型不存在！");
        }
    }

}
