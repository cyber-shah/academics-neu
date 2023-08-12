<!-- TOC -->

- [Final Report - Path Finders and A\* Algorithm](#final-report---path-finders-and-a-algorithm)
- [0 - Abstract](#0---abstract)
- [1 - Introduction](#1---introduction)
    - [ 1.1 - Why Path Finding? ](#-11---why-path-finding-)
    - [ 1.2 - What is Path Finding? ](#-12---what-is-path-finding-)
    - [ 1.3 - How do computers find paths? ](#-13---how-do-computers-find-paths-)
    - [ 1.4 Algorithms covered ](#-14-algorithms-covered-)
- [2 - Background](#2---background)
  - [Question 1: Is there a path between node A and node B?](#question-1-is-there-a-path-between-node-a-and-node-b)
    - [1.1 - Depth First Search](#11---depth-first-search)
  - [Question 2 : What is the shortest path between A to B?](#question-2--what-is-the-shortest-path-between-a-to-b)
    - [2.1 - Breadth First Search](#21---breadth-first-search)
    - [2.2 - Dijkstra's Algorithm](#22---dijkstras-algorithm)
    - [2.3 - A\* Algorithm](#23---a-algorithm)
- [3 - Implementation Details](#3---implementation-details)
  - [3.0 - Data Structures](#30---data-structures)
    - [3.0.1 - Nodes](#301---nodes)
    - [3.0.2 - Graphs](#302---graphs)
  - [3.1 - Depth First Search](#31---depth-first-search)
    - [Recursive Approach in C](#recursive-approach-in-c)
    - [Stack Approach in Python](#stack-approach-in-python)
  - [3.2 - Breadth First Search](#32---breadth-first-search)
- [4 - Theoretical Analysis](#4---theoretical-analysis)
- [5 - Emperical Analysis](#5---emperical-analysis)
- [6 - Results and Discussion](#6---results-and-discussion)
- [6 - Conclusion](#6---conclusion)
- [7 - Future Work](#7---future-work)
- [8 - References](#8---references)

<!-- /TOC -->

# Final Report - Path Finders and A* Algorithm
* **Author**: Pranchal Shah
* **GitHub Repo**: [5008 Repo](www.github.com/cyber-shah/5008-Project)
* **Semester**: Summer 2023
* **Languages Used**: C and Python

<!-- TODO : check time complexities -->

# 0 - Abstract

This report aims to comprehensively explore the implementation and performance of key pathfinding algorithms: Depth First Search, Breadth First Search, Dijkstra's Algorithm, and A* Algorithm. Each algorithm will be scrutinized in terms of theoretical foundations, practical implementation details, rigorous testing, and insightful discussions. The overarching goal is to gain a profound understanding of these algorithms' inner workings and their efficacy across various scenarios.


The report delves into the nuances of each algorithm, dissecting their strengths and weaknesses, and uncovering their optimal use cases. Thorough testing is conducted using a diverse set of criteria, including shortest path determination, nodes explored, time and memory utilization, and adaptability to distinct graph configurations. The selected testing conditions encompass a spectrum of scenarios, ranging from straightforward weighted graphs to intricate mazes replete with obstacles.

As we progress through this exploration, the report will provide clarity on the reasons behind the chosen testing criteria and conditions, grounding our analysis in a solid rationale. Through this holistic investigation, readers will gain not only a deep comprehension of these fundamental pathfinding techniques but also insights into their applicability across real-world situations.

<!-- # TODO : describe why these criterias were chosen -->
The algorithms are tested on the following criteria:
1. Shortest path
2. Nodes explored to find the shortest path
3. Time taken to find the shortest path
4. Memory used to find the shortest path
5. Performance on different graphs
6. Performance on different grid sizes
7. Performance on different obstacles
8. Performance on different edge weights
9. Variying start and end nodes

The algorithms are tested on the following conditions:
1. A simple graph weighted edges
2. A grid of nodes each with uniform edge weight and no obstacles and a pretty straight forward shortest path
3. A maze with obstacles and many shorter paths
<!-- # TODO : add a concise summary of the results -->

# 1 - Introduction

### <u> 1.1 - Why Path Finding? </u>
Path finding has been a very important problem in computer science. It has been used in many applications such as logistics planning, least cost call or IP routing, and gaming simulation. 
I got really intrested into it when I learned that a logistics company (UPS) collceted years of data and used algorithms to navigate better.
UPS says it’s saved 10 million gallons of fuel, avoided the emission of 20,000 tons of CO2, and delivered 350,000 more packages a year, just due to efficient path finding!
More on that here: [Science Behind UPS Trucks!](https://bigthink.com/technology-innovation/the-science-behind-why-ups-trucks-avoid-making-left-turns/)

I was also intrested in it because I am a largely intrested in video games and I wanted to learn how video games work. I also wanted to learn how self driving cars work. I learned that path finding is a very important part of both of these applications and it solves a very important problem. A problem that we as humans encounter almost everyday.


<img src = https://happycoding.io/tutorials/libgdx/images/pathfinding-12.png> 

Image Source: [Happy Coding/ Path finding](https://happycoding.io/tutorials/libgdx/pathfinding)

### <u> 1.2 - What is Path Finding? </u>
Path finding is the process of finding a path between two points in a graph.
A graph is a data structure that consists of nodes and edges. The nodes are the points in the graph and the edges are the connections between the nodes. The edges can be weighted or unweighted. The weight of an edge is the cost of traversing that edge. The cost can be anything such as distance, time, fuel, etc. The edges can also be directed or undirected. The directed edges are one way and the undirected edges are two way.

The goal of path finding is to find the shortest path between two nodes in a graph. The path can be defined as the sequence of nodes that need to be traversed to reach the destination node from the source node.  

### <u> 1.3 - How do computers find paths? </u>
A computer starts at a souce node and then starts 'scanning' or 'exploring' the graph. It scans the graph by exploring the nodes and edges. It explores the nodes and edges by traversing them. The computer traverses the nodes and edges by following the edges. The computer follows the edges by selecting the edge with the lowest cost. 

An analogy to it would be like a blind person trying to find the shortest path between two points. Instead of using his eyes to see the path, he uses his hands to feel the path. He follows on one path and then feels the edges to see if there is a better path. If there is a better path, he follows that path. He keeps doing this until he reaches the destination.

### <u> 1.4 Algorithms covered </u>
The algorithms covered in this report are:
1. Depth First Search
2. Breadth First Search
3. Dijkstra's Algorithm
4. A* Algorithm







# 2 - Background
Graph traversal algorithms form the backbone of various computational processes, from deciphering networks to enabling efficient pathfinding. These algorithms are instrumental in navigating the intricate web of connections that graphs represent. One fundamental class of graph traversal algorithms includes Depth First Search (DFS), which excels in exploring the depths of a graph's structure.

In this section, we will delve into the theoretical foundations of DFS, its operational principles, and its applicability across various scenarios. By understanding DFS in the context of graph traversal algorithms, we can appreciate its unique strengths and limitations as we delve deeper into its mechanics.

Each algorithm is divided into the following sections:
1. History
2. Overiew of how it works
3. Advanatages
4. Disadvantages
5. Complexity
6. Use cases
7. Psuedo code

The algorithms can be divided into two parts here: 1 that help us find out IF a path exists and 2 that help us find the SHORTEST path. The algorithms that help us find out IF a path exists are: DFS and BFS. The algorithms that help us find the SHORTEST path are: Dijkstra's Algorithm and A* Algorithm.

## Question 1: Is there a path between node A and node B?
The two algorithms that help us find out IF a path exists are: DFS and BFS. 
> All the GIFs shown in this section are made by me, unless  otherwise stated. 
>  Kindly note that the algorithms have been implemented by me and pygame is used to create animations. The pygame animation code can be found here : [pyGame_Vizs.py](vizs/pyGame_Vizs.py)

### 1.1 - Depth First Search
   1. **_History_**: 
      - Depth First search dates back to 19th century. It was first used by French mathematician Charles Pierre Trémaux as a strategy for solving mazes. 
      - DFS is was pretty much the background for most of the modern day path finding algorithms. From Bellman Ford to Dijkstra to A* to Prim's Algorithm, have all built on top of DFS.
      - It's simplicity and efficiency makes it a very popular algorithm. It is also very easy to implement.
   2.  **Overview of how it works_**:  
       - Depth First Search is a graph traversal algorithm that starts at a source node and explores the graph by traversing the edges. 
       - It follows a single path until it reaches a dead end. It then backtracks to the previous node and explores the next path. It keeps doing this until it reaches the destination node. 
       - It then backtracks from the destination node to the source node to find the shortest path. It uses a stack to keep track of the nodes that need to be explored. It uses a parent array to keep track of the path. The parent array is used to backtrack from the destination node to the source node. The parent array is also used to find the number of nodes explored to find the shortest path.
       - ![DFS-basic](view/graphics/basic-DFS.gif)
   3.  **_Advantages_**: 
       - The advantage of DFS is that it is very simple to implement. 
       - It is also very fast and uses very little memory. 
       - It can quickly determine if a graph has cycles.
   4. **_Disadvantages_**: 
      - It does not guarantee the shortest path. 
      - It can get stuck in a loop. 
      - The other disadvantage is that it may keep moving down the wrong path and may be too late to backtrack towards the right path.
   5. **_Complexity_**:
      | Approach | Time Complexity | Space Complexity |
      | --- |----------------| --- |
      | Adjacency Matrix | $O(V^2)$        | $O(V)$ |
      | Adjacency List | $O(V + E)$           | $O(V + E)$ |
      The implementation in this report uses an adjacency matrix, just because it is easier to implement.
   6. **_Use Cases_**:
      - DFS is used in maze generation algorithms.
      - It is also used in topological sorting.
      - DFS is used in finding connected components in a graph.
      - It is also used in cycle detection in a graph.
   7. **_Psuedocode_**:
      ```
      DFS(Graph,vertex v)
         Stack S
         for each vertex u, set visited[u] := false;
         push S, v;
         while (S is not empty) do
            u := pop S;
            if (not visited[u]) then
               visited[u] := true;
               for each unvisited neighbour w of u
                  push S, w;
      ```


## Question 2 : What is the shortest path between A to B?
<!-- TODO : move the use cases here -->
So by now we know how to solve the question of `is there a path between A to B?`. Now the next two algorithms will help us solve the question of `what is the shortest path between A to B?`.
### 2.1 - Breadth First Search
1. **_History_**:  
      - Breadth First Search was invented by Konrad Zuse in 1945. 
      - It was later rediscovered by Edsger Dijkstra in 1959. 
      - It is also known as the `Breadth First Traversal` or `Breadth First Walk`.
      - It is a graph traversal algorithm that starts at a source node and explores the graph by traversing the edges.
      - BFS is a core algorithm in computer science and is widely used in fields like network routing, social network analysis, and more.
2.  **_Overview of how it works_**: 
       - It operates in a similar way to DFS, but it uses a queue instead of a stack. 
       - It starts from the source node and explores it neighbours in `layers`. Layers are nothing but the nodes that are at a distance of `n` from the source node. So the first layer contains nodes that are at a distance of 1 from the source node. The second layer contains nodes that are at a distance of 2 from the source node and so on.
       - It explores the first layer first, then the second layer and so on. This means that nodes closer are explored first and nodes farther away are explored later.
   ![BFS-basic](view/graphics/basic-BFS.gif)
3. **_Advantages_**: 
      - BFS is guaranteed to find the shortest path between the source node and the destination node.
      - If the graph is connected, then BFS can be used to find the shortest path between all the nodes in the graph.
      - It explores the graph in layers which can be useful if we need to analyse the neighbours.
      - It does not visit a node more than once.
4. **_Disadvantages_**: 
      - It is less suitable for weighted graphs. In a weighted graph, where edges have different costs or distances, the standard BFS will not necessarily find the shortest path. This is because BFS explores nodes in layers, and the order in which nodes are explored might not correspond to the shortest path.
      - It uses more memory than DFS.
5. **_Complexity_**:
      | Approach | Time Complexity | Space Complexity |
      | --- |----------------| --- |
      | Adjacency Matrix | $O(V^2)$        | $O(V)$ |
      | Adjacency List | $O(V + E)$           | $O(V + E)$ |
      The implementation in this report uses an adjacency matrix, just because it is easier to implement.
6. **_Use Cases_**:
   - BFS is used in shortest path algorithms, especially when the graph is unweighted or equally weighted.
   - It is employed for finding the shortest path between nodes in a graph.
   - BFS can be used to explore hierarchical structures or levels of depth in graphs.
   - It's useful for determining connected components in a graph.
7. **_Psuedocode_**:
      ```
      BFS(Graph, start_vertex)
         Queue Q
         visited = set()
         enqueue Q, start_vertex
         visited.add(start_vertex)
         while Q is not empty do
            vertex = dequeue Q
            for each neighbor w of vertex do
               if w is not in visited then
                  visited.add(w)
                  enqueue Q, w
      ```
### 2.2 - Dijkstra's Algorithm
1. **_History_**:  
      - Dijkstra's Algorithm was invented by Edsger Dijkstra in 1956. The technique first appeared as a solution to the problem of finding the shortest path between nodes in a weighted graph.
      - It has been widely used in real-world applications, including routing in computer networks and navigation systems.
      - It is also known as the `Shortest Path First Algorithm`.
      - It was invented to solve the problem of finding the shortest path between nodes in a weighted graph.
2. **_Overview of how it works_**:
      - Dijkstra's Algorithm is a graph traversal algorithm that starts at a source node and explores the graph by traversing the edges. It is mainly used to find shortest path between nodes in a weighted graph. 
      - It is similar to BFS, but uses a concept called `edge relaxation`. Which means that it relaxes the edges of the graph by updating the distance of the nodes.
      - While visiting nodes it maintains a distance array which stores the distance of each node from the source node. Initially the distance of all the nodes is set to infinity. And the distance of the source node is set to 0. 
      - These distances are updated as the algorithm progresses.
      - The process ends when all the nodes have been visited and returns a list of shortest distances from the source node to all the other nodes.   
      <img src = https://upload.wikimedia.org/wikipedia/commons/e/e4/DijkstraDemo.gif>   
      Image source : [Wikimedia Commons](https://commons.wikimedia.org/wiki/File:DijkstraDemo.gif") 
3. **_Advantages_**: 
   - Dijkstra's Algorithm is guaranteed to find the shortest path between the source node and the destination node.
   - The basic concept is straightforward to understand and implement.
   - It is also very efficient.
4. **_Disadvantages_**:
   - Limited to Positive Weights: Dijkstra's algorithm is not well-suited for graphs with negative edge weights, as negative cycles can lead to incorrect results or cause the algorithm to fail.
   - Inapplicability to Negative Weights: The algorithm's reliance on selecting the minimum distance can break down when dealing with graphs containing negative edge weights.
   - Potential Inefficiency on Large Graphs: While Dijkstra's algorithm provides optimal solutions, it might not be the most efficient choice for large graphs due to its time complexity.
5. **_Complexity_**:
      | Approach | Time Complexity | Space Complexity |
      | --- |----------------| --- |
      | Adjacency Matrix | $O(V^2)$        | $O(V)$ |
      | Priority Queues | $O((V + E) * log(V))$           | $O(V)$ |
6. **_Use Cases_**:
   - Dijkstra's algorithm is used in shortest path algorithms, especially when the graph is weighted.
   - Routing and navigation systems use Dijkstra's algorithm to find the shortest path between two locations.
   - Network routing protocols use Dijkstra's algorithm to find the shortest path between two nodes in a network.
   - Resource Management and Allocation use Dijkstra's algorithm to find the shortest path between two resources.
7. **_Psuedocode_**:
      ```
      1. Initialize all nodes with distance INFINITY, except the source node with distance 0.
      2. Create a priority queue (min-heap) and insert the source node with distance 0.
      3. While the priority queue is not empty:
         3.1 Extract the node with the minimum distance from the priority queue.
         3.2 For each adjacent node of the extracted node:
               3.2.1 Calculate a tentative distance by adding the edge weight to the extracted node's distance.
               3.2.2 If the tentative distance is less than the current distance of the adjacent node:
                     3.2.2.1 Update the adjacent node's distance with the tentative distance.
                     3.2.2.2 Enqueue the adjacent node into the priority queue.
      4. The distances now hold the shortest paths from the source node to all other nodes.
      ```
### 2.3 - A* Algorithm
1.  **_History_**:
      - A* was created by Peter Hart, Nils Nilsson and Bertram Raphael of Stanford Research Institute (now SRI International) in 1968. It was first published in the 1968 paper "A Formal Basis for the Heuristic Determination of Minimum Cost Paths".
      - It was created to solve the problem of finding the shortest path between nodes in a weighted graph, in a more efficient way.
      - It is a combination of Dijkstra's Algorithm and Greedy Best First Search.
2. **_Overview of how it works_**:
      - A* shares similarities with Dijkstra's Algorithm but introduces a pivotal concept known as `heuristics`.
      - Heuristics involve estimating the distance between two nodes, specifically between the current node and the destination node. This estimation guides the algorithm in its exploration.
      - Unlike exhaustive searches, heuristic algorithms, like A*, selectively explore the most promising routes. They leverage an educated guess to determine which path holds the greatest potential.
      - In the animation below, the algorithm prioritizes promising routes, resulting in more efficient exploration while disregarding less viable paths.
      ![A*](view/graphics/basic-Astar.gif)
3. **_Advantages_**: 
      - A* boasts notable efficiency due to its effective utilization of heuristics.
     - Through the application of heuristics, A* excels at efficiently finding the shortest path in weighted graphs, often outperforming Dijkstra's Algorithm.
      - Notably, A* guarantees the discovery of the shortest path between the source and destination nodes when an admissible and consistent heuristic is employed.
4. **_Disadvantages_**:
      - A* does have limitations. Its guarantee of finding the shortest path hinges on using an admissible, consistent, and monotonic heuristic.
      - Other disadvantage include the fact that it is not well-suited for graphs with negative edge weights, as negative cycles can lead to incorrect results or cause the algorithm to fail.
5. **_Complexity_**:
      | Approach | Time Complexity | Space Complexity |
      | --- |----------------| --- |
      | Adjacency Matrix | $O(V^2)$        | $O(V)$ |
      | Priority Queues | $O((V + E) * log(V))$  | $O(V)$ |
6. **_Use Cases_**:
      - Telecommunication networks to optimize call routing.
     - GPS navigation systems for optimal route planning.
     - Network routing to minimize data transmission costs.
     - Pathfinding in video games and robotics.


# 3 - Implementation Details
Initially, the decision was made to implement all the algorithms in C, driven by concerns related to speed and efficiency. Given its status as a low-level programming language, C emerged as the natural choice for this purpose. The complete C implementation can be accessed within the [c-code](c-code) directory. 
 
However, as the implementation of Dijkstra's algorithm progressed, the necessity to subject the algorithms to rigorous testing across a spectrum of mazes and scenarios became apparent. This testing was intended to evaluate their performance under diverse conditions. To facilitate this assessment, the aspiration emerged to create animations that would offer insights into how the algorithms explore nodes, quantify the number of nodes each algorithm traverses before identifying the shortest path, and much more. In essence, the goal was to **visualize** the algorithms' behavior.

> In general, all the python implementations are higher in completeness and functionality than the C implementations. A lot of functionality was added to the python implementations while the C implementations were being ported to python. So python implementations are an improvement over the C implementations.

This is because I started with the C implementations and then moved to Python. So the C implementations are not as complete as the Python implementations. But they help in understanding the memory management and the data structures used in the Python implementations.

However, as the implementation of Dijkstra's algorithm progressed, the necessity to subject the algorithms to rigorous testing across a spectrum of mazes and scenarios became apparent. This testing was intended to evaluate their performance under diverse conditions. To facilitate this assessment, the aspiration emerged to create animations that would offer insights into how the algorithms explore nodes, quantify the number of nodes each algorithm traverses before identifying the shortest path, and much more. In essence, the goal was to visualize the algorithms' behavior.

Therefore, the shift to Python was motivated by the intention to holistically comprehend algorithmic behavior through animations, leveraging Python's high-level capabilities and visualization tools, while the initial implementation in C addressed concerns of speed and efficiency.

## 3.0 - Data Structures

Initially the structs/classes that hold the graphs and nodes were based on XY coordinate system, however when I started using libraries like Matplotlib and Pygame, I realized that it would be easier to use a matrix to represent the graph. So I changed the structs/classes to use a matrix instead of XY coordinates.

### 3.0.1 - Nodes
The node data structure is responsible to hold the information about the node, heuristics, distance, parent and position. The node is represented as a struct in C and a class in Python. The C implementation of the node can be found here [Node in C](c-code/structs/Node.h).

```C
struct Node {
    char *name;
    int index;
    int distance;
} typedef Node;
```

The Python implementation of the node can be found here [Node in Python](model/Node.py). It holds the following attributes:
- `index` - The index of the node in the graph.
- `name` - name of the node.
- `parent` - The parent node of the current node.
- `g` - cost from the start node to the current node
- `h` - The heuristic value of the node.
- `f` - total estimated cost of the node. $f(n) = g(n) + h(n)$

> A noteable difference between the C and Python implementations is the python implementations are a lot more object oriented. The C implementations are more procedural. Apart from that they are also a big upgrade in terms of functionality and completeness.

```python
class NodeRC:

    def __init__(self, name,
                 row=None, column=None, parent=None,
                 g=None, h=None, f=None):
        self.index = None
        self.name = name
        self.parent = parent
        self.g = g
        self.h = h
        self.f = f
        if row is None or column is None:
            self.row = 0
            self.column = 0
        else:
            self.row = row
            self.column = column
```
Apart from getters and setters for all the attributes, the class also has the a method called `get_neighbors` that returns a list of nodes that are connected to the current node.
```Python
    def get_neighbors(self, graph):
        # a list of nodes index
        neighbors = []
        for i in range(graph.number_of_nodes):
            if graph.adjacency_matrix[self.index][i] != float('inf') and i != self.index:
                neighbors.append(graph.get_node_via_index(i))
        return neighbors
        
```

> Another major difference is the use of `rows` and `columns` to represent the nodes instead of using XY coordinates. This was done to make it easier to use libraries like Matplotlib and Pygame to visualize the graph. This decision was made after the C implementation was completed. The C implementation uses XY coordinates to represent the nodes.

### 3.0.2 - Graphs
The graph data structure is responsible to hold the nodes and edges. The graph is represented as a matrix, where each row and column represents a node. The value of the matrix at the row and column index represents the weight of the edge between the two nodes. If the value is `inf`, it means that there is no edge between the two nodes.
The C implementation of the graph can be found here [Graph in C](c-code/structs/Graph.h).
```C
struct Graph {
    int numberOfNodes;
    int adjacencyMatrix[MAX_NODES][MAX_NODES];
    Node *nodes[MAX_NODES];
} typedef Graph;
```

The python implementation of the graph can be found here [Graph in Python](model/Graph.py). It holds the following attributes:
``` Python
class GraphRC:
    def __init__(self):
        self.number_of_nodes = 0
        self.number_of_edges = 0
        # Use a list of lists for the adjacency matrix
        self.adjacency_matrix = []
        # Use a dictionary key, value = (x pos, y pos), node
        self.nodesDictionary = {}
        # Use a dictionary key, value = index, node
        self.nodeIndices = {}
        # Use a dictionary key, value = name, node
        self.nodeNames = {}
```

Apart from getters and setters for all the attributes, the class also has the following methods:


One notable difference was the use of a dictionary to store the nodes. This was done to make it easier to access the nodes. The dictionary uses the node name as the key and the node object as the value. This makes it easier to access the node object using the name of the node. The dictionary can be accessed using the `nodeNames` attribute of the graph object.



  
  - Graphs for C : [Graph.h](c-code/structs/Graph.h)
  - Nodes for Python : [Node.py](model/model_old/Node_old.py)
  - Nodes for C : [Node.h](c-code/structs/Node.h)
- Priority Queue
- Stack
- Nodes
- Queue

## 3.1 - Depth First Search

- C implementation can be found here [DFS in C](c-code/algorithms/DFS.h)
- 🐍 Python implementation can be found here [DFS in Python](algorithms/DFS.py)

### Recursive Approach in C

The C implementation of DFS was based on recursion. The recursive approach was selected due to its simplicity and elegance. There's a helper function that is called recursively to visit all the nodes in the graph. The helper function takes in the graph, the source node index, and a boolean array that keeps track of the visited nodes.
The recursive implementation can be found below:

```C
void dfsHelper(Graph *graph, int sourceNodeIndex, bool visited[]) {
    // base case - set the node to visited
    visited[sourceNodeIndex] = true;

    // recursive case - if there is an adjacent node that is not visited, then visit it
    for (int i = 0; i < graph->numberOfNodes; ++i) {
        if (graph->adjacencyMatrix[sourceNodeIndex][i] != INF && !visited[i]) {
            dfsHelper(graph, i, visited);
        }
    }
}

void dfs_recursive(Graph *graph, int sourceNodeIndex) {
    bool visited[MAX_NODES] = {false};
    dfsHelper(graph, sourceNodeIndex, visited);
}
```

### Stack Approach in Python

The approach to implementing DFS was to leverage a stack data structure to store the nodes that were visited. The stack was implemented as a linked list, with each node containing a pointer to the next node in the stack. The stack was initialized with the source node, and the algorithm proceeded to pop nodes from the stack, marking them as visited, and pushing their unvisited neighbors onto the stack. This process continued until the destination node was reached, or the stack was empty. If the destination node was reached, the algorithm would backtrack to the source node, marking the shortest path. If the stack was empty, the algorithm would terminate, indicating that no path existed between the source and destination nodes.

```Python
def dfs_destination(graph, source_node_name, destination_node_name):
    source_node_index = (graph.get_node_via_name
                         (source_node_name).index)
    destination_node_index = (graph.get_node_via_name
                              (destination_node_name).index)

    stack = deque()
    visited = [False] * graph.number_of_nodes
    distances_list = []  # to store the order of visited nodes
    parent_map = {}  # To store parent nodes for backtracking

    visited[source_node_index] = True
    stack.append(source_node_index)
    distances_list.append(source_node_index)

    while stack:
        current_node_index = stack.pop()

        if current_node_index == destination_node_index:
            break

        for i in range(graph.number_of_nodes):
            if graph.adjacency_matrix[current_node_index][i] != float('inf') and not visited[i]:
                visited[i] = True
                stack.append(i)
                distances_list.append(i)
                parent_map[i] = current_node_index  # Store parent information

    if destination_node_index not in parent_map:
        return distances_list, []

    shortest_path = build_shortest_path(parent_map, source_node_index, destination_node_index)
    return distances_list, shortest_path
```

Then the function called `build_shortest_path` is called to create a list of indices of nodes that represent the shortest path between the source and destination nodes. The function takes in the parent map, the source node index, and the destination node index. The function iterates through the parent map, starting from the destination node, and appends the current node index to the shortest path list. The function terminates when the current node index is equal to the source node index. The shortest path list is then reversed to obtain the correct order of nodes.

```Python
def build_shortest_path(parent_map, source_node_index, destination_node_index):
    current_node_index = destination_node_index
    shortest_path = []

    while current_node_index != source_node_index:
        shortest_path.append(current_node_index)
        current_node_index = parent_map[current_node_index]

    shortest_path.append(source_node_index)
    shortest_path.reverse()
    return shortest_path
```


<!-- TODO : maybe insert tests -->

## 3.2 - Breadth First Search
 
- C implementation can be found here [BFS in C](c-code/algorithms/BFS.h)
- 🐍 Python implementation can be found here [BFS in Python](algorithms/BFS.py)


# 4 - Theoretical Analysis

# 5 - Emperical Analysis

# 6 - Results and Discussion

<!-- ![20x20DFS](view/DFS-20x20.gif) -->
# 6 - Conclusion

# 7 - Future Work

# 8 - References



