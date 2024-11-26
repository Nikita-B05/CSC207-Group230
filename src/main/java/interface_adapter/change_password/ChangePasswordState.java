package interface_adapter.change_password;

public class ChangePasswordState {
    private String username = "";
    private String password = "";
    private String passwordError;


    public ChangePasswordState(ChangePasswordState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    public ChangePasswordState() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }
}
