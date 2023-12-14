#### Types of Problems:
1. **Shortest Path:** given nodes $s, t ∈ V$, find the shortest path from s -> t
2. **Single-Source Shortest Paths:** given a node $s ∈ V$, find the shortest paths from s to **every** $t ∈ V$
# Single  Shortest Path Problem (SP)

#### Why just not use BFS?
- BFS is primarily designed for *unweighted graphs.* In a weighted graph, where edges have different weights or costs, BFS may not give the correct shortest path. It doesn't consider edge weights in its traversal, treating all edges as having equal weight.
- BFS assumes that all edges have the same weight. If the graph has *negative-weight* edges, BFS may not produce correct results. Negative weights can lead to cycles that decrease the overall cost, and BFS might get stuck in such cycles without finding the optimal path.
- BFS guarantees the shortest path in terms of the *number of edges*, but this may not correspond to the shortest path in terms of the sum of edge weights. In weighted graphs, the shortest path in terms of edges may not be the shortest path in terms of weights.
## #Dijkstra's Algorithm:
Dijkstra’s Shortest Path Algorithm is a modification of BFS for *non-negatively weighted graphs.*
![[Dijkstra_Animation.gif]]
#### Informal Version:
1. Initialization:
    - Begin with the starting node.
    - Assign it a distance value of zero.
    - Set all other nodes' distances to infinity.
2. Exploration:
    - Choose the node with the smallest current distance.
    - Explore its neighbors.
3. Update Distances:
    - For each neighbor, calculate the distance through the current node.
    - If the newly calculated distance is smaller, update the neighbor's distance.
4. Repeat:
    - Repeat steps 2 and 3 until all nodes have been visited or the destination is reached.

> To find the shortest path maintain a parent pointer as well so its easier to backtrack.
#### Naive Implementation:
```python
Dijkstra(G = (V,E,{w(e)},s):
	d[s] ← 0, d[u] ← ∞ for every u != s
	parent[u] ←⊥ for every u
	Q ← V # Q holds the unexplored nodes
	
	While (Q is not empty):
		u ← argmin d[w]  # Find closest unexplored
		Remove u from Q
		
		# Update the neighbors of u
		For (v in out[u]):
			If (d[v] > d[u] + w(u,v)):
			d[v] ← d[u] + w(u,v)
			parent[v] ← u
	Return (d, parent)
```

#### Time complexity:
1. Each edge is explored one in  ``` For (v in out[u]): ``` $O(m)$
2. To find the closest unexplored node = and removing u from Q : $O(n^2)$
3. Therefore the overall time complexity = $O(n^2 + m)$ 
### Using Heaps/Priority queues:
#### Why queues?
Need a data structure Q to hold key-value pairs so that we can quickly find closest unexplored node.
- Keys: unexplored vertices
- Values: upper-bound of distance from source.

We need to **support** the following operations:
- Insert(Q,k,v): add a new key-value pair
- Lookup(Q,k): return the value of some key
- ExtractMin(Q): identify the key with the smallest value
- DecreaseKey(Q,k,v): reduce the value of some key

#### There are two options to implement this:
1. Naive approach : Dictionary
	- Insert, Decrease and Lookup take $O(1)$ time
	- ExtractMin takes $O(n)$ time.
2. Binary Heaps:
	- All operations take $O(logn)$ time.

## #Heaps

Heaps are a specialized tree-based data structure that satisfy the heap property. A heap is a complete binary tree where every node satisfies the heap property, which means that the key of each node is either always greater than or always less than the keys of its children, depending on whether it's a max-heap or a min-heap.
![[0*ok7-U3UxzYtOz5b9.gif]]

There are two main types of heaps:
1. **Max-Heap:**
    - In a max-heap, the key of each node is always greater than or equal to the keys of its children.
    - The maximum element is at the root.
2. **Min-Heap:**
    - In a min-heap, the key of each node is always less than or equal to the keys of its children.
    - The minimum element is at the root.
### Heap Operations:
1. **Insertion:**
    - To insert an element into a heap, it is typically added at the next available position (bottom, rightmost position).
    - Then, the heap property is restored by repeatedly comparing the new node with its parent and swapping them if necessary until the heap property is satisfied.
2. **Deletion:**
    - In a max-heap, the maximum element (root) is removed.
    - In a min-heap, the minimum element (root) is removed.
    - After removal, the last element is moved to the root, and the heap property is restored by comparing the new root with its children and swapping if necessary.
### Heapify:
- Heapify is a process of converting an array into a heap, either max-heap or min-heap.
- It can be done in two ways: bottom-up (starting from the last non-leaf node and moving up) or top-down (starting from the first node and moving down).
## What about negative edge weight : #Bellman Ford!
The Bellman-Ford algorithm is a single-source shortest path algorithm that can handle graphs with negative edge weights, provided that the graph does not have negative cycles reachable from the source vertex. Here are the key points about the Bellman-Ford algorithm:
![[Bellman–Ford_algorithm_example.gif]]
#### Algorithm Steps:
1. **Initialization:**
    - Initialize the distance from the source vertex to itself as 0 and to all other vertices as infinity.
    - Set the predecessor of all vertices to null.
2. **Relaxation:**
    - Iterate through all edges in the graph for |V| - 1 times, where |V| is the number of vertices.
    - For each edge (u, v) with weight w, if the distance to v through u is shorter than the current distance to v, update the distance to v and set the predecessor of v to u.
3. **Check for Negative Cycles:**
    - After |V| - 1 iterations, if there are still updates to be made, then the graph contains a negative cycle reachable from the source.
```python
function BellmanFord(graph, source):
    initializeSingleSource(graph, source)
    
    # for each edge
    for i from 1 to |V| - 1:
        for each edge (u, v) in graph.edges:
            relax(u, v, w)

    # Check for negative cycles
    for each edge (u, v) in graph.edges:
        if distance[v] > distance[u] + weight(u, v):
            return "Graph contains a negative cycle"

    return distance, predecessor
```
#### Time Complexity:
- The time complexity of the Bellman-Ford algorithm is $O(|V| * |E|)$, where $|V|$ is the number of vertices and $|E|$ is the number of edges.
#### Key Points:
- Bellman-Ford works with graphs that may have negative edge weights but not negative cycles reachable from the source.
- It can be less efficient than Dijkstra's algorithm for graphs without negative weights.
- It is a dynamic programming approach, iterating over all edges multiple times to find the shortest paths. 

| Feature                            | Dijkstra's Algorithm                  | Bellman-Ford Algorithm                |
| ----------------------------------- | ------------------------------------- | ------------------------------------- |
| **Type of Algorithm**                   | Greedy | Dynamic Programming |
| **Type of Graph**                   | Works only with non-negative weights | Works with graphs with negative weights (but not negative cycles) |
| **Edge Relaxation**                 | Greedy approach: relaxes edges based on the current shortest path estimate | Iterative approach: relaxes all edges in each iteration |
| **Priority Queue**                  | Typically uses a priority queue       | Not necessarily requires a priority queue; can use simple arrays |
| **Negative Weights**                | Cannot handle negative edge weights   | Handles graphs with negative edge weights |
| **Negative Cycles**                 | Doesn't work with graphs containing negative cycles | Can detect and report the presence of negative cycles |
| **Optimality**                      | Finds the shortest path from the source to all other vertices based on current estimates | Finds the shortest path from the source to all other vertices, even with negative weights |
| **Initialization of Distances**     | Sets initial distances to infinity    | Sets initial distances to infinity    |
| **Termination Condition**           | Stops when the destination is reached | Stops after V - 1 iterations, and checks for negative cycles |
| **Time Complexity**                 | $O((V + E) * log V)$ (with binary heap) | $O(V * E)$ |



