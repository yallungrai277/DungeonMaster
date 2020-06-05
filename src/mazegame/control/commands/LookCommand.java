package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Exit;
import mazegame.entity.Player;

public class LookCommand implements Command {
	
	private CommandResponse response;
	
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		response = new CommandResponse("Cannot find anything there.");
		if(userInput.getArguments().size() == 0) {
			response.setMessage(thePlayer.getCurrentLocation().toString());
			return response;
		}
		for (Object argument: userInput.getArguments()) {		
			if (thePlayer.getCurrentLocation().containsExit(argument.toString())) {
				Exit theExit = thePlayer.getCurrentLocation().getExit((String)argument);
				return new CommandResponse(theExit.getDescription());
			}
		}
		return response;
	}
}
