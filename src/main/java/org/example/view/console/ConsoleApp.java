package org.example.view.console;

import org.example.controller.BalanceController;
import org.example.controller.BankAccountController;
import org.example.controller.ControllerManager;
import org.example.controller.OwnerController;
import org.example.controller.providers.AccountBalanceProvider;
import org.example.model.Account;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private BankAccountController bankAccountController;
    private BankAccountController depositController;
    private OwnerController ownerController;
    private BalanceController balanceProvider;

    public static void main(String[] args) {
        new ConsoleApp().run();
    }

    public ConsoleApp() {
        ControllerManager controllerManager = new ControllerManager();
        bankAccountController = controllerManager.getAccountController();
        depositController = controllerManager.getDepositController();
        ownerController = controllerManager.getOwnerController();
        balanceProvider = controllerManager.getBalanceController();

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
        if(bankAccountController.equals(false)) {
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
        System.out.println("Your balance is: " + balanceProvider.getBalanceByOwner(accountId));
    }
}
