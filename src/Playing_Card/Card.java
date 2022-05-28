package Playing_Card;

public class Card {
    private int count;
    private String suit;

    public Card(int count, String suit)
    {
        this.count = count;
        this.suit = suit;
    }
    public String getSuit()
    {
        return suit;
    }
    public int getCount()
    {
        return count;
    }
    public void setCount(int num)
    {
        count = num;
    }
    public void setSuit(String s)
    {
        suit = s;
    }
}

