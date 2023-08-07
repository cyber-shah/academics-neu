//
// Created by shahp on 8/7/2023.
//

#include <stdio.h>
#include <unistd.h>

#include "algorithms/djikstra.h"


int test_graphs() {
//    char* stringVertices = "a \n b \n c \n d";
//    char* stringDistances = "a b 2 \n b c 2 \n b c 2 \n b d 1 \n c d 3 \n a d 5";
//
//    Graph *graph = graph_from_string(stringVertices, stringDistances);
//    Graph *sgraph = graph_from_files("vertices.txt", "distances.txt");

    return 1;
}









int (*unitTests[])(int) = {
        test_graphs
};



/** use this file for tests.
 *
 * Below isn't actually any 'real' tests, it
 * just simply is a sample run.
*/
int main() {
    // TESTING SUITE ----------------------------------
    unsigned int testsPassed = 0;
    // List of Unit Tests to test your data structure
    int counter = 0;
    while (unitTests[counter] != NULL)
    {
        printf("\n\n========unitTest %d========\n", counter);
        if (1 == unitTests[counter](1))
        {
            printf("passed test\n");
            testsPassed++;
        }
        else
        {
            printf("failed test, missing functionality, or incorrect test\n");
        }
        counter++;
    }

    printf("%d of %d tests passed\n", testsPassed, counter);


    return 0;
}