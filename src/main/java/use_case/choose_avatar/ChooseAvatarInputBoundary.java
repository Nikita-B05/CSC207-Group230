package use_case.choose_avatar;

import use_case.choose_avatar.ChooseAvatarInputData;

/**
 * Input Boundary for the Choose Avatar Use Case.
 */
public interface ChooseAvatarInputBoundary {
    /**
     * Executes the avatar selection use case.
     *
     * @param inputData the input data containing username and selected avatar.
     */
    void selectAvatar(ChooseAvatarInputData inputData);
}