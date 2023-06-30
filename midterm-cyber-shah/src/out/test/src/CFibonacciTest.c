#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <assert.h>
#include "HeaderFibonacci.h"

// Function to compare two integer arrays
bool compareArrays(int* arr1, int* arr2, int size) {
    for (int i = 0; i < size; i++) {
        if (arr1[i] != arr2[i]) {
            return false;
        }
    }
    return true;
}

// Function to test the fibonacci_iterative function
void testFibonacciIterative() {
    int n = 10;
    int expected[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
    int result = fibonacci_iterative(n, 2);

    assert(result == expected[n]);

    printf("Test Fibonacci Iterative: Passed\n");
}

// Function to test the fibonacci_recursive function
void testFibonacciRecursive() {
    int n = 10;
    int expected[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
    int result = fibonacci_recursive(n);

    assert(result == expected[n]);

    printf("Test Fibonacci Recursive: Passed\n");
}

// Function to test the fibonacci_dp_controller function
void testFibonacciDP() {
    int n = 10;
    int expected[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
    int result = fibonacci_dp_controller(n, 2);

    assert(result == expected[n]);

    printf("Test Fibonacci Dynamic Programming: Passed\n");
}

// Function to test the fibonacci_manager function
void testFibonacciManager() {
    int n = 10;
    int expected[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};

    for (int type = 0; type <= 2; type++) {
        int result = fibonacci_manager(n, type, 2);

        assert(result == expected[n]);
    }

    printf("Test Fibonacci Manager: Passed\n");
}

// Function to test the help function
void testHelp() {
    // No assertions, just verify the output manually
    help();
}

// Function to run all the tests
void runTests() {
    testFibonacciIterative();
    testFibonacciRecursive();
    testFibonacciDP();
    testFibonacciManager();
    testHelp();
}

int main() {
    runTests();
    return 0;
}
