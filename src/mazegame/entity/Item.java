package mazegame.entity;

public class Item {
	private String name;
	private String description; 
	private double weight;
	private double value;
	
	public Item(String name, String description, double weight, double value)
	{
		this.name = name; 
		this.description = description;
		this.weight = weight; 
		this.value = value; 
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public double getBonusPoints() {
		// TODO Auto-generated method stub
		return (Double) null;
	}

}
