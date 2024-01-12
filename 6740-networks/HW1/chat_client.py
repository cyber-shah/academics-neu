import socket
import argparse


class Client:
    def __init__(self, username, server_ip, server_port):
        self.username = username
        self.server_ip = server_ip
        self.server_port = server_port
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.sock.sendto(self.username.encode(),
                         (self.server_ip, self.server_port))
        print(f"Connected to {self.server_ip}:{self.server_port}")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Client for chat application, starts with -u <username> -sip <server-ip> -sp <PORT>")
    parser.add_argument("-u", help="username", required=True)
    parser.add_argument("-sip", help="server ip", required=True)
    parser.add_argument("-sp", help="port number", required=True)
    args = parser.parse_args()
    client = Client(args.u, args.sip, int(args.sp))
