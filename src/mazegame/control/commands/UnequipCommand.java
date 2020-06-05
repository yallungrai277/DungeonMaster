package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;

public class UnequipCommand implements Command {

	@Override
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		// TODO Auto-generated method stub
		if(userInput.getArguments().size() == 0)
		{
			if(thePlayer.getItems().size()== 0)
			{
				return new CommandResponse("There are no items to unequip.");
			}
			return new CommandResponse("Please specify what you want to unequip."); 
		}

		String desiredItem = (String) userInput.getArguments().get(0);
		String message = thePlayer.unequipItem(desiredItem, thePlayer);
		System.out.println(message);
		//if the player is with hostile player unequipping item or failing to unequip item will finish turn
		if(thePlayer.getCurrentLocation().getHostilePlayer(thePlayer) != null && message.equalsIgnoreCase("Item unequipped successfully"))
		{
			return new CommandResponse("unequip-in-attack-mode");
		}
		return new CommandResponse("normal-unequip-mode");
	}

}
