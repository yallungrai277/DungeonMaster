package mazegame.control.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Exit;
import mazegame.entity.Player;
import mazegame.entity.Shop;

public class FleeCommand implements Command {

	String message = "";
	@Override
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		// TODO Auto-generated method stub

		if(userInput.getArguments().size() == 0)
		{
			//Extra check even though already done in combat state.
			if(thePlayer.getCurrentLocation().getHostilePlayer(thePlayer) == null)
			{
				return new CommandResponse("There is no hostile player so no, need of fleeing....");
			}				
			else
			{
				HashMap<String, Exit> availableExits = thePlayer.getCurrentLocation().getCurrentExits();
				List<String> exits = availableExits.keySet().stream().collect(Collectors.toList()); //converting availableExits to a list
				Random rand = new Random();
				//getting random exit from index from the exits size
				String randomExitLabel = exits.get(rand.nextInt(exits.size()));				
				//additional checking
				Exit randomExit = thePlayer.getCurrentLocation().getExit(randomExitLabel);
				if(randomExit == null)
				{
					return new CommandResponse("You are unable to flee. There are no available exits. Fight warrior..."); 
				}
				//if random exit is locked then player turn is over.
				if(randomExit.isLocked())
				{
					System.out.println("You are unlucky you tried to flee throguh the exit which was locked. \n"
							+ "Hence your turn is over and you got attacked"
							+ ". You should try to execute \nflee command one more time.\n");
					return new CommandResponse("flee-unsuccessful");
				}
				//check if the location or exit is locked
				thePlayer.setCurrentLocation(randomExit.getDestination());
				String message = "You successfully fleed to " +	randomExitLabel + " "+ thePlayer.getCurrentLocation().toString();
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

		return new CommandResponse("Flee command does not take any additional arguments");

	}

}
