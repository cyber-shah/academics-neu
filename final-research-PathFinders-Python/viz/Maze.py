import numpy as np

from viz.pyamaze import maze, agent, COLOR
import algorithms.Dijkstra
import algorithms.DFS
from model.GraphMakers import graph_from_csv
import model.Graph

import time
import threading
from PIL import ImageGrab


def start_recording():
    global recording, frame_array
    recording = True
    while recording:
        screenshot = ImageGrab.grab(bbox=None)  # Capture the entire screen
        frame = screenshot.convert("RGB")
        frame_array.append(frame)
        time.sleep(0.1)  # Adjust the sleep time to control frame rate


def stop_recording():
    global recording
    recording = False


def pymaze():
    # create explored_agent maze of size 20 x 20
    custom_maze = maze(3, 5)
    # set the goal at 4 , 4
    # loop percent means multiple paths in the maze
    custom_maze.CreateMaze(1, 1, loopPercent=10, loadMaze='maze--2023-08-09--12-13-06.csv')

    custom_graph = graph_from_csv('maze--2023-08-09--12-13-06.csv', 3)

    

    # create an agent at 1, 1
    explored_agent = agent(custom_maze, 1, 1, filled=True, footprints=True, color=COLOR.blue)
    explored_nodes = [(2, 4), (2, 3), (2, 2), (2, 1)]

    path_agent = agent(custom_maze, 1, 1, filled=True, footprints=True, color=COLOR.red)
    shortest_path = [(2, 8), (2, 7), (2, 6), (2, 5), (2, 4), (2, 3), (2, 2), (2, 1)]

    # trace the explored_nodes
    # key is the agent, value is the explored_nodes
    custom_maze.tracePath({explored_agent: explored_nodes}, delay=75)
    custom_maze.tracePath({path_agent: shortest_path}, delay=75)

    # Run the maze
    custom_maze.run()


def main():
    recording = False
    if recording:
        global frame_array
        # Start recording the maze ________________________________________________
        frame_array = []
        recording_thread = threading.Thread(target=start_recording)
        recording_thread.start()

        # Create the maze
        pymaze()

        # Stop the recording when the maze window is closed
        stop_recording()
        recording_thread.join()
        # Save the frames as a GIF using Pillow
        frame_array[0].save(
            'maze_recording.gif',
            save_all=True,
            append_images=frame_array[1:],
            duration=100,  # Duration between frames in milliseconds
            loop=0  # 0 means an infinite loop
        )
    else:
        pymaze()


if __name__ == '__main__':
    main()
