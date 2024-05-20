/* Compile this assignment with: clang -g -Wall main.c -o main.out
 Use this file to implement testing for your
 doubly linked list implementation*/

#include <stdio.h>  // For IO operations
#include <stdlib.h> // for malloc/free

#include "my_dll.h"


/** Tests on empty lists
 * Count should be 0 // dll empty should be true
 * Head and tail should be NULL
 */
int test_empty_list1(int status) {
    dll_t* test = create_dll();
    if (dll_empty(test) == 1 && test->head == NULL && test->tail == NULL) {
        free_dll(test);
        return 1;
    }
    else {
        free_dll(test);
        return 0;
    }
}

// Tests pop_front and pop_back on empty list
int test_empty_list2(int status) {
    dll_t* test = create_dll();
    int pop_result = dll_pop_back(test);
    int pop_result2 = dll_pop_front(test);
    if (pop_result == 0 && pop_result2 == 0) {
        free_dll(test);
        return 1;
    }
    else {
        free_dll(test);
        return 0;
    }

}

// tests push_back on a single node list
int test_single_node_list(int status) {
    dll_t* test = create_dll();
    dll_push_back(test, 1);
    if (dll_empty(test) == 0 && test->head->data == 1 && test->tail->data == 1 && test->count == 1) {
        free_dll(test);
        return 1;
    }
    else {
        free_dll(test);
        return 0;
    }
}

/** Tests insert
 * Inserts at the middle and then frees
 */
int test_insert_1(int status)
{
    dll_t* test = create_dll();
    for (int i = 0; i < 10; i++) {
        dll_push_back(test,i);
    }
    dll_insert(test,5, 100);
    int item = dll_get(test, 5);
    if (item == 100) {
        free_dll(test);
        return 1;
    }
    else
    {
        free_dll(test);
        return 0;
    }
}

int test_insert_2(int status) {
    dll_t* l = create_dll();
    // insert at the first position
    for (int i = 5; i >= 0; i--) {
        dll_insert(l, 0, i);
    }
    //insert at last position
    for (int i = 6; i <= 10; i++) {
        dll_insert(l, i, i);
    }

    // check all the elements in the list
    for (int i = 0; i <= 10; i++) {
        int item = dll_get(l, i);
        if (item != i) {
            return 0;
        }
    }
    return 1;
}

/** Tests get function
 * get a node in the middle
 */
int test_get_1(int status)
{
    dll_t* test = create_dll();
    for (int i = 0; i < 10; i++) {
        dll_push_back(test,i);
    }

    int item = dll_get(test,5);
    if (item == 5) {
        free_dll(test);
        return 1;
    }
    else
    {
        free_dll(test);
        return 0;
    }
}

int test_pop_front_1(int status)
{
    dll_t* test = create_dll();
    for (int i = 0; i < 10; i++) {
        dll_push_back(test,i);
    }
    dll_pop_front(test);
    int item = dll_get(test,0);
    if (item == 1) {
        free_dll(test);
        return 1;
    }
    else
    {
        free_dll(test);
        return 0;
    }
}

/**
 * Tests the remove function
 */
int test_remove_1(int status) {
    dll_t* t = create_dll();
    for(int i = 0; i < 10; i++) {
        dll_push_back(t, i);
    }

    for (int i = 0; i < 10; i++) {
        dll_remove(t, 0);
    }
    // check size
    if (0 == dll_size(t)) {
        return 1;
    }
    else {
        return 0;
    }
}

int test_remove_2(int status) {
    dll_t* t = create_dll();
    for(int i = 0; i < 10; i++) {
        dll_push_back(t, i);
    }
    // remove odd elements
    if (9 == dll_remove(t, 9)) {
        return 1;
    }
    else {
        return 0;
    }
}

/* An array of function pointers to all of the tests
 that main() can use iterate over them.
 UNCOMMENT Tests as you are ready to use them
 Add your own tests!*/
int (*unitTests[])(int) = {
        test_empty_list1,
        test_empty_list2,
        test_single_node_list,
        test_insert_1,
        test_insert_2,
        test_get_1,
        test_pop_front_1,
        test_remove_1,
        test_remove_2,
        NULL};

/* ====================================================
 ================== Program Entry ===================
 ====================================================*/
int main()
{
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

    return 0;
}