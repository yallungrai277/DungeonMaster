package mazegame.control.state;

import mazegame.control.commands.AttackCommand;
import mazegame.control.commands.EquipCommand;
import mazegame.control.commands.FleeCommand;
import mazegame.control.commands.ItemCommand;
import mazegame.control.commands.RestoreCommand;
import mazegame.control.commands.UnequipCommand;
import mazegame.entity.Player;

public class CombatState extends CommandState{

	
	public CombatState()
	{		
        this.getAvailableCommands().put("item", new ItemCommand());
        this.getAvailableCommands().put("attack", new AttackCommand());
        this.getAvailableCommands().put("equip", new EquipCommand());
        this.getAvailableCommands().put("unequip", new UnequipCommand());
        this.getAvailableCommands().put("flee", new FleeCommand());
        this.getAvailableCommands().put("restore", new RestoreCommand());        
	}
	
	public CommandState update(Player thePlayer) {
		// TODO Auto-generated method stub
		//only go to combat state if there is a hostile player
		if(thePlayer.getCurrentLocation().getNPC().size() > 0 && thePlayer.getCurrentLocation().getNPC().get("npc") != null
				&& thePlayer.getCurrentLocation().getNPC().get("npc").isHostile() == true)
			return this;
		return new MovementState();
	}

}
