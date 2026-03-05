package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AStarTest {

    private Graph graph;
    private AStar aStar;

    @BeforeEach
    void setUp() {
        graph = Graph.create();
        aStar = new AStar(graph);
    }

    @Test
    void shouldFindSimplePath() {
        Graph.Node s = graph.createNode("S");
        Graph.Node a = graph.createNode("A");
        Graph.Node g = graph.createNode("G");

        graph.connectNodes(s, a, 2);
        graph.connectNodes(a, g, 2);

        aStar.setCoordinates(s, 0, 0);
        aStar.setCoordinates(a, 2, 0);
        aStar.setCoordinates(g, 4, 0);

        List<Graph.Node> path = aStar.findPath(s, g);

        assertEquals(List.of(s, a, g), path);
    }

    @Test
    void shouldFindShortestAmongTwoPaths() {
        Graph.Node s = graph.createNode("S");
        Graph.Node a = graph.createNode("A");
        Graph.Node b = graph.createNode("B");
        Graph.Node g = graph.createNode("G");

        graph.connectNodes(s, a, 2);
        graph.connectNodes(a, g, 2);
        graph.connectNodes(s, b, 5);
        graph.connectNodes(b, g, 5);
        aStar.setCoordinates(s, 0, 0);
        aStar.setCoordinates(a, 2, 0);
        aStar.setCoordinates(b, 0, -3);
        aStar.setCoordinates(g, 4, 0);

        List<Graph.Node> path = aStar.findPath(s, g);

        assertEquals(List.of(s, a, g), path);
    }

    @Test
    void shouldReturnEmptyListWhenNoPathExists() {
        Graph.Node s = graph.createNode("S");
        Graph.Node g = graph.createNode("G");

        aStar.setCoordinates(s, 0, 0);
        aStar.setCoordinates(g, 4, 0);

        List<Graph.Node> path = aStar.findPath(s, g);

        assertTrue(path.isEmpty());
    }

    @Test
    void shouldReturnSingleNodeWhenStartEqualsGoal() {
        Graph.Node s = graph.createNode("S");
        aStar.setCoordinates(s, 0, 0);
        List<Graph.Node> path = aStar.findPath(s, s);

        assertEquals(List.of(s), path);
    }

    @Test
    void shouldWorkWithoutCoordinates() {
        Graph.Node s = graph.createNode("S");
        Graph.Node a = graph.createNode("A");
        Graph.Node g = graph.createNode("G");

        graph.connectNodes(s, a, 1);
        graph.connectNodes(a, g, 1);

        List<Graph.Node> path = aStar.findPath(s, g);

        assertEquals(List.of(s, a, g), path);
    }

    @Test
    void shouldThrowWhenNodeDoesNotBelongToGraph() {
        Graph otherGraph = Graph.create();
        Graph.Node foreign = otherGraph.createNode("foreign");
        Graph.Node local = graph.createNode("local");

        assertThrows(IllegalArgumentException.class, () -> aStar.findPath(foreign, local));
        assertThrows(IllegalArgumentException.class, () -> aStar.findPath(local, foreign));
    }

    @Test
    void shouldThrowWhenSettingCoordinatesForForeignNode() {
        Graph otherGraph = Graph.create();
        Graph.Node foreign = otherGraph.createNode("foreign");

        assertThrows(IllegalArgumentException.class, () -> aStar.setCoordinates(foreign, 1, 1));
    }

    @Test
    void shouldFindPathThroughMultipleNodes() {
        Graph.Node s = graph.createNode("S");
        Graph.Node a = graph.createNode("A");
        Graph.Node b = graph.createNode("B");
        Graph.Node c = graph.createNode("C");
        Graph.Node g = graph.createNode("G");

        graph.connectNodes(s, a, 1);
        graph.connectNodes(a, b, 1);
        graph.connectNodes(b, c, 1);
        graph.connectNodes(c, g, 1);

        aStar.setCoordinates(s, 0, 0);
        aStar.setCoordinates(a, 1, 0);
        aStar.setCoordinates(b, 2, 0);
        aStar.setCoordinates(c, 3, 0);
        aStar.setCoordinates(g, 4, 0);

        List<Graph.Node> path = aStar.findPath(s, g);

        assertEquals(List.of(s, a, b, c, g), path);
    }
}