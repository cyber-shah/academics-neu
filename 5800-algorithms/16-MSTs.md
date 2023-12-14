[![Distributed minimum spanning tree - Wikipedia](https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Minimum_spanning_tree.svg/1200px-Minimum_spanning_tree.svg.png)

A minimum spanning tree (MST) is a **subset** of the edges of a connected, edge-weighted undirected graph that connects all the vertices together, without any cycles and **with the minimum possible total edge weight.** That is, it is a spanning tree whose sum of edge weights is as small as possible.
- **Properties**: A MST has the same number of vertices as the original graph, but only V-1 edges, where V is the number of vertices. It is also acyclic and connected. There can be more than one MST for a given graph.
- A spanning tree of G is a subset of the edges such that G' forms a tree  - **no cycles and connected**

## Cut Property:
- For any cut in a connected, undirected graph, **the minimum-weight edge that crosses the cut** is an edge of the minimum spanning tree. This property holds true for all possible cuts in the graph.
https://www.youtube.com/watch?v=QYdZS4S-FyU
- We call such an edge a $safe$ edge.

## Cycle Property:
The cycle property is another important concept related to minimum spanning trees (MSTs) in graph theory. It states that for any cycle in a connected, undirected graph, **the maximum-weight edge in that cycle cannot be part of the minimum spanning tree (MST).** 
Let $C$ be a cycle. Let $f$ be the maximum weight edge in $C$. Then the MST $T∗$ does not contain $f$
-  We call such an edge a $useless$ edge

## Kruskal's Algorithm:
![[MST_kruskal_en.gif]]
Let $T = ∅$ 
- **Sort** all the edges in non-decreasing order of their weights and add them to the MST one by one, avoiding the formation of cycles.
- **For each edge** e in ascending order of weight: 
	- **If** adding , would **decrease the number of connected components** add $e$ to $T$.


#### Time complexity: $O(m \cdot logn)$ 
- O(E log E) with a standard sorting algorithm, where E is the number of edges.
- The sorting step dominates the time complexity.
#### Correctness: 
every edge we add is $safe$ and every edge we don’t add is $useless$


## Prim's Algorithm
![[Prim-animation.gif]]
1. Create an empty list called visited []
2. Start with one arbitrary node.
3. For all vertices in the graph:
	1. Add to visited
	2. Pick the **smallest edge that connects an unvisited node** (Greedy Step)
	3. Repeat step 2 until all vertices are visited.
#### Time complexity : $O((E + V )\cdot log(V))$


## Borůvka’s Algorithm
![[Pasted image 20231213200900.png]]
1. **Initialization:**
    - In the initialization step, each vertex is considered as a separate connected component. The initialization step takes $O(V)$ time, where $V$ is the number of vertices.
2. **Main Loop:**
    
    - The main loop continues until there is only a single connected component remaining.
    - In each iteration of the loop, the algorithm goes through each connected component to find the minimum-weight edge connecting it to another component.
    - The number of iterations of the main loop is at most $log(V)$  because in each iteration, the number of connected components is reduced by half (binary-like reduction).
    - In each iteration, the algorithm examines each connected component, and for each component, it looks for the minimum-weight edge. The time complexity of finding the minimum-weight edge for a component can be $O(E)$, where $E$ is the number of edges.
3. **Overall Time Complexity:**
    - The overall time complexity is the product of the number of iterations and the time complexity of each iteration.
    - Time Complexity=$O(log⁡(V)) \cdot O(E) = O(logV \cdot E)$
