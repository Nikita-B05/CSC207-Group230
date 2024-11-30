package view;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JPanel {
    public GameOverView() {
        setLayout(new BorderLayout());

        // Center Panel for Message
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel messageLabel = new JLabel("You have run out of money and assets!");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(gameOverLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(messageLabel);
        centerPanel.add(Box.createVerticalGlue());

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Restart Game");
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);

        restartButton.addActionListener(e -> System.out.println("Restarting game..."));
        exitButton.addActionListener(e -> System.exit(0));

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new GameOverView());
        frame.setVisible(true);
    }
}