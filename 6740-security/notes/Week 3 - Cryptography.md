Week 2 - kinds of tools and hacks to attack networks
scanning networks like traceroute etc to be practiced in labs

## Terminology
1. Authentication
	- two entities, A wants to verify B is who it says it is. Verify identities. Is B the entity it claims to be it is?
	- Auth is a protocol. 
1. Confidentiality
2. Integrity
3. Access control
4. Non repudiation
5. Availability
6. Key Management
7. Auditing
#### Authentication


Auth data, its not necessarily online. 
#### Confidentiality
Communication between A and B should not be accessed by a third party C.
Subtle things need to be clearly defined, if partial data is visible, how much partial data - such as senders, receivers, etc.

#### Integrity
No adversary should be able to modify messages/communication `without being detected`. They can modify for sure, any router can modify, but the important part is DETECTION.

#### Access control

#### Non repudiation
Tying action to a particular user.
Non repudiation **provides proof of the origin, authenticity and integrity of data**. It provides assurance to the sender that its message was delivered, as well as proof of the sender's identity to the recipient. 
> This way, neither party can deny that a message was sent, received and processed.

#### Key Management


#### Auditing



### Informal Security Attacks

Active vs passive: 
Passive is when the advisery is not interacting with the system. They don't want to call attention to themselves.

Active is when they are.

> So then if they are not really interacting, How can we detect passive attacks?

Lure the adversary! they don't want to be passive forever. Put honeypots!

> DOLEV YAO model

# PUT HONEY POTS in your project




### Kerchoff's Principle
"When you build a crypto system the only security is key. "


what are the tradeofss for protection at lower vs higher layers.
on a header/packet, everything under the protection layer is unprotected
lets say we protect at app layer, we reveal everything under it -- receiver, sender etc.


So then why not protect everything from layer 2?
key management - each router in the hop must have an encryption key, like for app layer, everything except the contents is not encrypted - that means everyone can see all the things around it.
think of it like a castle with multiple fortification covers. or ELI5 this better.


![[Pasted image 20240123102244.png]]





##### how does phone tapping work

##### How does military encrypt data at physical layer so we can't even scan that there is a communication
##### one time pad
##### German ENGIMA

## Encrypted communication
Block vs stream cipher
block = input of n bits, output of n bits
same bits of output

stream = ![[Pasted image 20240123105857.png]]

### Symmetric vs assymetric
assyemtric - encrypt key and decrypt key is different
so one key can be used only for encryption. and you can give it to everyone, they can encrypt messages and send it to you. only you can decrypt it
``





# Hashing

## Birthday attacks

why? adversary wants to replace one message with other so that the 
find collisions without having to explore all the space.

usually for 64 bits the adversary needs to look at only 2^32 bits.

why are collisions good for an adversary?

two mesasges with the same hash

Hash of M1 = Hash of M2
![[Pasted image 20240126101831.png]]

#### Applications
1. Authentication - In authentication systems, when users create a new account and input their chosen password, the application code passes that password through a hashing function and stores the result in the database. When the user wants to authenticate later, the process is repeated and the result is compared to the value from the database. If it’s a match, the user provided the right password.
2. Encryption - 
3. Message Authentication Codes -

HMAC

![[Pasted image 20240126105536.png]]
question on this in the quiz next week, p and q will be given.

https://en.wikipedia.org/wiki/RSA_%28cryptosystem%29



RSA
$(M^e)^d$  where e is something that only you know and d is known by everyone
and $(M^d)^e$ and then you use e to decrypt the message.

Flaws in using textbook RSA

what is IND CPA security
check jupyter notebook













