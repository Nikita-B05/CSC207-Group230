package use_case.game_over;

public class GameOverInputData {
    private final boolean isSuccess;  // True if the game was won, false if it was lost
    private final String username;   // to print out username with game over message

    public GameOverInputData(boolean isSuccess, String username) {
        this.isSuccess = isSuccess;
        this.username = username;}

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getUsername() {
        return username;
    }
}

