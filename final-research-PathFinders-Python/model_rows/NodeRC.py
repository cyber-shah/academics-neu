class NodeRC:

    def __init__(self, name, row=None, column=None):
        self.index = None
        self.name = name
        if row is None or column is None:
            self.row = 0
            self.column = 0
        else:
            self.row = row
            self.column = column

    def get_node(self):
        return self

    def get_row(self):
        return self.row

    def get_column(self):
        return self.column

    def get_neighbors(self, graph):
        # a list of nodes
        neighbors = []
        for i in range(graph.number_of_nodes):
            if graph.adjacency_matrix[self.index][i] != float('inf') and i != self.index:
                neighbors.append(graph.get_node_via_index(i))
        return neighbors

    def get_index(self):
        return self.index

    def get_name(self):
        return self.name

    def set_row(self, row):
        self.row = row

    def set_column(self, column):
        self.column = column

    def set_index(self, index):
        self.index = index

    def __str__(self):
        return "(" + str(self.row) + ", " + str(self.column) + ")"
