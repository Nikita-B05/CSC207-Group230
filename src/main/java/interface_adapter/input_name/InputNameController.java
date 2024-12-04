package interface_adapter.input_name;

import use_case.input_name.InputNameInputBoundary;
import use_case.input_name.InputNameInputData;

/**
 * Controller for the Input Name Use Case.
 */
public class InputNameController {
    private final InputNameInputBoundary inputBoundary;

    public InputNameController(InputNameInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Executes the character name input use case.
     *
     * @param username      the username of the user.
     * @param characterName the input character name.
     */
    public void inputCharacterName(String username, String characterName) {
        InputNameInputData inputData = new InputNameInputData(username, characterName);
        inputBoundary.inputCharacterName(inputData);
    }
}
