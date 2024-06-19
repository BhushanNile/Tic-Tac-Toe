package WinningStrategy;

import Models.Bord;
import Models.Move;

public interface WinningStrategy {
    boolean CheckWinner(Bord bord, Move move);
    void handleUndo(Move move,Bord bord);
}
