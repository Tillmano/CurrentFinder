import java.util.ArrayList;
import org.jgrapht.*;
import org.jgrapht.graph.*;

public class Solver {

    static void solve(ArrayList<Component> components){
        Graph<Integer, Object> g = new DefaultDirectedGraph<>(DefaultEdge.class);
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (!g.containsVertex(component.GetDestNode(component))) {
                g.addVertex(component.GetDestNode(component));
            }
            if (!g.containsVertex(component.GetSourceNode(component))){
                g.addVertex(component.GetSourceNode(component));
            }
            g.addEdge(component.GetSourceNode(component), component.GetDestNode(component));
            System.out.println(g.toString());
        }

    }
}
