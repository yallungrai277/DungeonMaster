package mazegame.control.commands;

import java.util.ArrayList;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Item;
import mazegame.entity.Player;
import mazegame.entity.Shop;

public class CollectCommand implements Command {

	@Override
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		
		String message = "";
		
		if(userInput.getArguments().size() == 0)
		{			
			if(thePlayer.getCurrentLocation() instanceof Shop == false)
	   	 	{
	   	 		if(thePlayer.getCurrentLocation().getItems().isEmpty())
	   	 		{
	   	 			return new CommandResponse("There are no items here to collect");
	   	 		}
	   	 		if(thePlayer.getCurrentLocation().getItems().get("items").size() > 0)
	   	 		{
	   	 			message = "\nThese are the items that you can collect";
	   	 			thePlayer.getCurrentLocation().displayItems(thePlayer);
	   	 		}
	   	 		else
	   	 		{
	   	 			message = "\nNothing to collect.";
	   	 		}
	   	 	}
		}
		else
		{
			String desiredItem = (String) userInput.getArguments().get(0);	
			ArrayList<Item> collectible = thePlayer.getCurrentLocation().getItems().get("items");
   	 		if(thePlayer.getCurrentLocation().getItems().isEmpty())
   	 		{
   	 			return new CommandResponse("There are no items here to collect");
   	 		}
			if(collectible.size() == 0)
			{
				return new CommandResponse("Nothing to collect");
			}
			
			if(thePlayer.getInventoryItemWeight(thePlayer) < thePlayer.getWeightLimit())
			{
				for(int i = 0; i < collectible.size(); i ++)
				{
					if(collectible.get(i).getName().equalsIgnoreCase(desiredItem))
					{
						double tempWeight = collectible.get(i).getWeight() + thePlayer.getInventoryItemWeight(thePlayer);											
						if(tempWeight > thePlayer.getWeightLimit())
						{
							message = "\nYour weight limit is " + thePlayer.getWeightLimit() +
									"\nYour current inventory weight is "+ thePlayer.getInventoryItemWeight(thePlayer)
									+ "\nCollecting this item will exceed weight limit. Please drop some items";
						}
						else
						{
							thePlayer.collectItems(thePlayer, collectible.get(i));
							collectible.remove(i);
							message = "You collected a " + desiredItem;
						}
					}
					else
					{
						message = desiredItem + " cannot be collected. "
								+ "\nEither you already collected it or there is no item named " + desiredItem;
					}
				}
			}
			else
			{
				message = "You have exceeded weight limit. Your weight limit is " + thePlayer.getWeightLimit();
			}
			//message = "\nCollect something";
		}
		return new CommandResponse(message);
	}

}
