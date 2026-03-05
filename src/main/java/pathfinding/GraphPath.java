package pathfinding;

import graph.Graph;

import java.util.List;

public interface GraphPath {
    List<Graph.Node> findPath(Graph.Node from, Graph.Node to);
}
