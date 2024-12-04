package interface_adapter.choose_avatar;

import entity.Avatar;
import use_case.choose_avatar.ChooseAvatarInputBoundary;
import use_case.choose_avatar.ChooseAvatarInputData;

/**
 * Controller for the Choose Avatar Use Case.
 */
public class ChooseAvatarController {
    private final ChooseAvatarInputBoundary chooseAvatarUseCaseInteractor;

    public ChooseAvatarController(ChooseAvatarInputBoundary chooseAvatarUseCaseInteractor) {
        this.chooseAvatarUseCaseInteractor = chooseAvatarUseCaseInteractor;
    }

    /**
     * Executes the avatar selection use case.
     *
     * @param username the username of the user.
     * @param avatar   the selected avatar.
     */
    public void selectAvatar(String username, Avatar avatar) {
        ChooseAvatarInputData inputData = new ChooseAvatarInputData(username, avatar);
        chooseAvatarUseCaseInteractor.selectAvatar(inputData);
    }
}
