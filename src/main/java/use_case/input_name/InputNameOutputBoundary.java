package use_case.input_name;

import use_case.input_name.InputNameOutputData;

/**
 * Output Boundary for the Input Name Use Case.
 */
public interface InputNameOutputBoundary {
    /**
     * Presents the result of the character name input.
     *
     * @param outputData the output data containing updated user info.
     */
    void presentCharacterNameInput(InputNameOutputData outputData);
}