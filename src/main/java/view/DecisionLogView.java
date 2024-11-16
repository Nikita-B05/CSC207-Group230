package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DecisionHistoryPage extends JPanel {
    private JTable historyTable;
    private JScrollPane scrollPane;

    public DecisionHistoryPage(List<Decision> decisions) {
        setLayout(new BorderLayout());
        String[] columns = {"Timestamp", "Decision", "Net Worth Change", "Happiness Change", "Quality of Life Change"};
        // Quality of Life can be Health

        // Prepare data for the table
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Decision decision : decisions) {
            Object[] row = {decision.getTimestamp(), decision.getDecisionText(),
                    decision.getNetWorthChange(), decision.getHappinessChange()};
            model.addRow(row);
        }

        historyTable = new JTable(model);
        scrollPane = new JScrollPane(historyTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add a button to go back to the homepage
        JButton backButton = new JButton("Back to Homepage");
        backButton.addActionListener(e -> {
            // Logic to navigate back to the homepage (this could be implemented by switching panels)
            System.out.println("Back to Homepage clicked");
            // Assuming you have a JFrame that contains this panel, you can set the visible panel back to homepage
        });
        add(backButton, BorderLayout.SOUTH);
    }
}
