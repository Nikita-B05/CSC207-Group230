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

        // Add the stats section at the bottom
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Stats panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JLabel statsHeader = new JLabel("Current Stats:");
        statsHeader.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel statsValues = new JLabel(String.format("Net Worth: %d, Happiness: %d",
                totalNetWorthChange, totalHappinessChange));
        statsValues.setFont(new Font("Arial", Font.PLAIN, 14));

        statsPanel.add(statsHeader);
        statsPanel.add(statsValues);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Homepage");
        backButton.setPreferredSize(new Dimension(120, 30)); // Smaller button size
        buttonPanel.add(backButton);

        // Add stats panel and button panel to the bottom panel
        bottomPanel.add(statsPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button action
        backButton.addActionListener(e -> {
            System.out.println("Back to Homepage clicked");
        });
    }

}
