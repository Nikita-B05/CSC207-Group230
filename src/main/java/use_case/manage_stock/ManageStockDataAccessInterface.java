package use_case.manage_stock;

import entity.Assets;
import entity.User;

/**
 * DAO for the Manage Home Use Case.
 */
public interface ManageStockDataAccessInterface {

    /**
     * Returns the current user.
     * @return the current user.
     */
    User getCurrentUser();

    /**
     * Updates the user's assets.
     * @param assets user's new assets.
     */
    void updateAssets(Assets assets);
}
