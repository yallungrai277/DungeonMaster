package mazegame.boundary;

import java.util.ArrayList;

import mazegame.entity.Inventory;
import mazegame.entity.Item;
import mazegame.entity.Location;

public interface IMazeData {
	Location getStartingLocation();
	String getWelcomeMessage();
	ArrayList<Inventory> getStartingInventory();
	
	
}
