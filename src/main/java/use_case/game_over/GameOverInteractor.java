package use_case.game_over;

import data_access.MongoDBUserDataAccessObject;
import entity.User;
import java.util.HashMap;

public class GameOverInteractor implements GameOverInputBoundary {
    private final GameOverOutputBoundary presenter;
    private final MongoDBUserDataAccessObject userDataAccessObject;

    public GameOverInteractor(GameOverOutputBoundary presenter, MongoDBUserDataAccessObject userDataAccessObject) {
        this.presenter = presenter;
        this.userDataAccessObject = userDataAccessObject; 
    }

    @Override
    public void execute(GameOverInputData inputData) {
        try {
            User user = userDataAccessObject.get(inputData.getUsername());

            if (user == null) {
                presenter.prepareFailView("Player not found");
                return;
            }

            HashMap<String, Double> emptyStockPrices = new HashMap<>();
            double wealth = user.getNetWorth(emptyStockPrices);
            double happiness = user.getHappiness();
            String username = user.getUsername();

            String message;
            boolean isWealthDepleted = wealth <= 0;
            boolean isHappinessDepleted = happiness <= 0;

            if (isWealthDepleted) {
                message = "You're dead broke!";
            } else if (isHappinessDepleted) {
                message = "You have crippling depression!";
            } else {
                message = "Something went wrong!";
            }

            GameOverOutputData outputData = new GameOverOutputData(
                message, username, isWealthDepleted, isHappinessDepleted
            );

            presenter.prepareSuccessView(outputData);
        } catch (RuntimeException e) {
            presenter.prepareFailView("Error accessing player data");
        }
    }

    public void cleanupPlayerData(String username) {
        try {
            User user = userDataAccessObject.get(username);
            
            // Reset relevant fields to initial values
            user.setSalary(0); // Assuming salary represents wealth
            user.setHappiness(0);
            // Reset other fields as necessary

            // Save the updated user data
            userDataAccessObject.save(user);
        } catch (RuntimeException e) {
            presenter.prepareFailView("Failed to reset player data");
        }
    }
}

