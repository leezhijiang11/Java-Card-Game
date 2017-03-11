
public class Queen extends Card {

	public Queen(int rank, int suit) {
		super(rank, suit);
		// TODO Auto-generated constructor stub
	}
	@Override
	 public int action(int playcounter,int num_players, int play_increment){
		if (num_players==2){
			return playcounter;
		}
		else if (playcounter==num_players-1&&play_increment==1){
			playcounter=0+1;
		}		
		else if (playcounter==0&&play_increment==-1){
			playcounter=num_players-2;
		}		
	return playcounter;		
	 }
}
