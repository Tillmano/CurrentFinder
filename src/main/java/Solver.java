import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.CycleBasisAlgorithm;
import org.jgrapht.graph.*;
import org.jgrapht.alg.cycle.*;

public class Solver {

    static void solve(ArrayList<Component> components) {
        Graph<Integer, Object> g = new DefaultDirectedGraph<>(DefaultEdge.class);
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (!g.containsVertex(component.GetDestNode(component))) {
                g.addVertex(component.GetDestNode(component));
            }
            if (!g.containsVertex(component.GetSourceNode(component))) {
                g.addVertex(component.GetSourceNode(component));
            }
            g.addEdge(component.GetSourceNode(component), component.GetDestNode(component), component);
            g.addEdge(component.GetDestNode(component),component.GetSourceNode(component), component);
        }
        QueueBFSFundamentalCycleBasis cycleBase = new QueueBFSFundamentalCycleBasis(g);
        CycleBasisAlgorithm.CycleBasis<Integer, Object> cycleBasis = cycleBase.getCycleBasis();
        Set<GraphPath<Integer, Object>> cycles = cycleBasis.getCyclesAsGraphPaths();
        System.out.println(cycles);


    }

}
