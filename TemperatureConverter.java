import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter {
    private JFrame frame;
    private JTextField inputField;
    private JButton convertButton;
    private JLabel resultLabel;

    public TemperatureConverter() {
        frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        panel.add(inputField);
        panel.add(convertButton);
        panel.add(resultLabel);

        JPanel radioPanel = new JPanel();
        ButtonGroup radioGroup = new ButtonGroup();
        JRadioButton celsiusButton = new JRadioButton("Celsius to Fahrenheit");
        JRadioButton fahrenheitButton = new JRadioButton("Fahrenheit to Celsius");
        radioGroup.add(celsiusButton);
        radioGroup.add(fahrenheitButton);
        radioPanel.add(celsiusButton);
        radioPanel.add(fahrenheitButton);

        panel.add(radioPanel);

        frame.add(panel);
        frame.setVisible(true);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double inputValue = Double.parseDouble(inputField.getText());
                    if (celsiusButton.isSelected()) {
                        double result = celsiusToFahrenheit(inputValue);
                        resultLabel.setText("Result: " + result + " °F");
                    } else if (fahrenheitButton.isSelected()) {
                        double result = fahrenheitToCelsius(inputValue);
                        resultLabel.setText("Result: " + result + " °C");
                    } else {
                        resultLabel.setText("Please select a conversion type.");
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a number.");
                }
            }
        });
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter();
            }
        });
    }
}
