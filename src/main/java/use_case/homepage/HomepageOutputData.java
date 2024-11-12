package use_case.homepage;

public class HomepageOutputData {
    private final String username;
    // If needed, add stats: network, quality of life, happiness

    public HomepageOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
