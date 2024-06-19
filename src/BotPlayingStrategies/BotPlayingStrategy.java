package BotPlayingStrategies;

import Models.Bord;
import Models.Cell;

public interface BotPlayingStrategy {
   public Cell MakeMove(Bord bord);
}
