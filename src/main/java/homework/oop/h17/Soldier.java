package homework.oop.h17;

public abstract class Soldier extends GameObject {

	public static int livingSoldierCount = 0;
	public static int deadedSoldierCount = 0;

	public Soldier(Barrack object) {
		super(object.getX(),object.getY());
		livingSoldierCount++;
		// TODO Auto-generated constructor stub
	}


	public static int getLivingSoldierCount() {
		return livingSoldierCount;
	}

	public static int getDeadedSoldierCount() {
		return deadedSoldierCount;
	}

	
}
