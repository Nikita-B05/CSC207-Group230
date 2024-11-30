package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.manage_home.ManageHomeState;
import interface_adapter.manage_stock.ManageStockController;
import interface_adapter.manage_stock.ManageStockState;
import interface_adapter.manage_stock.ManageStockViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The View for the Manage Stock Use Case.
 */
public class ManageStockView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "manageStock";

    private final ManageStockViewModel viewModel;
    private ManageStockController controller;

    private final JComboBox<String> stockSelector;
    private final JTextField quantityField;

    private final JLabel cashAvailable;
    private final JLabel totalField;

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

        // Cash Available
        cashAvailable = new JLabel("Cash Available: $" + state.getCash());
        cashAvailable.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 3;
        this.add(cashAvailable, gbc);

        // Cash Available
        totalField = new JLabel("Total: $" + state.getQuantity());
        totalField.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 4;
        this.add(totalField, gbc);

        // Buttons
        backButton = new JButton(ManageStockViewModel.BACK_LABEL);
        buyButton = new JButton(ManageStockViewModel.BUY_LABEL);
        sellButton = new JButton(ManageStockViewModel.SELL_LABEL);

        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(backButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        gbc.gridy = 5;
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
                String stockCode = state.getNameToCode().get((String) stockSelector.getSelectedItem());
                try {
                    int quantity;
                    if (quantityField.getText().isEmpty()) {
                        quantity = 0;
                    }
                    else {
                        quantity = Integer.parseInt(quantityField.getText());
                    }

                    state.setCode(stockCode);
                    double totalAmount = quantity * state.getCodeToPrice().get(state.getCode());
                    totalField.setText("Total: $" + totalAmount);
                } catch (NumberFormatException e) {
                    // Don't update
                }
            }
        });

        quantityField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                state.setQuantity(quantityField.getText());
                try {
                    int quantity;
                    if (quantityField.getText().isEmpty()) {
                        quantity = 0;
                    }
                    else {
                        quantity = Integer.parseInt(quantityField.getText());
                    }
                    double totalAmount = quantity *
                            state
                                    .getCodeToPrice()
                                    .get(state.getNameToCode().get((String) stockSelector.getSelectedItem()));
                    totalField.setText("Total: $" + totalAmount);
                } catch (NumberFormatException e) {
                    // Don't update
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        backButton.addActionListener(evt -> {
            if (evt.getSource().equals(backButton)) {
                controller.switchToAssetManagerView();
            }
        });

        buyButton.addActionListener(evt -> {
            if (evt.getSource().equals(buyButton)) {
                controller.execute(state.getCode(), state.getQuantity(), true);
            }
        });

        sellButton.addActionListener(evt -> {
            if (evt.getSource().equals(sellButton)) {
                controller.execute(state.getCode(), state.getQuantity(), false);
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
        if (evt.getPropertyName().equals("state")) {
            handleStateChange(evt);
        }
        else if (evt.getPropertyName().equals("manageStockError")) {
            handleErrorInput(evt);
        }
        else if (evt.getPropertyName().equals("manageStockBuySuccess")) {
            handleSuccessInput(evt);
        }
        else if (evt.getPropertyName().equals("manageStockSellSuccess")) {
            handleSuccessInput(evt);
        }
    }

    private void handleStateChange(PropertyChangeEvent evt) {
        final ManageStockState state = (ManageStockState) evt.getNewValue();
        cashAvailable.setText("Cash Available: $" + state.getCash());
        updateTheme(state.isDarkMode());
        setItems(state.getStockNames());
    }

    private void handleErrorInput(PropertyChangeEvent evt) {
        final ManageStockState state = (ManageStockState) evt.getNewValue();
        if (state.getErrorMessage() == null) {
            throw new RuntimeException("Error message cannot be null.");
        }
        else {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }
    }

    private void handleSuccessInput(PropertyChangeEvent evt) {
        final ManageStockState state = (ManageStockState) evt.getNewValue();
        if (state.getSuccessMessage() == null) {
            throw new RuntimeException("Success message cannot be null.");
        }
        else {
            cashAvailable.setText("Cash Available: $" + state.getCash());
            JOptionPane.showMessageDialog(this, state.getSuccessMessage());
        }
    }

    private void setItems(String[] newItems) {
        stockSelector.setModel(new DefaultComboBoxModel<>(newItems));
    }

    public String getViewName() {
        return viewName;
    }

    public void setManageStockController(ManageStockController manageStockController) {
        this.controller = manageStockController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("AssetManager click: " + evt.getActionCommand());
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
        state.setDarkMode(false);
        String[] stockNames = {"Stock1", "Stock2", "Stock3"};
        state.setStockNames(stockNames);
        Map<String, Double> nameToPrice = new HashMap<>();
        nameToPrice.put("Stock1", 1.0);
        nameToPrice.put("Stock2", 2.0);
        nameToPrice.put("Stock3", 3.0);
        state.setCodeToPrice(nameToPrice);
        Map<String, String> nameToCode = new HashMap<>();
        nameToCode.put("Stock1", "Stock1");
        nameToCode.put("Stock2", "Stock2");
        nameToCode.put("Stock3", "Stock3");
        state.setNameToCode(nameToCode);
        final ManageStockView view = new ManageStockView(viewModel);
        cardPanel.add(view, view.getViewName());

        frame.add(cardPanel);

        viewManagerModel.setState(viewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        frame.pack();
        frame.setVisible(true);
    }
}
