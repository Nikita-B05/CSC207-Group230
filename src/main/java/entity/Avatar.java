package entity;

import javax.swing.*;

/**
 * Represents the player's avatar in the game.
 */
public class Avatar {
    private String id;
    private String imagePath;

    public Avatar() {
        this.id = "default";
        this.imagePath = "/images/rickroll.png";
    }

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

    public void setName(String selectedAvatarName) {
        this.id = selectedAvatarName;
    }

    public void setImagePath(String s) {
        this.imagePath = s;
    }

    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource(imagePath));
    }
}

