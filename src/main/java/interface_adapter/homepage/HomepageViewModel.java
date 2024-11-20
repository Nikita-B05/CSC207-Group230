package interface_adapter.homepage;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Homepage View.
 */
public class HomepageViewModel extends ViewModel<HomepageState> {

    public static final String TITLE_LABEL = "Homepage";
    public static final String CHOOSE_AVATAR_LABEL = "Choose Character";
    public static final String PLAY_GAME_LABEL = "Play Game";
    public static final String DECISION_LOG_LABEL = "Decision Log";
    public static final String PROFILE_SETTINGS_LABEL = "Profile Settings";

    public HomepageViewModel() {
        super("homepage");
        setState(new HomepageState());
    }
}
