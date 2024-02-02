Alot of crypto is about pushing things into areas which nobody knows how to solve.
``` 
Crypto people wake up in the morning and look at the problems that have not been solved yet -- after a lot of effort, then derive algos that push numbers in that area!
```

## 1 - Intro to Cryptography
### Why cryptography ?
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

### Security Attacks

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
*The decision to implement security at lower or higher layers involves a delicate balance between privacy, end-to-end security, and the level of trust in intermediary entities.*

| Lower layers | Higher layers |
| ---- | ---- |
| **Advantages:**<br>**Information Hiding:** Implementing security at lower layers, such as the link layer with WPA encryption, allows the system to conceal source and destination IP addresses, enhancing user privacy regarding accessed sites. | **Advantages:**<br>**End-to-End Security:** Implementing security at higher layers, like application layer encryption such as PGP, provides end-to-end security. Users don't need to trust intermediate nodes, ensuring a more secure communication path. |
| **Drawbacks:**<br>**Trust in Intermediaries:** Users must trust intermediary entities like Wireless Access Points. If compromised, the user's information may be at risk.   | **Drawbacks:**<br>**Traffic Information Exposure:** Encryption at higher layers doesn't hide source/destination information. Traffic patterns and metadata may be more visible. |
|  |  |
#### What are the Trade-Offs?
At lower layers, protection covers everything in the packet/header, while at higher layers, only the application data is protected. The layers underneath are exposed.
#### So then why not protect everything from layer 2?
Protecting everything from layer 2 presents challenges in key management. Each router in the hop would require an encryption key, making it operationally complex.
- Imagine a castle with multiple layers of defenses. The outermost layer represents lower-layer security, and each subsequent layer adds a level of protection. However, providing a unique key to every guard in the castle (representing routers in a hop) becomes operationally challenging, just like managing encryption keys for every router in a network.

key management - each router in the hop must have an encryption key, like for app layer, everything except the contents is not encrypted - that means everyone can see all the things around it.
think of it like a castle with multiple fortification covers. or ELI5 this better.



## 2 - Encryption
Over the years many encryption algorithms were developed and are classified according to various criteria. 
### Way 1 - Block vs stream cipher
> One way to classify them is based on if they operate over *blocks of data or streams.* 

| Block Cipher | Stream Cipher |
| ---- | ---- |
| A block cipher, takes a chunk of n bits as the plaintext input, and generates an output ciphertext block of *usually the same length. * | Stream ciphers take a continuous stream of plaintext symbols and output a continuous stream of ciphertext symbols. Stream ciphers are more appropriate for some traffic such as voice communication.  |
| Examples, of block ciphers include the current US/NIST standard Advanced Encryption Standard (AES), and the old DES standard which is no longer recommended for use | Examples of stream ciphers include RC4 and GSM A5 algorithm. |
 > We will see later that one can construct a stream cipher from a block cipher by using it in a mode such as cipher block chaining (CBC).


### Way 2 - Symmetric vs Asymmetric
There is another more fundamental way of differentiating cryptographic algorithms in general and encryption algorithms in particular. This separation is called symmetric vs. asymmetric or public key vs. shared key cryptography.

|Symmetric (Shared Key) Encryption|Asymmetric (Public Key) Encryption|
|---|---|
|**Definition:** Symmetric encryption involves using the same key for both encryption and decryption.|**Definition:** Asymmetric encryption uses a pair of keys - a public key for encryption and a private key for decryption.|
|**Operation:** The sender and receiver share a secret key, which is used for both encrypting and decrypting the data.|**Operation:** The sender uses the recipient's public key to encrypt the data, and the recipient uses their private key to decrypt it.|
|**Examples:** Common symmetric encryption algorithms include AES, DES, and 3DES.|**Examples:** RSA and Elliptic Curve Cryptography (ECC) are popular asymmetric encryption algorithms.|
|**Key Distribution:** Requires secure key distribution since the same key is used for both parties.|**Key Distribution:** Public keys can be freely distributed, eliminating the need for secure key exchange.|
|**Computational Complexity:** Generally faster and computationally less complex.|**Computational Complexity:** Slower due to more complex mathematical operations.|
|**Use Cases:** Often used for bulk data encryption.|**Use Cases:** Commonly used for key exchange, digital signatures, and securing communication channels.|





### Early Attack Models

There are several possible assumptions about what is available to the adversary and called 
1.  <font style="color:red">ciphertext only </font> 
	- refers to the scenario where the adversary has only access to encrypted text (i.e., ciphertext)  
	- needs to identify the secret/private key
2. known plaintext: 
	- where the adversary is given to a set of pairs of (plaintext, ciphertext)  
	- needs to identify the secret/private key
3. chosen plaintext:
	 - refers to the scenario where the adversary is allowed to choose plaintexts and can obtain the corresponding ciphertexts,  
	 - needs to identify the secret/private key
4.  <font style="color:red">chosen</font>  ciphertext:
	1. refers to the scenario where the adversary is allowed to  choose ciphertexts and can obtain the corresponding plaintexts 
	3. identify the secret/private key
5. chosen text.
	- refers to the scenario where the adversary is allowed to choose plaintexts or ciphertexts and can obtain the corresponding ciphertexts or plaintexts, and needs to identify the secret/private key

CHOSEN == gets to choose that thing and can obtain the counter part.

### IND CPA
In the context of IND-CPA (Indistinguishability under Chosen Plaintext Attack), the protocol involves: 
1. an adversary selecting two plaintext messages, denoted as M₀ and M₁.
2. An oracle, representing a hypothetical entity performing encryption operations, then randomly chooses a bit 'b' and encrypts the corresponding message Mb.
3. The primary objective is to ensure that, even with knowledge of the encryption of one of the messages, the adversary cannot reliably guess the value of 'b' with a probability significantly better than chance.

This is expressed formally as the adversary's inability to distinguish between the encryptions of M₀ and M₁. 
Mathematically, the goal is to prevent the adversary from gaining a non-negligible advantage in guessing 'b.'

#### Why is it Important:
It guarantees a level of semantic security in encrypted communication, ensuring that *adversaries cannot deduce meaningful information about the plaintexts or distinguish between different messages*, even when actively choosing plaintexts for analysis. 

The property provides protection against chosen plaintext attacks, a scenario where adversaries actively choose plaintexts and observe their corresponding ciphertexts. 

By resisting pattern analysis, *IND-CPA ensures that ciphertexts appear random and unpredictable*, preventing adversaries from exploiting regularities in the encrypted data. The importance of IND-CPA lies in its role in maintaining confidentiality, preventing information leakage, and upholding the integrity of encrypted communication.

#### Implications if a Scheme is Not IND-CPA Secure:
Without this security property, there is an increased risk of information leakage, as adversaries might be able to deduce details about the plaintexts from observing the ciphertexts. 

The vulnerability to chosen plaintext attacks escalates, allowing adversaries to actively exploit their ability to choose plaintexts for analysis. 
The lack of semantic security compromises the confidentiality of the encrypted communication, and *patterns in the ciphertexts may become exploitable,* enabling educated guesses about the plaintexts. 

## 3 - Secret Key Cryptography

We first go over the characteristics two well known encryption algorithms.  
1. `AES`, the *Advanced Encryption Algorithm*, is the current NIST standard approved by the US government. It is a block cipher, accepts blocks of 128 bits, and can use keys of size 128, 192, and 256 bits.  
2. `DES`, the *Data Encryption Standard*, is the old standard that was approved by the US government until the end of the last century. It accepts blocks of 64 bits and keys of 56 bits. It is not safe to use DES today. It is possible to build a machine that can easily break it.

> What are modes? 
There are several ways to use an encryption algorithm to encrypt plaintext. These ways are called modes. The most straightforward way is called the Electronic Code Book (ECB) mode.  

### ECB:  Electronic codebook

![[Pasted image 20240201183240.png]]
Basically, the the plaintext is divided into chunks P1, P2, ..., PN. Each block is encrypted separately using the same key K. The resulting ciphertexts C1, C2, ..., CN can be decrypted independently of each other.  
The is the simplest more for of using an encryption algorithm. It is for example used in challenge-response authentication protocols. However, it is not recommended for encrypting data, especially large chunks of data.  
There are two main reasons. 
1. First, with the same key, a plaintext is always encrypted into the same ciphertext. This would allow to guess a plaintext. For example, consider an email text that starts with “From: Guevara Noubir, To: John Smith”. All such emails will have the first 32 bytes encrypted the same way.
2. Second, an adversary can change the order of ciphertext without being detected, unless additional mechanisms are used.
>   ECB Mode is not IND-CPA secure


### CBC: Cipher block chaining

![[Pasted image 20240201183126.png]]
The most common mode of encrypting a *stream* of data is Cipher Block Chaining (CBC). 
1. ciphertext Ci-1 is xored with Pi before encryption. 
2. The first plaintext P1 is xored with an initialization vector IV. The IV is usually sent in the clear and is used to make sure that if the same plaintext is encrypted multiple times with the same key it would result in different ciphertexts. Some systems make the IV secret and derive it through an authentication and session key establishment protocol. Although, in principle it is not necessary.

$$ C_i = E_k (P_i \oplus C_(i-1)) $$
![[Pasted image 20240201184644.png]]


### CFB :  Cipher feedback (CFB)

![[Pasted image 20240201190016.png]]

While CBC is adequate when the source enough data to fill blocks, it is not optimal when a sender wants to send a small amount of information periodically. Now, consider an example, where the source has only j bits every second and assume that we are using a block encryption algorithm of block size 128 bits. 
CBC would imply either buffering (therefore delay) or padding (therefore low efficiency).  
Cipher Feedback is a technique that allows the transmission of blocks of j bits using an encryption algorithms of larger block size.


## 4 - Hashing
https://www.youtube.com/watch?v=Rk0NIQfEXBA
####  Goal:
The goal of a hashing function $H$ is to transform a long message into a short block, often referred to as a hash or message digest. This transformation serves several purposes, including ensuring data integrity, verifying data authenticity, and creating unique identifiers for messages.
#### Properties:
##### 1. Pre-image Resistance:
- **Definition:** Given a hash $h$, it should be computationally infeasible to find a message MM that produces $h$. In other words, finding $M$ such that $H(M)=h$ should be a challenging task.
- **Importance:** This property ensures that the original message cannot be easily determined from its hash, providing a level of security against reverse engineering.
##### 2. Second Pre-image Resistance:
- **Definition:** Given a specific message $M$, it should be computationally infeasible to find another message $M′$ such that $H(M)=H(M′)$.
- **Importance:** This property ensures that, even if one knows the hash of a particular message, finding another message with the same hash is a difficult task. It adds an additional layer of security against hash collisions.
##### 3. Collision Resistance:
- **Definition:** It should be computationally infeasible to find two distinct messages $M$ and  $M′$ such that $H(M)=H(M′)$.
- **Importance:** This property guards against collisions, where two different inputs produce the same hash. Collision resistance is crucial for the integrity of the hashing function, especially in applications like digital signatures and data verification.

1. **Deterministic:**
    - For the same input, a hash function must always produce the same hash output. This property is crucial for consistency and predictability.
2. **Fast Computation:**
    - The hash function should be computationally efficient, allowing for quick processing of data. This is especially important in applications with large datasets.
3. **Pre-image Resistance:**
    - It should be computationally infeasible to reverse the hash function and determine the original input from its hash value. This property ensures a one-way function.
4. **Collision Resistance:**
    - Collisions occur when two different inputs produce the same hash output. A good hash function minimizes the likelihood of collisions, enhancing the uniqueness of hash values.
5. **Avalanche Effect:**
    - A small change in the input should result in a significantly different hash output. This property ensures that similar inputs generate distinct hash values, contributing to the security and distribution of hash codes.
6. **Fixed Output Size:**
    - The hash function produces a fixed-size output, regardless of the input size. This allows for consistency and simplifies data handling.
7. **Efficient Update:**
    - If a small change is made to the input, the hash function should efficiently update the hash value without rehashing the entire input. This property is important for data integrity checks in dynamic environments.
8. **Resistance to Birthday Attacks:**
    - A good hash function should resist birthday attacks, where an attacker attempts to find two different inputs that produce the same hash value. This involves the concept of the birthday paradox.
9. **Resistance to Manipulation:**
    - It should be difficult for an attacker to manipulate the input data in such a way that the hash output remains unchanged. This is essential for data integrity.
10. **Uniform Distribution:**
    - Hash values should be evenly distributed across the output space. This property helps prevent clustering and ensures that hash codes are uniformly distributed.
11. **Keyed Hashing (for HMAC):**
    - In some applications, such as HMAC (Hash-based Message Authentication Code), a hash function may require a secret key to enhance security.
12. **Non-reversibility:**
    - It should be practically impossible to deduce any information about the input from its hash value, even with advanced computational techniques.

### Birthday Attacks:
[Birthday Attack](https://www.youtube.com/watch?v=4shxYyV4O_k&t=0s)

In the context of hashing functions, a birthday attack exploits the probability of finding two different inputs that produce the same hash value.
Named after the birthday paradox, where the probability of two people sharing the same birthday is higher than intuitively expected.

Birthday attacks take advantage of the probability of collisions increasing as more hashes are computed.
> Can generalize the problem to one wanting a matching pair from any two sets, and show need $2^m/2$ in each to get a matching m-bit hash.

1. Attacker precomputes a set of hash values for various inputs. The attacker stores these hashes for future comparison.
2. As more hashes are computed, the probability of finding a collision increases.
3. The attacker checks whether a newly computed hash matches any of the precomputed values.
4. If a match is found, a collision has occurred.

**Forgery and Fraud:**
    - Attackers may use collisions to create fraudulent data that produces the same hash value as legitimate data.
    - This can lead to unauthorized access, data manipulation, or other malicious activities.
	
**Digital Signatures:**
- Collisions can be exploited to create different messages with the same hash value.
- This poses a threat to digital signatures, where verification relies on the uniqueness of hash value

### Applications:
#### Message Authentication Code (MAC) Using an Encryption Algorithm
- **Goal:** Detect any modification or forgery of the content by an attacker.
##### Using CBC Mode:
- **Approach:** Utilize Cipher Block Chaining (CBC) mode.
- **Strategy:** Send only the last block (residue) along with the plaintext message.
- **Note:** While an improvement, simple CBC mode may still have limitations.
##### Confidentiality + Integrity:
- **Key Usage:** Utilize two keys—one for CBC encryption and one for CBC residue computation.
- **Enhancement:** Append a cryptographic keyed-hash to the message before CBC encryption.
- **Objective:** Combine confidentiality and integrity measures for a comprehensive security approach.

### Authenticated Encryption (AE)
>How can one provide joint Encryption and Integrity protection?

![[Pasted image 20240201195754.png]]

### Galois Counter Mode (GCM):

![[Pasted image 20240201202439.png]]


## 5 - Public Key Systems

### Modular Arithmetic
• Modular addition:  – E.g., $3 + 5 = 1 mod 7$
• Modular multiplication: – E.g., $3 * 4 = 5 mod 7$
• Modular exponentiation: –E.g., $33 =6mod7$

![[Pasted image 20240201203129.png]]

### RSA Cryptosystem

[Great video on how this works](https://www.youtube.com/watch?v=oOcTVTpUsPQ)

![[Pasted image 20240201212418.png]]

$E(M) = M^e$ mod $n = C$ **(Encryption)** 
$D(C) = C^d$ mod $n = M$ **(Decryption)**

**Key Generation:**
1. Select two large prime numbers, \(p\) and \(q\).
2. Compute \(n = pq\).
3. Calculate Euler's totient function, $(\phi(n) = (p-1)(q-1))$.
4. Choose a public exponent (\(e\)) such that $(1 < e < \phi(n))$ and \(e\) is coprime with $(\phi(n))$.
5. Compute the private exponent (\(d\)) such that $(d \equiv e^{-1} \mod \phi(n))$.
6. The public key is \((n, e)\).
7.  The private key is \((n, d)\)

| Encryption | Decryption |
| ---- | ---- |
| Sender obtains the recipient's public key \((n, e)\). | Receiver uses their private key \((n, d)\). |
| Represent the plaintext message as an integer \(m\) where \(0 < m < n\). | Compute $(m \equiv c^d \mod n)$ |
| Compute the ciphertext $(c \equiv m^e \mod n)$ |  |

> For RSA encryption (E) and decryption (D) functions, the following is true: $E(D(M)) = D(E(M)) = M$

### Issues with textbook RSA:



## 6 - Key Exchange
### Diffie-Hellman
**Purpose:** 
Securely exchange cryptographic keys over an insecure communication channel. Allows two parties to independently generate a shared secret key over an insecure channel.

![[Pasted image 20240201205255.png]]

> However, the Diffie-Hellman Key exchange is vulnerable to an active attack called Man-in-the-Middle (MITM) attack.

How Man in the middle works and why its insecure, so we must use RSA
https://www.youtube.com/watch?v=vsXMMT2CqqE