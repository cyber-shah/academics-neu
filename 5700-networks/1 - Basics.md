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
# Performance
#### Bandwidth:
 
 **Definition:** Bandwidth refers to the maximum rate at which data can be transmitted over a network or communication channel. It is often measured in bits per second (bps) or multiples thereof (e.g., kilobits per second, megabits per second).
**Analogy:** Think of bandwidth as the width of a pipe; a wider pipe allows more water (data) to flow through at a time.

#### Latency:
 **Definition:** Latency is the delay or time it takes for data to travel from the source to the destination in a network. It is often expressed in milliseconds (ms) or microseconds (µs).
 
 **Components of Latency:**
        - **Propagation Delay:** The time it takes for a signal to travel from the sender to the receiver. This depends on the physical distance between the devices.
        - **Transmission Delay:** The time it takes to push all the bits of a packet onto the communication channel.
        - **Queuing Delay:** The time a packet spends waiting in a network device's queue before being transmitted.
        - **Processing Delay:** The time it takes for routers, switches, or other network devices to process the packet.

**Analogy:** Latency is like the time it takes for a letter to be sent from one location to another; it includes the time it spends in transit, in processing centers, and in delivery.

Certainly! Here are notes summarizing the concept using the analogy of a hollow pipe to explain latency and bandwidth:

## Latency v/s Bandwith:

1. **Analogy:**
    - The communication channel between processes is envisioned as a hollow pipe.
    - **Latency:** Represented as the length of the pipe, indicating the time it takes for data to traverse the channel.
    - **Bandwidth:** Analogous to the width of the pipe, denoting the maximum rate of data transmission.
2. **Given Example:**
    - Latency: 50 milliseconds (ms)
    - Bandwidth: 45 Megabits per second (Mbps)
3. **Calculation for Data Transfer:**
    - Convert latency to seconds: 50 ms = 50 x 10^(-3) seconds.
    - Multiply latency (in seconds) by bandwidth (in bits per second).
    - Result: 2.25 x 10^6 bits.
4. **Conversion and Interpretation:**
    - Convert the total bits to kilobits: 2.25 x 10^6 bits = 280 kilobytes (KB).
    - This represents the amount of data that can be transferred within the given latency period.
5. **Key Takeaways:**
    - Latency influences the time it takes for data to travel through the channel.
    - Bandwidth determines the maximum rate of data transmission.
    - The analogy helps visualize how the length and width of the "pipe" affect the efficiency of data transfer.
6. **Conclusion:**
    - Understanding the relationship between latency and bandwidth is crucial for optimizing network performance.
    - The hollow pipe analogy provides a clear representation of how these factors impact data transmission in a network.



### When to use high latency vs when to use high bandwith?
| **Latency for Responsiveness:** | **Bandwidth for Large Files:** |
| ---- | ---- |
|  **Scenario:** When the primary concern is responsiveness, such as in applications dealing with small messages or requests (e.g., HTTP, NFS, online gaming), minimizing latency is crucial.<br> | **Scenario:** In situations where the primary task involves transferring large files or data sets, the critical factor is the capacity to transmit a high volume of data. |
| **Reasoning:** Low latency ensures that individual messages or requests are transmitted quickly, leading to faster response times and improved user experience. | **Reasoning:** High bandwidth becomes important for efficiently handling the large amounts of data, ensuring faster file transfers. |
### Why?
**Increased Overhead:** Transmitting smaller packets can result in increased overhead. Overhead refers to the additional information, such as headers, added to each packet for proper routing and identification. With more packets, the overhead per unit of data becomes relatively larger.
