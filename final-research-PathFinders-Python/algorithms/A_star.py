def a_star_destination(graph, source_node_name, destination_node_name):
    # Getters ________________________________________________________
    source_node_index = graph.getNodeIndex(source_node_name)
    destination_node_index = graph.getNodeIndex(destination_node_name)

    open_set = set()  # unvisited nodes
    open_set.add(source_node_index)
    closed_set = set()  # visited nodes

    # while there are unvisited nodes
    while open_set:
        # get the current node from the open_set with the lowest f_score
        current_node = min(open_set, key=lambda node: graph.getNode(node).f_score)
        open_set.remove(current_node)
        closed_set.add(current_node)

        # if destination node is found, break the loop
        if current_node == destination_node_index:
            break

        # else visit all the UNVISITED adjacent nodes of current node
        for neighbor in graph.getNeighbors(current_node):
            if neighbor not in closed_set:
                open_set.add(neighbor)
    """
    there's two ways algorithm can be completed
    1. destination node is found
    2. open_set is empty - no path to destination node
    
    closed set starts as empty set, and then just grows
    start with only the source node in it and then add nodes to it as we visit them
    
    psuedocode:
    1. create a set of unvisited nodes called open_set
    2. create a set of visited nodes called closed_set
    3. put the starting node in the closed_set, remove it from the open_set
    4. while the open_set is not empty
        a. get the current node from the open_set with the lowest f_score
        b. remove the current node from the open_set and add it to the closed_set
        c. if the current node is the destination node, break the loop
        d. else visit all the UNVISITED adjacent nodes of current node
            i. for each adjacent node
                1. if the adjacent node is not in the closed_set
                    a. add the adjacent node to the open_set
    5. return the closed_set

    
    """



