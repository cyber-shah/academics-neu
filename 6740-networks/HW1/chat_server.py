import socket
import argparse


HOST = "localhost"

"""
Server's job:
1. LIST : show all the clients connected
2. REGISTER : register the client with this format
    -u <username> -sip <server-ip> -sp <PORT> 
3. SEND <username> <message> : send message to the user
    emit the message to the client : <FROM IP:PORT Carole> : <Message> 
4. START : start the server
    python server.py -sp <PORT>
"""


class Server:
    clients_details = {}

    def __init__(self, port):
        self.port = port
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.sock.bind((HOST, self.port))
        print("Server Initialized...")

    def start(self):
        while True:
            client_socket, client_address = self.sock.accept()
            username = client_socket.recv(1024).decode()
            self.clients_details[username] = {client_socket, client_address}
            print(
                f"New connection from {self.clients_details[username]} at {username}")
            print(self.clients_details)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Server for chat application, starts with -sp <PORT>")
    parser.add_argument("-sp", help="port number", required=True)
    args = parser.parse_args()
    server = Server(int(args.sp))
    server.start()
