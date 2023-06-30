#include <stdio.h>
#include <stdbool.h>
#include <assert.h>
#include "CHeaderFibonacci.h"

// Test fibonacci_iterative function
void test_fibonacci_iterative() {
    printf("Running test_fibonacci_iterative...\n");
    assert(fibonacci_iterative(0, 0) == 0);
    assert(fibonacci_iterative(1, 0) == 1);
    assert(fibonacci_iterative(5, 0) == 5);
    assert(fibonacci_iterative(10, 0) == 55);
    printf("test_fibonacci_iterative passed!\n");
}

// Test fibonacci_recursive function
void test_fibonacci_recursive() {
    printf("Running test_fibonacci_recursive...\n");
    assert(fibonacci_recursive(0) == 0);
    assert(fibonacci_recursive(1) == 1);
    assert(fibonacci_recursive(5) == 5);
    assert(fibonacci_recursive(10) == 55);
    printf("test_fibonacci_recursive passed!\n");
}

// Test fibonacci_dp function
void test_fibonacci_dp() {
    printf("Running test_fibonacci_dp...\n");
    long long int value_table[11];  // Adjust the array size according to the maximum value of n you want to test
    for (int i = 0; i < 11; i++) {  // Initialize the value_table array with -1
        value_table[i] = -1;
    }
    assert(fibonacci_dp(0, value_table) == 0);
    assert(fibonacci_dp(1, value_table) == 1);
    assert(fibonacci_dp(5, value_table) == 5);
    assert(fibonacci_dp(10, value_table) == 55);
    printf("test_fibonacci_dp passed!\n");
}

// Test fibonacci_manager function
void test_fibonacci_manager() {
    printf("Running test_fibonacci_manager...\n");
    assert(fibonacci_manager(0, 0, 0) == 0);
    assert(fibonacci_manager(1, 0, 0) == 1);
    assert(fibonacci_manager(5, 0, 0) == 5);
    assert(fibonacci_manager(10, 0, 0) == 55);
    printf("test_fibonacci_manager passed!\n");
}


// Run all the tests
void run_all_tests() {
    test_fibonacci_iterative();
    test_fibonacci_recursive();
    test_fibonacci_dp();
    test_fibonacci_manager();
}

int main() {
    run_all_tests();
    return 0;
}
