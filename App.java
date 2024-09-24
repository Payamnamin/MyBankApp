import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class App {
    // Variable to hold the user's balance
    private double balance;

    // GUI components
    private JFrame frame;
    private JTextArea textArea;
    private JTextField amountField;

    // Constructor to initialize the balance to zero and set up GUI
    public App() {
        this.balance = 0.0;
        setupGUI();
    }

    // Method to set up the GUI
    private void setupGUI() {
        frame = new JFrame("Bank Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        amountField = new JTextField(10);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");

        checkBalanceButton.addActionListener(e -> checkBalance());
        depositButton.addActionListener(e -> depositMoney());
        withdrawButton.addActionListener(e -> withdrawMoney());
        exitButton.addActionListener(e -> exitApplication());

        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(exitButton);

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    // Method to display the current balance
    private void checkBalance() {
        textArea.append(String.format("Your balance is: %.2f kr%n", balance));
    }

    // Method to handle depositing money
    private void depositMoney() {
        double amount = getAmount("Please enter amount to deposit:");
        if (amount >= 0) {
            balance += amount;
            textArea.append(String.format("You have deposited: %.2f kr.%n", amount));
        }
    }

    // Method to handle withdrawing money
    private void withdrawMoney() {
        double amount = getAmount("Enter amount to withdraw:");
        if (amount >= 0) {
            if (amount > balance) {
                textArea.append("Insufficient funds for this withdrawal.%n");
            } else {
                balance -= amount;
                textArea.append(String.format("You have withdrawn: %.2f kr.%n", amount));
            }
        }
    }

    // Method to get a valid amount from the user
    private double getAmount(String prompt) {
        String input = amountField.getText();
        try {
            double amount = Double.parseDouble(input);
            if (amount < 0) {
                textArea.append("The amount must be positive.%n");
                return -1;
            }
            amountField.setText(""); // Clear the input field
            return amount;
        } catch (NumberFormatException e) {
            textArea.append("Invalid amount, please try again.%n");
            amountField.setText(""); // Clear the input field
            return -1;
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