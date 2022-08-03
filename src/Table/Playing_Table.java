package Table;
import java.util.*;
import Players.Playerlist;
import Playing_Card.*;
import Players.Player;


public class Playing_Table {
    private boolean gameEndStatus = false;
    private boolean secondRoundStatus = false;
    private boolean thirdRoundStatus = false;
    private boolean fourthRoundStatus = false;
    private int totalPot = 0;
    private int playerCount = 0;
    private int countOfCard = 0;
    Deck deck = new Deck();
    Playerlist playerlist = new Playerlist();
    ArrayList<Player> pList = playerlist.getPlayerlist();
    Scanner console = new Scanner(System.in);


    public void preFlop()
    {
        Scanner console = new Scanner(System.in);
        Playerlist playerlist = new Playerlist();

        int initPos = -1;
        int raisePos = -1;

        boolean firstTime = true;
        boolean raiseStatus = false;

        System.out.println("Number of players? Min 2, Max 10");
        int numOfPlayers = console.nextInt();


        ArrayList<Card> dCard = deck.getDeckOfCard();
        deck.initDeck();
        deck.shuffleDeck();

        System.out.println("Big Blind amount? (SmallBlind will be half the amount of Big Blind)");
        int currentCallAmount = console.nextInt();
        totalPot += currentCallAmount + (currentCallAmount/2);

        for(int i = 1; i <= numOfPlayers; i++)
        {
            Card first = dCard.get(countOfCard);
            countOfCard++;
            Card second = dCard.get(countOfCard);
            countOfCard++;
            System.out.println("Budget for the game, Player " + i);
            int money = console.nextInt();
            Player player = new Player(money, first, second, 0, i);
            pList.add(player);
            //System.out.println("Card for player " + (playerlist.getPlayerlist().indexOf(player) + 1) + " are " + player.getFirstCard().getCount() + " " + player.getFirstCard().getSuit() + " " + player.getSecondCard().getCount() + " " + player.getSecondCard().getSuit());
        }

        pList.get(playerCount).setSmallBlind();
        pList.get(playerCount).setFirstRoundBet(currentCallAmount/2);
        pList.get(playerCount).minusPersonalBudget(currentCallAmount/2);
        playerCount++;
        pList.get(playerCount).setReturnMark();
        pList.get(playerCount).setBigBlind();
        pList.get(playerCount).setFirstRoundBet(currentCallAmount);
        pList.get(playerCount).minusPersonalBudget(currentCallAmount);
        playerCount++;
        if(numOfPlayers == 2)
        {
            playerCount = 0;
        }
        else {
            pList.get(playerCount).setDealer();
        }




        while(gameEndStatus == false)
        {
            System.out.println("Player " + pList.get(playerCount).getPlayerNumber() + ", It's your turn, 1 for Call; 2 for Raise; 3 for Fold; 4 to check current status.");
            int result = console.nextInt();
            if(result == 1) {
                System.out.println("Total pot is now: " + totalPot);
                if(pList.get(playerCount).getPersonalBudget() <= currentCallAmount)
                {
                    pList.get(playerCount).setAllInMark();
                    totalPot += pList.get(playerCount).getPersonalBudget();
                    pList.get(playerCount).minusPersonalBudget(pList.get(playerCount).getPersonalBudget());
                    pList.remove(playerCount);
                    numOfPlayers--;
                    if(playerCount == numOfPlayers) {
                        playerCount = 0;
                    }
                    continue;
                }

                int rest = currentCallAmount - pList.get(playerCount).getFirstRoundBet();
                pList.get(playerCount).setFirstRoundBet(rest);
                pList.get(playerCount).minusPersonalBudget(rest);
                totalPot += rest;


                if (pList.get(playerCount).getReturnMark() == true && raisePos == -1) {
                        gameEndStatus = true;
                        continue;
                }
                playerCount++;

                if (playerCount == pList.size()) {
                    playerCount = 0;
                }

                if (playerCount == raisePos && raisePos != -1) {
                    gameEndStatus = true;
                    continue;
                }
                System.out.println("Total pot is now: " + totalPot);
            }
            else if(result == 2)
            {
                System.out.println("New amount?");
                int newAmount = console.nextInt();
                if(newAmount <= currentCallAmount)
                {
                    System.out.println("Must larger than previous bet");
                    continue;
                }
                if(newAmount > pList.get(playerCount).getPersonalBudget())
                {
                    System.out.println("Invalid amount, Exceed your maximum budget, Enter it again");
                    continue;
                }
                /*if(newAmount == pList.get(playerCount).getPersonalBudget())
                {
                    int rest = newAmount - pList.get(playerCount).getBetMoney();
                    rest += newAmount;
                    totalPot += rest;
                    pList.get(playerCount).minusPersonalBudget(rest);
                    pList.remove(playerCount);
                    numOfPlayers--;
                    if(playerCount == numOfPlayers) {
                        playerCount = 0;
                    }
                    continue;
                }*/
                int rest = newAmount - pList.get(playerCount).getBetMoney();

                pList.get(playerCount).setFirstRoundBet(rest);
                pList.get(playerCount).minusPersonalBudget(rest);

                totalPot += rest;
                System.out.println("Total pot is now: " + totalPot);
                currentCallAmount = newAmount;

                pList.get(playerCount).setRaiseMark();
                raisePos = playerCount;
                playerCount++;
                if(playerCount == numOfPlayers)
                {
                    playerCount = 0;
                }
                System.out.println("Total pot is now: " + totalPot);
            }
            else if(result == 3){
                pList.get(playerCount).minusPersonalBudget(pList.get(playerCount).getFirstRoundBet());
                pList.remove(playerCount);
                numOfPlayers--;
                if(playerCount == numOfPlayers) {
                    playerCount = 0;
                }
            }
            else
            {
                checkPlayerStatus(pList.get(playerCount));
            }

        }
    }

    public void Flop()
    {
        int dealerPos = -1;
        int secRaisePos = -1;
        int raiseAmount = 0;

        System.out.println("Public pool:");
        for(int i = countOfCard + 1; i < countOfCard + 4; i++)
        {
            System.out.println(deck.getDeckOfCard().get(i).getCount() + " " + deck.getDeckOfCard().get(i).getSuit());
        }
        countOfCard += 3;
        System.out.println("New around starts, Players");
        for(int i = 0; i < pList.size(); i++)
        {
            if(pList.get(i).getDealer() == true)
            {
                dealerPos = i;
            }
        }
        int secreturnPos = dealerPos + 1;

        if(secreturnPos == pList.size())
        {
            secreturnPos = 0;
        }
        playerCount = secreturnPos;

        while(secondRoundStatus == false)
        {
            System.out.println("Player " + pList.get(playerCount).getPlayerNumber() + ", It's your turn, 1 for Checked; 2 for Raise; 3 for Fold; 4 to check current status.");
            int result = console.nextInt();
            if(result == 1)
            {

                if(secRaisePos != -1)
                {
                    int gap = raiseAmount - pList.get(playerCount).getSecondRoundBet();
                    pList.get(playerCount).setSecondRoundBet(gap);
                    pList.get(playerCount).minusPersonalBudget(gap);
                    totalPot += gap;
                    if(playerCount == secRaisePos - 1)
                    {
                        secondRoundStatus = true;
                    }
                }
                playerCount++;
                if(playerCount == pList.size())
                {
                    playerCount = 0;
                }
                if(secRaisePos == -1)
                {
                    if(playerCount == secreturnPos)
                    {
                        secondRoundStatus = true;
                        continue;
                    }
                }
                System.out.println("Total pot is now: " + totalPot);
            }
            else if(result == 2)
            {
                System.out.println("New Amount?");
                raiseAmount = console.nextInt();
                if(raiseAmount > pList.get(playerCount).getPersonalBudget())
                {
                    System.out.println("Invalid amount, Exceed your maximum budget, Enter it again");
                    continue;
                }
                int gap = raiseAmount - pList.get(playerCount).getSecondRoundBet();
                pList.get(playerCount).setSecondRoundBet(gap);
                pList.get(playerCount).minusPersonalBudget(gap);
                totalPot += gap;
                secRaisePos = playerCount;
                playerCount++;
                if(playerCount == pList.size())
                {
                    playerCount = 0;
                }
                System.out.println("Total pot is now: " + totalPot);
            }
            else if(result == 3)
            {
                pList.get(playerCount).minusPersonalBudget(pList.get(playerCount).getSecondRoundBet());
                pList.remove(playerCount);
                if(playerCount == pList.size()) {
                    playerCount = 0;
                }
            }
            else
            {
                checkPlayerStatus(pList.get(playerCount));
            }

        }

    }

    public void Turn()
    {
        int dealerPos = -1;
        int thirdRaisePos = -1;
        int raiseAmount = 0;
        thirdRoundStatus = false;
        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).setRoundBet(0);
        }
        countOfCard = countOfCard + 1;
        System.out.println("Card for turn round: " + deck.getDeckOfCard().get(countOfCard).getCount() + " " + deck.getDeckOfCard().get(countOfCard).getSuit());
        System.out.println("New around starts, Players");
        for(int i = 0; i < pList.size(); i++)
        {
            if(pList.get(i).getDealer() == true)
            {
                dealerPos = i;
            }
        }
        int thirdReturnPos = dealerPos + 1;

        if(thirdReturnPos == pList.size())
        {
            thirdReturnPos = 0;
        }
        playerCount = thirdReturnPos;

        while(thirdRoundStatus == false)
        {
            System.out.println("Player " + pList.get(playerCount).getPlayerNumber() + ", It's your turn, 1 for Checked; 2 for Raise; 3 for Fold; 4 to check current status.");
            int result = console.nextInt();
            if(result == 1)
            {
                if(thirdRaisePos != -1)
                {
                    int gap = raiseAmount - pList.get(playerCount).getThirdRoundBet();
                    pList.get(playerCount).setThirdRoundBet(gap);
                    pList.get(playerCount).minusPersonalBudget(gap);
                    totalPot += gap;
                    if(playerCount == thirdRaisePos - 1)
                    {
                        thirdRoundStatus = true;
                    }
                }
                playerCount++;
                if(playerCount == pList.size())
                {
                    playerCount = 0;
                }
                if(thirdRaisePos == -1)
                {
                    if(playerCount == thirdReturnPos)
                    {
                        thirdRoundStatus = true;
                        continue;
                    }
                }
                System.out.println("Total pot is now: " + totalPot);
            }
            else if(result == 2)
            {
                System.out.println("New Amount?");
                raiseAmount = console.nextInt();
                if(raiseAmount > pList.get(playerCount).getPersonalBudget())
                {
                    System.out.println("Invalid amount, Exceed your maximum budget, Enter it again");
                    continue;
                }
                int gap = raiseAmount - pList.get(playerCount).getThirdRoundBet();
                pList.get(playerCount).setThirdRoundBet(gap);
                pList.get(playerCount).minusPersonalBudget(gap);
                totalPot += gap;
                thirdRaisePos = playerCount;
                playerCount++;
                if(playerCount == pList.size())
                {
                    playerCount = 0;
                }
                System.out.println("Total pot is now: " + totalPot);
            }
            else if(result == 3)
            {
                pList.get(playerCount).minusPersonalBudget(pList.get(playerCount).getThirdRoundBet());
                pList.remove(playerCount);
                if(playerCount == pList.size()) {
                    playerCount = 0;
                }
            }
            else
            {
                checkPlayerStatus(pList.get(playerCount));
            }

        }

    }

    public void River()
    {
        int dealerPos = -1;
        int fourthRaisePos = -1;
        int raiseAmount = 0;
        for(int i = 0; i < pList.size(); i++)
        {
            pList.get(i).setRoundBet(0);
        }
        countOfCard = countOfCard + 1;
        System.out.println("Card for turn round: " + deck.getDeckOfCard().get(countOfCard).getCount() + " " + deck.getDeckOfCard().get(countOfCard).getSuit());
        System.out.println("New around starts, Players");
        for(int i = 0; i < pList.size(); i++)
        {
            if(pList.get(i).getDealer() == true)
            {
                dealerPos = i;
            }
        }
        int fourthReturnPos = dealerPos + 1;

        if(fourthReturnPos == pList.size())
        {
            fourthReturnPos = 0;
        }
        playerCount = fourthReturnPos;

        while(fourthRoundStatus == false)
        {
            System.out.println("Player " + pList.get(playerCount).getPlayerNumber() + ", It's your turn, 1 for Checked; 2 for Raise; 3 for Fold; 4 to check current status.");
            int result = console.nextInt();
            if(result == 1)
            {
                if(fourthRaisePos != -1)
                {
                    int gap = raiseAmount - pList.get(playerCount).getThirdRoundBet();
                    pList.get(playerCount).setThirdRoundBet(gap);
                    pList.get(playerCount).minusPersonalBudget(gap);
                    totalPot += gap;
                    if(playerCount == fourthRaisePos - 1)
                    {
                        fourthRoundStatus = true;
                    }
                }
                playerCount++;
                if(playerCount == pList.size())
                {
                    playerCount = 0;
                }
                if(fourthRaisePos == -1)
                {
                    if(playerCount == fourthReturnPos)
                    {
                        fourthRoundStatus = true;
                        continue;
                    }
                }
                System.out.println("Total pot is now: " + totalPot);
            }
            else if(result == 2)
            {
                System.out.println("New Amount?");
                raiseAmount = console.nextInt();
                if(raiseAmount > pList.get(playerCount).getPersonalBudget())
                {
                    System.out.println("Invalid amount, Exceed your maximum budget, Enter it again");
                    continue;
                }
                int gap = raiseAmount - pList.get(playerCount).getThirdRoundBet();
                pList.get(playerCount).setThirdRoundBet(gap);
                pList.get(playerCount).minusPersonalBudget(gap);
                totalPot += gap;
                fourthRaisePos = playerCount;
                playerCount++;
                if(playerCount == pList.size())
                {
                    playerCount = 0;
                }
                System.out.println("Total pot is now: " + totalPot);
            }
            else if(result == 3)
            {
                pList.get(playerCount).minusPersonalBudget(pList.get(playerCount).getThirdRoundBet());
                pList.remove(playerCount);
                if(playerCount == pList.size()) {
                    playerCount = 0;
                }
            }
            else
            {
                checkPlayerStatus(pList.get(playerCount));
            }

        }
    }

    public void checkTotalPot()
    {
        System.out.println("Total pot is now: " + totalPot);
    }

    public void calculateResult()
    {

    }


    public void checkPlayerStatus(Player p)
    {
        System.out.println("Below are player's information");
        System.out.println("Card for player are " + p.getFirstCard().getCount() + " " + p.getFirstCard().getSuit() + " " + p.getSecondCard().getCount() + " " + p.getSecondCard().getSuit());

        System.out.println("Left budget: " + p.getPersonalBudget());

    }

}
