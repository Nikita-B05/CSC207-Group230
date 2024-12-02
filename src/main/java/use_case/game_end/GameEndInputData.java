package use_case.game_end;

public class GameEndInputData {
    private final boolean isSuccess;  // True if the game was won, false if it was lost
    private final String username;   // Optionally, for personalizing messages or logic
    private final int finalScore;    // The player's final score or performance metric

    public GameEndInputData(boolean isSuccess, String username, int finalScore) {
        this.isSuccess = isSuccess;
        this.username = username;
        this.finalScore = finalScore;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getUsername() {
        return username;
    }

    public int getFinalScore() {
        return finalScore;
    }
}

