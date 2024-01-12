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
            data, client_address = self.sock.recvfrom(1024)
            host, port = client_address
            username = data.decode()
            if username in self.clients_details:
                self.sock.sendto(
                    f"Username {username} already exists, please try again".encode(), client_address)
                continue
            else:
                self.sock.sendto(
                    f"Username {username} is registered".encode(), client_address)
            self.clients_details[username] = {'IP': host, 'PORT': port}
            print(
                f"New connection from {self.clients_details[username]} at {username}")
            print(self.clients_details)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Server for chat application, starts with -sp <PORT>")
    parser.add_argument("-sp", help="port number", required=True)
    args = parser.parse_args()
    server = Server(int(args.sp))
    try:
        server.start()
    except KeyboardInterrupt:
        print(" gracefully shutting down the server....bye!")
