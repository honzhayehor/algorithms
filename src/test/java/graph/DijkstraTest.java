package graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void correctPathFound() {
        Graph graph = Graph.getInstance();
        Graph.Node A = graph.createNode();
        Graph.Node F = graph.createNode();
        Graph.Node D = graph.createNode();
        Graph.Node E = graph.createNode();
        Graph.Node B = graph.createNode();
        Graph.Node G = graph.createNode();
        Graph.Node C = graph.createNode();

        graph.connectNodes(A, F, 3);
        graph.connectNodes(A, D, 5);
        graph.connectNodes(A, B, 2);
        graph.connectNodes(F, B, 4);
        graph.connectNodes(B, C, 7);
        graph.connectNodes(B, E, 1);
        graph.connectNodes(D, E, 1);
        graph.connectNodes(D, G, 1);
        graph.connectNodes(E, G, 3);
        graph.connectNodes(E, C, 3);
        graph.connectNodes(G, C, 4);

        List<Graph.Node> list = Dijkstra.findPath(graph, A, C);

        List<Graph.Node> expected = List.of(A, B, E, C);
        assertEquals(expected, list);
    }

    @Test
    void throwsExceptionWhenGraphNull() {
        Graph graph = null;
        assertThrows(IllegalArgumentException.class, () -> Dijkstra.findPath(graph, null, null));
    }

    @Test
    void correctPathFoundWithNotWeightedGraph() {
        Graph graph = Graph.getInstance(true);
        Graph.Node A = graph.createNode("A");
        Graph.Node B = graph.createNode("B");
        Graph.Node C = graph.createNode("C");
        Graph.Node D = graph.createNode("D");
        Graph.Node E = graph.createNode("E");
        graph.connectNodes(A, B, 1);
        graph.connectNodes(A, C, 1);
        graph.connectNodes(B, D, 1);
        graph.connectNodes(C, D, 1);
        graph.connectNodes(D, E, 1);
        List<Graph.Node> result = Dijkstra.findPath(graph, A, E);
        List<Graph.Node> expected = List.of(A, B, D, E);

        assertEquals(expected, result);
    }
}