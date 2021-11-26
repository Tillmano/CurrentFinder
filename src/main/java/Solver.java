import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.CycleBasisAlgorithm;
import org.jgrapht.graph.*;
import org.jgrapht.alg.cycle.*;

public class Solver {

    static void solve(ArrayList<Component> components) {
        Graph<Integer, Component> g = new DefaultDirectedGraph<>(Component.class);
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (!g.containsVertex(component.GetDestNode())) {
                g.addVertex(component.GetDestNode());
            }
            if (!g.containsVertex(component.GetSourceNode())) {
                g.addVertex(component.GetSourceNode());
            }
            g.addEdge(component.GetSourceNode(), component.GetDestNode(), component);
            g.addEdge(component.GetDestNode(),component.GetSourceNode(), component);
        }
        QueueBFSFundamentalCycleBasis cycleBase = new QueueBFSFundamentalCycleBasis(g);
        CycleBasisAlgorithm.CycleBasis<Integer, Component> cycleBasis = cycleBase.getCycleBasis();
        Set<GraphPath<Integer, Component>> cycles = cycleBasis.getCyclesAsGraphPaths();
        System.out.println(cycles);

        Set<Integer> vertexSet = g.vertexSet();
        int rows = cycles.size() + vertexSet.size() ;
        int columns = rows;
        System.out.println(columns);

        double twoD[][] = new double[rows][columns];
        double answers[] = new double[rows];
        int row = 0;

        for(int i = 0; i < vertexSet.size(); i++){
            Set<Component> edges = g.edgesOf(i + 1);
            answers[row] = 0;
            row++;
            for(Component component : edges) {
                if (component.GetSourceNode() == i + 1) {
                    twoD[i][component.GetID() - 1] = -1;
                } else {
                    twoD[i][component.GetID() - 1] = 1;
                }
            }

        }

            for (GraphPath graphPath : cycles) {
                List edges = graphPath.getEdgeList();
                for (Object component : edges){
                    Resistor rcomp;
                    try {
                        rcomp = (Resistor) component;
                        twoD[row][rcomp.GetID()] = rcomp.GetResistance();
                    }   catch (ClassCastException classCastException) {
                        Battery vcomp = (Battery) component;
                        answers[row] = vcomp.GetVoltage();
                    }
                }
                row++;
            }
            MatrixSolver matrixSolver = new MatrixSolver();
            double[] solutions = matrixSolver.Solve(twoD, answers);
            System.out.println(solutions);

        System.out.print("\nData you entered : \n");
        for (double[] x : twoD) {
            for (double y : x) {
                System.out.print(y + "        ");
            }
            System.out.println();
        }


    }

}
