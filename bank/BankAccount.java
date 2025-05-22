package bank;

import java.util.Scanner;

public class BankAccount {

    // The following can only be accessed through public methods.

    private double balance;
    private String accountHolder;
    private String accountNumber;

    // Constructor method that initializes the starting balance to zero

    public BankAccount() {

        this.balance = 0.0;
    }

    // Getters

    public String getAccountHolder() {

        return accountHolder;
    }

    public String getAccountNumber() {

        return accountNumber;
    }

    // Method to access the balance

    public double getBalance() {

        return balance;
    }

    // Constructor for opening an account

    public BankAccount(String accountHolder, String accountNumber) {

        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = 0.0;

    }

    // Deposit method with exception handling

    public void deposit(double amount) throws IllegalArgumentException {

        if (amount <= 0) {

            throw new IllegalArgumentException("Deposit amount must be positive");

        }

        balance += amount;
    }

    // Withdraw method with exception handling

    public void withdraw(double amount) throws IllegalArgumentException {

        if (amount <= 0) {

            throw new IllegalArgumentException("The amount must be positive");
        }

        if (balance < amount) {

            throw new IllegalArgumentException("Insufficient funds:\n Available balance " + getBalance());

        }

        balance -= amount;
    }

    public void withdraw(double amount, String message) {

        withdraw(amount); // Calling the original withdraw method

        System.out.println(message);
    }

    // Check balance method

    public double checkBalance() {

        return balance;

    }

    // Method for multiple transactions (not to overload my main method)

    public static void multipleTransactions(BankAccount account, Scanner input) {
        char userChoice;
        do {
            System.out.println("\nDo you want to perform another transaction? D(Deposit) - W(Withdraw) - C(Cancel)");
            userChoice = input.next().toUpperCase().charAt(0);

            switch (userChoice) {
                case 'D':
                    System.out.println("Enter your deposit amount:");
                    double newDepositAmount = input.nextDouble();
                    try {
                        account.deposit(newDepositAmount);
                        System.out.println("Your new balance is: " + account.getBalance());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 'W':
                    System.out.println("Enter your withdrawal amount:");
                    double newWithdrawalAmount = input.nextDouble();
                    try {
                        account.withdraw(newWithdrawalAmount);
                        System.out.println("Your new balance is: " + account.getBalance());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 'C':
                    System.out.println("Transaction canceled.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose D, W, or C.");
            }
        } while (userChoice != 'C');
    }

    // Main method

    public static void main(String[] args) {

        BankAccount account = new BankAccount("Raynardt Louw", "123456789");

        // Welcome message for the account holder
        System.out.println("Welcome " + account.getAccountHolder());

        // Displaying the accountholders initial balance
        System.out.println("Your initial balance is: " + account.getBalance());

        Scanner input = new Scanner(System.in);

        System.out.print("How much do you want to deposit: ");

        double depositAmount = input.nextDouble();

        try {
            account.deposit(depositAmount);
            System.out.println("Your balance is: " + account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Deposit amount must be positive: " + e.getMessage());
        }

        BankAccount.multipleTransactions(account, input);

    }

}