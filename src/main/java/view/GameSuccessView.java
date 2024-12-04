package view;

import entity.Avatar;
import interface_adapter.ViewManagerModel;
import interface_adapter.game_success.GameSuccesController;
import interface_adapter.game_success.GameSuccessState;
import interface_adapter.game_success.GameSuccessViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameSuccessView extends JPanel implements PropertyChangeListener {
    private final String viewName = "gameSuccess";

    private GameSuccessViewModel viewModel;
    private GameSuccesController controller;

    private JLabel successLabel;
    private JLabel avatarLabel;
    private JLabel happinessLabel;
    private JLabel netWorthLabel;

    public GameSuccessView(GameSuccessViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        final GameSuccessState state = viewModel.getState();

        // Header Label
        successLabel = new JLabel("Congratulations " + state.getCharacterName() + "!");
        successLabel.setFont(new Font("Arial", Font.BOLD, 24));
        successLabel.setHorizontalAlignment(SwingConstants.CENTER);
        successLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        successLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Avatar Image (replace with your actual file path)
        avatarLabel = new JLabel(state.getAvatar().getIcon());
        avatarLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center avatar

        // Current User Info Panel
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        happinessLabel = new JLabel("Happiness: " + state.getHappiness());
        happinessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        happinessLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        userInfoPanel.add(happinessLabel);
        netWorthLabel = new JLabel(String.format("Net Worth: $%,.2f", state.getNetWorth()));
        netWorthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInfoPanel.add(netWorthLabel);

        JButton returnButton = new JButton("Return to Homepage");
        returnButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        returnButton.addActionListener(exp -> controller.switchToHomepageView());
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set the layout to BoxLayout and add padding
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.add(Box.createVerticalGlue());
        this.add(successLabel);
        this.add(avatarLabel);
        this.add(userInfoPanel);
        this.add(returnButton);
        this.add(Box.createVerticalGlue());

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(state.isDarkMode());
        }
        catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final GameSuccessState state = (GameSuccessState) evt.getNewValue();
        updateTheme(state.isDarkMode());

        happinessLabel.setText("Happiness: " + state.getHappiness());
        netWorthLabel.setText(String.format("Net Worth: $%,.2f", state.getNetWorth()));
        avatarLabel.setIcon(state.getAvatar().getIcon());
        String charName;
        if (state.getCharacterName() != null) {
            charName = state.getCharacterName();
        }
        else {
            charName = state.getUsername();
        }
        successLabel.setText("Congratulations " + charName + "!");
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

    public String getViewName() {
        return viewName;
    }

    public void setController(GameSuccesController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Game Success Example");
        frame.setSize(500, 600);  // Set frame size to 500x600px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Just to see the view
        final JPanel cardPanel = new JPanel();
        final CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        final ViewManagerModel viewManagerModel = new ViewManagerModel();

        final GameSuccessViewModel viewModel = new GameSuccessViewModel();
        final GameSuccessState state = viewModel.getState();
        state.setCharacterName("Example UserName");
        state.setAvatar(new Avatar());
        state.setHappiness(100);
        state.setNetWorth(1000.123);
        state.setDarkMode(false);
        final GameSuccessView view = new GameSuccessView(viewModel);
        cardPanel.add(view, view.getViewName());
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.add(cardPanel);

        viewManagerModel.setState(viewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        frame.pack();
        frame.setVisible(true);
    }
}
