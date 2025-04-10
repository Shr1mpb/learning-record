package homework.h07;

public class Dog extends GameObject {

	public Dog() {
		super(0,0);
		// super( Param.DOG_HEALTH,Param.DOG_STRENGTH);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Soldier soldier) {
		int dx = this.getX() - soldier.getX();
		int dy = this.getY() - soldier.getY();
		double dis = Math.pow(dx * dx + dy * dy, 0.5);
		if (this.getRange() <= dis) {
			return;
		}
		if (soldier.getHealth() == 0) {
			Soldier.livingSoldierCount--;
			Soldier.deadedSoldierCount++;
		}
		soldier.setHealth(0);
    }

}
