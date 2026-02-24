package graph;

import org.junit.jupiter.api.Test;

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

        for (Graph.Node node : list) {
            System.out.println(node);
        }

    }

}