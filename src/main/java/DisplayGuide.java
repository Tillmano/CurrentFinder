import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;

public class DisplayGuide extends JDialog {
    public DisplayGuide(final JFrame frame){
        //Panel and frame to display the guide are initialised.
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);
        JEditorPane jEditorPane = new JEditorPane();
        jEditorPane.setEditable(false);
        URL url= DisplayGuide.class.getResource("guide.html");

        //The program tries to set the url of the file as that of the page.
        try {
            jEditorPane.setPage(url);
        } catch (IOException e) {
            jEditorPane.setContentType("text/html");
            jEditorPane.setText("Page not found.");
        }

        //A scroll pane with the html is made and displayed in the panel.
        JScrollPane jScrollPane = new JScrollPane(jEditorPane);
        jScrollPane.setPreferredSize(new Dimension(540,400));

        panel.add(jScrollPane);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
}