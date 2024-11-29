package interface_adapter.choose_avatar;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Choose Avatar View.
 */
public class ChooseAvatarViewModel extends ViewModel<ChooseAvatarState> {

    public static final String VIEW_NAME = "chooseAvatar";

    public ChooseAvatarViewModel() {
        super(VIEW_NAME);
        setState(new ChooseAvatarState());
    }
}