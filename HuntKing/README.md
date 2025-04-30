# HuntKing Game

A two-player board game where players take turns to guess the position of the opponent's King on an 8x8 grid. The first player to locate the opponent's King wins!

---

## How to Play
1. **Setup**:
   - Enter names for Player 1 and Player 2.
   - Place each player's King on the board by specifying coordinates (e.g., `3 5` for row 3, column 5).

2. **Gameplay**:
   - Players alternate turns.
   - On your turn, enter coordinates to guess the opponent's King location.
   - Valid moves are within the board bounds (0-7 for rows/columns).
   - If you guess correctly, you win! Otherwise, the board updates with an "X" at the guessed position.

3. **Winning**:
   - The game ends when a player locates the opponent's King.

---


### **SOLID Principles in the Game Design**

This game follows the **SOLID** principles, which are a set of five design principles that make software more understandable, flexible, and maintainable.

#### 1. **Single Responsibility Principle (SRP)**

Each class in the game has one responsibility:

- **Game**: Manages the overall flow of the game, handles player turns, and checks win conditions.
- **Player**: Represents the player and stores their name.
- **King**: Represents the king, including its position and movement.
- **Board**: Represents the game board, including displaying it and managing positions.
- **MoveHandler**: Handles the logic of validating and applying moves.
- **InputHandler**: Manages all user inputs (names, positions, etc.).

This separation ensures that each class is focused on a single task, making the code easier to maintain and extend.

#### 2. **Open/Closed Principle (OCP)**

The game is designed to be open for extension but closed for modification. For example:

- You can add new types of players, kings, or boards  without changing the existing code. You can subclass `Player` or `Board` and extend the functionality.
  
- The `Game` class can be extended to add new game modes or win conditions without modifying the core game logic.

#### 3. **Liskov Substitution Principle (LSP)**

This principle ensures that objects of a superclass can be replaced with objects of a subclass without affecting the correctness of the program.


---

### **Potential Extensions**

- **Game Modes**: Add multiplayer support or different difficulty levels.
- **Different Boards**: Implement other board sizes or custom maps.
- **Special Player Abilities**: Add unique moves for specific players or kings.
- **AI Opponent**: Implement an AI to play against the user.

---

### **Folder Structure**

```bash
Models/
 ├── src/
 │    ├── Board/           # Handles board creation and display
 │    ├── Game/            # Core game logic (turns, win conditions, etc.)
 │    ├── Input/           # Handles user input (name, positions, etc.)
 │    ├── King/            # King class (position, movement)
 │    ├── MoveHandler/     # Handles validating and applying moves
 │    ├── Player/          # Player class (stores player name and their king)
 │    └── Main.java        # Main class to run the game
```

---

### **Conclusion**

The **Hunt King Game** utilizes object-oriented principles and follows **SOLID** design principles, making it modular, extensible, and maintainable. The game design allows for easy updates and modifications to introduce new features, making it a robust foundation for future development.

