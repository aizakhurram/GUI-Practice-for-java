import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField display;
    private double currentValue;
    private String currentOperator;

    public Calculator() {
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        display = new JTextField(10);
        display.setEditable(false);
        panel.add(display);

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        currentValue = 0;
        currentOperator = "";
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("[0-9]")) {
                display.setText(display.getText() + command);
            } else if (command.equals("C")) {
                display.setText("");
                currentValue = 0;
                currentOperator = "";
            } else if (command.equals("=")) {
                double value = Double.parseDouble(display.getText());
                performCalculation(value);
            } else {
                currentOperator = command;
                currentValue = Double.parseDouble(display.getText());
                display.setText("");
            }
        }
    }

    private void performCalculation(double newValue) {
        switch (currentOperator) {
            case "+":
                currentValue += newValue;
                break;
            case "-":
                currentValue -= newValue;
                break;
            case "*":
                currentValue *= newValue;
                break;
            case "/":
                if (newValue != 0) {
                    currentValue /= newValue;
                } else {
                    display.setText("Error");
                    currentValue = 0;
                    currentOperator = "";
                    return;
                }
                break;
        }

        display.setText(Double.toString(currentValue));
        currentOperator = "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}
