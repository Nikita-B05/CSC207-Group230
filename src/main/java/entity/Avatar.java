package entity;

/**
 * Represents the player's avatar in the game.
 */
public class Avatar {
    private final String id;
    private final String imagePath;

    public Avatar(String id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
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

