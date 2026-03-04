package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AStar implements GraphPath {

    private final Graph graph;
    private final Map<Graph.Node, double[]> coordinates = new HashMap<>();

    AStar(Graph graph) {
        this.graph = graph;
    }

    public void setCoordinates(Graph.Node node, double x, double y) {
        if (!graph.containsNode(node)) {
            throw new IllegalArgumentException("This node does not belong to current graph");
        }
        coordinates.put(node, new double[] {x, y});
    }

    private double heuristic(Graph.Node current, Graph.Node goal) {
        double[] a = coordinates.get(current);
        double[] b = coordinates.get(goal);
        if (a == null || b == null) {
            return 0;
        }
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public List<Graph.Node> findPath(Graph.Node from, Graph.Node to) {
        return List.of();
    }
}
