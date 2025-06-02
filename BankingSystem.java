import java.util.Scanner;
import java.util.ArrayList;

// Customer class
class Customer {
    int id;
    String name;
    double balance;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0;
    }

    public void display() {
        System.out.println("Customer ID: " + id + ", Name: " + name + ", Balance: ৳" + balance);
    }
}

// ATM class
class ATM {
    String location;

    public ATM(String location) {
        this.location = location;
    }

    public void showLocation() {
        System.out.println("ATM Location: " + location);
    }
}

// Bank class
class Bank {
    String bankName;
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<ATM> atms = new ArrayList<>();

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public void addCustomer(Customer c) {
        customers.add(c);
        System.out.println("Customer added successfully.");
    }

    public void addATM(ATM atm) {
        atms.add(atm);
        System.out.println("ATM added successfully.");
    }

    public Customer findCustomer(int id) {
        for (Customer c : customers) {
            if (c.id == id) return c;
        }
        return null;
    }

    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        System.out.println("\n--- Customers List ---");
        for (Customer c : customers) {
            c.display();
        }
    }

    public void displayATMs() {
        if (atms.isEmpty()) {
            System.out.println("No ATMs found.");
            return;
        }
        System.out.println("\n--- ATMs List ---");
        for (ATM atm : atms) {
            atm.showLocation();
        }
    }
}

public class BankingSystem {

    static Bank bank = new Bank("Bangladesh Java Bank");
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        while (true) {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Add Customer");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Customers");
            System.out.println("5. Add ATM");
            System.out.println("6. View ATMs");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    bank.displayCustomers();
                    break;
                case 5:
                    addATM();
                    break;
                case 6:
                    bank.displayATMs();
                    break;
                case 7:
                    System.out.println("Thank you! Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        Customer customer = new Customer(id, name);
        bank.addCustomer(customer);
    }

    static void deposit() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Customer customer = bank.findCustomer(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter amount to deposit (৳): ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount > 0) {
            customer.balance += amount;
            System.out.println("৳" + amount + " deposited to " + customer.name + "'s account.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    static void withdraw() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Customer customer = bank.findCustomer(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter amount to withdraw (৳): ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount > customer.balance) {
            System.out.println("Insufficient balance.");
        } else if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else {
            customer.balance -= amount;
            System.out.println("৳" + amount + " withdrawn from " + customer.name + "'s account.");
        }
    }

    static void addATM() {
        System.out.print("Enter ATM Location: ");
        String location = scanner.nextLine();

        ATM atm = new ATM(location);
        bank.addATM(atm);
    }
}
