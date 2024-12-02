package use_case.game_over;

import entity.User;
import entity.Assets;

import java.util.ArrayList;

public class GameOverInteractor implements GameOverInputBoundary {

    private GameOverUserDataAccessInterface userDataAccess;
    private GameOverOutputBoundary outputBoundary;

    public GameOverInteractor(GameOverUserDataAccessInterface userDataAccess,
                              GameOverOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void switchToHomeview(GameOverInputData inputData) {
        GameOverOutputData outputData = new GameOverOutputData(inputData.getUsername(), inputData.getCharacterName(),
                inputData.isDarkMode(), inputData.getAssets(), inputData.getAvatar(), inputData.getAge(),
                inputData.getHappiness());
        outputBoundary.prepareHomepageView(outputData);
        User user = userDataAccess.getCurrentUser();
        user.setAge(22);
        user.setHappiness(100);
        user.setAssets(new Assets());
        user.setSalary(0);
        user.setDecisions(new ArrayList<>());
        userDataAccess.updateAge(user);
        userDataAccess.updateSalary(user);
        userDataAccess.updateAssets(user);
        userDataAccess.updateHappiness(user);
        userDataAccess.updateDecisions(user);
    }
}
