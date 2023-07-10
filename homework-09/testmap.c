/** 
 * CS 5008 - Homework 09
 * Student: Pranchal Shah
 * Semester: Summer 2023 Online
*/

#include "hashmap.h"

/**
 * CREATE
 * 1. create a hash map and check if size is correct
 * and everything is initialised to null
 * 2. if size is 0
 * 3. if size is negative
 * 4. if size is a large number
 * 
 * PUT
 * 1. if key does not exist
 * 2. if key already exists, update
 * 3. if collision add next
 * 4. put with NULL key
 * 5. empty key value pair
 * 
 * GET
 * 1. no item with they specified key at index
 * 2. key found
 * 3. key found at index but not == given key
 * 4. NULL key
 * 
 * DELETE
 * 1. key doesnot exists
 * 2. key exists
 * 3. delete from empty hashmap
 * 
 * PRINT
 * 1. empty list
 * 2. with items
*/


int (*unitTests[])(int) = {
    NULL
};




/** use this file for tests. 
 * 
 * Below isn't actually any 'real' tests, it
 * just simply is a sample run. 
*/

int main() {
   hashmap* map = map_create(2);

    map_put(map, "Apple", 2.08);
    map_put(map, "Orange", 3.01);
    map_put(map, "Orange", 2.50);
    // it should update the value instead of adding a new one

    map_print(map);


    printf("Dread is %.2f\n", map_get(map, "Orange"));

    map_del(map, "Apple");
    map_del(map, "Pineapple");
    map_print(map);

    map_free(map);


/*     
    // TESTING SUITE ----------------------------------
    unsigned int testsPassed = 0;
    // List of Unit Tests to test your data structure
    int counter = 0;
    while (unitTests[counter] != NULL)
    {
        printf("========unitTest %d========\n", counter);
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
*/
    return 0;
}