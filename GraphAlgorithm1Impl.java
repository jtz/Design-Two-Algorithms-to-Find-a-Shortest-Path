import java.util.HashMap;

public class GraphAlgorithm1Impl extends AbstractGraphAlgorithm{

    public GraphAlgorithm1Impl() {
        super("Algorithm 1");
    }

    /**
     * Among all nodes v that are adjacent to the node n, choose the one with the smallest dd(v).
     * @param adjacentMap current node's adjacent nodes
     * @return
     */
    @Override
    public String shortestAdjacentDistance(HashMap<String, Integer> adjacentMap) {
        String minDistanceNode = null;
        Integer minDistance = -1;
        for (String oneNode : adjacentMap.keySet()) {
            Integer oneNodeDistance = directDistanceMap.get(oneNode);
            // if oneNode already in the path, continue check next adjacent node
            if (sequenceNodes.contains(oneNode)) {
                continue;
            }
            // first time, setting the minDistanceNode
            if (minDistanceNode == null) {
                minDistanceNode = oneNode;
                minDistance = oneNodeDistance;
            } else if (minDistance > oneNodeDistance) {
                // if this node dd less than minDistance, setting this as minDistanceNode
                minDistanceNode = oneNode;
                minDistance = oneNodeDistance;
            }
        }
        return minDistanceNode;
    }
}
