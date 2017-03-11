
public class Eight extends Card {
	
	public Eight(int rank, int suit) {
		super(rank, suit);
		// TODO Auto-generated constructor stu	
	}
	Game g= new Game();
	@Override
	public  int action(int player_increcounter) {
		return g.reverse(player_increcounter);
		// TODO Auto-generated method stub	
	}

}
