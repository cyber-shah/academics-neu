#include <stdio.h>

// declarations
int fibonacci_dp(int n, int print);
int fibonacci_recursive(int n);
int fibonacci_recursive_controller(int n, int print);
int fibonacci_iterative(int n, int print);
void fibonacci_printer(int print);


void help() {
    printf("./Fibonacci.out [N] [Type] [Print Level]\n");
    printf("\t[N] = Nth Fibonacci number, required.\n");
    printf("\t[Type] is either 2 for dynamic programming version, 1 for recursive version, 0 for iterative version, defaults to 0.\n");
    printf("\t[Print Level] is 0 for no print/speed compare only, 1 for Nth number only, 2 for print all numbers, defaults to 0.\n");
}

int fibonacci_manager(int n, int type, int print) {
    int value = 0;
    if (type == 2) {
        value = fibonacci_dp(n, print);
    }
    else if (type == 1) {
        value = fibonacci_recursive_controller(n, print);
    }
    else {
        value = fibonacci_iterative(n, print);
    }
    return value;
    fibonacci_printer(print);
}

void fibonacci_printer(int print) {

}

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

int fibonacci_recursive_controller(int n, int print) {
    int value = fibonacci_recursive(n);
    if (print > 0) {
        printf("Fibonacci Value at %i = %i \n", n, value);
    }
    return value;
}

int fibonacci_dp(int n, int print) {
    return 0;
}

