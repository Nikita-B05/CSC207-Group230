package interface_adapter.input_name;

/**
 * The state for the Input Name ViewModel.
 */
public class InputNameState {
    private String username;
    private String characterName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}