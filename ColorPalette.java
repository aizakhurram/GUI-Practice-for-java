import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPalette {
    private JFrame frame;
    private JPanel colorPanel;
    private JButton colorButton;

    public ColorPalette() {
        frame = new JFrame("Color Palette");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        colorPanel = new JPanel();
        colorPanel.setBackground(Color.WHITE);

        colorButton = new JButton("Select Color");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(frame, "Choose a Color", colorPanel.getBackground());
                if (selectedColor != null) {
                    colorPanel.setBackground(selectedColor);
                }
            }
        });

        frame.setLayout(new BorderLayout());
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 1));
        centerPanel.add(colorPanel);
        
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(colorButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ColorPalette();
            }
        });
    }
}
