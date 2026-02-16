package graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph {
    private final PriorityQueue<Node> queue;

    Graph(Comparator<Node> comparator) {
        queue = new PriorityQueue<>(comparator);
    }

    public class Node {

    }
}
