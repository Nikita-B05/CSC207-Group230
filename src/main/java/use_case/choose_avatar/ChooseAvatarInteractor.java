package use_case.choose_avatar;

import entity.Avatar;
import entity.User;

/**
 * Interactor for the Choose Avatar Use Case.
 */
public class ChooseAvatarInteractor implements ChooseAvatarInputBoundary {

    private final ChooseAvatarUserDataAccessInterface userDataAccessObject;
    private final ChooseAvatarOutputBoundary outputBoundary;

    public ChooseAvatarInteractor(ChooseAvatarUserDataAccessInterface userDataAccessObject,
                                  ChooseAvatarOutputBoundary outputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void selectAvatar(ChooseAvatarInputData inputData) {
        String username = inputData.getUsername();
        Avatar avatar = inputData.getAvatar();

        User user = userDataAccessObject.get(username);
        user.setAvatar(avatar);
        userDataAccessObject.save(user);

        ChooseAvatarOutputData outputData = new ChooseAvatarOutputData(username, avatar);
        outputBoundary.presentAvatarSelection(outputData);
    }
}