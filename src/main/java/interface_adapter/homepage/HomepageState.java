package interface_adapter.homepage;

/**
 * The state for the Signup View Model.
 */
public class HomepageState {
    private final String username;

    public HomepageState(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
