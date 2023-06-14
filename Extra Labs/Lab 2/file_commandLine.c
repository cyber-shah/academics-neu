#include <stdio.h>
#include <stdlib.h>
#define BUFF_SIZE 256

int main (int argc, char** argv) {

    printf("Number of Command Line arguments: %d\n",argc);

    for (int i = 0; i < argc; i++) {
        printf("Argument %d: %s \n",i, argv[i]);
    }


    char buff[BUFF_SIZE];

    FILE* input = fopen(argv[1],"r");
    char* line = malloc(BUFF_SIZE * sizeof(char));
    printf("Contents of the file are: \n");
    while (fgets(line, BUFF_SIZE, input) != NULL) {
        // do the thing, line now has the line from input
        printf("%s", line);
    }

    free(line); // don't forget to free memory!
    fclose(input); // don't forget to close a file!
}
