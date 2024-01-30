
## Why cryptography ?
In previous lectures we discussed the Internet architecture, and its key networking protocols specifically routing, transport, naming, and some important applications layer protocols. The understanding of these protocols is necessary to discuss the security of networks.  

We also had an overview of non-cryptographic network security issues and tools. We specifically discussed typical steps for attacks such as reconnaissance, information gathering, probes, etc. Most of these techniques and tools, both for defense and attacks, are not based on cryptographic mechanisms. They are however essential to mitigate common technical and non-technical attacks such as social engineering. They are further explored in the laboratories that are part of this course.  

Before we delve into the design of crypto-based network security services, and standards, we first need to have a good understanding of the fundamentals of cryptography. Cryptography provides the elementary (or atomic) blocks to build network security services such as encryption algorithms, hashing functions, etc.

> Cryptography provides the key building blocks for many network security services

Cryptographic algorithms (building blocks)  
– Encryption:  
• Symmetric Encryption (e.g., AES), Asymmetric Encryption (e.g., RSA, El-Gamal)  
– Hashing functions  
– Message Authentication Code (e.g., HMAC + SHA256)  
– Authenticated Encryption (e.g., GCM)  
– Digital Signature functions (e.g., RSA, El-Gamal)  
– Cryptographic Random Numbers Generation


### Terminology

**Authentication**
- two entities, A wants to verify B is who it says it is. Verify identities. Is B the entity it claims to be it is?
- Auth is a protocol. 
- Auth data, its not necessarily online. 

**Confidentiality**
- Communication between A and B should not be accessed by a third party C.
- Subtle things need to be clearly defined, if partial data is visible, how much partial data - such as senders, receivers, etc.

**Integrity**
- No adversary should be able to modify messages/communication `without being detected`
- They can modify for sure, any router can modify, but the important part is DETECTION.

**Access Control**
> Determines what actions entities (users or systems) are allowed to perform within a system.
- Involves the enforcement of policies and rules to restrict unauthorized access.
- Authentication is often a prerequisite for effective access control.

**Non repudiation**
> Tying action to a particular user.
- Non repudiation provides proof of the origin, authenticity and integrity of data. It provides assurance to the sender that its message was delivered, as well as proof of the sender's identity to the recipient. 
> This way, neither party can deny that a message was sent, received and processed.

**Availability**
- Ensures that resources and services are consistently accessible and operational.
- Protection against denial-of-service (DoS) attacks, system failures, or other disruptions.
- Involves redundancy, fault tolerance, and effective resource management.

**Key Management**
> Involves the generation, distribution, storage, and revocation of cryptographic keys.
- Crucial for maintaining the confidentiality and integrity of communication.
- Proper key management is essential for secure encryption and authentication.

**Auditing**
> Monitoring and recording activities within a system or network for later analysis.
- Helps in identifying security incidents, policy violations, or suspicious behavior.
- Enables accountability and can serve as evidence during investigations.

##  Security Attacks

**Active vs passive:** 
Passive is when the advisory is not interacting with the system. They don't want to call attention to themselves.
Active is when they are.
> So then if they are not really interacting, How can we detect passive attacks?

Lure the adversary! they don't want to be passive forever. Put honeypots!
> DOLEV YAO model : "Adversary controls the medium can intercept, inject, modify, replay messages"

### Passive Attacks:

**1. Release of Message Content:**
- Involves unauthorized access to and disclosure of the actual content of a message.
- Attackers gain insights into sensitive information, potentially leading to privacy breaches or exposure of confidential data.

**2. Traffic Analysis:**
- Focuses on the patterns, volumes, and frequencies of communication rather than the message content.
- Helps attackers identify relationships, activities, or potential upcoming events based on the observed traffic patterns.
- Examples include analyzing communication frequencies between CEOs for clues about a possible merger or observing military communication for signs of imminent missions.

### Informal Security Attacks (Kent's Classification):

**1. Masquerade:**
- Occurs when an attacker impersonates a legitimate entity to gain unauthorized access.
- Can involve using stolen credentials or manipulating data to appear as a trusted user or system.

**2. Replay:**
- Involves the interception and subsequent retransmission of valid messages to gain unauthorized access or cause disruptions.
- Attackers may capture and replay authentication credentials or previously valid transactions.

**3. Modification of Message:**
- Unauthorized alteration of the content of a message during transmission.
- Aims to manipulate information, potentially leading to misinformation or compromise of data integrity.

**4. Denial of Service (DoS):**
- Intentionally overloads or disrupts a system, service, or network to make it unavailable to users.
- Attacks can be achieved through flooding the target with traffic or exploiting vulnerabilities to exhaust resources.

**5. Security Attacks (Interception, Interruption, Modification, Fabrication):**
- Interception (Confidentiality): Unauthorized access to or disclosure of information to unauthorized entities.
- Interruption (Availability): Disruption of services or resources, making them temporarily or permanently unavailable.
- Modification (Integrity): Unauthorized alteration of data, compromising its accuracy or reliability.
- Fabrication (Authenticity): Creation and introduction of false information to deceive recipients about the origin or legitimacy of a message.





***
### Kerchoff's Principle
```
"The cipher should be secure even if the intruder knows all the details of the  
encryption process except for the secret key"
```

When designing a security mechanism be it an encryption algorithm, a hashing function, an authentication protocol, or a secure instant messaging application; **it** **is not acceptable to assume that your design is secret.** The only thing that can be assumed to be secret is the key (or password), although you need to account for cases when the key is compromised. This open design is then subjected to the scientific and security community analysis to determine its robustness. Not making the design public only delays the process of adversaries figuring it out,  
which usually results in more catastrophic outcomes as the flaws get discovered too late.

##### Why?
==Several closed or proprietary cryptograph algorithms paid the price for not following this principle.== Examples are the GSM A3/A8 and A5 algorithms. These algorithms were only available under NDA until the general design of A3/A8 leaked in 1997, then the algorithms were fully reverse engineered and a cloning attack ensued in 1999. A5 followed a similar path. The WiFi WEP security protocol was also not widely analyzed within the cryptography community until its adoption and was broken a few years after its release. Wi-Fi  security is still recovering from the impact of this mistake.  

### Securing Networks
![[Pasted image 20240123102244.png]]

In a networked system, security mechanisms can be implemented at different layers of the stack. There are advantages and drawbacks to implementing security at lower of higher layers.
> Implementing security at lower layers allows the system to hide more information, but requires that the user trusts more intermediary entities. 

For example, encryption at the link layer such as WPA hides the source and destination IP addresses therefore protecting the privacy of the users in terms of sites they access. However, if the Wireless Access Point is compromised or not trusted, the users information leaks.  
 > Implementing security at higher layers allows for end-to-end security but reveals more information about the traffic.  
 
Encryption at the application layer such as PGP allows the user not to trust the mail server or other intermediate nodes, however it does not hide the information of the source/destination of messages.
##### what are the tradeofss for protection at lower vs higher layers.
on a header/packet, everything under the protection layer is unprotected
lets say we protect at app layer, we reveal everything under it -- receiver, sender etc.
##### So then why not protect everything from layer 2?
key management - each router in the hop must have an encryption key, like for app layer, everything except the contents is not encrypted - that means everyone can see all the things around it.
think of it like a castle with multiple fortification covers. or ELI5 this better.

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



## Random is at the foundation, its strength determines the strength of everything above it








