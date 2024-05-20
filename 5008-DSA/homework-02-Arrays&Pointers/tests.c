/**
 * Student Name: Pranchal Shah
 * Semester: Summer 2023
 * 
 * 
 * This file is to test the functions in cpractice.h. You are to write at least *TWO* (maybe more) tests for every function. 
 * Some sample ones have been provided for you.
*/

#include <stdio.h>  // basic input and output
#include <stdlib.h> // standard library

#include "cpractice.h" // header file for cpractice.c


// this may help with some tests, as the array numbers match their index
int* create_simple_array(int size) {
    int* arr = malloc(sizeof(int) * size);
    for (int i = 0; i < size; i++) {
        arr[i] = i;
    }
    return arr;
}

/**
 * --------------------------------------------------------------------------
 * Tests a basic swap
*/
int test_swap_one(void) {
    printf("1. Swapping two ints\n");
    printf("    1.1 Test: Basic Test\n");
    int a = 5;
    int b = 10;
    swap(&a, &b);
    if (a == 10 && b == 5) {
        return 1;
    }
    return 0;
}

int test_swap_two(void) {
    printf("    1.2 Test: Non-positive values\n");
    int a = -23;
    int b = 0;
    swap(&a, &b);
    if (a == 0 && b == -23) {
        return 1;
    }
    return 0;
}

/**
 * --------------------------------------------------------------------------
 * Tests the create_array_of_ints_fib function
*/
int test_create_array_of_ints_fib_5(void) {
    printf("2. Create Array of ints in Fibonacci Series\n");
    printf("    2.1 Test: Size 5\n");
    int* arr = create_array_of_ints_fib(5);
    int expected[] = {1, 1, 2, 3, 5};
    for (int i = 0; i < 5; i++) {
        if (arr[i] != expected[i]) {
            free(arr);
            return 0;
        }
    }
    free(arr); // note expected does not need freed.. why is that?
    // Bc it is not a heap, its a local variable on the stack, it get's freed up automatically
    // once this function is done executing
    // everything matched
    return 1;
}

int test_create_array_of_ints_fib_2(void) {
    printf("    2.2 Test: Size 2\n");
    int* arr = create_array_of_ints_fib(2);
    int expected[] = {1, 1};
    for (int i = 0; i < 2; i++) {
        if (arr[i] != expected[i]) {
            free(arr);
            return 0;
        }
    }
    free(arr);
    // everything matched
    return 1;
}

int test_create_array_of_ints_fib_0(void) {
    printf("    2.3 Test: Size 0\n");
    int* arr = create_array_of_ints_fib(0);
    if (arr == NULL) {
        return 1;
    }
    else {
        return 0;
    }
}

/**
 * --------------------------------------------------------------------------
 * Tests the reverse_array function
*/
int test_reverse_array(void) {
    printf("3. Reverse Array\n");
    printf("    3.1 Test: Basic Test\n");
    int arr[] = {1, 2, 3, 4, 5};
    reverse_array(arr, 5);
    int expected[] = {5, 4, 3, 2, 1};
    for (int i = 0; i < 5; i++) {
        if (arr[i] != expected[i]) {
            return 0;
        }
    }

    // CHECK for zero sized arrays #######################

    // array of size 1:
    printf("    3.2 Test: Array size 1\n");
    int arr_2[] = {1};
    int expected_2[] = {1};
    reverse_array(arr_2, 0);
    for (int i = 0; i < 1; i++) {
        if (arr_2[i] != expected_2[i]) {
            return 0;
        }
    }
    // everything works fine
    return 1;
}

/**
 * --------------------------------------------------------------------------
 * Tests the double array function
 */
int test_double_array_1(void) {
    printf("4. Double Array\n");
    printf("    4.1 Test: Basic Test\n");

    int original_arr[] = {3, 8, 0, 1};
    int size = sizeof(original_arr) / sizeof(original_arr[0]);
    int expected_arr[] = {3, 8, 0, 1, 0, 0, 0, 0};
    // new name to array
    int* temp_pointer = original_arr;
    // pointer to the array
    int** pointer_to_array = &temp_pointer;
    // returns an array
    int* actual_output = (int *) double_array_size((int **) pointer_to_array, size);
    for (int i = 0; i < size*2; i++) {
        if (actual_output[i] != expected_arr[i]) {
            return 0;
        }
    }
    free(actual_output);
    return 1;
}

int test_double_array_2(void) {
        printf("    4.2 Test: Single Item Array\n");
        int original_arr[] = {0};
        int size = sizeof(original_arr) / sizeof(original_arr[0]);
        int expected_arr[] = {0,0};
        // new name to array
        int* temp_pointer = original_arr;
        // pointer to the array
        int** pointer_to_array = &temp_pointer;
        // returns an array
        int* actual_output = (int *) double_array_size((int **) pointer_to_array, size);
        for (int i = 0; i < 2; i++) {
            if (actual_output[i] != expected_arr[i]) {
                return 0;
            }
        }
        free(actual_output);
        return 1;
}

/**
 * --------------------------------------------------------------------------
 * Tests the copy_array function
 */
int test_copy_array (void) {
    printf("5. Copy Array\n");
        printf("    5.1 Test: Basic Test\n");
    int arr[] = {1,2,3,4,5};
    int expected_arr[] = {2,3,4};
    int new_size = 3-1;
    int* actual_output = copy_array_start_end_loop(arr, (sizeof(arr)/sizeof(int)), 1, 3, &new_size);
    for (int i = 0; i < new_size; i++) {
        if (actual_output[i] != expected_arr[i]) {
            return 0;
        }
    }
    printf("    Old array: ");
    print_array(arr, (sizeof(arr)/sizeof(int)));
    printf("    Actual Output: ");
    print_array(actual_output, new_size);

    free(actual_output);
    return 1;
}

int test_copy_array_2 (void) {
    printf("    5.2 Test: Wrapping Test\n");
    int arr[] = {1,2,3,4,5};
    int expected_arr[] = {4, 5, 1, 2};
    int new_size;
    int* actual_output = copy_array_start_end_loop(arr, (sizeof(arr)/sizeof(int)), 3, 1, &new_size);
    for (int i = 0; i < new_size; i++) {
        if (actual_output[i] != expected_arr[i]) {
            return 0;
        }
    }
    printf("    Old array: ");
    print_array(arr, (sizeof(arr)/sizeof(int)));
    printf("    Actual Output: ");
    print_array(actual_output, new_size);
    free(actual_output);
    return 1;
}

/**
 * --------------------------------------------------------------------------
 * Tests the Point struct
*/
int test_create_point(void) {
    printf("6. Create point\n");
    printf("    6.1 Test: Basic Test\n");
    // basic test
    Point* p1 = create_point(1,2);
    if ((*p1).x != 1 || (*p1).y != 2) {
        printf("test_create_point p1 failed\n");
        return 0;
    }
    return 1;
}

int test_create_point_2(void) {
    printf("    6.2 Test: Negative Test\n");
    // negative test
    Point* p2 = create_point (-1, -2);
    if (p2->x != -1 || p2->y != -2) {
        printf("test_create_point p2 failed\n");
        return 0;
    }
    return 1;
}


/**
 * --------------------------------------------------------------------------
 * Tests the Polygon struct
*/
int test_create_polygon(void) {
    printf("7. Create Polygon:\n");
    // basic test
    // create polygon of size 3
    Polygon* poly = create_polygon(3);

    // create points
    Point* p1 = create_point(1,2);
    Point* p2 = create_point(3,4);
    Point* p3 = create_point(5,6);
    Point* points[] = {p1, p2, p3};

    // add points to polygon
    // change the pointer to the array of points
    (*poly).points = points;

    printf("    7.1 Test: Size Test\n");
    if (poly->size != 3) {
        printf("test_create_polygon poly->size failed\n");
        return 0;
    }
    printf("    7.2 Test: X and Y values \n");
    if (poly->points[0]->x != 1 || poly->points[0]->y != 2) {
        printf("test_create_polygon poly->points[0] failed\n");
        return 0;
    }
    printf("    7.3 Test: X and Y values \n");
    if (poly->points[1]->x != 3 || poly->points[1]->y != 4) {
        printf("test_create_polygon poly->points[1] failed\n");
        return 0;
    }
    return 1;
}

/**
 * --------------------------------------------------------------------------
 * Tests the create rectangle function
 */
int test_create_rectangle (void) {
    Polygon* rect = create_rectangle(3,4);

    printf("9. Create Rectangle:\n");
    printf("    9.1 Test: Size Test\n");
    if (rect->size != 4) {
        printf("test_create_rectangle rect->size failed\n");
        return 0;
    }
    printf("    9.2 Test: X and Y values \n");
    if (rect->points[0]->x != 0 || rect->points[0]->y != 0) {
        printf("test_create_rectangle rect->points[0] failed\n");
        return 0;
    }
    printf("    9.3 Test: X and Y values \n");
    if (rect->points[1]->x != 3 || rect->points[1]->y != 0) {
        printf("test_create_rectangle rect->points[1] failed\n");
        return 0;
    }
    return 1;
}


/**
 * --------------------------------------------------------------------------
 * Tests the create triangle function
 */
int test_create_triangle (void) {
    Polygon* tri = create_triangle(3,4);

    printf("10. Create Triangle:\n");
    printf("    10.1 Test: Size Test\n");
    if (tri->size != 3) {
        printf("test_create_triangle tri->size failed\n");
        return 0;
    }
    printf("    10.2 Test: X and Y values \n");
    if (tri->points[0]->x != 0 || tri->points[0]->y != 0) {
        printf("test_create_triangle tri->points[0] failed\n");
        return 0;
    }
    printf("    10.3 Test: X and Y values \n");
    if (tri->points[2]->x != 3 || tri->points[2]->y != 4) {
        printf("test_create_triangle tri->points[2] failed\n");
        return 0;
    }
    return 1;
}

/**
 * --------------------------------------------------------------------------
 * Tests the calculate polygon function
 */
int test_calculate_area (void) {
    printf("12. Calculate Area:\n");
    printf("    12.1 Test: Rectangle Test\n");
    Polygon* rect = create_rectangle(4,4);
    double area = calculate_polygon_area( rect);
    if (area != 16) {
        printf("Rectangle area failed\n");
        return 0;
    }
    return 1;
}

int test_calculate_area_2 (void) {
    printf("    12.2 Test: Triangle Test\n");
    Polygon* tri = create_triangle(4,4);
    double area = calculate_polygon_area( tri);
    if (area != 8) {
        printf("Triangle area failed\n");
        return 0;
    }
    return 1;
}

// this is a list of all the unit tests
int (*unitTests[])(void) = {
        test_swap_one,
        test_swap_two,
        test_create_array_of_ints_fib_5,
        test_create_array_of_ints_fib_2,
        test_create_array_of_ints_fib_0,
        test_reverse_array,
        test_double_array_1,
        test_double_array_2,
        test_copy_array,
        test_copy_array_2,
        test_create_point,
        test_create_point_2,
        test_create_polygon,
        test_create_rectangle,
        test_create_triangle,
        test_calculate_area,
        test_calculate_area_2
        // add more test function names here
};

int main(int argc, char const *argv[])
{
    int numTests = sizeof(unitTests) / sizeof(unitTests[0]);
    int numPassed = 0;

    for (int i = 0; i < numTests; i++) {
        if (unitTests[i]()) {
            numPassed++;
        } else {
            printf("Test %d failed\n", i);
        }
    }

    printf("Passed %d out of %d tests\n", numPassed, numTests);
    return 0;
}
