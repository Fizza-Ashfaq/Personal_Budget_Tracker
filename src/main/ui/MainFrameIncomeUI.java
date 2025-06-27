package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainFrameIncomeUI {

    private JFrame frame;
    private JPanel sidebar, contentPanel;
    private boolean isExpanded = false;
    private JButton btnToggle;
    private IncomeRecord[] incomes = new IncomeRecord[100];
    private int incomeCount = 0;

    public MainFrameIncomeUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Income - Budget Tracker");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(230, 245, 230));

        // Content panel
        contentPanel = new JPanel();
        contentPanel.setBounds(150, 80, 800, 550);
        contentPanel.setBackground(new Color(230, 245, 230));
        contentPanel.setLayout(null);
        frame.getContentPane().add(contentPanel);

        // Sidebar
        sidebar = new JPanel();
        sidebar.setBackground(Color.BLACK);
        sidebar.setBounds(0, 0, 43, 700);
        sidebar.setLayout(null);
        frame.getContentPane().add(sidebar);

        btnToggle = new JButton("☰");
        btnToggle.setBounds(0, 0, 43, 25);
        btnToggle.setForeground(Color.PINK);
        btnToggle.setBackground(Color.BLACK);
        sidebar.add(btnToggle);

        JButton btnAddIncome = new JButton("Add Income");
        styleSidebarButton(btnAddIncome, 40);

        JButton btnViewBalance = new JButton("View Balance");
        styleSidebarButton(btnViewBalance, 90);

        JButton btnBack = new JButton("Back to Menu");
        styleSidebarButton(btnBack, 140);

        JButton btnExit = new JButton("Exit");
        styleSidebarButton(btnExit, 190);

        sidebar.add(btnAddIncome);
        sidebar.add(btnViewBalance);
        sidebar.add(btnBack);
        sidebar.add(btnExit);

        btnAddIncome.setVisible(false);
        btnViewBalance.setVisible(false);
        btnBack.setVisible(false);
        btnExit.setVisible(false);

        btnToggle.addActionListener(e -> {
            isExpanded = !isExpanded;
            sidebar.setBounds(0, 0, isExpanded ? 150 : 43, 700);
            btnAddIncome.setVisible(isExpanded);
            btnViewBalance.setVisible(isExpanded);
            btnBack.setVisible(isExpanded);
            btnExit.setVisible(isExpanded);
        });

        btnAddIncome.addActionListener(e -> showAddIncomeUI());
        btnViewBalance.addActionListener(e -> showBalanceCard());
        btnBack.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(() -> new MainDashboard().setVisible(true));
        });
        btnExit.addActionListener(e -> System.exit(0));

        showWelcomeCard();
        frame.setVisible(true);
    }

    private void styleSidebarButton(JButton btn, int y) {
        btn.setBounds(10, y, 130, 35);
        btn.setForeground(new Color(230, 245, 230));
        btn.setBackground(Color.BLACK);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
    }

    private void showWelcomeCard() {
        contentPanel.removeAll();
        JLabel welcome = new JLabel("<html><div style='text-align:center;'>👋 Welcome to Income Section</div></html>");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setBounds(200, 100, 400, 100);
        contentPanel.add(welcome);
        contentPanel.repaint();
        contentPanel.revalidate();
    }

    private void showAddIncomeUI() {
        contentPanel.removeAll();

        JLabel title = new JLabel("Add Income Entry");
        title.setBounds(20, 20, 300, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        contentPanel.add(title);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 70, 100, 25);
        contentPanel.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(120, 70, 200, 25);
        contentPanel.add(amountField);

        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setBounds(20, 110, 100, 25);
        contentPanel.add(sourceLabel);

        JTextField sourceField = new JTextField();
        sourceField.setBounds(120, 110, 200, 25);
        contentPanel.add(sourceField);

        JButton submit = new JButton("Submit");
        submit.setBounds(120, 160, 100, 30);
        contentPanel.add(submit);

        submit.addActionListener(ev -> {
            try {
                String amountStr = amountField.getText().trim();
                if (amountStr.isEmpty()) return;
                double amount = Double.parseDouble(amountStr);
                if (amount < 0) throw new NumberFormatException();

                String source = sourceField.getText().trim();
                if (source.isEmpty()) return;

                LocalDate date = promptForDate("Enter income date (yyyy-MM-dd) or leave blank for today:");
                if (date == null) return;

                incomes[incomeCount++] = new IncomeRecord(source, amount, date);
                JOptionPane.showMessageDialog(frame, "✅ Income added.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid number.");
            }
        });

        contentPanel.repaint();
        contentPanel.revalidate();
    }

    private void showBalanceCard() {
        contentPanel.removeAll();

        double total = 0;
        for (int i = 0; i < incomeCount; i++) {
            total += incomes[i].amount;
        }

        JLabel balanceCard = new JLabel("<html><div style='text-align:center;'>💰<br>Total Income<br>₹" + total + "</div></html>");
        balanceCard.setHorizontalAlignment(SwingConstants.CENTER);
        balanceCard.setOpaque(true);
        balanceCard.setBackground(new Color(230, 245, 230));
        balanceCard.setFont(new Font("SansSerif", Font.BOLD, 20));
        balanceCard.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        balanceCard.setBounds(200, 100, 400, 150);
        contentPanel.add(balanceCard);

        contentPanel.repaint();
        contentPanel.revalidate();
    }

    private LocalDate promptForDate(String msg) {
        String input = JOptionPane.showInputDialog(frame, msg);
        if (input == null) return null;
        if (input.trim().isEmpty()) return LocalDate.now();
        try {
            return LocalDate.parse(input.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid date format.");
            return promptForDate(msg);
        }
    }
}
