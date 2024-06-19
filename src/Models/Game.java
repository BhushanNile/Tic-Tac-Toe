package Models;

import WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Bord bord;
    private List<Player> players;
    private List<WinningStrategy> winningstrategies;
    private List<Move> moves;
    private GameStatus gameStatus;
    private Player winner;
    private int CurrentPlayerIndex;

    private Game( int dimension,List<Player> players,List<WinningStrategy> winningstrategies){
        this.bord = new Bord(dimension);
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.CurrentPlayerIndex = 0;
        this.winningstrategies = winningstrategies;
        this.players = players;

    }
    public static Builder getBuilder(){
        return new Builder();

    }

    public Bord getBord() {
        return bord;
    }

    public void setBord(Bord bord) {
        this.bord = bord;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void
    setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setWinningstrategies(List<WinningStrategy> winningstrategies) {
        this.winningstrategies = winningstrategies;
    }

    public List<WinningStrategy> getWinningstrategies() {
        return winningstrategies;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getCurrentPlayerIndex() {
        return CurrentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        CurrentPlayerIndex = currentPlayerIndex;
    }

    public void printBord(){
        this.bord.print();
    }

    public void printResult(){
        if(gameStatus.equals(GameStatus.ENDED))
        {
            System.out.println("Game has ENDED. ");
            System.out.println("Winer is : " + winner.getName());
        }else {
            System.out.println(" Game is DRAW ");
        }
    }
    private  boolean validateMove(Cell cell){
          int row = cell.getRow();
          int col = cell.getCol();
          if(row < 0|| col < 0|| row >= bord.getSize() || col >= bord.getSize()){
              return false;
          }
          if(bord.getBord().get(row).get(col).getCellType().equals(CellType.EMPTY)){
              return true;
          }

          return false;
    }
    public void makeMove(){
        Player currentPlayer = players.get(CurrentPlayerIndex);
        System.out.println("It is " + currentPlayer.getName()+ "'s turn. ");
        Cell proposedCell = currentPlayer.makeMove(bord);
        System.out.println("Move made at row: "+proposedCell.getRow()+"col: "+ proposedCell.getCol()+".");
        if(!validateMove(proposedCell))
        {
            System.out.println("Invalid Move. Please try again.");
            return;
        }
        Cell cellinBord = bord.getBord().get(proposedCell.getRow()).get(proposedCell.getCol());
        cellinBord.setCellType(CellType.FILLED);
        cellinBord.setPlayer(currentPlayer);
        Move move =  new Move(currentPlayer,cellinBord);
        moves.add(move);

        for(WinningStrategy winningStrategy :winningstrategies){
          if(winningStrategy.CheckWinner(bord,move)){
              winner = currentPlayer;
              gameStatus = GameStatus.ENDED;

              return;
          }
        }
        if(moves.size() == bord.getSize() * bord.getSize())
        {
           gameStatus = GameStatus.DRAW;
           return;
        }
        CurrentPlayerIndex +=1;
        CurrentPlayerIndex %= players.size();

    }
    public void undo(){
        if(moves.size() == 0)
        {
            System.out.println("No Move till now. Can't UNDO");
            return;
        }
        Move lastmoves = moves.get(moves.size()-1);
        for(WinningStrategy winningStrategy : winningstrategies)
        {
            winningStrategy.handleUndo(lastmoves, bord);
        }
        Cell cellinBord = lastmoves.getCell();
        cellinBord.setCellType(CellType.EMPTY);
        cellinBord.setPlayer(null);

        moves.remove(lastmoves);
        CurrentPlayerIndex -=1;
        CurrentPlayerIndex += players.size();
        CurrentPlayerIndex %= players.size();

    }
    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningstrategies;

       private Builder(){

       }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningstrategies(List<WinningStrategy> winningstrategies) {
            this.winningstrategies = winningstrategies;
            return this;
        }

        private boolean valid(){
           if(this.players.size() < 2){
               return false;
           }
           if(this.players.size() != this.dimension-1)
           {
               return false;
           }
           int botcount = 0;
           for(Player player : this.players){
               if(player.getPlayerType().equals(PlayerType.BOT))
               {
                   botcount+=1;
               }
           }
           if(botcount >2)
           {
               return false;
           }
           Set<Character> existingSymbol = new HashSet<>();
           for(Player player : players){
               if(existingSymbol.contains(player.getSymbol().getaChar()))
               {
                   return false;
               }
               existingSymbol.add(player.getSymbol().getaChar());
           }
            return true;
        }
        public Game build() {
            if(!valid())
            {
                throw new RuntimeException("Invalid parameters please check once");
            }
           return new Game(dimension,players,winningstrategies);
        }
    }


}
