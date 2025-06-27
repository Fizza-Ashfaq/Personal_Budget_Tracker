package main.ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainFrameIncomeUI extends JFrame {
    protected IncomeRecord[] incomes = new IncomeRecord[100];
    protected int incomeCount = 0;

    public MainFrameIncomeUI() {
        setTitle("Budget Tracker - Income Features");
        setSize(400, 300); 
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1)); 

        JButton addBtn = new JButton("Add Income");
        JButton balanceBtn = new JButton("View Balance");
        JButton backBtn = new JButton("Back to Menu");
        JButton exitBtn = new JButton("Exit");

        add(addBtn);
        add(balanceBtn);
        add(backBtn);
        add(exitBtn);

        addBtn.addActionListener(e -> addIncome());
        balanceBtn.addActionListener(e -> showBalance());
        backBtn.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new MainDashboard().setVisible(true));
        });
        exitBtn.addActionListener(e -> System.exit(0));
    }

    protected void addIncome() {
        try {
            String amountStr = JOptionPane.showInputDialog(this, "Enter income amount:");
            if (amountStr == null || amountStr.trim().isEmpty()) return;
            double amount = Double.parseDouble(amountStr);
            if (amount < 0) throw new NumberFormatException();

            String source = JOptionPane.showInputDialog(this, "Enter income source:");
            if (source == null || source.trim().isEmpty()) return;

            LocalDate date = promptForDate("Enter income date (yyyy-MM-dd) or leave blank for today:");
            if (date == null) return;

            incomes[incomeCount++] = new IncomeRecord(source, amount, date);
            showMessage("Income added.");
        } catch (NumberFormatException e) {
            showMessage("Invalid number.");
        }
    }

    protected void showBalance() {
        double income = 0;
        for (int i = 0; i < incomeCount; i++) {
            income += incomes[i].amount;
        }
        showMessage("💰 Total Income: " + income);
    }

    protected LocalDate promptForDate(String msg) {
        String input = JOptionPane.showInputDialog(this, msg);
        if (input == null) return null;
        if (input.trim().isEmpty()) return LocalDate.now();
        try {
            return LocalDate.parse(input.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            showMessage("Invalid date format.");
            return promptForDate(msg);
        }
    }

    protected void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}