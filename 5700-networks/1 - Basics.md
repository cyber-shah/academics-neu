zoq. what if someone broadcasts messages in the entire internet to fllod it with packets -- and stop everything from working -- how would that be protected against?

## Multiplexing

![Image](https://media.geeksforgeeks.org/wp-content/uploads/20210403142627/Screenshot181.png)


## Sockets

#### What are sockets?
A socket is effectively a type of file handle, behind which can lie a network session.
> It's a programming interface (API) for network communication.

You can `read and write it` (mostly) *like any other file handle* and have the data go to and come from the other end of the session.
A server establishes (binds to) a socket which can be used to accept incoming connections. Upon acceptance, you get _another_ socket for the established session so that the server can go back and listen on the original socket for more incoming connections.
#### Why do we need them?
They serve as the bridge between the application layer and the transport layer in networking. Provided by the Operation system.
##### No such thing as sockets at the physical level
Sockets are not a physical component at the hardware level. Sockets *are a software abstraction* or interface that allows programs (software) to communicate over a network. They are part of the operating system's networking API and **provide a way for applications to send and receive data over a network.**
The concept of sockets comes into play at a higher level of abstraction, allowing software applications to use a standardized interface for network communication.

## Ports

### Why do we need ports?
Lets assume just have IPs. 
IP addresses are used to uniquely identify devices (computers, servers, routers, etc.) on a network. They provide a way for data to be routed from one device to another across the internet or a local network.
Ports, on the other hand, allow multiple services or processes on a single device to share the same IP address. They help in distinguishing different communication endpoints on a device.

Therefore we can conclude the need as :
1. *Multiple Services on One Device.*
	Devices often run multiple services or applications simultaneously (e.g., web server, email server, FTP server). Ports allow these services to coexist and receive data independently.
2. *Granular Control:*
    - Ports offer a granular level of control over network traffic. Firewalls and security measures can be configured based on specific port numbers, allowing or blocking traffic as needed. 
3.  *Communication Multiplexing:*
    - Ports provide a way to multiplex communication on a single device. Instead of having separate IP addresses for each service, ports help manage multiple connections using a single IP address.
## OSI Basics

#### Each layer has its own protocol 
- **IP (Internet Protocol):** This is a ==network layer protocol== responsible for routing and addressing packets of data so that they can be transmitted across networks.
- **TCP (Transmission Control Protocol):** This is a ==transport layer protocol==. It provides reliable, connection-oriented communication between devices. TCP ensures that data is delivered in the correct order and handles retransmission of lost or corrupted data.
- **HTTPS (Hypertext Transfer Protocol Secure):** This is an ==application layer protocol==. HTTPS is an extension of HTTP with the addition of security features provided by TLS/SSL. It's commonly used for secure communication over the web.

In the OSI model, IP is part of the network layer (Layer 3), TCP is part of the transport layer (Layer 4), and HTTPS operates at the application layer (Layer 7). Each layer of the OSI model has specific responsibilities in facilitating communication across a network.



## HTTP:

HTTP headers are components of the HTTP (Hypertext Transfer Protocol) protocol used to transmit *additional information* between the client and the server as part of an HTTP request or response. Headers are included in the HTTP messages' header section, which precedes the actual data being transmitted (such as the HTML content of a webpage or the body of a POST request).

HTTP headers provide metadata about the HTTP message, allowing both the client and server to understand and process the request or response appropriately. There are two main types of HTTP headers: request headers and response headers.
### Request Headers:
1. **Host:**
    - Specifies the domain name of the server (e.g., "[www.example.com](http://www.example.com)") and, optionally, the port number.
2. **User-Agent:**
    - Identifies the user agent (typically the web browser or application) making the request.
3. **Accept:**
    - Informs the server about the types of media that the client can process, usually specifying accepted content types.
4. **Authorization:**
    - Provides credentials for authentication purposes, allowing the client to access protected resources on the server.
5. **Cookie:**
    - Contains information about cookies previously sent by the server to the client. The client includes this header in subsequent requests to maintain session information.
6. **Referer (or Referrer):**
    - Indicates the URL of the page that linked to the current page. This header is often used for analytics and tracking.

### Response Headers:
1. **Content-Type:**
    - Specifies the type of data in the response body (e.g., "text/html" for HTML content or "application/json" for JSON).
2. **Content-Length:**
    - Indicates the length of the response body in bytes.
3. **Server:**
    - Identifies the software or server technology used by the server.
4. **Set-Cookie:**
    - Sends cookies from the server to the client, which will be stored and sent back with subsequent requests.
5. **Location:**
    - Used in redirections to indicate the URL to which the client should navigate.
6. **Cache-Control:**
    - Directs caching behavior, specifying whether a response can be cached and, if so, for how long.

These are just a few examples, and there are many more HTTP headers with specific purposes. Headers play a crucial role in defining the behavior and characteristics of the communication between clients and servers in the HTTP protocol.


# 1.5 Performance

### What Is Bandwidth?
Bandwidth refers to the **maximum amount of data** that can move from one point to another. It’s expressed in megabits per second, or Mbps, that can pass through a network at any given time.
> Think of network connection as a highway; the cars driving along it are data packets. A five-lane highway typically has a bigger bandwidth than a two-lane local road because the former can accommodate a greater number of cars. Because of this, there’s a smaller chance of vehicles getting stuck in traffic jams.

However, having a wide highway will tell you nothing about how fast the cars are going, similar to how bandwidth alone can’t determine the internet speed. It simply tells how much data can be received at a given time.

Remember that stated bandwidth of your users’ network only refers to its theoretical maximum capacity. In practice, the actual amount of data they can send and receive is smaller than this. That’s because most applications and protocols add extra bits or perform more processing, which introduces overhead.

This is called “goodput” or “good throughput.”

### What Is Latency?
Latency refers to the **amount of time** a data packet takes to travel from one point to another, AKA the delay between the time data is sent and received, measured in milliseconds (ms). Whereas bandwidth refers to the **volume** of data sent, latency refers to the **speed** at which it’s transmitted.
> Imagine a 400-seater bus and a 2-seater sports car traveling from New York to San Francisco. The sports car obviously travels faster and reaches its destination much sooner — it goes at a higher speed, which means it has a lower latency. But the bus has a bigger bandwidth because it carries more people in a single trip.

We often think of latency as having three components. 
1. First, there is the speed-of-light propagation delay. This delay occurs because nothing, including a bit on a wire, can travel faster than the speed of light. If you know the distance between two points, you can calculate the speed-of-light latency, although you have to be careful because light travels across different media at different speeds: It travels at 3.0 × 108m/s in a vacuum, 2.3 × 108m/s in a copper cable, and 2.0 × 108m/s in an optical fiber. 
2. Second, there is the amount of time it takes to transmit a unit of data. This is a function of the network bandwidth and the size of the packet in which the data is carried. 
3. Third, there may be queuing delays inside the network, since packet switches generally need to store packets for some time before forwarding them on an outbound link. So, we could define the total latency as
```
Latency = Propagation + Transmit + Queue 
```
$$Propagation = Distance/SpeedOfLight 
$$
$$Transmit = Size/Bandwidth$$


### Bandwidth or Latency?
Bandwidth and latency combine to define the performance characteristics of a given link or channel. Their relative importance, however, depends on the application. For some applications, latency dominates bandwidth. 

For example,: 
1. a client that sends a 1-byte message to a server and receives a 1-byte message in return is latency bound. Assuming that no serious computation is involved in preparing the response, the application will perform much differently on a transcontinental channel with a 100-ms RTT than it will on an across-the-room channel with a 1-ms RTT. *Whether the channel is 1 Mbps or 100 Mbps is relatively insignificant*, however, since the former implies that the time to transmit a byte is 8 μs and the latter implies Transmit = 0.08 μs.
2. In contrast, consider a digital library program that is being asked to fetch a 25-megabyte (MB) image—the more bandwidth that is available, the faster it will be able to return the image to the user. Here, the bandwidth of the channel dominates performance. To see this, suppose that the channel has a bandwidth of 10 Mbps. It will take 20 seconds to transmit the image (25 × 106× 8-bits / (10 × 106 Mbps = 20 seconds), *making it relatively unimportant if the image is on the other side of a 1-ms channel or a 100-ms channel;* the difference between a 20.001-second response time and a 20.1-second response time is negligible.
### When to use high latency vs when to use high bandwith?

| **Latency for Responsiveness:** | **Bandwidth for Large Files:** |
| ---- | ---- |
|  **Scenario:** When the primary concern is responsiveness, such as in applications dealing with small messages or requests (e.g., HTTP, NFS, online gaming), minimizing latency is crucial.<br> | **Scenario:** In situations where the primary task involves transferring large files or data sets, the critical factor is the capacity to transmit a high volume of data. |
| **Reasoning:** Low latency ensures that individual messages or requests are transmitted quickly, leading to faster response times and improved user experience. | **Reasoning:** High bandwidth becomes important for efficiently handling the large amounts of data, ensuring faster file transfers. |
### Delay x Bandiwth Product
Intuitively, if we think of a channel between a pair of processes as a hollow pipe, where the latency corresponds to the length of the pipe and the bandwidth gives the diameter of the pipe, 
>then the delay × bandwidth product gives the volume of the pipe—the maximum number of bits that could be in transit through the pipe at any given instant.

For example, a transcontinental channel with a one-way latency of 50 ms and a bandwidth of 45 Mbps is able to hold 
$$50 \cdot 10^{−3} s × 45 \cdot 10^6 bits/s$$
$$= 2.25 × 10^6 bits$$
or approximately 280 KB of data.

#### why should we know it?
The delay × bandwidth product is important to know when constructing high-performance networks because ==it corresponds to how many bits the sender must transmit before the first bit arrives at the receiver.==

The bits in the pipe are said to be ==“in flight”== which means that if the receiver tells the sender to stop transmitting it might receive up to one RTT × bandwidth’s worth of data before the sender manages to respond. In our example above, that amount corresponds to 5.5 × 106 bits (671 KB) of data. 

On the other hand, ==if the sender does not fill the pipe==—send a whole RTT × bandwidth product’s worth of data before it stops to wait for a signal—the sender will not fully utilize the network.
![](https://www.youtube.com/watch?v=cDVNU2j26Bs)


For a bandwith of 512kbps means you can load 512kb data per second on the link and then the latency of 1000ms would mean we have 1000ms time to load data before it becomes full. or that is the number of times you can refill data before it becomes full.


**Link Capacity:**
- Think of it like the width of a pipe. A bigger pipe can carry more water. Similarly, a link with higher capacity can carry more data.
- Example: If a link has a capacity of 100 Mbps, it means it can carry 100 million bits of data every second.

**Delay Bandwidth Product (DBP):**
- Imagine you have a long tunnel (representing the round-trip time or delay) that you need to send a message through. The DBP is like how many toy cars (representing bits of data) you can have inside the tunnel at once.
- Example: If the round-trip time is 10 seconds and the link capacity is 100 Mbps, the DBP is 1,000 megabits (100 Mbps * 10 seconds).




In short, link capacity is about how wide the pipe is (how much data it can carry per second), and DBP is about how much data you can have in transit in a long tunnel before getting a response.

They help us understand how efficiently we are using the network. 
>If the link capacity is much higher than the DBP, it means we have a wide pipe, but we are not filling it up efficiently. If they are close, it means we are using the network effectively.

