package homework.oop.h06;


public class GameBase extends Building {

    private GameBase(int health) {
        super(health);
    }

    private GameBase(int x, int y) {
        super(500, x, y);
    }

    public static GameBase createGameBase(int x,int y) {
        return new GameBase(x, y);
    }

    public Building building(EnumObjectType type, int x, int y) {
        if (type == EnumObjectType.barrack) {
            return new Barrack(x, y);
        } else if (type == EnumObjectType.warFactory) {
            return new WarFactory(x, y);
        } else {
            throw new RuntimeException("创建的类型不存在！");
        }
    }
}
