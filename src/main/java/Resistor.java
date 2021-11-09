public class Resistor extends Component {
    double resistance;
    //Constructs a resistor, extending the component class.
    public Resistor(int sourceNode, int destNode, double resistance, int ID, double current) {
        super(sourceNode, destNode, ID, current);
        this.resistance = resistance;
    }

    @Override
    public String toString() {
        return "Resistor{" +
                "sourceNode=" + sourceNode +
                ", destNode=" + destNode +
                ", resistance=" + resistance +
                ", current through=" + current +
                ", ID='" + ID + '\'' +
                '}';
    }
}
