import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
public class Game implements IGame  {
public ArrayList<Card> card_deck= new ArrayList<Card>();
public ArrayList<Card> stock= new ArrayList<Card>();
public ArrayList<Card> pile = new ArrayList<Card>();
Sound_Effect sound=new Sound_Effect();
 Images imgobj = new Images();
   public void launch(){
	   UIManager.put("Panel.font",new FontUIResource("SimSun", Font.BOLD, 14));
	   UIManager.getLookAndFeelDefaults().put("SimSun", new Font(Font.SANS_SERIF, 0, 14));
	    sound.play_song();

		JOptionPane.showMessageDialog(null, "Hi, I am the developer of this Java Card Game. \n My Name is Lee Zhi Jiang \n An undergraduate Computer Sceince student from UTAR, Malaysia ! ", "Hello From Me", JOptionPane.INFORMATION_MESSAGE, imgobj.lzjhandsomepic);
	    showMessage("\n\t This is a simple Java Card Game.\n\n\t The Song Playing now is 小幸运","INFO","WELCOME MESSAGE");
			
		  	/* =================================================================
	        Next: initialize all 52 card objects in the newly created array
	        ================================================================= */
		

		  	for ( int suit = Card.DIAMOND; suit <= Card.SPADE; suit++ ) {          
		  		for ( int rank = 1; rank <= 13; rank++ )
		  			{
		  			
		  			 if(rank==8) { 
						card_deck.add(new Eight(rank,suit));
						continue;
		  			 }
						
		  			 else if(rank==11) { 
							card_deck.add(new Joker(rank,suit));
							continue;
						}
		  			 
		  			 else if(rank==12) { //Special effect for Queen 
						card_deck.add(new Queen(rank,suit));
						continue;
					}
						
					else if (rank==1) {
						card_deck.add(new Ace(rank,suit));	
						continue;
					}
						
		  			//deckOfCards[i++] = new Card(suit, rank);  // Put card in
					else card_deck.add(new Card(rank,suit));
		  			}
		  	}                                         
	
		
		  shuffle(90,card_deck); // Shuffle the card deck 
		  	JOptionPane promptnumplayers = new JOptionPane();
		  	promptnumplayers.setBackground(Color.red);
			Object[] num_of_players = {2, 3, 4};		
			@SuppressWarnings("static-access")
			int n_temp =  (int) promptnumplayers.showInputDialog(null, "Please Select Number of Players",
				        "Number of Playets Selection Dialog", JOptionPane.QUESTION_MESSAGE, imgobj.card, // Use
				                                                                        // default
				                                                                        // icon
				        num_of_players, // Array of number list
				        num_of_players[0]); // Initial choice
		
			 
			int  number_Players= n_temp;
			
			// Initializing player objects 
				
			Player[] wan_jia= new Player[number_Players]; 
			showMessage(number_Players +" player objects has successfully created !","INFO", "LZJ Java Game");
		

			
		  	/* =================================================================
	        Create Player Objects with cards 
	        ================================================================= */
			int k;
			for (k=0;k<number_Players;k++){
				Card c;
			
				String name =JOptionPane.showInputDialog("Name of Player "+ (k+1));
				wan_jia[k]= new Player (name,0);
				
				//Take 5 cards from the deck and give99 to players
				int l=0;
				while (l<5) 
				{
				c=card_deck.get(0);
				wan_jia[k].hand.add(c);
				card_deck.remove(0);
				l++;
				}
				shuffle(90,card_deck); 
				
			}

			// Put the remaining deck as stock where player draw cards from
				
				for (int r=0;r<card_deck.size();r++){
					Card q;
					q=card_deck.get(0);
					stock.add(q);
					card_deck.remove(0);
				}
			for (int i1=0;i1<9999;i1++){
				shuffle(809000,stock);
			}
				
				/* =================================================================
		        The Game begins .... 
		        ================================================================= */
			// Take one card from the stock and put in the pile 
				
				Card temp;
				temp=stock.get(0);
				pile.add(0, temp);
				stock.remove(0);
				
				// The game begins with Player 1 
				int n_Player=0;
				Boolean found_winner=false;
				
				int player_increment=1;
				int winner=-1;
				int skipcount=0;
				
				// Entering an continuous loop where the game will keep running until a winner is found 
				while (found_winner==false){
				
					for (int i1=0;i1<9999;i1++){
						shuffle(809000,stock);
						shuffle(809000,pile);
						
					}
					
					Boolean skipped=false;
	
					// * This section is to reset the player index based on the player sequence either clockwise or anticlockwise
					if (player_increment==1&&n_Player==wan_jia.length){
						n_Player=0;
					}
					else if (player_increment==-1&&n_Player==-1)
						n_Player=wan_jia.length-1;
				// * ===================================================================================================================	
					
					// Inform user about his or her turn and show their card
			
					// Loop through All the player cards and check if user has playable cards
					for (int rr=0;rr<wan_jia.length;rr++){
						wan_jia[rr].setIsPLayable(pile.get(0));
					}
					
					int tcard=0;
					
					while (wan_jia[n_Player].hand.size()<5&&wan_jia[n_Player].IsPLayable()==false){  
						 wan_jia[n_Player].addCard(stock,pile);
						 for (int rr=0;rr<wan_jia.length;rr++){
								wan_jia[rr].setIsPLayable(pile.get(0));
							}
						 Collections.sort(wan_jia[n_Player].hand);
					}

					if (wan_jia[n_Player].IsPLayable()==true){ // Section of codes where player has playable cards 
				
						Collections.sort(wan_jia[n_Player].hand);
						tcard=getUserSelection(tcard,wan_jia[n_Player],pile);  // Calling the method that prompt user for their card selection 
						
						while(wan_jia[n_Player].hand.get(tcard).match(pile.get(0))==false){				
							
							showMessage("This card is not playable !","ERROR", "Message from Game Engine");
							Collections.sort(wan_jia[n_Player].hand);
							tcard=getUserSelection(tcard,wan_jia[n_Player],pile);
							if (tcard==wan_jia[n_Player].hand.size())
							{
								wan_jia[n_Player].addCard(stock,pile);
			
								break;
							}
							
						}

							for (int rr=0;rr<wan_jia.length;rr++){
								wan_jia[rr].setIsPLayable(pile.get(0));			
							}	
							
					
							 if (wan_jia[n_Player].hand.get(tcard) instanceof Joker){							
								
								while(wan_jia[troll(n_Player,player_increment,wan_jia.length)].hand.size()<5){
								
								wan_jia[troll(n_Player,player_increment,wan_jia.length)].addCard(stock, pile);
								}
								
								showMessage(wan_jia[n_Player].getName() +" just issued a troll card to add make " +wan_jia[troll(n_Player,player_increment,wan_jia.length)].getName() +" has 5 cards again !","INFO","MESSAGE FROM GAME ENGINE");								
								sound.evillaugh();
							 }

							
							else if(wan_jia[n_Player].hand.get(tcard) instanceof Ace){
								sound.nominatesuit();
								wan_jia[n_Player].hand.get(tcard).action();															
							}
							
							else if(wan_jia[n_Player].hand.get(tcard) instanceof Eight){
								//showMessage("Order Reversed ! ","INFO","Message From Game Engine");
								sound.orderreversed();
								player_increment=wan_jia[n_Player].hand.get(tcard).action(player_increment);
							}
							
							else if(wan_jia[n_Player].hand.get(tcard) instanceof Queen){
								showMessage(wan_jia[n_Player].getName()+" skipped next player's turn !","INFO","Message From Game Engine");
								sound.evillaugh();
								n_Player=wan_jia[n_Player].hand.get(tcard).action(n_Player,wan_jia.length,player_increment);
							}
						 
							
						for (int rr=0;rr<wan_jia.length;rr++){
							wan_jia[rr].setIsPLayable(pile.get(0));
						}	
														
						   											
						wan_jia[n_Player].playCard(tcard, pile,n_Player,wan_jia.length,player_increment);
						showMessage("Successful placed card on pile","INFO","Message from Game Engine");					
												
						skipcount=0;  // If one player manage to place card on the pile then the consecutive skip counter is reseted 
						
						if(wan_jia[n_Player].hand.isEmpty()){  // When the player hand is empty break the loop and set the player as winner 
							found_winner=true;		
							winner=n_Player; // store the index of the player 
						}
						
						
				}		
					
					else if (wan_jia[n_Player].IsPLayable()==false){
						 UIManager.put("OptionPane.background",new   ColorUIResource(254,255,208));
			             UIManager.put("Panel.background",new ColorUIResource(254,255,208));
			             sound.nocard();
						JOptionPane.showMessageDialog(null, wan_jia[n_Player].getName()+", it seems you do not have any card. You will be skipped this round !", "Message from Game Engine", JOptionPane.INFORMATION_MESSAGE);
						n_Player=SkipTurn(n_Player,player_increment);
						skipped=true;
						skipcount++;;
						
					}
				
					if (skipped==false) // If there are no player skipped proceed to the normal game flow with normal increment 
						n_Player=nextPlayer(n_Player,player_increment);
					
				
					//If the consecutive skips is equals to the number of players the game, break the loop and end the game 
					else if(skipcount==wan_jia.length) 
						break;
					
	}	
		
				//If winner is found ( either one of the player has finished their cards 
				if(found_winner==true) {
					sound.winner(); 
				
					showMessage("Winner Found !\n The Winner is " + wan_jia[winner].getName(),"INFO","WINNER ANNOUCEMENT");
					System.exit(0);
				}
				//Otherwise call method to calculate penalty points for every player 
				else if (found_winner==false){
				
					for(int i1=0;i1<wan_jia.length;i1++)
						wan_jia[i1].calculatepenaltypoints();			
			
					Get_Winner(wan_jia);
					System.exit(0);
					
				}
   }
 
	public  static void main(String[] args) {	
	  Game game= new Game();
	  game.launch();
}			
		
	// Shuffle Any Cards using n exchanges 
	public  void shuffle(int n, ArrayList<Card> o)
	   {
	      int k;	      
	      int one,two,three,four;
	      n*=9000;
	      for ( k = 0; k < n; k++ )
	      {      
	          one = (int) ( o.size() * Math.random() );
	          two = (int) ( o.size() * Math.random() );
	          three = (int) ( o.size() * Math.random() );
	          four = (int) ( o.size() * Math.random() );
	          /* ---------------------------------
	             Swap these randomly picked cards
	             --------------------------------- */
	         Card tmp=o.get(one); 
	         o.set(one, o.get(three));
	          o.set(three, tmp);
	          
	          Card tmp1=o.get(two); 
		         o.set(two, o.get(four));
		          o.set(four, tmp1);        
	      }
	   }
	
	  // Method to call nextPlayer
	public  int nextPlayer(int n_Player,int increment){
		return n_Player+=increment;
	}	
	public  int troll(int n_Player,int increment, int n){
	
		if (n_Player==n-1&&increment==1){
			return 0;
		}	
		else if (n_Player==0&&increment==-1)
			return n-1;
		
		else if(increment==1)
			return n_Player+1;
		
		else if(increment==-1)
			return n_Player-1;
		
		return n_Player;
	}
 	
	//Return player cards in string
	public  String printplayercard(ArrayList<Card> card, int n ){
		 String[] Suit = { "*", "Diamond", "Club", "Hearts", "Suit"};
	      String[] Rank = { "*","*", "2", "3", "4",
	 			   "5", "6", "7", "8", "9", "10", "Joker", "Queen", "King", "Ace"}; 
	      String output =" ";
	      for (int i=0;i<card.size();i++){
			 Card temp=card.get(i);
			 int tempsuit=temp.getCardSuit();
			 int temprank=temp.getCardRank();		
			 output+= i + "| " + Rank[temprank] + " of " + Suit[tempsuit] +"\n";		 
		 }

		 return output;
		
	}
	
	//Print the top card of the pile
	public String print_pile(ArrayList<Card> card, String type){
		 String[] Suit = { "*", "Diamond", "Club", "Hearts", "Suit"};
	      String[] Rank = { "*","*", "2", "3", "4",
	 			   "5", "6", "7", "8", "9", "10", "Joker", "Queen", "King", "Ace"}; 
	      String output =" ";

			 Card temp=card.get(0);
			 int tempsuit=temp.getCardSuit();
			 int temprank=temp.getCardRank();
			 

			return output+=type+" " +Rank[temprank] + " of " + Suit[tempsuit] +"\n";	 
	}

//Skip a turn of the current player 
	public  int SkipTurn(int n_Player,int increment) {
		// TODO Auto-generated method stub
		return (nextPlayer(n_Player,increment));
	}

// Get user selection method that will return user selection for the hand cards
	public int getUserSelection(int temp_index,Player p, ArrayList<Card> pile) {
		UIManager.put("OptionPane.background",new   ColorUIResource(216,247,255));
		UIManager.put("Panel.background",new ColorUIResource(216,247,255));
		UIManager.put("Panel.background",new ColorUIResource(216,247,255));
		// TODO Auto-generated method stu
		String out= printplayercard(p.hand,0);	
		String pile_card=print_pile(pile,"Top Card on the Pile Is ");
		
		  JFrame frame = new JFrame("Prompt for User Selection Dialog");
		
		    String tempo ; 
		    	tempo=(String)JOptionPane.showInputDialog(
		        frame, 
		        pile_card+ "****************************\n" + out + "\nPlease Input Your Card Selection. Hints: Select the Index: ", 
		        "It's " +p.getName() +"'s turn", 
		        JOptionPane.QUESTION_MESSAGE,imgobj.card,null,""
		    );	
	//	String tempo =JOptionPane.showInputDialog(out + "\nPlease Input Your Card Selection. Hints: Select the Index: (Enter " + p.hand.size()+" to draw card)");
		while(tempo==""){
			sound.error();
			JOptionPane.showMessageDialog(null, "That is an invalid move", "Message from game engine", JOptionPane.ERROR_MESSAGE);
			tempo =(String) JOptionPane.showInputDialog(
			        frame, 
			        pile_card+ "***************************\n" +out + "\nPlease Input Your Card Selection. Hints: Select the Index: ", 
			        "It's " +p.getName() +"'s turn", 
			        JOptionPane.QUESTION_MESSAGE,imgobj.card, null, ""
			    );
		}
		
		
		while ((Integer.parseInt(tempo)<0||Integer.parseInt(tempo)>p.hand.size())){
			sound.error();
			JOptionPane.showMessageDialog(null, "That is an invalid move", "Message from game engine", JOptionPane.ERROR_MESSAGE);
			 tempo =(String) JOptionPane.showInputDialog(
				        frame, 
				        pile_card+ "***************************\n" +out + "\nPlease Input Your Card Selection. Hints: Select the Index: ", 
				        "It's " +p.getName() +"'s turn", 
				        JOptionPane.QUESTION_MESSAGE,imgobj.card, null, ""
				    );
		}
		return temp_index=Integer.parseInt(tempo);
	}	
//Method to show any message via pop up GUI dialog 
	public  void showMessage(String msg, String type, String title) {
		// TODO Auto-generated method stub
		  new UIManager();
		  
		JOptionPane showmsginfo = new JOptionPane();
		JOptionPane showmsgerror = new JOptionPane();
		showmsginfo.setBackground(ColorUIResource.GREEN);
		showmsgerror.setBackground(ColorUIResource.RED);
		
	 	
		if(type=="ERROR")
		{
			UIManager.put("OptionPane.background",new   ColorUIResource(255,0,0));
			UIManager.put("Panel.background",new ColorUIResource(255,0,0));
			sound.error();
			JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE,imgobj.exclaimation);
		}
			
		if (type=="INFO"){
			UIManager.put("OptionPane.background",new   ColorUIResource(0,255,0));
			UIManager.put("Panel.background",new ColorUIResource(0,255,0));
			JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE,imgobj.info);
		}		
		
	}
		
	//Method to Get Winner by finding players with lowest penalty points 
	public  void Get_Winner(Player[] player){
		String output="";
		int min=player[0].getPenalty_points(); 
		int player_index=0;
		
		for (int k=0;k<player.length;k++){
			output+=player[k].getName()+" " +player[k].getPenalty_points()+"\n";
			if(player[k].getPenalty_points()<min){
				min=player[k].getPenalty_points();
				player_index=k;
			}
		}
        showMessage(output,"INFO","Score Board");

		sound.winner();
        showMessage(player[player_index].getName() +" with the lowest penalty score of "+ player[player_index].getPenalty_points(),"INFO","Winner Annoucement");
	}

	@Override
	public int reverse(int player_increcounter) {
		showMessage("Order Reversed","INFO","Game Message");
		return player_increcounter=player_increcounter*-1;	
	}		
}