	package mazegame.control;

import java.util.ArrayList;
import mazegame.boundary.IMazeClient;
import mazegame.boundary.IMazeData;
import mazegame.entity.Dice;
import mazegame.entity.NonPlayerCharacter;
import mazegame.entity.Player;
import mazegame.entity.table.AgilityTable;
import mazegame.entity.table.StrengthTable;
import mazegame.entity.table.WeightLimit;

public class DungeonMaster {
	private IMazeClient gameClient;
	private IMazeData gameData;
	private Player thePlayer;
	private boolean continueGame;
	private ArrayList<String> commands;
	private Parser theParser;
	private CommandHandler playerTurnHandler;


	//Imaze gameData -> HardCodedData;
	//ImazeClient gameClient -> SimpleConsoleClient;

	private static DungeonMaster instance = null;

	private DungeonMaster()
	{

	}

	public static DungeonMaster getInstance()
	{
		if(instance == null)
		{
			instance = new DungeonMaster();
		}
		return instance;
	}

	public DungeonMaster(IMazeData gameData, IMazeClient gameClient)
	{
		this.gameData = gameData;
		this.gameClient = gameClient;
		playerTurnHandler = new CommandHandler(); 
	}

	public void printWelcome()
	{
		gameClient.playerMessage(gameData.getWelcomeMessage());
	}
	public void setupPlayer() 
	{
		String playerName = gameClient.getReply("Hello mighty player. Please tell us your name so that we can set you up."
				+ "\nWhat do you want to be known by ?");
		if(!playerName.isEmpty())
		{
			thePlayer = new Player(playerName);
			thePlayer.setCurrentLocation(gameData.getStartingLocation());
			thePlayer.setInventoryList(gameData.getStartingInventory());
			//Setting up agility, lifepoints, strength and weight limit
			Dice dice = new Dice();
			int randomNumber = dice.generateRandomNumber(1,46);
			thePlayer.setStrength(StrengthTable.getInstance().getStrength(randomNumber));
			thePlayer.setAgility(AgilityTable.getInstance().getAgility(randomNumber));
			//thePlayer.setWeightLimit(WeightLimit.getInstance().getweightLimit(randomNumber));
			thePlayer.setWeightLimit(20);			
			thePlayer.setLifePoints(20);
			thePlayer.setFinishedTurn(false);
			thePlayer.setFinishedGame(false);
			gameClient.playerMessage("Welcome " + playerName + "\n");			
			gameClient.playerMessage("\n\nPlease enjoy the game");
			gameClient.playerMessage("\n\nYou are right now at ");
			gameClient.playerMessage(thePlayer.getCurrentLocation().getDescription() + "\n");
			gameClient.playerMessage("Current Location => " + thePlayer.getCurrentLocation().getLabel());	
			
		}
		else
		{
			setupPlayer();
		}
	}

	public void runGame()
	{
		printWelcome();
		setupPlayer();
		processTurns();
	}

	public void processTurns()
	{
		while (thePlayer.isFinishedGame() == false && thePlayer.isFinishedTurn() == false)
		{
			handlePlayerTurn();
		}		
		if(thePlayer.isFinishedGame() == false)
		{			
			if(thePlayer.getCurrentLocation().getNPC().size() > 0 && thePlayer.getCurrentLocation().getNPC().get("npc") != null)
			{
				//System.out.println(thePlayer.getCurrentLocation().getNPC().get("npc").getLifePoints());
				if(thePlayer.getCurrentLocation().getNPC().get("npc").getLifePoints() > 0 &&
						thePlayer.getCurrentLocation().getNPC().get("npc").isHostile() == true)
				{
					handleHostileNPCTurn();
				}		
				
			}
			if(thePlayer.getLifePoints() > 0)
			{
				thePlayer.setFinishedTurn(false); 
				processTurns();
			}
			else
			{	
				gameClient.playerMessage("***************************************************");	
				gameClient.playerMessage("\nYou were defeated mighty player. \nPlease try again next time. Thanks for playing");
				thePlayer.setFinishedGame(true);
				exitGame();
			}
		}
		else
		{
			exitGame();
		}

	}

	private void handleHostileNPCTurn() {				
		NonPlayerCharacter npc = thePlayer.getCurrentLocation().getNPC().get("npc");
		npc.attackPlayer(thePlayer, npc);		
	}

	private void handlePlayerTurn()
	{
		//playerTurnHandler is an instance of CommandHandler;
		CommandResponse playerResponse = playerTurnHandler.processTurn(gameClient.getCommand(), thePlayer);				
		gameClient.setPlayerMessage(playerResponse.getMessage());		
		if(gameClient.getplayerMessage().equalsIgnoreCase("attack-mode") || 
		   gameClient.getplayerMessage().equalsIgnoreCase("equip-in-attack-mode") ||
		   gameClient.getplayerMessage().equalsIgnoreCase("unequip-in-attack-mode") ||
		   gameClient.getplayerMessage().equalsIgnoreCase("restored-in-attack-mode") ||
		   gameClient.getplayerMessage().equalsIgnoreCase("flee-unsuccessful"))
		{
			thePlayer.setFinishedTurn(true);
		}
		else if(gameClient.getplayerMessage().equalsIgnoreCase("normal-unequip-mode") || 
				gameClient.getplayerMessage().equalsIgnoreCase("normal-equip-mode") ||
				gameClient.getplayerMessage().equalsIgnoreCase("normal-restore-mode")) 		
		{
			thePlayer.setFinishedTurn(false);
		}
		else
		{
			gameClient.playerMessage(playerResponse.getMessage()); 
		}
	}

	private void exitGame()
	{
		gameClient.playerMessage("\n***************************************************");
		gameClient.playerMessage("\nThanks for playing dungeon master");
		gameClient.playerMessage("\nVersion 1.0");
	}

}
