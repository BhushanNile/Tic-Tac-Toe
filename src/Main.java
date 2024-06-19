import Controllers.GameController;
import Models.*;
import WinningStrategy.OrderOneColumnWinningStrategy;
import WinningStrategy.OrderOneDiagonalWinningStrategy;
import WinningStrategy.OrderOneRowWinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // CREATE A GAME

        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        List<Player> players =  List.of(
                new Player(new Symbol('X'), "BHUSHAN", PlayerType.HUMAN),
                new Bot(new Symbol('O'),"BOT",BotDifficultyLevel.EASY)
        );
        int dimension = 3;
        Game game = gameController.creategame(dimension,players,
                List.of(
                        new OrderOneRowWinningStrategy( dimension,players),
                        new OrderOneColumnWinningStrategy(dimension,players),
                        new OrderOneDiagonalWinningStrategy(dimension,players)
                )
        );
        System.out.println("---------------GAME IS STARTING---------------");

        // KEPT GAME RUNNING TILL GAME STATUS IS IN_PROGRESS
        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("This is how board look like :");
            // PRINT BORD
            gameController.displaybord(game);
            // ASK UNDO
            System.out.println("Do you want to UNDO? (Y/N)");
            String input = scanner.next();
            // IF -> YES -> CALL UNDO METHOD
            if(input.equalsIgnoreCase("y"))
            {
                gameController.undo(game);
            }
            else {
                // MOVE OF NEXT PLAYER
                gameController.makeomve(game);
            }
        }
        // CHECK STAUS OF GAME
        // IF WINNER -> PRINT WINNER
        gameController.printResult(game);
        gameController.displaybord(game);



    }
}
