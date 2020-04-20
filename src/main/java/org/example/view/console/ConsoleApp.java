package org.example.view.console;

import org.example.controller.*;
import org.example.controller.operators.WithdrawOperator;
import org.example.controller.providers.AccountBalanceProvider;
import org.example.model.Account;
import org.example.model.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private BankAccountController bankAccountController;
    private BankAccountController depositController;
    private OwnerController ownerController;
    private BalanceController balanceProvider;
    private TransactionController transactionController;

    public static void main(String[] args) {
        new ConsoleApp().run();
    }

    public ConsoleApp() {
        ControllerManager controllerManager = new ControllerManager();
        bankAccountController = controllerManager.getAccountController();
        depositController = controllerManager.getDepositController();
        ownerController = controllerManager.getOwnerController();
        balanceProvider = controllerManager.getBalanceController();
        transactionController = controllerManager.getTransactionProvider();

    }

    public void run() {
        String ownerName = "jperez";

        // TODO Change this to use DTOs
        List<Account> accounts = ownerController.getAccountsByOwner(ownerName);

        // TODO Add InfoProvider to replace this logic
        Account account = accounts.get(0);

        printWelcomeMessage(ownerName, account.getId());
        showMainMenu();
        selectOption(account.getId());
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private void printWelcomeMessage(String name, int accountId) {
        // Welcome to the BankApp
        print("Welcome " + name);
        print("Your current account is " + accountId);
    }

    private void showMainMenu() {
        // Main Menu
        print(System.lineSeparator());
        print("********************");
        print("(W) Withdraw");
        print("(D) Deposit");
        print("(B) My Balance");
        print("(T) My Transactions");
        print("(E) Exit");
        print("********************");
    }

    private void selectOption(int accountId) {
        // Press a key to select an option
        try (Scanner scanner = new Scanner(System.in)){
            boolean closeApp = false;
            char option;
            while (true) {
                System.out.print("Select an option: ");
                option = getSelectedOption(scanner);
                switch (option) {
                    case 'A':
                        showMainMenu();
                        break;
                    case 'E':
                        print("Exit...");
                        closeApp = true;
                        break;
                    case 'B':
                        myBalance(accountId);
                        break;
                    case 'W':
                        withdraw(scanner, accountId);
                        break;
                    case 'D':
                        deposit(scanner, accountId);
                        break;
                    case 'T':
                        myTransaction(accountId);
                        break;
                    default:
                        print(option + " is an invalid option");
                        break;
                }

                // Is running
                if (closeApp) {
                    break;
                }

                print("(A) Show All options");
            }
        }
    }

    private char getSelectedOption(Scanner scanner) {
        return scanner.next().trim().toUpperCase().charAt(0);
    }

    private void withdraw(Scanner scanner, int accountId) {
        System.out.print("Enter amount you need to withdraw: ");
        double amount = scanner.nextDouble();
        bankAccountController.withdraw(accountId, amount);
        if(balanceProvider.getBalanceByOwner(accountId) < amount) {
            System.out.println("You do not have amount enough to do the transaction");
        }else System.out.println("successful withdraw operation");
    }

    private void deposit(Scanner scanner, int accountId) {
        System.out.print("Enter amount you will deposit: ");
        double amount = scanner.nextDouble();
        depositController.deposit(accountId, amount);
        System.out.println("successful deposit operation");
    }

    private void myBalance(int accountId){
        double balance = balanceProvider.getBalanceByOwner(accountId);
        System.out.println("Your balance is: " + balance);
    }

    private void myTransaction(int accountId){
        Transaction transaction = transactionController.getTransactionProvider(accountId);
        System.out.println("Your transactions:");
        System.out.println(transaction);
    }
}
