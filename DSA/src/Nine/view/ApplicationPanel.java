package Nine.view;

import javax.swing.*;
import java.awt.*;

public class ApplicationPanel extends JPanel{
    static JPanel APPLICATION;
    static CardLayout CARD_LAYOUT_APPLICATION;

    JPanel navigationBar;
    Home home;
    Stats stats;
    Settings settings;
    Prices prices;


    public ApplicationPanel(){
        setLayout(null);

        navigationBar = new UIElements.NavigationBar();
        add(navigationBar);

        CARD_LAYOUT_APPLICATION = new CardLayout();
        APPLICATION = new JPanel(CARD_LAYOUT_APPLICATION);

        home = new Home();
        stats = new Stats();
        settings = new Settings();
        prices = new Prices();

        APPLICATION.add(home,"home");
        APPLICATION.add(stats,"stats");
        APPLICATION.add(settings,"settings");
        APPLICATION.add(prices,"prices");

        APPLICATION.setBounds(105,5,874,601);
        add(APPLICATION);
    }
}
