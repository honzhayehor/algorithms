package pathfinding;

import graph.Graph;
import org.jspecify.annotations.Nullable;
import java.util.*;

public class AStar{

    private final Graph graph;
    private final Map<Graph.Node, double[]> coordinates;

    /**
     * Constructor that takes graph and coordinate map as parameter
     * @param graph that contains nodes
     * */
    public AStar(Graph graph) {
        this.graph = graph;
        this.coordinates = new HashMap<>();
    }

    /**
     * Constructor that takes graph and coordinate map as parameter
     * @param graph that contains nodes
     * @param coordinateMap map with coordinates of nodes of provided graph. This map can be filled later through public API of this class.
     * @throws IllegalArgumentException if at least one node of provided coordinateMap does not belong to current graph
     * */
    public AStar(Graph graph, CoordinateMap coordinateMap) {
        this.graph = graph;
        this.coordinates = new HashMap<>();
        coordinateMap.getMap().forEach(this::validateAndPut);
    }

    /**
     * Data holder structure, that holds best distance of given node and its previous node.
     * This is used in pair with Graph.Node class inside Map<Graph.Node, DataHolder> for quick lookup while iterating Priority Queue (as specified by Dijkstra algorithm)
     * */
    private static final class DataHolder {
        double bestDistance;
        Graph.Node previous;

        /**
         * Constructor of DataHolder object.
         * @param bestDistance double value that indicates the best yet found distance of given key (node)
         * @param previous previous node, going through which will grant that best distance. This parameter can and should be null when initializing algorithm table.
         * */
        DataHolder(double bestDistance, Graph.@Nullable Node previous) {
            this.bestDistance = bestDistance;
            this.previous = previous;
        }
    }

    /**
     * Data holder for node, fScore and gScore as specified by AStar algorithm.
     * This structure is used in priority queue.
     * */
    private record QNode(Graph.Node node, double fScore, double gScore) {}

    /**
     * Method that enables setting coordinates for given node (since Graph does not provide coordinate system for nodes, this system implemented here)
     * @param node node to which coordinates needed to be assigned
     * @param x X coordinate in 2D space (Cartesian coordinate system)
     * @param y Y coordinate in 2D space (Cartesian coordinate system)
     * @throws IllegalArgumentException if provided node does not belong to current graph
     * @return Astar itself to enable method chaining in object creation.
     * */
    public AStar setCoordinates(Graph.Node node, double x, double y) {
        validateAndPut(node, new double[]{x, y});
        return this;
    }

    /**
     * Validation method that checks if provided node belongs to current graph
     * @param node that is to be validated
     * @param coords of given node. Will be added to object Map if validation passes
     * @throws IllegalArgumentException if node has not passed the validation
     * */
    private void validateAndPut(Graph.Node node, double[] coords) {
        if (!graph.containsNode(node)) {
            throw new IllegalArgumentException("Node " + node + " does not belong to this graph");
        }
        coordinates.put(node, coords);
    }

    /**
     * Method that calculates Euclidean distance heuristic for two given nodes
     * @param current node at which the algorithm currently at
     * @param goal node that is the end goal of the algorithm
     * @return double that represents Euclidean heuristic or 0 if two nodes are null
     * */
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

    /**
     * Method that finds path between two given nodes of a graph
     * @param from starting node
     * @param to end goal node
     * @throws IllegalArgumentException if one of provided nodes does not belong to current graph
     * @return List that contains path (sequence of nodes) from starting node to end node
     * */
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

        for (Graph.Node at = to; at != null; at = table.get(at).previous) { // reversing the list since it starts with end node
            result.add(at);
            if (at.equals(from)) break;
        }

        if (!result.get(result.size() - 1).equals(from)) return List.of();
        Collections.reverse(result);
        return result;
    }

}
