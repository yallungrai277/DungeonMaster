package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Exit;
import mazegame.entity.Player;
import mazegame.entity.Shop;

public class MoveCommand implements Command {

	public CommandResponse execute (ParsedInput userInput, Player thePlayer)
	{
		if(userInput.getArguments().size() == 0)
		{
			return new CommandResponse("If you want to move, you need to tell me where and available exits are : "  +
					thePlayer.getCurrentLocation().getCurrentExits().keySet()); 
		}
		String exitLabel = (String) userInput.getArguments().get(0);
		Exit desiredExit = thePlayer.getCurrentLocation().getExit(exitLabel);   	 	
		if (desiredExit == null) {
			return new CommandResponse("There is no exit there. Try moving somewhere else..");
		}
		if(desiredExit.isLocked())
		{
			return new CommandResponse("The exit on that direction is locked. Unlock it or try moving somewhere else..");
		}
		thePlayer.setCurrentLocation(desiredExit.getDestination());
		String message = "You successfully moved to " + exitLabel + " "	+ thePlayer.getCurrentLocation().toString();
		//shop does not have items that can be collected or npc 
		//shop only has items that can be bought which is further coded in BuyCommand
		if(thePlayer.getCurrentLocation() instanceof Shop == false)
		{
			//check for location items
			if(thePlayer.getCurrentLocation().getItems().get("items") != null)
			{
				if(thePlayer.getCurrentLocation().getItems().get("items").size() > 0)
				{
					message = message + "\n**************************************************************************************\n" 
							+ "You found some items. Wanna check it out? \nPlease type collect command to see it. ";					
				}
			}
			//check for hostile characters
			if(thePlayer.getCurrentLocation().getHostilePlayer(thePlayer) != null)
			{
				message = message + "\n**************************************************************************************\n" 
						+ "You encountered a hostile player. "
						+ thePlayer.getCurrentLocation().getHostilePlayer(thePlayer).getName() + " wants to atack you.\n"
						+ "What do you want to do. Attack / Flee ?";				
			}
			//check for non hostile characters
			if(thePlayer.getCurrentLocation().getNonHostilePlayer(thePlayer) != null)
			{				
				message = message + "\n**************************************************************************************\n" 
						+ "There is  a non-hostile player. His name is "
						+ thePlayer.getCurrentLocation().getNonHostilePlayer(thePlayer).getName()
						+ ". He wants to talk to you. \nSay Hello !!!Begin conversating by typing talk hello command."; 								
			}
		}		
		return new CommandResponse(message);
	}
}
