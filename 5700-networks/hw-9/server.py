"""
Pranchal Shah
5700 hw-9
"""
# A simple program that will create a server that would echo a client request back to the client

import socket
import sys

HOST = str(sys.argv[1])  # Standard loopback interface address (localhost)
PORT = int(sys.argv[2])  # Port to listen on

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    print(f"server IP address: {(HOST)}")
    print(f"server port number: {(PORT)}")
    s.bind((HOST, PORT))

    print("\nReady to serve...")
    s.listen()

    conn, addr = s.accept()
    with conn:
        print(f"Connected by {addr}")
        while True:
            data = conn.recv(1024)
            if not data:
                break
            conn.send(b"Connection Successful!")
            print(f"Recieved data : {data.decode()}")

            file_path = data.decode()
