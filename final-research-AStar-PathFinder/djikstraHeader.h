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
Graph* initializeGraph() {
    Graph *graph = (Graph *) malloc(sizeof(Graph));
    graph->numberOfNodes = 0;
    graph->numberOfEdges = 0;
    graph->adjacencyMatrix[MAX_NODES][MAX_NODES] = INF;

    return graph;
}

/**
 * Finds the unvisited node with the minimum distance.
 * 
 * @param visited the array of visited nodes.
 * @param distArray the array of nodes with their distance from the source node.
*/
int find_min_distance_node(bool visited[], Node distArray[]) {
    int minDistance = INF;

    // for all nodes if unvisited and distance less than minDistance.
    for (int i = 0; i < MAX_NODES; i++) {
        int minDistance = distArray[i].distance;
        if (visited[i] == false && distArray[i].distance <= minDistance) {
            minDistance = distArray[i].distance;
        }
    }
}

/**
 * This function is the implementation of the djikstra algorithm.
 * here distance = sum of weights of edges from source node to the current node.
 *                  or the distance from the source node to the current node.
 * 1. find the node with the minimum distance to visit next.
 * 2. Loop over all of its adjacent nodes.
 *      2.1 Relax all the adjacent nodes unvisited nodes. (why?)
 *              because we visit a node only when we have found the shortest path to it 
 *              and there is no other shorter path.
 *      2.2 If the new distance is less than the current distance, update the distance.
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
       // finds the node with the minimum distance to visit next.
        int minDistanceNode = find_min_distance_node(visited, distArray);

        // mark the node as visited.
        visited[minDistanceNode] = true;

        // loop through all the nodes.
        for (int j = 0; j < graph->numberOfNodes; ++j) {
            // relax all the adjacent unvisited nodes. adjacent means distance != INF
            if (visited[j] == false && graph->adjacencyMatrix[minDistanceNode][j] != INF) {
                int newDistance = distArray[minDistanceNode].distance + graph->adjacencyMatrix[minDistanceNode][j];
                if (newDistance < distArray[j].distance) {
                    // update the distance.
                    distArray[j].distance = newDistance;
                }
            }
        }
    }

}


