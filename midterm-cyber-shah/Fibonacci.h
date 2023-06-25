#include <stdio.h>
#include "my_dll.h"

// declarations
int fibonacci_dp(int n, int print);
int fibonacci_recursive(int n, int print, dll_t* printer);
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
    return 0;
}

int fibonacci_recursive(int n, int print, dll_t* printer) {
    if (n == 0) {
        return 0;
    }
    else if (n == 1) {
        return 1;
    }
    else {
        int fib = fibonacci_recursive(n-1, print, printer) + fibonacci_recursive(n-2, print, printer);
        if (print) {
            dll_push_back(printer,fib);
        }
        return fib;
    }
}

int fibonacci_recursive_controller(int n, int print) {
    if (print) {
        dll_t* printer = create_dll();
    }
    dll_t* printer = create_dll();
    int value = fibonacci_recursive(n, print,printer);
    for (int i = 0; i < dll_size(printer); i++) {
        printf("%i ", dll_get(printer, i));
    }
    return value;
}

int fibonacci_dp(int n, int print) {
    return 0;
}

