import java.util.*;

public class project {

    public static void main(String[] args) throws Exception {
        List<GraphAlgorithm> graphAlgorithmList = new ArrayList<>();
        graphAlgorithmList.add(new GraphAlgorithm1Impl());
        graphAlgorithmList.add(new GraphAlgorithm2Impl());

        // step1: read and store info from two input files
        for (GraphAlgorithm graphAlgorithm : graphAlgorithmList) {
            graphAlgorithm.initGraph();
        }

        // step2: prompt and validate input of start node from user
        String startNode;
        while(true) {
            System.out.print("Please enter a node to start: ");
            // use scanner to get input node
            Scanner in = new Scanner(System.in);
            startNode = in.nextLine().trim().toUpperCase();
            // validate input node
            if(startNode.matches("[A-Z]")) {
                System.out.println("User enters node " + startNode + " as the start node");
                in.close();
                break;
            }
            else {
                System.out.println("User enters node " + startNode + " is out of A - Z. Please enter again.");
            }
        }

        // Step 3: using two algorithms to find shortest graph path
        for (GraphAlgorithm graphAlgorithm : graphAlgorithmList) {
            // check if start node is in input graph or not
            if (graphAlgorithm.containsInGraph(startNode)) {
                // Start to find path from input node to node Z
                boolean result = graphAlgorithm.findGraphPath(startNode);
                if (result) {
                    // Step 4: print the result if find path
                    graphAlgorithm.printPath();
                } else {
                    // print message if not find path
                    System.out.println(graphAlgorithm.returnAlgorithmName() + " can't find path to node Z.");
                }

            } else {
                // exit if start node is not in input graph
                System.out.println("Enter node " + startNode + "is not in the input graph, exit.");
                return;
            }
        }
    }
}
