# Midterm p1: Report on Analysis of Fibonacci  Series

* **Author**: Pranchal Shah
* **GitHub Repo**: [linke to github repo with this report]
* **Semester**: Summer 2023
* **Languages Used**: c, Java

## Overview

The Fibonacci series is a sequence of numbers that appears in various natural phenomena and is often regarded as a fascinating mathematical pattern. It serves as the foundation for the golden ratio, which can be observed in numerous aspects of the natural world. In this report, we will delve into the analysis of the Fibonacci series, exploring different algorithms to calculate the series efficiently and comparing their performance.

### The Significance of the Fibonacci Series and the Golden Ratio

The Fibonacci series serves as the foundation for the golden ratio, a mathematical concept that holds immense significance in nature, art, and design. The golden ratio can be observed in the spiral patterns of seashells, the arrangement of leaves on plants, the growth patterns of trees, and even in the proportions of human faces. It is a mathematical harmony that resonates throughout the universe, creating aesthetically pleasing and visually balanced compositions.
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Fibonacci_Spiral.svg/1280px-Fibonacci_Spiral.svg.png">

Mathematically, the Fibonacci series is defined as a sequence in which each number, known as a Fibonacci number, is the sum of the two preceding numbers.
$$F(n) = F(n-1) + F(n-2)$$
The simplest form of the series starts with 1 and 1, followed by the sum of the last two numbers to generate the next number in the sequence:
```1, 1, 2, 3, 5, 8, 13, and so on.```

### Exploring Different Computation Approaches

In this analysis, we will examine three distinct approaches to compute the Fibonacci series: iterative, recursive, and dynamic programming. Each approach offers a unique perspective on solving the series efficiently, and we will compare their time and space complexities to determine the most effective method.

| Approach | Time Complexity | Space Complexity |
| --- |----------------| --- |
| Iterative | O(n)           | O(1) |
| Recursive | O(2^n)         | O(n) |
| Dynamic Programming | O(n)           | O(n) |

1. **_Iterative Approach_**: The iterative approach involves iterating over the values of the Fibonacci series until reaching the desired nth number. By adding the last two numbers in the series, the subsequent numbers are calculated. This approach offers simplicity and intuition, making it an appealing choice for computing the Fibonacci series efficiently.
   * Time Complexity: The time complexity of the iterative approach is O(n) since each number in the series from 3 to n requires a constant-time operation (addition) to calculate the next number. The number of iterations directly corresponds to the input value of n.
   * Space Complexity: The space complexity of the iterative approach is O(1) or constant. It only requires storing the last two numbers of the series to compute the next number. Regardless of the input value of n, the amount of memory used remains constant.

2. **_Recursive Approach_**: The recursive approach involves calling the Fibonacci function recursively until reaching the desired nth number. The function calculates the Fibonacci number by recursively calling itself for the previous two numbers and summing them. Although conceptually elegant, this approach has some drawbacks in terms of efficiency.
   * Time Complexity: The time complexity of the recursive approach is O(2^n). Each recursive call branches into two additional recursive calls, resulting in exponential growth. The number of recursive calls increases exponentially with the input value of n, leading to a significant increase in the number of calculations required.
   * Space Complexity: The space complexity of the recursive approach is O(n). As the recursive calls stack up, memory is allocated to store each function call. Since the maximum depth of the recursive call stack is determined by the input value of n, the space complexity is directly proportional to n.

3. **_Dynamic Programming Approach_**: The dynamic programming approach optimizes the efficiency of calculating the Fibonacci series by utilizing memoization. This approach stores the Fibonacci numbers in an array and reuses the precomputed values to avoid redundant calculations, resulting in improved performance.
   * Time Complexity: The time complexity of the dynamic programming approach is O(n). By storing the calculated Fibonacci numbers in an array, we eliminate the need for redundant calculations. Each number in the series is computed once, resulting in linear time complexity with respect to n.
   * Space Complexity: The space complexity of the dynamic programming approach is O(n). We need to store the Fibonacci numbers in an array to reuse them in subsequent calculations. The size of the array is directly proportional to the input value of n, hence the linear space complexity.

### Conclusion

In conclusion, the Fibonacci series and its computation approaches offer a fascinating journey into the realms of mathematics and nature. By analyzing the time and space complexities of the three approaches, it becomes evident that the iterative approach is the most efficient method for calculating the Fibonacci series. It provides the fastest execution time and requires the least amount of memory. The recursive approach, although conceptually appealing, suffers from exponential time complexity and increased memory usage. The dynamic programming approach offers a balance between efficiency and memory usage by leveraging precomputed values. Understanding the nuances and trade-offs of these approaches allows us to make informed decisions when dealing with Fibonacci series computations.

In the following sections of this report, we will present empirical data, compare different programming languages for implementing these algorithms, and discuss the results and insights gained from the analysis. Furthermore, we will explore practical applications of the Fibonacci series and its associated properties, shedding light on its significance in various domains.

## Empirical Data & Discussion

For the empirical analysis, the time limit for each algorithm was set to 30 seconds. For the implementations I used in this report the following chart represents the Big O value.

| Version |  Big O | Space Used | 
| :-- | :-- |  :-- |
| Iterative | $O(n)$ | $O(1)$ |
| Recursive | $O(2^n)$  | $O(n)$ |
| Dynamic Programming | $O(n)$ | $O(n)$ |


### Environmental Setup:

* **Environment**: The tests were conducted on a 2022 Alienware x14 laptop equipped with an Intel i7-11900H CPU running at 2.50 GHz and 32.0 GB of RAM. The operating system used was Fedora Linux, Workstation edition.

* **Testing Parameters**: Two sets of input values were used to test the algorithms. The first set ranged from 1 to 50, incrementing by 50 steps. The second set ranged from 90 million to two billion and seventy million, incrementing by 90 million at each step. These values were chosen to cover both smaller and larger input sizes, allowing for a comprehensive performance analysis.
  
### Performance Comparisons with Small N Values (< 50)

The recursive version of the C algorithm reached its time limit of 30 seconds when calculating the 48th Fibonacci number, hence all the timers presented here represent the time taken to calculate the 48th value.

* **Execution Times**: The table shows time taken by each algorithm to calculate 48th value.

| Algorithm        | Java      | C         |
|------------------|-----------|-----------|
| Iterative        | 0.03243     | 0.00142     |
| Recursive        | 15.77696     | 27.91239     |
| Dynamic Programming | 0.03299     | 0.00209     |

![AllC](EmpericalAnalysis/AllC.png)

* **Observations**:  The empirical analysis reveals that the iterative approach is the fastest, followed by the dynamic programming approach, and finally the recursive approach. The recursive approach is the slowest because it involves a large number of recursive calls, which are computationally expensive. The iterative approach is the fastest because it only requires a constant number of operations per iteration. The dynamic programming approach falls in between, as it also requires a constant number of operations per iteration but has the additional overhead of storing values in an array.

* **Insights**: The analysis of small N values confirms that both the iterative and dynamic programming approaches are more efficient for computing the Fibonacci sequence compared to the recursive approach. These findings lay the foundation for further investigation with larger values of N to assess the scalability and performance characteristics of the algorithms.

![NonRecursiveC](EmpericalAnalysis/NonRecursiveC.png)
![NonRecursiveJava](EmpericalAnalysis/NonRecursiveJava.png)

> For N values smaller than 50, both the iterative and dynamic programming approaches exhibit similar performance, running very close to each other.  
> > This raises the question for further investigation: what happens when the values become larger? How do these algorithms scale?


### Performance Comparisons with Large N Values (> 30 million)
Upon analyzing the results, it was observed that the recursive versions of both the Java and C algorithms reached their limits at around the 47th and 48th Fibonacci numbers, respectively, for smaller values of N. Thus, further investigation was conducted to assess the performance for extremely large values.

To further evaluate the performance of the algorithms, I conducted tests using larger values of N. Specifically, I focused on values greater than 90 million. The following table presents the execution times for calculating the 90 millionth value using different algorithms in Java and C:

* **Execution Times**: The table shows time taken by each algorithm to calculate 20 billion and 70 millionth value using iterative algorithm.

   | Algorithm        | Java      | C         |
   |------------------|-----------|-----------|
   | Iterative        | 9.39257     | 1.51927     |
   | Dynamic Programming | N/A    | N/A     |

   Unfortunately, limitations were encountered in Java when using the dynamic programming approach. Java has a maximum size limit for arrays, which prevented the calculation of the 20 billion and 70 millionth Fibonacci number using dynamic programming.

* **Execution Times**:  For dynamic programming in Java, the limit was reached at the 30 millionth Fibonacci number. This table represents the values at the 28 millionth Fibonacci number.

   | Algorithm        | Java      | C         |
   |------------------|-----------|-----------|
   | Dynamic Programming | 5.77818     | 1.41863    |
   | Iterative        | 1.51969     | 0.21527    

![NonRecursiveJava](EmpericalAnalysis/20MillionJava.png)

* **Observations** : The empirical analysis reveals that the dynamic programming approach, despite having the same theoretical time complexity as the iterative approach, is slower in practice. This is primarily due to the additional overhead of storing values in an array and accessing them. The recursive approach remains the slowest due to the large number of recursive calls. The iterative approach, with its constant number of operations per iteration, emerges as the most efficient.

   ![NonRecursiveJava](EmpericalAnalysis/PercentageDifference.png)

* **Insights** : The findings demonstrate that time complexity alone is not always the best metric for measuring efficiency. In this case, the iterative approach proves to be approximately four times more efficient than the dynamic programming approach when dealing with large sets of numbers. This is because the dynamic programming approach incurs additional overhead in storing and accessing values in an array.
  
* **Scalability**: The iterative approach exhibits good scalability with larger input sizes, enabling efficient computation of Fibonacci numbers. On the other hand, the dynamic programming approach shows limitations in Java due to array size restrictions, preventing the calculation of extremely large Fibonacci numbers.

### Conclusion-

* The empirical analysis highlights that the iterative approach consistently outperforms the recursive and dynamic programming approaches in calculating Fibonacci numbers. The recursive approach demonstrates poor performance due to the high computational cost of recursive calls. The dynamic programming approach, despite its theoretical efficiency, incurs overhead due to array storage and access, making it slower than the iterative approach.


* The iterative approach offers the advantage of efficient computation with a constant number of operations per iteration, making it highly scalable. The dynamic programming approach provides similar scalability but at the expense of additional overhead due to array storage and access. The recursive approach proves to be the least efficient due to the large number of recursive calls. It is also noted that the choice of language can impact performance, as seen with Java's limitations in handling extremely large Fibonacci numbers.



> This leads to another question: what is the impact of the programming language on algorithm performance? While the analysis has focused on the impact of different algorithms on runtimes, exploring the specific differences between Java and C and their effects on performance would provide further insights.
## Language Analysis

In the previous analysis, we observed that the choice of programming language can impact the performance of Fibonacci algorithms. Specifically, we encountered limitations in Java when using the dynamic programming approach due to array size restrictions. To further investigate the impact of language on runtimes, we can compare the performance of the algorithms implemented in different languages.

Graph

From the above table, we can see that the C implementation outperforms the Java implementation in terms of execution time for the iterative algorithm. This difference can be attributed to several factors, including language-specific optimizations, memory management, and lower-level access in C.


### Language 1: C

### Language 2: Java

### Comparison and Discussion Between Experiences

Surprisingly C recursive maxed out before java which is very surprising.


## Conclusions / Reflection
