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
        this.imagePath = "/images/avatar1.png";
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Avatar avatar = (Avatar) obj;
        return id.equals(avatar.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode(); // Use id's hashCode
    }

    public void setName(String selectedAvatarName) {
        this.id = selectedAvatarName;
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource(imagePath));
    }
}
