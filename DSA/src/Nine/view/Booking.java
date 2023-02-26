package Nine.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Booking extends UIElements.Panels implements ActionListener {
    JLabel label;

    JTextField date, time, name, phone, note;
    JButton confirm;

    Booking(){
        super(null, true,2,42);

        label= new UIElements.Label("Place Booking",23);
        label.setBounds(2, 2, 382, 51);
        add(label);

        date = new UIElements.RoundedJTextField("December 20 2022, (Wed)", 14, 10, 65, 220, 38);
        add(date);

        time = new UIElements.RoundedJTextField("00:00 - 00:00", 14, 238, 65, 138, 38);
        add(time);

        name = new UIElements.RoundedJTextField("Enter Full Name...", 14, 10, 115, 220, 38);
        add(name);

        phone = new UIElements.RoundedJTextField("Phone Number...", 14, 238, 115, 138, 38);
        add(phone);

        note = new UIElements.RoundedJTextField("Notes... (Optional)",14,10,165,366,38);
        add(note);

        confirm = new UIElements.Button("Book", 16, 151, 220, 88, 35,17,17);
        add(confirm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
