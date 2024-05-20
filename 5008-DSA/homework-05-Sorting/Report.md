# Sort Analysis Data

## Results Table
Make sure to go out to at least 100,000 (more are welcome), and you have 10 different values (more welcome). You are welcome to go farther, but given 100,000 can take about 20 seconds using a selection sort on a fast desktop computer, and 200,000 took 77 seconds, you start having to wait much longer the more 0s you add. However, to build a clearer line, you will want more data points, and you will find merge and quick are able to handle higher numbers easier (but at a cost you will explore below). 

You are free to write a script to run the program and build your table (then copy that table built into the markdown). If you do that, please include the script into the repo.  Note: merge and quick sorts are going to be explored in the team activity for Module 06. You can start on it now, but welcome to wait.
```C
void printResultsTable()
{
    int data_size[] = {10000, 20000, 30000, 40000, 50000, 100000, 130000};
    int dataSizes = sizeof(data_size) / sizeof(data_size[0]);
    // Define the sort types
    const char *sortNames[] = {"Bubble", "Selection", "Insertion", "Merge", "Quick"};
    int numSorts = sizeof(sorts) / sizeof(sorts[0]);

    // Print the table header
    printf("Results Table\n");
    printf("%-8s", "N");
    for (int i = 0; i < numSorts; i++) {
        printf("%-12s\t", sortNames[i]);
    }
    printf("\n");

    // Iterate over the sizes
    for (int i = 0; i < dataSizes; i++) {
        int size = data_size[i];
        printf("%d\t", size);

        // Iterate over the sort types
        for (int j = 0; j < numSorts; j++) {
            // Generate a random array
            int *random = get_random_array(size);
            // Perform the sort and measure the time
            double time_taken = sort_and_time(random, size, j, 0);

            // Free the random array
            free(random);

            // Print the time taken
            printf("%.6f\t", time_taken);
        }

        printf("\n");
    }
}
```
### Table [^note]
| N      | Bubble     | Selection  | Insertion  | Merge      | Quick      |
|--------|------------|------------|------------|------------|------------|
| 10000  | 0.163486   | 0.026365   | 0.024361   | 0.000936   | 0.000615   |
| 20000  | 0.719355   | 0.104635   | 0.094830   | 0.001943   | 0.001335   |
| 30000  | 1.765385   | 0.237790   | 0.218204   | 0.003046   | 0.002141   |
| 40000  | 3.237114   | 0.420047   | 0.384890   | 0.004261   | 0.002959   |
| 50000  | 5.216846   | 0.662328   | 0.610120   | 0.005303   | 0.003625   |
| 100000 | 21.626711  | 2.711665   | 2.433027   | 0.011683   | 0.007885   |
| 130000 | 37.310214  | 4.646400   | 4.175014   | 0.015422   | 0.010344   |


## BigO Analysis  / Questions

### 1. Build a line chart
Build a line chart using your favorite program. Your X axis will be N increasing, and your Y access will be the numbers for each type of sort. This will create something similar to the graph in the instructions, though it won't be as smooth. Due to speed differences, you may need to break up the $O(\log n)$ and $O(n^2)$ into different charts.

Include the image in your markdown. As a reminder, you save the image in your repo, and use [image markdown].

![Bubble, Selection, Insertion, Merge and Quick.png](Bubble%2C%20Selection%2C%20Insertion%2C%20Merge%20and%20Quick.png)

### 2. Convinced?
Given the direction of the line chart, are you "convinced" of the complexity of each of the sorts? Why or why not?
```
As the size of the array increases, the time taken to sort the array increases exponentially for Bubble Sort.
Selection and Insertion sorts grow at a slower place.
This is because the number of comparisons and swaps increases exponentially as the size of the array increases. 
The time taken to sort the array using Merge and Quick sorts increases linearly as the size of the array increases. 
This is because the number of comparisons and swaps increases linearly as the size of the array increases.
```

### 3. Big O
Build another table that presents the best, worst, and average case for Bubble Sort. Selection, Insertion, Merge, and Quick. You are free to use resources for this, but please reference them if you do.

| Sorting Algorithm | Best Case  | Worst Case | Average Case |
|-------------------|------------|------------|--------------|
| Bubble Sort       | O(n)       | O(n^2)     | O(n^2)       |
| Selection Sort    | O(n^2)     | O(n^2)     | O(n^2)       |
| Insertion Sort    | O(n)       | O(n^2)     | O(n^2)       |
| Merge Sort        | O(n log n) | O(n log n) | O(n log n)   |
| Quick Sort        | O(n log n) | O(n^2)     | O(n log n)   |

Here's what I referred : https://www.geeksforgeeks.org/analysis-of-different-sorting-techniques/

#### 3.2 Worst Case
Provide example of arrays that generate _worst_ case for Bubble, Selection, Insertion, Merge Sorts
> 1. Bubble Sort:
> Whenever the array is sorted in reverse order, the worst case occurs. ```Example: 5 4 3 2 1```

> 2. Selection Sort:
> Whenever the array is sorted in reverse order, the worst case occurs. ```Example: 5 4 3 2 1``` 

> 3. Insertion Sort:
> Whenever the array is sorted in reverse order, the worst case occurs. ```Example: 5 4 3 2 1```

> 4. Merge Sort:
> Merge Sort has a time complexity of O(n log n) for all cases, including the worst case. Therefore, the input array does not affect the worst-case scenario.

> 5. Quick Sort:
> The worst-case scenario for Quick Sort occurs when the input array is already sorted or sorted in reverse order. ```Example: 5 4 3 2 1```

#### 3.3 Best Case
Provide example of arrays that generate _best_ case for Bubble, Selection, Insertion, Merge Sorts 
> 1. Bubble Sort:
The best-case scenario for Bubble Sort occurs when the input array is already sorted. ```Example: 1 2 3 4 5```

> 2. Selection Sort:
The best-case scenario for Selection Sort also occurs when the input array is already sorted. ```Example: 1 2 3 4 5```

> 3. Insertion Sort:
The best-case scenario for Insertion Sort occurs when the input array is already sorted. ```Example: 1 2 3 4 5```

> 4. Merge Sort:
Merge Sort has a time complexity of O(n log n) for all cases, including the best case. Therefore, the input array does not affect the best-case scenario.

> 5. Quick Sort:
     The best-case scenario for Quick Sort occurs when the pivot chosen during partitioning is always the median element. ```Example: 3 2 1 4 5```


#### 3.4 Memory Considerations
Order the various sorts based on which take up the most memory when sorting to the least memory. You may have to research this, and include the mathematical notation.
> Here's what I referred! 
https://www.geeksforgeeks.org/sorting-algorithms/
> 1. Merge Sort: O(n)
> 2. Quick Sort: O(log n)
> 3. Insertion Sort: O(1), Selection Sort: O(1), Bubble Sort: O(1)

### 4. Growth of Functions
Give the following values, place them correctly into *six* categories. Use the bullets, and feel free to cut and paste the full LatexMath we used to generate them.  

$n^2$  
$n!$  
$n\log_2n$  
$5n^2+5n$  
$10000$  
$3n$    
$100$  
$2^n$  
$100n$  
$2^{(n-1)}$
#### Categories
1. Exponential Growth:
- $2^n$ 
- $2^{(n-1)}$

2. Factorial Growth:
- $n!$

3. Quadratic Growth:
- $n^2$
- $5n^2+5n$

4. Logarithmic Growth:
- $n\log_2n$
5. Linear Growth:
- $3n$
- $100n$

6. Constant Growth:
- $10000$
- $100$

### 5. Growth of Function Language

Pair the following terms with the correct function in the table. 
* Constant, Logarithmic, Linear, Quadratic, Cubic, Exponential, Factorial

| Big $O$     |  Name  |
| ------      | ------ |
| $O(n^3)$    | Cubic|
| $O(1)$      | Constant |
| $O(n)$      | Linear |
| $O(\log_2n)$ | Logarithmic |
| $O(n^2)$    | Quadratic |
| $O(n!)$     | Factorial |
| $O(2^n)$    | Exponential |



### 6. Stable vs Unstable
Look up stability as it refers to sorting. In your own words, describe one sort that is stable and one sort that isn't stable

>In the context of sorting algorithms, stability refers to the preservation of the relative order of elements with equal keys or values during the sorting process.
A stable sort algorithm ensures that if two elements have the same key, the one that appears first in the original input will also appear first in the sorted output.
An unstable sorting algorithm, like Quick Sort, does not guarantee the preservation of the original order of equal elements.

### 6.2 When stability is needed?
Explain in your own words a case in which you will want a stable algorithm over an unstable. Include an example.
* Stability is important when we want to maintain the original order of equal elements in the input array. For example, if we want to sort a list of students by their first name, we would want to use a stable sorting algorithm. This is because if two students have the same first name, we would want the student that appears first in the original list to also appear first in the sorted list. If we use an unstable sorting algorithm, the order of the students with the same first name may be changed in the sorted list.
* Therefore, stable sorting algorithm maintains the relative order of items with equal sort keys. Some examples of stable sorting algorithms are Bubble Sort, Insertion Sort, and Merge Sort. These algorithms preserve the relative order of equal elements while sorting the input data.

### 7. Gold Thief

You are planning a heist to steal a rare coin that weighs 1.0001 ounces. The problem is that the rare coin was mixed with a bunch of counter fit coins. You know the counter fit coins only weight 1.0000 ounce each. There are in total 250 coins.  You have a simple balance scale where the coins can be weighed against each other. Hint: don't think about all the coins at once, but how you can break it up into even(ish) piles. 

#### 7.1 Algorithm
Describe an algorithm that will help you find the coin. We encourage you to use pseudo-code, but not required.
1. Start by dividing the 250 coins into two equal-ish piles (125 each) and place them on the balance scale. 
2. If one pile is heavier than the other, it must contain the rare coin. Proceed to the next step with the heavier pile. 
3. If both piles weigh the same, it means the rare coin is not in either of them. Now you can safely eliminate both piles from your search. 
4. In case you have an uneven number of coins after dividing, set aside the one unallocated coin for now. 
   1. Take the heavier pile (if applicable) and divide it again into two equal-ish piles (e.g., 63 and 62 coins) for weighing. 
   2. Once more, if one pile is heavier, it contains the rare coin. Proceed to the next step with the heavier pile. 
7. If both piles weigh the same, eliminate both piles from your search.
8. If you have an uneven number of coins at this step, combine the unallocated coin from the previous division with one of the piles. Place the other pile on the balance scale for weighing. 
9. Continue this process of dividing the heavier pile in half and weighing the sub-piles, ensuring to handle any uneven number of coins that may arise. 
10. Keep repeating the process until you are left with only one coin.

The remaining one coin is the one we are looking for. We can now weigh it against the original coin to confirm that it is indeed the rare coin.
#### 7.2 Time Complexity
What is the average time complexity of your algorithm? 
> By dividing the coins into two at each step, we effectively reduce the search space in logarithmic steps, resulting in a maximum of 8 weighings required to find the counterfeit coin.
> The average time complexity of this algorithm is O(log n), where n is the total number of coins (250 in this case). Each weighing divides the remaining search space by a factor of 2, leading to a logarithmic growth rate. Therefore, the number of weighings required to find the counterfeit coin is logarithmic with respect to the total number of coins.


### 8. Modern Sort
Sorting algorithms are still being studied today. They often include a statistical analysis of data before sorting. This next question will require some research, as it isn't included in class content. When you call `sort()` or `sorted()` in Python 3.6+, what sort is it using? 
> TimSort


#### 8.1 Visualize
Find a graphic / visualiation (can be a youtube video) that demonstrates the sort in action.
https://www.geeksforgeeks.org/timsort/
<img src="https://res.cloudinary.com/practicaldev/image/fetch/s--xted_fT3--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_66%2Cw_880/https://dev-to-uploads.s3.amazonaws.com/i/lvsvw4gn3eytl7jvka2g.gif">

#### 8.2 Big O
Give the worst and best case time-complexity, and examples that would generate them.
1. Best-case time complexity: O(n)
> The best-case scenario for Timsort occurs when the input array is already sorted or has very few inversions. In this case, Timsort can take advantage of the presorted nature of the array and minimize the number of comparisons and swaps required. The best-case time complexity of O(n) means that the algorithm can achieve linear time complexity when the input is already sorted. An example of best-case input would be an already sorted array: [1, 2, 3, 4, 5, 6, 7, 8, 9].
2. Worst-case time complexity: O(n log n)
> The worst-case scenario for Timsort occurs when the input array is in a completely reverse or nearly reverse order. In this case, Timsort needs to perform a significant number of comparisons and swaps to sort the array properly. The worst-case time complexity of O(n log n) indicates that the algorithm's performance degrades as the size of the input (n) increases. An example of worst-case input would be an array in reverse order: [9, 8, 7, 6, 5, 4, 3, 2, 1].

<hr>

## References
Add your references here. A good reference includes an inline citation, such as [1] , and then down in your references section, you include the full details of the reference. Computer Science research often uses [IEEE] or [ACM Reference format].

[1] Reference info, date, etc.
> 1. TimSort: https://www.geeksforgeeks.org/timsort/ 
> 2. Analysis of Sorting : https://www.geeksforgeeks.org/analysis-of-different-sorting-techniques/
> 3. Stability : https://www.geeksforgeeks.org/stability-in-sorting-algorithms/



## Footnotes:
[^note]: You will want at least 10 different N values, probably more to see the curve for Merge and Quick. If bubble, selection, and insertion start to take more than a  minute, you can say $> 60s$. 


<!-- links moved to bottom for easier reading in plain text (btw, this a comment that doesn't show in the webpage generated-->
[image markdown]: https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#images

[ACM Reference Format]: https://www.acm.org/publications/authors/reference-formatting
[IEEE]: https://www.ieee.org/content/dam/ieee-org/ieee/web/org/conferences/style_references_manual.pdf