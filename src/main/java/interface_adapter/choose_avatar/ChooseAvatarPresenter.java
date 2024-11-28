package interface_adapter.choose_avatar;

import interface_adapter.ViewManagerModel;
import use_case.choose_avatar.ChooseAvatarOutputBoundary;
import use_case.choose_avatar.ChooseAvatarOutputData;

/**
 * Presenter for the Choose Avatar Use Case.
 */
public class ChooseAvatarPresenter implements ChooseAvatarOutputBoundary {

    private final ChooseAvatarViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public ChooseAvatarPresenter(ChooseAvatarViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentAvatarSelection(ChooseAvatarOutputData outputData) {
        viewModel.getState().setUsername(outputData.getUsername());
        viewModel.getState().setAvatar(outputData.getAvatar());
        viewModel.firePropertyChanged();

        // Navigate to Input Name View
        viewManagerModel.setState("inputName");
        viewManagerModel.firePropertyChanged();
    }
}