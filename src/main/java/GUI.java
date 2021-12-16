import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.*;

public class GUI extends JDialog implements ActionListener {
    private JList list;
    private JLabel label;
    private JButton rButton, vButton, sButton, gButton;
    private DefaultListModel components;


    public GUI() {
        Solver solver = new Solver();

        //Creates GUI elements
        label = new JLabel("List of components:");
        label.setFont(label.getFont().deriveFont(30.0f));
        add(label, (BorderLayout.PAGE_START));
        components = new DefaultListModel<Component>();
        list = new JList(components);
        list.setVisibleRowCount(3);
        add(new JScrollPane(list));
        list.setFont(list.getFont().deriveFont(15.0f));
        add(list);
        rButton = new JButton("Add resistor");
        add(rButton);
        rButton.addActionListener(this);
        vButton = new JButton("Add battery");
        add(vButton);
        vButton.addActionListener(this);
        sButton = new JButton("Solve");
        add(sButton);
        sButton.addActionListener(this);
        gButton = new JButton("Show Guide");
        add(gButton);
        gButton.addActionListener(this);

        //lays out GUI
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(350, 80));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        listPane.add(label);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        listPane.add(listScroller);
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(rButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(vButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(sButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(gButton);
        Container contentPane = getContentPane();
        contentPane.add(listPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);
    }
    //Instantiates dialogs to input the component that the user wants, or calls the solve method.
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == rButton) {
            InputResistor input = new InputResistor(this);
            input.pack();
            input.setVisible(true);
            input.setSize(200, 400);
            input.setTitle("Input");
        }else if(event.getSource() == vButton) {
            InputBattery input = new InputBattery(this);
            input.pack();
            input.setVisible(true);
            input.setSize(200, 400);
            input.setTitle("Input");
        }else if(event.getSource() == sButton){
            //A list of the inputted components is passed to the solver.
            ArrayList<Component> c = new ArrayList<>();
            for (Enumeration<Component> e = components.elements(); e.hasMoreElements();) {
                c.add(e.nextElement());
            }
            Solver.solve(c);
            list.updateUI();
        }else if(event.getSource() == gButton) {
            JFrame frame = new JFrame("Guide");
            new DisplayGuide(frame);
            frame.setSize(560, 450);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

    }
    //adds the new component to the list of components
    public void addComponent(Component component){
        components.addElement(component);
    }


    public static void main(String args[]) {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        gui.setVisible(true);
        gui.setSize(800, 500);
        gui.setTitle("GUI");
    }
}
