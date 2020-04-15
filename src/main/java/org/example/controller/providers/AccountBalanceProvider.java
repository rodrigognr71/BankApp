package org.example.controller.providers;

import org.example.controller.interfaces.IInfoProvider;
import org.example.model.interfaces.IDataSaver;

public class AccountBalanceProvider implements IInfoProvider<Double, Integer> {

    private IDataSaver dataSaver;

    public AccountBalanceProvider(IDataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    @Override
    public Double get(Integer accountId) {
        return dataSaver.getBalanceByAccount(accountId);
    }
}
