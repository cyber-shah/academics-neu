#include <stdio.h>
#include <djikstraHeader.h>

Graph* graph_from_file(const char *filepath) {
    // initialize graph
    Graph *graph = initializeGraph();

    // read the file
    FILE *file = fopen(filepath, "r");
    if (file == NULL) {
        perror("Error opening file \n");
        return;
    }

    // read the file line by line and add to graph
    char line[256];
    while (fgets(line, sizeof(line), file)) {
        // get the first character of the line
        char firstChar = line[0];

        // if the first character is a number, then it is a node
        if (firstChar >= '0' && firstChar <= '9') {
            // get the node number
            int nodeNumber = firstChar - '0';

            // get the distance from the source node
            int distance = line[2] - '0';

            // create a node
            Node *node = (Node *) malloc(sizeof(Node));
            node->nodeNumber = nodeNumber;
            node->distance = distance;

            // add the node to the graph
            graph->adjacencyMatrix[nodeNumber][nodeNumber] = node;
        }
    }


    fclose(file);
    return graph;    
}


int main(char *argv, int argc) {
}