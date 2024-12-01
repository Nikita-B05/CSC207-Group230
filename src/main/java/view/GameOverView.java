package view;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JPanel {
    public GameOverView(String reason) {
        setLayout(new BorderLayout());

        // Center Panel for Message
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Determine the message based on the reason
        String messageText = switch (reason.toLowerCase()) {
            case "money" -> "You ran out of money!";
            case "happiness" -> "You are depressed! :(";
            default -> "You have crippling debt and depression :(";
        };

        JLabel messageLabel = new JLabel(messageText);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(gameOverLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(messageLabel);
        centerPanel.add(Box.createVerticalGlue());

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Return to Homepage");
        buttonPanel.add(restartButton);

        restartButton.addActionListener(e -> System.out.println("Returning to homepage..."));

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // Example for testing
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Test with different reasons
        String reason = "money"; // Change to "happiness" or other value to test
        frame.add(new GameOverView(reason));
        frame.setVisible(true);
    }
}