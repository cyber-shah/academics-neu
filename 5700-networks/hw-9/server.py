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
    print(f"Server IP address: {(HOST)}")
    print(f"Server port number: {(PORT)}")
    s.bind((HOST, PORT))
    s.listen()

    print("\nReady to serve...")

    while True:
        try:
            conn, addr = s.accept()
            with conn:
                print(f"Connected by {addr}")
                while True:
                    data = conn.recv(1024)
                    if not data:
                        break
                    conn.send(b"Connection Successful!")

                    file_path = data.decode().strip()
                    try:
                        with open(file_path, "r") as file:
                            response = (
                                b"\n---------------HTTP RESPONSE---------------\n"
                                b"HTTP/1.1 200 OK\n\n"
                                + file.read().encode()
                                + b"\n---------------END OF HTTP RESPONSE---------------\n"
                            )
                            conn.sendall(response)
                    except FileNotFoundError:
                        response = (
                            b"\n---------------HTTP RESPONSE---------------\n"
                            b"HTTP/1.1 404 Not Found\n"
                            b"\n---------------END OF HTTP RESPONSE---------------\n"
                        )
                        conn.sendall(response)
        except KeyboardInterrupt:
            print("\nExiting...")
            exit()
