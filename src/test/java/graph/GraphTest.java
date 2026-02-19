package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph graph;

    @BeforeEach
    void setup() {
        graph = new Graph();
    }

    @Test
    void createsAndReturnsNodeWithCorrectNodeID() {
        Graph.Node node = graph.createNode();
        assertEquals(1, node.getId());
        assertEquals(1, node.getGraphId());
    }

    @Test
    void correctIdAssigningToOtherNodes() {
        Graph.Node firstNode = graph.createNode();
        Graph.Node secondNode = graph.createNode();
        assertEquals(1, firstNode.getId());
        assertEquals(2, secondNode.getId());
    }

    @Test
    void connectTwoNodesResultsInCorrectMapPutMethod() {
        Graph.Node nodeFrom = graph.createNode();
        Graph.Node nodeTo = graph.createNode();
        graph.connectNodes(nodeFrom, nodeTo, 2);
        Map<Graph.Node, Graph.Edge> map = graph.pointsTo(nodeFrom);
        assertTrue(map.containsKey(nodeTo));
    }
    @Test
    void correctDisconnectMethodCall() {
        Graph.Node nodeFrom = graph.createNode();
        Graph.Node nodeTo = graph.createNode();
        graph.connectNodes(nodeFrom, nodeTo, 2);
        assertTrue(graph.disconnectNodes(nodeFrom, nodeTo));
    }

    @Test
    void throwsExceptionWhenNullPassedToMethod() {
        Graph.Node nodeFrom = graph.createNode();
        Graph.Node nodeTo = graph.createNode();
        assertThrows(IllegalArgumentException.class, () -> graph.disconnectNodes(nodeFrom, null));
    }

    @Test
    void checksIfGivenNodeExists() {
        Graph.Node node1 = graph.createNode();
        Graph.Node node2 = graph.createNode();

        graph.connectNodes(node1, node2, 1);

        assertTrue(graph.containsNode(node1));
    }

    @Test
    void removesNodeAndAllEdges() {
        Graph.Node node1 = graph.createNode();
        Graph.Node node2 = graph.createNode();
        Graph.Node node3 = graph.createNode();

        graph.connectNodes(node1, node2, 1);
        graph.connectNodes(node1, node3, 1);

        assertTrue(graph.containsNode(node1));
        graph.removeNode(node1);
    }

    @Test
    void afterConnectionAllEdgesArePresent() {
        Graph.Node node1 = graph.createNode();
        Graph.Node node2 = graph.createNode();
        Graph.Node node3 = graph.createNode();

        graph.connectNodes(node1, node2, 1);
        graph.connectNodes(node1, node3, 1);
        Map<Graph.Node, Graph.Edge> connections =  graph.pointsTo(node1);
        Graph.Node nodeToChek1 = ((Graph.Edge) connections.values().toArray()[0]).getTarget();
        Graph.Node nodeToChek2 = ((Graph.Edge)connections.values().toArray()[1]).getTarget();

        assertEquals(node2, nodeToChek1);
        assertEquals(node3, nodeToChek2);
    }
}