package homework.h17;

import java.util.ArrayList;
import java.util.List;

public class GameBase extends GameObject {

	private Player player;
	private List<GameObject> items = new ArrayList<>();

	public List<GameObject> getItems() {
		return items;
	}

	public GameBase(int x, int y) {
		super(x, y);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public static GameBase createGameBase(int x, int y, Player player) {

		GameBase gameBase = new GameBase(x, y);
		gameBase.setHealth(PropLoadConfig.getBaseHealth());
		gameBase.setRange(PropLoadConfig.getBaseRange());
		gameBase.setStrength(PropLoadConfig.getBaseStrength());
		gameBase.setPlayer(player);;
		gameBase.setGameBase(gameBase);
		gameBase.getItems().add(gameBase);
		BattleField.getGameBases().add(gameBase);
		return gameBase;
	}

	public Building building(EnumObjectType type, int x, int y) {
		if (type == EnumObjectType.barrack) {
			Barrack barrack = new Barrack(x, y);
			barrack.setHealth(PropLoadConfig.getBarrackHealth());
			barrack.setRange(PropLoadConfig.getBarrackRange());
			barrack.setStrength(PropLoadConfig.getBarrackStrength());
			barrack.setGameBase(this);
			items.add(barrack);
			return barrack;
		} else if (type == EnumObjectType.warFactory) {
			WarFactory warFactory = new WarFactory(x, y);
			warFactory.setHealth(PropLoadConfig.getWarFactoryHealth());
			warFactory.setRange(PropLoadConfig.getWarFactoryRange());
			warFactory.setStrength(PropLoadConfig.getWarFactoryStrength());
			warFactory.setGameBase(this);
			items.add(warFactory);
			return warFactory;
		} else {
			throw new RuntimeException("创建的类型不存在！");
		}

	}
}
