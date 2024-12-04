package use_case.input_name;

import entity.Avatar;

/**
 * Output Data for the Input Name Use Case.
 */
public class InputNameOutputData {
    private final String username;
    private final String characterName;
    private final Avatar avatar;

    public InputNameOutputData(String username, String characterName, Avatar avatar) {
        this.username = username;
        this.characterName = characterName;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Avatar getAvatar() {
        return avatar;
    }
}
