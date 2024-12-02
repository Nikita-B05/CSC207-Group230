package use_case.game_over;

import use_case.settings.SettingsOutputData;

public interface GameOverOutputBoundary {
    void prepareSuccessView(GameOverOutputData outputData);
    void prepareFailView(String error);

    void switchToHomepageView(SettingsOutputData outputData);
}

