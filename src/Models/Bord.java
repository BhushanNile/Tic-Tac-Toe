package Models;

import java.util.ArrayList;
import java.util.List;

public class Bord {
    private int size;
    private List<List<Cell>> bord;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBord() {
        return bord;
    }

    public void setBord(List<List<Cell>> bord) {
        this.bord = bord;
    }

    public Bord(int size){
        this.size = size;
        this.bord = new ArrayList<>();
        for(int i=0; i<size; ++i)
        {
            bord.add(new ArrayList<>());
            for(int j=0; j<size; ++j)
            {
                bord.get(i).add( new Cell(i,j));
            }
        }
    }

    public void print(){
        for(List<Cell> row : bord){
            System.out.print("|");
            for(Cell cell : row) {
                if (cell.getCellType().equals(CellType.EMPTY)) {
                    System.out.print(" - |");
                } else {
                    System.out.print(" " + cell.getPlayer().getSymbol().getaChar() + " |");
                }
            }
                  System.out.println();
        }

    }
}
