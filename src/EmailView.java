import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class EmailView {
    static String emailContent = "";
    static String emailSubject = "";
    static String[] credentials = new String[2];

    public EmailView(){
        //get email credentials before creating the email
        credentials = EmailValidation();

        JFrame frame = new JFrame("Email Sender");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(510, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        //frame.getContentPane().setBackground(Color.white);

        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setBounds(0, 0, 510, 450);

        JPanel pane1 = new JPanel(new FlowLayout());
        JLabel senderLbl = new JLabel("To: ");
        JTextField senderTf = new JTextField();
        senderLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        senderTf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        senderTf.setColumns(20);
        pane1.add(senderTf, FlowLayout.LEFT);
        pane1.add(senderLbl, FlowLayout.LEFT);

        JPanel pane2 = new JPanel(new FlowLayout());
        pane2.setBorder(new EmptyBorder(0, 0, 0, 20) );
        JLabel receiverLbl = new JLabel("From: ");
        JTextField receiverTf = new JTextField();
        receiverTf.setText(credentials[0]);
        receiverTf.setEditable(false);
        receiverLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        receiverTf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        receiverTf.setColumns(20);
        pane2.add(receiverTf, FlowLayout.LEFT);
        pane2.add(receiverLbl, FlowLayout.LEFT);

        JPanel pane5 = new JPanel(new FlowLayout());
        pane5.setBorder(new EmptyBorder(0, 0, 0, 25));
        JLabel subjectLbl = new JLabel("Subject: ");
        JTextField subjectTf = new JTextField();
        subjectLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        subjectTf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        subjectTf.setColumns(20);
        pane5.add(subjectTf, FlowLayout.LEFT);
        pane5.add(subjectLbl, FlowLayout.LEFT);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new BorderLayout());
        pane3.setBorder(new EmptyBorder(50, 30, 70, 50));
        JPanel pane4 = new JPanel(new BorderLayout());
        pane4.setBorder(new EmptyBorder(0, 0, 10, 0));
        JLabel contentLbl = new JLabel("Content: ");
        JTextArea contentTa = new JTextArea(10, 15);
        contentLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        contentTa.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        JButton sendEmail = new JButton("Send Email");
        sendEmail.setPreferredSize((new Dimension(110, 20)));
        sendEmail.setBackground(Color.blue);
        sendEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                emailSubject = subjectTf.getText();
                emailContent = contentTa.getText();
                sendEmail_actionPerformed(arg0, credentials[0], credentials[1], emailSubject, emailContent, senderTf.getText());
            }
        });
        pane4.add(contentLbl, BorderLayout.WEST);
        pane4.add(sendEmail, BorderLayout.EAST);
        pane3.add(pane4, BorderLayout.NORTH);
        pane3.add(contentTa);

        emailPanel.add(pane1);
        emailPanel.add(pane2);
        emailPanel.add(pane5);
        emailPanel.add(pane3);

        frame.add(emailPanel);
        frame.setVisible(true);

    }

    private static void sendEmail_actionPerformed(ActionEvent arg0, String eToSend, String password, String eSubject, String eMessage, String eToReceive){
        try{
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", true);
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            properties.put("mail.smtp.starttls.enable", true);
            properties.put("mail.transport.protocol", "smtp");

            Session session = Session.getInstance(properties, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(eToSend, password);
                }
            });

            Address addressTo = new InternetAddress(eToReceive);
            MimeMessage message = new MimeMessage(session);
            try{
                message.setRecipient(Message.RecipientType.TO, addressTo);
                message.setSubject(eSubject);
                message.setText(eMessage);  
                Transport.send(message);
                System.out.println("Email sent successfully!");
            } catch (MessagingException e){
                e.printStackTrace();
            }
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String[] EmailValidation(){
        String[] emailCredentials = new String[2];
        String senderEmail = "";
        String senderPassword = "";
        senderEmail = JOptionPane.showInputDialog("Enter your email: ");
        if(senderEmail.equals("")){
            JOptionPane.showMessageDialog(null, "Input Error");
            EmailValidation();
        }
        senderPassword = JOptionPane.showInputDialog("Enter your password: ");
        if(senderPassword.equals("")){
            JOptionPane.showMessageDialog(null, "Input Error");
            EmailValidation();
        }
        emailCredentials[0] = senderEmail;
        emailCredentials[1] = senderPassword;

        return emailCredentials;
    }

    
}

    