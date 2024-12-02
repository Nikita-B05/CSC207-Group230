package use_case.game_success;
import entity.Assets;
import entity.User;

public class GameSuccessInteractor implements GameSuccessInputBoundary {

    private GameSuccessOutputBoundary gameSuccessPresenter;
    private GameSuccessUserDataAccessInterface userDataAccess;

    public GameSuccessInteractor(GameSuccessOutputBoundary gameSuccessOutputBoundary,
                                 GameSuccessUserDataAccessInterface gameSuccessUserDataAccessInterface) {
        this.gameSuccessPresenter = gameSuccessOutputBoundary;
        this.userDataAccess = gameSuccessUserDataAccessInterface;
    }

    @Override
    public void switchToHomepageView() {
        User user = userDataAccess.getCurrentUser();
        user.setAge(22);
        user.setHappiness(100);
        user.setSalary(0);
        user.setAssets(new Assets());
        userDataAccess.updateHappiness(user);
        userDataAccess.updateAge(user);
        userDataAccess.updateSalary(user);
        userDataAccess.updateAssets(user);
        GameSuccessOutputData outputData = new GameSuccessOutputData(
                user.getUsername(),
                user.getCharacterName(),
                user.isDarkMode(),
                user.getAvatar()
        );
        gameSuccessPresenter.prepareHomepageView(outputData);
    }
}
