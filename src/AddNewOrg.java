import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class AddNewOrg {
    public AddNewOrg(String modify, String addition, Component c, String n, String m, String p, String d, String a) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(510, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.white);

        JLabel title = new JLabel(modify + " New " + addition);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title.setForeground(Color.gray);
        title.setBounds(20, 0, 250, 50);
        frame.add(title);

        JPanel dataAdd = new JPanel();
        dataAdd.setBounds(20, 40, 450, 350);
		dataAdd.setLayout(new BoxLayout(dataAdd, BoxLayout.Y_AXIS));
		dataAdd.setBorder(new EmptyBorder(10, 10, 10, 30));
		
		JLabel nameLbl = new JLabel(addition + " Name");
		nameLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField nameInput = new JTextField(50);
		nameInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		nameInput.setText(n);

        JLabel emailLbl = new JLabel("Email");
		emailLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField emailInput = new JTextField(50);
		emailInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		emailInput.setText(m);

        JLabel phoneLbl = new JLabel("Phone Number");
		phoneLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField phoneNumInput = new JTextField(50);
		phoneNumInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		phoneNumInput.setText(p);
		
		JLabel descLbl = new JLabel("Description");
		descLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField descInput = new JTextField(50);
		descInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		descInput.setText(d);

		JLabel addressLbl = new JLabel("Address");
		addressLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		JTextField orgAddressInput = new JTextField(50);
		orgAddressInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		orgAddressInput.setText(a);

		//button to update an instance of the event object
	    JButton updateEvent = new JButton(modify + " " + addition);
		updateEvent.setBounds(30, 450, 60, 40);
		updateEvent.addActionListener(e -> {
			//validating that all inputs have been added
			if(nameInput.getText().equals("") || emailInput.getText().equals("") || phoneNumInput.getText().equals("") || descInput.getText().equals("") || orgAddressInput.getText().equals("")){
				JOptionPane.showMessageDialog(frame,"Please enter information in all fields.","Alert",JOptionPane.WARNING_MESSAGE);
				return;
			} else if(nameInput.getText().equals(null) || emailInput.getText().equals(null) || phoneNumInput.getText().equals(null) || descInput.getText().equals(null) || orgAddressInput.getText().equals(null)) {
				JOptionPane.showMessageDialog(frame,"Please enter information in all fields.","Alert",JOptionPane.WARNING_MESSAGE);
				return;
			}
			//if all inputs validated the data is saved accordingly
			if(addition.equals("Organization")){
				Org newOrg = new Org(nameInput.getText(), emailInput.getText(), phoneNumInput.getText(), descInput.getText(), orgAddressInput.getText());
				Org.addOrg(newOrg);
				try {
					frame.dispose();
					Main.getMainFrame().dispose();
					OrgRecords.saveOrg(); 
					new MainFrame();
					JOptionPane.showMessageDialog(c, addition + " added successfully!");  
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if(addition.equals("Contact")){
				Contact newContact = new Contact(nameInput.getText(), emailInput.getText(), phoneNumInput.getText(), descInput.getText(), orgAddressInput.getText());
				Contact.addContact(newContact);
				try {
					frame.dispose();
					Main.getMainFrame().dispose();
					OrgData.saveContact();
					new MainFrame();
					JOptionPane.showMessageDialog(c, addition + " added successfully!");  
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
        });

        dataAdd.add(nameLbl);
		dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
		dataAdd.add(nameInput);
		dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
		dataAdd.add(descLbl);
		dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
		dataAdd.add(descInput);
        dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
        dataAdd.add(emailLbl);
		dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
		dataAdd.add(emailInput);
		dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
        dataAdd.add(phoneLbl);
		dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
		dataAdd.add(phoneNumInput);
		dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
		dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
		dataAdd.add(addressLbl);
		dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
		dataAdd.add(orgAddressInput);
		dataAdd.add(Box.createRigidArea(new Dimension(0, 5)));
		dataAdd.add(updateEvent);
        frame.add(dataAdd);

        frame.setVisible(true);
    }
}
