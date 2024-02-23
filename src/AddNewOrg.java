import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class AddNewOrg {
    public AddNewOrg() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(510, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.white);

        JLabel title = new JLabel("Add New Organization");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title.setForeground(Color.gray);
        title.setBounds(20, 0, 250, 50);
        frame.add(title);

        JPanel eventEdit = new JPanel();
        eventEdit.setBounds(20, 40, 450, 350);
		eventEdit.setLayout(new BoxLayout(eventEdit, BoxLayout.Y_AXIS));
		eventEdit.setBorder(new EmptyBorder(10, 10, 10, 30));
		
		JLabel nameLbl = new JLabel("Organization Name");
		nameLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField orgName = new JTextField(50);
		orgName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

        JLabel emailLbl = new JLabel("Email");
		emailLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField orgEmail = new JTextField(50);
		orgEmail.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

        JLabel phoneLbl = new JLabel("Contact Number");
		phoneLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField orgPhoneNum = new JTextField(50);
		orgPhoneNum.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		
		JLabel descLbl = new JLabel("Description");
		descLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField orgDesc = new JTextField(50);
		orgDesc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

		JLabel addressLbl = new JLabel("Location");
		addressLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField orgAddress = new JTextField(50);
		orgAddress.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

		//button to update an instance of the event object
	    JButton updateEvent = new JButton("Add Organization");
		updateEvent.setBounds(30, 450, 60, 40);
		updateEvent.addActionListener(e -> {
            Org newOrg = new Org(orgName.getText(), orgEmail.getText(), orgPhoneNum.getText(), orgDesc.getText(), orgAddress.getText());
            Org.addOrg(newOrg);
            try {
				frame.dispose();
				MainFrame.frame.dispose();
                MainFrame.saveOrg();
				new MainFrame();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        eventEdit.add(nameLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(orgName);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(descLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(orgDesc);
        eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
        eventEdit.add(emailLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(orgEmail);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
        eventEdit.add(phoneLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(orgPhoneNum);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(addressLbl);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(orgAddress);
		eventEdit.add(Box.createRigidArea(new Dimension(0, 5)));
		eventEdit.add(updateEvent);
        frame.add(eventEdit);

        frame.setVisible(true);
    }
}
