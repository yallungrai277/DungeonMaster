package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Inventory;
import mazegame.entity.Player;


public class SellCommand implements Command{

	String message = "";
	@Override
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		
		if(userInput.getArguments().size() == 0)
		{
			if(thePlayer.getItems().size() == 0)
			{
				message = "You have zero items in your inventory.\nYou cannot sell anything";
			}
			else
			{
				//Listing and receiving currently held items by the player
				message = "\n" + thePlayer.listItem(thePlayer) + " .Please note collectible cannot be sold"
						+ "nWhat do you want to sell ?";
			}
		}
		else
		{
			String currentLocation = thePlayer.getCurrentLocation().getLabel();
			validateSelling(currentLocation, thePlayer, userInput);
											
		}
		return new CommandResponse(message);
	}
	
	public void validateSelling(String currentLocation, Player thePlayer, ParsedInput userInput)
	{
		String desiredItem = (String) userInput.getArguments().get(0);		
		for(int i = 0; i < thePlayer.getItems().size(); i++)
		{										
			if(thePlayer.getItems().get(i).getItemName().equalsIgnoreCase(desiredItem))
			{			
				if(thePlayer.getItems().get(i).getItemCategory().equalsIgnoreCase("collectible"))
				{
					message = "Collectibles cannot be sold";					
				}
				else
				{
					processSelling(currentLocation, thePlayer, thePlayer.getItems().get(i), i);
				}
				break;				
			}
			else
			{
				message = "Sorry, the item cannot be found."; 
			}
		}								
	}
	
	public void processSelling(String currentLocation, Player thePlayer, Inventory item, int indexofItem)
	{
		//Selling items based on shop location
		if(currentLocation.equalsIgnoreCase("Shield Shop"))
		{
			if(item.getItemCategory().equalsIgnoreCase("weapon") || item.getItemCategory().equalsIgnoreCase("armor"))
			{
				message = "\nOnly shield can be sold in shield shop";
			}
			else
			{				
				thePlayer.sellItem(thePlayer, indexofItem);
				message = "\nItem sold successfully";
			}
		}
		else if(currentLocation.equalsIgnoreCase("Armor Shop"))
		{
			if(item.getItemCategory().equalsIgnoreCase("weapon") || item.getItemCategory().equalsIgnoreCase("shield"))
			{
				message = "\nOnly armor can be sold in armor shop";
			}
			else
			{
				thePlayer.sellItem(thePlayer, indexofItem);
				message = "\nItem sold successfully";
			}
		}
		else
		{
			if(item.getItemCategory().equalsIgnoreCase("shield") || item.getItemCategory().equalsIgnoreCase("armor"))
			{
				message = "\nOnly weapon can be sold in blacksmith weapon shop";
				
			}
			else
			{
				thePlayer.sellItem(thePlayer, indexofItem);
				message = "\nItem sold successfully";
			}
		}
	}
}
