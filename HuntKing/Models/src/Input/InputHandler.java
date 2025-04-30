package Input;
import Player.Position;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String inputName() {
        System.out.print("Please enter your name: ");
        return scanner.nextLine();
    }

    public int[] inputPosition() {
        System.out.print("Enter the King Position (3 5): ");
        String[] parts = scanner.nextLine().trim().split("\\s+");

        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        return new int[] { x, y };
    }


    public Position[] inputAllKingPositions() {
        Position[] kingPositions = new Position[2];
        for (int i = 0; i < 2; i++) {
            System.out.println("Enter position for King " + (i + 1) + ":");
            int[] pos = inputPosition();
            kingPositions[i] = new Position(pos[0], pos[1]);
        }
        return kingPositions;
    }



}
