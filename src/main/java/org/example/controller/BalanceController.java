package org.example.controller;

import org.example.controller.interfaces.IInfoProvider;

public class BalanceController {

    private IInfoProvider<Double, Integer> balanceProvider;

    public BalanceController(IInfoProvider<Double, Integer> balanceProvider) {
        this.balanceProvider = balanceProvider;
    }

    public Double getBalanceByOwner(Integer accountId){
        return balanceProvider.get(accountId);
    }
}
