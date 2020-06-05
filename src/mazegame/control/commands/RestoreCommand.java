package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;


///This command only is used for restoring life points
public class RestoreCommand implements Command{

	@Override
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		// TODO Auto-generated method stub
		if(userInput.getArguments().size() == 0)
		{
			String message = thePlayer.restoreLifePoints(thePlayer);
			System.out.println(message); 

			if(thePlayer.getCurrentLocation().getHostilePlayer(thePlayer) != null && message.equalsIgnoreCase("Restored health"))
			{
				return new CommandResponse("restored-in-attack-mode");
			}
			else
			{
				return new CommandResponse("normal-restore-mode");
			}
		}
		else
		{
			return new CommandResponse("Restore command does not take any additional arguments");
		}
		
	}

}
