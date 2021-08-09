import java.util.HashMap;

public class GraphAlgorithm2Impl extends AbstractGraphAlgorithm{

    public GraphAlgorithm2Impl() {
        super("Algorithm 2");
    }

    /**
     * Among all nodes v that are adjacent to the node n, choose the one for which w(n, v) + dd(v) is the smallest.
     * @param adjacentMap current node's adjacent nodes
     * @return
     */
    @Override
    public String shortestAdjacentDistance(HashMap<String, Integer> adjacentMap) {
        String minDistanceNode = null;
        Integer minDistance = -1;
        for (String oneNode : adjacentMap.keySet()) {
            Integer oneNodeDistance = directDistanceMap.get(oneNode);
            // if nodeNode already in the path, continue check next adjacent node
            if (sequenceNodes.contains(oneNode)) {
                continue;
            }
            // compare distance with edge weight + dd(v)
            Integer thisNodeDistance = oneNodeDistance + adjacentMap.get(oneNode);
            // first time, setting the minDistanceNode
            if (minDistanceNode == null) {
                minDistanceNode = oneNode;
                minDistance = thisNodeDistance;
            } else if (minDistance > thisNodeDistance) {
                // if this node dd less than minDistance, setting this as minDistanceNode
                minDistanceNode = oneNode;
                minDistance = thisNodeDistance;
            }
        }
        return minDistanceNode;
    }
}
