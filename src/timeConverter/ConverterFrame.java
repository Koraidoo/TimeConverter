package timeConverter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class ConverterFrame extends JFrame implements Runnable {
    public ConverterFrame() {
    }

    @Override
    public void run() {
        setup();
    }

    public void setup() {
        JLabel yearLabel = new JLabel("year:");
        yearLabel.setBounds(10, 10, 40, 20);
        JTextField year = new JTextField(Integer.toString(LocalDateTime.now().getYear()));
        year.setBounds(40, 10, 50, 20);
        this.add(yearLabel);
        this.add(year);

        JLabel monthLabel = new JLabel("month:");
        monthLabel.setBounds(90, 10, 60, 20);
        JTextField month = new JTextField("");
        month.setBounds(150, 10, 30, 20);
        this.add(monthLabel);
        this.add(month);

        JLabel dayLabel = new JLabel("day:");
        dayLabel.setBounds(190, 10, 40, 20);
        JTextField day = new JTextField("");
        day.setBounds(230, 10, 30, 20);
        this.add(dayLabel);
        this.add(day);

        JLabel hourLabel = new JLabel("hour:");
        hourLabel.setBounds(10, 40, 40, 20);
        JTextField hour = new JTextField();
        hour.setBounds(50, 40, 30, 20);
        this.add(hourLabel);
        this.add(hour);

        JLabel minuteLabel = new JLabel("minute:");
        minuteLabel.setBounds(90, 40, 60, 20);
        JTextField minute = new JTextField();
        minute.setBounds(150, 40, 30, 20);
        this.add(minuteLabel);
        this.add(minute);
        
        JLabel fromLabel = new JLabel("from Zone:");
        fromLabel.setBounds(20, 70, 80, 20);
        String[] fromZones = {"UTC", "Australia/Sydney"};
        JComboBox<String> fromZone = new JComboBox<>(fromZones);
        fromZone.setBounds(100, 70, 180, 20);
        this.add(fromLabel);
        this.add(fromZone);

        JLabel toLabel = new JLabel("to Zone:");
        toLabel.setBounds(20, 100, 80, 20);
        String[] toZones = {"UTC", "Australia/Sydney"};
        JComboBox<String> toZone = new JComboBox<>(toZones);
        toZone.setBounds(100, 100, 180, 20);
        this.add(toLabel);
        this.add(toZone);

        JButton button = new JButton("Convert");
        button.setBounds(100, 150, 90, 20);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(20, 190, 50, 20);
        this.add(resultLabel);

        JLabel result = new JLabel();
        result.setBounds(85, 190, 120, 20);
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Converter converter = new Converter(
                    fromZone.getItemAt(fromZone.getSelectedIndex()), 
                    toZone.getItemAt(toZone.getSelectedIndex()));

                String field = "";
                int year_ = 0, month_ = 0, day_ = 0, hour_ = 0, minute_ = 0;
                try {
                    year_ = Integer.parseInt(year.getText());
                } catch (NumberFormatException error) {
                    field += "year";
                }
                try {
                    month_ = Integer.parseInt(month.getText());
                } catch (NumberFormatException error) {
                    field += "".equals(field) ? "month" : ", month";
                }
                try {
                    day_ = Integer.parseInt(day.getText());
                } catch (NumberFormatException error) {
                    field += "".equals(field) ? "day" : ", day";
                }
                try {
                    hour_ = Integer.parseInt(hour.getText());
                } catch (NumberFormatException error) {
                    field += "".equals(field) ? "hour" : ", hour";
                }
                try {
                    minute_ = Integer.parseInt(minute.getText());
                } catch (NumberFormatException error) {
                    field += "".equals(field) ? "minute" : ", minute";
                }

                if ("".equals(field)) {
                    try {
                        converter.setDate(year_, month_, day_, hour_, minute_);
                        result.setText(converter.convert());
                    } catch (DateTimeException error) {
                        JOptionPane.showMessageDialog(null, "Please fill with a valid date time");
                    }
                } else {
                    field += field.contains(",") ? " fields" : " field";
                    JOptionPane.showMessageDialog(null, "Please fill " + field + " with integers");
                }
            }
        });

        this.add(button);
        this.add(result);

        this.setLayout(null);
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
