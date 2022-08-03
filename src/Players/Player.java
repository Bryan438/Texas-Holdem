package Players;

import Playing_Card.Card;

public class Player {
    private Card firstCard;
    private Card secondCard;
    private boolean bigBlind = false;
    private boolean smallBlind = false;
    private int personalBudget;
    private int betMoney;
    private int firstRoundBet = 0;
    private int secondRoundBet = 0;
    private int thirdRoundBet = 0;
    private int fourthRoundBet = 0;
    private boolean returnMark = false;
    private boolean raiseMark = false;
    private int playerNumber;
    private boolean dealer = false;
    private int sidePot = 0;
    private int roundBet;
    private boolean allInMark = false;
    private boolean winMark = false;

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

    public void setFirstRoundBet(int money)
    {
        firstRoundBet += money;
    }
    public void setSecondRoundBet(int money)
    {
        secondRoundBet += money;
    }
    public void setThirdRoundBet(int money)
    {
        thirdRoundBet += money;
    }
    public void setFourthRoundBet(int money)
    {
        fourthRoundBet += money;
    }
    public int getFirstRoundBet()
    {
        return firstRoundBet;
    }
    public int getSecondRoundBet()
    {
        return secondRoundBet;
    }
    public int getThirdRoundBet()
    {
        return thirdRoundBet;
    }
    public int getFourthRoundBet()
    {
        return fourthRoundBet;
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
    public void setAllInMark()
    {
        allInMark = true;
    }

    public void setWinMark()
    {
        winMark = true;
    }
    public boolean getWinMark()
    {
        return winMark;
    }


}
