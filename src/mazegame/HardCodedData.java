package mazegame;

import java.util.ArrayList;
import java.util.HashMap;

import mazegame.boundary.IMazeData;
import mazegame.entity.Armor;
import mazegame.entity.Dice;
import mazegame.entity.Exit;
import mazegame.entity.Inventory;
import mazegame.entity.Item;
import mazegame.entity.Location;
import mazegame.entity.NonPlayerCharacter;
import mazegame.entity.Shield;
import mazegame.entity.Shop;
import mazegame.entity.Weapon;
import mazegame.entity.table.AgilityTable;
import mazegame.entity.table.StrengthTable;
import mazegame.entity.table.WeightLimit;

public class HardCodedData implements IMazeData {
	
	private Location startUp;
    private ArrayList<Item> weaponList = new ArrayList<Item>();
    private ArrayList<Item> armorList = new ArrayList<Item>();
    private ArrayList<Item> shieldList = new ArrayList<Item>();
    private ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
    private ArrayList<Item> itemsList = new ArrayList<Item>();
    private ArrayList<Item> itemsList2 = new ArrayList<Item>();
    private ArrayList<Item> itemsList3 = new ArrayList<Item>();
    private ArrayList<Item> itemsList4 = new ArrayList<Item>();  
    private ArrayList<Item> itemsList5 = new ArrayList<Item>(); 
    private ArrayList<Item> itemsList6 = new ArrayList<Item>(); 
    private ArrayList<Location> map = new ArrayList<Location>();
    
    HashMap<String, String> conversationList = new HashMap<String, String>();
    HashMap<String, String> conversationList2 = new HashMap<String, String>();
    
    private static HardCodedData instance = null;
  
	public HardCodedData()
	{
		createItems();
		createConversationList();
		createWorldMap();
		populateWeightLimitTable();
		populateAgilityTable();
		populateStrengthTable();			

	}
	
	public static HardCodedData getInstance()
	{
		if(instance == null)
		{
			instance = new HardCodedData();
		}
		return instance;
	}
	
	public Location getStartingLocation()
	{
		return startUp;
	}
	
	public ArrayList<Inventory> getStartingInventory()
	{
		return inventoryList;
	}
	
	public ArrayList<Location> getMazeStatus()
	{
		return map;
	}
	

	public String getWelcomeMessage()
	{
		return "\n----------------------------------------------------------------------\n"
				+ "----------------------- Welcome to the Dungeon Master ---------------- \n"
				+ "----------------------------------------------------------------------";
		
	}
	
	private void createWorldMap() 
	{
		//Create Locations
		startUp = new Location ("A place with high walls and watch towers", "Castle");
		Location jungle = new Location ("A dark dense forest ", "Jungle");
		Location mountain = new Location ("Place with snow and mountatins", "Mountain");
		Location desert = new Location ("Sandy land with extreme heat", "Desert");
		Location oasis = new Location("A fertile spot in a desert", "Oasis");  
		Location cave = new Location("A large hollow dark place", "Cave"); 
		Location plateau = new Location("A fairly high level ground", "Plateau");
		Location town = new Location("A place with houses and many people", "Town");
		Location greenland = new Location("A place with flat green land", "GreenLand");
		Shop blacksmith = new Shop("A place to buy attacking weapons", "Blacksmith");
		Shop shieldShop = new Shop("A place for buying shield", "Shield Shop");
		Shop armorShop = new Shop("A place to buy defensive weapons", "Armor Shop");
		
		//Create Non Player Characters
		Dice dice = new Dice();
		int randomNumber = dice.generateRandomNumber(1,46);
		NonPlayerCharacter hydra = new NonPlayerCharacter("hydra");
		hydra.setStrength(StrengthTable.getInstance().getStrength(randomNumber));
		hydra.setAgility(AgilityTable.getInstance().getAgility(randomNumber));
		hydra.setLifePoints(15);
		hydra.setHostile(true);		
				
		int randomNumber2 = dice.generateRandomNumber(1,46);
		NonPlayerCharacter dragon = new NonPlayerCharacter("dragon");
		dragon.setStrength(StrengthTable.getInstance().getStrength(randomNumber2));
		dragon.setAgility(AgilityTable.getInstance().getAgility(randomNumber2));
		dragon.setLifePoints(18);
		dragon.setHostile(true);		
		
		
		NonPlayerCharacter jack = new NonPlayerCharacter("jack");		
		jack.setHostile(false);
		jack.setConversationListMap(conversationList);
		
		NonPlayerCharacter ranger = new NonPlayerCharacter("ranger"); 
		ranger.setHostile(false); 
		ranger.setConversationListMap(conversationList2);
		
		//Create map to get maze status
		map.add(startUp);
		map.add(jungle);
		map.add(mountain); 
		map.add(desert);
		map.add(oasis);
		map.add(cave); 
		map.add(plateau);
		map.add(town); 
		map.add(greenland);  
		map.add(blacksmith); 
		map.add(shieldShop); 
		map.add(armorShop);
		
		// Connect Locations, add items and add npc to locations
		
		startUp.addExit("south",  new Exit (jungle.getDescription(), jungle, false));
		startUp.addExit("west", new Exit(desert.getDescription(), desert, false));
		startUp.addExit("east", new Exit(town.getDescription(), town, false));
		startUp.addExit("north", new Exit(mountain.getDescription(),  mountain, false));
		
		startUp.addExit("northeast", new Exit(plateau.getDescription(), plateau, false));
		startUp.addExit("northwest", new Exit(greenland.getDescription(), greenland, false));
		startUp.addExit("southwest", new Exit(oasis.getDescription(), oasis, true));
		startUp.addExit("southeast", new Exit(cave.getDescription(), cave, false));
		
		town.addExit("west", new Exit(startUp.getDescription(), startUp, false));
		town.addExit("north", new Exit(plateau.getDescription(), plateau, false));
		town.addExit("south", new Exit(cave.getDescription(), cave, false));
		town.addExit("northwest", new Exit(mountain.getDescription(), mountain, false));
		town.addExit("southwest", new Exit(jungle.getDescription(), jungle, false));
		town.addExit("northeast", new Exit(armorShop.getDescription(), armorShop, false));
		town.addExit("east", new Exit(shieldShop.getDescription(), shieldShop, false));		
		town.addNPC("npc", jack);
		town.createItems("items", itemsList5);
		
		desert.addExit("east", new Exit(startUp.getDescription(), startUp, false));
		desert.addExit("north", new Exit(greenland.getDescription(), greenland, false));
		desert.addExit("south", new Exit(oasis.getDescription(), oasis, true)); 
		desert.addExit("northeast", new Exit(mountain.getDescription(), mountain, false)); 
		desert.addExit("southeast", new Exit(jungle.getDescription(), jungle, false));
		desert.addExit("west", new Exit(blacksmith.getDescription(), blacksmith, false)); 
		desert.addNPC("npc", ranger);
		desert.createItems("items", itemsList6);
		
		plateau.addExit("south", new Exit(town.getDescription(), town, false));
		plateau.addExit("west", new Exit(mountain.getDescription(), mountain, false));
		plateau.addExit("southwest", new Exit(startUp.getDescription(), startUp, false));
		plateau.addExit("east", new Exit(armorShop.getDescription(), armorShop, false));
		plateau.addExit("southeast", new Exit(shieldShop.getDescription(), shieldShop, false));
		plateau.createItems("items", itemsList2);
		
		mountain.addExit("south", new Exit(startUp.getDescription(), startUp, false));
		mountain.addExit("east", new Exit(plateau.getDescription(), plateau, false));
		mountain.addExit("southeast", new Exit(town.getDescription(), town, false));
		mountain.addExit("west", new Exit(greenland.getDescription(), greenland, false));
		mountain.addExit("southwest", new Exit(desert.getDescription(), desert, false));
		mountain.addNPC("npc", dragon);
		mountain.createItems("items", itemsList3);
		
		greenland.addExit("south", new Exit(desert.getDescription(), desert, false));
		greenland.addExit("east", new Exit(mountain.getDescription(), mountain, false));
		greenland.addExit("southeast", new Exit(startUp.getDescription(), startUp, false)); 
		greenland.addExit("southwest", new Exit(blacksmith.getDescription(), blacksmith, false));
		greenland.createItems("items", itemsList3);
		
		oasis.addExit("north", new Exit(desert.getDescription(),desert, false));
		oasis.addExit("east", new Exit(jungle.getDescription(), jungle, false));
		oasis.addExit("northeast", new Exit(startUp.getDescription(), startUp, false));
		oasis.addExit("northwest", new Exit(blacksmith.getDescription(), blacksmith, false));
		oasis.createItems("items", itemsList4);
		
		jungle.addExit("north", new Exit(startUp.getDescription(), startUp,false)); 
		jungle.addExit("west", new Exit(oasis.getDescription(), oasis, true)); 
		jungle.addExit("east", new Exit(cave.getDescription(), cave, false)); 
		jungle.addExit("northwest", new Exit(desert.getDescription(), desert, false)); 
		jungle.addExit("northeast", new Exit(town.getDescription(), town, false)); 
		jungle.addNPC("npc", hydra);
		jungle.createItems("items", itemsList3);
		
		cave.addExit("north", new Exit(town.getDescription(), town, false)); 
		cave.addExit("west", new Exit(jungle.getDescription(), jungle, false)); 
		cave.addExit("northwest", new Exit(startUp.getDescription(), startUp, false)); 
		cave.addExit("northeast", new Exit(shieldShop.getDescription(), shieldShop, false));
		cave.createItems("items", itemsList);
		
		blacksmith.addExit("east", new Exit(desert.getDescription(), desert, false));
		blacksmith.addExit("southeast", new Exit(oasis.getDescription(), oasis, true));
		blacksmith.addExit("northeast", new Exit(greenland.getDescription(), greenland, false));		
		blacksmith.createItems("weapon", weaponList);	
		
		armorShop.addExit("west", new Exit(plateau.getDescription(), plateau, false)); 
		armorShop.addExit("southwest", new Exit(town.getDescription(), town, false)); 
		armorShop.addExit("south", new Exit(shieldShop.getDescription(), shieldShop, false));
		armorShop.createItems("armor", armorList);
		
		shieldShop.addExit("north", new Exit(armorShop.getDescription(), armorShop, false)); 
		shieldShop.addExit("northwest", new Exit(plateau.getDescription(), plateau, false)); 
		shieldShop.addExit("west", new Exit(town.getDescription(), town, false)); 
		shieldShop.addExit("southwest", new Exit(cave.getDescription(), cave,false));
		shieldShop.createItems("shield", shieldList);
	
	}
	
	private void createConversationList()
	{
		conversationList.put("hello", "Hey dungeon master. How are you doing ?"); 
		conversationList.put("good", "That's great.\nHave you heard about the dragon that lives in the mountain on the\n far north of the castle ?");
		conversationList.put("yes", "Well then you should try to defeat it if you are a \nworthy player."); 
		conversationList.put("no", "Well the people in the town are terrified.");
		conversationList.put("ok", "I like to hear that. You can find valuable stuffs across \ndifferent locations and buy stuffs in different shops to aid in your journey."); 
		conversationList.put("sure","Well then tell me when you defeat the dragon.");
		conversationList.put("defeated", "I knew you were a brave warrior. I will spread the good \nnews around the town.");
		conversationList.put("fleed", "Well better luck next time lad.");
		conversationList.put("bye", "Good luck on your quest lad.");
		conversationList.put("nothing", "Nevermind lad.");
		
		conversationList2.put("hello", "Hey lad. Do you know where the direction of jungle is ?");
		conversationList2.put("yes", "I am a ranger and was defeated by hydra in the jungle.\nSo I had to quickly run away."); 
		conversationList2.put("ok", "I somehow managed to escape but forgot the way to go \nback. Can you tell me the direction please?"); 
		conversationList2.put("southeast", "Thanks lad, I have to get my revenge."); 
		conversationList2.put("no", "Nevermind lad. I will find it on my own once I finish \nmy preparation. I tried to go south but it is locked out there. \nI wonder what there is.");		 		
		conversationList2.put("bye", "See ya lad"); 
		conversationList2.put("nothing", "Nevermind lad.");
	}
	
	private void createItems()
	{
	  	
			// name,  description,  weight,  value, damage
			Item sword = new Weapon("sword","A power sword", 3 , 10, "4d8");
			Item axe = new Weapon("axe", "A huge axe", 4, 8, "1d6");
			Item dagger =new Weapon("dagger", "A dagger", 2, 1,"2d4");
			Item knife = new Weapon("knife", "A tiny knife", 2, 2,"1d3"); 
			Item spear = new Weapon("spear", "A flex weapon",9,5, "3d7");
			Item staff = new Weapon("staff", "A tiny staff", 4, 4,"1d4");
			
			//name, description, weight, value, bonus point
			Item padded = new Armor("padded", "A padded armor", 4, 5, 1);
			Item leather = new Armor("leather", "A leather armor",6 , 10, 2);
			Item chain = new Armor("chain", "A chain armor", 8, 20, 5);
			
			Item wooden = new Shield("wooden","A wooden shield", 5,2, 1); 
			Item steel = new Shield("steel", "A steel shield", 15,20, 5);  
			Item buckle = new Shield("buckle", "A buckle shield", 5, 15, 3);
					
			Item coin = new Item("Coin", "A Coin", 0.1, 15);
			Item coin2 = new Item("Coin", "A Coin", 0.2, 10);
			Item coin3 = new Item("Coin", "A Coin", 0.3, 20);
			
			Item key = new Item("Key", "A key", 0.1, 0);
			Item potion = new Item("Potion", "potion", 0.3, 0);
				
			weaponList.add(sword);
			weaponList.add(axe);
			weaponList.add(dagger); 
			weaponList.add(knife);
			weaponList.add(spear);
			
			armorList.add(padded); 
			armorList.add(leather); 
			armorList.add(chain); 
			
			shieldList.add(wooden);
			shieldList.add(steel);
			shieldList.add(buckle);
			
			itemsList.add(coin);
			itemsList.add(key); 
			
			itemsList2.add(coin2);
			itemsList2.add(potion);
			
			itemsList3.add(potion);
			
			itemsList4.add(coin3);
			itemsList4.add(potion);		
			
			itemsList5.add(coin);
			
			itemsList6.add(staff);
					
	}
	
	private void populateStrengthTable()
    {
        StrengthTable strengthModifiers = StrengthTable.getInstance();
        strengthModifiers.setModifier(1, 0);
        strengthModifiers.setModifier(2, 0);
        strengthModifiers.setModifier(3, 0);
        strengthModifiers.setModifier(4, 0);
        strengthModifiers.setModifier(5, 1);
        strengthModifiers.setModifier(6, 1);
        strengthModifiers.setModifier(7, 1);
        strengthModifiers.setModifier(8, 2);
        strengthModifiers.setModifier(9, 2);
        strengthModifiers.setModifier(10, 2);
        strengthModifiers.setModifier(11, 2);
        strengthModifiers.setModifier(12, 3);
        strengthModifiers.setModifier(13, 3);
        strengthModifiers.setModifier(14, 3);
        strengthModifiers.setModifier(15, 3);
        strengthModifiers.setModifier(16, 4);
        strengthModifiers.setModifier(17, 4);
        strengthModifiers.setModifier(18, 4);
        strengthModifiers.setModifier(19, 4);
        strengthModifiers.setModifier(20, 5);
        strengthModifiers.setModifier(21, 5);
        strengthModifiers.setModifier(22, 5);
        strengthModifiers.setModifier(23, 5);
        strengthModifiers.setModifier(24, 5);
        strengthModifiers.setModifier(25, 5);
        strengthModifiers.setModifier(26, 6);
        strengthModifiers.setModifier(27, 6);
        strengthModifiers.setModifier(28, 6);
        strengthModifiers.setModifier(29, 6);
        strengthModifiers.setModifier(30, 7);
        strengthModifiers.setModifier(31, 7);
        strengthModifiers.setModifier(32, 7);
        strengthModifiers.setModifier(33, 7);
        strengthModifiers.setModifier(34, 7);
        strengthModifiers.setModifier(35, 7);
        strengthModifiers.setModifier(36, 8);
        strengthModifiers.setModifier(37, 8);
        strengthModifiers.setModifier(38, 8);
        strengthModifiers.setModifier(39, 8);
        strengthModifiers.setModifier(40, 9);
        strengthModifiers.setModifier(41, 9);
        strengthModifiers.setModifier(42, 9);
        strengthModifiers.setModifier(43, 9);
        strengthModifiers.setModifier(44, 10);
        strengthModifiers.setModifier(45, 10);
        strengthModifiers.setModifier(46, 10);
    }

    private void populateAgilityTable()
    {
        AgilityTable agilityModifiers = AgilityTable.getInstance();
        agilityModifiers.setModifier(1, 0);
        agilityModifiers.setModifier(2, 0);
        agilityModifiers.setModifier(3, 0);
        agilityModifiers.setModifier(4, 0);
        agilityModifiers.setModifier(5, 0);
        agilityModifiers.setModifier(6, 0);
        agilityModifiers.setModifier(7, 1);
        agilityModifiers.setModifier(8, 1);
        agilityModifiers.setModifier(9, 1);
        agilityModifiers.setModifier(10, 2);
        agilityModifiers.setModifier(11, 2);
        agilityModifiers.setModifier(12, 2);
        agilityModifiers.setModifier(13, 3);
        agilityModifiers.setModifier(14, 3);
        agilityModifiers.setModifier(15, 3);
        agilityModifiers.setModifier(16, 3);
        agilityModifiers.setModifier(17, 3);
        agilityModifiers.setModifier(18, 4);
        agilityModifiers.setModifier(19, 4);
        agilityModifiers.setModifier(20, 6);
        agilityModifiers.setModifier(21, 6);
        agilityModifiers.setModifier(22, 6);
        agilityModifiers.setModifier(23, 6);
        agilityModifiers.setModifier(24, 6);
        agilityModifiers.setModifier(25, 6);
        agilityModifiers.setModifier(26, 8);
        agilityModifiers.setModifier(27, 8);
        agilityModifiers.setModifier(28, 8);
        agilityModifiers.setModifier(29, 8);
        agilityModifiers.setModifier(30, 8);
        agilityModifiers.setModifier(31, 8);
        agilityModifiers.setModifier(32, 10);
        agilityModifiers.setModifier(33, 10);
        agilityModifiers.setModifier(34, 10);
        agilityModifiers.setModifier(35, 10);
        agilityModifiers.setModifier(36, 10);
        agilityModifiers.setModifier(37, 10);
        agilityModifiers.setModifier(38, 10);
        agilityModifiers.setModifier(39, 10);
        agilityModifiers.setModifier(40, 12);
        agilityModifiers.setModifier(41, 12);
        agilityModifiers.setModifier(42, 12);
        agilityModifiers.setModifier(43, 12);
        agilityModifiers.setModifier(44, 14);
        agilityModifiers.setModifier(45, 14);
        agilityModifiers.setModifier(46, 14);
    }

    private void populateWeightLimitTable()
    {
        WeightLimit weightModifier = WeightLimit.getInstance();
        weightModifier.setModifier(1, 10);
        weightModifier.setModifier(2, 10);
        weightModifier.setModifier(3, 10);
        weightModifier.setModifier(4, 10);
        weightModifier.setModifier(5, 11);
        weightModifier.setModifier(6, 11);
        weightModifier.setModifier(7, 11);
        weightModifier.setModifier(8, 11);
        weightModifier.setModifier(9, 11);
        weightModifier.setModifier(10, 12);
        weightModifier.setModifier(11, 12);
        weightModifier.setModifier(12, 12);
        weightModifier.setModifier(13, 12);
        weightModifier.setModifier(14, 12);
        weightModifier.setModifier(15, 13);
        weightModifier.setModifier(16, 13);
        weightModifier.setModifier(17, 13);
        weightModifier.setModifier(18, 13);
        weightModifier.setModifier(19, 14);
        weightModifier.setModifier(20, 14);
        weightModifier.setModifier(21, 15);
        weightModifier.setModifier(22, 15);
        weightModifier.setModifier(23, 15);
        weightModifier.setModifier(24, 16);
        weightModifier.setModifier(25, 16);
        weightModifier.setModifier(26, 17);
        weightModifier.setModifier(27, 17);
        weightModifier.setModifier(28, 17);
        weightModifier.setModifier(29, 17);
        weightModifier.setModifier(30, 18);
        weightModifier.setModifier(31, 18);
        weightModifier.setModifier(32, 18);
        weightModifier.setModifier(33, 18);
        weightModifier.setModifier(34, 19);
        weightModifier.setModifier(35, 19);
        weightModifier.setModifier(36, 20);
        weightModifier.setModifier(37, 25);
        weightModifier.setModifier(38, 25);
        weightModifier.setModifier(39, 25);
        weightModifier.setModifier(40, 26);
        weightModifier.setModifier(41, 26);
        weightModifier.setModifier(42, 26);
        weightModifier.setModifier(43, 27);
        weightModifier.setModifier(44, 27);
        weightModifier.setModifier(45, 30);
        weightModifier.setModifier(46, 30);
    }
    
    
}
