package use_case.homepage;

public class HomepageInputData {
    private final String username;

    public HomepageInputData(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }
}
