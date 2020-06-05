package mazegame.control.state;

import mazegame.control.commands.CollectCommand;
import mazegame.control.commands.DropCommand;
import mazegame.control.commands.EquipCommand;
import mazegame.control.commands.ItemCommand;
import mazegame.control.commands.LookCommand;
import mazegame.control.commands.MoveCommand;
import mazegame.control.commands.QuitCommand;
import mazegame.control.commands.RestoreCommand;
import mazegame.control.commands.StatusCommand;
import mazegame.control.commands.TalkCommand;
import mazegame.control.commands.UnequipCommand;
import mazegame.control.commands.UnlockCommand;
import mazegame.entity.Player;
import mazegame.entity.Shop;

public class MovementState extends CommandState {

	public MovementState()
	{
		this.getAvailableCommands().put("go", new MoveCommand());
		this.getAvailableCommands().put("move", new MoveCommand());
		this.getAvailableCommands().put("quit", new QuitCommand());
		this.getAvailableCommands().put("look", new LookCommand());
		this.getAvailableCommands().put("unlock", new UnlockCommand());
		this.getAvailableCommands().put("item", new ItemCommand());
		this.getAvailableCommands().put("drop", new DropCommand());
		this.getAvailableCommands().put("equip", new EquipCommand());
		this.getAvailableCommands().put("unequip", new UnequipCommand());
		this.getAvailableCommands().put("collect", new CollectCommand());
		this.getAvailableCommands().put("restore", new RestoreCommand());
		this.getAvailableCommands().put("status", new StatusCommand());
		this.getAvailableCommands().put("talk", new TalkCommand());
	}
	
	@Override
	public CommandState update(Player thePlayer) {
		// TODO Auto-generated method stub
		if(thePlayer.getCurrentLocation() instanceof Shop)
			return new CommerceState();
		//only go to combat state if there is a hostile player
		else if(thePlayer.getCurrentLocation().getNPC().size() > 0 && thePlayer.getCurrentLocation().getNPC().get("npc") != null
				&& thePlayer.getCurrentLocation().getNPC().get("npc").isHostile() == true)
			return new CombatState();
		return this;
	}

}
