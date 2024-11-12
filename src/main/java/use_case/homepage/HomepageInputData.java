package use_case.homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class HomepageInputData {
    private final String username;
    private final String name;
    private final Avatar avatar;

    public HomepageInputData(String username, String name, Avatar avatar) {
        this.username = username;
        this.name = name;
        this.avatar = avatar;
    }


    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public Avatar getAvatar() {
        return avatar;
    }
}
