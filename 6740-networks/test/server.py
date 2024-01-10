import socket
import threading

HOST = '127.0.0.1'  # IP address of the server
PORT = 55555  # Port number of the server

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((HOST, PORT))
server.listen()

clients = []
nicknames = []


def broadcast(message):
    for client in clients:
        client.send(message)


def handle(client):
    while True:
        try:
            # recieve message from the client
            message = client.recv(1024)
            # broadcast the message to all the clients
            print(f"{nicknames[clients.index(client)]} says {message}")
            # brocast the message to all the clients
            broadcast(message)
        except:
            index = clients.index(client)
            clients.remove(client)
            client.close()
            nickname = nicknames[index]
            nicknames.remove(nickname)
            broadcast(f"{nickname} left the chat".encode('ascii'))
            break


def recieve():
    while True:
        # accept the connection from the client
        client, address = server.accept()
        print(f"Connected with {str(address)}")
        # ask the client for the nickname
        client.send("NICK".encode('ascii'))
        nickname = client.recv(1024).decode('ascii')
        # add the nickname and client to the list
        nicknames.append(nickname)
        clients.append(client)
        # print the nickname and broadcast the nickname
        print(f"Nickname of the client is {nickname}")
        broadcast(f"{nickname} joined the chat".encode('ascii'))
        client.send("Connected to the server".encode('ascii'))
        # start the thread for the client
        thread = threading.Thread(target=handle, args=(client,))
        thread.start()


print("Server is listening...")
recieve()
