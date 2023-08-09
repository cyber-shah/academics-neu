from viz.pyamaze import maze, agent, COLOR

import mmaze


def pymaze():
    # create explored_agent maze of size 20 x 20
    custom_maze = maze(20, 20)
    # set the goal at 4 , 4
    # loop percent means multiple paths in the maze
    custom_maze.CreateMaze(15, 3, loopPercent=50)

    # create an agent at 5, 5
    explored_agent = agent(custom_maze, 5, 5, filled=True, footprints=True, color=COLOR.blue)
    explored_nodes = [(2, 4), (2, 3), (2, 2), (2, 1)]

    path_agent = agent(custom_maze, 5, 5, filled=True, footprints=True, color=COLOR.red)
    shortest_path = [(2, 8), (2, 7), (2, 6), (2, 5), (2, 4), (2, 3), (2, 2), (2, 1)]

    # trace the explored_nodes
    # key is the agent, value is the explored_nodes
    custom_maze.tracePath({explored_agent: explored_nodes}, delay=75)
    custom_maze.tracePath({path_agent: shortest_path}, delay=75)

    # run the maze
    custom_maze.run()


# def mmaze_generator():
#     start = (0, 0)
#     end = (10, 10)
#     m = mmaze.generate(width=11, height=11, symmetry="horizontal")
#     m.plot()
#
#     m.solutions(start, end)
#


def main():
    pymaze()
    # create a gif of the maze



if __name__ == '__main__':
    main()
