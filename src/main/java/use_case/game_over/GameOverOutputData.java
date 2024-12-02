package use_case.game_over;

public class GameOverOutputData {
    private final String message;
    private final String username;
    private final boolean isWealthDepleted;
    private final boolean isHappinessDepleted; 

    public GameOverOutputData(String message, String username, boolean isWealthDepleted, 
                            boolean isHappinessDepleted) {
        this.message = message;
        this.username = username;
        this.isWealthDepleted = isWealthDepleted;
        this.isHappinessDepleted = isHappinessDepleted;
    }

    // Getters
    public String getMessage() { return message; }
    public String getUsername() { return username; }
    public boolean isWealthDepleted() { return isWealthDepleted; }
    public boolean isHappinessDepleted() { return isHappinessDepleted; }
}

