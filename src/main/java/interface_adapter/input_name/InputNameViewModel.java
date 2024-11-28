package interface_adapter.input_name;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Input Name View.
 */
public class InputNameViewModel extends ViewModel<InputNameState> {

    public static final String VIEW_NAME = "inputName";
    public static final String TITLE_LABEL = "Enter Character Name";
    public static final String SUBMIT_BUTTON_LABEL = "Finish";

    public InputNameViewModel() {
        super(VIEW_NAME);
        setState(new InputNameState());
    }
}