import javax.swing.JOptionPane;
public class Ace extends Card {
	
	public Ace(int rank, int suit) {
		super(rank, suit);
		// TODO Auto-generated constructor stub	
	}
	@Override	
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


}
