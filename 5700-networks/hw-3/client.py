"""
Pranchal Shah
CS 5700 - Computer Networks
HW 3 - Sockets Programming
"""

import socket
import sys

HOST = "127.0.0.1"  # The server's host name or IP address
PORT = 65438  # The port used by the server

number = sys.argv[1]

# TODO: check if we need encoding
# create TCP socket
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    s.send(number.encode('utf-8'))
    data = s.recv(1024)
    # The maximum amount of data to be received in each chunk
    # is 1024 as specified.
    print(f"Sent {number} and received {data!r}")  # print to console

