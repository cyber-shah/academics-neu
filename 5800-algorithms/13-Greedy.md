## 1 - Unweighted Interval Scheduling
**Weighted**: DP, but for unweighted we don’t need to use DP. We can just use Greedy approach.
There are two steps to this:
- Greedy template or Greedy choices - The greediest choice that you can make at every step. Like a choice diagram.
- And then prove them using greedy stays ahead approach.
### Algorithm

#### 01 - Greedy Template
![[Pasted image 20231212175329.png]]
1. Sort intervals based on their finish times.
2. Set A to be empty, where A is set of jobs selected.
3. Go one by one based on their finish times.
#### 02 - Algorithm
![[Pasted image 20231212175426.png]]
But this algorithm takes $O(n^2)$ time.
##### Honestly, we can do faster.
Instead of looping over all the elements in A, we can instead only check for the last interval. Now checking compatibility takes O(1) time.
##### Overall, the time complexity becomes : $O(nlogn)$
Sorting takes $O(n \cdot logn)$ time, and then the for loop takes $O(n)$ time.
![[Pasted image 20231212175604.png]]
#### Therefore the implementation looks like
Finding the next earliest finishing time of remaining intervals via linear search: 
- $O(n^2)$
OR **Sorting** 
- Sort all the requests by finishing time — $O(n \cdot logn)$  
- Iterate through the sorted array taking the next legal request — $O(n)$ • $O(n \cdot logn)$
### Proof:
#### PF by contradiction // Greedy Stays Ahead
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

**Contradiction** : `OPT` gives a better solution to `Greedy` then OPT. Hence, it contradicts the first statement.
Continuing this, we can obtain a solution that is closer to the greedy algorithm and then eventually becomes the same as greedy algorithm.
##### Conclusion and Summary
1. Assume greedy is not optimal
2. Take the point till where optimal solution is the same as greedy.
3. Then you make it closer to the greedy algorithm, at each step which contradicts the first state
#### Proof - Exchange argument
![[Pasted image 20231212180430.png]]
1. **Assumption: Greedy Approach is Not Optimal**
    - Start by assuming that the greedy approach is not optimal for the interval scheduling problem.
2. **Identify Divergence Point $r+1$:**
    - Assume there exists a point $r+1$ where the optimal solution diverges from the greedy algorithm.
3. **Define the Common Set of Intervals $r$**
    - Identify the set of intervals that both the optimal solution and the greedy algorithm have in common up to point $r+1$
4. **Greedy Picks the Earliest Finishing Interval at r+1r+1:**
    - At the point $r+1$, the greedy algorithm picks the interval that finishes first among the remaining intervals.

> **Claim**: Finish Time of $r+1$ is No Later Than the Optimal Finish Time:
> The claim is that the finish time of the interval selected by the greedy algorithm at r+1r+1 is no later than the finish time of the corresponding interval selected by the optimal solution.

5. **Replace $jr+1$  with $ir+1​$:**
    - Consider replacing the interval selected by the optimal solution (jr+1​) with the interval selected by the greedy algorithm (ir+1​).
	- Compatibility with Other Choices of OPT:
	    - This replacement does not conflict with other choices of the optimal solution because the other intervals will definitely start after ir+1
	    - This is based on the greedy template that at every step, it chooses the interval that finishes first.
6. **Excluding and Including Works:**
    - Excluding jr+1​ and including ir+1​ maintains compatibility with the optimal solution, as the total number of intervals remains the same.
7. **Contradiction:**    
    - Assume the optimal solution is better after the replacement (jr+1 replaced with ir+1​).
    - This leads to a contradiction, as it implies that the optimal solution is better than the greedy algorithm, contradicting the initial assumption that the greedy approach is not optimal.
8. **Continuation of the Exchange:**
    - Continue this process to obtain a solution that is closer to the greedy algorithm at each step, leading to a contradiction.
    - Eventually, this process will result in a solution that is the same as the greedy algorithm.



## 2 - Minimum Lateness Scheduling
![[Pasted image 20231213201819.png]]

#### 1. Greedy Template
1. Shortest Job first — $min (t_i)$
2. Earliest Deadline first
3. Choose the most urgent job first — $min (d_i - t_i)$
> How do you decide any one? Come up with an example where one is optimal.

The most efficient one is Earliest Deadline first:
- Sort jobs so that $d_1 \leq d_2 \leq .... \leq d_n$
- For $i = 1,...,n$
    - Schedule the job $(i)$ right after job $(i -1)$ finishes
#### 2. Proof - Exchange Argument
##### Idea of the exchange argument
- We want to say we can iteratively apply some exchanges to this solution $O$, and a series of exchanges that eventually lead to greedy solution $G$.
- In this context, exchange will be the order of jobs done.
- Provided that, no exchange increases the lateness of $O$
![[Pasted image 20231213203258.png]]
> how is this different from Greedy Stays Ahead? we start with the idea that at any point greedy approach makes the best possible choice, here we keep modifying the optimal solution to reach greedy solution. 

##### Application
We can say that two jobs $i, j$ are inverted in $O$ if $d_i < d_j$ (deadline of i is before j) but j comes before i in $O$.
- **Observation**: greedy has no inversions.

![[Pasted image 20231213203453.png]]
##### Step 1:
If $O$ has an inversion, then it has an inversion $i,j$ which are scheduled consecutively in $O$.
- Take an inversion $i, j$ where $i$ and $j$ are closest in schedule $O$
    - By definition $d_j < d_i$ but $j$ comes before $i$.
- Suppose there is a job k scheduled between `i` and `j`
For any pair `i` and `j` either they come consecutively or there is something in between `k`.
Case 1: $d_k < d_j$
Case 2: $d_k > d_i$
##### Step 2:
If `i`, `j` are consecutive jobs that are inverted then flipping them only reduces lateness. As:
- Does not change the lateness of other jobs.
- Let's assume these jobs have. $d_i < d_j$ and lengths $t_i, t_j$.
> 💡 So the idea of picking consecutive ones is we do not change anything before it, and we also do not change anything after it. So it finishes exactly at the same time. If they were not consecutive, everything in between would change!
![[Pasted image 20231213203646.png]]

And saying that we mean that with every inversion we improve the optimal solution. Which means that it is not optimal as it cannot be improved. So, the optimal solution should not have any consecutive inverted pairs, implying optimal solution is equal to the greedy solution.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7812bb71-3628-4933-9b5d-6521e4bb06bf/e15713bd-3bc7-40cc-87b2-1c10651b4c35/Untitled.png)
