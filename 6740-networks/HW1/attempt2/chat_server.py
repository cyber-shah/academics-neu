import threading
import socket
import argparse
import json


class Server:
    """
    Server has the following responsibilities:
    - Register clients
    - notify clients of other clients
    - share port and IP of clients with other clients
    Each message must have the following format
    {
        response: <response>,
        sender: <sender>,
        type: <type>,
        payload: <payload>
    }
    """

    def __init__(self, server_port):
        """
        Initialize the server
        """
        self.server_ip = "localhost"
        try:
            self.server_port = int(server_port)
        except ValueError:
            print("Port number must be an integer")
            exit()
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.socket.bind((self.server_ip, self.server_port))
        self.client_details = {}
        print("Server Initialized...")

    def start(self):
        """
        Start the server and listen to incoming connections
        Parse the message and call the appropriate function
        :note Sending messages to clients is done in the respective functions
        """
        while True:
            data, client_address = self.socket.recvfrom(1024)
            data = json.loads(data.decode())
            if data['type'] == 'register':
                self.register_client(client_address, data)
            elif data['type'] == 'list':
                self.list_clients(client_address, data)
            elif data['type'] == 'send':
                self.send_client_address(data)
            else:
                message = {'response': 'error',
                           'type': 'command',
                           'message': 'Invalid command'}
                self.socket.sendto(json.dumps(
                    message).encode(), client_address)

    def register_client(self, client_address, data):
        """
        Register a client with the server
        Send a success message to the client if registration is successful
        Send an error message to the client if registration is unsuccessful
        """
        username = data['payload']['username']
        if username in self.client_details:
            message = {'sender': 'server',
                       'response': 'error',
                       'type': 'register',
                       'payload': 'Username already exists'}
            self.socket.sendto(json.dumps(message).encode(), client_address)
        else:
            message = {'sender': 'server',
                       'response': 'success',
                       'type': 'register',
                       'payload': f'User {username} registered'}
            self.client_details[username] = {
                'IP': client_address[0], 'PORT': int(client_address[1])}
            print(f"New connection from \
                {self.client_details[username]} at {username}")
            # notify all the clients that a new client has joined
            for client in self.client_details:
                self.socket.sendto(json.dumps(message).encode(),
                                   (self.client_details[client]['IP'],
                                   self.client_details[client]['PORT']))

    def list_clients(self, client_address, data):
        """
        List all the clients registered with the server
        """
        # construct the list of clients
        list = "Signed in users: "
        for client in self.client_details:
            list += f" {client}"
        # send the message
        message = {'sender': 'server',
                   'response': 'success',
                   'type': 'list',
                   'payload': list}
        self.socket.sendto(json.dumps(message).encode(),
                           client_address)


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
