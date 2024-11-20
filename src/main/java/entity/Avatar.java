package entity;

/**
 * Represents the player's avatar in the game.
 */
public class Avatar {
    private final String id;
    private final String characterName;
    private final String imagePath;

    public Avatar(String id, String characterName, String imagePath) {
        this.id = id;
        this.characterName = characterName;
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean equals(Avatar o) {
        return id.equals(o.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}