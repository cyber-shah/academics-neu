# ICMP
Corresponds to protocol type 1 in the IP Packet header
used when you  do ping 
no concept of port
#### Uses
echo/ ping
redirect
destination unreachable  -- you send TCP/UDP packet but you get back ICMP packet saying its not reachable.
sent by router when TTL is exceeded
Find MTU for each router. so that router doesn't have to work towards it. -- Fragmentation. Sent by router when it exceeds their MTU, so we can fragment by ourselves and send it.

#### Works under the hood in TRACEROUTE
1. send a packet with TTL 0
2. then 1 ...
3. so each router needs to send a ICMP packet with their source IP
Doesn't always work, some routers don't respond for security
as they are configured to therow away the packet once it dies down.

#### so how come the ping response comes back to your terminal and not the other one that you have open
your app will include PID with the packet, when it comes back it extracts the PID and sends it where its needed.



# DHCP
when you connect to the internet and don;t have an IP, you broadcast an DHCPDisover request, and the server recieves it and sends DHCPOFFER message

# Routing
forwarding vs routing



# TCP
1. flow control and 
2. Congestion control
reciever tells the source about amount of packets it can handle before getting overwhelmed.

1. syn ack is random to not be predictable as someone can prtened to be someone on the other side ... and predict it
syn flood attacks

congestion window


## NATs

## ARPs

### 