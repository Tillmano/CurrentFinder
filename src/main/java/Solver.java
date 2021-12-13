import java.util.*;

import org.jgrapht.*;
import org.jgrapht.alg.interfaces.CycleBasisAlgorithm;
import org.jgrapht.graph.*;
import org.jgrapht.alg.cycle.*;
import org.jgrapht.graph.builder.GraphBuilder;

public class Solver {

    static void solve(ArrayList<Component> components) {

        Graph g = BuildGraph(components);


        QueueBFSFundamentalCycleBasis cycleBase = new QueueBFSFundamentalCycleBasis(g);
        CycleBasisAlgorithm.CycleBasis<Integer, Component> cycleBasis = cycleBase.getCycleBasis();
        Set<GraphPath<Integer, Component>> cycles = cycleBasis.getCyclesAsGraphPaths();
        System.out.println(cycles);

        Set<Integer> vertexSet = g.vertexSet();
        int rows = cycles.size() + vertexSet.size();
        System.out.println(rows);

        double twoD[][] = new double[rows][components.size()];
        double answers[] = new double[rows];
        int row = 0;

        for (
                int i = 0; i < vertexSet.size(); i++) {
            Set<Component> edges = g.edgesOf(i + 1);
            answers[row] = 0;
            row++;
            for (Component component : edges) {
                if (component.GetSourceNode() == i + 1) {
                    twoD[i][component.GetID() - 1] = -1;
                } else {
                    twoD[i][component.GetID() - 1] = 1;
                }
            }

        }

        for (
                GraphPath graphPath : cycles) {
            List edges = graphPath.getEdgeList();
            Resistor lastResistor = null;
            boolean flip;
            int factor = 1;
            boolean first = true;
            for (Object component : edges) {
                Resistor rcomp;
                Battery vcomp;
                if (component instanceof Battery) {
                    vcomp = (Battery) component;
                    answers[row] = vcomp.GetVoltage();
                } else if (component instanceof Resistor) {
                    rcomp = (Resistor) component;
                    if (first) {
                        lastResistor = rcomp;
                        twoD[row][rcomp.GetID() - 1] = rcomp.GetResistance() * factor;
                        first = false;
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

        MatrixSolver matrixSolver = new MatrixSolver();
        double[] solutions = matrixSolver.Solve(twoD, answers);
        for (int i = 0; i < solutions.length; i++) {
            for (Component component : components) {
                int ID = component.GetID();
                if (ID == i + 1) {
                    component.setCurrent(solutions[i]);
                }
            }
        }

    }

    private static Graph BuildGraph(ArrayList<Component> components) {
        Graph<Integer, Component> g = new DefaultDirectedGraph<>(Component.class);
        for (Component component : components) {
            if (!g.containsVertex(component.GetDestNode())) {
                g.addVertex(component.GetDestNode());
            }
            if (!g.containsVertex(component.GetSourceNode())) {
                g.addVertex(component.GetSourceNode());
            }
            g.addEdge(component.GetSourceNode(), component.GetDestNode(), component);
            g.addEdge(component.GetDestNode(), component.GetSourceNode(), component);
        }
        return g;
    }
    private static Double[][] Double[] ()
}




