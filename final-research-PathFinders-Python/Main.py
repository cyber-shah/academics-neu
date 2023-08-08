import Graph
import Node as Node
import networkx as nx
import matplotlib.pyplot as plt


def main():
    graph = Graph.Graph()

    # create Nodes and add them to the graph
    for x in range(20):
        for y in range(20):
            name = "(" + str(x) + ", " + str(y) + ")"
            graph.add_node(Node.Node(name, x, y))

    # create edges
    for x in range(20):
        for y in range(20):
            if x > 0:
                graph.set_edge_unweighted(graph.get_node(x, y), graph.get_node(x - 1, y))
            if y > 0:
                graph.set_edge_unweighted(graph.get_node(x, y), graph.get_node(x, y - 1))
            if x < 19:
                graph.set_edge_unweighted(graph.get_node(x, y), graph.get_node(x + 1, y))
            if y < 19:
                graph.set_edge_unweighted(graph.get_node(x, y), graph.get_node(x, y + 1))

    # print adjacency matrix
    # graph.print_adjacency_matrix()

    # print adjacency list
    # graph.print_adjacency_list()

    G = nx.Graph()

    # Add nodes
    for node in graph.nodesDictionary.values():
        G.add_node(node.get_index())

    # Add edges
    # for i in range(graph.number_of_nodes):
    #     for j in range(i + 1, graph.number_of_nodes):
    #         if graph.adjacency_matrix[i][j] != float('inf'):
    #             G.add_edge(i, j)

    # Create a grid layout for the nodes
    grid_size = int(graph.number_of_nodes ** 0.5)  # Adjust this based on your node count
    grid_spacing = 1.0  # Adjust this to control the spacing between nodes

    # Calculate positions for the nodes
    pos = {}
    for i in range(graph.number_of_nodes):
        row = i // grid_size
        col = i % grid_size
        pos[i] = (col * grid_spacing, -row * grid_spacing)

    # Draw the graph in grid layout
    nx.draw(G, pos, with_labels=True, node_size=50, node_color='black', font_size=4)

    # Display the visualization
    plt.show()


if __name__ == "__main__":
    main()
