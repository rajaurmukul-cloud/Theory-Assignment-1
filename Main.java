import java.util.Scanner; 
import java.util.InputMismatchException;


class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    public Account(int accountNumber, String accountHolderName, double initialDeposit,
                   String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! Current balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful! Current balance: " + balance);
        }
    }
    public void displayAccountDetails() {
        System.out.println("\n=== Account Details ===");
        System.out.println("AC Number: " + accountNumber);
        System.out.println("Holder Name: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }
    public void updateContactDetails(String email, String phoneNumber) {
        if (isValidEmail(email) && isValidPhone(phoneNumber)) {
            this.email = email;
            this.phoneNumber = phoneNumber;
            System.out.println("Contact details updated successfully.");
        } else {
            System.out.println("Invalid email or phone number format.");
        }
    }
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }
}
class Main {
    // Data storage
    private static Account[] accounts = new Account[100];
    private static int accountCount = 0;
    private static int nextAccountNumber = 1001;
    private static Scanner sc = new Scanner(System.in);

    // Entry point
    public static void main(String[] args) {
        System.out.println("Welcome to the Banking Application! CLI");
        mainMenu();//abhinavgautam08
    }
    private static void mainMenu() {
        while (true) {
            try {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. Create a new account");
                System.out.println("2. Deposit money");
                System.out.println("3. Withdraw money");
                System.out.println("4. View account details");
                System.out.println("5. Update contact details");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                
                switch (choice) {
                    case 1 -> createAccount();
                    case 2 -> performDeposit();
                    case 3 -> performWithdrawal();
                    case 4 -> showAccountDetails();
                    case 5 -> updateContact();
                    case 6 -> {
                        System.out.println("Exiting... Thank you for using the Banking Application!");
                        return;//abhinavgautam08
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers only.");
                sc.nextLine();
            }
        }
    }
    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter email address: ");
        String email = sc.nextLine();

        System.out.print("Enter phone number (10 digits): ");
        String phone = sc.nextLine();

        if (initialDeposit > 0 && email.contains("@") && phone.matches("\\d{10}")) {
            accounts[accountCount] = new Account(nextAccountNumber, name, initialDeposit, email, phone);
            System.out.println("Account created successfully with Account Number: " + nextAccountNumber);
            nextAccountNumber++;
            accountCount++;
        } else {
            System.out.println("Invalid input. Account creation failed.");
        }
    }
    private static void performDeposit() {
        Account acc = findAccount();
        if (acc != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            acc.deposit(amount);
        }
    }
    private static void performWithdrawal() {
        Account acc = findAccount();
        if (acc != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            acc.withdraw(amount);
        }
    }
    private static void showAccountDetails() {
        Account acc = findAccount();
        if (acc != null) {
            acc.displayAccountDetails();
        }
    }
    private static void updateContact() {
        Account acc = findAccount();
        if (acc != null) {
            System.out.print("Enter new email: ");
            String email = sc.nextLine();
            System.out.print("Enter new phone number: ");
            String phone = sc.nextLine();
            acc.updateContactDetails(email, phone);
        }
    }
    private static Account findAccount() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        sc.nextLine();//abhinavgautam08
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNum) {
                return accounts[i];
            }
        }
        System.out.println("Account not found.");
        return null;
    }
}
