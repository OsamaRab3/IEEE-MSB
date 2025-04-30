package MoveHandler;
import Board.Board;
import Game.GameState;
import King.King;
import Player.Player;
import Player.Position;



public class MoveHandler implements IMoveHandler {

    @Override
    public boolean isMoveValid(Position position) {
        int[] pos = position.getPosition();
        return pos[0] >= 0 && pos[0] < 8 && pos[1] >= 0 && pos[1] < 8;
    }

    @Override
    public GameState applyMove(Position newPosition, Player player, King opponentKing, Board board) {
        if (!isMoveValid(newPosition)) {
            System.out.println("Invalid Move: Out of bounds.");
            return GameState.IN_PROGRESS;
        }

        Position opponentKingPos = opponentKing.getKingPosition();
        if (opponentKingPos.getPosition()[0] == newPosition.getPosition()[0] &&
                opponentKingPos.getPosition()[1] == newPosition.getPosition()[1]) {
            System.out.println("You found the opponent's King! You Win!!!");
            return GameState.FINISHED;
        } else {
            board.setPosition(newPosition, "X"); // or any symbol
            return GameState.IN_PROGRESS;
        }
    }

}

