import socket
import argparse
import json
import threading


class Client:
    def __init__(self, username, server_ip, server_port):
        self.username = username
        self.server_ip = server_ip
        self.server_port = server_port
        # this is the address of the client that we want to send the message to
        self.client_address_event = threading.Event()
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        # the first message to the server should always be the username
        # so that the server can register the client
        self.register()

    def register(self):
        message = {"command": "register", "username": self.username}
        self.sock.sendto(json.dumps(message).encode(),
                         (self.server_ip, self.server_port))

        # wait for the server to respond for confirmation
        data, server_address = self.sock.recvfrom(1024)
        data = json.loads(data.decode())
        if data['response'] == "success":
            print(data['message'])
        else:
            print(data['message'])
            exit()

    def list(self):
        self.sock.sendto(json.dumps({"command": "list"}).encode(),
                         (self.server_ip, self.server_port))

    def send(self, message):
        # 1. get the destinations port and IP
        self.sock.sendto(json.dumps(
            {"command": "send", "username": message[1]}
        ).encode(), (self.server_ip, self.server_port))
        # wait for the server to respond with the client address
        self.client_address_event.wait()
        IP = self.client_address['IP']
        PORT = self.client_address['PORT']
        # 2. send the message to that destination port and IP
        self.sock.sendto(json.dumps(
            {"command": "client_message", "message": message[2:]}
        ).encode(), (IP, PORT))

    def receive(self):
        """
        Receive messges from other clients
        """

    def exit(self):
        pass

    def listen(self):
        while True:
            data, server_address = self.sock.recvfrom(1024)
            data = json.loads(data.decode())
            if data['response'] == "success":
                if data['type'] == "client_address":
                    self.client_address = data['message']
                    # Set the event to signal that the client address is updated
                    self.client_address_event.set()

    def parse_input(self):
        while True:
            user_input = input("Enter a command>> ")
            message = user_input.split()
            command = message[0].lower()
            if command == "list":
                self.list()
            elif command == "send":
                self.send(message)
            elif command == "exit":
                self.exit()
            else:
                pass


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Client for chat application, \
        starts with -u <username> -sip <server-ip> -sp <PORT>")
    parser.add_argument("-u", help="username", required=True)
    parser.add_argument("-sip", help="server ip", required=True)
    parser.add_argument("-sp", help="port number", required=True)
    args = parser.parse_args()
    client = Client(args.u, args.sip, int(args.sp))

    # start a thread to listen to messages from the server_ip
    server_thread = threading.Thread(target=client.listen)
    server_thread.start()
    # another thread to listen to messages from other clients

    # another thread to listen to user input
    input_thread = threading.Thread(target=client.parse_input)
    input_thread.start()
