package homework.oop.h17;

public class Barrack extends Building {

	public Barrack(int x, int y) {

		// super( Param.BARRACK_HEALTH,Param.BARRACK_STRENGTH);
		// TODO Auto-generated constructor stub
		super(x, y,PropLoadConfig.getBarrackHealth());
	}

	public GameObject traing(EnumObjectType type) {
		GameObject gameObject;

		if (type == EnumObjectType.rifleSoldier) {
			gameObject = new RifleSoldier(this);
			gameObject.setHealth(PropLoadConfig.getRifleSoldierHealth());
			gameObject.setRange(PropLoadConfig.getRifleSoldierRange());
			gameObject.setStrength(PropLoadConfig.getRifleSoldierStrength());
			gameObject.setGameBase(this.getGameBase());
			this.getGameBase().getItems().add(gameObject);
		} else if (type == EnumObjectType.RPGSoldier) {
			gameObject = new RPGSoldier(this);
			gameObject.setHealth(PropLoadConfig.getRpgSoldierHealth());
			gameObject.setRange(PropLoadConfig.getRpgSoldierRange());
			gameObject.setStrength(PropLoadConfig.getRpgSoldierStrength());
			gameObject.setGameBase(this.getGameBase());
			this.getGameBase().getItems().add(gameObject);
		} else if (type == EnumObjectType.dog) {
			gameObject = new Dog(this);
			gameObject.setHealth(PropLoadConfig.getDogHealth());
			gameObject.setRange(PropLoadConfig.getDogRange());
			gameObject.setStrength(PropLoadConfig.getDogStrength());
			gameObject.setGameBase(this.getGameBase());
			this.getGameBase().getItems().add(gameObject);
		}else{
			throw new RuntimeException("创建的类型不正确！");
		}
		gameObject.setX(this.getX());
		gameObject.setY(this.getY());
		return gameObject;
	}

}
