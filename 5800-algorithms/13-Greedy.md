# 1 - Unweighted Interval Scheduling

**Weighted**: DP, but for unweighted we don’t need to use DP. We can just use Greedy approach.

There are two steps to this:
- Greedy template or Greedy choices - The greediest choice that you can make at every step. Like a choice diagram.
- And then prove them using greedy stays ahead approach.

## Algorithm

### 01 - Greedy Template
![[Pasted image 20231212175329.png]]

1. Sort intervals based on their finish times.
2. Set A to be empty, where A is set of jobs selected.
3. Go one by one based on their finish times.

> So, the correct way to do is sort them based of earliest finish time.
### 02 - Algorithm

![[Pasted image 20231212175426.png]]
But this algorithm takes $O(n^2)$ time.
#### Honestly, we can do faster.
Instead of looping over all the elements in A, we can instead only check for the last interval. Now checking compatibility takes O(1) time.
#### Overall, the time complexity becomes : $O(nlogn)$
Sorting takes $O(n \cdot logn)$ time, and then the for loop takes $O(n)$ time.
![[Pasted image 20231212175604.png]]
### Therefore the implementation looks like
Finding the next earliest finishing time of remaining intervals via linear search: • $O(n^2)$
OR **Sorting** 
- Sort all the requests by finishing time — $O(n \cdot logn)$  
- Iterate through the sorted array taking the next legal request — $O(n)$ • $O(n \cdot logn)$

## Proof - Greedy stays ahead
### PF by contradiction
1. Assume that greedy approach is not optimal.
2. `r+1` is the first interval where optimal solution diverges from greedy algorithm.
3. The same intervals are `r`
4. At `r+1` greedy picks the one that finishes first.

**Claim**: Finish time of r+1 is no later than the finish time of r+1 selected by the optimal solution.
![[Pasted image 20231212175934.png]]
what if we replace $j_{r+1}$ with $i_{r+1}$ ?
**it will not conflict** with other choices of the OPT algorithm because others will definitely start after $i_{r+1}$. Making, it will be compatible with OPT. 
This is also based on our greedy template that at every step it will choose the item that finishes first.
![[Pasted image 20231212180040.png]]
So we take OPT but we only replace $j_{r+1}$ with $i_{r+1}.$ Excluding and including something works because we exclude one and include one, so the optimal solution should remain optimal. As the total number of intervals remain the same.

> 💡 This is the step that breaks while WEIGHTED INTERVALS. because greedy picks one that finishes faster but the other one might have larger weight. Therefore exlcuding and including does not keep the optimal solution optimal as weights change!

Contradiction ? : OPT’ gives a better solution to Greedy then OPT. Hence, it contradicts the first statement.

Continuing this, we can obtain a solution that is closer to the greedy algorithm and then eventually becomes the same as greedy algorithm.

#### Conclusion and Summary
1. Assume greedy is not optimal
2. Take the point till where optimal solution is the same as greedy.
3. Then you make it closer to the greedy algorithm, at each step which contradicts the first state

## Proof - Exchange argument
![[Pasted image 20231212180430.png]]
- And $r \geq s$ 
- $G$ and $O$ are the same till interval $k$
- 

### Exchange argument :
Exchange $j_k$ for $i_k$ in $O$ .... and this will not impact the output of $O$.

# 2 - Minimum Lateness Scheduling

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/eb08417d-4145-44ca-8a66-d1606cae011b/Untitled.png)

Output: ordering of jobs, when you can do only one job at a time. Hence return ordering such that the max lateness is minimized.

### 1. Greedy Template

1. Shortest Job first — $min (t_i)$
2. Earliest Deadline first
3. Choose the most urgent job first — $min (d_i - t_i)$

How do you decide any one? Come up with an example where one is optimal.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/7cd9fc3e-2392-4046-9826-95f2b006cb39/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/b6244d4a-842f-47ff-b160-f0d313c1d311/Untitled.png)

<aside> 💡 This only works when there are no weights on the job!

</aside>

The most efficient one is Earliest Deadline first:

- Sort jobs so that $d_1 \leq d_2 \leq .... \leq d_n$
- For $i = 1,...,n$
    - Schedule the job $(i)$ right after job $(i -1)$ finishes

### 2. Proof - Exchange Argument

- We want to say we can iteratively apply some exchanges to this solution $O$, and a series of exchanges that eventually lead to greedy solution $G$.
- In this context, exchange will be the order of jobs done.
- Provided that, no exchange increases the lateness of $O$

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/1540b9bf-70cf-489d-bc9b-a03217e90905/Untitled.png)

> how is this different from Greedy Stays Ahead? we start with the idea that at any point greedy approach makes the best possible choice, here we keep modifying the optimal solution to reach greedy solution.

We can say that two jobs $i, j$ are inverted in $O$ if $d_i < d_j$ (deadline of i is before j) but j comes before i in $O$.

- Observation: greedy has no inversions.

<aside> 💡 I want to invert two jobs, but I don’t want to invert any two jobs, I just wish to invert consecutive ones. And flipping them only reduces the lateness. if there is some inversion, there is also some consecutive inversion.

</aside>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/5347cf1b-644d-48b3-9ecc-5c6aa460d68c/Untitled.png)

### Step 1:

If $O$ has an inversion, then it has an inversion $i,j$ which are scheduled consecutively in $O$.

- Take an inversion $i, j$ where $i$ and $j$ are closest in schedule $O$
    - By definition $d_j < d_i$ but $j$ comes before $i$.
- Suppose there is a job k scheduled between `i` and `j`

For any pair `i` and `j` either they come consecutively or there is something in between `k`.

Case 1: $d_k < d_j$

Case 2: $d_k > d_i$

### Step 2:

If `i`, `j` are consecutive jobs that are inverted then flipping them only reduces lateness. As:

- Does not change the lateness of other jobs.
- Let's assume these jobs have. $d_i < d_j$ and lengths $t_i, t_j$.

<aside> 💡 So the idea of picking consecutive ones is we donot change anything before it, and we also do not change anything after it. So it finishes exactly at the same time. If they were not consecutive, everything in between would change!

</aside>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/28503dd4-d05e-4430-9697-6443fabd9634/Untitled.png)

### Max lateness of `i` and `j` before flipping:

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/fd471726-5abd-46b7-a610-c290c8b3e563/Untitled.png)

### Max Lateness for `i` and `j` AFTER flipping:

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/742ea95f-1b6e-4472-8ba1-f56bc353f623/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/785834e5-2310-4c67-ac4c-5cea2da8f497/Untitled.png)

And saying that we mean that with every inversion we improve the optimal solution. Which means that it is not optimal as it cannot be improved. So, the optimal solution should not have any consecutive inverted pairs, implying optimal solution is equal to the greedy solution.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/e15713bd-3bc7-40cc-87b2-1c10651b4c35/Untitled.png)

# 3 - Fractional Knapsack

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/8e34c468-8876-48e0-8746-64ca90c216e7/Untitled.png)