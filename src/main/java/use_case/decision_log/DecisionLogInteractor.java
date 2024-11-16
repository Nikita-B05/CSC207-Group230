package use_case.decision_log.;

/**
 * The Decision Log Interactor.
 */
public class DecisionLogInteractor implements DecisionLogInputBoundary  {

    private final DecisionLogOutputBoundary DecisionLogPresenter;

    public DecisionLogInteractor(DecisionLogOutputBoundary DecisionLogOutputBoundary) {
        this.DecisionLogPresenter = DecisionLogOutputBoundary;
    }

    @Override
    public void switchToHomepageView() { DecisionLogPresenter.switchToHomepageView(); }
}
