package graph;

import org.jspecify.annotations.NonNull;

import java.util.*;

/**
 * Implementation of weighted and directional Graph that exposes public methods for Nodes and Edges manipulation
 * */
public final class Graph {
    private final int graphId;
    private static int lastGraphID = 0;
    private final Map<Node, Map<Node, Edge>> nodes = new HashMap<>();
    private int lastNodeID = 0;
    private int lastEdgeID = 0;

    public Graph() {
        this.graphId = ++lastGraphID;
    }

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
     *
     * @param node Node that needs to be removed
     * @throws IllegalArgumentException if given node is not from this graph
     *
     */
    public void removeNode(Node node) {
        validateNode(node);
        nodes.remove(node); // remove node

        for (Map<Node, Edge> edges : nodes.values()) { // remove edges that removed node had
            edges.remove(node);
        }
    }

    /**
     * Removes connection between two nodes
     * @param from Node that has initial connection
     * @param to Node that Node 'from' points to
     * @return boolean depends on the outcome of remove() operation.
     * @throws IllegalArgumentException if nodes are null or they are the same node
     * */
    public boolean disconnectNodes(Node from, Node to) {
        validateNodes(from, to);
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
     * @throws IllegalArgumentException if given node is null or does not belong to current Graph
     * */
    public boolean checkAdjacency(Node from, Node to) {
        validateNode(from);
        validateNode(to);
        return nodes.get(from).containsKey(to);
    }

    /**
     * Checks the specified node if it has any connection to other nodes
     * @param node Node that needs to be checked
     * @return true if specified node has any connection and false if it doesn't
     * @throws IllegalArgumentException if given node is null or does not belong to current Graph
     * */
    public boolean hasConnections(Node node) {
        validateNode(node);
        return !nodes.get(node).isEmpty();
    }

    /**
     * Returns distance between two given nodes
     * @param from Node from which the edge goes
     * @param to Node to which edge from Node 'from' goes
     * @return int that represents distance between given nodes
     * @throws IllegalArgumentException if one of two nodes is null, doesn't belong to current Graph or if two nodes are the same node
     * */
    public int distanceBetween(Node from, Node to) {
        validateNode(from);
        validateNode(to);
        Edge e = nodes.get(from).get(to);
        if (e == null) throw new IllegalArgumentException("No edge from 'from' to 'to'");
        return e.getDistance();
    }

    /**
     * Returns map of all nodes that provided node points to
     * @param nodeFrom Node for which needs to be return Map of other nodes
     * @return Map of Nodes and Edges
     * @throws IllegalArgumentException if given Node is null or does not belong to current Graph
     * */
    public Map<Node, Edge> pointsTo(Node nodeFrom) {
        validateNode(nodeFrom);
        return Collections.unmodifiableMap(nodes.get(nodeFrom));
    }

    /**
     * Returns list that consists of all nodes that are in current Graph
     * @return List of Nodes
     * */
    public List<Node> asList() {
        return List.copyOf(nodes.keySet());
    }

    /**
     * Check if given Node exists in a Graph
     * @param node Node that needs to be checked
     * @return boolean. True if Node exists in current Graph or False if it is not
     * */
    public boolean containsNode(Node node) {
        if (node == null) return false;
        if (node.getGraphId() != this.graphId) return false;
        return nodes.containsKey(node);
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

        private void changeDistance(int distance) {
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
