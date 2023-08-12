import heapq


def dijkstra(graph, source_node_name):
    """
    This function is the implementation of the Dijkstra algorithm.
    here distance = sum of weights of edges from source node to the current node.

    1. find the node with the minimum distance to visit next.
    2. Loop over all of its adjacent nodes.
        2.1 Relax all adjacent unvisited nodes. WHY?
            because we visit a node only when we have found the shortest path to it
            and there is no other shorter path.
        2.2 If the new distance is less than the current distance, update the distance.
    3. Repeat steps 1 and 2 until all the nodes are visited.
    """
    # get total and index of source node ____________________________________________
    total_nodes = graph.number_of_nodes
    source_index = graph.get_node_via_name(source_node_name).get_index()

    # Initialize the visited and distance list _______________________________________
    visited_list = [False] * total_nodes

    distance_list = [float('inf')] * total_nodes
    distance_list[source_index] = 0
    # init the distance list by copying the weights from the graph
    # all distances are from the source node
    for i in range(total_nodes):
        source_node = graph.get_node_via_index(source_index)
        destination_node = graph.get_node_via_index(i)
        if i != source_index and graph.get_edge(source_node, destination_node) != float('inf'):
            distance_list[i] = graph.get_edge(source_node, destination_node)

    # DIJKSTRA starts here ________________________________________________________________
    for i in range(total_nodes):
        # 1. find the node with the minimum distance to visit next.
        min_distance_node = get_min_distance_node(visited_list, distance_list)
        # Mark the node as visited
        visited_list[min_distance_node] = True

        # 2. Loop over all of its adjacent nodes.
        for j in range(total_nodes):
            source_node = graph.get_node_via_index(min_distance_node)
            destination_node = graph.get_node_via_index(j)
            edge_weight = graph.get_edge(source_node, destination_node)
            # 2.1 Relax all adjacent unvisited nodes.
            if not visited_list[j] and edge_weight != float('inf'):
                # 2.2 If the new distance is less than the current distance, update the distance.
                if distance_list[min_distance_node] + edge_weight < distance_list[j]:
                    distance_list[j] = distance_list[min_distance_node] + edge_weight

    return distance_list


def dijkstra_path(graph, source_node_name, destination_node_name):
    """
    This function is the implementation of the Dijkstra algorithm.
    here distance = sum of weights of edges from source node to the current node.

    1. find the node with the minimum distance to visit next.
    2. Loop over all of its adjacent nodes.
        2.1 Relax all adjacent unvisited nodes. WHY?
            because we visit a node only when we have found the shortest path to it
            and there is no other shorter path.
        2.2 If the new distance is less than the current distance, update the distance.
    3. Repeat steps 1 and 2 until all the nodes are visited.
    """
    # get total and index of source node ____________________________________________
    total_nodes = graph.number_of_nodes
    source_index = graph.get_node_via_name(source_node_name).get_index()
    destination_index = graph.get_node_via_name(destination_node_name).get_index()
    source_node = graph.get_node_via_index(source_index)

    # Initialize the priority queue and distance list ______________________________________
    priority_queue = [(0, source_index)]  # (distance, node_index)
    visited_list = [False] * total_nodes
    # distance list init
    distance_list = [float('inf')] * total_nodes
    for neighbor in source_node.get_neighbors(graph):
        neighbor_index = neighbor.get_index()
        distance_list[neighbor_index] = graph.get_edge(source_node, neighbor)
        heapq.heappush(priority_queue, (distance_list[neighbor_index], neighbor_index))
    distance_list[source_index] = 0

    explored_nodes_indexes = []
    previous_nodes = [-1] * total_nodes

    # DIJKSTRA starts here ________________________________________________________________
    while priority_queue:
        # Extract the node with the minimum distance from the priority queue
        current_distance, current_node_index = heapq.heappop(priority_queue)
        explored_nodes_indexes.append(current_node_index)
        current_node = graph.get_node_via_index(current_node_index)
        visited_list[current_node_index] = True

        # If the destination node is visited, we can stop the algorithm
        if current_node_index == destination_index:
            break

        # 2. Loop over all of its adjacent nodes.
        for neighbour in current_node.get_neighbors(graph):
            neighbour_index = neighbour.get_index()
            edge_weight = graph.get_edge(current_node, neighbour)
            new_distance = distance_list[current_node_index] + edge_weight
            neighbor_distance = distance_list[neighbour_index]

            # 2.1 Relax all adjacent unvisited nodes.
            if visited_list[neighbour_index] is False and neighbour_index != source_index:
                if new_distance < neighbor_distance:
                    # 2.2 If the new distance is less than the current distance, update the distance.
                    heapq.heappush(priority_queue, (new_distance, neighbour_index))
                    previous_nodes[neighbour_index] = current_node_index
                    distance_list[neighbour_index] = new_distance

    shortest_path = []
    current_node = destination_index
    while current_node != -1:
        shortest_path.insert(0, current_node)
        current_node = previous_nodes[current_node]

    return distance_list, explored_nodes_indexes, shortest_path


def dijkstra_old(graph, source_node_name, destination_node_name):
    total_nodes = graph.number_of_nodes
    source_index = graph.get_node_via_name(source_node_name).get_index()
    destination_index = graph.get_node_via_name(destination_node_name).get_index()

    # Initialize the visited and distance list _______________________________________
    visited_list = [False] * total_nodes

    distance_list = [float('inf')] * total_nodes
    distance_list[source_index] = 0
    # init the distance list by copying the weights from the graph
    # all distances are from the source node
    for i in range(total_nodes):
        source_node = graph.get_node_via_index(source_index)
        destination_node = graph.get_node_via_index(i)
        if i != source_index and graph.get_edge(source_node, destination_node) != float('inf'):
            distance_list[i] = graph.get_edge(source_node, destination_node)

    # for getting the path and explored nodes ____________________________________________
    explored_nodes_indexes = []
    previous_nodes = [-1] * total_nodes  # Initialize with -1, meaning no previous node

    # DIJKSTRA starts here ________________________________________________________________
    for i in range(total_nodes):
        # 1. find the node with the minimum distance to visit next.
        min_distance_node_index = get_min_distance_node(visited_list, distance_list)
        # Mark the node as visited
        visited_list[min_distance_node_index] = True
        explored_nodes_indexes.append(min_distance_node_index)
        min_distance_node = graph.get_node_via_index(min_distance_node_index)

        # If the destination node is visited, we can stop the algorithm
        if min_distance_node_index == destination_index:
            break

        # 2. Loop over all of its adjacent nodes.
        # TODO : change this to neighbours instead of all nodes
        for neighbour in min_distance_node.get_neighbors(graph):
            neighbour_index = neighbour.get_index()
            edge_weight = graph.get_edge(min_distance_node, neighbour)
            new_distance = distance_list[min_distance_node_index] + edge_weight
            current_distance = distance_list[neighbour_index]
            # 2.1 Relax all adjacent unvisited nodes.
            if not visited_list[neighbour_index]:
                # 2.2 If the new distance is less than the current distance, update the distance.
                if new_distance < current_distance:
                    distance_list[neighbour_index] = new_distance
                    previous_nodes[neighbour_index] = min_distance_node_index

    # Backtrack to construct the shortest path ____________________________________________
    shortest_path = []
    current_node = destination_index
    while current_node != -1:
        shortest_path.insert(0, current_node)
        current_node = previous_nodes[current_node]

    return distance_list, explored_nodes_indexes, shortest_path


def get_min_distance_node(visited_list, distance_list):
    """
    This function returns the node with the minimum distance to visit next.

    @param visited_list: list of booleans, True if the node is visited, False otherwise.
    @param distance_list: list of floats, the distance from the source node to the current node.
    @return: the index of the node with the minimum distance to visit next.
    """
    min_distance = float('inf')
    min_distance_node = None
    for i in range(len(distance_list)):
        # if not visited and distance is less than the current min distance
        if not visited_list[i] and distance_list[i] < min_distance:
            min_distance = distance_list[i]
            min_distance_node = i
    return min_distance_node
