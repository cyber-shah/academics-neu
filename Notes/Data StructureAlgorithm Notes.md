# Data Structure/Algorithm Notes

# <span style="color:yellowgreen;"> Bubble Sort </span>
![Alt Text](https://i1.faceprep.in/fp/articles/img/71784_1580551129.png)

## <span style="color:yellowgreen;"> Description </span>
Bubble Sort is a simple sorting algorithm that repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. This process is repeated until the list is sorted.

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

<img src="https://upload.wikimedia.org/wikipedia/commons/9/94/Selection-Sort-Animation.gif" style="float:right;padding-left:20px;" />
