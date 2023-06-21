# Few Terminoloioes to know
### <ins> Stable Sorting Algorithm:
A sorting algorithm is said to be stable if two objects with equal keys appear in the same order in sorted output as they appear in the input array to be sorted.

### <ins>In-place Sorting Algorithm:
An in-place sorting algorithm uses constant extra space for producing the output (modifies the given array only). It sorts the list only by modifying the order of the elements within the list.

### <ins> Out-place Sorting Algorithm:
An out-place sorting algorithm uses extra space for producing the output (not modifies the given array). It sorts the list by creating a new list containing the elements of the original list.

### Internal Sorting Algorithm:
An internal sorting algorithm is any algorithm where all data to be sorted is placed in memory or RAM.

### External Sorting Algorithm:
An external sorting algorithm is any algorithm where data that needs to be sorted does not fit into memory and instead they reside in external storage devices such as magnetic disks, etc.

### Recursive Sorting Algorithm:
A recursive sorting algorithm is one that calls itself with a smaller version of the input and then does some processing to combine the smaller version with the larger input. 

### Non-Recursive Sorting Algorithm:
 non-recursive sorting algorithm is one that does not call itself with a smaller version of the input.

### Online Sorting Algorithm:
An online algorithm is one that can process its input piece-by-piece in a serial fashion, i.e., in the order that the input is fed to the algorithm, without having the entire input available from the start.

<div style="page-break-after: always; visibility: hidden">
\pagebreak
</div>

# Bubble Sort

<img src = "https://miro.medium.com/v2/resize:fit:802/1*0aPxBKrbMTVbF0UCI6gxZg.gif">

> Bubble Sort is a simple sorting algorithm that repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. This process is repeated until the list is sorted.

## <span style="color:yellowgreen;"> Key Characteristics </span>
- In-place sorting algorithm
- Stable sorting algorithm
- Simple and easy to understand

## <span style="color:yellowgreen;"> Time Complexity: </span> O(n^2)

	| Big O   | BestCase | Average | WorstCase |
	|---------|----------|---------|-----------|
	| BubbleS | O(n)     | O(n^2)  | O(n^2)    |


## <span style="color:yellowgreen;"> Space Complexity: </span>O(1) 
(In-place algorithm)

## <span style="color:yellowgreen;"> Advantages </span>
- Simple and easy to implement
- Works well for small input sizes or partially sorted lists
- Requires only a constant amount of additional memory

## <span style="color:yellowgreen;"> Disadvantages </span>
- Inefficient for large or nearly sorted lists
- Quadratic time complexity makes it slow for large input sizes
- Not suitable for real-time or performance-critical applications

## <span style="color:yellowgreen;"> Use Cases </span>
- Sorting small input sizes
- Teaching and learning algorithms
- When simplicity is more important than efficiency

## <span style="color:yellowgreen;"> Example code </span>
```
#include <stdio.h>
void bubbleSort(int arr[], int n) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

int main() {
    int arr[] = {64, 34, 25, 12, 22, 11, 90};
    int n = sizeof(arr) / sizeof(arr[0]);
    bubbleSort(arr, n);
    printf("Sorted array: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    return 0;
}
```

## <span style="color:yellowgreen;"> Notes and Observations </span>
Compare two items and keep swapping the smaller one towards the left.

The smallest item ends up 'Bubbling' towards the left and the largest one bubbles to the right with every swap.

<br>

# <span style="color:turquoise;"> Insertion Sort </span>
<img src = "https://miro.medium.com/v2/resize:fit:1400/1*5WXRN62ddiM_Gcf4GDdCZg.gif">

> Insertion Sort is a simple sorting algorithm that builds the final sorted array one item at a time. It takes an element from the unsorted part of the array and inserts it into its correct position within the sorted part of the array. This process is repeated until the entire array is sorted.

### <span style="color:turquoise;"> Key Characteristics </span>
- In-place sorting algorithm
- Stable sorting algorithm
- Efficient for small data sets or partially sorted lists
- Performs well on nearly sorted arrays

### <span style="color:turquoise;"> Time Complexity: </span> O(n^2)

    | Big O   | BestCase | Average | WorstCase |
    |---------|----------|---------|-----------|
    | InsertS | O(n)     | O(n^2)  | O(n^2)    |

### <span style="color:turquoise;"> Space Complexity: </span>O(1) 
(In-place algorithm)

### <span style="color:turquoise;"> Advantages </span>
- Simple and easy to implement
- Efficient for small input sizes or partially sorted lists
- Adaptive algorithm (performs better on nearly sorted arrays)
- Requires only a constant amount of additional memory

### <span style="color:turquoise;"> Disadvantages </span>
- Inefficient for large input sizes
- Quadratic time complexity makes it slow for large or reverse-sorted arrays
- Not suitable for real-time or performance-critical applications

### <span style="color:turquoise;"> Use Cases </span>
- Sorting small input sizes
- When simplicity is more important than efficiency
- When the input array is almost sorted

### <span style="color:turquoise;"> Example code </span>
``` C
```

### <span style="color:turquoise;"> Notes and Observations </span>
Insertion sort is a simple sorting algorithm that works similar to the way you sort playing cards in your hands. The array is virtually split into a sorted and an unsorted part. Values from the unsorted part are picked and placed at the correct position in the sorted part.

Insertion Sort is called so because it involves inserting elements into their correct position within a sorted portion of the list. The algorithm iterates over the elements of the unsorted part of the list and inserts each element into its proper place in the sorted part by shifting larger elements to the right.
