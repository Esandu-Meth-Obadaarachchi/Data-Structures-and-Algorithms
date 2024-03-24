import java.util.*;

public class Main {

    static ArrayList<Player> players = new ArrayList<>(13);
    static ArrayList<String> deck = new ArrayList<>(52); // the deck will always be max 52

    //=======================================================================
    public static void printingPlayerHands(){    //calls iterator to go through each player's hand and printing it
        for(int i = 0;i<players.size();i++){
            System.out.print("player "+(i + 1) + "'s hand: ");  //to display the player number
            Player player = players.get(i);
            System.out.println(player.iterateToPrint());
            System.out.println();
        }
    }

    public static int comparingCards(String card1,String card2){  //subtracting  the 2 card's assigned values and returning it.
        String[] cardParts1 = card1.split("-");
        String[] cardParts2 = card2.split("-");

        String rankOfCard1 = cardParts1[0];
        String rankOfCard2 = cardParts2[0];
        String suitOfCard1 = cardParts1[1];
        String suitOfCard2 = cardParts2[1];

        if(suitOfCard1.equals(suitOfCard2)){
            //since suits are equal now we compare the ranks
            int rankValue1 = getRankValue(rankOfCard1);
            int rankValue2 = getRankValue(rankOfCard2);
            return rankValue1-rankValue2;     //we subtract the 2 values and to check whether the card is greater or not and return it
        }
        else {
            // comparing suits
            int suitValue1 = getSuitValue(suitOfCard1);
            int suitValue2 = getSuitValue(suitOfCard2);
            return suitValue1 - suitValue2;    //we subtract the 2 values and to check whether the card is greater or not and return it
        }
    }

    public static int getRankValue(String rank){ //giving a value to each rank so we can sort
        return switch (rank) {
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6" -> 6;
            case "7" -> 7;
            case "8" -> 8;
            case "9" -> 9;
            case "10" -> 10;
            case "jack" -> 11;
            case "queen" -> 12;
            case "king" -> 13;
            case "ace" -> 14;
            default -> 0;
        };
    }

    public static int getSuitValue(String suit) { //giving a value to each suit to sort accordingly
        return switch (suit) {
            case "spades" -> 4;
            case "hearts" -> 3;
            case "diamonds" -> 2;
            case "clubs" -> 1;
            default -> 0;
        };
    }
    //=====================================================================================================
    public static void main(String[] args) {
        //adding all the cards to the deck first
        Random randomNum = new Random();
        for (String suit : new String[]{"spades", "hearts", "diamonds", "clubs"}) {
            for (String rank : new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"}) {
                deck.add(rank + "-" + suit);
            }
        }

        //-------------------------------Getting user input for no.of player---------------------------------------------------------
        Scanner inputs = new Scanner(System.in);
        int numOfPlayers;
        int indexOfCard;
        int dealNumber=1;

        while(true) {
            System.out.print("Enter the number of players(2,4 or 13): ");
            try {
                numOfPlayers = inputs.nextInt();
                if(numOfPlayers==2 || numOfPlayers==4 || numOfPlayers==13){
                    break;
                }
                else {
                    System.out.println("Please enter a proper no. of players");
                }
            } catch (InputMismatchException e) {
                System.out.println("pls enter properly again");
                inputs.nextLine();  //to clear the input scanner, so it moves the input stream to the next line
            }
        }

        for (int i = 0; i < numOfPlayers; i++) {
            players.add(new Player());
        }

        //-------------------------------------------------------------------------------------------------------------
        while (!deck.isEmpty()) {     //distributing the cards until the deck is empty
            for (Player player : players) {
                indexOfCard = randomNum.nextInt(deck.size());

                String cardFromDeck = deck.remove(indexOfCard);
                String rank = cardFromDeck.split("-")[0];
                String suit = cardFromDeck.split("-")[1];
                player.addACard(rank, suit);
            }
            System.out.println("Deal no. "+dealNumber);
            printingPlayerHands();
            System.out.println("------------------");
            dealNumber++;
        }

        //sorting the hand (insertion sort) and then printing 
        for (Player player : players) {
            ArrayList<String> hand = player.getHand();
            for (int i = 1; i < hand.size(); i++) {
                String key = hand.get(i);
                int j = i - 1;
                while (j >= 0 && comparingCards(hand.get(j), key) > 0) {
                    hand.set(j + 1, hand.get(j));
                    j--;
                }
                hand.set(j + 1, key);
            }
            player.setHand(hand);
        }

        System.out.println("~AFTER FINAL DEAL, SORTED HANDS~");
        printingPlayerHands();

        //removing a card of suit of the users choice
        String suitToPlay = "";
        int removingTurn = 1;
        int playerNumber = 1;
        System.out.println();

        while (!suitToPlay.equalsIgnoreCase("esc") && removingTurn<52 ) {   //this loop will continue until user enters esc or removed all 52 cards
            System.out.print("Enter the suit of a card you wish to play! (IF YOU WISH TO EXIT, ENTER 'esc'): ");
            suitToPlay = inputs.next();
            if (suitToPlay.equalsIgnoreCase("spades") || suitToPlay.equalsIgnoreCase("clubs") || suitToPlay.equalsIgnoreCase("hearts") || suitToPlay.equalsIgnoreCase("diamonds")) {
                for (Player player : players) {
                    System.out.print("From player "+playerNumber);
                    player.playACard(suitToPlay);
                    playerNumber++;
                    removingTurn++;
                }

                System.out.println();
                System.out.println("~The balance cards in each hand~");
                printingPlayerHands(); // printing the hand after removing the card
            }
            else if (suitToPlay.equalsIgnoreCase("esc")) {
                System.out.println("program ended, thanks for playing ");
            } else {
                System.out.println("INVALID OPTION ENTERED");
            }
        }

    }
}
