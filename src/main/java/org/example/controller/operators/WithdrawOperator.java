package org.example.controller.operators;

import org.example.controller.BalanceController;
import org.example.controller.interfaces.IAccountOperator;
import org.example.controller.providers.AccountBalanceProvider;
import org.example.model.Account;
import org.example.model.Transaction;
import org.example.model.TransactionType;
import org.example.model.interfaces.IDataSaver;
import org.example.model.persistence.DataInMemory;

public class WithdrawOperator implements IAccountOperator {

    private IDataSaver dataSaver;
    private AccountBalanceProvider accountBalanceProvider;

    public WithdrawOperator(IDataSaver dataSaver, AccountBalanceProvider accountBalanceProvider) {
        this.dataSaver = dataSaver;
        this.accountBalanceProvider = accountBalanceProvider;
    }

    @Override
    public boolean execute(int id, double amount) {
        Transaction withdraw = new Transaction(TransactionType.WITHDRAW, amount * -1, id);
        dataSaver.addTransaction(withdraw);
        return true;
    }
}
