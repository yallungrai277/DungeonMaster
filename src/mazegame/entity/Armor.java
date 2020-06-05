package mazegame.entity;

public class Armor extends Item {
	private double bonusPoints;

	public Armor(String name, String description, double weight, double value, double bonusPoints) {
		super(name, description, weight, value);
		 
		this.bonusPoints = bonusPoints;
		// TODO Auto-generated constructor stub
	}

	
	public double getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(double bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	

	
	//private double bonusPoints;
	
	/*public Armor(String name, String description, double weight, double price, double bonusPoints) {
		super(name, description, weight, price);
		// TODO Auto-generated constructor stub
		this.bonusPoints = bonusPoints;
	}

	public double getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(double bonusPoints) {
		this.bonusPoints = bonusPoints;
	}*/
	
		
}
