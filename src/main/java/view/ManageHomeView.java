package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.asset_manager.AssetManagerState;
import interface_adapter.homepage.HomepageState;
import interface_adapter.manage_home.ManageHomeController;
import interface_adapter.manage_home.ManageHomeState;
import interface_adapter.manage_home.ManageHomeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * The View for the Manage Stock Use Case.
 */
public class ManageHomeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "manageHome";
    private final GridBagConstraints constraints = new GridBagConstraints();

    private final ManageHomeViewModel viewModel;
    private ManageHomeController controller;

    private JLabel decriptionLabel;
    private final ButtonGroup houseGroup = new ButtonGroup();
    private final ArrayList<JToggleButton> houseButtons = new ArrayList<>();
    private final JPanel housePanel = new JPanel();

    private final JButton backButton;
    private final JButton buyButton;
    private final JButton sellButton;

    public ManageHomeView(ManageHomeViewModel manageHomeViewModel) {
        this.viewModel = manageHomeViewModel;
        this.viewModel.addPropertyChangeListener(this);

        ManageHomeState state = viewModel.getState();

        // Set the layout to GridBagLayout for centering
        this.setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10); // Minimal space between components

        // Title
        final JLabel title = new JLabel(ManageHomeViewModel.TITLE, SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridy = 0;
        this.add(title, constraints);

        // Description
        if (state.hasHome()) {
            decriptionLabel = new JLabel(ManageHomeViewModel.SELL_DESCRIPTION, SwingConstants.CENTER);
        }
        else {
            decriptionLabel = new JLabel(ManageHomeViewModel.BUY_DESCRIPTION, SwingConstants.CENTER);
        }
        constraints.gridy++;
        this.add(decriptionLabel, constraints);

        // House options
        housePanel.setLayout(new BoxLayout(housePanel, BoxLayout.Y_AXIS));
        housePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (String description : ManageHomeViewModel.HOME_DESCRIPTIONS) {
            final JToggleButton house = new JToggleButton(description);
            house.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            housePanel.add(house);
            houseGroup.add(house);
            houseButtons.add(house);
            housePanel.add(Box.createVerticalStrut(10));
            house.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        if (!state.hasHome()) {
            constraints.gridy++;
            this.add(housePanel, constraints);
        }

        // Buttons
        backButton = new JButton(ManageHomeViewModel.BACK_LABEL);
        buyButton = new JButton(ManageHomeViewModel.BUY_LABEL);
        sellButton = new JButton(ManageHomeViewModel.SELL_LABEL);

        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(backButton);
        if (state.hasHome()) {
            buttonPanel.add(sellButton);
        } else {
            buttonPanel.add(buyButton);
        }
        constraints.gridy++;
        this.add(buttonPanel, constraints);

        // Set Look and Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(state.isDarkMode());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        final ManageHomeState state = (ManageHomeState) evt.getNewValue();
        updateTheme(state.isDarkMode());
        if (state.hasHome()) {
            decriptionLabel.setText(ManageHomeViewModel.SELL_DESCRIPTION);
            if (housePanel.getParent() == this) {
                this.remove(housePanel);
                this.revalidate();
                this.repaint();
                constraints.gridy--;
            }
        }
        else {
            decriptionLabel.setText(ManageHomeViewModel.BUY_DESCRIPTION);
            if (housePanel.getParent() != this) {
                constraints.gridy++;
                this.add(housePanel, 2);
                this.revalidate();
                this.repaint();
            }
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setManageStockController(ManageHomeController manageHomeController) {
        this.controller = manageHomeController;
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Manage Stock Example");
        frame.setSize(500, 600);  // Set frame size to 500x600px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JPanel cardPanel = new JPanel();
        final CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        final ViewManagerModel viewManagerModel = new ViewManagerModel();

        final ManageHomeViewModel viewModel = new ManageHomeViewModel();
        final ManageHomeState state = viewModel.getState();
        state.setDarkMode(true);
        state.setHasHome(false);
        final ManageHomeView view = new ManageHomeView(viewModel);
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
