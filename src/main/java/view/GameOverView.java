package view;

import entity.Avatar;
import entity.Assets;
import interface_adapter.game_over.GameOverController;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.game_over.GameOverState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameOverView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "gameOver";
    private final GameOverViewModel gameOverViewModel;
    private GameOverController gameOverController;

    private JLabel messageLabel;
    private JLabel avatarLabel;
    private JLabel statsLabel;
    private JButton restartButton;

    public GameOverView(GameOverViewModel gameOverViewModel) {
        this.gameOverViewModel = gameOverViewModel;
        this.gameOverViewModel.addPropertyChangeListener(this);

        initializeComponents();
        setupLayout();

        updateTheme(gameOverViewModel.getState().isDarkModeEnabled());
    }

    private void initializeComponents() {
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));

        avatarLabel = new JLabel("", SwingConstants.CENTER);
        avatarLabel.setHorizontalAlignment(SwingConstants.CENTER);

        statsLabel = new JLabel("", SwingConstants.CENTER);
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.addActionListener(this);
    }

    private void setupLayout() {
        // Apply padding around the entire view
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new BorderLayout(10, 10));

        // Message panel at the top
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        // Center panel to hold avatar and stats
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Avatar panel with centered flow layout
        JPanel avatarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        avatarPanel.add(avatarLabel);

        // Stats panel with centered flow layout
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statsPanel.add(statsLabel);

        // Add components to center panel with vertical spacing
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(avatarPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing between avatar and stats
        centerPanel.add(statsPanel);
        centerPanel.add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(restartButton);

        add(messagePanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    // Handle restart button click
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            GameOverState state = gameOverViewModel.getState();
            gameOverController.switchToHomepage(
                    state.getAssets(),
                    state.getHappiness(),
                    state.getAvatar(),
                    state.isDarkModeEnabled(),
                    state.getAge(),
                    state.getCharacterName(),
                    state.getUsername()
            );
        }
    }

    // Update view when game state changes
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            GameOverState state = (GameOverState) evt.getNewValue();

            // Set game over message based on failure condition
            if (state.getHappiness() <= 0) {
                messageLabel.setText("Game Over: Your happiness has hit 0.");
            } else if (state.getAssets() != null && state.getAssets().getCash() < 0) {
                messageLabel.setText("Game Over: Your net worth is in the negative!!");
            } else {
                messageLabel.setText("Game Over: Your net worth is negative.");
            }

            // Set avatar
            Avatar avatar = state.getAvatar();
            if (avatar != null && avatar.getIcon() != null) {
                avatarLabel.setIcon(avatar.getIcon());
                avatarLabel.setText("");
            } else {
                avatarLabel.setIcon(null);
                avatarLabel.setText("No Avatar Available");
            }

            // Set stats label
            Assets assets = state.getAssets();
            final String statName = state.getCharacterName() != null ? state.getCharacterName() : state.getUsername();
            statsLabel.setText(String.format(
                    "<html><div style='text-align: center;'>Character: %s<br>Age: %d<br>Net Worth: $%.2f<br>Happiness: %d</div></html>",
                    statName,
                    state.getAge(),
                    assets != null ? assets.getCash() : 0.0,
                    state.getHappiness()
            ));

            // Update theme
            updateTheme(state.isDarkModeEnabled());
        }
    }

    // Theme update method
    private void updateTheme(boolean isDarkMode) {
        if (isDarkMode) {
            ColorTheme.applyDarkMode(this);
        } else {
            ColorTheme.applyLightMode(this);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    // Getter for view name
    public String getViewName() {
        return viewName;
    }

    // Setter for game over controller
    public void setController(GameOverController gameOverController) {
        this.gameOverController = gameOverController;
    }
}