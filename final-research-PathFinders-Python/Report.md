<!-- TOC -->

- [Final Report - Path Finders and A\* Algorithm](#final-report---path-finders-and-a-algorithm)
- [0 - Abstract](#0---abstract)
- [1 - Introduction](#1---introduction)
    - [ 1.1 - Why Path Finding? ](#-11---why-path-finding-)
    - [ 1.2 - What is Path Finding? ](#-12---what-is-path-finding-)
    - [ 1.3 - How do computers find paths? ](#-13---how-do-computers-find-paths-)
    - [ 1.4 Algorithms covered ](#-14-algorithms-covered-)
- [2 - Background and Theory](#2---background-and-theory)
  - [2.1 - Depth First Search](#21---depth-first-search)
  - [2.2 - Breadth First Search](#22---breadth-first-search)
  - [2.3 - Dijkstra's Algorithm](#23---dijkstras-algorithm)
  - [2.4 - A\* Algorithm](#24---a-algorithm)
- [3 - Implementation Details](#3---implementation-details)
- [4 - Testing and Validation](#4---testing-and-validation)
- [5 - Results and Discussion](#5---results-and-discussion)
- [6 - Conclusion](#6---conclusion)
- [7 - Future Work](#7---future-work)
- [8 - References](#8---references)

<!-- /TOC -->

# Final Report - Path Finders and A* Algorithm
* **Author**: Pranchal Shah
* **GitHub Repo**: [5008 Repo](www.github.com/cyber-shah/5008-Project)
* **Semester**: Summer 2023
* **Languages Used**: C and Python

# 0 - Abstract

The purpose of this report is to discuss the implementation of various path finding algorithms and their performance. The algorithms discussed in this report are Depth First Search, Breadth First Search, Dijkstra's Algorithm and A* Algorithm. The report will discuss the theory behind each algorithm, their implementation details, testing and validation, results and discussion, conclusion and future work. The report will also discuss the performance of each algorithm and compare them with each other.

The report will also discuss the pros and cons of each algorithm and their use cases. The report will also discuss the performance of each algorithm in different scenarios and compare them with each other. The goal is to understand the implementation details of each algorithm and their performance in different scenarios.

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


# 2 - Background and Theory


## 2.1 - Depth First Search
   - Concept: Depth First Search is a graph traversal algorithm. It starts at a source node and explores the graph by traversing the edges. It traverses the edges by following the edges with the lowest cost. It keeps doing this until it reaches the destination node. It uses a stack to keep track of the nodes that it has visited. It also uses a parent array to keep track of the parent of each node. The parent array is used to find the path. The parent array is used to backtrack from the destination node to the source node. The parent array is also used to find the number of nodes explored to find the shortest path.
   - Advantages: The advantage of DFS is that it is very simple to implement. It is also very fast and uses very little memory. It is also very easy to visualize. It is also very easy to implement in a grid. It is also very easy to implement in a maze.
   - Disadvantages:
   - Complexity Table : 
   - Use Cases:
   - Pseudocode:


## 2.2 - Breadth First Search

## 2.3 - Dijkstra's Algorithm

## 2.4 - A* Algorithm

| Approach | Time Complexity | Space Complexity |
| --- |----------------| --- |
| Depth First Search | $O(n)$          | $O(1)$ |
| Breadth First Search | $O(2^n)$        | $O(n)$ |
| Djikstra | $O(n)$           | $O(n)$ |
| A* | $O(n)$           | $O(n)$ |

# 3 - Implementation Details
![20x20Djikstra](viz/Djikstra-20x20.gif)

# 4 - Testing and Validation

# 5 - Results and Discussion

![20x20DFS](viz/DFS-20x20.gif)
# 6 - Conclusion

# 7 - Future Work

# 8 - References



