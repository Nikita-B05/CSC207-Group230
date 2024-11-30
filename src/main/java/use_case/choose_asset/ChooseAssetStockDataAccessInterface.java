package use_case.choose_asset;

import java.util.Map;

/**
 * Stock data interface for choose asset use case.
 */
public interface ChooseAssetStockDataAccessInterface {

    /**
     * Gets an array of company names.
     * @return an array of company names.
     */
    String[] getStockNames();

    /**
     * Gets a map of company names to stock code.
     * @return a map of company names to stock code.
     */
    Map<String, String> getNameToCode();

    /**
     * Gets a map of stock code to price.
     * @return a map of stock code to price.
     */
    Map<String, Double> getCodeToPrice();

    /**
     * Updates the object to retrieve prices of the new date.
     * @param date new date
     */
    void setDate(String date);
}
