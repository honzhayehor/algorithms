package pathfinding;

import graph.Graph;

public class PathFinderFactory {
    public static Dijkstra dijkstra(Graph graph) {
        return new Dijkstra(graph);
    }

    public static AStar aStar(Graph graph) {
        return new AStar(graph);
    }

}
