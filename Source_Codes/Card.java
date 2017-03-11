import javax.swing.JOptionPane;

public class Card implements Comparable<Card> {
	/* constant suits and ranks */
	 public static final int SPADE   = 4;
     public static final int HEART   = 3;
     public static final int CLUB    = 2;
     public static final int DIAMOND = 1;
     
	 public static final String[] Suit = { "*", "Diamond", "Club", "Hearts", "Suit"};
     public static final String[] Rank = { "*", "2", "3", "4",
 			   "5", "6", "7", "8", "9", "10", "Joker", "Queen", "King", "Ace"}; 
    private int cardRank;  /* values: 1-13 (see Rank[] above) */
    private int cardSuit;  /* values: 0-3  (see Suit[] above) */
   // private Boolean isplayabe;
    //Create constructor for Card 
    Game g= new Game();
    public Card(int rank,int suit){	
    	if(rank==1){
    		cardRank=14;
    	}
    	else
    		cardRank=rank;
    	this.cardSuit=suit;
    } 
	/**
	 * @return the cardRank
	 */
	public int getCardRank() {
		return cardRank;
	}
	/**
	 * @param cardRank the cardRank to set
	 */

	/**
	 * @return the cardSuit
	 */
	public String suitStr() {
		return( Suit[ cardSuit ] );
	}
	/**
	 * @param cardSuit the cardSuit to set
	 */
	public int getCardSuit(){
		return cardSuit;	
	}
	public void setCardSuit(int suit){
		this.cardSuit=suit;		
	}

	 // The match function to match hand card with the card on the pile
	public Boolean match(Card pile){
		if(this.cardRank==pile.cardRank||this.cardSuit==pile.cardSuit)
			return true;
		else if(this.cardRank==14)
			return true;
		else 
			return false;
	}
	
	//Compare to function which is intended to sort the card in the hand class	
	
	public int compareTo(Card k) {
		int return_val=0;
		if(this.cardSuit==k.cardSuit){
			if(this.cardRank<k.cardRank)
				return_val= -1;
			else if(this.cardRank>k.cardRank)
				return_val=1;
			else if(this.cardRank==k.cardRank)
				return 0;
		}
		
		else if(this.cardRank==k.cardRank){
			if(this.cardSuit<k.cardSuit)
				return_val= -1;
			else if(this.cardSuit>k.cardSuit)
				return_val=1;
			else if(this.cardSuit==k.cardSuit)
				return_val= 0;
		}
		return return_val;
	
	}

	
	public int action(int playcounter, int num_players, int play_increment) {
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

	public void action(){
		 String[] choices = { "*", "Diamond", "Club", "Hearts", "Suit"};
		 String input = (String) JOptionPane.showInputDialog(null, "Please Nominate a Suit",
			        "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, // Use
			                                                                        // default
			                                                                        // icon
			        choices, // Array of choices
			        choices[1]); // Initial choice
		 if(input=="Diamond"){
			 this.setCardSuit(1);
		 }
		 else  if(input=="Club"){
			 this.setCardSuit(2);
			 }
		 else if(input=="Hearts"){
			 this.setCardSuit(3);
			 }
		 else if(input=="Suit"){
			 this.setCardSuit(4);
			 }
	}
		// TODO Auto-generated method stub
		
	public int action(int player_increcounter) {
		return g.reverse(player_increcounter);
		// TODO Auto-generated method stub	
	}

}
