package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Exit;
import mazegame.entity.Player;

public class UnlockCommand implements Command {

	@Override
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		// TODO Auto-generated method stub
		if(userInput.getArguments().size() == 0)
		{
			return new CommandResponse("If you want to unlock , you need to tell me the direction of the exit that you want to unlock");
		}
		else
		{
			String exitLabel = (String) userInput.getArguments().get(0);
			Exit desiredExit = thePlayer.getCurrentLocation().getExit(exitLabel);   	 	
			if (desiredExit == null) {
				return new CommandResponse("There is no exit there. Try unlocking somewhere else!");
			}
			else if(!desiredExit.isLocked())
			{
				return new CommandResponse("The exit on that direction is not locked. You can move freely");
			}
			else 
			{
				if(thePlayer.retrieveInventoryItem("Key", thePlayer) != null)
				{
					desiredExit.setLocked(false);
					
					return new CommandResponse("The door is unlocked. You can move there");
				}
				else
				{
					return new CommandResponse("You dont have the key to unlock the door.\nPlease collect some key");
				}
				
			}
		}		
	}		
}
