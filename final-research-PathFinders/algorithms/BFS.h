#include "graph.h"
#include "myQueue.h"

void bfs(Graph *graph, int sourceNodeIndex) {
    // create a queue for BFS
    bool visited[MAX_NODES] = {false};
    struct Queue *queue = createQueue();

    // set the start node to visited
    visited[sourceNodeIndex] = true;
    enqueue(queue, sourceNodeIndex);

    // loop until queue is empty
    while (!isEmpty(queue)) {
        // get the next node from the queue
        int currentNodeIndex = dequeue(queue);
        printf("%s ", graph->nodes[currentNodeIndex]->name);

        // loop through all the adjacent nodes of currentNodeIndex
        // if a adjacent has not been visited, then mark it visited
        // and enqueue it
        for (int i = 0; i < graph->numberOfNodes; ++i) {
            if (graph->adjacencyMatrix[currentNodeIndex][i] != INF && !visited[i]) {
                visited[i] = true;
                enqueue(queue, i);
            }
        }
    }
}