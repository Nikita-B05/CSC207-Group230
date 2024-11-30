package use_case.choose_avatar;

import entity.Avatar;

/**
 * Input Data for the Choose Avatar Use Case.
 */
public class ChooseAvatarInputData {
    private final String username;
    private final Avatar avatar;

    public ChooseAvatarInputData(String username, Avatar avatar) {
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