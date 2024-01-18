
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

