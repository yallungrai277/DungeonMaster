package mazegame.control.state;

import mazegame.control.commands.BuyCommand;
import mazegame.control.commands.EquipCommand;
import mazegame.control.commands.ItemCommand;
import mazegame.control.commands.MoveCommand;
import mazegame.control.commands.SellCommand;
import mazegame.control.commands.StatusCommand;
import mazegame.control.commands.TalkCommand;
import mazegame.control.commands.UnequipCommand;
import mazegame.control.commands.UnlockCommand;
import mazegame.entity.Player;
import mazegame.entity.Shop;

public class CommerceState extends CommandState{

	public CommerceState()
	{
		this.getAvailableCommands().put("go", new MoveCommand());
        this.getAvailableCommands().put("buy", new BuyCommand());
        this.getAvailableCommands().put("move", new MoveCommand());        
        this.getAvailableCommands().put("sell", new SellCommand());
        this.getAvailableCommands().put("item", new ItemCommand());
        this.getAvailableCommands().put("equip", new EquipCommand());
        this.getAvailableCommands().put("unequip", new UnequipCommand());
        this.getAvailableCommands().put("restore", new UnequipCommand());
        this.getAvailableCommands().put("status", new StatusCommand());
        this.getAvailableCommands().put("unlock", new UnlockCommand());
        this.getAvailableCommands().put("talk", new TalkCommand());

	}
	@Override
	public CommandState update(Player thePlayer) {
		
		if(thePlayer.getCurrentLocation() instanceof Shop)
			return this;
		return new MovementState();
	}

}
