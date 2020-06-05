package mazegame;

import java.util.Scanner;

import mazegame.boundary.IMazeClient;

public class SimpleConsoleClient implements IMazeClient {
	

	private String playerMessage;
	
	public String getReply (String question)
	{
		
		System.out.println("\n" + question + " ");
		Scanner in = new Scanner (System.in);
		return in.nextLine();
	}
	
	public void playerMessage (String message)
	{
		System.out.print(message);
		
	}
	
	public String getCommand()
	{
		System.out.print ("\n\n>>>\t");
		return new Scanner(System.in).nextLine();
	
	}
	
	public String getCommands()
	{
		System.out.print ("\n\n>>>\t");
		return new Scanner(System.in).nextLine();
	
	}
	

	@Override
	public String getplayerMessage() {
		// TODO Auto-generated method stub
		return playerMessage;
	}

	@Override
	public void setPlayerMessage(String playerMessage) {
		// TODO Auto-generated method stub
		this.playerMessage = playerMessage;
	}
}
