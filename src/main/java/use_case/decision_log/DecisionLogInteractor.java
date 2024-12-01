package use_case.decision_log;

import data_access.MongoDBUserDataAccessObject;
import entity.Decision;
import entity.User;

import java.util.List;

/**
 * The Decision Log Interactor.
 */
public class DecisionLogInteractor implements DecisionLogInputBoundary {
    private final DecisionLogUserDataAccessInterface decisionDataAccessObject;
    private final DecisionLogOutputBoundary decisionLogPresenter;
    private final MongoDBUserDataAccessObject decisionRepository;


    public DecisionLogInteractor(DecisionLogUserDataAccessInterface decisionDataAccessInterface,
                                 DecisionLogOutputBoundary decisionLogOutputBoundary, MongoDBUserDataAccessObject userRepository) {
        this.decisionDataAccessObject = decisionDataAccessInterface;
        this.decisionLogPresenter = decisionLogOutputBoundary;
        this.decisionRepository = userRepository;
    }

    @Override
    public void execute(DecisionLogInputData inputData) {
        String username = inputData.getUsername();

        try {
            // Retrieve the full User object from the database
            User user = decisionRepository.get(username);

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
        decisionLogPresenter.switchToHomePageView();
    }

    @Override
    public void switchToDecisionLogView(DecisionLogInputData inputData) {

    }
}
