package Game;
import Board.Board;
import Input.InputHandler;
import King.King;
import Player.Player;
import Player.Position;
import MoveHandler.MoveHandler;

public class Game {
    private GameState state;
    private final Player[] players;
    private final King[] kings;
    private Board [] boards = new Board[2];
    InputHandler handler ;

    public Game(GameState initialState) {
        this.state = initialState;
        players = new Player[2];
        kings = new King[2];
        boards = new Board[2];
        handler = new InputHandler();

    }

    public void setupGame() {


        for (int i = 0; i < 2; i++) {
            String name = handler.inputName();
            players[i] = new Player(name);
            boards[i] = new Board(8, 8);
        }

        Position[] kingPositions = handler.inputAllKingPositions();
        for (int i = 0; i < 2; i++) {
            kings[i] = new King(kingPositions[i]);
        }

        MoveHandler moveHandler = new MoveHandler();
        int currentPlayer = 0;
        GameState gameState = GameState.IN_PROGRESS;

        do {
            System.out.println(players[currentPlayer].getName() + "'s turn:");
            boards[currentPlayer].displayBoard();

            int[] newPos = handler.inputPosition();
            Position position = new Position(newPos[0], newPos[1]);

            int opponentIndex = (currentPlayer + 1) % 2;
            gameState = moveHandler.applyMove(position, players[currentPlayer], kings[opponentIndex], boards[currentPlayer]);

            boards[currentPlayer].displayBoard();

            if (gameState == GameState.FINISHED) {
                System.out.println(players[currentPlayer].getName() + " wins the game!");
                break;
            }

            currentPlayer = opponentIndex;

        } while (getState() == GameState.IN_PROGRESS);

    }


public void startGame() {
        if (state == GameState.PAUSED || state == GameState.NOT_STARTED) {
            state = GameState.IN_PROGRESS;
            System.out.println("Game started!");
            this.setupGame();

        } else {
            System.out.println("Game cannot be started from state: " + state);
            endGame();
        }
    }

    public GameState getState() {
        return state;
    }

    public void pauseGame() {
        if (state == GameState.IN_PROGRESS) {
            state = GameState.PAUSED;
            System.out.println("Game paused.");
        }
    }

    public void endGame() {
        state = GameState.FINISHED;
        System.out.println("Game finished.");
    }


}

