package use_case.choose_avatar;

import entity.Avatar;

/**
 * Output Data for the Choose Avatar Use Case.
 */
public class ChooseAvatarOutputData {
    private final String username;
    private final Avatar avatar;

    public ChooseAvatarOutputData(String username, Avatar avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public Avatar getAvatar() {
        return avatar;
    }
}