package mazegame.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dice {
	
	private int sides;
	private int rolls;
	
	private static final Random RANDOM = new Random();
	
	public Dice(int rolls, int sides)
	{		
		this.rolls = rolls;
		this.sides = sides;
		//this.rolls = rolls;
	}
	
	public Dice()
	{
		
	}
    
    public int getSides(){
        return this.sides;
    }

    private int rollMyDice(){
        return  1 + RANDOM.nextInt(this.sides);
    }

    public int rollMyDiceManyTimes(){
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < this.rolls; i++){
            result.add(rollMyDice());
        }        
        //returns the highest number of the list...
        return Collections.max(result);
    }
	
    
    
	public int generateRandomNumber(int min, int max)
	{
		Random random =new Random();
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		//returns random number within a range and both min and mix are inclusive
		return random.nextInt((max - min) + 1) + min; 
				
	}
}
