import java.util.*;

import org.jgrapht.*;
import org.jgrapht.alg.interfaces.CycleBasisAlgorithm;
import org.jgrapht.graph.*;
import org.jgrapht.alg.cycle.*;
import org.jgrapht.graph.builder.GraphBuilder;

public class Solver {

    static void solve(ArrayList<Component> components) {

        Graph g = BuildGraph(components);
        Set<GraphPath<Integer, Component>> cycles = FindCycles(g);

        MatrixBuilder matrixBuilder = new MatrixBuilder(g, cycles, components);
        double[][] twoD = matrixBuilder.GetInputs();
        double[] answers = matrixBuilder.GetAnswers();

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
    private static Set<GraphPath<Integer, Component>> FindCycles(Graph g){
        QueueBFSFundamentalCycleBasis cycleBase = new QueueBFSFundamentalCycleBasis(g);
        CycleBasisAlgorithm.CycleBasis<Integer, Component> cycleBasis = cycleBase.getCycleBasis();
        Set<GraphPath<Integer, Component>> cycles = cycleBasis.getCyclesAsGraphPaths();
        return cycles;
    }
}




