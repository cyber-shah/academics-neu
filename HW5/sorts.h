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
    return 0; // modify to return the index of the min value
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
void selectionSortIntegers(int *array, unsigned int size, int print)
{
    // todo: implement selection sort
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
    // TODO: Implement insertion sort
 

}

/** Code for Bubble Sort (from Lab -if not compiling, comment out the internals, but leave the function definition) ***/

/*// =============== Sort Function ===============
// Name: bubblesort
// Desc: O(n^2) comparison sort
// param(1): 'array' is a pointer to an integer address.
//           This is the start of some 'contiguous block of memory'
//           that we will sort.
// param(2)  'size' tells us how big the array of
//           data is we are sorting.
// param(3) 'print' tells it to print out after each iteration.
// Output:   No value is returned, but 'array' should
//           be modified to store a sorted array of size.*/
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
            if (array[j] < array[j+1]) {
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

/*// ** You will work on merge sort during the lab on Module 06 ** //

// Merges two subarrays of arr[].
// First subarray is arr[l..m]
// Second subarray is arr[m+1..size]*/
void merge(int arr[], int temp[], int l, int m, int size)
{
    if (arr == NULL || temp == NULL) {
        exit(1);
    }
    if (l > m || m + 1 > size)
        return;

    // starting index of LEFT
    int left = l;
    // starting index of RIGHT
    int right = m + 1;
    // starting index of MERGED
    int merged = l;

    // while left pointer <= merged pointer
    // and right pointer <= size
    while (left <= merged && right <= size) {
        // if left is less than right
        // copy left data to merged
        if (arr[left] <= arr[right]) {
            temp[merged] = arr[left];
            left ++;
        }
        // else copy data from right into merged
        else {
            temp[merged] = arr[right];
            right ++;
        }
        // move the merged pointer
        merged ++;
    }

    // copy all elements from left if it is remaining
    while (left <= merged) {
        temp[merged] = arr[left];
        left ++;
        merged ++;
    }

    // copy all elements from right if that is remaining
    while (right <= size) {
        temp[merged] = arr[right];
        right ++;
        merged ++;
    }

    // copy elements from temporary array to original array
    for (int x = 0; x < size; x++) {
        arr[x] = temp[x];
    }
}

/*// To be built during week 6 lab
// Name: mergeSort
// Input(s):
//          (1) 'arr' is a pointer to an integer address.
//              This is the start of some 'contiguous block of memory' that we will sort.
//          (2) 'temp' is a pointer to an integer address.
//          	This helps temporarily store the sorted subarray.
//          (3) 'l' and 'r' are integers, which are the first index and the last index of 'arr' respectively.
// Output: No value is returned, but 'array' should be modified to store a sorted array of numbers.*/
void merge_sort(int arr[], int temp[], int l, int r) {
    if (l < r)
    {
        // Calculate the middle index
        int m = l + (r - l) / 2;
        // Recursively sort the left subarray
        merge_sort(arr, temp, l, m);
        // Recursively sort the right subarray
        merge_sort(arr, temp, m + 1, r);

        // Merge the two sorted subarrays
        merge(arr, temp, l, m, r);
    }
}

// lab build, merge sort

void mergeSortIntegers(int *array, unsigned int size, int print)
{ // print is ignored for this one
    if (array == NULL) {
        exit(1);
    }
    // already sorted
    if (size <= 1) {
        return;
    }

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