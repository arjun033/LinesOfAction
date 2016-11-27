package line.of.action;

public class Player {
	// player can be W or B 
	public static final char W='X',B='O',ZERO='_';
	public char name;
	public char otherPlayerName;

	public Player(char name) 
	{
		this.name=name;
	}
	public char getName() {
		return name;
	}
	public void setName(char name) {
		this.name = name;
	}

	public char getOtherPlayerName() {
		return otherPlayerName;
	}
	
	public Player getOpponent()
	{
		if(this.name==W)
			return new Player(B);
		return new Player(W);
		
			
	}

	public void setOtherPlayerName(char otherPlayerName) {
		this.otherPlayerName = otherPlayerName;
	}

	public void reversePlayerTurn(char playerName) 
	{
		if(playerName == W) 
		{
			otherPlayerName = B;
		} 
		else if(playerName == B) 
		{
			otherPlayerName = W;
		}
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", otherPlayerName=" + otherPlayerName
				+ "]";
	}
	public static void main(String args[])
	{
		Player player= new Player(B);
		System.out.println(player.getOpponent());
		
	}
	
}
