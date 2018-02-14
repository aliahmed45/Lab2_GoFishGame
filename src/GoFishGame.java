import java.io.*;

public class GoFishGame {

	private static Deck GameDeck;
	private static Player Player1;
	private static Player Player2;
	private static int NumPlayers = 2;

	PrintWriter out;
	public GoFishGame() {
		// TODO Auto-generated constructor stub
		File myFile = new File("GoFish_results.txt");
		try{
			myFile.createNewFile();
		}catch(IOException e){
			e.printStackTrace();
		}
		try {
			out = new PrintWriter(myFile);
		}catch(IOException e){
			e.printStackTrace();
		}

		GameDeck = new Deck();
		GameDeck.shuffle();

		Player1 = new Player("Mazhar");
		Player2 = new Player("Ali");

		for(int i = 0; i < 7; i = i + 1){
			Player1.addCardToHand(GameDeck.dealCard());
			Player2.addCardToHand(GameDeck.dealCard());
		}
		///*System.*/out.write(Player1.getName() + " Cards: " + Player1.hand + "\n");
		///*System.*/out.write(Player2.getName() + " Cards: " + Player2.hand + "\n");

	}

	public void StartGame(){
		/*System.*/out.write("Mazhar goes first\n");
		BeginCheckForBook();
		out.write("\n");
		int turn = 0;
		while(GameDeck.length() != 0) {
			///*System.*/out.write(Player1.getName() + " Cards: " + Player1.hand + "\n");
			///*System.*/out.write(Player2.getName() + " Cards: " + Player2.hand + "\n");

			///*System.*/out.write("Deck Length = " + GameDeck.length() + "\n");

			///*System.*/out.write("Turn #: " + turn + "\n");
			///*System.*/out.write("\n");

			turn = turn(turn % NumPlayers, turn);
			turn = turn + 1;

			/*System.*/out.write("\n");
		}
		while((Player1.hand.size() > 0) || (Player2.hand.size() > 0)){
			endGame(turn % NumPlayers);
			turn = turn + 1;
		}

			///*System.*/out.write("\n");
			///*System.*/out.write(("Game Deck Should Be Empty\n"));
			///*System.*/out.write("Deck Length = " + GameDeck.length() + "\n");
			///*System.*/out.write(Player1.getName() + " Cards: " + Player1.hand + "\n");
			///*System.*/out.write(Player2.getName() + " Cards: " + Player2.hand + "\n");

		out.write("\n");
		if(Player1.book.size()>Player2.book.size()) {
			/*System.*/out.write(Player1.getName() + " wins with " + Player1.book.size()/2 + " booked pairs." + "\n");
			/*System.*/out.write("" + Player1.book + "\n");
			/*System.*/out.write(Player2.getName() + " has " + Player2.book.size()/2 + " booked pairs." + "\n");
			/*System.*/out.write("" + Player2.book);
		} else{
			/*System.*/out.write(Player2.getName() + " wins with " + Player2.book.size()/2 + " booked pairs." + "\n");
			/*System.*/out.write("" + Player2.book + "\n");
			/*System.*/out.write(Player1.getName() + " has " + Player1.book.size()/2 + " booked pairs." + "\n");
			/*System.*/out.write("" + Player1.book);
		}
		out.close();
	}

	// Players 1 & 2 check their hands for
	// pairs based on their original dealt cards
	// Both players book their pairs before the
	// game begins
	public void BeginCheckForBook(){
		Player1.checkHandForBook(out);
		Player2.checkHandForBook(out);
	}

	// After all the cards are dealt, we are in the end game stages
	// Either each player has a pair in hand or other player has the paired card
	// As such, even if a player "guesses" the right rank to ask for, his/her turn
	// will end.

	public void endGame(int turn){
		int PlayerTurn = turn % NumPlayers;
		switch(PlayerTurn){
			case 0:
				int RankAsked = Player1.chooseCardFromHand();
				/*System.*/out.write(Player1.getName() + " asks - Do you have a " + RankAsked + "?" + "\n");
				if(Player2.rankInHand(RankAsked)) {
					/*System.*/out.write(Player2.getName() + " says - Yes. I have a " + RankAsked + "." + "\n");
					Card toAdd = Player2.giveCardFromHand(RankAsked);
					Player1.hand.add(toAdd);
					Player1.checkHandForBook(out);
					return;
				}
			case 1:

				int RankAsked2 = Player2.chooseCardFromHand();
				/*System.*/out.write(Player2.getName() + " asks - Do you have a " + RankAsked2 + "?" + "\n");
				if(Player1.rankInHand(RankAsked2)) {
					/*System.*/out.write(Player1.getName() + " says - Yes. I have a " + RankAsked2 + "." + "\n");
					Card toAdd = Player1.giveCardFromHand(RankAsked2);
					Player2.hand.add(toAdd);
					Player2.checkHandForBook(out);
					return;
				}
		}

	}

	public int turn(int playerTurn, int repeatTurn) {
		switch (playerTurn) {
			case 0: // Player 1's turn
				// Ask for Card with Rank
				if (checkPlayerHandSize(Player1)){
					return repeatTurn;
				}

				int RankAsked = Player1.chooseCardFromHand();
				/*System.*/out.write(Player1.getName() + " asks - Do you have a " + RankAsked + "?" + "\n");
				if(Player2.rankInHand(RankAsked)){
					/*System.*/out.write(Player2.getName() + " says - Yes. I have a " + RankAsked + "." + "\n");
					Card toAdd = Player2.giveCardFromHand(RankAsked);
					Player1.hand.add(toAdd);
					Player1.checkHandForBook(out);
					return (repeatTurn + 1);

				}else{
					/*System.*/out.write(Player2.getName() + " says - Go Fish" + "\n");
					Card c = GameDeck.dealCard();
					/*System.*/out.write(Player1.getName() + " draws " + c + "\n");
					Player1.addCardToHand(c);
					Player1.checkHandForBook(out);
					return repeatTurn;
				}

			case 1:
				// Ask for Card with Rank
				if (checkPlayerHandSize(Player2)){
					return repeatTurn;
				}

				int RankAsked2 = Player2.chooseCardFromHand();
				/*System.*/out.write(Player2.getName() + " asks - Do you have a " + RankAsked2 + "?" + "\n");
				if(Player1.rankInHand(RankAsked2)){
					/*System.*/out.write(Player1.getName() + " says - Yes. I have a " + RankAsked2 + "." + "\n");
					Card toAdd = Player1.giveCardFromHand(RankAsked2);
					Player2.hand.add(toAdd);
					Player2.checkHandForBook(out);
					return (repeatTurn + 1);

				}else{
					/*System.*/out.write(Player1.getName() + " says - Go Fish" + "\n");
					Card c = GameDeck.dealCard();
					Player2.addCardToHand(c);
					/*System.*/out.write(Player2.getName() + " draws " + c + "\n");
					Player2.checkHandForBook(out);
					return repeatTurn;
				}

		}
		/*System.*/out.write("!!!!!!!!!! something messed up !!!!!!!!!!!!!!!" + "\n");
		return repeatTurn;
	}

	private boolean checkPlayerHandSize(Player player){
		if(player.hand.size()==0){
			Card c = GameDeck.dealCard();
			player.addCardToHand(c);
			return true;
		}
		else{
			return false;
		}
	}
}
