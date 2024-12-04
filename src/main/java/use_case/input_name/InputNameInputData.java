package use_case.input_name;

/**
 * Input Data for the Input Name Use Case.
 */
public class InputNameInputData {
    private final String username;
    private final String characterName;

    public InputNameInputData(String username, String characterName) {
        this.username = username;
        this.characterName = characterName;
    }

    public String getUsername() {
        return username;
    }

    public String getCharacterName() {
        return characterName;
    }
}
