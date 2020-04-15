package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int id;
    private String ownerId;
    private List<Transaction> transactions;
    private double balance;

    public Account(int id, String ownerId) {
        this.id = id;
        this.ownerId = ownerId;
        transactions = new ArrayList<>();
        balance = 0.0;
    }

    public int getId() {
        return id;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getOwnerId() {
        return ownerId;
    }

    public double getBalance() {
        for (Transaction transaction: transactions) {
            balance = balance + transaction.getAmount();
        }
        return balance;
    }
}
