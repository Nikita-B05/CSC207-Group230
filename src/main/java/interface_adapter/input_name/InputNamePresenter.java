package interface_adapter.input_name;

import interface_adapter.ViewManagerModel;
import use_case.input_name.InputNameOutputBoundary;
import use_case.input_name.InputNameOutputData;

/**
 * Presenter for the Input Name Use Case.
 */
public class InputNamePresenter implements InputNameOutputBoundary {

    private final InputNameViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public InputNamePresenter(InputNameViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentCharacterNameInput(InputNameOutputData outputData) {
        viewModel.getState().setUsername(outputData.getUsername());
        viewModel.getState().setCharacterName(outputData.getCharacterName());
        viewModel.firePropertyChanged();

        // Navigate back to Homepage
        viewManagerModel.setState("homepage");
        viewManagerModel.firePropertyChanged();
    }
}