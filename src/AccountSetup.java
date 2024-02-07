import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class AccountSetup {
    public AccountSetup() {
        JFrame frame = new JFrame("Create an account");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#76BEE8"));

        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Let's get to know you a bit");
        headerLabel.setForeground(Color.white);
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 34));
        headerPanel.add(headerLabel);
        headerPanel.setBounds(385, 50, 500, 60);
        headerPanel.setBackground(Color.decode("#76BEE8"));
        frame.add(headerPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(400, 120, 465, 500);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 25));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.add(mainPanel);

        frame.setVisible(true);
    }
}
