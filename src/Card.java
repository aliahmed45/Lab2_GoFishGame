import java.util.Enumeration;
public class Card {

	public enum Suits {club, diamond, heart, spade};
	
	static int TOP_RANK = 13; //King
	static int LOW_RANK = 1; //Ace
	
	int rank;  //1 is an Ace
	Suits suit;
	
	public Card() {
		rank = 1;
		suit = Suits.spade;
	}

	public Card(char s) {
		suit = toSuit(s);
		//suit = Suits.s;

	}

	public Card(int r, char s) {
		rank = r;
		suit = toSuit(s);
		//suit = Suits.s;

	}
	
	public Card(int r, Suits s) {
		rank = r;
		suit = s;
	}
	
	private Suits toSuit(char c) {
		if (c == 'c') {
			return Suits.club; //dummy
		} else if (c == 'd'){
			return Suits.diamond;
		} else if (c == 'h') {
			return Suits.heart;
		} else if (c == 's') {
			return Suits.spade;
		} else {
			System.out.println("There is No Suit with that letter");
			return null;
		}
	}
	
	private String suitToString(Suits s) {
		//return "Spade"; //dummy
		return s.toString();
	}
	
	private String rankToString(int r) {
		return String.valueOf(r);
	}


	public void setRank(int r) {
		rank = r;
	}

	public int getRank() {
		return rank;
	}


	public Suits getSuit() {
		return suit;
	}
	
	public String toString() {
		String s = "";
		
		s = s + rankToString(getRank()) + suitToString(getSuit());
		
		return s;
	}
}
