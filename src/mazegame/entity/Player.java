package mazegame.entity;

import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Character {

	private Location currentLocation;
	private ArrayList<Inventory> inventoryList;
	private  boolean finishedTurn;
	private boolean finishedGame;

	public Player()
	{

	}


	public Player(String name)
	{
		super (name);
	}

	public Location getCurrentLocation()
	{
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation)
	{
		this.currentLocation = currentLocation;
	}

	public void setInventoryList(ArrayList<Inventory> inventoryList) {
		// TODO Auto-generated method stub
		this.inventoryList = inventoryList; 
	} 

	public ArrayList<Inventory> getItems()
	{
		return inventoryList;
	}

	public void setCurrentItems(ArrayList<Inventory> inventoryList) {
		// TODO Auto-generated method stub
		this.inventoryList = inventoryList; 
	} 

	public boolean isFinishedTurn()
	{	
		return finishedTurn;
	}

	public void setFinishedTurn(boolean finishedTurn)
	{
		this.finishedTurn = finishedTurn;
	}

	public boolean isFinishedGame() {
		return finishedGame;
	}

	public void setFinishedGame(boolean finishedGame) {
		this.finishedGame = finishedGame;
	}

	public void purchaseItem(Player thePlayer,Item item) {
		// TODO Auto-generated method stub
		String damage;
		double bonusPoints;
		String itemCategory;

		if(item instanceof Weapon)
		{
			bonusPoints = 0;
			damage = ((Weapon) item).getDamage();
			itemCategory = "weapon";

		}
		else if(item instanceof Shield)
		{
			damage = "N/A";
			bonusPoints = item.getBonusPoints();
			itemCategory = "shield";

		}
		else
		{
			damage = "N/A";
			bonusPoints = item.getBonusPoints();
			itemCategory = "armor";
		}
		Inventory currentItemtoAdd = new Inventory(item.getName(), itemCategory, item.getDescription(), item.getWeight(), 
				item.getValue(), damage, bonusPoints, "no");

		thePlayer.getItems().add(currentItemtoAdd);
	}

	public double getInventoryItemWeight(Player thePlayer)
	{
		double totalWeight = 0;
		for(int i=0; i< thePlayer.getItems().size(); i ++)
		{
			double currentItemWeight = getItems().get(i).getWeight();
			totalWeight += currentItemWeight;
		}

		return totalWeight;
	}

	public String listItem(Player thePlayer)
	{
		System.out.println("");
		System.out.println("Item\tCategory\tWeight\tValue\tDamage\tBonus Points\tEquipped\tItem Description");		
		if(getItems().size() == 0)
		{
			return "You don't have any items in your inventory. ";
		}
		ArrayList<Inventory> inventoryItems = thePlayer.getItems();
		for(int i=0; i<thePlayer.getItems().size(); i++)
		{
			/////THis condition is just used in making the output better
			if(inventoryItems.get(i).getItemCategory().equalsIgnoreCase("collectible") == false)
			{
				System.out.println(inventoryItems.get(i).getItemName()+ "\t" +inventoryItems.get(i).getItemCategory() + 
						"\t\t" + inventoryItems.get(i).getWeight()  + "\t"+ inventoryItems.get(i).getValue() + 
						"\t" + inventoryItems.get(i).getDamage() + "\t" + inventoryItems.get(i).getBonusPoints()+
						"\t\t"+ inventoryItems.get(i).isEquipped() + "\t\t" + inventoryItems.get(i).getItemDescription());
			}
			else
			{
				System.out.println(inventoryItems.get(i).getItemName()+ "\t" +inventoryItems.get(i).getItemCategory() + 
						"\t" + inventoryItems.get(i).getWeight()  + "\t"+ inventoryItems.get(i).getValue() + 
						"\t" + inventoryItems.get(i).getDamage() + "\t" + inventoryItems.get(i).getBonusPoints()+
						"\t\t"+ inventoryItems.get(i).isEquipped()+ "\t\t"+inventoryItems.get(i).getItemDescription());
			}
		}
		return "Your currently held items";

	}

	public String listIndividualItem(Player thePlayer, String desiredItem)
	{
		String message= "";
		for(int i = 0; i < thePlayer.getItems().size(); i++)
		{										
			if(thePlayer.getItems().get(i).getItemName().equalsIgnoreCase(desiredItem))
			{
				System.out.println("Item Name : " + thePlayer.getItems().get(i).getItemName());
				System.out.println("Item Description : "+ thePlayer.getItems().get(i).getItemDescription());
				System.out.println("Item Category : "+ thePlayer.getItems().get(i).getItemCategory());
				System.out.println("Weight : " + thePlayer.getItems().get(i).getWeight());
				System.out.println("Value : " + thePlayer.getItems().get(i).getValue());
				System.out.println("Damage : " + thePlayer.getItems().get(i).getDamage());
				System.out.println("Bonus Points: " + thePlayer.getItems().get(i).getBonusPoints());	
				System.out.println("Is Equipped: " + thePlayer.getItems().get(i).isEquipped());	

				message = "Item detail"; 
				break;
			}
			else
			{
				message = desiredItem + " is not available in your inventory item list.";
			}					
		}		
		return message;
	}

	public String dropItem(Player thePlayer,String desiredItem)
	{
		String message = "";
		for(int i = 0; i < thePlayer.getItems().size(); i++)
		{										
			if(thePlayer.getItems().get(i).getItemName().equalsIgnoreCase(desiredItem))
			{				
				if(thePlayer.getItems().get(i).getItemCategory().equalsIgnoreCase("armor") ||
						thePlayer.getItems().get(i).getItemCategory().equalsIgnoreCase("shield") &&
						thePlayer.getItems().get(i).isEquipped().equalsIgnoreCase("yes"))
				{
					String status = thePlayer.decreaseLifePoints(thePlayer.getItems().get(i), thePlayer);
					if(status.equalsIgnoreCase("successful"))
					{
						thePlayer.getCurrentLocation().updateInventoryIteminLocation(thePlayer.getItems().get(i), thePlayer);
						thePlayer.getItems().remove(i);		
						message = "The selected item is dropped successfully";		
					}
					else
					{	
						message = "Since this item is equipped and has a bonus point. Dropping this will\n"
								+ "reduce your life points to zero. Please restore health and try dropping this item again"; 
					}

				}
				else
				{

					thePlayer.getCurrentLocation().updateInventoryIteminLocation(thePlayer.getItems().get(i), thePlayer);
					thePlayer.getItems().remove(i);		
					message = "The selected item is dropped successfully";			
				}
				break;
			}
			else
			{
				message = "Sorry, the item cannot be found"; 
			}
		}
		return message;
	}

	public String decreaseLifePoints(Inventory inventory, Player thePlayer)
	{
		String message = "unsuccessful";
		double updatedLifePoints = thePlayer.getLifePoints() - inventory.getBonusPoints(); 
		if(updatedLifePoints > 0)
		{
			//if the updatedLifePoints > 0, then only setLife points otherwise the player will have zero life points and game will be over..
			thePlayer.setLifePoints(updatedLifePoints);
			message = "successful";				
		}						
		return message;
	}


	public Inventory retrieveInventoryItem(String desiredItem, Player thePlayer)
	{
		for(int i=0; i<thePlayer.getItems().size(); i++)
		{
			if(thePlayer.getItems().get(i).getItemName().equalsIgnoreCase(desiredItem))
			{
				return thePlayer.getItems().get(i);
			}
		}
		return null;
	}

	public String equipItem(String desiredItem, Player thePlayer)
	{
		Inventory itemToEquip = retrieveInventoryItem(desiredItem, thePlayer);
		if(itemToEquip != null)
		{	
			if(itemToEquip.getItemCategory().equalsIgnoreCase("collectible"))
			{
				return "The selected item collectibles cannot be equipped";
			}

			if(itemToEquip.isEquipped().equalsIgnoreCase("yes"))
			{
				return "Item is already equipped";
			}
			/// Checking whether the player has already equipped weapon
			if(itemToEquip.getItemCategory().equalsIgnoreCase("weapon"))
			{
				for(int i = 0; i<thePlayer.getItems().size(); i++)
				{
					if(thePlayer.getItems().get(i).getItemCategory().equalsIgnoreCase("weapon") && 
							thePlayer.getItems().get(i).isEquipped().equalsIgnoreCase("yes"))
					{
						return "You already have an equipped weapon. Please unequip that first";
					}
				}
			}
			/// Checking whether the player has already equipped armor
			if(itemToEquip.getItemCategory().equalsIgnoreCase("armor"))
			{
				for(int i = 0; i<thePlayer.getItems().size(); i++)
				{
					if(thePlayer.getItems().get(i).getItemCategory().equalsIgnoreCase("armor") && 
							thePlayer.getItems().get(i).isEquipped().equalsIgnoreCase("yes"))
					{
						return "You already have an equipped armor. Please unequip that first";
					}
				}
			}			
			/// Checking whether the player has already equipped shield
			if(itemToEquip.getItemCategory().equalsIgnoreCase("shield"))
			{
				for(int i = 0; i<thePlayer.getItems().size(); i++)
				{
					if(thePlayer.getItems().get(i).getItemCategory().equalsIgnoreCase("shield") && 
							thePlayer.getItems().get(i).isEquipped().equalsIgnoreCase("yes"))
					{
						return "You already have an equipped shield. Please unequip that first";
					}
				}
			}
			itemToEquip.setEquipped("yes");
			//add life points if equipped item is shield or armor
			if(itemToEquip.getItemCategory().equalsIgnoreCase("shield") 
					|| itemToEquip.getItemCategory().equalsIgnoreCase("armor"))
			{
				thePlayer.setLifePoints(thePlayer.getLifePoints() + itemToEquip.getBonusPoints());
				System.out.println("Your life points increased because you equipped the item having bonus points");
			}
		}
		else
		{
			return "The selected item is not in your inventory";
		}

		return "Item equipped successfully";
	}

	public String unequipItem(String desiredItem, Player thePlayer)
	{
		String message = "Item cannot be found in your inventory list.";
		Inventory itemToUnEquip = retrieveInventoryItem(desiredItem, thePlayer);
		if(itemToUnEquip != null)
		{
			if(itemToUnEquip.isEquipped().equalsIgnoreCase("yes"))
			{
				itemToUnEquip.setEquipped("no");
				//deduct life points if the item unequipped is armor or shield
				if(itemToUnEquip.getItemCategory().equalsIgnoreCase("shield") 
						|| itemToUnEquip.getItemCategory().equalsIgnoreCase("armor"))
				{
					double lifepoints = thePlayer.getLifePoints() - itemToUnEquip.getBonusPoints();
					if(lifepoints > 0)
					{
						thePlayer.setLifePoints(lifepoints);
						System.out.println("Your life points decreased because you unequipped the item having bonus points");
						message = "Item unequipped successfully";
					}						
					else
					{
						message = "The item cannot be unequipped because unequipping it will make your lifepoints to zero"
								+ "\nPlease restore life points by using potion and try unequipping again";

					}

				}
				else 
				{
					message = "Item unequipped successfully";
				}

			}
			else
			{	
				message = "Item must be equipped in order to unequip";
			}						
		}
		return message;		
	}

	public void collectItems(Player thePlayer, Item itemToCollect)
	{
		if(itemToCollect instanceof Weapon)
		{
			Inventory currentItemToAdd = new Inventory(itemToCollect.getName(), "weapon", itemToCollect.getDescription(), 
					itemToCollect.getWeight(), itemToCollect.getValue(), ((Weapon) itemToCollect).getDamage(), 0, "no");
			thePlayer.getItems().add(currentItemToAdd);
		}
		else if(itemToCollect instanceof Armor)
		{
			Inventory currentItemToAdd = new Inventory(itemToCollect.getName(), "armor", itemToCollect.getDescription(), 
					itemToCollect.getWeight(), itemToCollect.getValue(), "N/A", itemToCollect.getBonusPoints(), "no");
			thePlayer.getItems().add(currentItemToAdd);
		}	
		else if(itemToCollect instanceof Shield)
		{
			Inventory currentItemToAdd = new Inventory(itemToCollect.getName(), "shield", itemToCollect.getDescription(), 
					itemToCollect.getWeight(), itemToCollect.getValue(), "N/A", itemToCollect.getBonusPoints(), "no");
			thePlayer.getItems().add(currentItemToAdd);
		}
		else
		{
			Inventory currentItemtoAdd = new Inventory(itemToCollect.getName(),"collectible",
					itemToCollect.getDescription(), itemToCollect.getWeight(),itemToCollect.getValue(),"N/A",0,"N/A");
			thePlayer.getItems().add(currentItemtoAdd);
		}		
	}

	public String sellItem(Player thePlayer, int indexofItem)
	{	
		double value = thePlayer.getItems().get(indexofItem).getValue() / 2;
		Item coin = new Item("Coin", "A coin", 0.1, value);
		Inventory currentItemtoAdd = new Inventory(coin.getName(),"collectible",
				coin.getDescription(), coin.getWeight(),coin.getValue(),"N/A",0,"N/A");
		thePlayer.getItems().add(currentItemtoAdd);
		thePlayer.getItems().remove(indexofItem);		
		return "Item is sold successfully and you received a coin with half the value of the item";
	}

	public double getInventoryCoinValue(Player thePlayer)
	{
		double coinValue = 0;
		for(int i = 0; i< getItems().size(); i++)
		{
			if(thePlayer.getItems().get(i).getItemName().equalsIgnoreCase("Coin"))
			{				
				coinValue += thePlayer.getItems().get(i).getValue();				
			}
		}
		return coinValue;
	}


	public void removeCoins(Player thePlayer) {
		// TODO Auto-generated method stub
		for (Iterator<Inventory> iter = thePlayer.getItems().iterator(); iter.hasNext(); ) {
			Inventory element = iter.next();
			if (element.getItemName().equalsIgnoreCase("Coin")) {
				iter.remove();
			}
		}
	}

	public Inventory getEquippedWeapon(Player thePlayer)
	{
		Inventory equippedWeapon = null;
		for(int i = 0; i < thePlayer.getItems().size();i++)
		{
			if(thePlayer.getItems().get(i).getItemCategory().equalsIgnoreCase("weapon"))
			{
				if(thePlayer.getItems().get(i).isEquipped().equalsIgnoreCase("yes"))
				{
					equippedWeapon = thePlayer.getItems().get(i);
					break;
				}
			}
		}
		return equippedWeapon;
	}

	public void attackNPC(Player thePlayer, NonPlayerCharacter npc, Inventory weapon)
	{
		String currentWeaponDamage = weapon.getDamage();		
		String[] numbers = currentWeaponDamage .split("d");
		int rolls = Integer.parseInt(numbers[0]);
		int sides = Integer.parseInt(numbers[1]);
		Dice dice = new Dice(rolls, sides);		
		int randomDamage = dice.rollMyDiceManyTimes(); 
		double finalhitPoints = randomDamage + thePlayer.getStrength(); 
		//double finalhitPoints = randomDamage; 
		if(finalhitPoints < 0)
		{
			System.out.println("You are unlucky. You dealt zero damage"); 
		}
		else
		{
			double npclifePoints = npc.getLifePoints() - finalhitPoints;
			npc.setLifePoints(npclifePoints);
			System.out.println("");
			System.out.println("*************************************************************************************");
			System.out.println("\t\t\t\tDAMAGE GIVEN");				
			System.out.println("\nYou attacked " + npc.getName());
			System.out.println("You dealt a damage of " + finalhitPoints + " with your strength and weapon damage");
			if(npclifePoints > 0)
			{
				System.out.println(npc.getName() + " has right now a life point of " + npc.getLifePoints());
			}
			else
			{
				System.out.println(npc.getName() + " has right now a life point of 0");
				System.out.println("You defeated "+ npc.getName() + "\nCongratulations " + thePlayer.getName());
				thePlayer.getCurrentLocation().getNPC().remove("npc");
			}

		}
	}

	public String restoreLifePoints(Player thePlayer)
	{
		String message = "";
		if(thePlayer.retrieveInventoryItem("Potion", thePlayer) != null)
		{			
			if(thePlayer.getLifePoints() < 20)  ///20 is the default life point of a player
			{
				Dice dice = new Dice(2, 6); //rolling 6 sided dice 2 times		
				int lifePointsToIncrease = dice.rollMyDiceManyTimes(); // getting the max value out of 2 rolls
				double finalifePoints = lifePointsToIncrease + thePlayer.getLifePoints();
				if(finalifePoints > 20)
				{					
					thePlayer.setLifePoints(20); //restoring to full health because 20 is the max life point
					System.out.println("You restored full life points");
					System.out.println("Your current life point is "+ thePlayer.getLifePoints());
					message = "Restored health";
				}
				else
				{
					thePlayer.setLifePoints(finalifePoints);
					System.out.println("You restored " + lifePointsToIncrease + " life points");
					System.out.println("Your new life points is "+ thePlayer.getLifePoints());
					message = "Restored health";
				}
				//Removing item from inventory
				for(int i = 0; i < thePlayer.getItems().size(); i++)
				{
					if(thePlayer.getItems().get(i).getItemName().equalsIgnoreCase("Potion"))
					{
						thePlayer.getItems().remove(i);
						break;
					}
				}
			}
			else
			{								 
				message = "You are already at full health";								
			}
		}
		else
		{
			message = "The life could not be restored.\nPotion is not available in your inventory";
		}

		return message;
	}
}
