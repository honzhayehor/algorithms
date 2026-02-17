package graph;

import org.jspecify.annotations.NonNull;

import java.util.*;
import com.google.common.base.Optional;


public final class Graph {
    private final int graphId;
    private static int lastGraphID = 0;
    private final Map<Node, Map<Node, Edge>> nodes = new HashMap<>();
    private int lastNodeID = 0;
    private int lastEdgeID = 0;

    public Graph() {
        this.graphId = ++lastGraphID;
    }

    //TODO: create methods for creating, removing and connecting nodes

    /**
     * Creates and registers new Node in Graph. Returns reference to newly created node.
     * @return empty Node that has no connection to other Nodes in this Graph
     * @throws IllegalArgumentException if map already has this node's ID
     * */
    public Node createNode(){
        Node newNode = new Node(++lastNodeID, this.graphId);
        if (nodes.putIfAbsent(newNode, new HashMap<>()) != null) {
            throw new IllegalStateException("Duplicate node key");
        }
        return newNode;
    }

    /**
     * Connects two nodes and setting distance between them.
     * @param from Starting Node that needs to be connected to the next one
     * @param to Node to which the 'from' node is pointing (connected)
     * @param distance integer that describes distance between two nodes (weights)
     * @throws IllegalArgumentException in multiple cases: Node 'from' points to itself, Node ('from' or 'to') is null and if Node does not belong to current graph
     * */
    public void connectNodes(Node from, Node to, int distance) {
        validateNodes(from, to);
        Edge edge = new Edge(distance, to, ++lastEdgeID, this.graphId);
        nodes.get(from).put(to, edge);
    }

    /**
     * Removes specified Node from the graph
     * @param node Node that needs to be removed
     * @return Optional that may contain removed Node value
     * @throws IllegalArgumentException if given node is not from this graph
     * */
    public boolean removeNode(Node node) {
        validateNode(node);
        nodes.remove(node);

        for (Map<Node, Edge> edges : nodes.values()) {
            edges.remove(node);
        }
        return true;
    }

    public boolean disconnectNodes(Node from, Node to) {
        validateNode(from);
        validateNode(to);
        return nodes.get(from).remove(to) != null;
    }

    /**
     * Validates node object for null-safety and affiliation to current graph
     * @param node Node that need to be validated
     * @throws IllegalArgumentException if node is null or does not belong to current graph
     * */
    private void validateNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
        if (node.getGraphId() != this.graphId || !nodes.containsKey(node)) {
            throw new IllegalArgumentException("Node does not belong to this graph");
        }
    }

    /**
     * Validates two nodes for null-safety and affiliation to current graph
     * @param from Node that need to be validated
     * @param to Node that need to be validated
     * @throws IllegalArgumentException if node is null, does not belong to current graph or 'from' and 'to' are the same object
     * */
    private void validateNodes(Node from, Node to) {
        validateNode(from);
        validateNode(to);
        if (from.equals(to)) throw new IllegalArgumentException("Cannot connect one node to itself");
    }

    /**
     * Check if two specified nodes are adjacent
     * @param from Node that needs to be checked for adjacency
     * @param to Node that needs to be checked for adjacency
     * @return boolean that indicates whether specified nodes are adjacent
     * */
    public boolean checkAdjacency(Node from, Node to) {
        validateNodes(from, to);
        return nodes.get(from).containsKey(to);
    }

    public static final class Edge {
        private final int graphId;
        private int distance;
        private final int id;
        private final Node target;

        private Edge(int distance, @NonNull Node target, int id, int graphId) {
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

        private Node(int id, int graphId) {
            this.id = id;
            this.graphId = graphId;
        }

        public int getId() {return id;}
        public int getGraphId() {return this.graphId;}

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
