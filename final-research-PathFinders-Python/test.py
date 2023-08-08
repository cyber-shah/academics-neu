import pygame
import networkx as nx
import Graph  # Import your graph module (assuming it contains Graph and Node classes)
import Dijkstra  # Import your Dijkstra module (assuming it contains Dijkstra class)
from Main import create_grid_graph

pygame.init()

WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
YELLOW = (200, 200, 0)

NODE_RADIUS = 20
WIDTH, HEIGHT = 800, 600
ANIMATION_DELAY = 100

screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Graph Visualization")


def plot_graph(graph):
    nx_graph = nx.Graph()

    for node in graph.nodesDictionary.values():
        nx_graph.add_node(node.get_index())

    # Calculate the number of nodes in each row and column
    grid_size = int(graph.number_of_nodes ** 0.5)

    # Calculate the spacing between nodes
    grid_spacing = min(WIDTH, HEIGHT) / grid_size

    # Calculate positions for the nodes
    pos = {}
    for i, node in enumerate(graph.nodesDictionary.values()):
        row = i // grid_size
        col = i % grid_size
        pos[node.get_index()] = (col * grid_spacing, row * grid_spacing)

    return nx_graph, pos


def draw_graph(graph, nx_graph, pos, exploration_history, shortest_path, current_step, current_path_step):
    screen.fill(WHITE)

    for node in graph.nodesDictionary.values():
        pygame.draw.circle(screen, BLACK, pos[node.get_index()], NODE_RADIUS)

    # for edge in graph.edges:
    #     start_pos = pos[edge.start.get_index()]
    #     end_pos = pos[edge.end.get_index()]
    #     pygame.draw.line(screen, BLACK, start_pos, end_pos, 2)

    for i in range(len(exploration_history)):
        if i <= current_step:
            pygame.draw.circle(screen, YELLOW, pos[exploration_history[i]], NODE_RADIUS)

    for i in range(len(shortest_path) - 1):
        if i <= current_path_step:
            pygame.draw.line(screen, RED, pos[shortest_path[i]], pos[shortest_path[i + 1]], 2)

    pygame.display.flip()


def main():
    graph = create_grid_graph()

    nx_graph, pos = plot_graph(graph)

    start_node_name = graph.get_node_via_xy(2, 4).name
    end_node_name = graph.get_node_via_xy(8, 8).name

    distances_list, exploration_history_indexes, shortest_path_indexes = (
        Dijkstra.dijkstra_path(graph, start_node_name, end_node_name))

    running = True
    current_step = 0
    current_path_step = 0
    exploring = True

    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

        if exploring and current_step < len(exploration_history_indexes):
            draw_graph(graph, nx_graph, pos, exploration_history_indexes, [],
                       current_step, current_path_step)
            pygame.time.delay(ANIMATION_DELAY)
            current_step += 1
        # all nodes have been explored
        elif current_path_step < len(shortest_path_indexes):
            exploring = False
            draw_graph(graph, nx_graph, pos, exploration_history_indexes, shortest_path_indexes,
                       current_step, current_path_step)
            pygame.time.delay(ANIMATION_DELAY * 2)
            current_path_step += 1
        else:
            running = False

    pygame.quit()


if __name__ == "__main__":
    main()
