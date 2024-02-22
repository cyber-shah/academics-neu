Pranchal Shah
5700 Computer Networks
## Question 1

![[Pasted image 20240213221157.png]]

## Question 2  
Assuming a framing protocol that uses bit stuffing, show the bit sequence transmitted over the link when the frame contains the following bit sequence: 110101111101011111101011111110.
<mark style="background: #BBFABBA6;">Mark the stuffed bits.</mark>

1101011111 **0** 01011111 **0** 101011111 **0** 110.

## Question 3  
Suppose the following sequence of bits arrives over a link: 1101011111010111110010111110110.  
<mark style="background: #BBFABBA6;">Show the resulting frame after any stuffed bits have been removed. Indicate any errors that might have been introduced into the frame.</mark>

As per HDLC: "HDLC denotes both the beginning and the end of a frame with the distinguished bit sequence `01111110`"
If the reciever sees 5 consecutive 1's, it has to make a decision based on the next bit:
1. if the 6th bit is 1, its the end of the frame.
2. if the 6th bit is 0, it has been stuffed

11010 **11111** 010 **11111** 0010 **11111** 0110  
11010 11111    10 11111    010 11111    110

Therefore the message after removing stuffed bits looks like this:
1101011111101111101011111110
As per the professor "in the context of HDLC the receiver expects no more than 5 consecutive 1's between two consecutive 0's after bit unstuffing. This constraint is important for maintaining synchronization and realiable data recovery at the receiver end. The restriction on the number of consecutive 1's after bit unstuffing is a fundamental aspect of HDLC protocol and any other protocol uses bit stuffing,"
This message has an error, because it has 7 consecutive 1s.


## Question 4  
Suppose we want to transmit the message $11001001$ and protect it from errors using the CRC polynomial  $x^3 + 1$
1. <mark style="background: #BBFABBA6;">Use polynomial long division to determine the message that should be transmitted.</mark>

Let's convert the polynomial to bits:
$$ 1 * x^3 + 0 * x^2 + 0 * x^1 + 1 * x^0 = 1001 $$
![[Pasted image 20240213204825.png]]
Therefore the final message to be sent is 1001001011.

2. <mark class="hltr-green">Suppose the leftmost bit of the message is inverted due to noise on the transmission link. What is the result of the receiver’s CRC calculation? How does the receiver know that an error has occurred?</mark>

If any of the bits change in the message, the receiver can use the same CRC process, take the $k$ bits out of the $m$ bits, and divide it with the CRC polynomial.
If the remainder is 0, they can infer that no error has occurred, if remainder is anything but 0, they can be sure that there was an error while transmission.
As in this case if the bit is flipped the remainder will not be 0, therefore they can be sure that there was an error




