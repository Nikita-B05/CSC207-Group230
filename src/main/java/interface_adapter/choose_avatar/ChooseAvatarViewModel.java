package interface_adapter.choose_avatar;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Choose Avatar View.
 */
public class ChooseAvatarViewModel extends ViewModel<ChooseAvatarState> {

    public static final String VIEW_NAME = "chooseAvatar";
    public static final String TITLE_LABEL = "Choose Your Avatar";
    public static final String SUBMIT_BUTTON_LABEL = "Next";

    public ChooseAvatarViewModel() {
        super(VIEW_NAME);
        setState(new ChooseAvatarState());
    }
}