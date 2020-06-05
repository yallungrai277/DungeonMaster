package mazegame.entity;

public class Weapon extends Item{

	private String damage;
		
	public Weapon(String name, String description, double weight, double value,String damage) {
		super(name, description, weight, value);
		this.damage = damage;
		
		// TODO Auto-generated constructor stub
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

}
