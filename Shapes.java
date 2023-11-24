import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shapes {
    private JFrame frame;
    private DrawingCanvas canvas;
    private JPanel toolsPanel;
    private JButton lineButton;
    private JButton rectangleButton;
    private JButton ellipseButton;
    private JButton clearButton;

    private Color currentColor = Color.BLACK;
    private int currentTool = LINE;

    private static final int LINE = 0;
    private static final int RECTANGLE = 1;
    private static final int ELLIPSE = 2;

    public Shapes() {
        frame = new JFrame("Simple Drawing Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        canvas = new DrawingCanvas();
        toolsPanel = new JPanel();

        lineButton = new JButton("Line");
        rectangleButton = new JButton("Rectangle");
        ellipseButton = new JButton("Ellipse");
        clearButton = new JButton("Clear");

        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTool = LINE;
            }
        });

        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTool = RECTANGLE;
            }
        });

        ellipseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTool = ELLIPSE;
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.clear();
            }
        });

        toolsPanel.add(lineButton);
        toolsPanel.add(rectangleButton);
        toolsPanel.add(ellipseButton);
        toolsPanel.add(clearButton);

        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(toolsPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private class DrawingCanvas extends JPanel {
        private int startX, startY, endX, endY;
        private Graphics2D g2d;

        public DrawingCanvas() {
            setBackground(Color.WHITE);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    startX = e.getX();
                    startY = e.getY();
                    endX = e.getX();
                    endY = e.getY();
                    g2d = (Graphics2D) getGraphics();
                    g2d.setColor(currentColor);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    drawShape(g2d);
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(currentColor);

            if (g2d != null) {
                g2d.setColor(currentColor);
                drawShape(g2d);
            }
        }

        private void drawShape(Graphics2D g) {
            switch (currentTool) {
                case LINE:
                    g.drawLine(startX, startY, endX, endY);
                    break;
                case RECTANGLE:
                    g.drawRect(Math.min(startX, endX), Math.min(startY, endY),
                            Math.abs(startX - endX), Math.abs(startY - endY));
                    break;
                case ELLIPSE:
                    g.drawOval(Math.min(startX, endX), Math.min(startY, endY),
                            Math.abs(startX - endX), Math.abs(startY - endY));
                    break;
            }
        }

        public void clear() {
            g2d.clearRect(0, 0, getWidth(), getHeight());
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Shapes();
            }
        });
    }
}

