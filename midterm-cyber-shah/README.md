# Midterm p1: Report on Analysis of Fibonacci  Series
* **Author**: Pranchal Shah
* **GitHub Repo**: [linke to github repo with this report]
* **Semester**: Summer 2023
* **Languages Used**: c, Java

## Overview

The Fibonacci series is a sequence of numbers that appears in various natural phenomena and is often regarded as a fascinating mathematical pattern. It serves as the foundation for the golden ratio, which can be observed in numerous aspects of the natural world. In this report, we will delve into the analysis of the Fibonacci series, exploring different algorithms to calculate the series efficiently and comparing their performance.

Mathematically, the Fibonacci series is defined as a sequence in which each number, known as a Fibonacci number, is the sum of the two preceding numbers. 
```F(n) = F(n-1) + F(n-2)```
The simplest form of the series starts with 1 and 1, followed by the sum of the last two numbers to generate the next number in the sequence:
```1, 1, 2, 3, 5, 8, 13, and so on.```

In this analysis, we will examine three different approaches to compute the Fibonacci series: iterative, recursive, and dynamic programming.

| Approach | Time Complexity | Space Complexity |
| --- |----------------| --- |
| Iterative | O(n)           | O(1) |
| Recursive | O(2^n)         | O(n) |
| Dynamic Programming | O(n)           | O(n) |

1. **_Iterative Approach_**: The iterative approach involves iterating over the values of the Fibonacci series until reaching the desired nth number. It starts with the first two numbers (1 and 1) and calculates the subsequent numbers by summing the last two numbers in the series.
- Time Complexity: The time complexity of the iterative approach is O(n). This is because for each number in the series from 3 to n, we perform a constant-time operation (addition) to calculate the next number. The number of iterations directly corresponds to the input value of n. 
- Space Complexity: The space complexity of the iterative approach is O(1) or constant. It only requires storing the last two numbers of the series to compute the next number. Regardless of the input value of n, the amount of memory used remains constant.

2. **_Recursive Approach_**: The recursive approach involves calling the Fibonacci function recursively until reaching the desired nth number. The function calculates the Fibonacci number by recursively calling itself for the previous two numbers and summing them.
- Time Complexity: The time complexity of the recursive approach is O(2^n). Each recursive call branches into two additional recursive calls, resulting in exponential growth. The number of recursive calls increases exponentially with the input value of n, leading to a significant increase in the number of calculations required.
- Space Complexity: The space complexity of the recursive approach is O(n). As the recursive calls stack up, memory is allocated to store each function call. Since the maximum depth of the recursive call stack is determined by the input value of n, the space complexity is directly proportional to n.

3. _**_Dynamic Programming Approach_**_: The dynamic programming approach optimizes the efficiency of calculating the Fibonacci series by using memoization. It stores the Fibonacci numbers in an array and reuses the precomputed values to avoid redundant calculations.
- Time Complexity: The time complexity of the dynamic programming approach is O(n). By storing the calculated Fibonacci numbers in an array, we eliminate the need for redundant calculations. Each number in the series is computed once, resulting in linear time complexity with respect to n.
- Space Complexity: The space complexity of the dynamic programming approach is O(n). We need to store the Fibonacci numbers in an array to reuse them in subsequent calculations. The size of the array is directly proportional to the input value of n, hence the linear space complexity.


By analyzing the time and space complexities of the three approaches, it becomes evident that the iterative approach is the most efficient method for calculating the Fibonacci series. It provides the fastest execution time and requires the least amount of memory. The recursive approach is the slowest and utilizes the most memory due to redundant calculations. The dynamic programming approach offers a balance between efficiency and memory usage by leveraging precomputed values.

In the following sections of this report, we will present empirical data, compare different programming languages for implementing these algorithms, and discuss the results and insights gained from the analysis.


## Empirical Data & Discussion 


## Language Analysis


### Language 1: C



### Language 2: UPDATE



### Comparison and Discussion Between Experiences


## Conclusions / Reflection


