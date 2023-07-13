#include <stdio.h>
#include <stdlib.h> // contains the functions free/malloc

#include "linkedlist.h"

int main()
{
    int test_years[5] = {2018, 2017, 2016, 2015, 2014};
    int test_wins[5] = {108, 93, 93, 78, 71};

    // TODO: Build a list with the above data, use add_back. 

    NeuList *list = create_list();
    for (int i = 0; i < 5; i++) {
        add_back(list, test_wins[i], test_years[i]);
    }

    // print the list out to make sure it's correct using your print list function you wrote in linkedlist.h
    print_list(list);

    Pair* found = find(list, 2016); // should print 93
    printf("\nFound : %d\n", found->num_wins);

    free(list);
    return 0;
}