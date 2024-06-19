package WinningStrategy;

import Models.Bord;
import Models.Move;
import Models.Player;
import Models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneRowWinningStrategy implements WinningStrategy{
      private List<Map<Symbol,Integer>> rowMaps;

      public OrderOneRowWinningStrategy(int size, List<Player> players){
          rowMaps = new ArrayList<>();
          for(int i=0; i<size; ++i){
              rowMaps.add(new HashMap<>());
              for(Player player : players){
                  rowMaps.get(i).put(player.getSymbol(),0);
              }
          }
      }
    @Override
    public boolean CheckWinner(Bord bord, Move move) {
          int row = move.getCell().getRow();
          Symbol symbol = move.getPlayer().getSymbol();

          rowMaps.get(row).put(symbol,1 + rowMaps.get(row).get(symbol));
          if(rowMaps.get(row).get(symbol) == bord.getSize()){
              return true;
          }

        return false;
    }

    @Override
    public void handleUndo(Move move,Bord bord) {
          int row = move.getCell().getRow();
          Symbol symbol = move.getPlayer().getSymbol();
        rowMaps.get(row).put(symbol,rowMaps.get(row).get(symbol)-1);






    }
}
