#### Types of Problems:
1. **Shortest Path:** given nodes $s, t ∈ V$, find the shortest path from s -> t
2. **Single-Source Shortest Paths:** given a node $s ∈ V$, find the shortest paths from s to **every** $t ∈ V$
# Shortest Path Problem (SP)

#### Why just not use BFS?
- BFS is primarily designed for *unweighted graphs.* In a weighted graph, where edges have different weights or costs, BFS may not give the correct shortest path. It doesn't consider edge weights in its traversal, treating all edges as having equal weight.
- BFS assumes that all edges have the same weight. If the graph has *negative-weight* edges, BFS may not produce correct results. Negative weights can lead to cycles that decrease the overall cost, and BFS might get stuck in such cycles without finding the optimal path.
- BFS guarantees the shortest path in terms of the *number of edges*, but this may not correspond to the shortest path in terms of the sum of edge weights. In weighted graphs, the shortest path in terms of edges may not be the shortest path in terms of weights.
## Dijkstra's Algorithm:
Dijkstra’s Shortest Path Algorithm is a modification of BFS for *non-negatively weighted graphs.*
### Informal Version:
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
### Naive Implementation:
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
