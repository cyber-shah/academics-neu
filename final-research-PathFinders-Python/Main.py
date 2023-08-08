import Graph
import Node as Node
import networkx as nx
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

import Dijkstra


def create_grid_graph():
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
                graph.set_edge_unweighted(graph.get_node_via_xy(x, y), graph.get_node_via_xy(x - 1, y))
            if y > 0:
                graph.set_edge_unweighted(graph.get_node_via_xy(x, y), graph.get_node_via_xy(x, y - 1))
            if x < 19:
                graph.set_edge_unweighted(graph.get_node_via_xy(x, y), graph.get_node_via_xy(x + 1, y))
            if y < 19:
                graph.set_edge_unweighted(graph.get_node_via_xy(x, y), graph.get_node_via_xy(x, y + 1))

    return graph
    # print adjacency matrix
    # graph.print_adjacency_matrix()

    # print adjacency list
    # graph.print_adjacency_list()


def plot_graph(graph):
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
    grid_size = int(graph.number_of_nodes ** 0.5)
    grid_spacing = 1.0  # Adjust this to control the spacing between nodes

    # Calculate positions for the nodes
    pos = {}
    for i in range(graph.number_of_nodes):
        row = i // grid_size
        col = i % grid_size
        pos[i] = (col * grid_spacing, row * grid_spacing)

    # Draw the graph in grid layout
    nx.draw(G, pos, with_labels=True, node_size=50, node_color='black', font_size=4)

    # run Dijkstra's algorithm
    start_node_name = graph.get_node_via_xy(10, 10).name
    end_node_name = graph.get_node_via_xy(2, 2).name
    distances_list, exploration_history_indexes, shortest_path_indexes = (
        Dijkstra.dijkstra_path(graph, start_node_name, end_node_name))

    # Highlight explored nodes
    nx.draw_networkx_nodes(G, pos, nodelist=exploration_history_indexes,
                           node_color='yellow', node_size=50)

    # Highlight the shortest path
    shortest_path_edges = [(shortest_path_indexes[i], shortest_path_indexes[i + 1])
                           for i in range(len(shortest_path_indexes) - 1)]
    nx.draw_networkx_edges(G, pos, edgelist=shortest_path_edges, edge_color='red', width=2)

    plt.show()


def main():
    graph = create_grid_graph()

    plot_graph(graph)


if __name__ == "__main__":
    main()
