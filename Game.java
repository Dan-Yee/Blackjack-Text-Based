import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Driver for the game
 * @author Dan Yee
 */
public class Game {
    public static void main(String[] args) throws InterruptedException {
        Blackjack gameSession = new Blackjack();
        Scanner playerMove = new Scanner(System.in);
        boolean continueGame = true;
        boolean isPlayerStanding = false;
        String playerChoice;
        int playerStatus;
        int gameStatus;

        System.out.println("Welcome to Blackjack!");
        do {
            System.out.println("====================================================================================================");
            gameSession.newGame();
            if((playerStatus = gameSession.checkPlayerStatus()) == 2) {
                System.out.println("[AUTO-STAND] You have a hand value of 21 [AUTO-STAND]");
                TimeUnit.SECONDS.sleep(2);
                isPlayerStanding = true;
                gameSession.standFunction();
            }
            while(isPlayerStanding == false) {
                System.out.print("Hit or Stand? ");
                playerChoice = playerMove.nextLine();

                if(playerChoice.equalsIgnoreCase("hit")) {
                    gameSession.hitFunction(1);

                    if((playerStatus = gameSession.checkPlayerStatus()) == 1) {
                        System.out.println("You Busted!");
                        break;
                    } else if(playerStatus == 2) {
                        System.out.println("[AUTO-STAND] You have a hand value of 21 [AUTO-STAND]");
                        TimeUnit.SECONDS.sleep(2);
                        isPlayerStanding = true;
                        gameSession.standFunction();
                        break;
                    }
                } else if(playerChoice.equalsIgnoreCase("stand")) {
                    isPlayerStanding = true;
                    gameSession.standFunction();
                }
            }
            switch((gameStatus = gameSession.checkGameStatus())) {
                case 1:
                    System.out.println("You beat the dealer!");
                    break;
                case 0:
                    System.out.println("You tied with the dealer!");
                    break;
                case -1:
                    System.out.println("You lost to the dealer! Better luck next time.");
                    break;
            }
            System.out.print("\nWould you like to play again? Please type \"Yes\" or \"No\": ");
            playerChoice = playerMove.nextLine();

            if(playerChoice.equalsIgnoreCase("no"))
                continueGame = false;
            else {
                isPlayerStanding = false;
            }
        } while(continueGame);

        playerMove.close();
        System.out.println("Thank you for playing and have a good day!\n");
        TimeUnit.SECONDS.sleep(2);
    }
}