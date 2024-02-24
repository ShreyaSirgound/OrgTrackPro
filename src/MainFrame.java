import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

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
        JTabbedPane tabs = new JTabbedPane();//JTabbedPane.LEFT); 
        tabs.setUI(new SpacedTabbedPaneUI());
        tabs.setOpaque(true);
        tabs.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        tabs.add("<html><body><table width='190'><tr><td><center>Organization Records</center></td></tr></table></body></html>", new OrgRecords());
        tabs.add("<html><body><table width='190'><tr><td><center>Organization Data</center></td></tr></table></body></html>", new OrgData());
        mainPanel.add(tabs);
        
        frame.add(mainPanel);
        frame.setVisible(true);
  }
}

class SpacedTabbedPaneUI extends BasicTabbedPaneUI {
    @Override
    protected LayoutManager createLayoutManager() {
      return new BasicTabbedPaneUI.TabbedPaneLayout() {
        @Override
        protected void calculateTabRects(int tabPlacement, int tabCount) {
          final int spacer = 20; // should be non-negative
          final int indent = 4;
  
          super.calculateTabRects(tabPlacement, tabCount);
  
          for (int i = 0; i < rects.length; i++) {
            rects[i].x += i * spacer + indent;
          }
        }
      };
    }
}
