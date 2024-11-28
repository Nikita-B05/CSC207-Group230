package use_case.input_name;

import use_case.input_name.InputNameInputData;

/**
 * Input Boundary for the Input Name Use Case.
 */
public interface InputNameInputBoundary {
    /**
     * Executes the character name input use case.
     *
     * @param inputData the input data containing username and character name.
     */
    void inputCharacterName(InputNameInputData inputData);
}