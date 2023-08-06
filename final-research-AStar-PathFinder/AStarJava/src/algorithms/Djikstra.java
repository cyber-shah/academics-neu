package algorithms;

import graph.Graph;

import java.util.Arrays;

public class Djikstra {
  static final int INF = Integer.MAX_VALUE;
  private Graph graph;

  public Djikstra(Graph graph) {
    this.graph = graph;
  }

  public int findMinDistanceNode(boolean[] visitedList, int[] distancesList) {
    int minDistance = INF;
    int nodeIndex = -1;

    // Iterate through all nodes to find the unvisited node with minimum distance
    for (int i = 0; i < this.graph.getNumNodes(); i++) {
      if (!visitedList[i] && distancesList[i] <= minDistance) {
        minDistance = distancesList[i];
        nodeIndex = i;
      }
    }
    return nodeIndex;
  }

  public int[] dijkstra(int sourceNode) {
    boolean[] visitedList = new boolean[this.graph.numNodes];
    int[] distancesList = new int[this.graph.numNodes];

    Arrays.fill(visitedList, false);
    Arrays.fill(distancesList, INF);
    distancesList[sourceNode] = 0;

    for (int i = 0; i < this.graph.numNodes - 1; ++i) {
      int minDistanceNode = findMinDistanceNode(visitedList, distancesList);

      visitedList[minDistanceNode] = true;

      for (int j = 0; j < this.graph.numNodes; ++j) {
        if (!visitedList[j] && graph.adjacencyMatrix[minDistanceNode][j] != INF) {
          int newDistance = distancesList[minDistanceNode] + graph.adjacencyMatrix[minDistanceNode][j];
          if (newDistance < distancesList[j]) {
            distancesList[j] = newDistance;
          }
        }
      }
    }
    return distancesList;
  }
}
