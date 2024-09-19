public void displayMenu() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("\nWelcome to the Bank Application!");
        System.out.println("\n1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.print("\nPlease make a choice (1-4): \n");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                checkBalance();
                break;
            case "2":
                depositMoney(scanner);
                break;
            case "3":
                withdrawMoney(scanner);
                break;
            case "4":
                System.out.println("Exiting the program.");
                scanner.close();
                return;
            default:
                System.out.println("INVALID CHOICE!!!, please try again.");

            
        }
    }
}
private void checkBalance() {
    System.out.printf("Your balance is: %.2f kr%n \n", balance);
}
private void depositMoney(Scanner scanner) {
    double amount = getAmount(scanner, "Please enter amount to deposit: \n");
    if (amount >= 0) {
        balance += amount;
        System.out.printf("You have deposited : %.2f kr.%n", amount);
    }
}
private void withdrawMoney(Scanner scanner) {
    double amount = getAmount(scanner, "Enter amount to withdraw: ");
    if (amount >= 0) {
        if (amount > balance) {
            System.out.println("Insufficient funds for this withdrawal.");
        } else {
            balance -= amount;
            System.out.printf("You have withdrawn : %.2f kr.%n", amount);
        }
    }
}
private double getAmount(Scanner scanner, String prompt) {
    System.out.print(prompt);
    try {
        double amount = Double.parseDouble(scanner.nextLine());
        if (amount < 0) {
            System.out.println("The amount must be positive.");
            return -1;
        }
        return amount;
    } catch (NumberFormatException e) {
        System.out.println("Invalid amount, please try again.");
        return -1;
    }
}