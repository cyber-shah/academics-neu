# HW README

Name: ```Pranchal Shah```

Github Account name: ```cyber-shah```

Link to Assignment on Github: (copy and paste the link to your assignment repo here)
https://github.com/Su23-CS5008-Online-Lionelle/homework-04-cyber-shah

How many hours did it take you to complete this assignment (estimate)? 

Did you collaborate with any other students/TAs/Professors? If so, tell us who and in what capacity.  
- one per row, add more if needed

Did you use any external resources (you do not have to cite in class material)? (Cite them below)  
- one row per resource


(Optional) What was your favorite part of the assignment? 

(Optional) How would you improve the assignment? 

## Understanding Time Complexity

1. Using a markdown table and markdown/latex math, show the BigO for Arrays, Singly Linked Lists, Doubly Linked Lists (so total of 3). For the columns, you will look at the Worst Case Time Complexity for Access, Search/Find, Insertion, and Deletion.

    | Big O               | Access | Search/Find | Insertion | Deletion |
    |---------------------|--------|-------------|-----------|----------|
    | Arrays              | O(1)   | O(n)        | O(n)      | O(1)     |
    | Singly Linked Lists | O(n)   | O(n)        | O(n)      | O(n)     |
    | Doubly Linked Lists | O(n)   | O(n)        | O(n)      | O(n)     |

2. Usually for singly and doubly linked lists, we reference both the head and tail for speed considerations. What would be the cost if you only had your head referenced, and you wanted to push to the tail of either?  $O(?)$
```O(n)```

3. Name an example where an array is better than a linked list, and an example where a linked list is better than an array. Make sure to reference the big O as part of your reasoning. 

```Arrays are better than linkedlists in cases wherever access to locations is important O(1). We can do it without traversing through the entire dataset. Insertion is where arrays are very expensive O(n). ```


```Whereas linkedlists are better with dynamic resizing, frequent insertion and deletion operations,. Linkedlists are also useful in data structures like stacks and queues as they are efficient in getting the first/last item. O(1). While finding or searching is very expensive with LinkedLists O(n)``` 


Note: Make sure look at your finished markdown in the browser hosted on github or via a markdown preview extension. To confirm the notation is showing up properly. Double check the resources section in the instructions if you need help with markdown tables. 