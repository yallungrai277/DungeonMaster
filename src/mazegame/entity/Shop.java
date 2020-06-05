package mazegame.entity;

import java.util.ArrayList;
import java.util.HashMap;

import mazegame.control.DungeonMaster;

public class Shop extends Location{
	
	private HashMap exits;
	public Shop(String description, String label) {
		super(description, label);	
		// TODO Auto-generated constructor stub
	}
	
	public Shop()
	{
		
	}
	
	public void displayShopItems(Player thePlayer, String currentLocation)
	{
		if(currentLocation.equalsIgnoreCase("Blacksmith"))
		{
			ArrayList<Item> weapons = thePlayer.getCurrentLocation().getItems().get("weapon");
			System.out.println("Armor\t\tDescription\t\tWeight\t\tPrice\t\tDamage Points");
			for(int i = 0; i < weapons.size();i++)
			{
				System.out.print(weapons.get(i).getName()+"\t\t");
				System.out.print(weapons.get(i).getDescription()+"\t\t");
				System.out.print(weapons.get(i).getWeight()+"\t\t");
				System.out.print(weapons.get(i).getValue()+"\t\t");
				System.out.print(((Weapon) weapons.get(i)).getDamage()+"\t\t");
				System.out.println("");
			}
		}
		else if(currentLocation.equalsIgnoreCase("Armor Shop"))
		{
			ArrayList<Item> armors = thePlayer.getCurrentLocation().getItems().get("armor");
			System.out.println("Armor\t\tDescription\t\tWeight\t\tPrice\t\tBonus Points");
			for(int i = 0; i < armors.size();i++)
			{
				System.out.print(armors.get(i).getName()+"\t\t");
				System.out.print(armors.get(i).getDescription()+"\t\t");
				System.out.print(armors.get(i).getWeight()+"\t\t");
				System.out.print(armors.get(i).getValue()+"\t\t");
				System.out.print(armors.get(i).getBonusPoints()+"\t\t");
				System.out.println("");
			}
		}
		else
		{
			ArrayList<Item> shields = thePlayer.getCurrentLocation().getItems().get("shield");
			System.out.println("Shield\t\tDescription\t\tWeight\t\tPrice\t\tBonus Points");
			for(int i = 0; i < shields.size();i++)
			{
				System.out.print(shields.get(i).getName()+"\t\t");
				System.out.print(shields.get(i).getDescription()+"\t\t");
				System.out.print(shields.get(i).getWeight()+"\t\t");
				System.out.print(shields.get(i).getValue()+"\t\t");
				System.out.print(shields.get(i).getBonusPoints()+"\t\t");
				System.out.println("");
			}
		}
	}
}
