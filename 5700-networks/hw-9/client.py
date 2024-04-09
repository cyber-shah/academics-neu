"""
Pranchal Shah
5700 hw-9
"""

import socket
import sys

HOST = sys.argv[1]
PORT = int(sys.argv[2])
FILE = sys.argv[3]

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    try:
        s.connect((HOST, PORT))
    except ConnectionRefusedError:
        print("\nError while connecting!")
        exit()

    # send the data request
    s.sendall(FILE.encode())

    # recieve
    while True:
        try:
            data = s.recv(1024)
            print(f"{data.decode()}")
        except KeyboardInterrupt:
            print("\nexiting...")
            exit()
