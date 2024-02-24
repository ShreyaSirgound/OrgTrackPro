import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class OrgData extends JPanel {
    static JFrame frame;
    static BufferedWriter out;
    static Admin curUser;
    DefaultTableModel model;
    JTable table;
    TableRowSorter<TableModel> rowSorter;
    JScrollPane sp;

    public OrgData() {
        setLayout(null);

        //titlebar
        JPanel titlebar = new JPanel();
        titlebar.setBackground(Color.decode("#76BEE8"));
        titlebar.setLayout(new BorderLayout());
        titlebar.setBorder(new EmptyBorder(10, 10, 10, 80));
        titlebar.setBounds(0, 0, 1280, 60);
        //titlebar.add(Common.getImage("orgtrackpro-logo.png"), BorderLayout.EAST); //TODO make smaller logo
        add(titlebar);

        //sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBorder( new EmptyBorder(15, 15, 15, 15));
        sidebar.setBackground(Color.decode("#3E3F40"));
        sidebar.setBounds(0, 0, 300, 720);
        
        //button to add a new contact to the records
		JButton addContact = new JButton("Add A Contact");
		addContact.setBounds(1050, 160, 180, 50);
		addContact.addActionListener(e -> {
			try {
				new AddNewOrg("Contact", this);
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}
		});
        add(addContact);

        //button to delete a contact from the records
		JButton deleteContact = new JButton("Delete An Contact");
		deleteContact.setBounds(1050, 220, 180, 50);
		deleteContact.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent ae) {
                DefaultTableModel t = (DefaultTableModel) table.getModel();
                if(table.getSelectedRowCount()==1) {
                    Contact.removeContact(Contact.getContacts().get(table.getSelectedRow()));
                    t.removeRow(table.getSelectedRow());
                    try {
                        saveContact();
                        new MainFrame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if(table.getRowCount()==0) {
                        JOptionPane.showMessageDialog(null, "Table is empty.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select only one row to delete.");
                    }
                }
            }
        });
        add(deleteContact);

        //button to add a new note 
		JButton addNote = new JButton("Create a Note");
		addNote.setBounds(1050, 280, 180, 50);
		addNote.addActionListener(e -> {
			try {
				new Notepad();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}
		});
        add(addNote);

        JLabel title1 = new JLabel("Organization Data");
        title1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title1.setForeground(Color.gray);
        title1.setBounds(125, 10, 250, 50);
        add(title1);

        //data to be displayed in the JTable
        String[][] data = new String[Contact.getContacts().size()][6];
        int contactsNum = 0;
        for(Contact c : Contact.getContacts()){
            data[contactsNum][0] = String.valueOf(contactsNum);
            data[contactsNum][1] = c.getName();
            data[contactsNum][2] = c.getEmail();
            data[contactsNum][3] = c.getPhoneNum();
            data[contactsNum][4] = c.getOrg();
            data[contactsNum][5] = c.getDesc();
            contactsNum++;
        }
        String[] columnNames = {"Order #", "Contact Name", "Email", "Phone Number", "Address", "Description"};
       
        //initializing the data table
        model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowHeight(25);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        sp = new JScrollPane(table);
        sp.setBounds(120, 110, 900, 500);
        add(sp);

        //creating the search filter feature
        JLabel lbl = new JLabel("Enter Search:");
        lbl.setBackground(Color.white);
        lbl.setForeground(Color.gray);
        lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        lbl.setBounds(155, 60, 150, 50);
        JTextField tf = new JTextField();
        tf.setEditable(true);
        tf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        tf.setBounds(280, 70, 550, 35);
        add(lbl);
        add(tf);

        //dynamically searching the table
        table.setRowSorter(rowSorter);
        tf.getDocument().addDocumentListener(new DocumentListener(){

            //if user enters a search entry
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = tf.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            //if user deletes a character sequence from their search entry
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = tf.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            //if user changes a character sequence from their search entry
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

    }

    protected static void saveContact() throws IOException {
        out = new BufferedWriter(new FileWriter("data\\orgContacts.txt"));
        
        for(Contact c : Contact.getContacts()) {
            out.write(c.getName() + "|");
        }
        out.newLine();
        for(Contact c : Contact.getContacts()) {
            out.write(c.getEmail() + "|");
        }
        out.newLine();
        for(Contact c : Contact.getContacts()) {
            out.write(c.getPhoneNum() + "|");
        }
        out.newLine();
        for(Contact c : Contact.getContacts()) {
            out.write(c.getOrg() + "|");
        }
        out.newLine();
        for(Contact c : Contact.getContacts()) {
            out.write(c.getDesc() + "|");
        }
        out.newLine();
        out.close();
    }
}
