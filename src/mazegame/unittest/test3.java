package mazegame.unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mazegame.entity.Inventory;
import mazegame.entity.Player;

class test3 {

	@Test
	void testretrieveInventoryItemFunction() {

		Player player= new Player("Sam");
		//creating inventory of items
		Inventory potion = new Inventory("Potion","collectible","A potion", 0.3,0,"N/A",0,"N/A");
		Inventory sword = new Inventory("sword", "weapon", "A power sword", 3, 10, "4d8", 0, "yes"); 
		Inventory coin = new Inventory("Coin", "collectible", "A Coin", 0.1, 15, "N/A", 0, "N/A");
		Inventory wooden = new Inventory("wooden", "shield", "A wooden shield", 5,2, "N/A", 1, "yes");
		Inventory padded = new Inventory("padded","armor", "A padded armor", 4,5,"N/A", 1, "yes");

		//Storing in the inventoryList
		ArrayList <Inventory> inventoryList = new ArrayList<Inventory>();
		inventoryList.add(potion); 
		inventoryList.add(sword); 
		inventoryList.add(coin);
		inventoryList.add(wooden);
		inventoryList.add(padded);
		player.setInventoryList(inventoryList);
		
		//asserting against every items
		assertEquals(potion, player.retrieveInventoryItem("potion", player));
		assertEquals(null, player.retrieveInventoryItem("test", player));
		assertEquals(sword, player.retrieveInventoryItem("sword", player));
		assertEquals(coin, player.retrieveInventoryItem("coin", player));
		assertEquals(wooden, player.retrieveInventoryItem("wooden", player));
		assertEquals(padded, player.retrieveInventoryItem("padded", player));
		
	}

}
