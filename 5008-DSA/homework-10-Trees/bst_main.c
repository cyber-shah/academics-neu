/**
 * Use this file to add your own tests and run a mini BST program.
 *
 * Things to test for...
 *
 * Fill a Binary Search Tree with values 1-100 and searching for nodes
 * Add 100 nodes and then check that the size is 100
 * Add 100 nodes with the value starting at 0 to 99, then check the sum of the tree to be 4950
 * etc...
 * (don't forget to check what happens in the null cases such as trying to find nodes that don't exist,
 * or any action on an empty tree...)
*/
#include <time.h>
#include <stdio.h>
#include <stdlib.h>

#include "my_bst.h"
#include "my_bst.c"
#include "my_bst_util.c"

// a helper to get a range  of numbers
int *get_range_array(int start, int end) {
    int *arr = (int*) malloc(sizeof(int) * (end - start));
    int j = 0;
    for (int i = start; i < end; i++, j++) arr[j] = i;
    return arr;
}

// sample helper function to help you create random int arrays
int *get_random_array(int size) {
    int *arr = (int *)malloc(sizeof(int) * size);
    srand(time(NULL)); // seed the random number generator

    for (int i = 0; i < size; i++) {
        arr[i] = rand() % 100;
    }

    return arr;
}

/**
 * Unit Tests
 * 
 * 1. Create a BST
 *    1.1 check the function bst_is_empty
 *    1.2 check the function bst_size
 * 
 * 2. Add a value to the BST
 * 2.1 Add at root
 * 2.2 Add at left
 * 2.3 Add at right
 * 2.4 Add in any order
 * 2.4 Add a duplicate value
 * 2.5 add 5 values
 * 2.6 add 100 values
 * 
 * 3. Check if a value exists in the BST (check get size too)
 * 3.1 check if a value exists in an empty tree
 * 3.2 check if a value exists in a tree with 1 value
 * 3.3 find the value in a tree with 10 values
 *      3.3.1 find the value at the root
 *      3.3.2 find the value at the left
 *      3.3.3 find the value at the right
 * 3.4 find a value that doesn't exist
 * 3.5 find a value in a tree with 100 values
 * 
 * 4. Get the sum of the BST
 * 4.1 get the sum of an empty tree
 * 4.2 get the sum of a tree with 1 value
 * 4.3 get the sum of a tree with 10 values
 * 
 * 5. Get the min value of the BST
 * 5.1 get the min of an empty tree
 * 5.2 get the min of a tree with 1 value
 * 5.3 get the min of a tree with 10 values
 * 
 * 
 * 6. Get the max value of the BST
 * 6.1 get the max of an empty tree
 * 6.2 get the max of a tree with 1 value
 * 6.3 get the max of a tree with 10 values
 * 
 * 
*/

int test_create_bst() {
    BST *tree = (BST *)create_bst();

    if (bst_is_empty(tree) == true && bst_size(tree) == 0) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;

}

int test_add_bst_one() {
    BST *tree = (BST *)create_bst();
    bst_add(tree, 5);

    if (bst_is_empty(tree) == 0 && bst_size(tree) == 1) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_add_bst_increasing() {
    BST *tree = (BST *)create_bst();
    bst_add(tree, 10);
    bst_add(tree, 15);
    bst_add(tree, 20);
    bst_add(tree, 25);

    if (bst_size(tree) == 4) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_add_bst_noOrder() {
    BST *tree = (BST *)create_bst();
    bst_add(tree, 10);
    bst_add(tree, 5);
    bst_add(tree, 25);
    bst_add(tree, 15);
    bst_add(tree, 20);

    if (bst_size(tree) == 5) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_add_bst_duplicate() {
    BST *tree = (BST *)create_bst();
    bst_add(tree, 10);
    bst_add(tree, 5);
    bst_add(tree, 5);
    bst_add(tree, 10);

    if (bst_size(tree) == 2) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_add_bst_100() {
    BST *tree = (BST *)create_bst();
    int *arr = get_range_array(0, 100);
    for (int i = 0; i < 100; i++) {
        bst_add(tree, arr[i]);
    }

    if (bst_size(tree) == 100) {
        free(tree);
        free(arr);
        return 1;
    }
    free(tree);
    free(arr);
    return 0;
}

int test_find_bst_empty() {
    BST *tree = (BST *)create_bst();
    if (bst_exists(tree, 10) == false) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_find_bst_null() {
    BST *tree = NULL;
    if (bst_exists(tree, 10) == false) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_find_bst_one() {
    BST *tree = (BST *)create_bst();
    bst_add(tree, 10);
    if (bst_exists(tree, 10) == true && bst_exists(tree, 5) == false) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_find_bst_10() {
    BST *tree = (BST *)create_bst();
    bst_add(tree, 10);
    bst_add(tree, 5);
    bst_add(tree, 25);
    bst_add(tree, 15);
    bst_add(tree, 20);
    if (bst_exists(tree, 10) == true && bst_exists(tree, 5) == true && 
        bst_exists(tree, 25) == true && bst_exists(tree, 15) == true && 
        bst_exists(tree, 20) == true && bst_exists(tree, 30) == false &&
        bst_exists(tree, 0) == false && bst_exists(tree, 100) == false) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int testFindBst_OneSided() {
    BST *tree = (BST *)create_bst();
    int *arr = get_range_array(0,99);
    for (int i = 0; i < 100; i++) {
        bst_add(tree, arr[i]);
    }

    for (int i = 0; i < 100; i++) {
        if (bst_exists(tree, arr[i]) == true) {
            free(tree);
            free(arr);
            return 1;
        }
    }
    free(tree);
    free(arr);
    return 0;
}

int test_find_bst_random_100() {
    BST *tree = (BST *)create_bst();
    int *arr = get_random_array(100);
    for (int i = 0; i < 100; i++) {
        bst_add(tree, arr[i]);
    }

    for (int i = 0; i < 100; i++) {
        if (bst_exists(tree, arr[i]) == true) {
            free(tree);
            free(arr);
            return 1;
        }
    }
    free(tree);
    free(arr);
    return 0;
}

int test_min() {
    BST *tree = (BST *)create_bst();
    bst_add(tree, 10);
    bst_add(tree, 5);
    bst_add(tree, 25);
    bst_add(tree, 15);
    bst_add(tree, 20);
    if (min(tree) == 5) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_min_empty() {
    BST *tree = (BST *)create_bst();
    if (min(tree) == -1) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_min_null() {
    BST *tree = NULL;
    if (min(tree) == -1) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_max() {
    BST *tree = (BST *)create_bst();
    bst_add(tree, 10);
    bst_add(tree, 5);
    bst_add(tree, 25);
    bst_add(tree, 15);
    bst_add(tree, 20);
    if (max(tree) == 25) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_max_empty() {
    BST *tree = (BST *)create_bst();
    if (max(tree) == -1) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_max_null() {
    BST *tree = NULL;
    if (max(tree) == -1) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_sum() {
    BST *tree = (BST *)create_bst();
    bst_add(tree, 10);
    bst_add(tree, 5);
    bst_add(tree, 25);
    bst_add(tree, 15);
    bst_add(tree, 20);
    if (sum(tree) == 75) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_sum_empty() {
    BST *tree = (BST *)create_bst();
    if (sum(tree) == 0) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

int test_sum_null() {
    BST *tree = NULL;
    if (sum(tree) == 0) {
        free(tree);
        return 1;
    }
    free(tree);
    return 0;
}

// --------------------------------------------------

int (*unitTests[])(int) = {
        test_create_bst,
        test_add_bst_one,
        test_add_bst_increasing,
        test_add_bst_noOrder,
        test_add_bst_duplicate,
        test_add_bst_100,
        test_find_bst_empty,
        test_find_bst_null,
        test_find_bst_one,
        test_find_bst_10,
        testFindBst_OneSided,
        test_find_bst_random_100,
        test_min,
        test_min_empty,
        test_min_null,
        test_max,
        test_max_empty,
        test_max_null,
        test_sum,
        test_sum_empty,
        test_sum_null,

        NULL
};

int main(int argc, char const *argv[])
{
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