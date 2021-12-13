public class Battery extends Component {
    double voltage;
    //Constructs a battery, extending the component class.
    public Battery(int sourceNode, int destNode, double voltage, int ID, double current) {
        super(sourceNode, destNode, ID, current);
        this.voltage = voltage;
    }

    public double GetVoltage() {
        return voltage;
    }

    @Override
    //Displays the details od the component in an easily readable form.
    public String toString() {
        double roundCurrent = Math.round(current*100.0)/100.0;
        return "Battery (" +
                "ID: " + ID +
                ", sourceNode: " + sourceNode +
                ", destNode: " + destNode +
                ", voltage: " + voltage +
                ", current through: " + roundCurrent + ")";
    }
}