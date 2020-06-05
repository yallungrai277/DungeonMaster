package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;

public class DropCommand implements Command {

	//@Override
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		// TODO Auto-generated method stub
		if(userInput.getArguments().size() == 0) 
		{
			if(thePlayer.getItems().size()== 0)
			{
				return new CommandResponse("There are no items to drop");
			}
			//thePlayer.listItem();
			return new CommandResponse("\nPlease specify which item you want to drop."); 
			
		}
		String desiredItem = (String) userInput.getArguments().get(0); 
		return new CommandResponse(thePlayer.dropItem(thePlayer, desiredItem));
	}

}
