#include <stdio.h>
#include "djikstraHeader.h"

Graph* graph_from_file(const char *filepath) {
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
}


int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <file_path>\n", argv[0]);
        return 1;
    }
    // get the file path
    char *filepath = argv[1];
    // create the graph
    Graph *graph = graph_from_file(filepath);
    return 0;
}