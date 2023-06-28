#include <stdio.h>

// declarations
int fibonacci_dp(int n, int* value_table);
int fibonacci_dp_controller(int n, int print);
int fibonacci_recursive(int n);
int fibonacci_recursive_controller(int n, int print);
int fibonacci_iterative(int n, int print);

/**
 * @brief Prints the help message for the program
 */
void help() {
    printf("./Fibonacci.out [N] [Type] [Print Level]\n");
    printf("\t[N] = Nth Fibonacci number, required.\n");
    printf("\t[Type] is either 2 for dynamic programming version, 1 for recursive version, 0 for iterative version, defaults to 0.\n");
    printf("\t[Print Level] is 0 for no print/speed compare only, 1 for Nth number only, 2 for print all numbers, defaults to 0.\n");
}

/**
 * Fibonacci Manager, calls the appropriate function based on the type.
 * @param n number of terms to print.
 * @param type 0 for iterative, 1 for recursive, 2 for dynamic programming.
 * @param print 0 for no print/speed compare only, 1 for Nth number only, 2 for print all numbers.
 * @return the Nth fibonacci number.
 */
int fibonacci_manager(int n, int type, int print) {
    int value = 0;
    if (type == 2) {
        value = fibonacci_dp_controller(n, print);
    }
    else if (type == 1) {
        value = fibonacci_recursive_controller(n, print);
    }
    else {
        value = fibonacci_iterative(n, print);
    }
    return value;
}

/**
 * Iterative Fibonacci function.
 * @param n number of terms to print.
 * @param print 0 for no print/speed compare only, 1 for Nth number only, 2 for print all numbers.
 * @return the Nth fibonacci number.
 */
int fibonacci_iterative(int n, int print) {
    int a = 0, b = 1;
    int c = a + b; // Initialize c with the initial value of a + b

    // Special case for n = 0
    if (print > 1) {
        if (n >= 1) {
            printf("%d", a);
        }
    }

    // Print the series up to the given number of terms
    for (int i = 2; i <= n; i++) {
        // print last digit only
        if (print == 1) {
            if (i == n) {
                printf("Fibonacci value at %i = %d\n", i, c);
            }
        }
        // print all values
        if (print > 1) {
            if (i == n) {
                printf(", %d\n", c);
            }
            else {
                printf(", %d", c);
            }
        }
        a = b;
        b = c;
        c = a + b;
    }
    return c;
}

/**
 * Recursive Fibonacci function.
 * @param n number of terms to print.
 * @return the Nth fibonacci number.
 */
int fibonacci_recursive(int n) {
    if (n == 0) {
        return 0;
    }
    else if (n == 1) {
        return 1;
    }
    else {
        int fib = fibonacci_recursive(n-1) + fibonacci_recursive(n-2);
        return fib;
    }
}

/**
 * Recursive Fibonacci controller function.
 * @param n number of terms to print.
 * @param print 0 for no print/speed compare only, 1 for Nth number only, 2 for print all numbers.
 * @return the Nth fibonacci number.
 */
int fibonacci_recursive_controller(int n, int print) {
    int value = fibonacci_recursive(n);
    if (print > 0) {
        printf("Fibonacci Value at %i = %i \n", n, value);
    }
    return value;
}

/**
 * Dynamic Programming Fibonacci function.
 * @param n number of terms to print.
 * @param value_table the table of values.
 * @return the Nth fibonacci number.
 */
int fibonacci_dp(int n, int* value_table) {
    if (n == 0) {
        return 0;
    }
    else if (n == 1) {
        return 1;
    }
        // if default value, calculate the number
    else if (value_table[n] == -1) {
        value_table[n] = fibonacci_dp(n-1, value_table) + fibonacci_dp(n-2, value_table);
        return value_table[n];
    }
        // if it's not -1, return the value at position
    else if (value_table[n] != -1) {
        return value_table[n];
    }
    else {
        return -1;
    }
}

/**
 * Dynamic Programming controller function.
 * @param n number of terms to print.
 * @param print 0 for no print/speed compare only, 1 for Nth number only, 2 for print all numbers.
 * @return the Nth fibonacci number.
 */
int fibonacci_dp_controller(int n, int print) {
    int value;
    int* value_table = (int*) malloc(sizeof(int) * (n + 1));  // Allocate n + 1 elements

    for (int i = 0; i <= n; i++) {  // Iterate up to n (inclusive)
        value_table[i] = -1;
    }
    int a = 0;
    value = fibonacci_dp(n, value_table);
    if (print == 1) {
        printf("Fibonacci Value at %i = %i \n", n, value);
    }
    else if (print == 2) {
        for (int i = 0; i <= n; i++) {  // Iterate up to n (inclusive)
            if (value_table[i] != -1) {
                printf("%i\n", value_table[i]);  // Add a newline character '\n' after each print
            }
        }
    }
    free(value_table);  // Free the allocated memory
    return value;
}

