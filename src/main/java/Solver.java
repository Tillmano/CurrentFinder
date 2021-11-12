import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.alg.interfaces.CycleBasisAlgorithm;
import org.jgrapht.graph.*;
import org.jgrapht.alg.cycle.*;

public class Solver {

    static void solve(ArrayList<Component> components) {
        Graph<Integer, Object> g = new DefaultUndirectedGraph<>(DefaultEdge.class);
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (!g.containsVertex(component.GetDestNode(component))) {
                g.addVertex(component.GetDestNode(component));
            }
            if (!g.containsVertex(component.GetSourceNode(component))) {
                g.addVertex(component.GetSourceNode(component));
            }
            g.addEdge(component.GetSourceNode(component), component.GetDestNode(component), component);
            System.out.println(g.toString());
        }
        PatonCycleBase cycleBase = new PatonCycleBase(g);
        CycleBasisAlgorithm.CycleBasis<Integer, Object> cycleBasis = cycleBase.getCycleBasis();
        Set<List<Object>> cycles = cycleBasis.getCycles();
        System.out.println(cycles);
    }

}
