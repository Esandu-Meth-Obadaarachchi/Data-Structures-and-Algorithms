import java.util.ArrayList;
import java.util.Iterator;

public class Player{
    private ArrayList<String> hand;

    public Player() { //constructor
        this.hand = new ArrayList<String>();
    }
    public void addACard(String rank,String suit){ //adding the card to the player
        this.hand.add(rank+"-"+suit);
    }

    public String playACard(String suit){ //method to play a card where the suit has to be passed
        Iterator<String> suitIterator = new HandIterator();
        while (suitIterator.hasNext()) {
            String card = suitIterator.next();
            if (card.endsWith(suit)){
                System.out.println(" the card removed is " + card);
                suitIterator.remove();
                return card;
            }
        }
        String arbitraryCard = hand.remove(0);
        System.out.println(" the card removed is " + arbitraryCard);
        return arbitraryCard;
    }
    //setter and getter
    public void setHand(ArrayList<String> hand){
        this.hand = hand;
    }
    public ArrayList<String> getHand() {
        return hand;
    }
    //----------------------------------------------------------------

    public String iterateToPrint() {            //this is used to iterate through the hand and make all the cards to one string
        StringBuilder sb = new StringBuilder();
        for (String card : hand) {
            sb.append(card);
            sb.append(",");
        }
        if (sb.length() > 0) {                       //only does this if hand is not empty
            sb.deleteCharAt(sb.length() - 1); // removes the last comma
        }
        return sb.toString();
    }
    //===============================================================================
    private class HandIterator implements Iterator<String> { //iterator implementation
        private int currentIndex;

        public HandIterator() {
            this.currentIndex = 0;
        }

        public boolean hasNext() {
            return currentIndex < hand.size();
        }

        public String next() {
            return hand.get(currentIndex++);
        }

        public void remove() {
            hand.remove(currentIndex - 1);
            currentIndex--;
        }
    }
}