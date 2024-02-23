import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame {
    public MainFrame() {
    JFrame frame = new JFrame("Dashboard");
    Main.setMainFrame(frame);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setSize(1280, 720);
    frame.setLocationRelativeTo(null);

    //The tabbed pane containing available views.
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT);
    tabs.add("Search Records", new SearchRecords());
    mainPanel.add(tabs);
    
    frame.add(mainPanel);
    frame.setVisible(true);
  }
}
