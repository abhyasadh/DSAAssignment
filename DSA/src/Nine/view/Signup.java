package Nine.view;

import Nine.controller.UserController;
import Nine.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Signup extends JPanel implements ActionListener {

    JButton loginPage, signupPage, showPassword1, showPassword2, signup;
    JTextField firstName, lastName, phone;
    JPasswordField createPassword, confirmPassword;

    ImageIcon show;
    ImageIcon hide;

    Signup(){
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
        Login login = new Login();
        loginPage = login.loginPage;
        loginPage.setBorderPainted(false);
        add(loginPage);

        signupPage = login.signupPage;
        signupPage.setBorderPainted(true);
        add(signupPage);

        show = login.show;
        hide = login.hide;
    }

    void textFields() {
        firstName = new UIElements.RoundedJTextField("First Name...", 14, 26, 85, 125, 38);
        firstName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    lastName.requestFocus();
                }
                if (e.getKeyCode()==KeyEvent.VK_DOWN){
                    lastName.requestFocus();
                }
            }
        });
        add(firstName);

        lastName = new UIElements.RoundedJTextField("Last Name...", 14, 160, 85, 125, 38);
        lastName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER||e.getKeyCode()==KeyEvent.VK_DOWN){
                    phone.requestFocus();
                }
                if (e.getKeyCode()==KeyEvent.VK_UP){
                    firstName.requestFocus();
                }
            }
        });
        add(lastName);

        phone = new UIElements.RoundedJTextField("Enter Your Phone Number...", 14, 26, 135, 260, 38);
        phone.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()!=KeyEvent.VK_0&&e.getKeyChar()!=KeyEvent.VK_1&&e.getKeyChar()!=KeyEvent.VK_2&&e.getKeyChar()!=KeyEvent.VK_3&&e.getKeyChar()!=KeyEvent.VK_4&&e.getKeyChar()!=KeyEvent.VK_5&&e.getKeyChar()!=KeyEvent.VK_6&&e.getKeyChar()!=KeyEvent.VK_7&&e.getKeyChar()!=KeyEvent.VK_8&&e.getKeyChar()!=KeyEvent.VK_9&&e.getKeyChar()!=KeyEvent.VK_BACK_SPACE&&e.getKeyChar()!=KeyEvent.VK_ENTER&&e.getKeyChar()!=KeyEvent.VK_DELETE){
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER||e.getKeyCode()==KeyEvent.VK_DOWN){
                    createPassword.requestFocus();
                }
                if (e.getKeyCode()==KeyEvent.VK_UP){
                    lastName.requestFocus();
                }
            }
        });
        add(phone);

        createPassword = new UIElements.PasswordField("Create a Password...", 14, 26, 185, 260, 38, showPassword1);
        createPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER||e.getKeyCode()==KeyEvent.VK_DOWN){
                    confirmPassword.requestFocus();
                }
                if (e.getKeyCode()==KeyEvent.VK_UP){
                    phone.requestFocus();
                }
            }
        });
        add(createPassword);

        confirmPassword = new UIElements.PasswordField("Confirm Password...", 14, 26, 235, 260, 38, showPassword2);
        confirmPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    signup.doClick();
                }
                if (e.getKeyCode()==KeyEvent.VK_UP){
                    createPassword.requestFocus();
                }
            }
        });
        add(confirmPassword);
    }

    void otherButtons() {
        showPassword1 = new UIElements.ShowPassword(248, 185, 38, 38);
        add(showPassword1);

        showPassword2 = new UIElements.ShowPassword(248, 235, 38, 38);
        add(showPassword2);

        signup = new UIElements.Button("Signup", 16, 110, 295, 92, 37, 17, 17);
        add(signup);
    }

    void handleActions(){
        signup.addActionListener(this);
        showPassword1.addActionListener(this);
        showPassword2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String enteredPassword1 = String.copyValueOf(createPassword.getPassword());
        String enteredPassword2 = String.copyValueOf(confirmPassword.getPassword());

        if (e.getSource().equals(signup)){
            User user = new User(firstName.getText(), lastName.getText(), phone.getText(), enteredPassword1);
            UserController userController = new UserController();
            int registered = userController.userRegister(user);

            if (registered > 0) {
                JOptionPane.showMessageDialog(null, "Successfully Registered!\nProceeding to Login...");
                LoginSignupPanel.CARD_LAYOUT_LOGIN.show(LoginSignupPanel.FORMS,"login");
            } else
                JOptionPane.showMessageDialog(null, "Failed to Register!");
        }
        if (e.getSource().equals(showPassword1)) {
            if (createPassword.getEchoChar() == (char) 0 && (!String.copyValueOf(createPassword.getPassword()).equals("Enter Your Password..."))) {
                showPassword1.setIcon(show);
                createPassword.setEchoChar('\u25cf');
            } else {
                showPassword1.setIcon(hide);
                createPassword.setEchoChar((char) 0);
            }
        }
        if (e.getSource().equals(showPassword2)) {
            if (confirmPassword.getEchoChar() == (char) 0 && (!String.copyValueOf(confirmPassword.getPassword()).equals("Enter Your Password..."))) {
                showPassword2.setIcon(show);
                confirmPassword.setEchoChar('\u25cf');
            } else {
                showPassword2.setIcon(hide);
                confirmPassword.setEchoChar((char) 0);
            }

        }
    }
}
