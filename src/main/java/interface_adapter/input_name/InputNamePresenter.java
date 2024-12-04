package interface_adapter.input_name;

import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomepageViewModel;
import use_case.input_name.InputNameOutputBoundary;
import use_case.input_name.InputNameOutputData;

/**
 * Presenter for the Input Name Use Case.
 */
public class InputNamePresenter implements InputNameOutputBoundary {

    private final InputNameViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;

    public InputNamePresenter(
            InputNameViewModel viewModel, ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
    }

    @Override
    public void presentCharacterNameInput(InputNameOutputData outputData) {
        homepageViewModel.getState().setUsername(outputData.getUsername());
        homepageViewModel.getState().setName(outputData.getCharacterName());
        homepageViewModel.getState().setAvatar(outputData.getAvatar());
        homepageViewModel.firePropertyChanged();

        // Navigate back to Homepage
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
