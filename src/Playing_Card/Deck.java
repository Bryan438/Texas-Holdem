package Playing_Card;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private Card cards;
    private ArrayList<Card> deckOfCard;
    public Deck()
    {
        deckOfCard = new ArrayList<Card>();
    }
    public ArrayList<Card> getDeckOfCard()
    {
        return deckOfCard;
    }
    public void initDeck()
    {
        for(int i = 1; i < 14; i++)
        {
            for(int j = 1; j < 5; j++)
            {
                Card c;
                if(j == 1) {
                    c = new Card(i, "dimond");
                }
                else if(j == 2)
                {
                    c = new Card(i, "club");
                }
                else if(j == 3)
                {
                    c = new Card(i, "heart");
                }
                else {
                    c = new Card(i, "spade");
                }
                deckOfCard.add(c);
            }
        }
    }
    public void shuffleDeck()
    {
        Collections.shuffle(deckOfCard);
    }

}
