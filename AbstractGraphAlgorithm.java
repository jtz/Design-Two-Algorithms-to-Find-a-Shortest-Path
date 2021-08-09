import java.io.File;
import java.util.*;

/**
 * Abstract class for project Algorithm
 */
public abstract class AbstractGraphAlgorithm implements GraphAlgorithm {
    // record each node direct distance to node Z
    HashMap<String, Integer> directDistanceMap = new HashMap<>();
    // record each node and weight info
    HashMap<String, HashMap<String, Integer>> graphMap = new HashMap<>();
    // record sequence of nodes initially include in shortest path
    List<String> sequenceNodes = new ArrayList<>();
    // record nodes in shortest path
    List<String> shortestPath = new ArrayList<>();
    // record total length of shortest path
    Integer shortestPathLength = 0;
    // record Algorithm Name
    private final String algorithmName;

    public AbstractGraphAlgorithm(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    /**
     * For a giving node, find this node path to node Z
     * @param node input start node
     * @return whether this node have path to node Z
     */
    @Override
    public boolean findGraphPath(String node) {
        // first call algorithm, initialize first node in path
        if (sequenceNodes.size() == 0) {
            sequenceNodes.add(node);
            shortestPath.add(node);
            // if start node is Z, finished
            if (node.equals("Z")) {
                sequenceNodes.add(node);
                shortestPath.add(node);
                return true;
            }
        }

        HashMap<String, Integer> adjacentMap = graphMap.get(node);
        boolean result = false;
        String nextNode = null;

        // recursively choose the node until find the node Z or find dead road
        while (!result) {
            // if nextNode is not null, means from this node find dead path, do backtracking
            if (nextNode != null) {
                // add current node to sequence path
                sequenceNodes.add(node);
                // remove previous add node, and also subtract length from total length
                shortestPath.remove(nextNode);
                shortestPathLength -= adjacentMap.get(nextNode);
            }
            // find next node which has smallest dd(v)
            nextNode = shortestAdjacentDistance(adjacentMap);

            // if this is a dead road, return false to previous path
            if (nextNode == null) {
                result = false;
                break;
            }

            // success find node Z, return result
            if (nextNode.equals("Z")) {
                sequenceNodes.add(nextNode);
                shortestPath.add(nextNode);
                shortestPathLength += adjacentMap.get(nextNode);
                result = true;
                break;
            }

            // using next node as start node to recursive call, try find next node
            sequenceNodes.add(nextNode);
            shortestPath.add(nextNode);
            shortestPathLength += adjacentMap.get(nextNode);
            result = this.findGraphPath(nextNode);
        }

        return result;
    }

    /**
     * Print the result of the shortest path
     */
    @Override
    public void printPath() {
        System.out.println();
        System.out.println(algorithmName + ":");
        System.out.println();
        System.out.print("Sequence of all nodes: ");
        for (int i = 0;i < sequenceNodes.size();i++) {
            if (i == sequenceNodes.size() - 1) {
                System.out.print(sequenceNodes.get(i));
            } else {
                System.out.print(sequenceNodes.get(i) + " -> ");
            }
        }
        System.out.println();
        System.out.print("Shortest path: ");
        for (int i = 0;i < shortestPath.size();i++) {
            if (i == shortestPath.size() - 1) {
                System.out.print(shortestPath.get(i));
            } else {
                System.out.print(shortestPath.get(i) + " -> ");
            }
        }
        System.out.println();
        System.out.println("Shortest path length: " + shortestPathLength);
    }

    /**
     * Setup the algorithm data from input file
     * @throws Exception
     */
    @Override
    public void initGraph() throws Exception {
        // read graph file
        try (Scanner graphInput = new Scanner(new File(GRAPH_FILE_PATH));
             Scanner distanceInput = new Scanner(new File(DIRECT_DISTANCE_FILE_PATCH))) {

            String[] headRow = graphInput.nextLine().trim().split("\\s+");
            while (graphInput.hasNext()) {
                // read the top row in the input file and create nodeArray
                String[] oneRow = graphInput.nextLine().trim().split("\\s+");
                if (oneRow.length != headRow.length + 1) {
                    throw new Exception("Graph Input row format not match first row.");
                }
                String node = oneRow[0];
                LinkedHashMap<String, Integer> nodeMap = new LinkedHashMap<>();
                for (int j = 1; j < oneRow.length; j++) {
                    Integer distance = Integer.valueOf(oneRow[j]);
                    if (distance > 0) {
                        String toNode = headRow[j - 1];
                        nodeMap.put(toNode, distance);
                    }
                }
                graphMap.put(node, nodeMap);
            }
            if (!graphMap.containsKey("Z")) {
                throw new Exception("Not find node Z from read file.");
            }
            if (graphMap.size() > 26) {
                throw new Exception("Graph input node size more than 26, not support.");
            }

            while (distanceInput.hasNext()) {
                // read the top row in the input file and create nodeArray
                String[] oneRow = distanceInput.nextLine().trim().split(" ");
                if (oneRow.length != 2) {
                    throw new Exception("direct distance file format not correct.");
                }
                String node = oneRow[0];
                Integer distance = Integer.valueOf(oneRow[1]);
                if (!graphMap.containsKey(node)) {
                    throw new Exception("Graph Input not find node " + node);
                }
                directDistanceMap.put(node, distance);
            }
            if (directDistanceMap.size() != graphMap.size()) {
                throw new Exception("Graph Input file not match with direct distance file.");
            }

        } catch (Exception e) {
            System.out.println("Get exception when init read file");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * For a giving node, checking this node in input file or not
     * @param node input node
     * @return whether this node in input file
     */
    @Override
    public boolean containsInGraph(String node) {
        if (node == null) {
            return false;
        }
        return graphMap.containsKey(node);
    }

    /**
     * @return return this AlgorithmName
     */
    @Override
    public String returnAlgorithmName() {
        return algorithmName;
    }
}
