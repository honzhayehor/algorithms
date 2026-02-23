import graph.Dijkstra;
import graph.Graph;

import java.util.List;

/**
 * Test class where all testing of performance will be performed.
 * */
public class Testing {
    public static void main(String[] args) {
        //testOfInfiniteCreationAndRemovalOfNodes();
        Graph graph = new Graph();
        Graph.Node A = graph.createNode("A");
        Graph.Node F = graph.createNode("F");
        Graph.Node D = graph.createNode("D");
        Graph.Node E = graph.createNode("E");
        Graph.Node B = graph.createNode("B");
        Graph.Node G = graph.createNode("G");
        Graph.Node C = graph.createNode("C");

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
            System.out.println("Node " + node.getName() + " ->");
        }
    }


    public static void testOfInfiniteCreationAndRemovalOfNodes(){
        Graph graph = new Graph();
        while(true) {
            Graph.Node node = graph.createNode();
            graph.removeNode(node);
        }
    }
}
