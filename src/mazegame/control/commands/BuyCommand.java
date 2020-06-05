package mazegame.control.commands;

import java.util.ArrayList;
import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Inventory;
import mazegame.entity.Item;
import mazegame.entity.Player;
import mazegame.entity.Shop;

public class BuyCommand implements Command {

	String message = " ";

	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {

		String currentLocation = thePlayer.getCurrentLocation().getLabel();
		if(userInput.getArguments().size() == 0)
		{			
			Shop shop= new Shop();  
			shop.displayShopItems(thePlayer, currentLocation);
			message = "Here are the list of items in this shop..";

			/*for (Entry<String, ArrayList<Item>> entry : thePlayer.getCurrentLocation().getInventory().entrySet()) {
			 * entry.getKey()
			    for(int i=0; i< entry.getValue().size(); i++)
			    {
			    	System.out.println(entry.getValue().get(i).getName());
			    	System.out.println(entry.getValue().get(i).getDescription());
			    	System.out.println(entry.getValue().get(i).getWeight());
			    	System.out.println(entry.getValue().get(i).getValue());

			    }
			}*/
		}
		else
		{

			processBuying(currentLocation, thePlayer, userInput);
		}

		return new CommandResponse(message);
	}

	public void processBuying(String currentLocation, Player thePlayer, ParsedInput userInput)
	{
		boolean executed = false;
		String desiredItem = (String) userInput.getArguments().get(0);
		//checking if the item exists in inventory
		if(thePlayer.retrieveInventoryItem(desiredItem, thePlayer) == null)
		{
			//checking inventory weight and weight limit
			if(thePlayer.getInventoryItemWeight(thePlayer) < thePlayer.getWeightLimit())
			{
				//Buying based on shop location and allow to buy items based on shop location
				if(currentLocation.equalsIgnoreCase("Shield Shop"))
				{
					ArrayList<Item> shields = thePlayer.getCurrentLocation().getItems().get("shield");
					for(int i = 0; i < shields.size(); i++)
					{									
						if(shields.get(i).getName().equalsIgnoreCase(desiredItem))
						{
							//checking if buying that shield item exceeds weight limit
							double tempWeight = thePlayer.getInventoryItemWeight(thePlayer) + shields.get(i).getWeight();
							if(tempWeight > thePlayer.getWeightLimit())
							{
								message = "\nYour weight limit is " + thePlayer.getWeightLimit() +
										"\nYour current inventory weight is "+ thePlayer.getInventoryItemWeight(thePlayer)
										+ "\nBuying this item will exceed weight limit. Please drop some items..";
							}
							else
							{							
								////checking if player has coin
								for(int  j=0; j < thePlayer.getItems().size();j++)
								{
									if(thePlayer.getItems().get(j).getItemName().equalsIgnoreCase("Coin"))
									{
										//checking if each individual coin has enough value to buy shield
										if(thePlayer.getItems().get(j).getValue() < shields.get(i).getValue())
										{
											message = "You do not have enough coins...";
										}
										else
										{
											executed = true;										
											double coinValueToUpdate = thePlayer.getItems().get(j).getValue() - shields.get(i).getValue();
											//if coin value to update is 0 then remove coin
											if(coinValueToUpdate == 0)
											{
												thePlayer.getItems().remove(j);
											}
											else
											{
												thePlayer.getItems().get(j).
												setValue(thePlayer.getItems().get(j).getValue() - shields.get(i).getValue());
											}										
											thePlayer.purchaseItem(thePlayer, shields.get(i));
											message = desiredItem + " shield bought successfully..";
											break;
										}
									}
									else
									{
										message = "You don't have any coins..";
									}															
								}
								///checking if total coin value in inventory is enough to buy shield
								if(executed == false)								
								{
									if(thePlayer.getInventoryCoinValue(thePlayer) < shields.get(i).getValue())
									{
										message = "You don't have enough coins combined or individual...";
									}	
									else
									{
										//if enough coin combined then do appropriate calculation and delete all the coin 
										//create new coin with updated value and store in inventory 
										//if coin value is 0 then all coin is spent.
										double coinValueToUpdate = thePlayer.getInventoryCoinValue(thePlayer) - shields.get(i).getValue();									
										thePlayer.removeCoins(thePlayer);
										if(coinValueToUpdate > 0)
										{
											Item coin = new Item("Coin", "A coin", 0.1, coinValueToUpdate);																		
											Inventory currentItemtoAdd = new Inventory(coin.getName(),"collectible",
													coin.getDescription(), coin.getWeight(),coin.getValue(),"N/A",0,"N/A");
											thePlayer.getItems().add(currentItemtoAdd);
										}
										thePlayer.purchaseItem(thePlayer, shields.get(i));
										message = desiredItem + " shield bought successfully..";
									}
								}							
							}						
							break;
						}
						else
						{
							message = desiredItem + " is not available in the shield shop..";
						}					
					}	
				}
				else if(currentLocation.equalsIgnoreCase("Armor Shop"))
				{							
					ArrayList<Item> armors = thePlayer.getCurrentLocation().getItems().get("armor");
					for(int i = 0; i < armors.size(); i++)
					{									
						if(armors.get(i).getName().equalsIgnoreCase(desiredItem))
						{
							double tempWeight = armors.get(i).getWeight() + thePlayer.getInventoryItemWeight(thePlayer);
							if(tempWeight > thePlayer.getWeightLimit())
							{	
								message = "\nYour weight limit is " + thePlayer.getWeightLimit() +
										"\nYour current inventory weight is "+ thePlayer.getInventoryItemWeight(thePlayer)
										+ "\nBuying this item will exceed weight limit. Please drop some items.";							
							}
							else
							{
								for(int  j=0; j < thePlayer.getItems().size();j++)
								{
									if(thePlayer.getItems().get(j).getItemName().equalsIgnoreCase("Coin"))
									{
										if(thePlayer.getItems().get(j).getValue() < armors.get(i).getValue())
										{
											message = "You do not have enough coins..";
										}
										else
										{
											executed = true;										
											double coinValueToUpdate = thePlayer.getItems().get(j).getValue() - armors.get(i).getValue();
											if(coinValueToUpdate == 0)
											{
												thePlayer.getItems().remove(j);
											}
											else
											{
												thePlayer.getItems().get(j).
												setValue(thePlayer.getItems().get(j).getValue() - armors.get(i).getValue());
											}															
											thePlayer.purchaseItem(thePlayer, armors.get(i));
											message = desiredItem + " armor bought successfully..";
											break;										
										}									
									}
									else
									{
										message = "You don't have any coins..";
									}																
								}
								if(executed == false)								
								{
									if(thePlayer.getInventoryCoinValue(thePlayer) < armors.get(i).getValue())
									{
										message = "You don't have enough coins combined or individual.";
									}	
									else
									{									
										double coinValueToUpdate = thePlayer.getInventoryCoinValue(thePlayer) - armors.get(i).getValue();									
										thePlayer.removeCoins(thePlayer);
										if(coinValueToUpdate > 0)
										{
											Item coin = new Item("Coin", "A coin", 0.1, coinValueToUpdate);																		
											Inventory currentItemtoAdd = new Inventory(coin.getName(),"collectible",
													coin.getDescription(), coin.getWeight(),coin.getValue(),"N/A",0,"N/A");
											thePlayer.getItems().add(currentItemtoAdd);
										}
										thePlayer.purchaseItem(thePlayer, armors.get(i));
										message = desiredItem + " armor bought successfully..";
									}
								}
							}
							break;
						}
						else
						{
							message = desiredItem + " is not available in the armor shop";
						}					
					}					
				}
				else 
				{	
					ArrayList<Item> weapons = thePlayer.getCurrentLocation().getItems().get("weapon");
					//buying items from blacksmith shop
					for(int i = 0; i < weapons.size(); i++)
					{									
						if(weapons.get(i).getName().equalsIgnoreCase(desiredItem))
						{
							double tempWeight = weapons.get(i).getWeight() + thePlayer.getInventoryItemWeight(thePlayer);
							if(tempWeight > thePlayer.getWeightLimit())
							{
								message = "\nYour weight limit is " + thePlayer.getWeightLimit() +
										"\nYour current inventory weight is "+ thePlayer.getInventoryItemWeight(thePlayer)
										+ "\nBuying this item will exceed weight limit. Please drop some items";
							}
							else
							{							
								for(int  j=0; j < thePlayer.getItems().size();j++)
								{
									if(thePlayer.getItems().get(j).getItemName().equalsIgnoreCase("Coin"))
									{
										if(thePlayer.getItems().get(j).getValue() < weapons.get(i).getValue())
										{
											message = "You do not have enough coins..";
										}
										else
										{
											executed = true;
											thePlayer.purchaseItem(thePlayer, weapons.get(i));
											double coinValueToUpdate = thePlayer.getItems().get(j).getValue() - weapons.get(i).getValue();
											if(coinValueToUpdate == 0)
											{
												thePlayer.getItems().remove(j);
											}
											else
											{
												thePlayer.getItems().get(j).
												setValue(thePlayer.getItems().get(j).getValue() - weapons.get(i).getValue());
											}																					
											message = desiredItem + " bought successfully..";
											break;										
										}									
									}
									else
									{
										message = "You don't have any coins..";
									}																
								}
								if(executed == false)								
								{
									if(thePlayer.getInventoryCoinValue(thePlayer) < weapons.get(i).getValue())
									{
										message = "You don't have enough coins combined or individual..";
									}	
									else
									{
										double coinValueToUpdate = thePlayer.getInventoryCoinValue(thePlayer) - weapons.get(i).getValue();									
										thePlayer.removeCoins(thePlayer);
										if(coinValueToUpdate > 0)
										{
											Item coin = new Item("Coin", "A coin", 0.1, coinValueToUpdate);																		
											Inventory currentItemtoAdd = new Inventory(coin.getName(),"collectible",
													coin.getDescription(), coin.getWeight(),coin.getValue(),"N/A",0,"N/A");
											thePlayer.getItems().add(currentItemtoAdd);
										}
										thePlayer.purchaseItem(thePlayer, weapons.get(i));
										message = desiredItem + " bought successfully.";
									}
								}
							}
							break;
						}
						else
						{
							message = desiredItem + " is not available in the blacksmith weapon shop..";
						}					
					}	
				}

			}
			else
			{
				message = "You have exceeded weight limit. Your weight limit is " + thePlayer.getWeightLimit();
			}
		}
		else if(thePlayer.retrieveInventoryItem(desiredItem, thePlayer).getItemCategory().equalsIgnoreCase("collectible"))
		{
			message = "Collectibles cannot be bought....";
		}
		else
		{
			message = "You already have that item in your inventory. No need to buy it..."; 
		}
	}
}
