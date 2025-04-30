package  MoveHandler;
import Board.Board;
import Game.GameState;
import King.King;
import Player.Player;
import Player.Position;


public  interface IMoveHandler {
    public boolean isMoveValid(Position position);

   public GameState applyMove(Position newPosition, Player player, King opponentKing, Board board);
}
