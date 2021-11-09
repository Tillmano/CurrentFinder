public class Battery extends Component {
    double voltage;
    //Constructs a battery, extending the component class.
    public Battery(int sourceNode, int destNode, double voltage, int ID, double current) {
        super(sourceNode, destNode, ID, current);
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return "Battery{" +
                "sourceNode=" + sourceNode +
                ", destNode=" + destNode +
                ", voltage=" + voltage +
                ", current through=" + current +
                ", ID='" + ID + '\'' +
                '}';
    }
}