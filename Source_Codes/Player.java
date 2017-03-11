import java.util.ArrayList;

public class Player {
	private String Name;
    private int penalty_points;
    private Boolean HasPLayableCards;
    public ArrayList<Card> hand= new ArrayList<>();
	Sound_Effect sound= new Sound_Effect();
	/**
	 * @return the handValue
	 */
    
    // Player Class constructor 
    public Player(String name,int penalty_points){
    	setName(name);  
    	setPenalty_points(penalty_points);
    	this.HasPLayableCards=false;  
    	
    }
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the penalty_points
	 */
	public int getPenalty_points() {
		return penalty_points;
	}
	/**
	 * @param penalty_points the penalty_points to set
	 */
	public void setPenalty_points(int penalty_points) {
		this.penalty_points = penalty_points;
	}
	/**
	 */
	
	//Method for drawing card from the stock deck
	public void addCard(ArrayList<Card> stock, ArrayList<Card> pile) {	
		sound.drawcard();
		if(stock.isEmpty()){
			for (int i=0;i<pile.size();i++){
				stock.add(pile.get(i));
			}
			
			for (int i=0;i<pile.size();i++){
				pile.remove(0);
			}
		}
		hand.add(stock.get(0))	;
		stock.remove(0);	
	}
	/**
	 * @param hand the hand to set
	 */
	
	// Method to play a card ( throw card from hand to the pile ) 
	public void playCard(int c, ArrayList<Card> pile, int player_counter,int num_players, int play_increment) {	 // c is the index which is from tcard in Game class	
		sound.playcard();
		pile.add(0, hand.get(c));
	//	System.out.println("Placing "+ hand.get(c).Rank[hand.get(c).getCardRank()] + " of " +hand.get(c).Suit[hand.get(c).getCardSuit()]+".....");
		hand.remove(c);	

	}
	/**
	 * @return the isPLayable
	 */
	public Boolean IsPLayable() {
		return HasPLayableCards;
	}
	/**
	 * @param isPLayable the isPLayable to set
	 */
	
	// Use the match method in game class to determine whether player has playable cards
	public void setIsPLayable(Card pile) { 
		for (int i=0;i<hand.size();i++){
			if(hand.get(i).match(pile)==true){
				HasPLayableCards=true;
				break;
			}
			else
				HasPLayableCards=false;
		}			
	}
	
	//Method to calculate penalty points of the player from their remainning hand cards
	public void calculatepenaltypoints(){
	
		while(hand.isEmpty()==false){
			if(hand.get(0).getCardRank()==14)
				penalty_points+=20; 				
				
			else if (hand.get(0).getCardRank()==10)
				penalty_points+=10; 
			
			else if (hand.get(0).getCardRank()==11)
				penalty_points+=10; 
			
			else if (hand.get(0).getCardRank()==12)
				penalty_points+=10; 
		
			else if (hand.get(0).getCardRank()<12)
				penalty_points+=hand.get(0).getCardRank(); 
			
			hand.remove(0);
		}
	}




	
}
