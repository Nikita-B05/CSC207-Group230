package use_case.decision_log;

import data_access.MongoDBUserDataAccessObject;
import entity.Decision;
import entity.User;
import use_case.homepage.HomepageOutputData;

import java.util.List;

/**
 * The Decision Log Interactor.
 */
public class DecisionLogInteractor implements DecisionLogInputBoundary {
    private final DecisionLogUserDataAccessInterface userDataAccessObject;
    private final DecisionLogOutputBoundary decisionLogPresenter;

    public DecisionLogInteractor(DecisionLogUserDataAccessInterface decisionDataAccessInterface,
                                 DecisionLogOutputBoundary decisionLogOutputBoundary,
                                 MongoDBUserDataAccessObject userRepository) {
        this.userDataAccessObject = decisionDataAccessInterface;
        this.decisionLogPresenter = decisionLogOutputBoundary;
    }

    @Override
    public void execute(DecisionLogInputData inputData) {
        String username = inputData.getUsername();

        try {
            // Retrieve the full User object from the database
            User user = userDataAccessObject.get(username);

            // Extract the list of decisions from the User object
            List<Decision> decisions = user.getDecisions();

            if (decisions == null || decisions.isEmpty()) {
                // If no decisions are found, trigger the failure view
                decisionLogPresenter.prepareFailView("No decisions found for " + username);
            } else {
                // If decisions are found, prepare the success view
                DecisionLogOutputData outputData = new DecisionLogOutputData(decisions, username);
                decisionLogPresenter.prepareSuccessView(outputData);
            }
        } catch (Exception e) {
            // Handle any unexpected exceptions and prepare the fail view
            decisionLogPresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public void switchToHomepageView() {
        // Implement navigation logic to homepage
        decisionLogPresenter.switchToHomepageView();
    }

    @Override
    public void switchToHomepageView(DecisionLogInputData DecisionLogInputData) {
        final String username = DecisionLogInputData.getUsername();
        final User user = userDataAccessObject.get(username);
        decisionLogPresenter.switchToHomepageView();
    }

    @Override
    public void switchToDecisionLogView(DecisionLogInputData inputData) {
        // Implement navigation logic to decision log view
        decisionLogPresenter.switchToDecisionLogView(
                new DecisionLogOutputData(inputData.getDecisions(), inputData.getUsername()));
    }

    @Override
    public List<Decision> getDecisions(String username) {
        // Fetch the list of decisions for the given user
        try {
            User user = userDataAccessObject.get(username);
            return user != null ? user.getDecisions() : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public double getTotalNetWorthChange(String username) {
        // Calculate and return the total net worth change
        try {
            User user = userDataAccessObject.get(username);
            if (user != null) {
                return user.getDecisions().stream().mapToDouble(Decision::getNetWorthChange).sum();
            }
        } catch (Exception e) {
            // Handle exceptions gracefully
        }
        return 0;
    }

    @Override
    public double getTotalHappinessChange(String username) {
        // Calculate and return the total happiness change
        try {
            User user = userDataAccessObject.get(username);
            if (user != null) {
                return user.getDecisions().stream().mapToDouble(Decision::getHappinessChange).sum();
            }
        } catch (Exception e) {
            // Handle exceptions gracefully
        }
        return 0;
    }
}
