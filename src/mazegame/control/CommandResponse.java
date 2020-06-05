package mazegame.control;

public class CommandResponse {
	private Boolean finishedGame;
	private String message;
	private boolean finishedTurn;
	
	public CommandResponse(String message)
	{
		this.message = message;
		finishedGame = false;
		finishedTurn = false;
	
	}
	
	public CommandResponse(String message, Boolean quitFlag)
	{
		this.message = message;
		finishedGame = quitFlag;
	}
	
	public void setFinishedGame(Boolean quitFlag)
	{
		finishedGame = quitFlag;
	}
	
	public boolean isFinishedGame()
	{
		return finishedGame;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public boolean isFinishedTurn()
	{	
		return finishedTurn;
	}
	
	public void setFinishedTurn(boolean finishedTurn)
	{
		this.finishedTurn = finishedTurn;
	}
		
}
