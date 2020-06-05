package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Player;

public class QuitCommand implements Command
{
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		thePlayer.setFinishedGame(true);
		return new CommandResponse("Good Bye " + thePlayer.getName());
	}
	
}
