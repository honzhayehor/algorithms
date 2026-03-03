package graph;

public enum AlgorithmType {

    ASTAR {
        @Override
        public GraphPath create() {
            return new AStar();
        }
    },
    DIJKSTRA {
        @Override
        public GraphPath create() {
            return new Dijkstra();
        }
    };

    public abstract GraphPath create();
}