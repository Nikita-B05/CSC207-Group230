package app;
import javax.swing.*;
import java.awt.*;

public class DarkModeDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dark Mode Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        Color backgroundColor = new Color(45, 45, 45);
        Color foregroundColor = new Color(220, 220, 220);
        Color buttonColor = new Color(70, 70, 70);
        Color buttonTextColor = foregroundColor;

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new GridBagLayout());

        JLabel label = new JLabel("This is what dark mode looks like");
        label.setForeground(foregroundColor);

        JTextField textField = new JTextField(15);
        textField.setBackground(buttonColor);
        textField.setForeground(buttonTextColor);
        textField.setCaretColor(foregroundColor);
        textField.setBorder(BorderFactory.createLineBorder(buttonTextColor));

        JButton button = new JButton("Click Me");
        button.setBackground(buttonColor);
        button.setForeground(buttonTextColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(buttonTextColor));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        gbc.gridy++;
        panel.add(textField, gbc);

        gbc.gridy++;
        panel.add(button, gbc);

        frame.add(panel);

        frame.setVisible(true);
    }
}
