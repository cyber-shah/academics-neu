import socket
import argparse
import json

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
    """
    Server class to handle all the server side operations
    All messages MUST be sent in JSON format
    """
    clients_details = {}

    def __init__(self, port):
        self.port = port
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.sock.bind((HOST, self.port))
        print("Server Initialized...")

    def start(self):
        """
        Start the server and
        listen to incoming connections
        """
        while True:
            data, client_address = self.sock.recvfrom(1024)
            # see what the data type is and
            # then forward it to the appropriate function
            data = json.loads(data.decode())
            if data['command'] == 'register':
                message = self.register_client(client_address, data)
            elif data['command'] == 'list':
                message = self.list_clients()
            elif data['command'] == 'send':
                message = self.send_client_address(data)
            else:
                message = {'response': 'error',
                           'message': 'Invalid command'}
            # eventually send the message to the client
            self.sock.sendto(json.dumps(message).encode(), client_address)

    def register_client(self, client_address, data):
        """
        Checks if the client is already registered
        and registers the client if not

        :return a message
        """
        # 1. see if the client is already registered
        username = data['username']
        if username in self.clients_details:
            message = {'response': 'error',
                       'message': 'Username already exists'}
            return message
        else:
            message = {'response': 'success',
                       'message': 'Welcome to the chat app'}
            self.clients_details[username] = {
                'IP': client_address[0], 'PORT': client_address[1]}
            print(f"New connection from \
                {self.clients_details[username]} at {username}")
            return message

    def list_clients(self):
        """
        List all the clients connected

        :return a message
        """
        message = {'response': 'success',
                   'type': 'list',
                   'message': 'List of clients\n'}
        for client in self.clients_details:
            message['message'] += f"    {client}\n"
        return message

    def send_client_address(self, data):
        """
        Send the client address to the client
        """
        username = data['username']
        if username not in self.clients_details:
            return {'response': 'error',
                    'message': 'Username does not exist'}
        else:
            return {'response': 'success',
                    'type': 'client_address',
                    'message': self.clients_details[username]}


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
