package Nine.view;

import Nine.controller.UserController;
import Nine.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class Login extends JPanel implements ActionListener {
    public static int USER_ID = 0;

    JButton loginButton, loginPage, signupPage, showPassword, forgotPassword;
    JTextField phone;
    JPasswordField password;

    ImageIcon show = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Show.png")));
    ImageIcon hide = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Hide.png")));

    public Login(){
        setLayout(null);
        setOpaque(false);

        mainButtons();
        otherButtons();
        textFields();
        handleActions();
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.decode("#f0f0f0"));
        g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 22, 22);
        super.paintComponent(g2d);
    }

    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(UIElements.textColor);
        g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 22, 22);
    }

    void mainButtons(){
        loginPage = new UIElements.MainButtons("Login",6);
        add(loginPage);

        signupPage = new UIElements.MainButtons("Sign Up", 158);
        signupPage.setBorderPainted(false);
        add(signupPage);
    }

    void textFields() {
        final int[] i = {0};
        phone = new UIElements.RoundedJTextField("Enter Your Phone Number...",14,26, 85, 260, 38);
        phone.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()!=8) i[0]++;
                else i[0]--;

                if (e.getKeyChar()!=KeyEvent.VK_0&&e.getKeyChar()!=KeyEvent.VK_1&&e.getKeyChar()!=KeyEvent.VK_2&&e.getKeyChar()!=KeyEvent.VK_3&&e.getKeyChar()!=KeyEvent.VK_4&&e.getKeyChar()!=KeyEvent.VK_5&&e.getKeyChar()!=KeyEvent.VK_6&&e.getKeyChar()!=KeyEvent.VK_7&&e.getKeyChar()!=KeyEvent.VK_8&&e.getKeyChar()!=KeyEvent.VK_9&&e.getKeyChar()!=KeyEvent.VK_BACK_SPACE&&e.getKeyChar()!=KeyEvent.VK_ENTER&&e.getKeyChar()!=KeyEvent.VK_DELETE){
                    e.consume();
                    i[0]--;
                }

                if (i[0]==10) password.requestFocus();
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    password.requestFocus();
                }
                if (e.getKeyCode()==KeyEvent.VK_DOWN){
                    password.requestFocus();
                }
            }
        });
        add(phone);

        password = new UIElements.PasswordField("Enter Your Password...", 14, 26, 135, 260, 38, showPassword);
        password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    loginButton.doClick();
                }
                if (e.getKeyCode()==KeyEvent.VK_UP){
                    phone.requestFocus();
                }
            }
        });
        add(password);
    }

    void otherButtons(){
        forgotPassword = new JButton("Forgot Password?");
        forgotPassword.setForeground(UIElements.textColor);
        forgotPassword.setBackground(Color.decode("#F0F0F0"));
        forgotPassword.setFont(new Font("Cambria", Font.PLAIN, 12));
        forgotPassword.setBounds(173, 176, 130, 20);
        forgotPassword.setBorderPainted(false);
        add(forgotPassword);

        showPassword = new UIElements.ShowPassword(248, 135, 38, 38);
        add(showPassword);

        loginButton = new UIElements.Button("Login",16,111,212,85,37,17,17);
        add(loginButton);
    }

    void handleActions(){
        loginButton.addActionListener(this);
        showPassword.addActionListener(this);
        forgotPassword.addActionListener(this);

        loginPage.addActionListener(e -> LoginSignupPanel.CARD_LAYOUT_LOGIN.show(LoginSignupPanel.FORMS,"login"));
        signupPage.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String enteredPassword = String.copyValueOf(password.getPassword());

        if (e.getSource().equals(forgotPassword)){
            LoginSignupPanel.FORMS.add(new ResetPassword(), "reset");
            LoginSignupPanel.CARD_LAYOUT_LOGIN.show(LoginSignupPanel.FORMS,"reset");
        }

        if (e.getSource().equals(loginButton)){
            if (phone.getText().equals("Enter Your Phone Number...") || enteredPassword.equals("Enter Your Password...")) {
                JOptionPane.showMessageDialog(null, "Please, fill all the details!");
            } else {
                UserController userController = new UserController();
                User user = userController.loginUser(phone.getText(), enteredPassword);
                if (user != null) {
                    LandingPage.LANDING_PANEL.add(new ApplicationPanel(), "application");
                    LandingPage.CARD_LAYOUT_LANDING_PAGE.show(LandingPage.LANDING_PANEL, "application");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!");
                    password.requestFocus();
                }
            }
        }

        if (e.getSource().equals(signupPage)){
            LoginSignupPanel.FORMS.add(new Signup(), "signup");
            LoginSignupPanel.CARD_LAYOUT_LOGIN.show(LoginSignupPanel.FORMS,"signup");
        }

        if (e.getSource().equals(showPassword)) {
            if (password.getEchoChar() == (char) 0 && (!enteredPassword.equals("Enter Your Password..."))) {
                showPassword.setIcon(show);
                password.setEchoChar('●');
            } else {
                showPassword.setIcon(hide);
                password.setEchoChar((char) 0);
            }
        } else {
            if (!enteredPassword.equals("Enter Your Password...")) {
                password.setForeground(UIElements.placeholderColor);
                password.setText("Enter Your Password...");
                password.setEchoChar((char) 0);
                showPassword.setIcon(show);
                showPassword.setEnabled(false);
            }
        }

        if (e.getSource().equals(loginButton)){

        }
    }
}
