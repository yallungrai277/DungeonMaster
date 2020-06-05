package mazegame.entity.table;

import java.util.HashMap;

public class StrengthTable {
	private static StrengthTable instance = null;
	private HashMap<Integer, Double> strengthModifier = new HashMap<Integer, Double>();
	
	public static StrengthTable getInstance()
	{
		if(instance == null)
		{
			instance = new StrengthTable(); 
		}
		return instance;
	}
	
	public boolean setModifier(int number, double value)
	{
		if(strengthModifier.containsKey(number))
		{
			return false;
		}
		strengthModifier.put(number, value);
		return true;
	}
	
	public double getStrength(int key)
	{
		if(strengthModifier.containsKey(key))
		{
			return strengthModifier.get(key);
		}
		return 0;
	}
}
