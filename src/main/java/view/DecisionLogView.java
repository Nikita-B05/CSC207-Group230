package view;

import data_access.MongoDBUserDataAccessObject;
import entity.Decision;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import interface_adapter.asset_manager.AssetManagerViewModel;
import interface_adapter.decision_log.DecisionLogController;
import interface_adapter.decision_log.DecisionLogViewModel;
import interface_adapter.decision_log.DecisionLogState;
import interface_adapter.dark_mode.DarkModeController;

public class DecisionLogView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "decisionLog";
    private DecisionLogController decisionLogController;
    private final DecisionLogViewModel decisionLogViewModel;

    private JTable historyTable;
    private JScrollPane scrollPane;

    private final JLabel statsValues;
    private final JButton backButton;
    private MongoDBUserDataAccessObject userDataAccess;

    public DecisionLogView(DecisionLogViewModel decisionLogViewModel, MongoDBUserDataAccessObject userDataAccess) {
        this.decisionLogViewModel = decisionLogViewModel;
        this.userDataAccess = userDataAccess;

        // Register property change listener
        this.decisionLogViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // Top Panel for Title
        JLabel titleLabel = new JLabel(AssetManagerViewModel.TITLE, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Add spacing
        add(titleLabel, BorderLayout.NORTH);

        // Columns for the decision log table
        String[] columns = {"Age", "Decision", "Your Response", "Net Worth Change", "Happiness Change"};

        // Prepare data for the table
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        updateTableModel(model, decisionLogViewModel.getState().getDecisions());

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

        statsValues = new JLabel(getStatsText());
        statsValues.setFont(new Font("Arial", Font.PLAIN, 14));
        statsValues.setAlignmentX(Component.CENTER_ALIGNMENT);

        statsPanel.add(statsHeader);
        statsPanel.add(Box.createVerticalStrut(5)); // Add spacing between header and values
        statsPanel.add(statsValues);

        // Button section
        backButton = new JButton("Return to Homepage");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setPreferredSize(backButton.getMinimumSize()); // Adjust button size to fit text
        backButton.addActionListener(this); // Add action listener

        // Add stats and button to the bottom panel
        bottomPanel.add(statsPanel);
        bottomPanel.add(Box.createVerticalStrut(10)); // Spacing between stats and button
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setDecisionLogController(DecisionLogController decisionLogController) {
        this.decisionLogController = decisionLogController;
    }

    private void updateTheme(boolean isDarkMode) {
        if (isDarkMode) {
            ColorTheme.applyDarkMode(this);
        } else {
            ColorTheme.applyLightMode(this);
        }

        SwingUtilities.updateComponentTreeUI(this);
    }

    private void updateTableModel(DefaultTableModel model, List<Decision> decisions) {
        model.setRowCount(0); // Clear existing rows
        DecisionLogState state = decisionLogViewModel.getState();

        for (Decision decision : decisions) {
            Object[] row = {
                    state.getAge(),
                    decision.getDecisionText(),
                    decision.getResponse(),
                    decision.getNetWorthChange(),
                    decision.getHappinessChange()
            };
            model.addRow(row);
        }
    }


    private String getStatsText() {
        return String.format("Net Worth: %.2f, Happiness: %d",
                decisionLogViewModel.getState().getTotalNetWorthChange(),
                decisionLogViewModel.getState().getTotalHappinessChange());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            String username = decisionLogViewModel.getState().getUsername();
            decisionLogController.switchToHomepageView();
        }
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateTableModel((DefaultTableModel) historyTable.getModel(), decisionLogViewModel.getState().getDecisions());
        statsValues.setText(getStatsText());
        updateTheme(decisionLogViewModel.getState().isDarkMode());
    }
}
