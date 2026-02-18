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
}