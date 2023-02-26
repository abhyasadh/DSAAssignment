package Nine.view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Objects;

public class UIElements {
    static Color textColor = Color.decode("#33515b");
    static Color placeholderColor = Color.decode("#588fa1");


    //Navigation Bar//////////////////////////////////////////////////////////////////////////////////////
    static class NavigationBar extends JPanel implements ActionListener, MouseListener {

        JButton home, stats, settings, prices, logout;
        JButton selectedButton;

        ImageIcon homeImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("dashboardTest.png")));
        ImageIcon statsImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("statsTest.png")));
        ImageIcon settingsImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("settingsTest.png")));
        ImageIcon pricesImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("pricesTest.png")));
        ImageIcon logoutImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("logoutTest.png")));

        NavigationBar() {
            addMouseListener(this);
            setLayout(null);
            setBounds(8, 96, 90, 410);
            setOpaque(false);
            setBackground(textColor);

            home = new Button(20, homeImage);
            home.setIcon(homeImage);
            home.setBounds(10, 10, 70, 70);
            home.addActionListener(this);
            home.addMouseListener(this);
            add(home);

            stats = new Button(100, statsImage);
            stats.addActionListener(this);
            stats.addMouseListener(this);
            add(stats);

            settings = new Button(180, settingsImage);
            settings.addActionListener(this);
            settings.addMouseListener(this);
            add(settings);

            prices = new Button(260, pricesImage);
            prices.addActionListener(this);
            prices.addMouseListener(this);
            add(prices);

            logout = new Button(340, logoutImage);
            logout.addActionListener(this);
            logout.addMouseListener(this);
            add(logout);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(textColor);
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 22, 22);

            super.paintComponent(g2d);
        }

        public void setSelected (JButton activeButton, ImageIcon image, int y){
            ArrayList<JButton> allButtons = new ArrayList<>();
            ArrayList<ImageIcon> allImages = new ArrayList<>();
            ArrayList<Integer> yValues = new ArrayList<>();

            allButtons.add(home); allButtons.add(stats); allButtons.add(settings); allButtons.add(prices); allButtons.add(logout);
            allImages.add(homeImage); allImages.add(statsImage); allImages.add(settingsImage); allImages.add(pricesImage); allImages.add(logoutImage);
            yValues.add(20); yValues.add(100); yValues.add(180); yValues.add(260); yValues.add(340);

            allButtons.remove(activeButton);
            allImages.remove(image);
            yValues.remove(Integer.valueOf(y+10));

            activeButton.setIcon(image);
            activeButton.setBounds(10,y,70,70);

            for (int i = 0; i<4; i++){
                Image scaledImages = allImages.get(i).getImage().getScaledInstance(42,42,Image.SCALE_SMOOTH);
                allButtons.get(i).setIcon(new ImageIcon(scaledImages));
                allButtons.get(i).setBounds(20,yValues.get(i),50,50);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(home)) {
                ApplicationPanel.CARD_LAYOUT_APPLICATION.show(ApplicationPanel.APPLICATION,"home");
                setSelected(home, homeImage, 10);
                selectedButton = home;
            }

            if (e.getSource().equals(stats)){
                ApplicationPanel.CARD_LAYOUT_APPLICATION.show(ApplicationPanel.APPLICATION,"stats");
                setSelected(stats, statsImage, 90);
                selectedButton = stats;
            }

            if (e.getSource().equals(settings)) {
                ApplicationPanel.CARD_LAYOUT_APPLICATION.show(ApplicationPanel.APPLICATION,"settings");
                setSelected(settings, settingsImage, 170);
                selectedButton = settings;
            }

            if (e.getSource().equals(prices)) {
                ApplicationPanel.CARD_LAYOUT_APPLICATION.show(ApplicationPanel.APPLICATION,"prices");
                setSelected(prices, pricesImage, 250);
                selectedButton = prices;
            }

            if (e.getSource().equals(logout)){
                JButton selected = selectedButton;

                int reply = JOptionPane.showConfirmDialog(LandingPage.LANDING_PANEL, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logoutImage);
                if (reply == JOptionPane.YES_OPTION) LandingPage.CARD_LAYOUT_LANDING_PAGE.show(LandingPage.LANDING_PANEL,"login");
                else {
                    if (selected == home) setSelected(home, homeImage, 10);
                    if (selected == stats) setSelected(stats, statsImage, 90);
                    if (selected == settings) setSelected(settings, settingsImage, 170);
                    if (selected == prices) setSelected(prices, pricesImage, 250);
                }
            }
        }

        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {
            if (e.getSource().equals(home)){
                setSelected(home,homeImage,10);
            }
            if (e.getSource().equals(stats)){
                setSelected(stats,statsImage,90);
            }
            if (e.getSource().equals(settings)){
                setSelected(settings,settingsImage,170);
            }
            if (e.getSource().equals(prices)){
                setSelected(prices,pricesImage,250);
            }
            if (e.getSource().equals(logout)){
                setSelected(logout,logoutImage,330);
            }
        }

        public void mouseExited(MouseEvent e) {
            Point p = new Point(e.getLocationOnScreen());
            SwingUtilities.convertPointFromScreen(p, this);
            if (!this.contains(p)) {
                setSelected(home, homeImage, 10);
                if (selectedButton == home) setSelected(home, homeImage, 10);
                if (selectedButton == stats) setSelected(stats, statsImage, 90);
                if (selectedButton == settings) setSelected(settings, settingsImage, 170);
                if (selectedButton == prices) setSelected(prices, pricesImage, 250);
            }
        }
    }



    //Show Password Button////////////////////////////////////////////////////////////////////////////

    static class ShowPassword extends JButton {
        ImageIcon show = new ImageIcon("Public\\Show.png");

        ShowPassword(int x, int y, int width, int height){
            setIcon(show);
            setAlignmentX(CENTER_ALIGNMENT);
            setAlignmentY(CENTER_ALIGNMENT);
            setBackground(textColor);
            setBounds(x+1,y+7,width-6,height-14);
            setBorderPainted(false);
            setFocusPainted(false);
            setEnabled(false);
            setDisabledIcon(show);
            setFocusable(false);
        }
    }




    //Normal Button////////////////////////////////////////////////////////////////////////////////

    static class Button extends JButton {
        int arcWidth;
        int arcHeight;

        public Button(String text, int fontSize, int x, int y, int width, int height, int arcWidth, int arcHeight) {
            super(text);

            this.arcWidth=arcWidth;
            this.arcHeight=arcHeight;

            setFont(new Font("Arial",Font.BOLD,fontSize));
            setBounds(x, y, width, height);
            setForeground(Color.white);
            setFocusPainted(false);
            setContentAreaFilled(false);
        }

        public Button(int y, ImageIcon image) {
            setBounds(20, y, 50, 50);
            setFocusPainted(false);
            Image scaledIcon = image.getImage().getScaledInstance(42,42,Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledIcon));
            setHorizontalTextPosition(SwingConstants.CENTER);
            setContentAreaFilled(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(textColor);
            g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, arcWidth, arcHeight);
            super.paintComponent(g2d);
        }

        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            if (!Objects.equals(getText(), "")) g2d.setColor(textColor);
            else g2d.setColor(placeholderColor);
            g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, arcWidth, arcHeight);
        }
    }



    //Login & Signup Buttons/////////////////////////////////////////////////////////////////////////

    static class MainButtons extends JButton{
        public MainButtons (String text, int x){
            setText(text);
            setFont(new Font("Cooper Black", Font.PLAIN, 26));
            setAlignmentX(CENTER_ALIGNMENT);
            setAlignmentY(CENTER_ALIGNMENT);
            setBounds(x, 6, 150, 60);
            setContentAreaFilled(false);
            setForeground(UIElements.textColor);
            setFocusPainted(false);
            setBorder(BorderFactory.createMatteBorder(0,0,4,0,UIElements.textColor));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.white);
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 22, 22);

            super.paintComponent(g2d);
        }
    }



    //Next Buttons/////////////////////////////////////////////////////////////////////////////////

    static class NextButton extends JButton{
        public NextButton(int x, int y){
            setText("\u279c");
            setFont(new Font("",Font.PLAIN,26));
            setForeground(UIElements.textColor);
            setBackground(Color.white);
            setBounds(x,y,38,38);
            setBorder(BorderFactory.createEmptyBorder(0,0,2,0));
            setOpaque(false);
            setEnabled(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.white);
            g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 17, 17);
            super.paintComponent(g2d);
        }

        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setColor(textColor);
            g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 17, 17);
        }
    }



    //Label///////////////////////////////////////////////////////////////////////////////////

    static class Label extends JLabel{
        Label(String text, int fontSize){
            setText(text);
            setHorizontalAlignment(SwingConstants.CENTER);
            setForeground(Color.white);
            setFont(new Font("Cooper Black",Font.PLAIN,fontSize));
            setBackground(textColor);
            setOpaque(false);
            setBounds(2, 2, 382, 50);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(textColor);
            g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 22, 22);
            super.paintComponent(g2d);
        }
        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setColor(textColor);
            g2d.drawRoundRect(0, 0, getWidth()-2, getHeight()-2, 22, 22);
        }
    }




    //Round Text Field////////////////////////////////////////////////////////////////////////////////

    static class RoundedJTextField extends JTextField {
        private Shape shape;
        public RoundedJTextField(String placeholder, int fontSize, int x, int y, int width, int height) {
            setText(placeholder);
            setBounds(x, y, width, height);
            setFont(new Font("Arial", Font.PLAIN, fontSize));
            setOpaque(false);
            setForeground(placeholderColor);
            setBorder(BorderFactory.createCompoundBorder(
                    getBorder(),
                    BorderFactory.createEmptyBorder(5, 8, 5, 5)));
            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(placeholder)) {
                        setText("");
                        setForeground(textColor);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setForeground(placeholderColor);
                        setText(placeholder);
                    }
                }
            });
        }

        RoundedJTextField(String placeholder, int fontSize, int x, int y, int width, int height, JButton nextButton) {
            setText(placeholder);
            setFont(new Font("Arial", Font.PLAIN, fontSize));
            setOpaque(false);
            setBounds(x, y, width, height);
            setForeground(placeholderColor);
            setBorder(BorderFactory.createCompoundBorder(
                    getBorder(),
                    BorderFactory.createEmptyBorder(5, 8, 5, 5)));
            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(placeholder)) {
                        nextButton.setEnabled(true);
                        setText("");
                        setForeground(textColor);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        nextButton.setEnabled(false);
                        setForeground(placeholderColor);
                        setText(placeholder);
                    }
                }
            });
        }

        public RoundedJTextField(String placeholder, String text, int fontSize, int x, int y, int width, int height) {
            setText(text);
            setBounds(x, y, width, height);
            setFont(new Font("Arial", Font.PLAIN, fontSize));
            setOpaque(false);
            setForeground(placeholderColor);
            setBorder(BorderFactory.createCompoundBorder(
                    getBorder(),
                    BorderFactory.createEmptyBorder(5, 8, 5, 5)));
            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(placeholder)) {
                        setText("");
                        setForeground(textColor);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setForeground(placeholderColor);
                        setText(placeholder);
                    }
                }
            });
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.white);
            g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 17, 17);
            super.paintComponent(g2d);
        }
        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setColor(textColor);
            g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 17, 17);
        }
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 17, 17);
            }
            return shape.contains(x, y);
        }
    }




    //Round Password Field///////////////////////////////////////////////////////////////////////////

    static class PasswordField extends JPasswordField {
        private Shape shape;

        PasswordField(String placeholder,int fontSize, int x, int y, int width, int height, JButton showPasswordButton){
            setText(placeholder);
            setEchoChar((char)0);
            setFont(new Font("Arial",Font.PLAIN,fontSize));
            setOpaque(false);
            setBounds(x,y,width,height);
            setForeground(placeholderColor);
            setBorder(BorderFactory.createCompoundBorder(
                    getBorder(),
                    BorderFactory.createEmptyBorder(5, 8, 5, 5)));
            addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    String ps = String.copyValueOf(getPassword());
                    if (ps.equals(placeholder)) {
                        showPasswordButton.setDisabledIcon(new ImageIcon("Public\\Show.png"));
                        showPasswordButton.setEnabled(true);
                        setEchoChar('\u25cf');
                        setText("");
                        setForeground(textColor);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    String ps = String.copyValueOf(getPassword());
                    if (ps.isEmpty()) {
                        showPasswordButton.setEnabled(false);
                        setEchoChar((char)0);
                        setForeground(placeholderColor);
                        setText(placeholder);
                    }
                }}
            );
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.white);
            g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 17, 17);
            super.paintComponent(g2d);
        }
        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setColor(textColor);
            g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 17, 17);
        }
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 17, 17);
            }
            return shape.contains(x, y);
        }
    }



    //Round Panels///////////////////////////////////////////////////////////////////////////////////

    static class Panels extends JPanel {
        Panels(LayoutManager style, boolean otherPanels, int x, int y){
            setLayout(style);
            setOpaque(false);

            if (otherPanels){
                JPanel corners = new JPanel();
                corners.setBackground(textColor);
                corners.setBounds(2,y,10,10);
                add(corners);

                corners = new JPanel();
                corners.setBackground(textColor);
                corners.setBounds(372+x,y,10,10);
                add(corners);
            }
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.white);
            g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 22, 22);
            super.paintComponent(g2d);
        }

        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setColor(textColor);
            g2d.drawRoundRect(1, 1, getWidth()-4, getHeight()-4, 22, 22);
        }
    }

    static class MyScrollBarUI extends BasicScrollBarUI {
        MyScrollBarUI(JScrollPane scrollPane){
            scrollPane.setLayout(new ScrollPaneLayout() {
                @Override
                public void layoutContainer(Container parent) {
                    JScrollPane scrollPane = (JScrollPane) parent;

                    Rectangle availR = scrollPane.getBounds();
                    availR.x = availR.y = 0;

                    Insets parentInsets = parent.getInsets();
                    availR.x = parentInsets.left;
                    availR.y = parentInsets.top;
                    availR.width -= parentInsets.left + parentInsets.right;
                    availR.height -= parentInsets.top + parentInsets.bottom;

                    Rectangle vsbR = new Rectangle();
                    vsbR.width = 12;
                    vsbR.height = availR.height;
                    vsbR.x = availR.x + availR.width - vsbR.width;
                    vsbR.y = availR.y;

                    if (viewport != null) {
                        viewport.setBounds(availR);
                    }
                    if (vsb != null) {
                        vsb.setVisible(true);
                        vsb.setBounds(vsbR);
                    }
                }
            });
        }

        private final Dimension d = new Dimension();

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new JButton() {
                @Override
                public Dimension getPreferredSize() {
                    return d;
                }
            };
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new JButton() {
                @Override
                public Dimension getPreferredSize() {
                    return d;
                }
            };
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Color color;
            JScrollBar sb = (JScrollBar) c;
            if (!sb.isEnabled() || r.width > r.height) {
                return;
            } else if (isDragging) {
                color = Color.DARK_GRAY;
            } else if (isThumbRollover()) {
                color = Color.LIGHT_GRAY;
            } else {
                color = Color.GRAY;
            }
            g2.setPaint(color);
            g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
            g2.setPaint(Color.WHITE);
            g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
            g2.dispose();
        }

        @Override
        protected void setThumbBounds(int x, int y, int width, int height) {
            super.setThumbBounds(x, y, width, height);
            scrollbar.repaint();
        }
    }
}