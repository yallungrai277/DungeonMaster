package mazegame.unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mazegame.entity.Armor;
import mazegame.entity.Inventory;
import mazegame.entity.Item;
import mazegame.entity.Player;
import mazegame.entity.Shield;

class test2 {

	@Test
	void testequipItemFunction() {
		Player player = new Player("Jack");
		
		//creating inventory of items
		Inventory potion = new Inventory("Potion","collectible","A potion", 0.3,0,"N/A",0,"N/A");
		Inventory sword = new Inventory("sword", "weapon", "A power sword", 3, 10, "4d8", 0, "yes"); 
		Inventory coin = new Inventory("Coin", "collectible", "A Coin", 0.1, 15, "N/A", 0, "N/A");
		Inventory wooden = new Inventory("wooden", "shield", "A wooden shield", 5,2, "N/A", 1, "yes");
		Inventory padded = new Inventory("padded","armor", "A padded armor", 4,5,"N/A", 1, "no");
		Inventory axe = new Inventory("axe", "weapon", "A huge axe", 4, 8, "1d6", 0, "no");		
		Inventory buckle = new Inventory("buckle", "shield", "A buckle shield", 5, 15,"N/A",3, "no");
		
		//Storing in the inventoryList
		ArrayList <Inventory> inventoryList = new ArrayList<Inventory>();
		inventoryList.add(potion); 
		inventoryList.add(sword); 
		inventoryList.add(coin);
		inventoryList.add(wooden);
		inventoryList.add(padded);
		inventoryList.add(axe);
		inventoryList.add(buckle);
		player.setInventoryList(inventoryList);
		
		//asserting each possible cases in the function
		assertEquals("The selected item collectibles cannot be equipped", player.equipItem("potion", player));
		assertEquals("Item is already equipped", player.equipItem("sword", player));
		assertEquals("Item equipped successfully", player.equipItem("padded", player));
		assertEquals("The selected item is not in your inventory", player.equipItem("steel", player)); 
		assertEquals("You already have an equipped weapon. Please unequip that first", player.equipItem("axe", player));
		assertEquals("You already have an equipped shield. Please unequip that first", player.equipItem("buckle", player));
		assertEquals("You already have an equipped shield. Please unequip that first", player.equipItem("buckle", player));
	}

}
