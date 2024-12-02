package use_case.game_end;

public class GameEndOutputData {
    private final String message;

    public GameEndOutputData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

