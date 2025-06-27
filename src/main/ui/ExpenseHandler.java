package main.ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpenseHandler extends JFrame {
    private final ExpenseRecord[] expenses;
    private final MainDashboard dashboard;

    public ExpenseHandler(ExpenseRecord[] expenses, MainDashboard dashboard) {
        this.expenses = expenses;
        this.dashboard = dashboard;

        setTitle("Expense Manager");
        setSize(450, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        JButton addBtn = new JButton("Add Expense");
        JButton reportBtn = new JButton("Category Report");
        JButton sortDateBtn = new JButton("Sort by Date");
        JButton sortAmountBtn = new JButton("Sort by Amount");
        JButton backBtn = new JButton("Back to Menu");
        JButton exitBtn = new JButton("Exit");

        add(addBtn);
        add(reportBtn);
        add(sortDateBtn);
        add(sortAmountBtn);
        add(backBtn);
        add(exitBtn);

        addBtn.addActionListener(e -> addExpenseDialog());
        reportBtn.addActionListener(e -> showMessage(getCategoryReport()));
        sortDateBtn.addActionListener(e -> {
            sortExpensesByDateOrAmount(true);
            showMessage(getSortedExpenseList());
        });
        sortAmountBtn.addActionListener(e -> {
            sortExpensesByDateOrAmount(false);
            showMessage(getSortedExpenseList());
        });
        backBtn.addActionListener(e -> dispose());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void addExpenseDialog() {
        String category = JOptionPane.showInputDialog(this, "Enter expense category:");
        if (category == null || category.trim().isEmpty()) return;

        String amtStr = JOptionPane.showInputDialog(this, "Enter amount:");
        try {
            double amount = Double.parseDouble(amtStr);
            if (amount < 0) throw new NumberFormatException();

            LocalDate date = promptForDate("Enter date (yyyy-MM-dd) or leave blank for today:");
            if (date == null) return;

            addExpense(category, amount, date);
            showMessage("Expense added.");
        } catch (NumberFormatException e) {
            showMessage("Invalid amount.");
        }
    }

    private LocalDate promptForDate(String msg) {
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

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void addExpense(String category, double amount, LocalDate date) {
        int count = dashboard.getSharedExpenseCount();
        expenses[count] = new ExpenseRecord(category, amount, date);
        dashboard.incrementExpenseCount();
    }

    public String getCategoryReport() {
        int count = dashboard.getSharedExpenseCount();
        if (count == 0) return "No expenses.";
        StringBuilder report = new StringBuilder("Category-wise Report:\n");
        boolean[] printed = new boolean[count];
        for (int i = 0; i < count; i++) {
            if (!printed[i]) {
                String cat = expenses[i].category;
                double total = expenses[i].amount;
                printed[i] = true;
                for (int j = i + 1; j < count; j++) {
                    if (!printed[j] && expenses[j].category.equalsIgnoreCase(cat)) {
                        total += expenses[j].amount;
                        printed[j] = true;
                    }
                }
                report.append("• ").append(cat).append(": ").append(total).append("\n");
            }
        }
        return report.toString();
    }

    public String getSortedExpenseList() {
        int count = dashboard.getSharedExpenseCount();
        if (count == 0) return "No expenses.";
        StringBuilder list = new StringBuilder("Sorted Expenses:\n");
        for (int i = 0; i < count; i++) {
            ExpenseRecord ex = expenses[i];
            list.append("• ").append(ex.date).append(" | ").append(ex.category).append(" - ").append(ex.amount).append("\n");
        }
        return list.toString();
    }

    public void sortExpensesByDateOrAmount(boolean byDate) {
        int count = dashboard.getSharedExpenseCount();
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                boolean condition = byDate
                        ? expenses[i].date.isAfter(expenses[j].date)
                        : expenses[i].amount > expenses[j].amount;
                if (condition) {
                    ExpenseRecord temp = expenses[i];
                    expenses[i] = expenses[j];
                    expenses[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new ExpenseHandler(new ExpenseRecord[100], new MainDashboard()).setVisible(true)
        );
    }
}
