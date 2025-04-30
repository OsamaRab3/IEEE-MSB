package Board;
import Player.Position;
public class Board implements  IBoard {
    private String[][] grid;
    private final int rows;
    private final int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        initBoard();
    }


    @Override
    public void initBoard() {
        grid = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = ".";
            }
        }
    }
    @Override
    public void displayBoard() {
        for (String[] row : grid) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    @Override
    public  String [][] getBoard(){
        return this.grid;
    }

    @Override
    public void setPosition(Position pos, String symbol) {
        grid[pos.getPosition()[0]][pos.getPosition()[1]] = symbol;
    }



}
