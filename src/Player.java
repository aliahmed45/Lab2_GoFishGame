import java.util.ArrayList;
import java.util.Random;
import java.io.PrintWriter;

public class Player {
	
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> book = new ArrayList<Card>();
	String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public void addCardToHand(Card c) {
		hand.add(c);
	}
	
	public Card removeCardFromHand(Card c) {
		Card retCard = c;
		hand.remove(c);
		return retCard;
	}
	
	public String getName() {
		return name;
	}
	
	public String handToString() {
		String s = new String();
	
		return s;
	}
	
	public String bookToString() {
		String s = new String();
		
		return s;
	}
	
	public int getHandSize() {
		return hand.size();
	}
	
	
	public int getBookSize() {
		return book.size();
	}
	
	
	//Here are som ideas for additional functionality that you may want/need
	//OPTIONAL
    // comment out if you decide to not use it
    //this function will check a players hand for a pair. 
    //If a pair is found, it moves the cards to the book

    public void checkHandForBook(PrintWriter out) {
		if(hand.size()==0){
			//Do something with empty hand
			return;
		}
		if(hand.size()==1){
			return;
		}
		int i = 0;
		int j = 0;
		for(i = 0; i < hand.size()-1; i = i + 1){
			for(j = i+1; j < hand.size(); j = j+1){
				int a = hand.get(i).getRank();
		    	int b = hand.get(j).getRank();
    			if(a == b){
					bookPair(i,j, out);
					i = 0; j = 0;
    			}
			}
		}

	}

	// Add Pair of cards to book
	// Remove Pair of cards from hand
	private void bookPair(int i, int j, PrintWriter out){
		out.write(name + " books " + hand.get(i) + " and " + hand.get(j) + "\n");
		book.add(hand.get(i));
		book.add(hand.get(j));
		hand.remove(hand.get(j)); //Must remove j first otherwise will
		hand.remove(hand.get(i)); //change the index of the 2nd card to remove
	}
    //OPTIONAL
    // comment out if you decide to not use it    
    //Does the player have a card with the same rank as c in her hand?
    public boolean rankInHand(int RankAsked) {
		ArrayList<Integer> handRanks = new ArrayList<Integer>(); //Ranks of Cards in Hand
		for(int i = 0; i < hand.size(); i = i + 1){
			handRanks.add(hand.get(i).getRank());
		}
		if(handRanks.contains(RankAsked)){
			return true;
		}else {
			return false;
		}
    }


	// More advanced version of ranksInHand
	// Looks for all the possible ranks,
	// looking at booked cards of both Players
	//
	public int ranksPossible(){
		return 0;
	}

    //uses some strategy to choose one card from the player's
    //hand so they can say "Do you have a 4?"
	// Something fishy - i think im messing up lines 120-122
    public int chooseCardFromHand() {
		ArrayList<Integer> handRanks = new ArrayList<Integer>(); //Ranks of Cards in Hand
		for(int i = 0; i < hand.size(); i = i + 1){
			handRanks.add(hand.get(i).getRank());
		}
		Random rankGen = new Random();
		//rankGen.setSeed(20);
		int RankAsk = rankGen.nextInt(hand.size());
		return handRanks.get(RankAsk);
    }

    public Card giveCardFromHand(int Rank) {
		ArrayList<Integer> handRanks = new ArrayList<Integer>(); //Ranks of Cards in Hand
		for(int i = 0; i < hand.size(); i = i + 1){
			handRanks.add(hand.get(i).getRank());
		}
		int IndexOfMatchedCard = handRanks.indexOf(Rank);
		Card tmp = hand.get(IndexOfMatchedCard);
		hand.remove(tmp);
		return tmp;
	}
    
    //Does the player have the card c in her hand?
    public boolean cardInHand(Card c) {
    	return false; //stubbed
    }
    

    //OPTIONAL
    // comment out if you decide to not use it    
    //Does the player have a card with the same rank as c in her hand?
    //e.g. will return true if the player has a 7d and the parameter is 7c
    
    public boolean sameRankInHand(Card c) {
    	return false; //stubbed
    }

}
