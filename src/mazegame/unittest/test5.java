package mazegame.unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mazegame.entity.Inventory;
import mazegame.entity.Player;

class test5 {

	@Test
	void testunequipItemFunction() {
		
		Player player = new Player("Hallan");
		player.setLifePoints(15);
		
		Player player2 = new Player("Greg");
		player2.setLifePoints(0.5);
		
		Inventory sword = new Inventory("sword", "weapon", "A power sword", 3, 10, "4d8", 0, "yes"); 
		Inventory wooden = new Inventory("wooden", "shield", "A wooden shield", 5,2, "N/A", 1, "yes");
		Inventory padded = new Inventory("padded","armor", "A padded armor", 4,5,"N/A", 1, "yes");
		Inventory axe = new Inventory("axe", "weapon", "A huge axe", 4, 8, "1d6", 0, "no");		
		Inventory buckle = new Inventory("buckle", "shield", "A buckle shield", 5, 15,"N/A",3, "no");
		
		//Storing in the inventoryList
		ArrayList <Inventory> inventoryList = new ArrayList<Inventory>();
		 
		inventoryList.add(sword); 
		inventoryList.add(wooden);
		inventoryList.add(padded);
		inventoryList.add(axe);
		inventoryList.add(buckle);
		player.setInventoryList(inventoryList);
		player2.setInventoryList(inventoryList);

		//asserting all possible cases
		
		assertEquals("Item unequipped successfully", player.unequipItem("sword", player));
		assertEquals("Item must be equipped in order to unequip", player.unequipItem("buckle", player));
		assertEquals("The item cannot be unequipped because unequipping it will make your lifepoints to zero"
								+ "\nPlease restore life points by using potion and try unequipping again", player2.unequipItem("padded", player2));
		
	}

}
