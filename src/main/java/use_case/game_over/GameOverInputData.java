package use_case.game_over;

public class GameOverInputData {
    private final boolean isSuccess;  // True if the game was won, false if it was lost
    private final String username;   // to print out username with game over message
    private final int finalScore;    // The player's final score or performance metric

    public GameOverInputData(boolean isSuccess, String username, int finalScore) {
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

