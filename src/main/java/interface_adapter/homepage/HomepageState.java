package interface_adapter.homepage;

import entity.Avatar;

/**
 * The state for the Signup View Model.
 */
public class HomepageState {

    private String username = null;
    private Avatar avatar = new Avatar("default", "rickroll", "/images/rickroll.png");
    private String name = null;

    public String getUsername() {
        return username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }
}
