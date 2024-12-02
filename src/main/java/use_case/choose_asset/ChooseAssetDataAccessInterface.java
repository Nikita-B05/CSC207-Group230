package use_case.choose_asset;

import entity.User;

/**
 * DAO for the Choose Asset Use Case.
 */
public interface ChooseAssetDataAccessInterface {

    /**
     * Returns the user with the given username.
     * @return the user with the given username
     */
    User getCurrentUser();
}
