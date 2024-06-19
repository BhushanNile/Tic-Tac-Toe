package Controllers;

import Models.Game;
import Models.GameStatus;
import Models.Player;
import WinningStrategy.WinningStrategy;

import java.util.List;

public class GameController {

    // CREATE A GAME
    public Game creategame(int dimension, List<Player> players,List<WinningStrategy> winningStrategies){
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningstrategies(winningStrategies)
                .build();
    }

    public void displaybord(Game game){
        game.printBord();
    }
    public void undo(Game game){
        game.undo();
    }
    public void makeomve(Game game){
        game.makeMove();

    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus() ;
    }
    public void printResult(Game game){
        game.printResult();

    }


}
