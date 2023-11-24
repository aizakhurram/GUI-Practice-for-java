import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Counter {
    private int clickCount = 0;
    private JLabel clickCountLabel;

    public Counter() {
        JFrame frame = new JFrame("Click Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();

        JButton clickButton = new JButton("Click");
        clickCountLabel = new JLabel("Click count: 0");

        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++;
                clickCountLabel.setText("Click count: " + clickCount);
            }
        });

        panel.add(clickButton);
        panel.add(clickCountLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Counter();
            }
        });
    }
}
