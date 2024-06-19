package WinningStrategy;

import Models.Bord;
import Models.Move;
import Models.Player;
import Models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OrderOneDiagonalWinningStrategy implements WinningStrategy{
    private Map<Symbol,Integer> leftDiagMap;
    private Map<Symbol,Integer> rightDiagMap;

    public OrderOneDiagonalWinningStrategy(int size, List<Player> players){
        leftDiagMap = new HashMap<>();
        rightDiagMap = new HashMap<>();
        for(Player player: players){
          leftDiagMap.put(player.getSymbol(),0);
          rightDiagMap.put(player.getSymbol(),0);
        }

    }
    @Override
    public boolean CheckWinner(Bord bord, Move move) {
       int row = move.getCell().getRow();
       int col = move.getCell().getCol();
       Symbol symbol = move.getPlayer().getSymbol();

       if(row == col)
       {
           leftDiagMap.put(symbol,leftDiagMap.get(symbol)+1);
       }
       if(row + col == bord.getSize()-1){
           rightDiagMap.put(symbol,rightDiagMap.get(symbol)+1);
       }
       if(row == col){
           if( leftDiagMap.get(symbol) == bord.getSize()){
               return true;
           }
       }
       if(row+col == bord.getSize()-1)
       {
           if(rightDiagMap.get(symbol) == bord.getSize()){
               return true;
           }
       }
        return false;
    }
    @Override
    public void handleUndo(Move move,Bord bord) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(row == col){
            leftDiagMap.put(symbol,leftDiagMap.get(symbol)-1);
        }
        if(row+col == bord.getSize()){
            rightDiagMap.put(symbol,rightDiagMap.get(symbol)-1);
        }


    }
}
