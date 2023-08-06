#include <stdio.h>
#include <stdbool.h>
#define INF 9999
#define MAX_NODES 100

/**
 * A node is represented by the following :
 * 1. The node number.
 * 2. The distance from the source node.
*/
struct Node {
    int nodeNumber;
    int distance;
} typedef Node;

/**
 * A graph is represented by the following :
 * 1. The adjacency matrix is a 2D array of integers.
 * 2. The number of nodes in the graph.
 * 3. The number of edges in the graph.
*/
struct Graph {
    int numberOfNodes;
    int numberOfEdges;
    int adjacencyMatrix[MAX_NODES][MAX_NODES];
} typedef Graph;

/**
 * initializes a graph to have no nodes or edges.
 * and all edges to have a weight of infinity.
 * 
 * Example : adjacencyMatrix[i][j] = distance from node i to node j.
 *  
 * @param graph the graph to initialize.
*/
void initializeGraph(Graph *graph) {
    graph->numberOfNodes = 0;
    graph->numberOfEdges = 0;
    graph->adjacencyMatrix[MAX_NODES][MAX_NODES] = INF;
}

int find_min_distance_node(bool visited[], Node distArray[]) {
    int minDistance = INF;

    // for all nodes if unvisited and distance less than minDistance.
    for (int i = 0; i < MAX_NODES; i++) {
        if (visited[i] == false && distArray[i].distance <= minDistance) {
            minDistance = distArray[i].distance;
        }
    }
}

/**
 * This function is the implementation of the djikstra algorithm.
 * 1. Starts with the source node.
 * 2. Examine the nodes adjacent to the source node.
 *      2.1 If the distance from the source node to the adjacent node is less than the current distance, update the distance.
 *      2.2 Mark the current node as visited.
 * 3. Pick the smallest distance node from unvisited nodes.
 * 4. Repeat steps 2 and 3 until all the nodes are visited.
 * 5. Keep updating the distance array.
 * 
 * @param sourceNode the source node.
 * @param graph the graph to find the shortest path.
*/
void djikstra(int sourceNode, Graph *graph) {

    // initialize all the visited nodes to false.
    bool visited [MAX_NODES] = {false};
    // array of nodes - to store the distance from the source node.
    Node distArray[MAX_NODES];

    // initialize the distance array.
    for (int i = 0; i < MAX_NODES; i++) {
        distArray[i].nodeNumber = i;
        distArray[i].distance = INF;
    }
    distArray[sourceNode].distance = 0;

    // loop through all the nodes.
    for (int i = 0; i < (graph->numberOfNodes - 1); ++i) {
        /**
         * 2. Examine the nodes adjacent to the source node.
         *      2.1 If the distance from the source node to the adjacent node is less than the current distance, update the distance.
         *      2.2 Mark the current node as visited.
         * 3. Pick the smallest distance node from unvisited nodes.
         * 4. Repeat steps 2 and 3 until all the nodes are visited.
         * 5. Keep updating the distance array.
        */
       // finds the node with the minimum distance to visit next.
        int minDistanceNode = find_min_distance_node(visited, distArray);

    }

}


