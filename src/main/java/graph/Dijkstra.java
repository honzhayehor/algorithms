package graph;

import org.jspecify.annotations.Nullable;

import java.util.*;

public final class Dijkstra {
    private Dijkstra() {}

    private static final class DataHolder {
        int bestDistance;
        Graph.Node previous;

        DataHolder(int bestDistance, Graph.@Nullable Node previous) {
            this.bestDistance = bestDistance;
            this.previous = previous;
        }
    }
    private record QNode(Graph.Node node, int dist) {}
    /**
     * For given graph finds best sequence of nodes that is the shortest distance between two given nodes.
     * @param graph Graph that contains sequence of connected nodes
     * @param from Node from which the path has to be found
     * @param to Node to which path has to be found
     * @return list of Nodes, e.g. path from start node to end node or empty list. If there are multiple path with equal cost, the algorithm will return the one that contains node that were created earlier (e.g. has lower NodeID). That explained by the hashCode() method of Graph.Node that uses Id only.
     * @throws IllegalArgumentException if start or end node do not belong to given graph.
     * */
    public static List<Graph.Node> findPath(Graph graph, Graph.Node from, Graph.Node to) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        if (!graph.containsNode(from) || !graph.containsNode(to)) {
            throw new IllegalArgumentException("Node does not belong to given graph");
        }

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

        if (table.get(to).bestDistance == Integer.MAX_VALUE) return List.of();

        for (Graph.Node at = to; at != null; at = table.get(at).previous) {
            result.add(at);
            if (at.equals(from)) break;
        }

        if (!result.get(result.size() - 1).equals(from)) return List.of();

        Collections.reverse(result);
        return result;
    }
}
