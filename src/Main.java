import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	final static int MAX = 10000;
    static BufferedReader in, in2, in3; 
	static BufferedWriter out;
	static int numOfAccounts;
	static String fullName;
    static String[] names, emails, passwords, schools, phoneNums, addresses, descs;
	static String fileName1 = "data\\admins.txt";
	static Admin curUser;
    public static void main(String[] args) throws Exception {
		names = new String[2*MAX];
    	emails = new String[MAX];
		schools = new String[MAX];
    	passwords = new String[MAX];

        //reads in all existing administrator accounts
		try {
			in = new BufferedReader(new FileReader("data\\admins.txt"));
			in2 = new BufferedReader(new FileReader("data\\userSchools.txt"));
			names = in.readLine().split(" ");
			emails = in.readLine().split(" ");
			passwords = in.readLine().split(" ");
			numOfAccounts = emails.length;
			for(int i = 0, n = 0; i < numOfAccounts; i++) {
				if(n < names.length){
					fullName = names[n] + " " + names[n+1];
					n+=2;
					schools[i] = in2.readLine();
					Admin.addAdmin(new Admin(fullName, emails[i], schools[i], passwords[i]));
				}
			}
			in.close();
			in2.close();
			for(Admin s : Admin.getAdmins()){
				System.out.println(Admin.toString(s));
			}
		} catch (NullPointerException e){
			e.printStackTrace();
		}

		//reads in all existing organization data
		try {
			in3 = new BufferedReader(new FileReader("data\\organizations.txt"));
			names = in3.readLine().split("\\|");
			emails = in3.readLine().split("\\|");
			phoneNums = in3.readLine().split("\\|");
			addresses = in3.readLine().split("\\|");
			descs = in3.readLine().split("\\|");

			numOfAccounts = names.length;
			for(int i = 0; i < numOfAccounts; i++) {
				Org.addOrg(new Org(names[i], emails[i], phoneNums[i], descs[i], addresses[i]));
			}
			in3.close();
			for(Org o : Org.getOrgs()){
				System.out.println(Org.toString(o));
			}
		} catch (NullPointerException e){
			e.printStackTrace();
		}


        // Set the look and feel of the GUI.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
			System.err.println("Unsupported operating system.");
		}

        EventQueue.invokeLater(TitleFrame::new);
    }
}
