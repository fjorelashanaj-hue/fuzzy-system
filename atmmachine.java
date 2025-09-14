import java.util.Scanner;

class BankAcc {
    String accHolder;
    String accNo;
    String accType;
    long balance;
    Scanner sc = new Scanner(System.in);

    // Open account
    public void openAcc() {
        System.out.print("Name: ");
        accHolder = sc.nextLine();
        System.out.print("Account No: ");
        accNo = sc.nextLine();
        System.out.print("Account Type: ");
        accType = sc.nextLine();
        System.out.print("Balance: ");
        balance = sc.nextLong();
        sc.nextLine(); // consume leftover newline
    }

    // Display account info
    public void displayInfo() {
        System.out.println("Account Holder Name: " + accHolder);
        System.out.println("Account Number: " + accNo);
        System.out.println("Account Type: " + accType);
        System.out.println("Balance: " + balance);
    }

    // Deposit money
    public void deposit() {
        System.out.print("Deposit Amount: ");
        long amt = sc.nextLong();
        balance += amt;
        System.out.println("Updated Balance: " + balance);
    }

    // Withdraw money
    public void withdraw() {
        System.out.print("Withdraw Amount: ");
        long amt = sc.nextLong();
        if (balance >= amt) {
            balance -= amt;
            System.out.println("Updated Balance: " + balance);
        } else {
            System.out.println("Insufficient Funds! Current Balance: " + balance);
        }
    }

    // Search by account number
    public boolean search(String acNo) {
        if (accNo.equals(acNo)) {
            displayInfo();
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many customers do you want to input? ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline
        BankAcc[] customers = new BankAcc[n];

        // Open accounts
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for customer " + (i + 1) + ":");
            customers[i] = new BankAcc();
            customers[i].openAcc();
        }

        int ch;
        do {
            System.out.println("\n**Banking System Application");
            System.out.println("1. Display all account details");
            System.out.println("2. Search by Account number");
            System.out.println("3. Deposit the amount");
            System.out.println("4. Withdraw the amount");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            ch = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (ch) {
                case 1:
                    for (BankAcc customer : customers) {
                        customer.displayInfo();
                        System.out.println("------------------------");
                    }
                    break;

                case 2:
                    System.out.print("Enter account no. you want to search: ");
                    String acNo = sc.nextLine();
                    boolean found = false;
                    for (BankAcc customer : customers) {
                        if (customer.search(acNo)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account no.: ");
                    acNo = sc.nextLine();
                    found = false;
                    for (BankAcc customer : customers) {
                        if (customer.search(acNo)) {
                            customer.deposit();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Account No.: ");
                    acNo = sc.nextLine();
                    found = false;
                    for (BankAcc customer : customers) {
                        if (customer.search(acNo)) {
                            customer.withdraw();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist.");
                    }
                    break;

                case 5:
                    System.out.println("See you soon...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (ch != 5);
    }
}