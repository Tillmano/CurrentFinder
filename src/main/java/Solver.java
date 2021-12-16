import java.util.*;
import org.jgrapht.*;
import org.jgrapht.alg.interfaces.CycleBasisAlgorithm;
import org.jgrapht.graph.*;
import org.jgrapht.alg.cycle.*;

public class Solver {
    static void solve(ArrayList<Component> components) {
        //The list of components is turned into a graph and cycles are found.
        Graph g = BuildGraph(components);
        Set<GraphPath<Integer, Component>> cycles = FindCycles(g);

        //The matrix is built and returns the two-d input array and the answers to the equations.
        MatrixBuilder matrixBuilder = new MatrixBuilder(g, cycles, components);
        double[][] twoD = matrixBuilder.GetInputs();
        double[] answers = matrixBuilder.GetAnswers();

        //The matrix is solved and each solution (current) is assigned to the correct ID component.
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
        //For each component the nodes it is between are added to the graph if they do not already exist.
        for (Component component : components) {
            if (!g.containsVertex(component.GetDestNode())) {
                g.addVertex(component.GetDestNode());
            }
            if (!g.containsVertex(component.GetSourceNode())) {
                g.addVertex(component.GetSourceNode());
            }
            //The component is added as an edge in both directions of the directional graph.
            //This is necessary for cycle finding.
            g.addEdge(component.GetSourceNode(), component.GetDestNode(), component);
            g.addEdge(component.GetDestNode(), component.GetSourceNode(), component);
        }
        return g;
    }
    private static Set<GraphPath<Integer, Component>> FindCycles(Graph g){
        //The fundamental cycles in the graph are found and returned as graph paths.
        QueueBFSFundamentalCycleBasis cycleBase = new QueueBFSFundamentalCycleBasis(g);
        CycleBasisAlgorithm.CycleBasis<Integer, Component> cycleBasis = cycleBase.getCycleBasis();
        Set<GraphPath<Integer, Component>> cycles = cycleBasis.getCyclesAsGraphPaths();
        return cycles;
    }
}




