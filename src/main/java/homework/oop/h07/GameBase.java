package homework.oop.h07;

public class GameBase extends GameObject {
	public GameBase(int x, int y) {
		super(x, y);
	}

	public static GameBase createGameBase(int x, int y) {
		GameBase gameBase = new GameBase(x,y);
		gameBase.setHealth(500);
		return gameBase;
	}

	public Building building(EnumObjectType type, int x, int y) {
		if (type == EnumObjectType.barrack) {
			Barrack barrack = new Barrack(x, y);
			return barrack;
		}else {
			throw new RuntimeException("创建的类型不存在！");
		}

	}
}
