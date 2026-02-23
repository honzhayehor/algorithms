package graph;

import org.jspecify.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

public final class Dijkstra {
    private Dijkstra() {}

    private static final class DataHolder {
        int bestDistance;
        Graph.Node previous;

        DataHolder(int bestDistance, Graph.Node previous) {
            this.bestDistance = bestDistance;
            this.previous = previous;
        }
    }
    /**
     * For given graph finds best sequence of nodes that is the shortest distance between two given nodes.
     * @param graph Graph that contains sequence of connected nodes
     * @param from Node from which the path has to be found
     * @param to Node to which path has to be found
     * @return list of Nodes, e.g. path from start node to end node or empty list
     * @throws IllegalArgumentException if start or end node do not belong to given graph.
     * */
    public static List<Graph.Node> findPath(Graph graph, Graph.Node from, Graph.Node to) {
        // TODO: Implement main method here
        if (!graph.containsNode(from) || !graph.containsNode(to)) {
            throw new IllegalArgumentException("Node does not belong to given graph");
        }
        record QNode(Graph.Node node, int dist) {}

        Map<Graph.Node, DataHolder> table = new HashMap<>();
        for (Graph.Node node : graph.asList()) {
            table.put(node, new DataHolder(Integer.MAX_VALUE, null));
        }
        table.get(from).bestDistance = 0;

        PriorityQueue<QNode> pq =
                new PriorityQueue<>(Comparator.comparingInt(QNode::dist));

        pq.add(new QNode(from, 0));

        while (!pq.isEmpty()) {
            QNode q = pq.poll();
            Graph.Node u = q.node();

            if (q.dist() != table.get(u).bestDistance) continue;

            for (var entry : graph.pointsTo(u).entrySet()) {
                Graph.Node v = entry.getKey();
                int w = entry.getValue().getDistance();

                int alt = table.get(u).bestDistance + w;
                if (alt < table.get(v).bestDistance) {
                    table.get(v).bestDistance = alt;
                    table.get(v).previous = u;
                    pq.add(new QNode(v, alt));
                }
            }
        }

        List<Graph.Node> result = new ArrayList<>();

        if (table.get(to).bestDistance == Integer.MAX_VALUE) return List.of(); // шляху нема

        for (Graph.Node at = to; at != null; at = table.get(at).previous) {
            result.add(at);
            if (at.equals(from)) break;
        }

        if (!result.get(result.size() - 1).equals(from)) return List.of(); // на всяк випадок

        Collections.reverse(result);
        return result;

    }
}
