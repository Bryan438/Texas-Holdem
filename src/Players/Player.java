package Players;

import Playing_Card.Card;

public class Player {
    private Card firstCard;
    private Card secondCard;
    private boolean bigBlind = false;
    private boolean smallBlind = false;
    private int personalBudget;
    private int betMoney;
    private boolean returnMark = false;
    private boolean raiseMark = false;
    private int playerNumber;
    private boolean dealer = false;
    private int roundBet;
    private int allInBet;

    public Player(int personalBudget, Card firstCard, Card secondCard, int betMoney, int playerNumber)
    {
        this.personalBudget = personalBudget;
        this.firstCard = firstCard;
        this.secondCard = secondCard;
        this.betMoney = betMoney;
        this.playerNumber = playerNumber;
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
    public void setReturnMark ()
    {
        returnMark = true;
    }
    public void setOffReturnMark()
    {
        returnMark = false;
    };
    public boolean getReturnMark()
    {
        return returnMark;
    }
    public void setRaiseMark ()

    {
        raiseMark = true;
    }
    public void setOffRaiseMark()
    {
        raiseMark = false;
    };
    public boolean getRaiseMark()
    {
        return raiseMark;
    }
    public int getPlayerNumber()
    {
        return playerNumber;
    }
    public boolean getDealer()
    {
        return dealer;
    }
    public void setDealer()
    {
        dealer = true;
    }
    public int getRoundBet()
    {
        return roundBet;
    }
    public void setRoundBet(int rb)
    {
        roundBet = rb;
    }
    public int getAllInBet()
    {
        return allInBet;
    }
    public void setAllInBet(int ab)
    {
        allInBet = ab;
    }
}
