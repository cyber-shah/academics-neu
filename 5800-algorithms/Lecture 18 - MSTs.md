A minimum spanning tree (MST) is a **subset** of the edges of a connected, edge-weighted undirected graph that connects all the vertices together, without any cycles and **with the minimum possible total edge weight.** That is, it is a spanning tree whose sum of edge weights is as small as possible.
- **Properties**: A MST has the same number of vertices as the original graph, but only V-1 edges, where V is the number of vertices. It is also acyclic and connected. There can be more than one MST for a given graph.
- A spanning tree of * is a subset of + ⊆ & of the edges such that !, + forms a tree  - **no cycles and connected**

## Cut Property:
https://www.youtube.com/watch?v=QYdZS4S-FyU
- We call such an edge a $safe$ edge.

## Cycle Property:
Let $C$ be a cycle. Let $f$ be the maximum weight edge in $C$. Then the MST $T∗$ does not contain $f$
-  We call such an $f$ a useless edge

## Kruskal's Algorithm:
Let $T = ∅$ 
- For each edge e in ascending order of weight: 
	- If adding , would decrease the number of connected components add $e$ to $T$.
- Time complexity: $m(logn)$ 
- Correctness: every edge we add is $safe$ and every edge we don’t add is $useless$

