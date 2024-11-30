package use_case.game_decision;

import entity.Assets;
import entity.Decision;
import entity.User;

public interface GameDecisionUserDataAccessInterface {
    void setCurrentUsername(String username);
    void updateUserDarkMode(boolean darkMode);
    User getCurrentUser();
    void updateDecision(Decision decision);
    void updateAssets(Assets assets);
    void updateHappiness(User user);
}
