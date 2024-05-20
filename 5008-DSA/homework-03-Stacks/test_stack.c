/*  ========== H03 - Personal Queue Implementation ============
 *
 *   Student: UPDATE
 *   Semester: UPDATE
 *
 * A simple queue unit-tst implementation
 *
 */

#include "mystack.h"
// Note that we are locating this file
// within the same directory, so we use quotations
// and provide the path to this file which is within
// our current directory.

// A sample test of your program
// You can add as many unit tests as you like
// We will be adding our own to test your program.

// 1. Tests the capacity of a stack
int unitTest_C1(int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(MAX_DEPTH);
    if (MAX_DEPTH == test_s->capacity)
    {
        passed = 1;
    }
    free_stack(test_s);
    return passed;
}

// 1.2 Capacity Test - 2 when capacity is 0.
int unitTest_C2 (int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(0);
    if (MAX_DEPTH == test_s->capacity)
    {
    passed = 1;
    }
    free_stack(test_s);
    return passed;
}

// Enqueue several items into a stack and test the size
int unitTest_E1(int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(MAX_DEPTH);
    stack_enqueue(test_s, 1);
    stack_enqueue(test_s, 2);
    stack_enqueue(test_s, 3);
    stack_enqueue(test_s, 4);
    stack_enqueue(test_s, 5);
    stack_enqueue(test_s, 6);
    stack_enqueue(test_s, 7);
    stack_enqueue(test_s, 8);
    stack_enqueue(test_s, 9);
    stack_enqueue(test_s, 10);
    if (10 == stack_size(test_s))
    {
        passed = 1;
    }
    free_stack(test_s);
    return passed;
}

// Enqueue over the capacity of a stack and test the return value
int unitTest_E2(int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(MAX_DEPTH);

    // enqueue 32 items
    int i = 0;
    for (i = 0; i < MAX_DEPTH; i++)
    {
        stack_enqueue(test_s, 1);
    }
    // 33rd time should return -1
    if (stack_enqueue(test_s, 1) == -1)
    {
        passed = 1;
    }
    return passed;
}

// Tests enqueuing and fully dequeue a stack
int unitTest_E3(int status)
{
    int passed = 0;

    // create a new stack
    neu_stack *test_s = create_stack(MAX_DEPTH);

    // enqueue 32 items
    int i = 0;
    for (i = 0; i < MAX_DEPTH; i++)
    {
        stack_enqueue(test_s, 1);
    }

    // dequeue 32 items
    for (i = 0; i < MAX_DEPTH; i++)
    {
        stack_dequeue(test_s);
    }

    // check if stack is empty
    if (0 == stack_size(test_s))
    {
        passed = 1;
    }

    // free stack
    free_stack(test_s);
    return passed;
}

// dequeue an empty stack
int unitTest_D2(int status)
{
    int passed = 0;
    // create a new stack
    neu_stack *test_s = create_stack(MAX_DEPTH);
    if (stack_dequeue(test_s) == EXIT_FAILURE)
    {
        passed = 1;
    }
    return passed;
}

// Tests enqueuing and fully queueing a stack multiple times
int unitTest_D1(int status)
{
    int passed = 0;

    neu_stack *test_s = create_stack(MAX_DEPTH);
    int i = 0;
    for (i = 0; i < MAX_DEPTH; i++)
    {
        stack_enqueue(test_s, 1);
    }
    for (i = 0; i < MAX_DEPTH; i++)
    {
        stack_dequeue(test_s);
    }
    for (i = 0; i < MAX_DEPTH; i++)
    {
        stack_enqueue(test_s, 1);
    }
    for (i = 0; i < MAX_DEPTH; i++)
    {
        stack_dequeue(test_s);
    }
    if (0 == stack_size(test_s))
    {
        passed = 1;
    }

    free_stack(test_s);

    return passed;
}

// dequeue and order of values returned
int unitTest_D3(int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(MAX_DEPTH);
    for (int i = 0; i<MAX_DEPTH; i++)
    {
        stack_enqueue(test_s, i);
    }

    // dequeue 32 items and check if they are in order
    for (int i = 0; i<MAX_DEPTH; i++)
    {
        int removed = stack_dequeue(test_s);
        if (removed != MAX_DEPTH - i-1)
        {
            return 0;
        }
        else {
            passed = 1;
        }
    }
    return passed;
}

// Simple enqueue and deque stack test
// Also confirms that a stack is full
int unitTest5(int status)
{
    int passed = 0;

    neu_stack *test_s = create_stack(1);
    stack_enqueue(test_s, 1);
    if (1 == stack_full(test_s))
    {
        passed = 1;
    }
    else
    {
        free_stack(test_s);
        return 0;
    }

    stack_dequeue(test_s);
    if (0 == stack_full(test_s))
    {
        passed = 1;
    }
    else
    {
        passed = 0;
    }

    free_stack(test_s);

    return passed;
}

// EMPTY STACK test
int unitTest_EM1(int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(1);
    if (1 == stack_empty(test_s))
    {
        passed = 1;
    }
    else
    {
        passed = 0;
    }
    free_stack(test_s);
    return passed;
}

// EMPTY STACK TEST 2
int unitTest_EM2(int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(1);
    stack_enqueue(test_s, 1);
    stack_dequeue(test_s);
    if (1 == stack_empty(test_s))
    {
        passed = 1;
    }
    else
    {
        passed = 0;
    }
    free_stack(test_s);
    return passed;
}

// FULL STACK TEST
int unitTest_F1(int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(1);
    stack_enqueue(test_s, 1);
    if (1 == stack_full(test_s))
    {
        passed = 1;
    }
    else
    {
        passed = 0;
    }
    free_stack(test_s);
    return passed;
}

// FULL STACK TEST 2 - not full
int unitTest_F2(int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(1);
    stack_enqueue(test_s, 1);
    stack_dequeue(test_s);
    if (0 == stack_full(test_s))
    {
        passed = 1;
    }
    else
    {
        passed = 0;
    }
    free_stack(test_s);
    return passed;
}

// STACK SIZE TEST
int unitTest_S1(int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(1);
    stack_enqueue(test_s, 1);
    if (1 == stack_size(test_s))
    {
        passed = 1;
    }
    else
    {
        passed = 0;
    }
    free_stack(test_s);
    return passed;
}

// STACK SIZE TEST 2
int unitTest_S2(int status)
{
    int passed = 0;
    neu_stack *test_s = create_stack(1);
    stack_enqueue(test_s, 1);
    stack_dequeue(test_s);
    if (0 == stack_size(test_s))
    {
        passed = 1;
    }
    else
    {
        passed = 0;
    }
    free_stack(test_s);
    return passed;
}

// add your own, and uncomment the provided tests as
// things are implemented
int (*unitTests[])(int) = {
        unitTest_C1,      // CONSTRUCTOR provided
    unitTest_C2,        // CONSTRUCTOR Test - 2 when capacity is 0.
    unitTest_E1,        // ENQUEUE provided
    unitTest_E2,        // ENQUEUE over the capacity of a stack and test the return
    unitTest_E3,        // ENQUEUE provided
    unitTest_D1,        // DEQUEUE provided
    unitTest_D2,        // DEQUEUE an empty stack
    unitTest_D3,        // DEQUEUE and order of values returned
    unitTest5,          // provided
    unitTest_EM1,      // EMPTY STACK test
    unitTest_EM2,    // EMPTY STACK TEST 2
    unitTest_F1,      // FULL STACK TEST
    unitTest_F2,    // FULL STACK TEST 2 - not full
    unitTest_S1,      // STACK SIZE TEST
    unitTest_S2,    // STACK SIZE TEST 2
    NULL};

// ====================================================
// ================== Program Entry ===================
// ====================================================
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