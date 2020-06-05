package mazegame.entity.table;

import java.util.HashMap;

public class AgilityTable {
	
	private static AgilityTable instance = null;
	private HashMap<Integer, Double> agilityModifier = new HashMap<Integer, Double>();
	
	public static AgilityTable getInstance()
	{
		if(instance == null)
		{
			instance = new AgilityTable(); 
		}
		return instance;
	}
	
	public boolean setModifier(int number, double value)
	{
		if(agilityModifier.containsKey(number))
		{
			return false;
		}
		agilityModifier.put(number, value);
		return true;
	}
	
	public double getAgility(int key)
	{
		if(agilityModifier.containsKey(key)) {
			return agilityModifier.get(key);
		}
		return 0;
	}
}
