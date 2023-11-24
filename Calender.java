import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Calender {
    private JFrame frame;
    private JPanel calendarPanel;
    private JLabel monthLabel;
    private JButton prevMonthButton;
    private JButton nextMonthButton;
    private JTextArea eventTextArea;
    private JButton addEventButton;

    private Calendar currentMonth;
    private Map<Integer, String> events;

    public Calender() {
        frame = new JFrame("Monthly Calendar with Events");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        currentMonth = Calendar.getInstance();
        events = new HashMap<>();

        calendarPanel = new JPanel(new BorderLayout());
        monthLabel = new JLabel();
        prevMonthButton = new JButton("Previous Month");
        nextMonthButton = new JButton("Next Month");
        eventTextArea = new JTextArea(5, 20);
        addEventButton = new JButton("Add Event");

        updateCalendar();

        prevMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMonth.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });

        nextMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMonth.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });

        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JDialog dialog = new JDialog(frame, "Add an Event");
                dialog.setSize(300, 200);

                JPanel panel = new JPanel(new BorderLayout());
                JLabel label = new JLabel("Enter the event details:");
                panel.add(label, BorderLayout.NORTH);

                JTextField textField = new JTextField();
                panel.add(textField, BorderLayout.CENTER);

                JButton addButton = new JButton("Add");
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String event = textField.getText();
                        events.put(currentMonth.get(Calendar.DAY_OF_MONTH), event);
                        updateCalendar();
                        dialog.setVisible(false);
                    }
                });
                panel.add(addButton, BorderLayout.SOUTH);

                dialog.add(panel);
                dialog.setVisible(true);
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(monthLabel, BorderLayout.NORTH);
        frame.add(calendarPanel, BorderLayout.CENTER);
        frame.add(prevMonthButton, BorderLayout.WEST);
        frame.add(nextMonthButton, BorderLayout.EAST);
        frame.add(eventTextArea, BorderLayout.SOUTH);
        frame.add(addEventButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void updateCalendar() {
        calendarPanel.removeAll();
        calendarPanel.revalidate();
        calendarPanel.repaint();

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
        monthLabel.setText(sdf.format(currentMonth.getTime()));

        int year = currentMonth.get(Calendar.YEAR);
        int month = currentMonth.get(Calendar.MONTH);

        Calendar calendar = new GregorianCalendar(year, month, 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK);

        JPanel headerPanel = new JPanel(new GridLayout(1, 7));
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : dayNames) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            headerPanel.add(label);
        }

        JPanel daysPanel = new JPanel(new GridLayout(0, 7));

        for (int i = 1; i < startDay; i++) {
            daysPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            JLabel label = new JLabel(Integer.toString(day), SwingConstants.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            label.setOpaque(true);
            label.setBackground(Color.WHITE);
            
            int finalDay = day;
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        displayEvent(finalDay);
                    }
                }
            });
            daysPanel.add(label);
        }

        calendarPanel.add(headerPanel, BorderLayout.NORTH);
        calendarPanel.add(daysPanel, BorderLayout.CENTER);
        frame.pack();
    }

    private void displayEvent(int day) {
        String event = events.get(day);
        eventTextArea.setText(event != null ? event : "");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calender();
            }
        });
    }
}
