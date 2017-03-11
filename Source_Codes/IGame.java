import java.util.ArrayList;



public interface IGame  {
	
	public int reverse(int player_increcounter);
	
	public abstract int SkipTurn(int n_Player,int increment);
	
	public abstract int nextPlayer(int n_Player,int increment);
	
	public abstract int getUserSelection(int temp_index,Player p,ArrayList<Card> hand);
		
	public abstract void showMessage(String msg, String type,String title);
			
}
