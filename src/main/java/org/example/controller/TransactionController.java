package org.example.controller;

import org.example.controller.interfaces.IInfoProvider;
import org.example.model.Transaction;

public class TransactionController {

    private IInfoProvider<Transaction, Integer> transactionProvider;

    public TransactionController(IInfoProvider<Transaction, Integer> transactionProvider) {
        this.transactionProvider = transactionProvider;
    }

    public Transaction getTransactionProvider(Integer accountId) {
        return transactionProvider.get(accountId);
    }
}
