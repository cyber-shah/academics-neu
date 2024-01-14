import socket
import json
import argparse
import threading


class Client:
    """
    Client class for the chat client.
    Each client has the following functionality:
    - Connect to the server
    - Listen to events
    - Parse user input and call the appropriate function

    Each message has the following format:
    {
        response: <response>,
        sender: <sender>,
        type: <type>,
        payload: <payload>
    }
    """

    def __init__(self, username, server_ip, server_port):
        """
        Initialize the client
        """
        self.server_host = server_ip
        try:
            self.server_port = int(server_port)
        except ValueError:
            print("Port number must be an integer")
            exit()
        self.username = username
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        # register the client with the server
        self.register()

    def register(self):
        """
        Register the client with the server
        """
        message = {
            "response": "success",
            "sender": "client",
            "type": "register",
            "payload": {
                "username": self.username
            }
        }
        self.socket.sendto(json.dumps(message).encode(),
                           (self.server_host, self.server_port))

    def listen(self):
        """
        Listen to events
        """
        while True:
            data, server_address = self.socket.recvfrom(1024)
            data = json.loads(data.decode())
            print(data['payload'])

    def parse_input(self):
        """
        Parse the user input and call the appropriate function
        """
        while True:
            message = input("Enter a command>> ")
            command = message.split()[0].lower()
            if command == "list":
                self.list()
            elif command.startswith("send"):
                self.send(message)
            else:
                print("Invalid command")

    def list(self):
        """
        List all the clients registered with the server
        """
        message = {
            "response": "success",
            "sender": "client",
            "type": "list",
            "payload": ""
        }
        self.socket.sendto(json.dumps(message).encode(),
                           (self.server_host, self.server_port))

    def send_client(self, message):
        """
        Send a message to a client
        """
        # 1. get the client address from the server
        pass


if __name__ == "__main__":
    # 1. set up the argument parser
    parser = argparse.ArgumentParser(
        description="Client for chat application, \
        starts with -u <username> -sip <server-ip> -sp <PORT>")
    parser.add_argument("-u", help="username", required=True)
    parser.add_argument("-sip", help="server ip", required=True)
    parser.add_argument("-sp", help="port number", required=True)
    args = parser.parse_args()

    # 2. create a client object
    client = Client(args.u, args.sip, args.sp)

    # 3. Start threads for listening to events and parsing user input
    user_thread = threading.Thread(target=client.parse_input)
    user_thread.start()

    listen_thread = threading.Thread(target=client.listen)
    listen_thread.start()
