package use_case.input_name;

import entity.User;

/**
 * Interactor for the Input Name Use Case.
 */
public class InputNameInteractor implements InputNameInputBoundary {

    private final InputNameUserDataAccessInterface userDataAccessObject;
    private final InputNameOutputBoundary outputBoundary;

    public InputNameInteractor(InputNameUserDataAccessInterface userDataAccessObject,
                               InputNameOutputBoundary outputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void inputCharacterName(InputNameInputData inputData) {
        String username = inputData.getUsername();
        String characterName = inputData.getCharacterName();

        User user = userDataAccessObject.get(username);
        user.setCharacterName(characterName);
        userDataAccessObject.save(user);

        InputNameOutputData outputData = new InputNameOutputData(username, characterName);
        outputBoundary.presentCharacterNameInput(outputData);
    }
}