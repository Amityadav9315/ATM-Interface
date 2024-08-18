import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BankAccount {
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
        transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Money Deposited: " + amount);
        } else {
            System.out.println("Invalid amount for deposit!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount);
            System.out.println("Money Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal!");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transferred to another account: " + amount);
            System.out.println("Money Transferred to another account: " + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount for transfer!");
        }
    }

    public void showTransactionHistory() {
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;
    private List<BankAccount> accounts;

    public ATM(BankAccount account, List<BankAccount> accounts) {
        this.account = account;
        this.accounts = accounts;
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("******** WELCOME TO ATM ********");
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Withdraw Cash");
        System.out.println("4. Transfer to another account");
        System.out.println("5. Show Transaction History");
        System.out.println("6. Exit");
    }

    public void start() {
        int choice;
        do {
            showMenu();
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear the invalid input
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    showTransactionHistory();
                    break;
                case 6:
                    System.out.println("Exiting ATM. Thank You!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option!");
            }
        } while (choice != 6);
        scanner.close();
    }

    private void checkBalance() {
        System.out.println("Current Balance: " + account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid amount.");
            scanner.next(); // Clear the invalid input
            System.out.print("Enter deposit amount: ");
        }
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid amount.");
            scanner.next(); // Clear the invalid input
            System.out.print("Enter withdrawal amount: ");
        }
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    private void transfer() {
        System.out.print("Enter recipient's account number: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid account number.");
            scanner.next(); // Clear the invalid input
            System.out.print("Enter recipient's account number: ");
        }
        int recipientAccountNumber = scanner.nextInt();
        System.out.print("Enter transfer amount: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid amount.");
            scanner.next(); // Clear the invalid input
            System.out.print("Enter transfer amount: ");
        }
        double amount = scanner.nextDouble();
        BankAccount recipientAccount = getAccount(recipientAccountNumber);
        if (recipientAccount != null) {
            account.transfer(recipientAccount, amount);
        } else {
            System.out.println("Recipient account not found!");
        }
    }

    private void showTransactionHistory() {
        account.showTransactionHistory();
    }

    private BankAccount getAccount(int accountNumber) {
        if (accountNumber >= 0 && accountNumber < accounts.size()) {
            return accounts.get(accountNumber);
        }
        return null;
    }

    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount(1000)); // Adding a dummy account for testing
        accounts.add(new BankAccount(2000)); // Adding another dummy account for testing
        BankAccount myAccount = new BankAccount(500);
        ATM atm = new ATM(myAccount, accounts);
        atm.start();
    }
}



