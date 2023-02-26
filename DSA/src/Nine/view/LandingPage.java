package Nine.view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LandingPage extends JFrame {
    public static CardLayout CARD_LAYOUT_LANDING_PAGE = new CardLayout();
    public static JPanel LANDING_PANEL;

    LoginSignupPanel loginSignupPanel;
    ApplicationPanel applicationPanel;

    public LandingPage(){
        setTitle("Manang Marshyangdi Futsal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon.png")));
        Image iconImage =  icon.getImage();
        setIconImage(iconImage);

        LANDING_PANEL = new JPanel(CARD_LAYOUT_LANDING_PAGE);
        loginSignupPanel = new LoginSignupPanel();
        applicationPanel = new ApplicationPanel();

        LANDING_PANEL.add(loginSignupPanel, "login");
        LANDING_PANEL.add(applicationPanel,"application");

        add(LANDING_PANEL);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000, 650);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LandingPage::new);
    }
}
