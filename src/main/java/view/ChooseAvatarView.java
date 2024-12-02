package view;

import entity.Avatar;
import interface_adapter.choose_avatar.ChooseAvatarController;
import interface_adapter.choose_avatar.ChooseAvatarState;
import interface_adapter.choose_avatar.ChooseAvatarViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The View for the Choose Avatar Use Case.
 */
public class ChooseAvatarView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "chooseAvatar";
    private final ChooseAvatarViewModel viewModel;
    private ChooseAvatarController controller;

    private final List<JToggleButton> avatarButtons = new ArrayList<>();
    private final ButtonGroup avatarGroup = new ButtonGroup();
    private final List<Avatar> avatars;

    private final JLabel titleLabel;
    private final JPanel avatarPanel;
    private final JPanel buttonPanel;

    public ChooseAvatarView(ChooseAvatarViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        // Title Label
        titleLabel = new JLabel("Choose Your Avatar");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Avatar Panel
        avatarPanel = new JPanel();
        avatarPanel.setLayout(new GridLayout(2, 3, 10, 10));
        avatarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Load avatars
        avatars = loadAvatars();
        for (Avatar avatar : avatars) {
            ImageIcon icon = avatar.getIcon();
            JToggleButton avatarButton = new JToggleButton(icon);
            avatarButton.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            avatarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            avatarButton.setFocusPainted(false);
            avatarButton.setContentAreaFilled(false);

            // Add ItemListener to update border on selection
            avatarButton.addItemListener(evt -> {
                if (avatarButton.isSelected()) {
                    avatarButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                }
                else {
                    avatarButton.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
                }
            });

            avatarGroup.add(avatarButton);
            avatarPanel.add(avatarButton);
            avatarButtons.add(avatarButton);
        }

        // Confirm Button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            int selectedIndex = getSelectedAvatarIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select an avatar.",
                        "No Avatar Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Avatar selectedAvatar = avatars.get(selectedIndex);
            ChooseAvatarState state = viewModel.getState();
            controller.selectAvatar(state.getUsername(), selectedAvatar);
        });

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);

        // Layout setup
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(avatarPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(viewModel.getState().isDarkMode());
        }
        catch (Exception e) {
            e.printStackTrace();
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
        List<Avatar> avatars = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            String imagePath = "/images/avatar" + i + ".png";
            ImageIcon icon = null;
            try {
                icon = new ImageIcon(getClass().getResource(imagePath));
            } catch (Exception e) {
                System.err.println("Could not load image: " + imagePath);
                e.printStackTrace();
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

    }
}