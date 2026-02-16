package graph;

import org.jspecify.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;


public final class Graph {
    private final List<Node> NODES = new ArrayList<>();

    Graph() {}

    public class Node {
        private final int DISTANCE;
        @Nullable
        private Node nextNode;
        @Nullable
        private Node previousNode;

        Node(int distance, @Nullable Node nextNode, @Nullable Node previousNode) {
            DISTANCE = distance;
            this.nextNode = nextNode;
            this.previousNode = previousNode;
        }

        Node(int distance) {
            DISTANCE = distance;
        }

        public void setNextNode(@Nullable Node nextNode) {
            this.nextNode = nextNode;
        }

        public void setPreviousNode(@Nullable Node previousNode) {
            this.previousNode = previousNode;
        }

        public boolean hasNextNode() {
            return nextNode == null;
        }

        public boolean hasPreviousNode() {
            return previousNode == null;
        }


    }
}
