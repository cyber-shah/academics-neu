/** 
 * CS 5008 - Homework 09
 * Student: Pranchal Shah
 * Semester: Summer 2023 Online
*/

#include "hashmap.h"
#include <time.h>
#include <stdio.h>

int test_create_1() {
    hashmap* map = map_create(5);
    
    // Check if all are NULL
    for (int i = 0; i < 5; i++) {
        if (map->contents[i] != NULL) {
            map_free(map);
            return 0;
        }
    }
    
    // Check for size
    if (map->size != 5) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_create_2() {
    hashmap* map = map_create(0);
    if (map != NULL) {
        map_free(map);
        return 0;
    }
    
    return 1;
}

int test_create_3() {
    hashmap* map = map_create(-5);
    if (map != NULL) {
        map_free(map);
        return 0;
    }
    
    return 1;
}

int test_create_4() {
    hashmap* map = map_create(200);
    
    // Check if all are NULL
    for (int i = 0; i < 200; i++) {
        if (map->contents[i] != NULL) {
            map_free(map);
            return 0;
        }
    }
    
    // Check for size
    if (map->size != 200) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_put_1() {
    hashmap* map = map_create(5);
    map_put(map, "Heart", 100);
    
    // Check if the value is stored correctly
    float value = map_get(map, "Heart");
    if (value != 100) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_put_2() {
    hashmap* map = map_create(5);
    map_put(map, "Heart", 100);
    map_put(map, "Heart", 200);
    
    // Check if the value is updated correctly
    float value = map_get(map, "Heart");
    if (value != 200) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_put_3() {
    hashmap* map = map_create(5);
    map_put(map, "Threat", 100);
    map_put(map, "Heart", 200);
    
    // Check if both values are stored correctly
    float value1 = map_get(map, "Threat");
    float value2 = map_get(map, "Heart");
    
    if (value1 != 100 || value2 != 200) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_put_4() {
    hashmap* map = map_create(5);
    map_put(map, "Threat", 100);
    map_put(map, "Heart", 200);
    
    // Check if value is updated correctly with collision
    map_put(map, "Threat", 300);
    float value = map_get(map, "Threat");
    
    if (value != 300) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_get_1() {
    hashmap* map = map_create(5);
    
    // Key does not exist
    float value = map_get(map, "Heart");
    if (value != -1.0F) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_get_2() {
    hashmap* map = map_create(5);
    map_put(map, "Heart", 100);
    
    // Key exists
    float value = map_get(map, "Heart");
    if (value != 100) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_get_3() {
    hashmap* map = map_create(5);
    map_put(map, "Heart", 100);
    
    // Key found at index but not equal to the given key
    float value = map_get(map, "Threat");
    if (value != -1.0F) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_get_4() {
    hashmap* map = map_create(5);
    
    // NULL key
    float value = map_get(map, NULL);
    if (value != -1.0F) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_del_1() {
    hashmap* map = map_create(5);
    
    // Key does not exist
    float value = map_del(map, "Heart");
    if (value != -1.0F) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_del_2() {
    hashmap* map = map_create(5);
    map_put(map, "Heart", 100);
    
    // Key exists
    float value = map_del(map, "Heart");
    if (value != 100) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}

int test_del_3() {
    hashmap* map = map_create(5);
    
    // Delete from an empty hashmap
    float value = map_del(map, "Heart");
    if (value != -1.0F) {
        map_free(map);
        return 0;
    }
    
    map_free(map);
    return 1;
}


    /** psuedo code
     * 1. sizes to test: data_size[] = {100, 500, 1000, 5000, 10000};
     * for each item in data_size
     * 2. time the following
     *      2.1 create: hashmap* map = map_create(i);
     *      2.2 put: map_put(map, 50, "fifty");
     *      2.3 get: float value = map_get(map, 50);
     *      2.4 del: float value = map_del(map, 50);
    */

void printResultsTable() {
    int data_size[] = {100, 500, 1000, 5000, 10000};
    int array_size = sizeof(data_size)/ sizeof(data_size[0]);

    printf("| N | create | put | get | delete |\n");
    printf("|---|--------|-----|-----|--------|\n");

    for (int i = 0; i < array_size; i++) {
        clock_t start, end;
        double cpu_time_used;

        // time create ------------------------------
        start = clock();
        hashmap* map = map_create(data_size[i]);
        end = clock();
        cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC * 1000;
        printf("| %d | %.6f |", data_size[i], cpu_time_used);

        // time put ------------------------------
        start = clock();
        map_put(map, "fifty", 50.000);
        end = clock();
        cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC * 1000;
        printf(" %.6f |", cpu_time_used);

        // time get ------------------------------
        start = clock();
        float value = map_get(map, "fifty");
        if (value != 50.000) {
            printf(" ERROR |");
        } else {
            end = clock();
            cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC * 1000;
            printf(" %.6f |", cpu_time_used);
        }

        // time del ------------------------------
        start = clock();
        float del_value = map_del(map, "fifty");
        if (del_value != 50.000) {
            printf(" ERROR |");
        } else {
            end = clock();
            cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC * 1000;
            printf(" %.6f |", cpu_time_used);
        }

        printf("\n");
    }
}



    
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
    test_create_1,
    test_create_2,
    test_create_3,
    test_create_4,
    test_put_1,
    test_put_2,
    test_put_3,
    test_put_4,
    test_get_1,
    test_get_2,
    test_get_3,
    test_get_4,
    test_del_1,
    test_del_2,
    test_del_3,
    NULL
};

/** use this file for tests. 
 * 
 * Below isn't actually any 'real' tests, it
 * just simply is a sample run. 
*/
int main() {
   hashmap* map = map_create(2);

    map_put(map, "Apple", 2.08); map_put(map, "Orange", 3.01);
    map_put(map, "Orange", 2.50);
    // it should update the value instead of adding a new one
    map_print(map);

    printf("\nDread is %.2f\n", map_get(map, "Orange"));

    map_del(map, "Apple"); map_del(map, "Pineapple"); map_print(map);
    map_free(map);

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

    // lets time all the functions
    printResultsTable();

    return 0;
}