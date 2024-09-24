import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class App {
    // Variable to hold the user's balance
    private double balance;

    // GUI components
    private JFrame frame;        // Main window
    private JTextArea textArea; // Area to display messages
    private JTextField amountField; // Field to enter amounts

    // Constructor to initialize the balance to zero and set up GUI
    public App() {
        this.balance = 0.0; // Initialize balance
        setupGUI(); // Call method to set up the GUI
    }

    // Method to set up the GUI
    private void setupGUI() {
        // Create the main window (JFrame)
        frame = new JFrame("Bank Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
        frame.setSize(400, 300); // Set window size
        frame.setLayout(new BorderLayout()); // Set layout manager

        // Create a text area for displaying messages
        textArea = new JTextArea();
        textArea.setEditable(false); // Make it non-editable
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER); // Add scrollable text area

        // Panel for buttons and input
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); // Set layout for buttons

        // Create text field for amount input
        amountField = new JTextField(10); // Input field with 10 columns
        panel.add(new JLabel("Amount:")); // Label for the input field
        panel.add(amountField); // Add input field to panel

        // Create buttons for various actions
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");

        // Action listener for Check Balance button
        checkBalanceButton.addActionListener(e -> checkBalance());
        // Action listener for Deposit button
        depositButton.addActionListener(e -> depositMoney());
        // Action listener for Withdraw button
        withdrawButton.addActionListener(e -> withdrawMoney());
        // Action listener for Exit button
        exitButton.addActionListener(e -> exitApplication());

        // Add buttons to the panel
        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(exitButton);

        // Add the panel to the bottom of the window
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true); // Make the window visible
    }

    // Method to display the current balance
    private void checkBalance() {
        textArea.append(String.format("Your balance is: %.2f kr%n", balance)); // Append balance to text area
    }

    // Method to handle depositing money
    private void depositMoney() {
        double amount = getAmount("Please enter amount to deposit:"); // Get amount from user
        if (amount >= 0) { // If amount is valid
            balance += amount; // Update balance
            textArea.append(String.format("You have deposited: %.2f kr.%n", amount)); // Display message
        }
    }

    // Method to handle withdrawing money
    private void withdrawMoney() {
        double amount = getAmount("Enter amount to withdraw:"); // Get amount from user
        if (amount >= 0) { // If amount is valid
            if (amount > balance) { // Check if there are sufficient funds
                textArea.append("Insufficient funds for this withdrawal.%n"); // Display message
            } else {
                balance -= amount; // Update balance
                textArea.append(String.format("You have withdrawn: %.2f kr.%n", amount)); // Display message
            }
        }
    }

    // Method to get a valid amount from the user
    private double getAmount(String prompt) {
        String input = amountField.getText(); // Get input from text field
        try {
            double amount = Double.parseDouble(input); // Parse input to a double
            if (amount < 0) { // Check if the amount is positive
                textArea.append("The amount must be positive.%n"); // Display error message
                return -1; // Return -1 to indicate an invalid amount
            }
            amountField.setText(""); // Clear the input field
            return amount; // Return valid amount
        } catch (NumberFormatException e) { // Handle invalid input
            textArea.append("Invalid amount, please try again.%n"); // Display error message
            amountField.setText(""); // Clear the input field
            return -1; // Return -1 to indicate an invalid amount
        }
    }

    // Method to exit the application
    private void exitApplication() {
        frame.dispose(); // Close the application window
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new); // Ensure GUI is created on the Event Dispatch Thread
    }
}