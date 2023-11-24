import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBarApp {
    private JFrame frame;
    private JPanel panel;
    private JProgressBar progressBar;
    private JButton startButton;
    private JButton resetButton;

    private Timer timer;
    private int progressValue;

    public ProgressBarApp() {
        frame = new JFrame("Progress Bar Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        startButton = new JButton("Start");
        resetButton = new JButton("Reset");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startProgressBar();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetProgressBar();
            }
        });

        panel.add(progressBar);
        panel.add(startButton);
        panel.add(resetButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void startProgressBar() {
        startButton.setEnabled(false);
        resetButton.setEnabled(false);

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (progressValue < 100) {
                    progressValue++;
                    progressBar.setValue(progressValue);
                } else {
                    ((Timer) e.getSource()).stop();
                    startButton.setEnabled(true);
                    resetButton.setEnabled(true);
                }
            }
        });

        timer.start();
    }

    private void resetProgressBar() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        progressValue = 0;
        progressBar.setValue(progressValue);
        startButton.setEnabled(true);
        resetButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProgressBarApp();
            }
        });
    }
}
