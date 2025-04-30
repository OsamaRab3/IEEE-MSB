import Game.Game;
import Game.GameState;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome in Hunt King Game!");
        Game game = new Game(GameState.NOT_STARTED);
        game.startGame();

    }
}