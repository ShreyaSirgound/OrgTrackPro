import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LoginPage {
	char[] enteredPassword;
	String curPassword = "";
	String curUsername = "";
	JPasswordField password;
	JTextField username;
    public LoginPage() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#76BEE8"));
        
        JLabel headerLabel = new JLabel("Login to get started!");
        headerLabel.setForeground(Color.white);
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 34));
        headerLabel.setBounds(465, 50, 350, 60);
        frame.add(headerLabel);

        //login input area
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        loginPanel.setBackground(Color.white);
        loginPanel.setBounds(460, 150, 350, 300);

        //username input
        JPanel filler = new JPanel();
        filler.setPreferredSize(new Dimension(350, 50));
        filler.setBackground(Color.white);
        loginPanel.add(filler);
        JLabel userDesc = new JLabel("Username");
        userDesc.setForeground(Color.gray);
        userDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        loginPanel.add(userDesc);
        username = new JTextField(21);
        username.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        loginPanel.add(username);

        JPanel filler2 = new JPanel();
        filler2.setPreferredSize(new Dimension(350, 10));
        filler2.setBackground(Color.white);
        loginPanel.add(filler2);

        //password input
        JLabel passwordDesc = new JLabel("Password");
        passwordDesc.setForeground(Color.gray);
        passwordDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        loginPanel.add(passwordDesc);

        password = new JPasswordField(21);
        password.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        password.setEchoChar('*');
        loginPanel.add(password);

        JPanel filler3 = new JPanel();
        filler3.setPreferredSize(new Dimension(350, 10));
        filler3.setBackground(Color.white);
        loginPanel.add(filler3);

        //login button
        JButton login = new JButton("Login");
        login.setBackground(Color.decode("#76BEE8"));
        login.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        login.setPreferredSize(new Dimension(340,45));

        login.addActionListener(e -> {
            if(validate()) {
                new MainFrame();
            } else {
                new LoginPage();
            }
            frame.dispose();
        });
        loginPanel.add(login);

        frame.add(loginPanel);
        
        //button to create an account
        JPanel newAccountPanel = new JPanel();
        newAccountPanel.setBounds(425, 0, 200, 75);
        JButton newAccount = new JButton("Create an account");
        newAccount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        newAccount.setBounds(460, 460, 350, 45);
        newAccount.addActionListener(e -> {
            new AccountSetup();
            frame.dispose();
        });
        
        newAccountPanel.add(newAccount);
        frame.add(newAccount);
        frame.setVisible(true);
        
    }
    	public boolean validate() {
    		enteredPassword = password.getPassword();
    		for(int i = 0; i < enteredPassword.length; i++) {
    			curPassword += enteredPassword[i];
    		}
    		curUsername = username.getText();
    		System.out.println(curUsername);
    		System.out.println(curPassword);
    		for(Student s : Student.getStudents()) {
    			System.out.print(s.getName() + " ");
    			System.out.println(s.getPassword());
    			if(s.getName().equals(curUsername) && s.getPassword().equals(curPassword)) {
    				MainFrame.curUser = s;
    				return true;
    			}
    		}
    		return false;
    }
}