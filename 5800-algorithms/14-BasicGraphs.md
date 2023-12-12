# Binary Graphs
## What is a tree?
In graph theory, a tree is an undirected, connected, and acyclic graph. This means that there is exactly one path between any two nodes, and there are no cycles (loops). Trees are fundamental data structures in computer science and are widely used in various algorithms and applications.

# Depth First Search
### Pseudo code for un-directed graphs
```python
def dfs_undirected(graph, start, visited):
    if start not in visited:
        print(start)
        visited.add(start)
        for neighbor in graph[start]:
            dfs_undirected(graph, neighbor, visited)
```

### Pseudo code for directed graphs
```python
def dfs_directed(graph, start, visited):
    if start not in visited:
        print(start)
        visited.add(start)
        for neighbor in graph[start]:
            dfs_directed(graph, neighbor, visited)
```
### Time and Space Complexity:
In the worst case, where all vertices and edges are explored, the time complexity is 
$O(V + E)$, where V is the number of vertices and E is the number of edges.

This is because each vertex and each edge is visited once. The adjacency list representation allows us to efficiently iterate over the neighbors of a vertex, resulting in a linear time complexity proportional to the sum of vertices and edges.

### Kinds of edges

![[Pasted image 20231212131612.png]]
- **Tree Edge:** An edge in the DFS tree.
- **Back Edge:** An edge that goes from a node to one of its ancestors in the DFS tree.
- **Forward Edge:** An edge that goes from a node to one of its descendants in the DFS tree.
- **Cross Edge:** An edge that goes between nodes that are not in a parent-child relationship in the DFS tree.
### Discovery and Finish Times determine Edge Types
For any (directed) edge ($u,v$):
1. If u started earlier but finished later ⟹ it's a Tree or Forward edge.
   - Tree Edge: If v is an undiscovered node or a descendant of u.
   - Forward Edge: If v is a non-descendant of u.
2. If v started earlier but finished later ⟹ it's a Back edge.
   - Back Edge: If v is an ancestor of u in the DFS tree.
3. If v started and finished earlier ⟹ it's a Cross edge.
   - Cross Edge: If v and u are in different subtrees and neither is an ancestor of the other.
4. If u started and finished earlier ⟹ it's a Cross edge (in the context of finish times)
   - Cross Edge: If v and u are in different sub trees and neither is an ancestor of the other.



# Directed Acyclic Graphs (DAGs)
- DAG: A directed graph with no directed cycles
- DAGs represent precedence relationships
### Topological Ordering
If a graph G is a DAG then it must be possible to topological order it.

> In any DAG, there is always a node with no outgoing edges (i.e., out-degree of 0)

Proof by contradiction: lets imagine that there is no node with out-degree of 0. Then, we can start at any node and follow the outgoing edges. Since there are no cycles, we will never return to the starting node. Therefore, we can keep following the outgoing edges forever, which is a contradiction because there are a finite number of nodes in the graph.\

Therefore as a theorem we can say that:\
**Theorem:** If G is a DAG, then G has a topological ordering.
**Fact**: In any DAG there is always a node with no outgoing edges (i.e., out-degree of 0).

### Topological Ordering Algorithm using DFS
1. Init a stack S
2. For each node u in G:
   1. If u is not visited, call dfs(u)
   2. In dfs(u), once all of u's neighbors are visited, push u onto S
   3. Return from dfs(u)
3. Return S
4. Pop elements from S to get the topological ordering

**Time Complexity**: $O(|V|+|E|)$ ( because we are using DFS and each node is visited once and each edge is visited once) 
**Space Complexity:** $O(|V|)$ (because we are using a stack)




# Breadth First Search
1. Init a queue Q
2. Add the start node s to Q
3. While Q is not empty:
   1. Pop the first node from Q
   2. For each neighbor of this node:
      1. If the neighbor is not visited, add it to Q
      2. Mark the neighbor as visited

![[Pasted image 20231212174245.png]]

### Time and Space Complexity:
In the worst case, where all vertices and edges are explored, the time complexity is 
$O(V + E)$, where V is the number of vertices and E is the number of edges.


# Strongly Connected Components
**Definition**: In a directed graph, we say two vertices $u$ and $v$ are strongly connected if there is a path from $v$ to $u$ and a path from $u$ to $v$.

> Algorithm to find strongly connected components: DFS!