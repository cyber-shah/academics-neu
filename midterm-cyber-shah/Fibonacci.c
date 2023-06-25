#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "Fibonacci.h"

int main(int argc, char* argv[]) {
    // if number of arguments is less than 2
    if (argc < 2)
    {
        printf("at least two arguments needed!\n");
        help();
        return 1;
    }

    // Nth value
    const int n = atoi(argv[1]);
    // defaults to 0
    int type = 0;
    int print = 0;

    // if second argument is provided = its type
    if (argc > 2) {
        type = atoi(argv[2]);
    }
    // if third is provided = its print
    bool print_it = false;
    if(argc > 3) {
        print = atoi(argv[3]);
        // if print is greater than one, print
        if (print > 1) {
            print_it = true;
        }
    }

    fibonacci_manager(n, type, print);
    return 0;
}