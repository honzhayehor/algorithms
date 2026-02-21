package graph;

import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Dijkstra {
    private Dijkstra() {}

    /**
     * For given graph finds best sequence of nodes that is the shortest distance between two given nodes.
     * @param graph Graph that contains sequence of connected nodes
     * @param from Node from which the path has to be found
     * @param to Node to which path has to be found
     * @return list of Nodes, e.g. path from start node to end node
     * @throws IllegalArgumentException if start or end node do not belong to given graph.
     * */
    public static List<Graph.Node> findPath(Graph graph, Graph.Node from, Graph.Node to) {
        // TODO: Implement main method here
        if (!graph.containsNode(from) || !graph.containsNode(to)) throw new IllegalArgumentException("Node does not belong to given graph");
        Map<Graph.Node, Integer> dist = new HashMap<>();
        Map<Graph.Node, Graph.@Nullable Node> prev = new HashMap<>();

        for (Graph.Node node : graph.asList()) {
            dist.put(node, Integer.MAX_VALUE);
            prev.put(node, null);
        }
        dist.put(from, 0);




        return List.of();
    }
}
