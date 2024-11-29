package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.asset_manager.AssetManagerController;
import interface_adapter.asset_manager.AssetManagerState;
import interface_adapter.asset_manager.AssetManagerViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Asset Manager Use Case.
 */
public class AssetManagerView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "assetManager";

    private final AssetManagerViewModel assetManagerViewModel;
    private AssetManagerController assetManagerController;

    private final JLabel title;

    private final JButton manageStock;
    private final JButton manageHome;

    public AssetManagerView(AssetManagerViewModel assetManagerViewModel) {
        this.assetManagerViewModel = assetManagerViewModel;
        this.assetManagerViewModel.addPropertyChangeListener(this);

        AssetManagerState assetManagerState = assetManagerViewModel.getState();

        title = new JLabel(AssetManagerViewModel.TITLE);
        manageStock = new JButton(AssetManagerViewModel.MANAGE_STOCK_LABEL);
        manageHome = new JButton(AssetManagerViewModel.MANAGE_HOME_LABEL);

        // Set the layout to BoxLayout and add padding
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Center the content horizontally using alignment
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageStock.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageHome.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add content to the panel
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createVerticalStrut(10));  // Add space between buttons
        this.add(manageHome);
        this.add(Box.createVerticalStrut(10));  // Add space between buttons
        this.add(manageStock);
        this.add(Box.createVerticalGlue());

        // Add action listeners for buttons
        manageHome.addActionListener(evt -> {
            if (evt.getSource().equals(manageHome)) {
                assetManagerController.switchToManageHomeView();
            }
        });

        manageStock.addActionListener(evt -> {
            if (evt.getSource().equals(manageStock)) {
                assetManagerController.switchToManageStockView();
            }
        });

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(assetManagerState.isDarkMode());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void setAssetManagerController(AssetManagerController assetManagerController) {
        this.assetManagerController = assetManagerController;
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Asset Manager Example");
        frame.setSize(500, 600);  // Set frame size to 500x600px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Just to see the view
        final JPanel cardPanel = new JPanel();
        final CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        final ViewManagerModel viewManagerModel = new ViewManagerModel();

        final AssetManagerViewModel assetManagerViewModel = new AssetManagerViewModel();
        final AssetManagerState assetManagerState = assetManagerViewModel.getState();
        assetManagerState.setDarkMode(true);
        final AssetManagerView assetManagerView = new AssetManagerView(assetManagerViewModel);
        cardPanel.add(assetManagerView, assetManagerView.getViewName());

        frame.add(cardPanel);

        viewManagerModel.setState(assetManagerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("AssetManager click: " + evt.getActionCommand());
    }
}
