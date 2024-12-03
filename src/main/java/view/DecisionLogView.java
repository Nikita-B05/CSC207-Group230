package view;

import data_access.MongoDBUserDataAccessObject;
import entity.Decision;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import interface_adapter.decision_log.DecisionLogController;
import interface_adapter.decision_log.DecisionLogViewModel;
import interface_adapter.decision_log.DecisionLogState;
import interface_adapter.homepage.HomepageController;

public class DecisionLogView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "decisionLog";
    private final DecisionLogViewModel decisionLogViewModel;
    private DecisionLogController decisionLogController;

    // UI Components
    private JTable historyTable;
    private JLabel titleLabel;
    private JLabel netWorthLabel;
    private JLabel happinessLabel;
    private JButton backButton;
    private DefaultTableModel tableModel;

    public DecisionLogView(DecisionLogViewModel decisionLogViewModel) {
        this.decisionLogViewModel = decisionLogViewModel;
        this.decisionLogViewModel.addPropertyChangeListener(this);

        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        // Initialize title
        titleLabel = new JLabel(DecisionLogViewModel.TITLE, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Initialize table
        String[] columns = {"Age", "Decision", "Your Response", "Net Worth Change", "Happiness Change"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        historyTable = new JTable(tableModel);

        // Initialize stats labels
        netWorthLabel = new JLabel();
        netWorthLabel.setFont(new Font("Arial", Font.BOLD, 14));
        happinessLabel = new JLabel();
        happinessLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Initialize button
        backButton = new JButton("Return to Homepage");
        backButton.addActionListener(this);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Create a panel for the title with vertical spacing
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0)); // Add more vertical padding
        
        // Center the title both horizontally and vertically
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);

        // Setup table
        add(new JScrollPane(historyTable), BorderLayout.CENTER);

        // Setup bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Setup stats panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        
        netWorthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        happinessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add padding between stats
        statsPanel.add(netWorthLabel);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        statsPanel.add(happinessLabel);
        
        // Setup button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        
        bottomPanel.add(statsPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(bottomPanel, BorderLayout.SOUTH);

        // Initial update
        updateTableData();
    }

    private void updateTableData() {
        tableModel.setRowCount(0);
        
        DecisionLogState state = decisionLogViewModel.getState();
        List<Decision> decisions = state.getDecisions();
        
        for (Decision decision : decisions) {
            tableModel.addRow(new Object[]{
                String.format("%d", decision.getAge()),
                decision.getDecisionText(),
                decision.getResponse(),
                String.format("$%.2f", decision.getNetWorthChange()),
                String.format("%d", decision.getHappinessChange())
            });
        }
    }

    public void setDecisionLogController(DecisionLogController decisionLogController) {
        this.decisionLogController = decisionLogController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            decisionLogController.switchToHomepageView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateTableData();
        DecisionLogState state = decisionLogViewModel.getState();
        if (state.isDarkMode()) {
            ColorTheme.applyDarkMode(this);
        }
        else {
            ColorTheme.applyLightMode(this);
        }
    }

    public String getViewName() {
        return viewName;
    }
}
