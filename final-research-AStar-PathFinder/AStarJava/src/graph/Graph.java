package graph;

public class Graph {
  public int numNodes;
  public Node[] nodesList;
  public int[][] adjacencyMatrix;

  public Graph(int numNodes) {
    this.numNodes = numNodes;
    nodesList = new Node[numNodes];
    adjacencyMatrix = new int[numNodes][numNodes];
  }

  public int addNewNode(String name) {
    int newNodeIndex = numNodes;
    nodesList[newNodeIndex] = new Node(name, newNodeIndex);
    numNodes++;
    return newNodeIndex;
  }

  public void addEdge(int node1, int node2) {
    if (node1 >= numNodes || node2 >= numNodes || node1 < 0 || node2 < 0) {
      System.out.println("Invalid node index");
      return;
    }
    adjacencyMatrix[node1][node2] = 1;
    adjacencyMatrix[node2][node1] = 1;
  }

  public void removeEdge(int node1, int node2) {
    adjacencyMatrix[node1][node2] = 0;
    adjacencyMatrix[node2][node1] = 0;
  }

  public String toString() {
    String result = "";
    for (int i = 0; i < numNodes; i++) {
      result += nodesList[i].name + ": ";
      for (int j = 0; j < numNodes; j++) {
        if (adjacencyMatrix[i][j] == 1) {
          result += nodesList[j].name + " ";
        }
      }
      result += "\n";
    }
    return result;
  }


  public int getNumNodes() {
    return numNodes;
  }

  public Node[] getNodesList() {
    return nodesList;
  }

  public int[][] getAdjacencyMatrix() {
    return adjacencyMatrix;
  }
}
