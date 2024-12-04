package view;

import java.awt.*;
import javax.swing.*;

public class ColorTheme {
    public static void applyDarkMode(JPanel panel) {
        panel.setBackground(Color.DARK_GRAY);
        panel.setForeground(Color.WHITE);
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JPanel) {
                applyDarkMode((JPanel) comp);
            }
            else if (comp instanceof JLabel) {
                comp.setForeground(Color.WHITE);
                comp.setBackground(Color.DARK_GRAY);
            }
            else if (comp instanceof JButton || comp instanceof JToggleButton) {
                comp.setBackground(Color.GRAY);
                comp.setForeground(Color.WHITE);
            }
            else if (comp instanceof JTextField || comp instanceof JPasswordField) {
                comp.setBackground(Color.DARK_GRAY);
                comp.setForeground(Color.WHITE);
            }
            else if (comp instanceof JCheckBox) {
                comp.setForeground(Color.WHITE);
                ((JCheckBox) comp).setBackground(Color.DARK_GRAY);
            }
        }
    }

    public static void applyLightMode(JPanel panel) {
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.BLACK);
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JPanel) {
                applyLightMode((JPanel) comp);
            }
            else if (comp instanceof JLabel) {
                comp.setForeground(Color.BLACK);
                comp.setBackground(Color.WHITE);
            }
            else if (comp instanceof JButton) {
                comp.setBackground(UIManager.getColor("Button.background"));
                comp.setForeground(Color.BLACK);
            }
            else if (comp instanceof JTextField || comp instanceof JPasswordField) {
                comp.setBackground(Color.WHITE);
                comp.setForeground(Color.BLACK);
            }
            else if (comp instanceof JCheckBox) {
                comp.setForeground(Color.BLACK);
                ((JCheckBox) comp).setBackground(Color.WHITE);
            }
        }
    }
}
