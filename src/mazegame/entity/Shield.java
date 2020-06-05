package mazegame.entity;

public class Shield extends Item{

	private double bonusPoints;
	
	public Shield(String name, String description, double weight, double value, double bonusPoints) {
		super(name, description, weight, value);		
		this.bonusPoints = bonusPoints;
	}

	public double getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoint(double bonusPoints) {
		this.bonusPoints = bonusPoints;
	}


	
	/*public Shield(String name, String description, double weight, double price, double bonusPoints) {
		super(name, description, weight, price, bonusPoints);
		// TODO Auto-generated constructor stub
	}*/

}
