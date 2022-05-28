package Players;

import Playing_Card.Card;

public class Player {
    private Card firstCard;
    private Card secondCard;
    private boolean bigBlind = false;
    private boolean smallBlind = false;
    private int personalBudget;
    private int betMoney;

    public Player(int personalBudget, Card firstCard, Card secondCard, int betMoney)
    {
        this.personalBudget = personalBudget;
        this.firstCard = firstCard;
        this.secondCard = secondCard;
        this.betMoney = betMoney;
    }
    public Card getFirstCard()
    {
        return firstCard;
    }
    public Card getSecondCard()
    {
        return secondCard;
    }
    public void setBigBlind()
    {
        bigBlind = true;
    }
    public boolean getBigBlind()
    {
        return bigBlind;
    }
    public void setSmallBlind()
    {
        smallBlind = true;
    }
    public boolean getSmallBlind()
    {
        return smallBlind;
    }
    public void setOffBigBlind()
    {
        bigBlind = false;
    }
    public void setOffSmallBlind()
    {
        smallBlind = false;
    }
    public void setBetMoney(int money)
    {
        betMoney += money;
    }
    public int getBetMoney()
    {
        return betMoney;
    }
    public void addPersonalBudget(int budget)
    {
        personalBudget += budget;
    }
    public void minusPersonalBudget(int budget)
    {
        personalBudget -= budget;
    }
    public int getPersonalBudget()
    {
        return personalBudget;
    }


}
