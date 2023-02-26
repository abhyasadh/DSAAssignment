package Nine.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Reservations extends UIElements.Panels implements ActionListener {
    JButton back, front, date, edit, delete;
    JPanel players, booking;

    SimpleDateFormat format = new SimpleDateFormat("MMMMM dd, yyyy (EEE)", Locale.ENGLISH);
    Calendar calendar = Calendar.getInstance();

    String dateValue = format.format(calendar.getTime());

    Reservations(){
        super(null, true,98,42);
        setLayout(null);

        back = new UIElements.NextButton(20,8);
        back.setText("\u2b9c");
        back.setEnabled(true);
        back.setFocusPainted(false);
        back.addActionListener(this);
        add(back);

        front = new UIElements.NextButton(423,8);
        front.setText("\u2b9e");
        front.setEnabled(true);
        front.setFocusPainted(false);
        front.addActionListener(this);
        add(front);

        date = new UIElements.Button(dateValue,24,0,2,480,50,17,17);
        date.setFont(new Font("Cooper Black",Font.PLAIN,22));
        date.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 1 && event.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Date picker");
                } else if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Date picker closed");

                    calendar = Calendar.getInstance();
                    String dateValue = format.format(calendar.getTime());
                    date.setText(dateValue);
                }
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        date.addActionListener(this);
        add(date);

        players = new JPanel();
        players.setLayout(new VerticalLayout(20,2));
        players.setBackground(Color.white);

        JScrollPane pane = new JScrollPane(players);
        pane.setBounds(4,54,475,544);

        for (int i=0; i<=6; i++){
            booking = new UIElements.Panels(null,false,0,0);
            booking.setBackground(Color.white);
            booking.setPreferredSize(new Dimension(420, 80));
            booking.setBorder(BorderFactory.createMatteBorder(10,13,5,13,Color.decode("#EBEBEB")));

            edit=new UIElements.Button("Edit",16,380,4,35,35,17,17);
            edit.addActionListener(this);
            booking.add(edit);

            delete=new UIElements.Button("Delete",16,380,40,35,35,17,17);
            delete.addActionListener(this);
            booking.add(delete);

            players.add(booking);
        }

        pane.setComponentZOrder(pane.getVerticalScrollBar(), 0);
        pane.getVerticalScrollBar().setOpaque(false);
        pane.getVerticalScrollBar().setUI(new UIElements.MyScrollBarUI(pane));
        pane.getVerticalScrollBar().setUnitIncrement(12);
        pane.setBorder(null);
        pane.setOpaque(false);
        add(pane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)){
            calendar.add(Calendar.DATE,-1);
            String dateValue = format.format(calendar.getTime());
            date.setText(dateValue);
        }
        if (e.getSource().equals(front)){
            calendar.add(Calendar.DATE,1);
            String dateValue = format.format(calendar.getTime());
            date.setText(dateValue);
        }
        if (e.getSource().equals(edit)){

        }
    }
}
