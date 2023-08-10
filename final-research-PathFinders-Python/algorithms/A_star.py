import heapq


def a_star_destination(graph, source_node_name, destination_node_name):
    """
    there's two ways algorithm can be completed
    1. destination node is found
    2. open_unvisited_set is empty - no path to destination node

    closed set starts as empty set, and then just grows
    start with only the source node in it and then add nodes to it as we visit them

    psuedocode:
    1. Create an open_unvisited_set (priority queue) to store nodes to be explored.
    2. Create a closed_visited_set (set) to store nodes that have been visited.
    3. Add the starting node to the open_unvisited_set.
    4. while the open_unvisited_set is not empty
        a. Get the current node from the open_unvisited_set with the lowest f_score.
        b. Remove the current node from the open_unvisited_set and add it to the closed_visited_set.
        c. If the current node is the destination node, terminate the loop.
        d. Else, for each neighbor of the current node:
            i. Calculate tentative_g = current_node.g + distance from current node to neighbor
            ii. If neighbor in closed_visited_set and the tentative_g > neighbor.g
                1. skip this iteration.
            iii. If the neighbor not in open_unvisited_set or tentative_g_score < neighbor.g:
                1. Set neighbor.g = tentative_g (update neighbor.g)
                2. Set neighbor.h = distance from neighbor to destination node (update heuristic)
                3. Set neighbor.f = neighbor.g + neighbor.h (update neighbor.f)
                4. Set neighbor.parent = current_node (update parent)
                5. If neighbor not in open_unvisited_set:
                    a. Add neighbor to open_unvisited_set.
    5. Once the loop terminates:
        a. If the destination node has been visited:
            i. reconstruct the path from destination node to source node using parent pointers.
        b. Else:
            i. No path exists from source node to destination node.
    """
    # instantiate ____________________________________________________
    open_unvisited_set = []  # unvisited nodes
    closed_visited_set = set()  # visited nodes

    # Getters ________________________________________________________
    source_node_index = graph.getNodeIndex(source_node_name)
    destination_node_index = graph.getNodeIndex(destination_node_name)
    source_node = graph.getNode(source_node_index)
    destination_node = graph.getNode(destination_node_index)

    # Setters ______________________________________________________
    source_node.g = 0
    source_node.h = heuristic(source_node, destination_node_index)
    source_node.f = source_node.g + source_node.h
    heapq.heappush(open_unvisited_set, (source_node.f, source_node))

    # while there are unvisited nodes
    while open_unvisited_set:
        # get the current node from the open_unvisited_set with the lowest f_score
        f, current_node = heapq.heappop(open_unvisited_set)
        open_unvisited_set.remove(current_node)
        closed_visited_set.add(current_node)

        # if destination node is found, break the loop
        if current_node == destination_node_index:
            break

        # else visit all the UNVISITED adjacent nodes of current node
        for neighbor in graph.getNeighbors(current_node):
            if neighbor not in closed_visited_set:
                # calculate tentative_g
                tentative_g = current_node.g + graph.getEdgeWeight(current_node, neighbor)
                # if neighbor is visited and the tentative_g > neighbor.g
                if neighbor in closed_visited_set and tentative_g > neighbor.g:
                    continue
                # if the neighbor not in open_unvisited_set or tentative_g_score < neighbor.g:
                elif neighbor not in open_unvisited_set or tentative_g < neighbor.g:
                    neighbor.g = tentative_g
                    neighbor.h = heuristic(neighbor, destination_node_index)
                    neighbor.f = neighbor.g + neighbor.h
                    neighbor.parent = current_node
                    if neighbor not in open_unvisited_set:
                        heapq.heappush(open_unvisited_set, (neighbor.f, neighbor))

    # return the path
    path = []
    current = destination_node
    while current is not None:
        path.append(current)
        current = current.parent
    return path[::-1]


def heuristic(source_node, goal_node):
    return abs(source_node.x - goal_node.x) + abs(source_node.y - goal_node.y)
