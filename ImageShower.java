import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageShower {
    private JFrame frame;
    private JLabel imageLabel;
    private JButton previousButton;
    private JButton nextButton;

    private File[] imageFiles;
    private int currentIndex;

    public ImageShower() {
        frame = new JFrame("Image Gallery");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(imageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousImage();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });

       
        String imagePath = "/Users/aizakhurram/21L_5771_BSE_5B1/Lab 8/Images";
        File imageFolder = new File(imagePath);
        imageFiles = imageFolder.listFiles(file -> file.getName().toLowerCase().endsWith(".jpeg"));

        currentIndex = 0;
        displayCurrentImage();

        frame.setVisible(true);
    }

    private void displayCurrentImage() {
        if (currentIndex >= 0 && currentIndex < imageFiles.length) {
            try {
                BufferedImage image = ImageIO.read(imageFiles[currentIndex]);
                imageLabel.setIcon(new ImageIcon(image));
                frame.setTitle("Image Gallery - " + imageFiles[currentIndex].getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showNextImage() {
        currentIndex++;
        if (currentIndex >= imageFiles.length) {
            currentIndex = 0;
        }
        displayCurrentImage();
    }

    private void showPreviousImage() {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = imageFiles.length - 1;
        }
        displayCurrentImage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImageShower();
            }
        });
    }
}
