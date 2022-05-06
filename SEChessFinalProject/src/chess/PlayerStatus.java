package chess;

public class PlayerStatus{
	
	boolean inGame;
	
	public PlayerStatus(){
		this.inGame = false;
	}
	
	public void setInGame(){
		this.inGame = true;
	}
	
	public void setNotInGame(){
		this.inGame = false;
	}
	
	public boolean isInGame(){
		return this.inGame;
	}
	
}
