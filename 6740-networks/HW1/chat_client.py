import socket
import json
import argparse
import threading
import sys
import select


# TODO : format the display of messages
# wait for the server to print before printing >> prompt
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
        sender: <sender_username> 
                <sender_IP> 
                <sender_PORT>,
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
        self.username = username.lower()
        self.send_address = None
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        # create a event that is set when the server responds
        self.response_event = threading.Event()
        self.exit_event = threading.Event()
        # start the client
        self.start()


    def start(self):
        """
        1. Register the client
        2. start the threads
        3. wait for the threads to complete
        """
         # register the client with the server
        self.register()
        self.listen_thread = threading.Thread(target=self.listen)
        self.user_thread = threading.Thread(target=self.parse_input)
        self.listen_thread.start()
        self.user_thread.start()
        # wait for the threads to end
        self.user_thread.join()
        self.listen_thread.join()


    # ------------------------------------------------------------
    #                     REGISTER CLIENT
    # ------------------------------------------------------------
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

    # ------------------------------------------------------------
    #                      LISTEN
    # ------------------------------------------------------------
    def listen(self):
        """
        Listen to events
        """
        while not self.exit_event.is_set():
            try:
                readable, _, _ = select.select([self.socket], [], [], 0.1)
                if self.socket in readable:
                    data, sender_address  = self.socket.recvfrom(1024)
                    data = json.loads(data.decode())
                    # if server sends 
                    if  data['sender'] == 'server':
                        # of the type send, then store it / print error
                        if data['type'] == 'send':
                            if data['response'] == 'error':
                                print(f"<- {data['payload']}")
                                self.response_event.set()
                                continue
                            else:
                                self.response_event.set()
                                self.send_address = (
                                    data['payload']['IP'], data['payload']['PORT'])
                        # if server sends but not of the type send
                        else:
                            print(f"<- server: {data['payload']}")

                    # else if message is from another client
                    else:
                        print(f"<- FROM IP {sender_address[0]}: PORT {sender_address[1]} : USER {data['sender']} : {data['payload']}")
            finally:
                pass
    # ------------------------------------------------------------
    #                      PARSE INPUT
    # ------------------------------------------------------------
    def parse_input(self):
        """
        Parse the user input and call the appropriate function
        """
        while not self.exit_event.is_set():
            try:
                # Use select to check for user input without blocking
                readable, _, _ = select.select([sys.stdin], [], [], 0.1) 
                if sys.stdin in readable:
                    message = input("+> ")
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
                        print("<- Invalid command")
            finally:
               pass

    # ------------------------------------------------------------
    #                       SEND CLIENT
    # ------------------------------------------------------------
    def send_client(self, message):
        """
        Send a message to a client
        """
        username = message.split()[1]
        # 1. get the client address from the server
        server_message = {
            "response": "success",
            "sender": self.username,
            "type": "send",
            "payload": {
                "username": username,
            }
        }
        # clear the event before sending the message
        self.response_event.clear()
        self.socket.sendto(json.dumps(server_message).encode(),
                           (self.server_host, self.server_port))
        # 2. wait for the server to respond with the client address
        self.response_event.wait()
        # 3. send the message to the Client
        if self.send_address is None:
            print("Client not found")
            return
        client_message = {
            'response': "success",
            'sender': self.username,
            'type': "message",
            'payload': message.split(' ', 2)[2]
        }
        self.socket.sendto(json.dumps(
            client_message).encode(), self.send_address)
        self.send_address = None

    # ------------------------------------------------------------
    #                      LIST CLIENTS
    # ------------------------------------------------------------
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
        print("exiting... bye!")
        self.exit_event.set()
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
