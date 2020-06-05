package mazegame.entity;

import java.util.HashMap;

public class NonPlayerCharacter extends Character{

	private boolean hostile;
	private HashMap<String, String> conversationList;
	

	public NonPlayerCharacter(String name)
	{
		super(name);
	}

	public boolean isHostile() {
		return hostile;
	}

	public void setHostile(boolean hostile) {
		this.hostile = hostile;
	}
	
	public HashMap<String, String> getConversationListMap() {
		return conversationList;
	}

	public void setConversationListMap(HashMap<String, String> conversationList) {
		this.conversationList = conversationList;
	}

	public void attackPlayer(Player thePlayer, NonPlayerCharacter npc)
	{
		
		try {
		    Thread.sleep(1000); /// delay the attack done by npc by 1 second
		    Dice dice = new Dice(2, 6);   //equivalent to 2 rolls having 6 sides of a die
			int randomDamage = dice.rollMyDiceManyTimes(); 
			double finalDamage = npc.getStrength() + randomDamage; 
			//double finalDamage = randomDamage;
			double playerlifePoints = thePlayer.getLifePoints() - finalDamage;
			thePlayer.setLifePoints(playerlifePoints);
			System.out.println("");
			System.out.println("*************************************************************************************");
			System.out.println("\t\t\t\tDAMAGE TAKEN");
			System.out.println("");
			System.out.println(npc.getName() + " attacked you");
			System.out.println("Your recieved a damage of " + randomDamage + " with enemy's strength and hit points");
			if(thePlayer.getLifePoints() > 0)
			{
				System.out.println("Your current life points is " + thePlayer.getLifePoints());
			}
			else
			{
				System.out.println("Your life point is 0");
			}
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
}


