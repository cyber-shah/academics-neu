import time
import csv

from algorithms import Dijkstra, DFS, BFS, A_star
from model.GraphMakers import graph_from_csv


def run_all_algos(csv_files, algos):
    # csv_files is a list of csv file names
    # algos is a list of algorithm names
    # create a dictionary that maps the algorithm names to the corresponding functions
    algo_dict = {
        "Dijkstra": Dijkstra.dijkstra_path,
        "DFS": DFS.dfs_destination,
        "BFS": BFS.bfs_destination,
        "A*": A_star.a_star_destination
    }

    # create an empty list to store the results
    results = []

    # loop through the csv files
    for csv_file in csv_files:
        # create a graph from the csv file
        graph = graph_from_csv(csv_file)

        # loop through the algorithms
        for algorithm in algos:
            # get the algorithm function from the dictionary using the algorithm name as a key
            algo_function = algo_dict.get(algorithm)

            # check if the algorithm function is valid
            if algo_function is not None:
                # start a timer
                start = time.time()

                # run the algorithm function on the graph and get the path, explored nodes
                explored_nodes_indexes, shortest_path = algo_function(graph, '(5, 3)', '(15, 17)')

                # stop the timer and get the elapsed time
                end = time.time()
                elapsed = end - start

                # get the length of the path
                path_length = len(shortest_path)
                explored_length = len(explored_nodes_indexes)

                # append a row to the results with the csv file name, algorithm name, time, path length and nodes explored
                results.append([csv_file, algorithm, elapsed, path_length, explored_length])
            else:
                print("Invalid algorithm name")

    # create a csv file name for the output
    output_file = "results.csv"

    # open the output file in write mode
    with open(output_file, "w") as f:
        # create a csv writer object
        writer = csv.writer(f)

        # write a header row with the column names
        writer.writerow(["CSV File", "Algorithm", "Time", "Path Length", "Nodes Explored"])

        # write the results to the output file
        writer.writerows(results)

    # return the output file name
    return output_file

