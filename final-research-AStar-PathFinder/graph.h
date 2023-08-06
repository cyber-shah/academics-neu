#define INF 9999
#define MAX_NODES 100

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

/**
 * A node is represented by the following :
 * 1. The node number.
 * 2. The distance from the source node.
*/
struct Node {
    char *name;
    int index;
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
    Node *nodes[MAX_NODES];
} typedef Graph;

/**
 * This struct is used to store the distance of each node from 
 * the source node.
*/
struct distArray {
    int nodeIndex;
    int distance;
} typedef distArray;


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
 * adds a node to the graph with the given name.
 * creates a node, and adds it to graph.
 * 
 * @param 
*/
int add_to_graph(Graph* graph, char *nodeName) {
    // 1. get the index
    int index = graph->numberOfNodes;

    // 2. create the node
    Node* node = (Node*) malloc(sizeof(Node));
    node->name = strdup(nodeName);
    node->index = index;
    node->distance = INF;

    // 3. add node to the graph
    graph->nodes[index] = node;
    graph->numberOfNodes++;

    return index;
}


/**
 * Sets the edge index based on the source and destination.
 * For example : set_edge_distance(&graph, "paris", "lille", 50)
 * 
 * 
*/
int set_edge_distance(Graph *graph, 
                      char *src, char *dest, int weight) {
    
    int src_index = -1; int dest_index = -1;
    
    // find the index of the source node.
    for (int i = 0; i < graph->numberOfNodes; i++) {
        if (strcmp(graph->nodes[i]->name, src) ) {
            src_index = i;
        }
        else if (strcmp(graph->nodes[i]->name, dest) ) {
            dest_index = i;
        }
    }

    // if node not found return
    if (src_index == -1 || dest_index == -1) {
        return -1;
    }

    // if node found edit the distance
    graph->adjacencyMatrix [src_index] [dest_index] = weight;
    return 1;
}


/**
 * Frees the graph.
 * 
 * @param graph the graph to free.
*/
void free_graph(Graph *graph) {
    for (int i = 0; i < graph->numberOfNodes; i++) {
        free(graph->nodes[i]);
    }
    free(graph);
}


/**
 * Prints the graph.
 * In the format :
 * 1. node1 -> node2 (distance)
 * 
 * @param graph the graph to print.
*/
void print_graph(Graph *graph) {
    for (int i = 0; i < graph->numberOfNodes; i++) {
        for (int j = 0; j < graph->numberOfNodes; j++) {
            if (graph->adjacencyMatrix[i][j] != INF) {
                printf("%s -> %s (%d)\n", 
                graph->nodes[i]->name, 
                graph->nodes[j]->name, 
                graph->adjacencyMatrix[i][j]);
            }
        }
    }
}