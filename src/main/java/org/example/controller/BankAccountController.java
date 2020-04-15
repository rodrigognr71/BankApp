package org.example.controller;

import org.example.controller.interfaces.IAccountOperator;

public class BankAccountController {
    private IAccountOperator withdrawOperator;
    private IAccountOperator depositOperator;

    public BankAccountController(IAccountOperator withdrawOperator, IAccountOperator depositOperator) {
        this.withdrawOperator = withdrawOperator;
        this.depositOperator = depositOperator;
    }

    public void withdraw(int accountId, double amount) {
        this.withdrawOperator.execute(accountId, amount);
    }

    public void deposit(int accountId, double amountToDeposit){
        this.depositOperator.execute(accountId, amountToDeposit);
    }
}

