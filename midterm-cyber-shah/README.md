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

1. **_Iterative Approach_**
The iterative approach involves iterating over the values of the series until reaching the desired nth number. It is the most efficient and intuitive method for calculating the Fibonacci series. The time complexity of the iterative approach is O(n), meaning the time required to calculate the nth Fibonacci number grows linearly with the input value. Additionally, the iterative approach uses O(1) space complexity, as it only requires storing the last two numbers to compute the next number.

2. **_Recursive Approach_**
The recursive approach calls the Fibonacci function recursively until reaching the desired nth number. While conceptually simple, the recursive approach is the least efficient method for computing the Fibonacci series. Its time complexity is exponential, with a growth rate of O(2^n). This is because each recursive call requires calculating the previous two numbers, leading to redundant calculations. The space complexity is O(n) as the function calls are stored in the stack.

3. _**_Dynamic Programming Approach_**_
The dynamic programming approach optimizes the efficiency of calculating the Fibonacci series by using a technique known as memoization. In this approach, the values of the Fibonacci series are stored in an array, eliminating the need for redundant calculations. By reusing the precomputed values, the dynamic programming approach achieves a time complexity of O(n), making it significantly faster than the recursive approach. The space complexity remains at O(n) since the array is used to store the previous numbers.

By analyzing the time and space complexities of the three approaches, it becomes evident that the iterative approach is the most efficient method for calculating the Fibonacci series. It provides the fastest execution time and requires the least amount of memory. The recursive approach is the slowest and utilizes the most memory due to redundant calculations. The dynamic programming approach offers a balance between efficiency and memory usage by leveraging precomputed values.

In the following sections of this report, we will present empirical data, compare different programming languages for implementing these algorithms, and discuss the results and insights gained from the analysis.


## Empirical Data & Discussion 


## Language Analysis


### Language 1: C



### Language 2: UPDATE



### Comparison and Discussion Between Experiences


## Conclusions / Reflection


