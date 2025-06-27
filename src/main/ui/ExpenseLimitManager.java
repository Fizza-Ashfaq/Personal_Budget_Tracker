package gui1;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ExpenseLimitManager extends JFrame {
    private final String[] limitCategories = new String[50];
    private final double[] limitAmounts = new double[50];
    private int limitCount = 0;

    private final ExpenseRecord[] expenses;
    private int expenseCount;

    public ExpenseLimitManager(ExpenseRecord[] expenses, int expenseCount) {
        this.expenses = expenses;
        this.expenseCount = expenseCount;

        setTitle("Expense Limit Manager");
        setSize(450, 500); // Increased size for 6 buttons
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1)); // 6 rows for 6 buttons

        JButton setLimitBtn = new JButton("Set Limit");
        JButton editDeleteExpenseBtn = new JButton("Edit/Delete Expense");
        JButton resetBtn = new JButton("Reset All Data");
        JButton summaryBtn = new JButton("Show Summary Report");
        JButton backBtn = new JButton("Back to Menu");
        JButton exitBtn = new JButton("Exit");

        add(setLimitBtn);
        add(editDeleteExpenseBtn);
        add(resetBtn);
        add(summaryBtn);
        add(backBtn);
        add(exitBtn); // Add exit button at the end

        setLimitBtn.addActionListener(e -> setLimit());
        editDeleteExpenseBtn.addActionListener(e -> editOrDeleteExpense());
        resetBtn.addActionListener(e -> resetLimits());
        summaryBtn.addActionListener(e -> showSummary());
        backBtn.addActionListener(e -> dispose());
        exitBtn.addActionListener(e -> System.exit(0)); // Exit entire app
    }

    private void setLimit() {
        String category = JOptionPane.showInputDialog(this, "Enter category:");
        if (category == null || category.trim().isEmpty()) return;

        String amtStr = JOptionPane.showInputDialog(this, "Enter limit for " + category + ":");
        try {
            double amount = Double.parseDouble(amtStr);
            if (amount < 0) throw new NumberFormatException();

            for (int i = 0; i < limitCount; i++) {
                if (limitCategories[i].equalsIgnoreCase(category)) {
                    limitAmounts[i] = amount;
                    showMessage("Limit updated for " + category);
                    return;
                }
            }

            limitCategories[limitCount] = category;
            limitAmounts[limitCount++] = amount;
            showMessage("Limit set for " + category);
        } catch (NumberFormatException e) {
            showMessage("Invalid number.");
        }
    }

    private void resetLimits() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to reset all data (limits and expenses)?",
                "Confirm Reset", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Reset limits
            limitCount = 0;

            // Reset expenses
            for (int i = 0; i < expenseCount; i++) {
                expenses[i] = null;
            }
            expenseCount = 0;

            showMessage("All limits and expenses have been reset.");
        }
    }


    private void showSummary() {
        if (limitCount == 0 && expenseCount == 0) {
            showMessage("No limits or expenses found.");
            return;
        }

        StringBuilder summary = new StringBuilder("Expense & Limit Summary:\n\n");

        double totalLimit = 0;
        for (int i = 0; i < limitCount; i++) {
            totalLimit += limitAmounts[i];
            summary.append("• Limit - ").append(limitCategories[i])
                   .append(": ").append(limitAmounts[i]).append("\n");
        }

        double totalExpense = 0;
        for (int i = 0; i < expenseCount; i++) {
            totalExpense += expenses[i].amount;
        }

        summary.append("\nTotal Limit: ").append(totalLimit)
               .append("\nTotal Expenses: ").append(totalExpense);

        
        showMessage(summary.toString());
    }


    private void editOrDeleteExpense() {
        if (expenseCount == 0) {
            showMessage("No expenses.");
            return;
        }

        StringBuilder builder = new StringBuilder("Choose index to edit/delete:\n");
        for (int i = 0; i < expenseCount; i++) {
            builder.append(i).append(": ").append(expenses[i].category).append(", ")
                    .append(expenses[i].amount).append(", ").append(expenses[i].date).append("\n");
        }

        String input = JOptionPane.showInputDialog(this, builder.toString());
        if (input == null) return;
        try {
            int index = Integer.parseInt(input);
            if (index < 0 || index >= expenseCount) throw new NumberFormatException();

            ExpenseRecord exp = expenses[index];
            String[] options = {"Edit", "Delete", "Cancel"};
            int choice = JOptionPane.showOptionDialog(this,
                    "Edit or delete?\n" + exp.category + ", " + exp.amount + ", " + exp.date,
                    "Edit/Delete", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                String amtStr = JOptionPane.showInputDialog(this, "New amount:", exp.amount);
                double amt = Double.parseDouble(amtStr);
                if (amt < 0) throw new NumberFormatException();

                String cat = JOptionPane.showInputDialog(this, "New category:", exp.category);
                if (cat == null || cat.trim().isEmpty()) return;

                LocalDate date = promptForDate("New date (yyyy-MM-dd):");
                if (date == null) return;

                exp.amount = amt;
                exp.category = cat;
                exp.date = date;
                showMessage("Expense updated.");
            } else if (choice == 1) {
                for (int i = index; i < expenseCount - 1; i++) {
                    expenses[i] = expenses[i + 1];
                }
                expenseCount--;
                showMessage("Expense deleted.");
            }
        } catch (Exception e) {
            showMessage("Invalid input.");
        }
    }

    private LocalDate promptForDate(String message) {
        String dateStr = JOptionPane.showInputDialog(this, message);
        if (dateStr == null) return null;
        try {
            return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            showMessage("Invalid date format. Use yyyy-MM-dd.");
            return null;
        }
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
