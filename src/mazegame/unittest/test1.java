package mazegame.unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mazegame.entity.Inventory;
import mazegame.entity.Player;

class test1 {

	@Test
	void testgetEquippedWeaponFunction() {
		Player player = new Player("Kevin");
		Player player2 = new Player("Thomas");
		
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
		player2.setInventoryList(new ArrayList<Inventory>());
		
		//asserting each possible cases in a function
		assertEquals(sword, player.getEquippedWeapon(player));
		assertEquals(null,player.getEquippedWeapon(player2));
	}
}
