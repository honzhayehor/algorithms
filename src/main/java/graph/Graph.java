package graph;

import org.jspecify.annotations.NonNull;

import java.util.*;


public final class Graph {
    private final int graphId;
    private static int lastGraphID = 0;
    private final Map<Node, Map<Node, Edge>> nodes = new HashMap<>(); // TODO: Change to Map<Node, List<Edge>>, since all nodes are effectively final with ID
    private int lastNodeID = 0;
    private int lastEdgeID = 0;

    public Graph() {
        this.graphId = ++lastGraphID;
    }

    //TODO: create methods for creating, removing and connecting nodes

    public Node createNode(){
        Node newNode = new Node(++lastNodeID, this.graphId);
        if (nodes.putIfAbsent(newNode, new HashMap<>()) != null) {
            throw new IllegalStateException("Duplicate node id");
        }
        return newNode;
    }

    public static final class Edge {
        private final int graphId;
        private int distance;
        private final int id;
        private final Node target;

        Edge(int distance, @NonNull Node target, int id, int graphId) {
            changeDistance(distance);
            this.target = target;
            this.id = id;
            this.graphId = graphId;
        }

        public void changeDistance(int distance) {
            if (distance <= 0) {
                throw new IllegalArgumentException("Distance cannot be negative or zero");
            }
            this.distance = distance;
        }
        public int getDistance() {return distance;}

        public Node getTarget() {
            return target;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge edge)) return false;
            return id == edge.id && graphId == edge.graphId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(graphId, id);
        }
    }

    public static final class Node {
        private final int id;
        private final int graphId;

        Node(int id, int graphId) {
            this.id = id;
            this.graphId = graphId;
        }

        public int getId() {return id;}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node node)) return false;
            return id == node.id && graphId == node.graphId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(graphId, id);
        }
    }
}
