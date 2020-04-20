package org.example.controller.providers;

import org.example.controller.interfaces.IInfoProvider;
import org.example.model.Transaction;
import org.example.model.interfaces.IDataSaver;

public class TransactionProvider implements IInfoProvider<Transaction, Integer> {

    private IDataSaver dataSaver;

    public TransactionProvider(IDataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    @Override
    public Transaction get(Integer accountId) {
        return dataSaver.getTransactionByAccountId(accountId);
    }
}
