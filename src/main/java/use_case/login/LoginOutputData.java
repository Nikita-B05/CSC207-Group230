package use_case.login;

import entity.Avatar;
import entity.Decision;

import java.util.ArrayList;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final Avatar avatar;
    private final String name;
    private final boolean isDarkMode;
    private final ArrayList<Decision> decisions;
    private final boolean useCaseFailed;

    public LoginOutputData(
            String username,
            Avatar avatar,
            String name,
            boolean isDarkMode,
            ArrayList<Decision> decisions,
            boolean useCaseFailed
    ) {
        this.username = username;
        this.avatar = avatar;
        this.name = name;
        this.isDarkMode = isDarkMode;
        this.decisions = decisions;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public ArrayList<Decision> getDecisions() {
        return decisions;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
