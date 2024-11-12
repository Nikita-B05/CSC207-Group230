package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomepageController;
import interface_adapter.homepage.HomepageState;
import interface_adapter.homepage.HomepageViewModel;
import interface_adapter.signup.SignupViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the HomePage Use Case.
 */
public class HomePageView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "homepage";

    private final HomepageViewModel homePageViewModel;
    private HomepageController homepageController;

    private final JButton chooseCharacter;
    private final JButton playGame;
    private final JButton decisionLog;
    private final JButton profileSettings;

    public static void main (String[] args) {
        // Just to see the view
        final JPanel cardPanel = new JPanel();
        final CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        final ViewManagerModel viewManagerModel = new ViewManagerModel();

        final HomepageViewModel homepageViewModel = new HomepageViewModel("example");
        final HomePageView homePageView = new HomePageView(homepageViewModel);
        cardPanel.add(homePageView, homePageView.getViewName());

        final JFrame application = new JFrame("Homepage Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    public HomePageView(HomepageViewModel homePageViewModel) {
        this.homePageViewModel = homePageViewModel;
        homePageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(HomepageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        chooseCharacter = new JButton(HomepageViewModel.CHOOSE_CHARACTER_LABEL);
        buttons.add(chooseCharacter);
        playGame = new JButton(HomepageViewModel.PLAY_GAME_LABEL);
        buttons.add(playGame);
        decisionLog = new JButton(HomepageViewModel.DECISION_LOG_LABEL);
        buttons.add(decisionLog);
        profileSettings = new JButton(HomepageViewModel.PROFILE_SETTINGS_LABEL);
        buttons.add(profileSettings);

        chooseCharacter.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(chooseCharacter)) {
                            final HomepageState currentState = homePageViewModel.getState();

                            homepageController.switchToChooseCharacterView( currentState.getUsername() );
                        }
                    }
                }
        );

        playGame.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(playGame)) {
                            final HomepageState currentState = homePageViewModel.getState();

                            homepageController.switchToPlayGameView( currentState.getUsername() );
                        }
                    }
                }
        );

        decisionLog.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(decisionLog)) {
                            final HomepageState currentState = homePageViewModel.getState();

                            homepageController.switchToDecisionLogView( currentState.getUsername() );
                        }
                    }
                }
        );

        profileSettings.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(profileSettings)) {
                            final HomepageState currentState = homePageViewModel.getState();

                            homepageController.switchToProfileSettingsView( currentState.getUsername() );
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(chooseCharacter);
        this.add(playGame);
        this.add(decisionLog);
        this.add(profileSettings);
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