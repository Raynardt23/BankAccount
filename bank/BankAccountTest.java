package bank;

import bank.BankAccount;
import java.util.Scanner;

public class BankAccountTest {

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
