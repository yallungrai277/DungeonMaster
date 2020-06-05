package mazegame.control.commands;

import java.util.ArrayList;

import mazegame.HardCodedData;
import mazegame.control.Command;
import mazegame.control.CommandResponse;
import mazegame.control.ParsedInput;
import mazegame.entity.Location;
import mazegame.entity.Player;
import mazegame.entity.Shop;

public class StatusCommand implements Command{

	
	HardCodedData hcd = HardCodedData.getInstance(); 
	
	public CommandResponse execute(ParsedInput userInput, Player thePlayer) {
		// TODO Auto-generated method stub
		if(userInput.getArguments().size() == 0)
		{
			return new CommandResponse("This command takes either one of these arguments => maze, player"); 
		}
		else
		{
			String message  = "";
			String userArgument = (String) userInput.getArguments().get(0); 

			if(userArgument.equalsIgnoreCase("maze"))
			{
				ArrayList<Location> worldmap = hcd.getMazeStatus();
				int shopCount = 0;
				int generalLocationCount = 0;
				System.out.println("************************All  Locations****************************");				
				for(int i = 0; i < worldmap.size(); i ++)
				{						
					System.out.println("");
					System.out.println("Location Name: " + worldmap.get(i).getLabel());
					System.out.println("Location Desc.: " + worldmap.get(i).getDescription());
					System.out.println("Total No. of exits:  "+ worldmap.get(i).getCurrentExits().size());
					if(worldmap.get(i) instanceof Shop)
					{
						System.out.println("Category: Shop Location");
						shopCount++;
					}
					else
					{
						System.out.println("Category: General Location");
						generalLocationCount++;
					}
															
					System.out.println("******************************************************************");
				}
				int totalLocations = generalLocationCount + shopCount;
				System.out.println("******************************************************************");
				System.out.println("");
				System.out.println("General Locations: "+ generalLocationCount);
				System.out.println("Shop Locations: "+ shopCount);
				System.out.println("Total Locations: "+ totalLocations);
				System.out.println("");
				
			}
			else if(userArgument.equalsIgnoreCase("player"))
			{
				System.out.println("************************************************************************");
				System.out.println("****************************Your Details********************************"); 
				System.out.println("PlayerName: "+ thePlayer.getName()); 
				System.out.println("Current Life Points (inc. equipped armor and shield bonus points): " + thePlayer.getLifePoints());
				System.out.println("Default Starting Life Points: " + 20.00);
				System.out.println("Strength: "+ thePlayer.getStrength()); 
				System.out.println("Weight Limit: " + thePlayer.getWeightLimit());
				System.out.println("Current Inventory Weight:" + thePlayer.getInventoryItemWeight(thePlayer));
				//System.out.println("Agility" + thePlayer.getAgility());
				
			}
			else
			{
				message = "The argument you typed is not recognized. Please try again";
			}
				
			return new CommandResponse(message); 
		}
		
	}

}
