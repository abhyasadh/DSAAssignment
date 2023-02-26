package Nine.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bill extends UIElements.Panels implements ActionListener {
    JLabel label;

    Bill(){
        super(null, true,2,41);
        setLayout(null);
        setBorder(new LineBorder(UIElements.textColor,3));

        label= new UIElements.Label("Bill",24);
        add(label);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
