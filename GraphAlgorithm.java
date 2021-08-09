import java.util.HashMap;

/**
 * Interface for project Algorithm
 */
public interface GraphAlgorithm {
    public static final String GRAPH_FILE_PATH = "Zou_Jingtian_project/graph_input.txt";
    public static final String DIRECT_DISTANCE_FILE_PATCH = "Zou_Jingtian_project/direct_distance.txt";

    /**
     * find adjacent node's shortest distance node
     *
     * @param adjacentMap current node's adjacent to all nodes
     * @return adjacent Node which had shortest dd value, if return null means dead road
     */
    String shortestAdjacentDistance(HashMap<String, Integer> adjacentMap);

    /**
     * For a giving node, find this node path to node Z
     * @param node input node
     * @return whether this node have path to node Z
     */
    boolean findGraphPath(String node);

    /**
     * Print the result
     */
    void printPath();

    /**
     * Setup the algorithm data from input file
     * @throws Exception
     */
    void initGraph() throws Exception;

    /**
     * For a giving node, checking this node in input file or not
     * @param node input node
     * @return whether this node in input file
     */
    boolean containsInGraph(String node);

    /**
     * @return return this AlgorithmName
     */
    String returnAlgorithmName();
}
