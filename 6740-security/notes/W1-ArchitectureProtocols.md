
## 0. Why was IP developed ?
The development of the Internet Protocol (IP) was driven by the need for a networking system that possessed two key characteristics:
1. **Decentralization - Flexibility:**
- Flexible and distributed structure where no single central authority controls the entire network.
- _Importance:_
    - **Flexibility:** A decentralized system allows for adaptability and autonomy, meaning individual devices or nodes can operate independently without relying on a central point of control.
    - **Resilience:** In the event of a failure or disruption in one part of the network, a decentralized system can continue to function as other nodes are not dependent on the affected area.
2. **Concatenation Ability - Scalability:**
    Ability to link or connect various networks seamlessly, allowing the overall network to grow by combining smaller networks.
    - _Importance:_
        - **Scalability:** Concatenation enables the network to expand horizontally by adding more components or networks. This is crucial for accommodating the growing number of devices and users on the Internet.
        - **Interoperability:** Different networks, regardless of their size or technology, can be linked together, fostering a more extensive and interconnected communication infrastructure.
#### What does it mean to be decentralized and why was it important?
- **Flexibility and Autonomy:** Decentralization allows each part of the network to operate independently, making the system more adaptable to changes in conditions, technology, or usage patterns.
- **Resilience:** With no central point of control, the network becomes more robust and resilient. It can continue to function even if certain parts face disruptions or failures.
#### Why concatenation ability was important
- **Scalability:** The ability to concatenate or link networks is essential for accommodating the increasing number of devices and users on the Internet.
- **Interconnectedness:** Concatenation facilitates the connection of diverse networks, promoting a more extensive and interconnected global communication infrastructure.

![[Pasted image 20240109213706 1.png]]


## 1. What is IP Service model?
- _Definition:_ IP operates in a connectionless service model, meaning it sends data without establishing a dedicated connection.
- _Example:_ Similar to sending postcards – no need to dial a number before sending a message.
#### Why was connectionless approach used?
Connectionless design provides flexibility.
_Advantages:_
    - Flexibility in routing and network design.
    - Well-suited for scalability, adapting to changing network conditions.
    - Simplicity in implementation.

#### What about Best effort delivery?
IP follows a best effort delivery model, where it does its best to deliver data without guarantees.
_Advantages:_
    - Flexibility: Adapts to varying network conditions.
    - Scalability: Efficient for large-scale networks.
    - Resource efficiency: Minimizes overhead without strict guarantees.

## 2. What exists inside an IP Packet?
#### TTL : Time to Live
- _Definition:_ Time to Live is a field in the IP packet header that indicates the maximum number of hops (router-to-router movements) the packet can take before it is discarded.
- _Purpose:_
    - **Preventing Infinite Loops:** Helps prevent packets from circulating endlessly in the network, avoiding congestion and inefficiency.
    - **Congestion Control:** Limits the packet's lifetime to manage network resources effectively.
- _Decision:_ The TTL value is typically set by the source device when creating the packet. It is often based on an estimation of the number of routers the packet is expected to traverse. If the TTL reaches zero, the packet is discarded.
- _Concerns:_ For critical applications like payments, a careful estimation of the expected network distance may be necessary to ensure timely delivery. However, TTL is not typically used for time-sensitive applications.
#### Fragmentation
- _Definition:_ Fragmentation involves breaking a packet into smaller fragments if the packet size exceeds the Maximum Transmission Unit (MTU) of a network segment.
- _Mechanism:_ Uses an Identifier and Offset in the IP header to reassemble fragments at the destination.
- _De-Fragmentation:_ Reassembly occurs only at the destination, where fragments are put back together to reconstruct the original packet.

#### Maximum Transmission Unit (MTU) in Network Links:
- _Definition:_ MTU represents the maximum size of a data packet that can be transmitted over a network link without fragmentation.
- _Variability:_ Different network links, which use various physical layers and communication technologies, have different MTUs defined by their standards.
    - Example: Ethernet v2 has an MTU of 1500 bytes, while IEEE 802.11 Wi-Fi has an MTU of 2304 bytes.


## Scaling IP Adresses.
### CIDR
#### Subnetting
- Subnet number and a mask

#### Supernetting


## IP over LAN





# OSI and TCP/IP Stack
![[Pasted image 20240201123700.png]]

![[Pasted image 20240201123759.png]]



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
- **4.1 How is it used in Trace route?**
    - **Function:** Trace route utilizes ICMP to trace the route that packets take from the source to the destination.
    - **Process:**
        1. Send packets with increasing TTL values.
        2. Routers along the path send ICMP packets with their source IP when TTL is exceeded.
        3. Limited response, as not all routers may reply for security reasons.
- **4.2 In MTU Discovery:**
    - **Function:** MTU Discovery determines the Maximum Transmission Unit (MTU) for each router in the path.
    - **Process:**
        - Determines the appropriate packet size to prevent fragmentation.
        - Routers send ICMP messages indicating the need for fragmentation or signaling reassembly failure.


# Dynamic Host Configuration Protocol (DHCP)
Imagine you walk into a busy coffee shop (the internet), 
and you want to find a seat to enjoy your coffee and work on your laptop (some ADDRESS). The process of finding a seat is similar to how DHCP works in a network.
- The Barista gives you a seat (DHCP).
- Once you leave, your seat (IP) gets assigned to another person (other device).
### What are the steps in DHCP client-server communication?
1. **DHCP Server Discovery:**
   - Clients broadcast DHCPDISCOVER requests.
2. **Offer and Request:**
   - Clients receive DHCPOFFER messages and broadcast DHCPREQUEST.
3. **Server Relay:**
   - Requests are unicast-relayed to the server by DHCP relays.
4. **Server Response:**
   - DHCP server broadcasts replies containing <HWADDR, IPADDR, lease-info>.
### Centralized management
The management of DHCP involves configuring parameters such as IP address ranges, lease times, and other network settings. DHCP servers maintain a pool of available IP addresses and assign them dynamically to devices as they join the network.

In summary, DHCP is typically centralized, and DHCP servers are hosted in locations such as local networks, data centers, or cloud environments. The organization or network administrator manages DHCP server configurations to ensure efficient IP address assignments within the network.


# TCP
1. flow control and 
2. Congestion control
reciever tells the source about amount of packets it can handle before getting overwhelmed.

1. syn ack is random to not be predictable as someone can prtened to be someone on the other side ... and predict it
syn flood attacks

congestion window

# DNS

### Why did this come into being?
The Domain Name System (DNS) **turns domain names into IP addresses, which browsers use to load internet pages.** Every device connected to the internet has its own IP address, which is used by other devices to locate the device. DNS servers make it possible for people to input normal words into their browsers, without having to keep track of the IP address for every website.

A DNS server is a computer with a database containing the public IP addresses associated with the names of the websites an IP address brings a user to. **DNS acts like a phonebook for the internet.** Whenever people type domain names, like Fortinet.com or Yahoo.com, into the address bar of web browsers, the DNS finds the right IP address. The site’s IP address is what directs the device to go to the correct place to access the site’s data.

### How does this work?
The domain names are organized as hierarchical zones  
starting at the Top Level Domains (TLD) (.net, .com, .edu,  
etc.)  
- Each zone has at least two dns servers  
- DNS runs on top of UDP port 53 (and also in a limited way on top of TCP port 53)  
- The DNS resolver hierarchically queries DNS servers until it obtains the mapping between a name/resource and an IP

1. A user types ‘example.com’ into a web browser and the query travels into the Internet and is received by a DNS recursive resolver.
2. The resolver then queries a DNS root nameserver (.).
3. The root server then responds to the resolver with the address of a Top Level Domain (TLD) DNS server (such as .com or .net), which stores the information for its domains. When searching for example.com, our request is pointed toward the .com TLD.
4. The resolver then makes a request to the .com TLD.
5. The TLD server then responds with the IP address of the domain’s nameserver, example.com.
6. Lastly, the recursive resolver sends a query to the domain’s nameserver.
7. The IP address for example.com is then returned to the resolver from the nameserver.
8. The DNS resolver then responds to the web browser with the IP address of the domain requested initially.

Once the 8 steps of the DNS lookup have returned the IP address for example.com, the browser is able to make the request for the web page:

10. The browser makes a [HTTP](https://www.cloudflare.com/learning/ddos/glossary/hypertext-transfer-protocol-http/) request to the IP address.
11. The server at that IP returns the webpage to be rendered in the browser (step 10).

![[Pasted image 20240119124507.png]]


### What does the resource record contain?
Each name server maintains a collection of resource records  
		(Name, Value, Type, Class, TTL)  
1. Name/Value: not necessarily host names to IP addresses  
2. Type  
	- A: Value is an IP address  
	- NS: Value gives domain name for host running name server that knows how to resolve names within specified domain.  
	- CNAME: Value gives canonical name for particle host; used to define aliases.  
	- MX: Value gives domain name for host running mail server that accepts messages for specified domain.  
3. Class: allow other entities to define types  
	- IN: Means Internet  
4. TTL: how long the resource record is valid

# NATs
![What is Network Address Translation](https://comptiacdn.azureedge.net/webcontent/images/default-source/researchreports/what-is-network-address-translation/what-is-network-address-translation.png?sfvrsn=fa091aee_2 "What is Network Address Translation")

NAT stands for network address translation. 
> *It’s a way to map multiple private addresses inside a local network to a public IP address* before transferring the information onto the internet. 

Organizations that want multiple devices to employ a single IP address use NAT, as do most home routers. If you’re connecting from your home right now, chances are your cable modem or DSL router is already providing NAT to your home.
### Why do we use NATs?
- To reduce the number of IP addresses allocated in the world.
- Imagine everyone with their own IP.. that's too many addresses, just like each person with their own address. 
- Instead what we have is families/people living in one home having the same address but then there is a name attached to the address(private IP/mac)

### How does NAT work?
Let’s say that there is a laptop connected to a home network using NAT. That network eventually connects to a router that addresses the internet. 
Suppose that someone uses that laptop to search for directions to their favorite restaurant. 
1. The laptop is using NAT. So, it sends this request in an IP packet to the router, which passes that request along to the internet and the search service you’re using. 
2. But before your request leaves your home network, the router first changes the internal IP address from a private local IP address to a public IP address. 
3. Your router effectively translates the private address you’re using to one that can be used on the internet, and then back again. 
# ARPs
Address Resolution Protocol (ARP) is used to resolve an **IPv4 address** (32 bit Logical Address) to the physical address (48 bit MAC Address). Network Applications at the Application Layer use IPv4 Address to communicate with another device. But at the **Datalink layer**, the addressing is **MAC address** (48 bit Physical Address).

Step 1: If a source device want to communicate with another device, source device checks its Address Resolution Protocol (ARP) cache to find if it already has a resolved MAC Address of the destination device. If it is there, it will use that MAC Address for communication. **it means its already there in the network?**

Step 2: If ARP resolution is not there in local cache, the source machine will generate an Address Resolution Protocol (ARP) request message, it puts: 
1. its own data link layer address as the Sender Hardware Address and **MAC Address? 
2. its own IPv4 Address as the Sender Protocol Address. **belongs to the router?
3. It fills the destination IPv4 Address as the Target Protocol Address.  
4. The Target Hardware Address will be left blank, since the machine is trying to find that.

Step 3: The source broadcast the Address Resolution Protocol (ARP) request message to the local network.

Step 4: The message is received by each device on the LAN since it is a broadcast. Each device compare the Target Protocol Address (IPv4 Address of the machine to which the source is trying to communicate) with its own Protocol Address (IPv4 Address). Those who do not match will drop the packet without any action.

Step 5: When the targeted device checks the Target Protocol Address, it will find a match and will generate an Address Resolution Protocol (ARP) reply message. It takes the Sender Hardware Address and the Sender Protocol Address fields from the Address Resolution Protocol (ARP) request message and uses these values for the Targeted Hardware Address and Targeted Protocol Address of the reply message.

Step 6: The destination device will update its Address Resolution Protocol (ARP) cache, since it need to contact the sender machine soon.

Step 7: Destination device send the Address Resolution Protocol (ARP) reply message and it will NOT be a broadcast, but a unicast in order to save network resources.

Step 8: The source machine will process the Address Resolution Protocol (ARP) reply from destination, it stores the Sender Hardware Address as the layer 2 address of the destination.

Step 9: The source machine will update its Address Resolution Protocol (ARP) cache with the Sender Hardware Address and Sender Protocol Address it received from the Address Resolution Protocol (ARP) reply message.

Machines at local network can't communicate if they don't know the MAC Address of each other. Neither **Internal IP Address** can be used for that. If a router wants to communicate with its client or with the other router then it must know the MAC Address of its client and the other router as well.


![[Pasted image 20240119122609.png]]