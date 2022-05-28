package Table;
import java.util.*;
import Players.Playerlist;
import Playing_Card.*;
import Players.Player;


public class Playing_Table {
    private boolean gameEndStatus = false;
    private int currentCallAmount;
    private int totalPot = 0;
    public void preFlop()
    {
        Scanner console = new Scanner(System.in);
        Playerlist playerlist = new Playerlist();
        ArrayList<Player> pList = playerlist.getPlayerlist();

        int playerCount = 0;
        int countOfCard = 0;
        int initPos = -1;
        int raisePos = -1;

        boolean firstTime = true;
        boolean raiseStatus = false;

        System.out.println("Number of players? Min 2, Max 10");
        int numOfPlayers = console.nextInt();

        Deck deck = new Deck();
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
            Player player = new Player(money, first, second, 0);
            playerlist.getPlayerlist().add(player);
            //System.out.println("Card for player " + (playerlist.getPlayerlist().indexOf(player) + 1) + " are " + player.getFirstCard().getCount() + " " + player.getFirstCard().getSuit() + " " + player.getSecondCard().getCount() + " " + player.getSecondCard().getSuit());
        }

        pList.get(playerCount).setSmallBlind();
        pList.get(playerCount).setBetMoney(currentCallAmount/2);
        pList.get(playerCount).minusPersonalBudget(currentCallAmount/2);
        playerCount++;
        initPos = playerCount;
        pList.get(playerCount).setBigBlind();
        pList.get(playerCount).setBetMoney(currentCallAmount);
        pList.get(playerCount).minusPersonalBudget(currentCallAmount);
        playerCount++;



        while(gameEndStatus == false)
        {
            System.out.println("Player " + (playerCount + 1) + ", It's your turn, 1 for Call; 2 for Raise; 3 for Fold.");
            int result = console.nextInt();
            if(result == 1) {
                System.out.println("Total pot is now: " + totalPot);
                if (pList.get(playerCount).getSmallBlind() == true && firstTime == true) {
                    int rest = currentCallAmount - pList.get(playerCount).getBetMoney();
                    pList.get(playerCount).setBetMoney(rest);
                    pList.get(playerCount).minusPersonalBudget(rest);
                    totalPot += rest;
                    firstTime = false;
                } else {
                    pList.get(playerCount).setBetMoney(currentCallAmount);
                    pList.get(playerCount).minusPersonalBudget(currentCallAmount);
                    totalPot += currentCallAmount;
                }

                if (playerCount == initPos && raisePos == -1) {
                        gameEndStatus = true;
                        continue;
                }
                playerCount++;

                if (playerCount == numOfPlayers) {
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
                System.out.println("New amount? (Must larger than previous bet)");
                int newAmount = console.nextInt();
                int rest = newAmount - pList.get(playerCount).getBetMoney();

                pList.get(playerCount).setBetMoney(rest);
                pList.get(playerCount).minusPersonalBudget(rest);

                totalPot += rest;
                System.out.println("Total pot is now: " + totalPot);
                currentCallAmount = newAmount;

                raisePos = playerCount;
                playerCount++;
                if(playerCount == numOfPlayers)
                {
                    playerCount = 0;
                }
            }
            else{
                pList.get(playerCount).minusPersonalBudget(pList.get(playerCount).getBetMoney());
                pList.remove(playerCount);
                if(playerCount == numOfPlayers)
                {
                    playerCount = 0;
                }

            }

        }

        System.out.println("Public pool:");
        for(int i = countOfCard + 1; i < countOfCard + 4; i++)
        {
            System.out.println(deck.getDeckOfCard().get(i).getCount() + " " + deck.getDeckOfCard().get(i).getSuit());

        }
    }

    public void checkPlayerStatus(Player p)
    {
        System.out.println("Below are player's information");
        System.out.println("Card for player are " + p.getFirstCard().getCount() + " " + p.getFirstCard().getSuit() + " " + p.getSecondCard().getCount() + " " + p.getSecondCard().getSuit());
        System.out.println("Bet money for the round: " + p.getBetMoney());
        System.out.println("Left budget: " + p.getPersonalBudget());

    }

}
