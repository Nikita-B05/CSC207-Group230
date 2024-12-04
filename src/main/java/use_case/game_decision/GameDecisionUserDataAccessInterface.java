package use_case.game_decision;

import entity.User;

public interface GameDecisionUserDataAccessInterface {
    void setCurrentUsername(String username);

    User getCurrentUser();

    void updateDecision(User user);

    void updateAssets(User user);

    void updateHappiness(User user);

    void updateSalary(User user);

    /**
     * Increments the user age.
     */
    void incrementAge();

    /**
     * Add the user's salary to cash.
     */
    void addSalary();

    /**
     * Appreciate the user's home.
     */
    void appreciateHome();
}
