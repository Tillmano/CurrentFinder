public class Resistor extends Component {
    double resistance;
    //Constructs a resistor, extending the component class.
    public Resistor(int sourceNode, int destNode, double resistance, int ID, double current) {
        super(sourceNode, destNode, ID, current);
        this.resistance = resistance;
    }
    public double GetResistance() {
        return resistance;
    }

    @Override
    //Displays the details od the component in an easily readable form.
    public String toString() {
        double roundCurrent = Math.round(current*100.0)/100.0;
        return "Resistor (" +
                "ID: " + ID +
                ", sourceNode: " + sourceNode +
                ", destNode: " + destNode +
                ", resistance: " + resistance +
                ", current through: " + roundCurrent + ")";
    }
}
