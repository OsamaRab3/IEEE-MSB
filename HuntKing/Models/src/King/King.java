package  King;
import  Player.Position;
public  class King implements IKing {

    private  Position kingPosition ;

    public King(Position position){
        this.kingPosition = position;

    }

    @Override
    public Position getKingPosition() {
        return this.kingPosition;
    }
    @Override
    public  void setKingPosition(Position position){
        this.kingPosition = position;
    }


}
