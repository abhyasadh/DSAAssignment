package Nine.view;

import javax.swing.*;

public class Home extends JPanel {
    Booking booking;
    Bill bill;
    Reservations reservations;

    Home(){
        setLayout(null);
        booking = new Booking();
        booking.setBounds(488,0, 387,278);

        bill = new Bill();
        bill.setBounds(488,283,387,319);

        reservations = new Reservations();
        reservations.setBounds(0,0,484,602);

        add(booking);
        add(bill);
        add(reservations);
    }
}
