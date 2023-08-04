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



