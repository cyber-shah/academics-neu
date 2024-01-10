import socket
import threading

# ask for a nickname
nickname = input("Choose a nickname: ")

# connect to the server
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(('127.0.0.1', 55555))


def receive():
    while True:
        try:
            message = client.recv(1024).decode('ascii')
            if message == 'NICK':
                client.send(nickname.encode('ascii'))
            else:
                print(message)

        except:
            print("An error occurred!")
            client.close()
            break


def write():
    while True:
        message = f'{nickname}: {input("")}'
        client.send(message.encode('ascii'))


# start the thread for the client, with the write function as the target
# and the receive function as the target
receive_thread = threading.Thread(target=receive)
write_thread = threading.Thread(target=write)

# start the threads
receive_thread.start()
write_thread.start()
