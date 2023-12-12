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


