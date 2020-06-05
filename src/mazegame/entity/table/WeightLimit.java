package mazegame.entity.table;

import java.util.HashMap;

public class WeightLimit {
	
	private static WeightLimit instance = null;
	private HashMap<Integer, Double> weightModifier = new HashMap<Integer, Double>();
	
	public boolean setModifier(int number, double capacity)
	{	
		if(weightModifier.containsKey(number))
		{
			return false;
		}
		weightModifier.put(number, capacity);
		return true;
	}

	public static WeightLimit getInstance() {
		// TODO Auto-generated method stub
		if(instance == null)
		{
			instance = new WeightLimit();
		}
		return instance;
	}
	
	public double getweightLimit(int key)
	{
		if(weightModifier.containsKey(key)) {
			return weightModifier.get(key);
		}
		return 0;
		
	}
}
