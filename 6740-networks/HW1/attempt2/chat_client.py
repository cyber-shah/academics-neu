import socket
import json
import argparse
import threading
import sys


# TODO : format the display of messages
# wait for the server to print before printing >> prompt
#
# TODO: store all clients in lower case
#
# TODO : exit the client correctly


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
        self.listen_thread = threading.Thread(target=self.listen)
        self.listen_thread.start()
        self.user_thread = threading.Thread(target=self.parse_input)
        self.user_thread.start()

    def register(self):
        """
        Register the client with the server
        """
        message = {
            "response": "success",
            "sender": self.username,
            "type": "register",
            "payload": {
                "username": self.username
            }
        }
        self.socket.sendto(json.dumps(message).encode(),
                           (self.server_host, self.server_port))
        # wait for the server to respond for confirmation
        data, server_address = self.socket.recvfrom(1024)
        data = json.loads(data.decode())
        # ERROR HANDLING -- if the server responds with an error
        # print the error and exit
        if data['response'] == "success":
            print(data['payload'])

        else:
            print(data['payload'])
            exit()

    def listen(self):
        """
        Listen to events
        """
        while True:
            data, server_address = self.socket.recvfrom(1024)
            data = json.loads(data.decode())
            # if server sends the username of the client
            if data['type'] == 'send' and data['sender'] == 'server':
                self.send_client(data)
            else:
                print(f"{data['sender']}: {data['payload']}")

    def parse_input(self):
        """
        Parse the user input and call the appropriate function
        """
        while True:
            message = input("Enter a command>> ")
            if not message:
                continue
            command = message.split()[0].lower()
            if command == "list":
                self.list()
            elif command.startswith("send"):

                self.send_client(message)
            elif command == "exit":
                self.exit_client()
            else:
                print("Invalid command")

    def list(self):
        """
        List all the clients registered with the server
        """
        message = {
            "response": "success",
            "sender": self.username,
            "type": "list",
            "payload": ""
        }
        self.socket.sendto(json.dumps(message).encode(),
                           (self.server_host, self.server_port))

    # ------------------------------------------------------------
    #                       SEND CLIENT
    # ------------------------------------------------------------
    def send_client(self, message):
        """
        Send a message to a client
        """
        # 1. get the client address from the server
        send_username = message.split()[1]
        message = {
            "response": "success",
            "sender": self.username,
            "type": "send",
            "payload": {
                "username": send_username
            }
        }
        self.socket.sendto(json.dumps(message).encode(),
                           (self.server_host, self.server_port))

        # 2. wait for the server to respond with the client address
        # 3. send the message to the client

    # ------------------------------------------------------------
    #                      EXIT CLIENT
    # ------------------------------------------------------------
    def exit_client(self):
        """
        Exit the client
        """
        message = {
            "response": "success",
            "sender": self.username,
            "type": "exit",
            "payload": ""
        }
        self.socket.sendto(json.dumps(message).encode(),
                           (self.server_host, self.server_port))
        # TODO: stop all the threads
        self.user_thread.join()
        self.listen_thread.join()
        sys.exit()


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