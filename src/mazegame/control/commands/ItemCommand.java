package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;

public class ItemCommand implements Command{
	@Override
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		String message = " "; 
		if(userInput.getArguments().size() == 0)
		{
			if(thePlayer.getItems().size() == 0)
			{
				message = "You have zero items in your inventory";
			}
			else
			{
				//Listing and receiving message currently held items by the player
				message = thePlayer.listItem(thePlayer);
			}
		}
		else
		{
			//Viewing inventory items based on the user argument
			String desiredItem = (String) userInput.getArguments().get(0);	
			message = thePlayer.listIndividualItem(thePlayer, desiredItem);					
		}
		return new CommandResponse("\n"+ message);
		
	}
}
