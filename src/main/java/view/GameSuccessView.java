package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
        JTable leaderboardTable = new JTable(new DefaultTableModel(leaderboardData, headers)) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Return the type for the avatar column as ImageIcon
                if (columnIndex == 1) {
                    return ImageIcon.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };

        // Set up custom cell renderer for the avatar column
        leaderboardTable.getColumnModel().getColumn(1).setCellRenderer(new AvatarCellRenderer());

        leaderboardTable.setEnabled(false); // Make the table read-only
        leaderboardTable.setFillsViewportHeight(true);

        // Table Styling
        leaderboardTable.setRowHeight(30);
        leaderboardTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(600, 250));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Add table panel to main panel
        add(tablePanel, BorderLayout.CENTER);

        // Current User Info Panel
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Avatar Image (replace with your actual file path)
        ImageIcon avatarIcon = new ImageIcon("path/to/currentUserAvatar.jpg");
        JLabel avatarLabel = new JLabel(avatarIcon);
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center avatar

        userInfoPanel.add(avatarLabel); // Add avatar to the user info panel

        // User Info Label
        JLabel userInfoLabel = new JLabel(
                String.format("You are ranked #%d with %d wealth and %d happiness!",
                        currentUserData[0], currentUserData[2], currentUserData[3]));
        userInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInfoPanel.add(userInfoLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton returnButton = new JButton("Return to Homepage");
        returnButton.addActionListener(e -> System.out.println("Returning to homepage..."));
        buttonPanel.add(returnButton);

        userInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        // Padding between label and button
        userInfoPanel.add(buttonPanel);

        add(userInfoPanel, BorderLayout.SOUTH);
    }

    // Custom cell renderer for the Avatar column
    private class AvatarCellRenderer extends JLabel implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
            } else {
                setIcon(null);
            }
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
            return this;
        }
    }

    public static void main(String[] args) {
        // Fetch leaderboard data from database
        Object[][] leaderboardData =
                new GameSuccessView(null, null).fetchLeaderboardDataFromDatabase();

        // Example Current User Data
        Object[] currentUserData = {10, "You", 20000, 70};

        // Create Frame
        JFrame frame = new JFrame("Game Success");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new GameSuccessView(leaderboardData, currentUserData));
        frame.setVisible(true);
    }

    private Object[][] fetchLeaderboardDataFromDatabase() {
        // Fetch leaderboard data from database
        return new Object[][]{
                {1, new ImageIcon("path/to/avatar1.jpg"), "User1", 50000, 80},
                {2, new ImageIcon("path/to/avatar2.jpg"), "User2", 40000, 75},
                {3, new ImageIcon("path/to/avatar3.jpg"), "User3", 30000, 70},
                {4, new ImageIcon("path/to/avatar4.jpg"), "User4", 20000, 65},
                {5, new ImageIcon("path/to/avatar5.jpg"), "User5", 10000, 60},
        };
    }
}
