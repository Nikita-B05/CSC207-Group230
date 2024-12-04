package use_case.manage_stock;

import java.io.IOException;
import java.util.Map;

/**
 * Stock data interface for choose asset use case.
 */
public interface ManageStockStockAccessInterface {

    /**
     * Gets price for one unit of stock.
     * @param stockCode the stock code to retrieve for.
     * @return price for one unit of stock.
     * @throws IOException cause of stuff.
     */
    double getPrice(String stockCode) throws IOException;

    /**
     * Gets a map of stock code to company names.
     * @return a map of stock code to company names.
     */
    Map<String, String> getCodeToName();

    /**
     * Gets a map of stock code to price.
     * @return a map of stock code to price.
     */
    Map<String, Double> getCodeToPrice();
}
