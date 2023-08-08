import Graph
import Node


def read_file(filename):
    try:
        with open(filename, 'rb') as f:
            content = f.read().decode('utf-8')
        return content
    except FileNotFoundError:
        print("Error opening file")
        return None


def graph_from_string(vertices, distances):
    graph = Graph.Graph()

    vertices_lines = vertices.split('\n')
    for vertex in vertices_lines:
        ascii_values = 0
        if vertex:
            for char in vertex:
                ascii_values += ord(char)
            graph.add_node(Node.Node(vertex, ascii_values, ascii_values))

    distances_lines = distances.split('\n')
    for line in distances_lines:
        if line:
            source, destination, weight = line.split()
            weight = int(weight)
            source_node = graph.get_node_via_name(source)
            destination_node = graph.get_node_via_name(destination)
            graph.set_edge_weighted(source_node, destination_node, weight)

    return graph


def graph_from_file(filename):
    content = read_file(filename)
    if content:
        vertices, distances = content.split('\n\n')
        return graph_from_string(vertices, distances)
    else:
        return None
