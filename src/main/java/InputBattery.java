import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputBattery extends JDialog implements ActionListener {
    private JButton button;
    private JTextField sourceTextfield, destTextfield, IdTextfield, vTextfield;
    private JLabel sourcePromptlabel, destPromptlabel, IdPromptLabel, vPromptLabel;
    private GUI owner;

    //A modal dialog (owned by the GUI) which lets the user input component properties.
    public InputBattery(GUI owner) {
        super(owner, true);
        this.owner = owner;
        setLayout(new FlowLayout());
        IdPromptLabel = new JLabel("Enter the component's ID");
        add(IdPromptLabel);
        IdTextfield = new JTextField(5);
        add(IdTextfield);
        sourcePromptlabel = new JLabel("Enter the source node");
        add(sourcePromptlabel);
        sourceTextfield = new JTextField(5);
        add(sourceTextfield);
        destPromptlabel = new JLabel("Enter the destination node");
        add(destPromptlabel);
        destTextfield = new JTextField(5);
        add(destTextfield);
        vPromptLabel = new JLabel("Enter the voltage");
        add(vPromptLabel);
        vTextfield = new JTextField(5);
        add(vTextfield);
        button = new JButton("Enter");
        add(button);
        button.addActionListener(this);
    }

    //When the button is pressed, a battery object is instantiated and the addComponent method is called.
    public void actionPerformed(ActionEvent e) {
        try {
            int sourceNode = (int) (Double.parseDouble(sourceTextfield.getText()));
            int destNode = (int) (Double.parseDouble(destTextfield.getText()));
            int ID = (int) (Double.parseDouble(IdTextfield.getText()));
            double voltage = (Double.parseDouble(vTextfield.getText()));
            Battery battery = new Battery(sourceNode, destNode, voltage, ID, 0);
            owner.addComponent(battery);
            //Ensures the user enters the correct format
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null,
                    "Error: You must enter an integer for the Nodes and ID and a Double for the voltage");

            dispose();
        }
    }
}
