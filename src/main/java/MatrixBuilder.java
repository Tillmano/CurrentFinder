import java.util.*;
import org.jgrapht.*;
import javax.swing.*;

public class MatrixBuilder {
    private static double[][] twoD;
    private static double[] answers;
    public MatrixBuilder(Graph g, Set<GraphPath<Integer, Component>> cycles, ArrayList<Component> components) {

        //The amount of rows in the matrix is determined by the number of equations that will be formed.
        //Each cycle and each vertex gives a different equation, therefore their sizes are added.
        Set<Integer> vertexSet = g.vertexSet();
        int rows = cycles.size() + vertexSet.size();

        //The number of columns is determined by how many components are inputted.
        //Each equation has one solution/answer so there is one for each row.
        double twoD[][] = new double[rows][components.size()];
        double answers[] = new double[rows];
        int row = 0;

        //For each vertex the total of the currents of the connected edges is set to 0.
        //This is using Kirchoff's current law.
        for (
                int i = 0; i < vertexSet.size(); i++) {
            Set<Component> edges = null;
            //Checks that the user has inputted a proper circuit.
            try {
                edges = g.edgesOf(i + 1);
            } catch(IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,
                "Error: Please enter a proper circuit with 2 or more components between different nodes." +
                        " See the guide for examples.");
            }
            answers[row] = 0;
            row++;
            for (Component component : edges) {
                //Direction is determined by the source/destination node.
                if (component.GetSourceNode() == i + 1) {
                    twoD[i][component.GetID() - 1] = -1;
                } else {
                    twoD[i][component.GetID() - 1] = 1;
                }
            }

        }

        //For each cycle in the set of cycles the program forms an equation.
        for (GraphPath graphPath : cycles) {
            List edges = graphPath.getEdgeList();
            Resistor lastResistor = null;
            boolean flip;
            int factor = 1;
            boolean first = true;
            //For each component in the cycle the program checks if it is a battery or resistor.
            for (Object component : edges) {
                Resistor rcomp;
                Battery vcomp;
                //If it is a battery the voltage is set as the answer to that cycle's matrix row.
                if (component instanceof Battery) {
                    vcomp = (Battery) component;
                    answers[row] = vcomp.GetVoltage();
                } else if (component instanceof Resistor) {
                    rcomp = (Resistor) component;
                    //If it is a resistor the current through it is set as positive the first time.
                    if (first) {
                        lastResistor = rcomp;
                        twoD[row][rcomp.GetID() - 1] = rcomp.GetResistance() * factor;
                        first = false;
                    //Otherwise, the current is set dependent on the sign of the previous current.
                    //If the destination node is the source node of the next, the sign is the same.
                    } else {
                        flip = lastResistor.GetDestNode() != rcomp.GetSourceNode();
                        if (flip) {
                            factor = -factor;
                        }
                        lastResistor = rcomp;
                        twoD[row][rcomp.GetID() - 1] = rcomp.GetResistance() * factor;
                    }
                }
            }
            row++;
        }
        this.twoD = twoD;
        this.answers = answers;
    }
    public double[][] GetInputs(){
        return twoD;
    }
    public double[] GetAnswers(){
        return answers;
    }
}