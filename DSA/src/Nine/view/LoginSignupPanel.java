package Nine.view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LoginSignupPanel extends JPanel{
    public static JPanel FORMS;
    public static CardLayout CARD_LAYOUT_LOGIN = new CardLayout();

    Login login;
    Signup signup;
    ResetPassword resetPassword;

    public LoginSignupPanel(){
        setLayout(null);

        login = new Login();
        signup = new Signup();
        resetPassword = new ResetPassword();

        FORMS = new UIElements.Panels(CARD_LAYOUT_LOGIN, false, 0,0);

        FORMS.add(login,"login");
        FORMS.add(signup,"signup");
        FORMS.add(resetPassword, "reset");

        FORMS.setBounds(580,115,314,365);

        add(FORMS);

        ImageIcon backgroundImage = new ImageIcon((Objects.requireNonNull(getClass().getClassLoader().getResource("Background.png"))));

        JLabel background = new JLabel(backgroundImage);
        background.setBounds(0,0,1000,650);
        add(background);
    }
}
