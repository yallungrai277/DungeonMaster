package mazegame.entity;

import java.util.ArrayList;

public class Inventory {
	
	private String itemName;
	private String itemCategory;
	private String itemDescription;
	private double weight;
	private double value;
	private String damage;
	private double bonusPoints;
	private String equipped;
		
	private ArrayList<Inventory> inventoryList;

	public Inventory(String itemName, String itemCategory, String itemDescription, double weight, double value, String damage, double bonusPoints, String equipped)
	{
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.itemDescription = itemDescription;
		this.weight = weight;
		this.value = value;
		this.damage = damage;
		this.bonusPoints = bonusPoints;
		this.equipped = equipped;
				
	}
		 
	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	public String isEquipped() {
		return equipped;
	}

	public void setEquipped(String equipped) {
		this.equipped = equipped;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public double getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(double bonusPoints) {
		this.bonusPoints = bonusPoints;
	}
	
}
