import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
public class Deck {
	ArrayList<Card> deck = new ArrayList<Card> ();
	final int NUM_CARDS = 52;  //for this kind of deck
	static int Cards_Left = 52;

	//creates a new sorted deck
	public Deck() {
			for (int j = Card.LOW_RANK; j <= Card.TOP_RANK; j = j + 1) {
				deck.add(new Card(j, Card.Suits.club));
			}
			for (int j = Card.LOW_RANK; j <= Card.TOP_RANK; j = j + 1){
				deck.add(new Card(j, Card.Suits.diamond));
			}
			for (int j = Card.LOW_RANK; j <= Card.TOP_RANK; j = j + 1){
				deck.add(new Card(j, Card.Suits.heart));
			}
			for (int j = Card.LOW_RANK; j <= Card.TOP_RANK; j = j + 1){
				deck.add(new Card(j, Card.Suits.spade));
			}
	}
	
	public void shuffle() {
		Random randomGenerator = new Random();
		//randomGenerator.setSeed(20);
		int numRandSwap = 100;
		for(int i = 0; i < numRandSwap; i = i + 1) {
			int first_swap = randomGenerator.nextInt(52);
			int secon_swap = randomGenerator.nextInt(52);
			Card tmp = deck.get(first_swap);
			deck.set(first_swap, deck.get(secon_swap));
			deck.set(secon_swap, tmp);
		}
	}
	
	
	public void printDeck() {
		for (int i = 0; i < deck.size(); i = i + 1) {
			System.out.print(deck.get(i) + " ");
			if((i % 5) == 4){
				System.out.println();
			}

		}
	}

	public void printDeck(int start) {
		for (int i = start; i < deck.size(); i = i + 1) {
			System.out.print(deck.get(i) + " ");
			if((i % 5) == 4){
				System.out.println();
			}

		}
	}

	public void printDeck(int start, int end) {
		for (int i = start; i <= end; i = i + 1) {
			System.out.print(deck.get(i) + " ");
			if((i % 5) == 4){
				System.out.println();
			}
		}
	}

	
	public Card dealCard() {
		/* Must consider what happens when no cards in deck */
		if(deck.size()==0){
			return new Card(-1, Card.Suits.club);
		}
		Card tmp = deck.get(Cards_Left-1);
		deck.remove(Cards_Left-1); // Or remove(tmp)
		Cards_Left = Cards_Left - 1;
		//System.out.println("Cards Remaining: " + Cards_Left);
		return tmp;
	}

	public int length(){
		return this.deck.size();
	}

}
