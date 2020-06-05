package mazegame.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Location {
	private HashMap<String, Exit> exits;
	private HashMap<String, ArrayList<Item>> itemList;
	private HashMap<String, NonPlayerCharacter> npc;
	private String description;
	private String label;


	public Location () { }

	public Location (String description, String label)
	{
		this.setDescription(description);
		this.setLabel(label);
		exits = new HashMap<String, Exit>();
		itemList = new HashMap<String, ArrayList<Item>>();
		npc = new HashMap<String, NonPlayerCharacter>();
	}

	public boolean addExit (String exitLabel, Exit theExit)
	{
		if (exits.containsKey(exitLabel))
			return false;
		exits.put(exitLabel, theExit);
		return true;
	}

	public Exit getExit(String exitLabel)
	{
		return (Exit) exits.get(exitLabel);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String availableExits()
	{
		StringBuilder returnMsg = new StringBuilder();		
		for(Object label: this.exits.keySet()) 
		{

			returnMsg.append("[" + label.toString() + "]");

		}
		return returnMsg.toString();
	}

	public HashMap<String, Exit> returnAvailableExits()
	{
		return exits;
	}

	public String toString() 
	{
		return "\n**************************************************************************************\n" + 
				"Current Location => " +  this.label + "\n" + this.description +
				"\n*************************************************************************************\n" 
				+ "Exits found :: " + availableExits() + 
				"\n*************************************************************************************\n";
	}

	public boolean containsExit(String exitLabel)
	{
		return exits.containsKey(exitLabel);
	}


	//only returning hashmap  of the exits
	public HashMap<String, Exit> getCurrentExits()
	{
		return this.exits;
	}

	public boolean createItems(String label,ArrayList<Item> list)
	{
		itemList.put(label, list);
		return true;
	}

	public HashMap<String, ArrayList<Item>> getItems()
	{
		return itemList;
	}


	public boolean addNPC(String label,NonPlayerCharacter npcObject)
	{
		npc.put(label, npcObject);
		return true;
	}


	public HashMap<String, NonPlayerCharacter> getNPC()
	{
		return npc;
	}


	public void displayItems(Player thePlayer)
	{
		ArrayList<Item> items = thePlayer.getCurrentLocation().getItems().get("items");
		System.out.println("Item\tWeight\tValue\tDescription");
		if(items.size() > 0)
		{	    		
			for(int i = 0; i<items.size(); i ++)
			{         		
				System.out.print(items.get(i).getName()+"\t");					
				System.out.print(items.get(i).getWeight()+"\t");
				System.out.print(items.get(i).getValue()+"\t");
				System.out.print(items.get(i).getDescription()+"\t");
				System.out.println("");

			}
		}     	
	}

	public  NonPlayerCharacter getHostilePlayer(Player thePlayer)
	{	
		if(thePlayer.getCurrentLocation().getNPC().size() > 0 && thePlayer.getCurrentLocation().getNPC().get("npc") != null)
		{
			NonPlayerCharacter npc = thePlayer.getCurrentLocation().getNPC().get("npc");
			if(npc.isHostile( )== true)
			{				
				return npc;
			}
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	public NonPlayerCharacter getNonHostilePlayer(Player thePlayer)
	{	    	
		if(thePlayer.getCurrentLocation().getNPC().size() > 0 && thePlayer.getCurrentLocation().getNPC().get("npc") != null)
		{
			NonPlayerCharacter npc = thePlayer.getCurrentLocation().getNPC().get("npc");
			if(npc.isHostile() == false)
			{				
				return npc;
			}
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}


	public void updateInventoryIteminLocation(Inventory inventory, Player thePlayer)
	{
		Item item;
		if(inventory.getItemCategory().equalsIgnoreCase("collectible"))
		{
			item = new Item(inventory.getItemName(), inventory.getItemDescription(), 
					inventory.getWeight(), inventory.getValue());
		}
		else if(inventory.getItemCategory().equalsIgnoreCase("weapon"))
		{
			item = new Weapon(inventory.getItemName(), inventory.getItemDescription(), 
					inventory.getWeight(), inventory.getValue(), inventory.getDamage());	
		}
		else if(inventory.getItemCategory().equalsIgnoreCase("armor"))
		{
			item = new Armor(inventory.getItemName(), inventory.getItemDescription(), 
					inventory.getWeight(), inventory.getValue(), inventory.getBonusPoints()); 
		}
		else
		{
			item = new Armor(inventory.getItemName(), inventory.getItemDescription(), 
					inventory.getWeight(), inventory.getValue(), inventory.getBonusPoints());
		}		
		updateItem(item, thePlayer);

	}

	public void updateItem(Item item, Player thePlayer)
	{		
		if(thePlayer.getCurrentLocation().getItems().containsKey("items"))
		{
			thePlayer.getCurrentLocation().getItems().get("items").add(item);				
		}
		else
		{
			ArrayList <Item>itemList = new ArrayList<Item>();
			itemList.add(item);
			createItems("items", itemList);

		}
	}
}
