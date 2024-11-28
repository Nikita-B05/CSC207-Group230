package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.asset_manager.AssetManagerController;
import interface_adapter.asset_manager.AssetManagerState;
import interface_adapter.asset_manager.AssetManagerViewModel;
import interface_adapter.manage_stock.ManageStockController;
import interface_adapter.manage_stock.ManageStockState;
import interface_adapter.manage_stock.ManageStockViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Manage Stock Use Case.
 */
public class ManageStockView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "assetManager";

    private final ManageStockViewModel viewModel;
    private ManageStockController controller;

    private final JComboBox<String> stockSelector;
    private final JTextField quantityField;

    private final JButton backButton;
    private final JButton buyButton;
    private final JButton sellButton;

    public ManageStockView(ManageStockViewModel manageStockViewModel) {
        this.viewModel = manageStockViewModel;
        this.viewModel.addPropertyChangeListener(this);

        ManageStockState state = viewModel.getState();

        // Set the layout to GridBagLayout for centering
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Minimal space between components
        gbc.gridx = 0;

        // Title
        final JLabel title = new JLabel(ManageStockViewModel.TITLE, SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        this.add(title, gbc);

        // Stock selector
        final JLabel stockSelectorLabel = new JLabel(ManageStockViewModel.CHOOSE_STOCK_LABEL);
        stockSelector = new JComboBox<>(state.getStockNames());
        stockSelector.setMaximumSize(new Dimension(200, 30)); // Set a fixed size for the combo box

        final JPanel chooseStockPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        chooseStockPanel.add(stockSelectorLabel);
        chooseStockPanel.add(stockSelector);
        gbc.gridy = 1;
        this.add(chooseStockPanel, gbc);

        // Quantity field
        final JLabel quantityFieldLabel = new JLabel(ManageStockViewModel.QUANTITY_LABEL);
        quantityField = new JTextField(15);
        quantityField.setMaximumSize(new Dimension(200, 30)); // Set a fixed size for the text field

        final JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        quantityPanel.add(quantityFieldLabel);
        quantityPanel.add(quantityField);
        gbc.gridy = 2;
        this.add(quantityPanel, gbc);

        // Buttons
        backButton = new JButton(ManageStockViewModel.BACK_LABEL);
        buyButton = new JButton(ManageStockViewModel.BUY_LABEL);
        sellButton = new JButton(ManageStockViewModel.SELL_LABEL);

        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(backButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        gbc.gridy = 3;
        this.add(buttonPanel, gbc);

        // Set Look and Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(state.isDarkMode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add action listeners for buttons
        stockSelector.addActionListener(evt -> {
            if (evt.getSource().equals(stockSelector)) {
                // controller.doSomething();
            }
        });

        backButton.addActionListener(evt -> {
            if (evt.getSource().equals(backButton)) {
                // controller.goBack();
            }
        });

        buyButton.addActionListener(evt -> {
            if (evt.getSource().equals(buyButton)) {
                // controller.buy();
            }
        });

        sellButton.addActionListener(evt -> {
            if (evt.getSource().equals(sellButton)) {
                // controller.sell();
            }
        });
    }

    private void updateTheme(boolean isDarkMode) {
        if (isDarkMode) {
            ColorTheme.applyDarkMode(this);
        } else {
            ColorTheme.applyLightMode(this);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AssetManagerState state = (AssetManagerState) evt.getNewValue();
        updateTheme(state.isDarkMode());
    }

    public String getViewName() {
        return viewName;
    }

    public void setManageStockController(ManageStockController manageStockController) {
        this.controller = manageStockController;
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Manage Stock Example");
        frame.setSize(500, 600);  // Set frame size to 500x600px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JPanel cardPanel = new JPanel();
        final CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        final ViewManagerModel viewManagerModel = new ViewManagerModel();

        final ManageStockViewModel viewModel = new ManageStockViewModel();
        final ManageStockState state = viewModel.getState();
        state.setStockNames(new String[]{"Stock1", "Stock2", "Stock3"});
        state.setDarkMode(false);
        final ManageStockView view = new ManageStockView(viewModel);
        cardPanel.add(view, view.getViewName());

        frame.add(cardPanel);

        viewManagerModel.setState(viewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("AssetManager click: " + evt.getActionCommand());
    }
}
