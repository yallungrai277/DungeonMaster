package mazegame.boundary;

public interface IMazeClient {
	public String getReply (String question);
	public void playerMessage (String message);
	public String getCommand();
	public void setPlayerMessage(String message);
	public String getplayerMessage();
}
