package use_case.input_name;

/**
 * Output Data for the Input Name Use Case.
 */
public class InputNameOutputData {
    private final String username;
    private final String characterName;

    public InputNameOutputData(String username, String characterName) {
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