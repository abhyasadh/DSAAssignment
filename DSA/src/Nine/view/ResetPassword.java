package Nine.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.Random;

public class ResetPassword extends JPanel implements ActionListener {

    JButton loginPage, signupPage, showPassword1, showPassword2, confirmPhone, confirmOTP, confirm;
    JTextField phone, otp1, otp2, otp3, otp4;
    JTextField[] OTPs;
    char[] placeholder;
    JPasswordField createPassword, confirmPassword;
    JLabel label;
    String otp;

    ImageIcon show;
    ImageIcon hide;

    ResetPassword(){
        setLayout(null);
        setOpaque(false);

        label=new JLabel("Reset Password");
        label.setFont(new Font("Cooper Black",Font.PLAIN,20));
        label.setBounds(0,75,310,22);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(UIElements.textColor);
        add(label);

        mainButtons();
        otherButtons();
        textFields();
        otpFields();
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
        signupPage.setBorderPainted(false);
        add(signupPage);

        show = login.show;
        hide = login.hide;
    }

    void textFields() {
        confirmPassword = new UIElements.PasswordField("Confirm New Password...", 14, 26, 258, 260, 38, showPassword2);
        confirmPassword.setEnabled(false);
        confirmPassword.setDisabledTextColor(UIElements.placeholderColor);
        confirmPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirm.doClick();
                }
                if (e.getKeyCode()==KeyEvent.VK_UP){
                    createPassword.requestFocus();
                }
            }
        });
        add(confirmPassword);

        createPassword = new UIElements.PasswordField("New Password...", 14, 26, 208, 260, 38, showPassword1);
        createPassword.setEnabled(false);
        createPassword.setDisabledTextColor(UIElements.placeholderColor);
        createPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER||e.getKeyCode()==KeyEvent.VK_DOWN) {
                    confirmPassword.setEnabled(true);
                    confirmPassword.requestFocus();
                }
            }
        });
        add(createPassword);

        phone = new UIElements.RoundedJTextField("Enter Your Phone Number...", 14, 26, 108, 260, 38, confirmPhone);
        phone.setDisabledTextColor(UIElements.textColor);
        phone.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_0 && e.getKeyChar() != KeyEvent.VK_1 && e.getKeyChar() != KeyEvent.VK_2 && e.getKeyChar() != KeyEvent.VK_3 && e.getKeyChar() != KeyEvent.VK_4 && e.getKeyChar() != KeyEvent.VK_5 && e.getKeyChar() != KeyEvent.VK_6 && e.getKeyChar() != KeyEvent.VK_7 && e.getKeyChar() != KeyEvent.VK_8 && e.getKeyChar() != KeyEvent.VK_9 && e.getKeyChar() != KeyEvent.VK_BACK_SPACE && e.getKeyChar() != KeyEvent.VK_ENTER && e.getKeyChar() != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirmPhone.doClick();
                }
            }
        });
        add(phone);
    }

    void otherButtons(){
        confirmPhone= new JButton("\u279c");
        confirmPhone.setFont(new Font("",Font.PLAIN,26));
        confirmPhone.setBackground(Color.white);
        confirmPhone.setForeground(UIElements.textColor);
        confirmPhone.setBounds(250,115,32,24);
        confirmPhone.setBorder(new LineBorder(Color.white, 2, true));
        confirmPhone.setEnabled(false);
        confirmPhone.setFocusPainted(false);
        add(confirmPhone);

        confirmOTP= new UIElements.NextButton(248,158);
        add(confirmOTP);

        showPassword1 = new UIElements.ShowPassword(248, 208, 38, 38);
        add(showPassword1);

        showPassword2 = new UIElements.ShowPassword(248, 258, 38, 38);
        add(showPassword2);

        confirm = new UIElements.Button("Confirm", 16, 108, 308, 95, 38, 17, 17);
        confirm.setEnabled(false);
        add(confirm);
    }

    void otpFields(){
        LineBorder lineBorder = new LineBorder(UIElements.textColor, 2, true);

        OTPs = new JTextField[]{otp1, otp2, otp3, otp4};
        final int[] otpCounter = {0};

        placeholder = new char[]{'O', 'T', 'P', ' '};
        for (int i=0; i<4; i++){
            final int[] j = {0};

            OTPs[i] = new UIElements.RoundedJTextField(String.valueOf(placeholder[i]),14,26+(55*i),158,39,38);

            OTPs[i].getActionMap().get(DefaultEditorKit.beepAction).setEnabled(false);
            OTPs[i].setBorder(lineBorder);
            OTPs[i].setDisabledTextColor(UIElements.placeholderColor);
            OTPs[i].setBorder(BorderFactory.createCompoundBorder(
                    OTPs[i].getBorder(),
                    BorderFactory.createEmptyBorder(5, 12, 5, 5)));

            int finalI = i;
            final int[] notClicked = {0};

            OTPs[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    //checking the number of characters
                    if (e.getKeyChar()!=8) {j[0]++; otpCounter[0]++;}

                    //only numbers allowed
                    if (e.getKeyChar() != KeyEvent.VK_0 && e.getKeyChar() != KeyEvent.VK_1 && e.getKeyChar() != KeyEvent.VK_2 && e.getKeyChar() != KeyEvent.VK_3 && e.getKeyChar() != KeyEvent.VK_4 && e.getKeyChar() != KeyEvent.VK_5 && e.getKeyChar() != KeyEvent.VK_6 && e.getKeyChar() != KeyEvent.VK_7 && e.getKeyChar() != KeyEvent.VK_8 && e.getKeyChar() != KeyEvent.VK_9 && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                        j[0]--;
                        otpCounter[0]--;
                        e.consume();
                    }

                    //change focus after 1 number
                    if (j[0]==1){
                        if (finalI!=3) {
                            OTPs[finalI +1].setEnabled(true);
                            OTPs[finalI +1].requestFocus();
                        }
                    }

                    //do not allow more than one number
                    if (j[0]>1){
                        j[0]--;
                        otpCounter[0]--;
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode()==KeyEvent.VK_RIGHT){
                        try {
                            if (OTPs[finalI +1].isEnabled())
                                OTPs[finalI +1].requestFocus();
                        } catch (Exception ignore){}
                    }
                    if (e.getKeyCode()==KeyEvent.VK_LEFT){
                        try {
                            OTPs[finalI -1].requestFocus();
                        } catch (Exception ignore){}
                    }
                    if (e.getKeyChar()==8) {
                        if (j[0] > 0) {
                            j[0]--;
                            otpCounter[0]--;
                        } else if (j[0] == 0) {
                            try {
                                OTPs[finalI - 1].requestFocus();
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_BACK_SPACE);
                                robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                            } catch (Exception ignore) {
                            }
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (otpCounter[0]==4&&notClicked[0]==0) {
                        confirmOTP.setEnabled(true);
                        confirmOTP.doClick();
                        notClicked[0]++;
                    }
                    if (e.getKeyCode()==KeyEvent.VK_ENTER&&otpCounter[0]==4){
                        confirmOTP.doClick();
                    }
                }
            });

            OTPs[i].setEnabled(false);
            add(OTPs[i]);
        }
    }

    String otpGenerator(){
        String str = "";
        Random rand = new Random();
        StringBuilder strBuilder = new StringBuilder(str);
        for (int i = 0; i < 4; i++) {
            int random = rand.nextInt(0, 10);
            strBuilder.append(random);
        }
        return String.valueOf(strBuilder);
    }

    void handleActions(){
        confirmPhone.addActionListener(this);
        confirmOTP.addActionListener(this);
        confirm.addActionListener(this);

        showPassword1.addActionListener(this);
        showPassword2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String enteredPassword1 = String.copyValueOf(createPassword.getPassword());
        String enteredPassword2 = String.copyValueOf(confirmPassword.getPassword());

        if(e.getSource().equals(confirm)){
            LoginSignupPanel.CARD_LAYOUT_LOGIN.show(LoginSignupPanel.FORMS,"login");
        }
        if (e.getSource().equals(confirmPhone)){
            this.otp=otpGenerator();
            System.out.println(otp);
            for (int i=0; i<4; i++){
                OTPs[i].setForeground(UIElements.textColor);
            }
            phone.setEnabled(false);
            OTPs[0].setEnabled(true);
            OTPs[0].requestFocus();

            confirmPhone.setEnabled(false);
        }
        if (e.getSource().equals(confirmOTP)){
            StringBuilder enteredOTP = new StringBuilder();
            for (int i=0; i<4; i++){
                enteredOTP.append(OTPs[i].getText());
            }
            if (Objects.equals(String.valueOf(enteredOTP), otp)){
                confirmOTP.setEnabled(false);
                createPassword.setEnabled(true);
                createPassword.requestFocus();
                confirmPassword.setEnabled(true);
                confirm.setEnabled(true);
                for (int i=0; i<4; i++){
                    OTPs[i].setDisabledTextColor(UIElements.textColor);
                    OTPs[i].setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(null,"Invalid OTP!");
            }
        }
        if (e.getSource().equals(showPassword1)||e.getSource().equals(showPassword2)||e.getSource().equals(confirmPhone)||e.getSource().equals(confirmOTP)) {
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
        } else {
            confirmPhone.setEnabled(false);
            confirmOTP.setEnabled(false);

            phone.setEnabled(true);
            phone.setForeground(UIElements.placeholderColor);
            phone.setText("Enter Your Phone Number...");

            if (!enteredPassword1.equals("New Password...")) {
                createPassword.setForeground(UIElements.placeholderColor);
                createPassword.setText("New Password...");
                createPassword.setEnabled(false);
                createPassword.setEchoChar((char) 0);
                showPassword1.setIcon(show);
                showPassword1.setEnabled(false);
            }
            if (!enteredPassword2.equals("Confirm New Password...")) {
                confirmPassword.setForeground(UIElements.placeholderColor);
                confirmPassword.setText("Confirm New Password...");
                confirmPassword.setEnabled(false);
                confirmPassword.setEchoChar((char) 0);
                showPassword2.setIcon(show);
                showPassword2.setEnabled(false);
            }
        }
    }
}
