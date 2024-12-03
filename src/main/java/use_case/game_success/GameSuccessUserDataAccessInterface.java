package use_case.game_success;

import entity.User;

public interface GameSuccessUserDataAccessInterface {
    User getCurrentUser();

    void updateSalary(User user);

    void updateAssets(User user);

    void updateHappiness(User user);

    void updateAge(User user);

    void updateDecisions(User user);
}
