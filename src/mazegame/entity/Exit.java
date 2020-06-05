package mazegame.entity;

public class Exit {
	private String description;
	private Location destination;
	private boolean isLocked;
	
	public Exit (String description, Location destination, boolean isLocked)
	{
		this.setDescription(description);
		this.setDestination(destination);
		this.setLocked(isLocked);
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	
	
}
