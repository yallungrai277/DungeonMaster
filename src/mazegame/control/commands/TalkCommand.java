package mazegame.control.commands;

import java.util.HashMap;

import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.NonPlayerCharacter;
import mazegame.entity.Player;

public class TalkCommand implements Command {
	
	String message = "";
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		// TODO Auto-generated method stub	
		if(thePlayer.getCurrentLocation().getNonHostilePlayer(thePlayer) == null)
		{
			return new CommandResponse("There is no player in this location to talk right now. Try in a different location...");			
		}
		if(userInput.getArguments().size() == 0)
		{
			return new CommandResponse("If you want to talk, you need to pass me some arguments..."); 
		}		
		else
		{	
			
			String userMessage = (String) userInput.getArguments().get(0);			
			if(thePlayer.getCurrentLocation().getNonHostilePlayer(thePlayer) != null)
			{
				NonPlayerCharacter npc = thePlayer.getCurrentLocation().getNonHostilePlayer(thePlayer); 
				HashMap <String, String>conversationList = npc.getConversationListMap();
				
				if(conversationList.containsKey(userMessage))
				{
					String npcReply = conversationList.get(userMessage); 
					message = "************************Conversation********************************\n"
							+ npc.getName() + " says: " + npcReply;
				}
				else
				{
					message = "************************Conversation********************************\n" +
							npc.getName() + " says: " + "Sorry. I did not get you. Can you repeat one more time ?";
				}								
			}
			else
			{
				message = "There is no player that you can talk to at this location. Try again in a different location...";
			}
			
		}	
		return new CommandResponse(message);
	}
}
