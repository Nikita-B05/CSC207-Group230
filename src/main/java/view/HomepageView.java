package view;

import entity.Avatar;
import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomepageController;
import interface_adapter.homepage.HomepageState;
import interface_adapter.homepage.HomepageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

/**
 * The View for the HomePage Use Case.
 */
public class HomepageView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "homepage";

    private final HomepageViewModel homePageViewModel;
    private HomepageController homepageController;

    private final JButton chooseAvatar;
    private final JButton playGame;
    private final JButton decisionLog;
    private final JButton profileSettings;

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Homepage Example");
        frame.setSize(500, 600);  // Set frame size to 500x600px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Just to see the view
        final JPanel cardPanel = new JPanel();
        final CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        final ViewManagerModel viewManagerModel = new ViewManagerModel();

        final HomepageViewModel homepageViewModel = new HomepageViewModel();
        homepageViewModel.getState().setName("Example Name");
        final HomepageView homePageView = new HomepageView(homepageViewModel);
        cardPanel.add(homePageView, homePageView.getViewName());

        frame.add(cardPanel);

        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        frame.pack();
        frame.setVisible(true);
    }

    public HomepageView(HomepageViewModel homePageViewModel) {
        this.homePageViewModel = homePageViewModel;
        homePageViewModel.addPropertyChangeListener(this);

        HomepageState homepageState = homePageViewModel.getState();

        chooseAvatar = new JButton(HomepageViewModel.CHOOSE_AVATAR_LABEL);
        playGame = new JButton(HomepageViewModel.PLAY_GAME_LABEL);
        decisionLog = new JButton(HomepageViewModel.DECISION_LOG_LABEL);
        profileSettings = new JButton(HomepageViewModel.PROFILE_SETTINGS_LABEL);

        // Set the layout to BoxLayout and add padding
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add greeting at the top
        if (homepageState.getName() != null) {
            JLabel greetingLabel = new JLabel("Hi, " + homepageState.getName());
            greetingLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the greeting
            this.add(greetingLabel); // Add greeting at the top
            this.add(Box.createVerticalStrut(20)); // Add space below the image
        }

        // Add the image above the buttons
        String imagePath = System.getProperty("user.dir") + homepageState.getAvatar().getImagePath();
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(imagePath);
        } catch (Exception e) {
            System.err.println("Could not load image: " + imagePath);
        }
        if (imageIcon != null) {
            Image scaledImage = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);

            JLabel imageLabel = new JLabel(resizedIcon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the image
            this.add(imageLabel); // Add image at the top
            this.add(Box.createVerticalStrut(20)); // Add space below the image
        }

        // Center the buttons horizontally using alignment
        chooseAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        playGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        decisionLog.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileSettings.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the panel
        this.add(chooseAvatar);
        this.add(Box.createVerticalStrut(10));  // Add space between buttons
        this.add(playGame);
        this.add(Box.createVerticalStrut(10));  // Add space between buttons
        this.add(decisionLog);
        this.add(Box.createVerticalStrut(10));  // Add space between buttons
        this.add(profileSettings);

        // Add action listeners for buttons
        chooseAvatar.addActionListener(evt -> {
            if (evt.getSource().equals(chooseAvatar)) {
                final HomepageState currentState = homePageViewModel.getState();
                homepageController.switchToAvatarView(currentState.getUsername());
            }
        });

        playGame.addActionListener(evt -> {
            if (evt.getSource().equals(playGame)) {
                final HomepageState currentState = homePageViewModel.getState();
                homepageController.switchToPlayGameView(currentState.getUsername());
            }
        });

        decisionLog.addActionListener(evt -> {
            if (evt.getSource().equals(decisionLog)) {
                final HomepageState currentState = homePageViewModel.getState();
                homepageController.switchToDecisionLogView(currentState.getUsername());
            }
        });

        profileSettings.addActionListener(evt -> {
            if (evt.getSource().equals(profileSettings)) {
                final HomepageState currentState = homePageViewModel.getState();
                homepageController.switchToProfileSettingsView(currentState.getUsername());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Homepage click: " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final HomepageState state = (HomepageState) evt.getNewValue();
        // do something
    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(HomepageController controller) {
        this.homepageController = controller;
    }
}
