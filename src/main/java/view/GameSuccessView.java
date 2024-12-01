package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GameSuccessView extends JPanel {
    public GameSuccessView(Object[][] leaderboardData, Object[] currentUserData) {
        setLayout(new BorderLayout());

        // Header Label
        JLabel successLabel = new JLabel("Game Success!");
        successLabel.setFont(new Font("Arial", Font.BOLD, 24));
        successLabel.setHorizontalAlignment(SwingConstants.CENTER);
        successLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(successLabel, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table Headers and Data
        String[] headers = {"Place", "Avatar", "Username", "Wealth", "Happiness"};
        JTable leaderboardTable = new JTable(new DefaultTableModel(leaderboardData, headers));
        leaderboardTable.setEnabled(false); // Make the table read-only
        leaderboardTable.setFillsViewportHeight(true);

        // Table Styling
        leaderboardTable.setRowHeight(30);
        leaderboardTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Ellipsis Label
        JLabel ellipsisLabel = new JLabel("...");
        ellipsisLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ellipsisLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tablePanel.add(ellipsisLabel, BorderLayout.SOUTH);

        add(tablePanel, BorderLayout.CENTER);

        // Current User Info Panel
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel userInfoLabel = new JLabel(
                String.format("You are ranked #%d with %d wealth and %d happiness!",
                        currentUserData[0], currentUserData[3], currentUserData[4]));
        userInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInfoPanel.add(userInfoLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton returnButton = new JButton("Return to Homepage");
        returnButton.addActionListener(e -> System.out.println("Returning to homepage..."));
        buttonPanel.add(returnButton);

        userInfoPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Padding between label and button
        userInfoPanel.add(buttonPanel);

        add(userInfoPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // Example Leaderboard Data
        Object[][] leaderboardData = {
                {1, "🌟", "Player1", 100000, 95},
                {2, "👑", "Player2", 85000, 90},
                {3, "🎩", "Player3", 75000, 85},
        };

        // Example Current User Data
        Object[] currentUserData = {10, "🙂", "You", 20000, 70};

        // Create Frame
        JFrame frame = new JFrame("Game Success");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new GameSuccessView(leaderboardData, currentUserData));
        frame.setVisible(true);
    }
}
