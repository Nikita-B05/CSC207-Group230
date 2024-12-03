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
    public static final int TWENTY = 20;
    public static final int TEN = 10;
    public static final int FIVE_HUNDRED = 500;
    public static final int SIX_HUNDRED = 600;
    private final String viewName = "assetManager";

    private final AssetManagerViewModel assetManagerViewModel;
    private AssetManagerController assetManagerController;

    private final JLabel title;

    private final JButton manageStock;
    private final JButton manageHome;
    private final JButton done;

    public AssetManagerView(AssetManagerViewModel assetManagerViewModel) {
        this.assetManagerViewModel = assetManagerViewModel;
        this.assetManagerViewModel.addPropertyChangeListener(this);

        final AssetManagerState assetManagerState = assetManagerViewModel.getState();

        title = new JLabel(AssetManagerViewModel.TITLE);
        manageStock = new JButton(AssetManagerViewModel.MANAGE_STOCK_LABEL);
        manageHome = new JButton(AssetManagerViewModel.MANAGE_HOME_LABEL);
        done = new JButton(AssetManagerViewModel.DONE_LABEL);

        // Set the layout to BoxLayout and add padding
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(TWENTY, TWENTY, TWENTY, TWENTY));

        // Center the content horizontally using alignment
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageStock.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        done.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add content to the panel
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createVerticalStrut(TEN));  // Add space between buttons
        this.add(manageHome);
        this.add(Box.createVerticalStrut(TEN));  // Add space between buttons
        this.add(manageStock);
        this.add(Box.createVerticalStrut(TWENTY));  // Add space between buttons
        this.add(done);
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

        done.addActionListener(evt -> {
            if (evt.getSource().equals(done)) {
                assetManagerController.switchToGameDecisionView();
            }
        });

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(assetManagerState.isDarkMode());
        }
        catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void updateTheme(boolean isDarkMode) {
        if (isDarkMode) {
            ColorTheme.applyDarkMode(this);
        }
        else {
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

    /**
     * An example of asset manager view.
     * @param args a list of strings representing NOTHING!
     */
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Asset Manager Example");
        frame.setSize(FIVE_HUNDRED, SIX_HUNDRED);  // Set frame size to 500x600px
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
