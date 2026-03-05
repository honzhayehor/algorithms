package pathfinding;

import graph.Graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CoordinateMap {

    private final Map<Graph.Node, double[]> map = new HashMap<>();

    public CoordinateMap add(Graph.Node node, double x, double y) {
        map.put(node, new double[]{x, y});
        return this;
    }

    Map<Graph.Node, double[]> getMap() {
        return Collections.unmodifiableMap(map);
    }
}