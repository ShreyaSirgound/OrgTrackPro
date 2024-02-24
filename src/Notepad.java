import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad extends KeyAdapter implements ActionListener, KeyListener {
    //Default Font Size for text
    int fsize = 17;

    private JTextArea area;
    private JScrollPane scpane;
    String text = "";

    //Creating Frame and setting up the title
    JFrame frame = new JFrame("Notepad");

    JTextField title;
    Font newFont;
    JPanel bottomPanel;
    JLabel detailsOfFile;
    JList fontFamilyList, fontStyleList, fontSizeList;
    JScrollPane sb;
    JMenuBar menuBar;
    JMenu file, edit, format;
    JMenuItem newdoc, open, save, print, exit, copy, paste, cut, selectall, fontfamily, fontstyle, fontsize;
    //Defining List of Fonts for Text
    String fontFamilyValues[] = {"Agency FB", "Antiqua", "Architect", "Arial", "Calibri", "Comic Sans", "Courier", "Cursive", "Impact", "Serif"};
    //Defining List of Font Size for Text
    String fontSizeValues[] = {"5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70"};
    int[] stylevalue = {Font.PLAIN, Font.BOLD, Font.ITALIC};
    //Defining List of Font Styles for Text
    String[] fontStyleValues = {"PLAIN", "BOLD", "ITALIC"};
    String fontFamily, fontSize, fontStyle;
    int fstyle;
    int cl;
    int linecount;
    JScrollPane sp;
    //JPanel p = new JPanel(new BorderLayout());

    public Notepad() {
       initUI();
       addActionEvents(); 
    }

    public void actionPerformed(ActionEvent ae) {
        //if new option is choosen
        if (ae.getActionCommand().equals("New")) {
            //Setting Text as empty by default
            area.setText("");
        } //if open option is chosen
        else if (ae.getActionCommand().equals("Open")) {
            //Setting current by default directory "C" folder
            JFileChooser chooser = new JFileChooser("C:/");
            chooser.setAcceptAllFileFilterUsed(false);
            //Allowing only text (.txt) files extension to open
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            chooser.addChoosableFileFilter(restrict);

            int result = chooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    //Reading the file
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    area.read(br, null);
                    //Closing the file after reading
                    //Clearing the memory
                    br.close();
                    area.requestFocus();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        } //if save option is choosen
        else if (ae.getActionCommand().equals("Save")) {
            final JFileChooser SaveAs = new JFileChooser();
            SaveAs.setApproveButtonText("Save");
            //Opening the dialog and asking from user where to save the file.
            int actionDialog = SaveAs.showOpenDialog(frame);
            if (actionDialog != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File fileName = new File(SaveAs.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(fileName));
                area.write(outFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } //if print option is choosen
        else if (ae.getActionCommand().equals("Print")) {
            try {
                //printer dialog will open
                area.print();
            } catch (Exception e) {
            }
        } //if copy option is choosen
        else if (ae.getActionCommand().equals("Copy")) {
            //Getting Selected Selected Text
            text = area.getSelectedText();
        } //if paste option is choosen
        else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        } //if cut option is choosen
        else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } //if select all option is choosen
        else if (ae.getActionCommand().equals("Select All")) {
            //Selecting all text
            area.selectAll();
        } //if font family change option is choosen
        else if (ae.getActionCommand().equals("Font Family")) {
            //Setting up Font Family
            JOptionPane.showConfirmDialog(null, fontFamilyList, "Choose Font Family", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            fontFamily = String.valueOf(fontFamilyList.getSelectedValue());
            newFont = new Font(fontFamily, fstyle, fsize);
            area.setFont(newFont);
        } //if font style change option is choosen
        else if (ae.getActionCommand().equals("Font Style")) {
            //Setting up Font Style
            JOptionPane.showConfirmDialog(null, fontStyleList, "Choose Font Style", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            fstyle = stylevalue[fontStyleList.getSelectedIndex()];
            newFont = new Font(fontFamily, fstyle, fsize);
            area.setFont(newFont);
        } //if font size change option is choosen
        else if (ae.getActionCommand().equals("Font Size")) {
            //Setting up Font Size
            JOptionPane.showConfirmDialog(null, fontSizeList, "Choose Font Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            fontSize = String.valueOf(fontSizeList.getSelectedValue());
            fsize = Integer.parseInt(fontSize);
            newFont = new Font(fontFamily, fstyle, fsize);
            area.setFont(newFont);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //Calculating length and count of words
        cl = area.getText().length();
        linecount = area.getLineCount();
        detailsOfFile.setText("Length: " + cl + " Line: " + linecount);
    }

    public void initUI() {
        detailsOfFile = new JLabel();

        bottomPanel = new JPanel();

        //Creating Menubar
        menuBar = new JMenuBar();

        //Creating Menu "File"
        file = new JMenu("File");

        //Creating MenuItem "New"
        newdoc = new JMenuItem("New");

        //Assigning shortcut "Cntrl + N" for "New" Menu Item
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        //Creating MenuItem "Open"
        open = new JMenuItem("Open");

        //Assigning shortcut "Cntrl + O" for "Open" Menu Item
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        //Creating MenuItem "Save"
        save = new JMenuItem("Save");

        //Assigning shortcut "Cntrl + S" for "Save" Menu Item
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        //Creating MenuItem "Print"
        print = new JMenuItem("Print");

        //Assigning shortcut "Cntl + P" for "Print" Menu Item
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        //Creating MenuItem "Exit"
        exit = new JMenuItem("Exit");

        //Assigning shortcut "ESC" for "Exit" Menu Item
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));

        //Creating Menu "Edit"
        edit = new JMenu("Edit");

        //Creating MenuItem "Copy"
        copy = new JMenuItem("Copy");

        //Assigning shortcut "Cntrl + C" for "Copy" Menu Item
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        //Creating MenuItem "Paste"
        paste = new JMenuItem("Paste");

        //Assigning shortcut "Cntrl + V" for "Paste" Menu Item
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        //Creating MenuItem "Cut"
        cut = new JMenuItem("Cut");

        //Assigning shortcut "Cntrl + X" for "Cut" Menu Item
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        //Creating MenuItem "Select All"
        selectall = new JMenuItem("Select All");

        //Assigning shortcut "Cntrl + A" for "Select All" Menu Item
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        //Creating Menu "Format"
        format = new JMenu("Format");

        //Creating MenuItem "Font Family"
        fontfamily = new JMenuItem("Font Family");

        //Creating MenuItem "Font Style"
        fontstyle = new JMenuItem("Font Style");

        //Creating MenuItem "Font Size"
        fontsize = new JMenuItem("Font Size");

        //Creating List of Font Family and assigning the list values
        fontFamilyList = new JList(fontFamilyValues);

        //Creating List of Font Styles and assigning the list values
        fontStyleList = new JList(fontStyleValues);

        //Creating List of Font Size and assigning the list values
        fontSizeList = new JList(fontSizeValues);

        //Allowing user to select only one option
        fontFamilyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontStyleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontSizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //TextArea / Editor of Notepad
        area = new JTextArea();

        //Default font will be sam_serif and default font style will be plain and default style will be 20. 
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));

        //Sets the line-wrapping policy of the text area
        area.setLineWrap(true);

        //Sets the word-wrapping policy of the text area
        area.setWrapStyleWord(true);

        //Creating Scrollables around textarea
        scpane = new JScrollPane(area);

        //Creating border for scrollpane
        scpane.setBorder(BorderFactory.createEmptyBorder());

        //Adding menubar in frame
        frame.setJMenuBar(menuBar);

        //Adding all menus in menubars
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);

        format.add(fontfamily);
        format.add(fontstyle);
        format.add(fontsize);

        bottomPanel.add(detailsOfFile);

        //Setting up the size of frame
        frame.setSize(980, 480);

        //Setting up the layout of frame
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        //Adding panels in frame
        frame.add(scpane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        //Setting Frame visible to user
        frame.setVisible(true);
    }

    public void addActionEvents() {
        //registering action listener to buttons
        newdoc.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        cut.addActionListener(this);
        selectall.addActionListener(this);
        open.addActionListener(this);
        fontfamily.addActionListener(this);
        fontsize.addActionListener(this);
        fontstyle.addActionListener(this);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
    
}
