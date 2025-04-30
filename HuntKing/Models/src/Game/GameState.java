package Game;

public enum GameState {
    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    PAUSED("Paused"),
    FINISHED("Finished");

    private final String label;

    GameState(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
