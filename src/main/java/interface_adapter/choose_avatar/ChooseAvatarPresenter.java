package interface_adapter.choose_avatar;

import interface_adapter.ViewManagerModel;
import interface_adapter.input_name.InputNameViewModel;
import use_case.choose_avatar.ChooseAvatarOutputBoundary;
import use_case.choose_avatar.ChooseAvatarOutputData;
import use_case.settings.SettingsOutputBoundary;

/**
 * Presenter for the Choose Avatar Use Case.
 */
public class ChooseAvatarPresenter implements ChooseAvatarOutputBoundary {

    private final ChooseAvatarViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final InputNameViewModel inputNameViewModel;

    public ChooseAvatarPresenter(ChooseAvatarViewModel viewModel, ViewManagerModel viewManagerModel, InputNameViewModel inputNameViewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.inputNameViewModel = inputNameViewModel;
    }

    @Override
    public void presentAvatarSelection(ChooseAvatarOutputData outputData) {
        inputNameViewModel.getState().setUsername(outputData.getUsername());
        inputNameViewModel.getState().setCharacterName(outputData.getCharacterName());
        inputNameViewModel.getState().setAvatar(outputData.getAvatar());
        inputNameViewModel.getState().setDarkMode(outputData.isDarkMode());
        inputNameViewModel.firePropertyChanged();
        viewModel.firePropertyChanged();
        viewManagerModel.setState(inputNameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}