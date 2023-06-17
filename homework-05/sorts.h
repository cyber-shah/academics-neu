#ifndef SORTS_H
#define SORTS_H

#include <stdlib.h>
#include "sort_helper.h"

/*** code for selection sort ****/

// Returns the minimum integer from a range in an array
// Input: array - An array of integers
//        start - Where to start looking in an array
//        stop - End of where to search. Typically the 'size' of the array.
// Output: The index in an array of the minimum value between a range [start,stop]
int findMinimum(int *array, int start, int stop)
{
    if (start >= stop) {
        return -1; // Invalid index range
    }
    int min_index = start;
    int min_value = array[start];
    for (int i = start + 1; i < stop; i++) {
        if (array[i] < min_value) {
            min_value = array[i];
            min_index = i;
        }
    }
    return min_index;
}


// =============== Sort Function ===============
// Provided below is a sort function. I have also
// provided a template for how to document functions
// to help organize your code.
// Name: sort
// Input(s):
//  - 'array' is a pointer to an integer address.
//     This is the start of some 'contiguous block of memory' that we will sort.
//  - 'size' tells us how big the array of data is we are sorting.
//  -  'print' tells it to print out after each interation
// Output: No value is returned, but 'array' should be modified to store a sorted array of numbers.
void selectionSortIntegers(int *array, unsigned int size, int print) {
    int i, min_index;
    // start the loop to move boundary of unsorted array
    for (i = 0; i < size; i++) {
        // find the min element in the unsorted array using
        // findMinimum function
        min_index = findMinimum(array, i, size);

        // swap the found minimum element with the current one
        if (min_index != i) {
            swap(&array[min_index], &array[i]);
        }

        // print out the array
        if (print) {
            for (unsigned int j = 0; j < size; j++) {
                printf("%d ", array[j]);
            }
            printf("\n");
        }
    }
}



/***  Code for Insertion Sort ***/

// =============== Sort Function ===============
// Provided below is a sort function. I have also
// provided a template for how to document functions
// to help organize your code.
// Name: sort
// Input(s):
//  - 'array' is a pointer to an integer address.
//     This is the start of some 'contiguous block of memory' that we will sort.
//  - 'size' tells us how big the array of data is we are sorting.
//  - 'print' tells it to print out after each iteration 
// Output: No value is returned, but 'array' should be modified to store a sorted array of numbers.
void insertionSortIntegers(int *array, unsigned int size, int print)
{
    for (int i = 1; i < size; i++) {
        int key = array[i];
        int j = i - 1;
        // Move elements greater than the key to one position ahead
        while (j >= 0 && array[j] > key) {
            array[j + 1] = array[j];
            j--;
        }
        array[j + 1] = key;
        if (print) {
            // Print array after each iteration
            for (int k = 0; k < size; k++) {
                printf("%d ", array[k]);
            }
            printf("\n");
        }
    } 
}

/** Code for Bubble Sort (from Lab -if not compiling, comment out the internals, but leave the function definition) ***/

// =============== Sort Function ===============
// Name: bubblesort
// Desc: O(n^2) comparison sort
// param(1): 'array' is a pointer to an integer address.
//           This is the start of some 'contiguous block of memory'
//           that we will sort.
// param(2)  'size' tells us how big the array of
//           data is we are sorting.
// param(3) 'print' tells it to print out after each iteration.
// Output:   No value is returned, but 'array' should
//           be modified to store a sorted array of size.
void bubbleSortIntegers(int *array, unsigned int size, int print)
{
    /* pseudo code --------------
     * 1. create i pointer i
     * 2. create i pointer j
     * 3. start with i at 0 and j at 1
     * 4. compare them
     *      4.1 swap them if needed
     * 5. move pointers by 1
     * 6. go back to 4.
     * -----------------------------
    */
    for (int i = 0; i < size - 1; i++) {
        for (int j = 0; j < size - 1; j++) {
            if (array[j] > array[j+1]) {
                swap(&array[j], &array[j+1]);
            }
        }
        // 'print' tells it to print out after each iteration.
        if (print) {
            printf("Iteration number %u: ", i + 1);
            for (unsigned int p = 0; p < size; p++) {
                printf("%d ", array[p]);
            }
            printf("\n");
        }
    }
}

// ** You will work on merge sort during the lab on Module 06 ** //

// Merges two subarrays of arr[].
// First subarray is arr[l..m]
// Second subarray is arr[m+1..r]
void merge(int arr[], int temp[], int l, int m, int r)
{
    if (arr == NULL || temp == NULL) {
        exit(1);
    }
    if (l > m || m >= r) {
        return;
    }
    // Starting index of the first subarray
    int left_pointer = l;
    // Starting index of the second subarray
    int right_pointer = m + 1;
    // Starting index of the merged subarray
    int merged_pointer = l;

    // Merge the two subarrays into the temporary array in sorted order
    while (left_pointer <= m && right_pointer <= r) {
        if (arr[left_pointer] <= arr[right_pointer]) {
            temp[merged_pointer] = arr[left_pointer];
            left_pointer++;
        }
        else {
            temp[merged_pointer] = arr[right_pointer];
            right_pointer++;
        }
        merged_pointer++;
    }

    // Copy the remaining elements from the first subarray, if any
    while (left_pointer <= m) {
        temp[merged_pointer] = arr[left_pointer];
        left_pointer++;
        merged_pointer++;
    }

    // Copy the remaining elements from the second subarray, if any
    while (right_pointer <= r) {
        temp[merged_pointer] = arr[right_pointer];
        right_pointer++;
        merged_pointer++;
    }

    // Copy the sorted elements from the temporary array back to the original array
    for (int x = l; x <= r; x++) {
        arr[x] = temp[x];
    }
}

// To be built during week 6 lab
// Name: mergeSort
// Input(s):
//          (1) 'arr' is a pointer to an integer address.
//              This is the start of some 'contiguous block of memory' that we will sort.
//          (2) 'temp' is a pointer to an integer address.
//          	This helps temporarily store the sorted subarray.
//          (3) 'l' and 'r' are integers, which are the first index and the last index of 'arr' respectively.
// Output: No value is returned, but 'array' should be modified to store a sorted array of numbers.
void merge_sort(int arr[], int temp[], int l, int r)
{
    if (l < r) {
        // Calculate the middle index
        int m = (l + r) / 2;
        // Recursively sort the left subarray
        merge_sort(arr, temp, l, m);
        // Recursively sort the right subarray
        merge_sort(arr, temp, m + 1, r);

        // Merge the two sorted subarray
        merge(arr, temp, l, m, r);
    }   
}

// lab build, merge sort

void mergeSortIntegers(int *array, unsigned int size, int print)
{ // print is ignored for this one
    if (array == NULL)
    {
        exit(1);
    }
    if (size <= 1)
        return;

    int *temp = (int *)malloc(sizeof(int) * size);
    merge_sort(array, temp, 0, size - 1);
    free(temp);
}

// provided code 

// =============== Helper Functions ===============
// Name:    compare
// Desc:    A compare function which returns
//          a value (positive,negative, or 0)
//          to show if the result is >,<, or =.
//
// Input:   a and b here are generic types,
//          that is why they are 'void'
int compare(const void *a, const void *b)
{
    return (*(int *)a - *(int *)b);
}

void quickSortIntegers(int* array, unsigned int size, int print) 
{   // print is ignored as qsort doesn't use it
    qsort(array, size, sizeof(int), compare);
}

#endif