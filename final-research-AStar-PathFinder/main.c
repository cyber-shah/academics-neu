#include <stdio.h>
#include "djikstraHeader.h"

char *read_file(const char *filename) {
    FILE *f = fopen(filename, "rb");
    if (f == NULL) {
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

Graph* vertices_from_file(const char *filename) {

    char* string = read_file(filename);
    if (string == NULL) {
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

    // print the vertices
    for (int i = 0; i < graph->numberOfNodes; i++) {
        printf("%d %s\n", i, graph->nodes[i]->name);
    }

    return graph;
}

/* Graph* vertices_from_chars(const char *filepath) {
    // initialize graph
    Graph *graph = initializeGraph();

    // read the file
    FILE *file = fopen(filepath, "r");
    if (file == NULL) {
        perror("Error opening file \n");
    }
    // read the file line by line and add to graph
    char line[256];

    // get the characters and create nodes and add nodes
    while (fgets(line, sizeof(line), file)) {
        // create a new node
        int index = add_to_graph(graph, line);   
    }
    for (int i = 0; i < graph->numberOfNodes; i++) {
        printf("%d %s\n", i, graph->nodes[i]->name);
    }

    fclose(file);
    return graph;    
} */




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
    // graph = distances_from_file(filepath_2, graph);

    return 0;
}