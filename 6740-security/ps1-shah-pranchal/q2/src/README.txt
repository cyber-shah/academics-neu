Project : PS1 
Pranchal Shah
CS 6740 - Network security

This submission contains two files:
1. chat_server.py
2. chat_client.py

chat_server.py is the server side code which will be run on the server machine.
Runs by calling the following command:
python chat_server.py -sp <port_number>

chat_client.py is the client side code which will be run on the client machine.
Runs by calling the following command:
python chat_client.py -u <username> -sip <server_ip> -sp <server_port>

The server and client can be run on the same machine or different machines.


Dependencies:
1. argparse
2. socket
3. threading
4. sys
5. select
6. json
