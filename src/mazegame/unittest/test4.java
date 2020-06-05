package mazegame.unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mazegame.entity.Inventory;
import mazegame.entity.Player;

class test4 {

	@Test
	void restoreLifePointsFunction() {
		Player player = new Player("Tom");
		player.setLifePoints(5);
		
		Player player2 = new Player("Jason");
		player2.setLifePoints(10);
		
		Player player3 = new Player("Terry"); 
		player3.setLifePoints(20); 
		
		Player player4 = new Player("Jim");
		player4.setLifePoints(19.5);
		
		Inventory potion = new Inventory("Potion","collectible","A potion", 0.3,0,"N/A",0,"N/A");
		Inventory sword = new Inventory("sword", "weapon", "A power sword", 3, 10, "4d8", 0, "yes"); 
		Inventory coin = new Inventory("Coin", "collectible", "A Coin", 0.1, 15, "N/A", 0, "N/A");
		Inventory potion2 = new Inventory("Potion","collectible","A potion", 0.3,0,"N/A",0,"N/A");
		
		ArrayList <Inventory> inventoryList = new ArrayList<Inventory>();
		inventoryList.add(potion); 
		inventoryList.add(sword); 
		inventoryList.add(coin);
		
		ArrayList<Inventory> inventoryList2 = new ArrayList<Inventory>(); 
		inventoryList2.add(potion2);
		
		
		player.setInventoryList(inventoryList);
		player2.setInventoryList(new ArrayList<Inventory>());
		player3.setInventoryList(inventoryList2);
		
		player4.setInventoryList(inventoryList2);
		
		assertEquals("Restored health", player.restoreLifePoints(player));
		assertEquals("The life could not be restored.\n" + 
				"Potion is not available in your inventory", player2.restoreLifePoints(player2));
		assertEquals("You are already at full health", player3.restoreLifePoints(player3));
		assertEquals("Restored health", player.restoreLifePoints(player4));
		
	}

}
