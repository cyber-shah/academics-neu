#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "hashmap.h"
#include "graph.h"

/**
 * Finds the unvisited node with the minimum distance.
 * 
 * @param visited_list the array of visited_list nodes.
 * @param distArray the array of nodes with their distance from the source node.
 * @returns node_index with the minimum distance.
*/
int find_min_distance_node(bool visited_list[], int distances_list[]) {
    int minDistance = INF;
    int node_index = -1;

    // for all nodes if unvisited and distance less than minDistance.
    for (int i = 0; i < MAX_NODES; i++) {
        if (visited_list[i] == false && distances_list[i] <= minDistance) {
            minDistance = distances_list[i];
            node_index = i;
        }
    }
    return node_index;
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
 * 4. Repeat steps 2 and 3 until all the nodes are visited_list.
 * 5. Keep updating the distance array.
 * 
 * @param sourceNode the source node.
 * @param graph the graph to find the shortest path.
 * @returns distances_list an array of distances from the source node to all the nodes.
*/
int* djikstra(int sourceNode, Graph *graph) {
    // initialize all the visited_list nodes to false.
    bool visited_list [MAX_NODES] = {false};
    // array of nodes - to store the distance from the source node.
    int* distances_list = (int*) malloc(sizeof(int) * MAX_NODES);


    // initialize all the distances to INF.
    for (int i = 0; i < MAX_NODES; i++) {
        distances_list[i] = INF;
    }
    // distance of source node from itself is 0.
    distances_list[sourceNode] = 0;

    // loop through all the nodes
    for (int i = 0; i < (graph->numberOfNodes - 1); ++i) {
        // finds the node with the minimum distance to visit next.
        int minDistanceNode = find_min_distance_node(visited_list, distances_list);

        // mark the node as visited
        visited_list[minDistanceNode] == true;

        // loop through all the nodes.
        for (int j = 0; j < graph->numberOfNodes; ++j) {
            // relax all the adjacent unvisited nodes. adjacent means distance != INF
            if (visited_list[j] == false && graph->adjacencyMatrix[minDistanceNode][j] != INF) {
                int newDistance = distances_list [minDistanceNode] 
                                  + graph->adjacencyMatrix[minDistanceNode][j];
                if (newDistance < distances_list[j]) {
                    // update the distance
                    distances_list[j] = newDistance;
                }
            }
        }
    }
    return distances_list;
}
