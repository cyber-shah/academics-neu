Internet was designed to be decentralized such that it shouldn't stop if one part of it was destroyed.
1. Concatenation of networks (stitching of networks)
Which is possible as far as you have IP. then you can control whatever protocol you want to use.

PROTOCOL STACK IMAGE.

TCP was always put at the edge, not at the routers. 
END NODES = 
CORE = Routers in between.


### IP service model
- IP connection-less - 
	- no guarantee that your packets will be received. 
	- due to congestion where routers throw away packets.
- Best effort delivery - packets can be delayed.
	- due to router queues

IP basically allows two nodes to talk to each other.
You send it to destination and what does it do with it? Uses a protocol to work with it.
So need to put that info in the ip packet -- so the destination knows how to work with it. (TCP or UDP or whatever.)

#### TTL : Time to Live
Number of hops a packet can take. Every time a router passes it TTL is decremented. 
Used to prevent packets from going in an infinite loop, and congesting the network.
> How can TTL be decided? what if its important like payment or something.

Integrity protection: IP doesn't have any protection inherently and we can't stop packets from being manipulated. All we have to focus on is identifying it.
#### Fragmentation
Cut packets if the router cannot handle larger sizes.
Use Identifier and offset. 
> De Fragmentation would never happen until it reaches the destination.
##### MTU
Each packet has a flag saying Fragment  or `don't fragment`. If that is found the router sends the packet back saying couldn't send this packet. The source then reformats it into fragments that could be sent.


## Scaling IP Adresses.
### CIDR
#### Subnetting
- Subnet number and a mask

#### Supernetting


## IP over LAN

