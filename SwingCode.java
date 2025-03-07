import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
public class CurrencyConverterApp extends JFrame {
    private JLabel titleLabel;
    private JLabel amountLabel;
    private JTextField amountTextField;
    private JLabel fromLabel;
    private JComboBox<String> fromComboBox;
    private JLabel toLabel;
    private JComboBox<String> toComboBox;
    private JButton convertButton;
    private JLabel resultLabel;
    private JLabel noteLabel;
    private static final String[] currencies = {"USD", "EUR", "GBP", "JPY", "CAD", "INR"};
    private static final double[][] conversionRates = {
        {1.0, 0.84, 0.84, 110.94, 1.26, 74.0},  // USD
        {1.19, 1.0, 0.88, 131.84, 1.5, 88.29},  // EUR
        {1.35, 1.13, 1.0, 149.96, 1.71, 100.53},  // GBP
        {0.0090, 0.0076, 0.0067, 1.0, 0.011, 0.65},  // JPY
        {0.79, 0.67, 0.59, 88.97, 1.0, 58.64},  // CAD
        {0.0135, 0.0113, 0.0099, 1.52, 0.017, 1.0}  // INR
    };
    public CurrencyConverterApp() {
        // Set the title of the JFrame
        setTitle("Currency Converter - Made by updateGadh.com");
        // Create and configure components
        titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        amountLabel = new JLabel("Enter Amount:");
        amountTextField = new JTextField(10);
        fromLabel = new JLabel("From Currency:");
        fromComboBox = new JComboBox<>(currencies);
        toLabel = new JLabel("To Currency:");
        toComboBox = new JComboBox<>(currencies);
        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
        resultLabel = new JLabel("Result: ");
        // Add a note label
        noteLabel = new JLabel("Note: The currency values used in this demo are for illustration purposes only.");
        // Create and configure the layout
        setLayout(new FlowLayout());
        // Add components to the JFrame
        add(titleLabel);
        add(amountLabel);
        add(amountTextField);
        add(fromLabel);
        add(fromComboBox);
        add(toLabel);
        add(toComboBox);
        add(convertButton);
        add(resultLabel);
        add(noteLabel);
        // Set default values
        amountTextField.setText("100");  // Default amount
        fromComboBox.setSelectedItem("USD");  // Default 'From' currency
        toComboBox.setSelectedItem("EUR");    // Default 'To' currency
        // Set JFrame properties
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setVisible(true);
    }
    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            String fromCurrency = (String) fromComboBox.getSelectedItem();
            String toCurrency = (String) toComboBox.getSelectedItem();
            // Find the index of 'fromCurrency' and 'toCurrency' in the 'currencies' array
            int fromIndex = -1;
            int toIndex = -1;
            for (int i = 0; i < currencies.length; i++) {
                if (currencies[i].equals(fromCurrency)) {
                    fromIndex = i;
                }
                if (currencies[i].equals(toCurrency)) {
                    toIndex = i;
                }
            }
            if (fromIndex != -1 && toIndex != -1) {
                double conversionRate = conversionRates[fromIndex][toIndex];
                double convertedAmount = amount * conversionRate;
                DecimalFormat df = new DecimalFormat("#.##");
                resultLabel.setText("Result: " + df.format(amount) + " " + fromCurrency + " = " + df.format(convertedAmount) + " " + toCurrency);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid currency selection.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverterApp();
            }
        });
    }
}
