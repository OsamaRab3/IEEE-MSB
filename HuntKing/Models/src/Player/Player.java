package Player;
import  Board.Board;

public class Player  implements IPlayer{
    protected String name;
    protected Board board;
    protected Position position;
    Player(String name, Position position){
        this.name = name;
        this.position = position;
        this.board = new Board(8,8);
    }

    public Player(String name){
        this.name = name;
    }

    @Override
    public  String getName(){
        return  this.name;
    }


}
