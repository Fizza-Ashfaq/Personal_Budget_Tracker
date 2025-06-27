package main.ui;
import java.time.LocalDate;

public class IncomeRecord{

    public String source;
    public double amount;
    public LocalDate date;

    public IncomeRecord(String source, double amount, LocalDate date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
    }


}