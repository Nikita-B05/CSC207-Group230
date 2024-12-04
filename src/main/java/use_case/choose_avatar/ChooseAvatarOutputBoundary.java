package use_case.choose_avatar;

import use_case.choose_avatar.ChooseAvatarOutputData;

/**
 * Output Boundary for the Choose Avatar Use Case.
 */
public interface ChooseAvatarOutputBoundary {
    /**
     * Presents the result of the avatar selection.
     *
     * @param outputData the output data containing updated user info.
     */
    void presentAvatarSelection(ChooseAvatarOutputData outputData);
}
