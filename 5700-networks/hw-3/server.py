"""
Pranchal Shah
CS 5700 - Computer Networks
HW 3 - Sockets Programming
"""

import socket

HOST = "127.0.0.1"  # Standard loopback interface address (localhost)
PORT = 65438  # Port to listen on

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    # Bind the socket to an address and port number.
    s.bind((HOST, PORT))
    s.listen()  # Enable a server to accept connections.
    # Accept a connection.
    conn, addr = s.accept()
    # conn is a new socket object usable to send and receive data on
    with conn:
        while True:
            data = conn.recv(1024)  # receives the data from the client
            if not data:
                break  # breaks out once no more data to receive

            decoded_data = int(data.decode('utf-8'))
            result = decoded_data + 100
            conn.sendall(str(result).encode('utf-8'))

