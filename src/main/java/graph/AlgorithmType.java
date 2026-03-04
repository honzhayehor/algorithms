package graph;

public enum AlgorithmType {

    ASTAR {
        @Override
        public GraphPath create(Graph graph) {
            return new AStar(graph);
        }
    },
    DIJKSTRA {
        @Override
        public GraphPath create(Graph graph) {
            return new Dijkstra(graph);
        }
    };

    public abstract GraphPath create(Graph graph);
}