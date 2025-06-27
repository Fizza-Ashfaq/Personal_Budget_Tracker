package gui1;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpenseHandler extends Jframe{
    private final ExpenseRecord[] expenses;
    private final MainDashboard  dashboard;

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
}
