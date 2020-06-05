package mazegame.control.commands;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Inventory;
import mazegame.entity.NonPlayerCharacter;
import mazegame.entity.Player;

public class AttackCommand implements Command {

	String message;
	@Override
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		
		if(userInput.getArguments().size() == 0)
		{
			//Extra check even though already done in combat state.
			if(thePlayer.getCurrentLocation().getHostilePlayer(thePlayer) == null)
			{
				return new CommandResponse("There is no hostile player to attack.");
			}				
			if(thePlayer.getEquippedWeapon(thePlayer) != null)
			{
				NonPlayerCharacter npc = thePlayer.getCurrentLocation().getNPC().get("npc");
				Inventory weapon = thePlayer.getEquippedWeapon(thePlayer);
				thePlayer.attackNPC(thePlayer, npc, weapon);
				message = "attack-mode";
			}
			else
			{
				message = "You donot have any equipped weapon.\nPlease equip it first";
			}
		}
		else
		{			
			message = "\nAttack command does not required additional parameters";
		}
		return new CommandResponse(message);			
	}	
}
