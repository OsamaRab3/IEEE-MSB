package Board;

import Player.Position;

public interface IBoard {
    void displayBoard();
    void initBoard();
    String [][] getBoard();
    public void setPosition(Position pos, String symbol);
}



