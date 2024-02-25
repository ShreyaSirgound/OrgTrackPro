import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AccountSetup {
    List<Admin> adminsList = Admin.getAdmins();
	String name, email, school;
	String enteredPassword = "";
	char[] p;
    BufferedReader in;
    public AccountSetup() throws IOException {
        in = new BufferedReader(new FileReader("data\\schools.txt"));
        ArrayList<String> schoolsList = new ArrayList<String>();
        String l = in.readLine();
        while(l != null){
            schoolsList.add(l);
            l = in.readLine();
        }
        in.close();

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
        mainPanel.setBounds(400, 120, 465, 540);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 25));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //input fields
        //name input
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(Color.white);
        JLabel label2 = new JLabel("What is your name? (First Last)", SwingConstants.RIGHT);
        label2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label2.setBackground(Color.decode("#76BEE8"));
        label2.setForeground(Color.gray);
        label2.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        JTextField input2 = new JTextField();
        
        input2.setColumns(30);
        panel2.add(label2);
        panel2.add(input2);
        mainPanel.add(panel2);

        //grade input
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.setBackground(Color.white);
        JLabel label3 = new JLabel("Enter your instituition", SwingConstants.LEFT);
        label3.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label3.setBackground(Color.decode("#76BEE8"));
        label3.setForeground(Color.gray);
        label3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panel3.add(label3);
        mainPanel.add(panel3);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new BoxLayout(pane3, BoxLayout.Y_AXIS));
        pane3.setBackground(Color.white);
        String[] schools = new String[schoolsList.size()];
        schools = schoolsList.toArray(schools);
        JComboBox<String> schoolsDropdown = new JComboBox(schools);
        pane3.add(schoolsDropdown);
        mainPanel.add(pane3);

        //email input
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel4.setBackground(Color.white);
        JLabel label4 = new JLabel("Enter your school email:", SwingConstants.RIGHT);
        
        label4.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label4.setBackground(Color.decode("#76BEE8"));
        label4.setForeground(Color.gray);
        label4.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        JTextField studentNumber = new JTextField();
        studentNumber.setColumns(30);
        panel4.add(label4);
        panel4.add(studentNumber);
        mainPanel.add(panel4);

        //password input
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        panel5.setBackground(Color.white);
        JLabel label5 = new JLabel("Enter your password:", SwingConstants.RIGHT);
        label5.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        label5.setBackground(Color.decode("#76BEE8"));
        label5.setForeground(Color.gray);
        label5.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        JPasswordField password = new JPasswordField(30);
        password.setEchoChar('*');
        panel5.add(label5);
        panel5.add(password);
        mainPanel.add(panel5);

        JButton createAcc = new JButton("Create my account");
        createAcc.setBackground(Color.decode("#76BEE8"));
        createAcc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        createAcc.setPreferredSize(new Dimension(435,45));
        createAcc.addActionListener(e -> {
        	name = input2.getText();
        	school = schoolsDropdown.getSelectedItem().toString();
        	email = studentNumber.getText();
        	p = password.getPassword();
        	enteredPassword = new String(p);
        	enteredPassword.trim();

            Admin newAdmin = new Admin(name, email, school, enteredPassword);
            System.out.println(Admin.exists(newAdmin));
            boolean userExists = Admin.exists(newAdmin);
            if(userExists == false){
                OrgRecords.curUser = newAdmin;
                Admin.addAdmin(OrgRecords.curUser);
                try {
                    OrgRecords.saveUser();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showConfirmDialog(frame, "  An account with this data has already been created. Please log in to use OrgTrack Pro services. ", 
                                            "Duplicate Input Error",
                                                    JOptionPane.OK_CANCEL_OPTION);
            }
            new LoginPage();
            frame.dispose();
        });
        mainPanel.add(createAcc);
    
        frame.add(mainPanel);
    
        frame.getRootPane().setDefaultButton(createAcc);
        frame.setVisible(true);
    }

}
