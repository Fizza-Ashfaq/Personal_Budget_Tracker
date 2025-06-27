package gui1;

import java.time.LocalDate;

public class ExpenseRecord {
    public String category;
    public double amount;
    public LocalDate date;

    public ExpenseRecord(String category, double amount, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }
}
