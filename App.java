import java.util.Scanner;

class App {
    // Variable to hold the user's balance
    private double balance;

    // Constructor to initialize the balance to zero
    public App() {
        this.balance = 0.0;
    }

    // Method to display the menu and handle user choices
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        // Infinite loop to keep showing the menu until the user chooses to exit
        while (true) {
            // Display the menu options
            System.out.println("\nWelcome to the Bank Application!");
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");

            // Prompt user for their choice
            System.out.print("\nPlease make a choice (1-4): \n");
            String choice = scanner.nextLine();

            // Handle user's choice with a switch statement
            switch (choice) {
                case "1":
                    // Call method to check balance
                    checkBalance();
                    break;
                case "2":
                    // Call method to deposit money
                    depositMoney(scanner);
                    break;
                case "3":
                    // Call method to withdraw money
                    withdrawMoney(scanner);
                    break;
                case "4":
                    // Exit the program
                    System.out.println("Exiting the program.");
                    scanner.close(); // Close the scanner to free resources
                    return; // Exit the method
                default:
                    // Handle invalid choice
                    System.out.println("INVALID CHOICE!!!, please try again.");
            }
        }
    }

    // Method to display the current balance
    private void checkBalance() {
        System.out.printf("Your balance is: %.2f kr%n \n", balance);
    }

    // Method to handle depositing money
    private void depositMoney(Scanner scanner) {
        // Get the amount to deposit from the user
        double amount = getAmount(scanner, "Please enter amount to deposit: \n");
        if (amount >= 0) { // Check if the amount is valid
            balance += amount; // Update the balance
            System.out.printf("You have deposited : %.2f kr.%n", amount);
        }
    }

    // Method to handle withdrawing money
    private void withdrawMoney(Scanner scanner) {
        // Get the amount to withdraw from the user
        double amount = getAmount(scanner, "Enter amount to withdraw: ");
        if (amount >= 0) { // Check if the amount is valid
            if (amount > balance) { // Check if there are sufficient funds
                System.out.println("Insufficient funds for this withdrawal.");
            } else {
                balance -= amount; // Update the balance
                System.out.printf("You have withdrawn : %.2f kr.%n", amount);
            }
        }
    }

    // Method to get a valid amount from the user
    private double getAmount(Scanner scanner, String prompt) {
        System.out.print(prompt); // Display the prompt
        try {
            double amount = Double.parseDouble(scanner.nextLine()); // Parse user input
            if (amount < 0) { // Check if the amount is positive
                System.out.println("The amount must be positive.");
                return -1; // Return -1 to indicate an invalid amount
            }
            return amount; // Return the valid amount
        } catch (NumberFormatException e) { // Handle invalid input
            System.out.println("Invalid amount, please try again.");
            return -1; // Return -1 to indicate an invalid amount
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        App app = new App(); // Create an instance of the BankApp
        app.displayMenu(); // Call the method to display the menu
    }
}
