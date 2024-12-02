package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import entity.Avatar;
import interface_adapter.choose_avatar.ChooseAvatarController;
import interface_adapter.choose_avatar.ChooseAvatarState;
import interface_adapter.choose_avatar.ChooseAvatarViewModel;

/**
 * The View for the Choose Avatar Use Case.
 */
public class ChooseAvatarView extends JPanel implements ActionListener, PropertyChangeListener {

    // Constants
    private static final int TITLE_FONT_SIZE = 18;
    private static final int AVATAR_PANEL_ROWS = 2;
    private static final int AVATAR_PANEL_COLS = 3;
    private static final int AVATAR_PANEL_HGAP = 10;
    private static final int AVATAR_PANEL_VGAP = 10;
    private static final int PANEL_PADDING = 20;
    private static final int AVATAR_BUTTON_BORDER_WIDTH = 2;
    private static final int AVATAR_BUTTON_EMPTY_BORDER = 2;
    private static final int FOR_LOOP_AVATAR = 6;

    private final String viewName = "chooseAvatar";
    private final ChooseAvatarViewModel viewModel;
    private ChooseAvatarController controller;

    private final List<JToggleButton> avatarButtons = new ArrayList<>();
    private final ButtonGroup avatarGroup = new ButtonGroup();
    private final List<Avatar> avatarList;  // Renamed to avoid shadowing the field

    private final JLabel titleLabel;
    private final JPanel avatarPanel;
    private final JPanel buttonPanel;

    public ChooseAvatarView(ChooseAvatarViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        // Title Label
        titleLabel = new JLabel("Choose Your Avatar");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        // Avatar Panel
        avatarPanel = new JPanel();
        avatarPanel.setLayout(new GridLayout(AVATAR_PANEL_ROWS, AVATAR_PANEL_COLS, AVATAR_PANEL_HGAP,
                AVATAR_PANEL_VGAP));
        avatarPanel.setBorder(BorderFactory.createEmptyBorder(PANEL_PADDING, PANEL_PADDING, PANEL_PADDING,
                PANEL_PADDING));

        avatarList = loadAvatars();  // Renamed to avoid shadowing the field
        for (Avatar avatar : avatarList) {
            final ImageIcon icon = avatar.getIcon();
            final JToggleButton avatarButton = new JToggleButton(icon);
            avatarButton.setBorder(BorderFactory.createEmptyBorder(AVATAR_BUTTON_EMPTY_BORDER,
                    AVATAR_BUTTON_EMPTY_BORDER,
                    AVATAR_BUTTON_EMPTY_BORDER, AVATAR_BUTTON_EMPTY_BORDER));
            avatarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            avatarButton.setFocusPainted(false);
            avatarButton.setContentAreaFilled(false);

            avatarButton.addItemListener(evt -> {  // Renamed to 'evt' to follow conventions
                if (avatarButton.isSelected()) {
                    avatarButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, AVATAR_BUTTON_BORDER_WIDTH));
                }
                else {
                    avatarButton.setBorder(BorderFactory.createEmptyBorder(AVATAR_BUTTON_EMPTY_BORDER,
                            AVATAR_BUTTON_EMPTY_BORDER, AVATAR_BUTTON_EMPTY_BORDER, AVATAR_BUTTON_EMPTY_BORDER));
                }
            });

            avatarGroup.add(avatarButton);
            avatarPanel.add(avatarButton);
            avatarButtons.add(avatarButton);
        }

        final JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(evt -> {
            final int selectedIndex = getSelectedAvatarIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select an avatar.",
                        "No Avatar Selected", JOptionPane.WARNING_MESSAGE);
            }
            else {
                final Avatar selectedAvatar = avatarList.get(selectedIndex);
                final ChooseAvatarState state = viewModel.getState();
                controller.selectAvatar(state.getUsername(), selectedAvatar);
            }
        });

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);

        // Layout Setup
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(PANEL_PADDING, PANEL_PADDING, PANEL_PADDING, PANEL_PADDING));
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(avatarPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(viewModel.getState().isDarkMode());
        }
        catch (Exception ex) {  // Renamed to 'ex' to follow conventions
            ex.printStackTrace();
        }
    }

    private void updateTheme(boolean isDarkMode) {
        if (isDarkMode) {
            ColorTheme.applyDarkMode(this);
            ColorTheme.applyDarkMode(avatarPanel);
            ColorTheme.applyDarkMode(buttonPanel);
            titleLabel.setForeground(Color.WHITE);
        }
        else {
            ColorTheme.applyLightMode(this);
            ColorTheme.applyLightMode(avatarPanel);
            ColorTheme.applyLightMode(buttonPanel);
            titleLabel.setForeground(Color.BLACK);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ChooseAvatarState state = (ChooseAvatarState) evt.getNewValue();
        updateTheme(state.isDarkMode());
    }

    public void setController(ChooseAvatarController controller) {
        this.controller = controller;
    }

    private int getSelectedAvatarIndex() {
        for (int i = 0; i < avatarButtons.size(); i++) {
            if (avatarButtons.get(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }

    private List<Avatar> loadAvatars() {
        final List<Avatar> avatars = new ArrayList<>();
        for (int i = 1; i <= FOR_LOOP_AVATAR; i++) {
            final String imagePath = "/images/avatar" + i + ".png";
            ImageIcon icon = null;
            try {
                icon = new ImageIcon(getClass().getResource(imagePath));
            }
            catch (Exception ex) {  // Renamed to 'ex' to follow conventions
                System.err.println("Could not load image: " + imagePath);
                ex.printStackTrace();
            }
            if (icon != null) {
                avatars.add(new Avatar("Avatar " + i, imagePath));
            }
        }
        return avatars;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Intentionally left blank
    }
}
