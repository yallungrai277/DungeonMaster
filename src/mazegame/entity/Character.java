package mazegame.entity;

public class Character {
	private double agility;
    private double lifePoints;
    private String name;
    private double strength;
    private double weightLimit;
    
//    public Mazegame.Entity.Dice m_Dice;
//    public Mazegame.Entity.Party m_Party;
//    public Mazegame.Entity.Item m_Item;
//    public Mazegame.Entity.Shield m_Shield;
//    public Mazegame.Entity.Weapon m_Weapon;
//    public Mazegame.Entity.Armor m_Armor;
    
    public Character()
    {
    }

    public Character(String name)
    {
        this.setName(name);
    }

	public double getAgility() {
		return agility;
	}

	public void setAgility(double agility) {
		this.agility = agility;
	}

	public double getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(double lifePoints) {
		this.lifePoints = lifePoints;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}
	
	public double getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(double weightLimit) {
		this.weightLimit = weightLimit;
	}
	

}
