import java.util.*;

class Bank {
    String code;
    String address;

    public Bank(String code, String address) {
        this.code = code;
        this.address = address;
    }

    void manages() {
        System.out.println("Bank manages ATM services.");
    }

    void maintains() {
        System.out.println("Bank maintains customer accounts.");
    }
}

class Customer {
    String name;
    String address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    void owns() {
        System.out.println(name + " owns one or more accounts.");
    }
}

class Account {
    String type;
    Customer owner;
    double balance;

    public Account(String type, Customer owner, double balance) {
        this.type = type;
        this.owner = owner;
        this.balance = balance;
    }

    void checkBalance() {
        System.out.println("Balance: " + balance);
    }

    void deposit(double amount) {
        balance += amount;
    }

    boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class DebitCard {
    String cardNo;
    Customer ownedBy;

    public DebitCard(String cardNo, Customer ownedBy) {
        this.cardNo = cardNo;
        this.ownedBy = ownedBy;
    }

    void access() {
        System.out.println("Accessing ATM using card: " + cardNo);
    }
}

class ATM {
    String location;
    Bank managedBy;

    public ATM(String location, Bank managedBy) {
        this.location = location;
        this.managedBy = managedBy;
    }

    void identifies() {
        System.out.println("ATM located at " + location);
    }

    void transactions() {
        System.out.println("ATM handles transactions.");
    }
}

class ATMTransaction {
    String transactionId;
    String date;
    String type;

    public ATMTransaction(String transactionId, String date, String type) {
        this.transactionId = transactionId;
        this.date = date;
        this.type = type;
    }

    void update() {
        System.out.println("Transaction updated: " + transactionId);
    }
}

class WithdrawalTransaction extends ATMTransaction {
    double amount;

    public WithdrawalTransaction(String id, String date, String type, double amount) {
        super(id, date, type);
        this.amount = amount;
    }

    boolean withdrawal(Account account) {
        return account.withdraw(amount);
    }
}

class TransferTransaction extends ATMTransaction {
    double amount;
    Account targetAccount;

    public TransferTransaction(String id, String date, String type, double amount, Account targetAccount) {
        super(id, date, type);
        this.amount = amount;
        this.targetAccount = targetAccount;
    }

    void transfer(Account sourceAccount) {
        if (sourceAccount.withdraw(amount)) {
            targetAccount.deposit(amount);
            System.out.println("Transferred " + amount + " to " + targetAccount.owner.name);
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample Data Setup
        Bank bank = new Bank("B123", "Chattogram");
        Customer c1 = new Customer("Kabya", "Hemsen Lane");
        Customer c2 = new Customer("Niloy", "Chawk Bazar");

        Account acc1 = new Account("Savings", c1, 10000);
        Account acc2 = new Account("Savings", c2, 5000);

        DebitCard card1 = new DebitCard("1234-5678", c1);
        ATM atm = new ATM("GEC Circle", bank);

        System.out.println("----- Welcome to " + bank.code + " ATM -----");
        card1.access();
        atm.identifies();

        while (true) {
            System.out.println("\nSelect option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                acc1.checkBalance();
            } else if (choice == 2) {
                System.out.print("Enter amount to withdraw: ");
                double amt = sc.nextDouble();
                WithdrawalTransaction wt = new WithdrawalTransaction("TXN001", "2025-05-29", "Withdrawal", amt);
                if (wt.withdrawal(acc1)) {
                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else if (choice == 3) {
                System.out.print("Enter amount to transfer: ");
                double amt = sc.nextDouble();
                TransferTransaction tt = new TransferTransaction("TXN002", "2025-05-29", "Transfer", amt, acc2);
                tt.transfer(acc1);
            } else if (choice == 4) {
                System.out.println("Thank you for using ATM.");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}
