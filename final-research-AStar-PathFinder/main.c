#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

#include "djikstra.h"

/**
 * This function reads a file and returns a string containing the contents of the file.
 *
 * @param filename the name of the file to read.
 * @return  a string containing the contents of the file.
 */
char *read_file(const char *filename) {
    FILE *f = fopen(filename, "rb");
    if (f == NULL) {
        perror("Error opening file");
        return NULL;
    }

    fseek(f, 0, SEEK_END);
    long fsize = ftell(f);
    fseek(f, 0, SEEK_SET);

    char *string = malloc(fsize + 1);
    fread(string, fsize, 1, f);
    fclose(f);

    string[fsize] = '\0';
    return string;
}

/**
 * Intializes a graph and adds vertices from a file.
 *
 * @param filename the name of the file to read.
 * @return a graph containing the vertices from the file.
 */
Graph* vertices_from_file(const char *filename) {
    char* string = read_file(filename);
    if (string == NULL) {
        perror("Error opening file for vertices");
        return NULL;
    }

    // initialize graph
    Graph *graph = initializeGraph();

    // read each line and create vertices
    char *line = strtok(string, "\n");
    while (line != NULL) {
        // create a new node
        int index = add_to_graph(graph, line);
        line = strtok(NULL, "\n");
    }

    return graph;
}

/**
 * Adds distances to a graph from a file.
 *
 * @param filename the name of the file to read.
 * @param graph the graph to add distances to.
 * @return a graph containing the distances from the file.
 */
Graph* distances_from_file(const char *filename, Graph *graph) {
    char* string = read_file(filename);
    if (string == NULL) {
        perror("Error opening file for distances.");
        return NULL;
    }

    // read each line and create edges
    // must be in the format: <source> <destination> <weight>
    char *line = strtok(string, "\n");
    while (line != NULL) {
        char source[100], destination[100]; int weight;
        sscanf(line, "%s %s %d", source, destination, &weight);
        set_edge_distance(graph, source, destination, weight);
        line = strtok(NULL, "\n");
    }
    return graph;
}


int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Usage: %s <vertices_path> <distances_path>\n", argv[0]);
        return 1;
    }

    // get the file path
    char *filepath_1 = argv[1];
    char *filepath_2 = argv[2];

    // initialize graph and create vertices
    Graph *graph = vertices_from_file(filepath_1);

    // create edges
    graph = distances_from_file(filepath_2, graph);

    // print the graph
    print_graph(graph);

    // get the source node
    char sourceName[100];
    printf("Enter the source node: ");
    scanf("%s", sourceName);
    int sourceNodeIndex = get_index(graph, sourceName);
    if (sourceNodeIndex == -1) {
        printf("Node not found.\n");
        return 1;
    }

    // get the shortest path list
    int* shortest_path_list = Dijkstra(sourceNodeIndex, graph, true);


    return 0;
}