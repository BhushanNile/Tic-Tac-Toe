package BotPlayingStrategies;

import Models.Bord;
import Models.Cell;
import Models.CellType;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Cell MakeMove(Bord bord) {
        for(List<Cell> row: bord.getBord())
        {
            for(Cell cell : row) {
                if (cell.getCellType().equals(CellType.EMPTY)) {
                    return cell;
                }
            }
        }
        return null;// will never come here
    }

}

