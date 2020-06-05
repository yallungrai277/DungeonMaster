package mazegame.control.state;

import java.util.ArrayList;
import java.util.HashMap;

import mazegame.control.Command;
import mazegame.entity.Player;

public abstract class CommandState {
	private HashMap <String, Command>availableCommands;
	
	public abstract CommandState update(Player thePlayer);
	
	public CommandState()
	{
		availableCommands = new HashMap<String, Command>();
	}
	
	public HashMap <String, Command> getAvailableCommands()
	{	
		return this.availableCommands;
	}
	
	public void setAvailableCommands(HashMap<String, Command> hashMap)
	{	
		availableCommands = hashMap;
	}
	
	public Command getCommand(String commandLabel)
	{
		return availableCommands.get(commandLabel);
	}
	
	public ArrayList<String> getLabels()
	{
		return new ArrayList <String>(availableCommands.keySet());
	}
}
