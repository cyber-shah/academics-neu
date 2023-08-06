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

    // get the characters and create nodes
    while (fgets(line, sizeof(line), file)) {
        // create a new node
        Node *new_nodes = create_node(line);
        printf("%s", new_nodes->name);
    }


    const int x = 5;
    int *ptr = (int*)&x; // cast away const-ness
    *ptr = 10; // modify the value of x
    

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