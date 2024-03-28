Generally, electromagnetic waves span a much wider range of frequencies, ranging from radio waves, to infrared light, to visible light, to x-rays and gamma rays. Figure 2.2 depicts the electromagnetic spectrum and shows which media are commonly used to carry which frequency bands.

Whether we want to construct a trivial two-node network with one link or connect the one-billionth host to an existing network like the Internet, we need to address a common set of issues. 
1. First, we need some physical medium over which to make the connection. The medium may be a length of wire, a piece of optical fiber, or some less tangible medium (such as air) through which electromagnetic radiation (e.g., radio waves) can be transmitted. It may cover a small area (e.g., an office building) or a wide area (e.g., transcontinental).

## Classes of Links:
Another important link characteristic is the frequency, measured in hertz, with which the electromagnetic waves oscillate. 
>The distance between a pair of adjacent maxima or minima of a wave, typically measured in meters, is called the wave’s wavelength.

Since all electro-magnetic waves travel at the speed of light (which in turn depends on the medium), that speed divided by the wave’s frequency is equal to its wavelength. We have already seen the example of a voice-grade telephone line, which carries continuous electromagnetic signals ranging between 300 Hz and 3300 Hz; a 300-Hz wave traveling through copper would have a wavelength of
$$SpeedOfLightInCopper ÷ Frequency$$
$$8= 2/3 × 3 × 10^8 ÷ 300$$
$$= 667 × 10^3 meters$$
#### But there's a bunch of problems
1. The first is *encoding bits onto the transmission medium* so that they can be understood by a receiving node. 
2. Second is the matter of *delineating the sequence of bits transmitted over the link into complete messages* that can be delivered to the end node. This is the *framing problem*, and the messages delivered to the end hosts are often called frames (or sometimes packets). 
3. Third, because frames are sometimes corrupted during transmission, it is necessary to detect these errors and take the appropriate action; this is the *error detection problem*. 
4. The fourth issue is making a link appear reliable in spite of the fact that it corrupts frames from time to time. 
5. Finally, in those cases where the link is shared by multiple hosts—as is often the case with wireless links, for example— *it is necessary to mediate access to this link*. This is the media access control problem.

Although these five issues—encoding, framing, error detection, reliable delivery, and access mediation—can be discussed in the abstract, they are very real problems that are addressed in different ways by different networking technologies. 

![[Pasted image 20240206143127.png]]


#### Why do all these links look alike?
These links all look alike not just because we're not very good artists but because part of the role of a network architecture is to provide a common abstraction of something as complex and diverse as a link. *The idea is that your laptop or smartphone doesn't have to care what sort of link it is connected to—the only thing that matters is that it has a link to the Internet.* Similarly, a router doesn't have to care what sort of link connects it to other routers—it can send a packet on the link with a pretty good expectation that the packet will reach the other end of the link.


## Send data physically? ENCODING!
The first step in turning nodes and links into usable building blocks is to understand how to connect them in such a way that bits can be transmitted from one node to the other. As mentioned in the preceding section, signals propagate over physical links. 
> The task, therefore, is to encode the binary data that the source node wants to send into the signals that the links are able to carry and then to decode the signal back into the corresponding binary data at the receiving node.

In practice, these signals might correspond to two different voltages on a copper-based link or two different power levels on an optical link.

![[Pasted image 20240206143841.png]]
### So how do we convert bits into signals for physical layer ?
Most of the functions discussed in this chapter are performed by a network adaptor—a piece of hardware that connects a node to a link. *The network adaptor contains a signalling component that actually encodes bits into signals at the sending node and decodes signals into bits at the receiving node.*


## 3 - Framing

We are focusing on packet-switched networks, which means that blocks of data (called frames at this level), not bit streams, are exchanged between nodes. It is the network adapter that enables the nodes to exchange frames. 
> When node A wishes to transmit a frame to node B, it tells its adapter to transmit a frame from the node's memory. This results in a sequence of bits being sent over the link. 

The adaptor on node B then collects together the sequence of bits arriving on the link and deposits the corresponding frame in B's memory. *Recognizing exactly what set of bits constitutes a frame—that is, determining where the frame begins and ends—is the central challenge faced by the adapter.*

There are three ways to address the framing problem:
#### 1. Byte-Oriented Protocols (PPP)
The first is to use special characters known as sentinel characters to indicate where frames start and end. The idea is to denote the beginning of a frame by sending a special SYN (synchronization) character. The data portion of the frame is then sometimes contained between two more special characters: STX (start of text) and ETX (end of text). 

The problem with the sentinel approach, of course, is that one of the special characters might appear in the data portion of the frame. The standard way to overcome this problem by "escaping" the character by preceding it with a DLE (data-link-escape) character whenever it appears in the body of a frame; the DLE character is also escaped (by preceding it with an extra DLE) in the frame body. 
*This approach is often called character stuffing because extra characters are inserted in the data portion of the frame.*

The Point-to-Point Protocol (PPP), which is commonly used to carry Internet Protocol packets over various sorts of point-to-point links, uses sentinels and character stuffing. The format for a PPP frame is given in Figure 2.
![[Pasted image 20240207125011.png]]
The special start-of-text character, denoted as the Flag field is 01111110 . The Address and Control fields usually contain default values and so are uninteresting. The (Protocol) field is used for demultiplexing; it identifies the high- level protocol, such as IP. The frame payload size can be negotiated, but it is1 500 bytes by default. The Checksum field is either 2 (by default) or 4 bytes long.

#### 2. HDLC
HDLC denotes both the beginning and the end of a frame with the distinguished bit sequence `01111110` . Because this sequence might appear anywhere in the body of the frame—in fact, the bits `01111110` might cross byte boundaries—bit-oriented protocols use the analog of the DLE character, a technique known as bit stuffing.
![[Pasted image 20240207125219.png]]
Bit stuffing in the HDLC protocol works as follows. 
1. On the sending side, any time five consecutive 1s have been transmitted from the body of the message (i.e., excluding when the sender is trying to transmit the distinguished `01111110` sequence), the sender inserts a 0 before transmitting the next bit. 
2. On the receiving side, should five consecutive 1s arrive, the receiver makes its decision based on the next bit it sees (i.e., the bit following the five 1s). If the next bit is a 0, it must have been stuffed, and so the receiver removes it. If the next bit is a 1, then one of two things is true: 
	1. <mark style="background: #ABF7F7A6;">Either this is the end-of-frame marker </mark> or an error has been introduced into the bit stream. By looking at the next bit, the receiver can distinguish between these two cases. If it sees a 0 (i.e., the last 8 bits it has looked at are `01111110` ), then it is the end-of-frame marker; 
	2. if it sees a 1 (i.e., the last 8 bits it has looked at are 01111111 ),<mark style="background: #ABF7F7A6;"> then there must have been an error and the whole frame is discarded.</mark> In the latter case, the receiver has to wait for the next 01111110 before it can start receiving again, and, as a consequence, there is the potential that the receiver will fail to receive two consecutive frames. Obviously, there are still ways that framing errors can go undetected, such as when an entire spurious end-of-frame pattern is generated by errors, but these failures are relatively unlikely. 

## 4 - Error Detection
The basic idea behind any error detection scheme is to add redundant information to a frame that can be used to determine if errors have been introduced. 
> In general, the goal of error detecting codes is to provide a high probability of detecting errors combined with a relatively low number of redundant bits.

In general, we can provide quite strong error detection capability while sending only k redundant bits for an n-bit message, where k is much smaller than n. On an Ethernet, for example, a frame carrying up to 12,000 bits (1500 bytes) of data requires only a 32-bit CRC code, or as it is commonly expressed, uses CRC-32. Such a code will catch the overwhelming majority of errors.

One note on the terminology for these extra bits. In general, they are referred to as error-detecting codes. In specific cases, when the algorithm to create the code is based on addition, they may be called a checksum. We will see that the Internet checksum is appropriately named: It is an error check that uses a summing algorithm.

#### Internet Checksum Algorithm
The idea behind the Internet checksum is very simple—you add up all the words that are transmitted and then transmit the result of that sum. The result is the checksum. The receiver performs the same calculation on the received data and compares the result with the received checksum. If any transmitted data, including the checksum itself, is corrupted, then the results will not match, so the receiver knows that an error occurred.

Consider, for example:
1. the addition of -5 and -3 in ones' complement arithmetic on 4-bit integers: 
2. +5 is 0101, so -5 is 1010; 
3. +3 is 0011, so -3 is 1100. 
4. If we add 1010 and 1100, ignoring the carry, we get 0110. 
5. In ones' complement arithmetic, the fact that this operation caused a carry from the most significant bit causes us to increment the result, giving 0111, which is the ones' complement representation of -8 (obtained by inverting the bits in 1000), as we would expect.

#### Cyclic Redundancy Check
When a sender wishes to transmit a message $M(x)$ that is n+1 bits long, 
what is actually sent is the ($n+1)$-bit message plus $k$ bits. 
We call the complete transmitted message, including the redundant bits, $P (x)$. 

What we are going to do is contrive to make the polynomial representing $P(x)$ *exactly divisible* by $C(x)$. 
1. If $P(x)$ is transmitted over a link and there are no errors introduced during transmission, then the receiver should be able to divide $P(x)$ by $C(x)$ exactly, leaving a remainder of zero. 
2. On the other hand, if some error is introduced into $P (x)$ during transmission, then in all likelihood the received polynomial will no longer be exactly divisible by $C(x)$, and thus the receiver will obtain a nonzero remainder implying that an error has occurred.

![CRC Solving method](https://www.youtube.com/watch?v=5Q-Yv6_0Qcw)
##### Polynomial rules
- Any polynomial $B(x)$ can be divided by a divisor polynomial $C(x)$ if $B(x)$ is of higher degree ￼￼3.  Clock-Based Framing (SONET)￼…￼
​￼￼￼than $C(x)$.  
- Any polynomial $B(x)$ can be divided once by a divisor polynomial $C(x)$ if $B(x)$ is of the same degree as $C(x)$.
- The remainder obtained when $B(x)$ is divided by $C(x)$ is obtained by performing the exclusive OR (XOR) operation on each pair of matching coefficients.

For example, the polynomial $x^3 +1$ can be divided by $x^3 + x^2 +1$ (because they are both of degree 3) and the remainder would be $$ 0×x^3 + 1×x^2 +0×x^1 +0×x^0 =x^2 $$ (obtained by XORing the coefficients of each term). 
In terms of messages, we could say that 1001 can be divided by 1101 and leaves a remainder of 0100.

We wanted to create a polynomial for transmission that is derived from the original message $M(x)$, is $k$ bits longer than $M(x)$, and is exactly divisible by $C(x)$. We can do this in the following way:
1. Multiply $M (x)$ by $x^k$ ; that is, add $k$ zeros at the end of the message. Call this zero-extended message $T (x)$. 
2. Divide $T(x)$ by $C(x)$ and find the remainder.  
3. Subtract the remainder from T (x).





## 5 - Reliable Transmission
Reliable delivery is usually accomplished using a combination of two fundamental mechanisms—acknowledgments and timeouts. 
1. An acknowledgment (ACK for short) is a small control frame that a protocol sends back to its peer saying that it has received an earlier frame. By control frame we mean a header without any data, although a protocol can piggyback an ACK on a data frame it just happens to be sending in the opposite direction. The receipt of an acknowledgment indicates to the sender of the original frame that its frame was successfully delivered. 
2. If the sender does not receive an acknowledgment after a reasonable amount of time, then it retransmits the original frame. This action of waiting a reasonable amount of time is called a timeout.
#### Stop-and-Wait
The idea of stop-and-wait is straightforward: After transmitting one frame, the sender waits for an acknowledgment before transmitting the next frame. If the acknowledgment does not arrive after a certain period of time, the sender times out and retransmits the original frame.
![[Pasted image 20240208142510.png]]

In both cases, the sender times out and retransmits the original frame, but the receiver will think that it is the next frame, since it correctly received and acknowledged the first frame. This has the potential to cause duplicate copies of a frame to be delivered. 
> To address this problem, the header for a stop-and-wait protocol usually includes a 1-bit sequence number—that is, the sequence number can take on the values 0 and 1—and the sequence numbers used for each frame alternate

*The main shortcoming of the stop-and-wait algorithm is that it allows the sender to have only one outstanding frame on the link at a time, and this may be far below the link's capacity.* 

Consider, for example, a 1.5-Mbps link with a 45-ms round-trip time. This link has a delay × bandwidth product of 67.5 Kb, or approximately 8 KB. Since the sender can send only one frame per RTT, and assuming a frame size of 1 KB, this implies a maximum sending rate of
$$Bits Per Frame ÷ Time Per Frame$$
$$= 1024 × 8 ÷ 0.045$$
$$= 182 kbps$$

or about one-eighth of the link's capacity. To use the link fully, then, we'd like the sender to be able to transmit up to eight frames before having to wait for an acknowledgment.

> Key Takeaway
> The significance of the delay × bandwidth product is that it represents the amount of data that could be in transit. We would like to be able to send this much data without waiting for the first acknowledgment. The principle at work here is often referred to as keeping the pipe full.

##### Nice analogy about an industry and road:
So, in your example:
- **DBP (max amount of data in flight):** Let's say it's like having 10 cars on the road at once.
- **Maximum Sending Rate:** It's like being able to send cars really fast, let's say at 12 cars per hour.
If the maximum sending rate is greater than the DBP, it means we're driving cars faster than we need to wait for signals, and we're making the best use of the road.

- **Road Capacity (Link Capacity):** Let's say the road can physically hold 12 cars at once. In the context of networking, this is like saying your network link has a certain capacity, let's say 12 units of data.
- **Sending Rate:** This is how fast you release cars onto the road. If you release fewer cars than the road can hold (let's say less than 12), it means you're not utilizing the road efficiently because you have the capacity to send more.
- **Efficient Use:** Now, if you release cars at a rate faster than the road can physically hold them (let's say 15 cars), it means you're sending them quickly, and you're making good use of the road's capacity. Even though the road can only physically hold 12 cars at once, you're sending cars at a rate that keeps the road busy, and you're not waiting around much between sending each car.

In networking terms, this corresponds to efficiently utilizing the link capacity. If you're sending data at a rate higher than the link's capacity, it means you're using the link efficiently, even though the link can physically handle a certain amount at once.
However, it doesn't mean we're sending double the amount of traffic in two lanes at once. It just means we're utilizing the road efficiently, sending cars without waiting too much in between.


##### Drawbacks:
1. Sends only one frame at a time.
2. Therefore poor utilization of bandwidth
3. Poor performance as does not use bandwidth fully.

#### Sliding Window protocol
To solve that problem, we can send multiple frames at a time.
Number of frames are decided based on a WINDOW SIZE.
- Each frame is numbered.

![](https://www.youtube.com/watch?v=LnbvhoxHn8M)

![[1709154535_grim.png]]

#### Window sizes
The sending window size is selected according to how many frames we want to have outstanding on the link at a given time; ==SWS is easy to compute for a given delay × bandwidth product. ==

On the other hand, the receiver can set RWS to whatever it wants. Two common settings are 
1. RWS = 1, which implies that the receiver will not buffer any frames that arrive out of order, and 
2. RWS = SWS, The receiver can buffer frames up to the size of the sending window. It can accept frames out of order and reorder them as needed.

It makes no sense to set RWS > SWS since it’s impossible for more than SWS frames to arrive out of order.

#### Finite Sequence Numbers 
Sequence numbers are specified in a header field of finite size (e.g., a 3-bit field allows eight possible sequence numbers, 0 to 7).

Due to the finite representation, sequence numbers wrap around, creating the need to distinguish between different occurrences of the same sequence numbers. *The number of possible sequence numbers must be larger than the number of outstanding frames allowed to distinguish between different occurrences of the same sequence numbers.*

If $SWS ≤ MaxSeqNum − 1$, it is generally sufficient, but the adequacy depends on the Receiving Window Size (RWS).
- If RWS = 1, then $MaxSeqNum ≥ SWS + 1$ is sufficient.
- If RWS = SWS, then $SWS < (MaxSeqNum + 1)/2$ is a more precise condition to avoid issues of sequence number confusion.

Why?
To see this, consider the situation in which we have the eight sequence numbers 0 through 7, and SWS = RWS = 7. Suppose the sender transmits frames 0 . . . 6, they are successfully received, but the ACKs are lost. The receiver is now expecting frames 7, 0 . . . 5, but the sender times out and sends frames 0 . . . 6. Unfortunately, the receiver is expecting
the second incarnation of frames 0 . . . 5 but gets the first incarnation of these frames. This is exactly the situation we wanted to avoid.