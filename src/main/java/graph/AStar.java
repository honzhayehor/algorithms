package graph;

import org.jspecify.annotations.Nullable;

import java.util.*;

public class AStar implements GraphPath {

    private final Graph graph;
    private final Map<Graph.Node, double[]> coordinates = new HashMap<>();

    AStar(Graph graph) {
        this.graph = graph;
    }

    private static final class DataHolder {
        double bestDistance;
        Graph.Node previous;

        DataHolder(double bestDistance, Graph.@Nullable Node previous) {
            this.bestDistance = bestDistance;
            this.previous = previous;
        }
    }

    private record QNode(Graph.Node node, double fScore, double gScore) {}

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
        if (!graph.containsNode(from) || !graph.containsNode(to)) {
            throw new IllegalArgumentException("Node does not belong to current graph");
        }

        Map<Graph.Node, DataHolder> table = new HashMap<>();
        for (Graph.Node node : graph.asList()) {
            table.put(node, new DataHolder(Double.MAX_VALUE, null));
        }
        table.get(from).bestDistance = 0;

        PriorityQueue<QNode> pq = new PriorityQueue<>(Comparator.comparingDouble(QNode::fScore));
        pq.add(new QNode(from, heuristic(from, to), 0));

        while (!pq.isEmpty()) {
            QNode q = pq.poll();
            Graph.Node u = q.node();

            if (u.equals(to)) break;

            if (q.gScore() != table.get(u).bestDistance) continue;

            for (var entry : graph.pointsTo(u).entrySet()) {
                Graph.Node v = entry.getKey();
                int w = entry.getValue().getDistance();

                double gScore = table.get(u).bestDistance + w;
                if (gScore < table.get(v).bestDistance) {
                    table.get(v).bestDistance = gScore;
                    table.get(v).previous = u;
                    double fScore = gScore + heuristic(v, to);
                    pq.add(new QNode(v, fScore, gScore));
                }
            }
        }

        List<Graph.Node> result = new ArrayList<>();
        if (table.get(to).bestDistance == Double.MAX_VALUE) return List.of();
        for (Graph.Node at = to; at != null; at = table.get(at).previous) {
            result.add(at);
            if (at.equals(from)) break;
        }

        if (!result.get(result.size() - 1).equals(from)) return List.of();
        Collections.reverse(result);
        return result;
    }

}
