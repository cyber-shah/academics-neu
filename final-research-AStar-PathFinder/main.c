#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

#include "djikstraHeader.h"

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

    char* string_copy = strcpy(malloc(strlen(string) + 1), string);
    // read each line and create edges
    // must be in the format: <source> <destination> <weight>
    char *line = strtok(string_copy, "\n");
    while (line != NULL) {
        // create a new node
        printf("%s\n", line);
        char *copy = strcpy(malloc(strlen(line) + 1), line);

        // Split the line into words using space as delimiter
        char *words[3]; // Assuming each line has three words: source, destination, weight
        int wordIndex = 0;
        char *word = strtok(copy, " ");
        while (word != NULL && wordIndex < 3) {
            words[wordIndex] = word;
            word = strtok(NULL, " ");
            wordIndex++;
        }

        // Now words[0], words[1], and words[2] contain the source, destination, and weight

        // Example usage
        if (wordIndex == 3) {
            char *source = words[0];
            char *destination = words[1];
            char *weight = words[2];

            // Use the values as needed
            printf("Source: %s, Destination: %s, Weight: %d\n", source, destination, atoi(weight));

            // Use set_edge_distance here if necessary
            // set_edge_distance(graph, source, destination, weightValue);
        }

        line = strtok(NULL, "\n");
    }

    // Clean up
    free(string);

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

    return 0;
}