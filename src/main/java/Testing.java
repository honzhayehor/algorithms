import graph.Graph;

/**
 * Test class where all testing of performance will be performed.
 * */
public class Testing {
    public static void main(String[] args) {
        testOfInfiniteCreationAndRemovalOfNodes();
    }


    public static void testOfInfiniteCreationAndRemovalOfNodes(){
        Graph graph = new Graph();
        while(true) {
            Graph.Node node = graph.createNode();
            graph.removeNode(node);
        }
    }
}
