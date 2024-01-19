# ICMP

### Why do we need ICMP packets, and how did they come into being?
- **Need:** ICMP (Internet Control Message Protocol) packets are essential for network diagnosis and troubleshooting. They provide a means for devices to communicate error messages or information about network conditions.
- **Origin:** ICMP was introduced to address the need for a standardized protocol that allows devices to exchange control and error information within an IP network.
### What are ICMP packets?
ICMP packets are a set of control messages and codes that operate at the network layer (Layer 3) of the Internet Protocol (IP) suite. They are used to report errors, test connectivity, and facilitate network management.
### What are their codes and their meanings
- **Echo (Ping):** Testing reachability of a host.
- **Redirect:** Informing the source host of a better route.
- **Destination Unreachable:** Indicating that the destination (protocol, port, or host) is unreachable.
- **TTL Exceeded:** Signaling that the Time To Live (TTL) value in a packet became zero, preventing infinite cycling.
- **Fragmentation Needed:** Indicating that the packet needs fragmentation.
- **Reassembly Failed:** Signaling a failure in packet reassembly.

### What are its applications?
- **4.1 How is it used in Traceroute?**
    - **Function:** Traceroute utilizes ICMP to trace the route that packets take from the source to the destination.
    - **Process:**
        1. Send packets with increasing TTL values.
        2. Routers along the path send ICMP packets with their source IP when TTL is exceeded.
        3. Limited response, as not all routers may reply for security reasons.
- **4.2 In MTU Discovery:**
    - **Function:** MTU Discovery determines the Maximum Transmission Unit (MTU) for each router in the path.
    - **Process:**
        - Determines the appropriate packet size to prevent fragmentation.
        - Routers send ICMP messages indicating the need for fragmentation or signaling reassembly failure.


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