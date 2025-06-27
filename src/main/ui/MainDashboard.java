package main.ui;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {
    private final ExpenseRecord[] sharedExpenses = new ExpenseRecord[100];
    private int sharedExpenseCount = 0;

    public MainDashboard() {
        setTitle("Budget Tracker - Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JButton incomeBtn = new JButton("Income Features");
        JButton expenseBtn = new JButton("Expense Features");
        JButton limitsBtn = new JButton("Limit / Edit / Summary");
        JButton exitBtn = new JButton("Exit");

        add(incomeBtn);
        add(expenseBtn);
        add(limitsBtn);
        add(exitBtn);

        incomeBtn.addActionListener(e -> {
            MainFrameIncomeUI incomeUI = new MainFrameIncomeUI();
//            incomeUI.setVisible(true);
        });

        expenseBtn.addActionListener(e -> {
            ExpenseHandler expenseUI = new ExpenseHandler(sharedExpenses, this);
            expenseUI.setVisible(true);
        });

        limitsBtn.addActionListener(e -> {
            ExpenseLimitManager limitUI = new ExpenseLimitManager(sharedExpenses, sharedExpenseCount);
            limitUI.setVisible(true);
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }

    public int getSharedExpenseCount() {
        return sharedExpenseCount;
    }

    public void incrementExpenseCount() {
        sharedExpenseCount++;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainDashboard().setVisible(true));
    }
}
