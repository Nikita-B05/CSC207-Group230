package view;

import entity.Decision;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DecisionLogView extends JPanel {
    private JTable historyTable;
    private JScrollPane scrollPane;

    public DecisionLogView(List<Decision> decisions) {
        setLayout(new BorderLayout());

        // Top Panel for Title
        JLabel titleLabel = new JLabel("Decision Log", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Add spacing
        add(titleLabel, BorderLayout.NORTH);

        // Columns for the decision log table
        String[] columns = {"Time", "Decision", "Your Answer", "Net Worth Change", "Happiness Change"};

        // Prepare data for the table
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        int totalNetWorthChange = 0;
        int totalHappinessChange = 0;

        for (Decision decision : decisions) {
            Object[] row = {decision.getTimestamp(), decision.getDecisionText(), decision.getDecisionResponse(),
                    decision.getNetWorthChange(), decision.getHappinessChange()};
            model.addRow(row);

            // Accumulate stats
            totalNetWorthChange += decision.getNetWorthChange();
            totalHappinessChange += decision.getHappinessChange();
        }

        // Create the decision log table
        historyTable = new JTable(model);
        scrollPane = new JScrollPane(historyTable);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel for Stats and Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Stats section
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel statsHeader = new JLabel("Current Stats:");
        statsHeader.setFont(new Font("Arial", Font.BOLD, 14));
        statsHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel statsValues = new JLabel(String.format("Net Worth: %d, Happiness: %d",
                totalNetWorthChange, totalHappinessChange));
        statsValues.setFont(new Font("Arial", Font.PLAIN, 14));
        statsValues.setAlignmentX(Component.CENTER_ALIGNMENT);

        statsPanel.add(statsHeader);
        statsPanel.add(Box.createVerticalStrut(5)); // Add spacing between header and values
        statsPanel.add(statsValues);

        // Button section
        JButton backButton = new JButton("Return to Homepage");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setPreferredSize(backButton.getMinimumSize()); // Adjust button size to fit text

        // Add stats and button to the bottom panel
        bottomPanel.add(statsPanel);
        bottomPanel.add(Box.createVerticalStrut(10)); // Spacing between stats and button
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button action
        backButton.addActionListener(e -> {
            System.out.println("returning to homepage...");
        });
    }

    public static void main(String[] args) {
        // Create a mock list of decisions
        List<Decision> mockDecisions = new ArrayList<>();
        mockDecisions.add(new Decision(LocalDateTime.now().minusDays(1), "Buy a new car",
                "Yes", -5000, -10));
        mockDecisions.add(new Decision(LocalDateTime.now().minusDays(2), "Take a vacation",
                "No", 0, 20));
        mockDecisions.add(new Decision(LocalDateTime.now(), "Invest in stocks",
                "Yes", 2000, 5));

        // Create the decision log view
        DecisionLogView decisionLogView = new DecisionLogView(mockDecisions);

        // Set up the JFrame
        JFrame frame = new JFrame("Decision Log");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(decisionLogView);
        frame.setVisible(true);
    }
}