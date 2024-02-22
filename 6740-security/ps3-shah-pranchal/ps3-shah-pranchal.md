# Problem 1
##### 1. Why can't the source just use any message authentication code mechanism based on symmetric key crypto (e.g., HMAC) if we assume that all the receivers share the same secret with the source (i.e., what is the threat)?
Two reasons:
- An attacker could intercept the authenticated data and later resend it to the receivers. This replayed data would be accepted by the receivers since they share the same secret key with the source. As a result, receivers would be unable to distinguish between the original and the replayed data.
- Without additional measures, the receivers have no way to verify the authenticity of the source. Even if the data integrity is protected using HMAC, there's no assurance that the data truly originates from the legitimate source. This lack of source authentication leaves the system vulnerable to impersonation or a man-in-the-middle (MITM) attacker.
##### 2. Now, let us assume that the sender uses asymmetric cryptography to sign each packet using his private key. Use `openssl` (hint: check the `speed` command) to estimate:
1. How many packets can the transmitter send per second using an RSA key of size 512, 1024, 2048, and 4096 bits keys?

``` bash
Doing 512 bits private rsa sign ops for 10s: 316073 512 bits private RSA sign ops in 9.98s
Doing 512 bits private rsa encrypt ops for 10s: 3850227 512 bits public RSA encrypt ops in 9.77s

Doing 1024 bits private rsa sign ops for 10s: 100397 1024 bits private RSA sign ops in 9.98s
Doing 1024 bits private rsa encrypt ops for 10s: 1049042 1024 bits public RSA encrypt ops in 9.92s

Doing 2048 bits private rsa sign ops for 10s: 13498 2048 bits private RSA sign ops in 10.00s
Doing 2048 bits private rsa encrypt ops for 10s: 405274 2048 bits public RSA encrypt ops in 9.94s

Doing 4096 bits private rsa sign ops for 10s: 992 4096 bits private RSA sign ops in 10.00s
Doing 4096 bits private rsa encrypt ops for 10s: 101997 4096 bits public RSA encrypt ops in 9.97s

```
2. How many packets can the receiver verify per second for RSA key size of 512, 1024, 2048, 4096 bits keys?
``` bash
Doing 512 bits private rsa decrypt ops for 10s: 269878 512 bits private RSA decrypt ops in 9.99s
Doing 512 bits public rsa verify ops for 10s: 5532639 512 bits public RSA verify ops in 9.98s

Doing 1024 bits public rsa verify ops for 10s: 1256268 1024 bits public RSA verify ops in 9.99s
Doing 1024 bits private rsa decrypt ops for 10s: 88640 1024 bits private RSA decrypt ops in 9.98s

Doing 2048 bits public rsa verify ops for 10s: 506812 2048 bits public RSA verify ops in 9.99s
Doing 2048 bits private rsa decrypt ops for 10s: 7750 2048 bits private RSA decrypt ops in 9.98s

Doing 4096 bits public rsa verify ops for 10s: 65145 4096 bits public RSA verify ops in 9.99s
Doing 4096 bits private rsa decrypt ops for 10s: 2019 4096 bits private RSA decrypt ops in 9.98s
```
3. Why is the signing time different from the verification time? Would the length of the packet matter very much in the signature/verification time?

Signing is slower than verifying because the math behind the processes:
- Signing  = $S≡ M^d \mod n$
- Verifying = $M′≡(S′)^e \mod n$
The private key exponent $(d)$ is usually higher than public key exponent $(e)$ and therefore private key operations are generally more computationally intensive, than public key ops.

4. Indicate the specs of the machine you used to run openssl
> Dell Alienware X14 : 12th Gen Intel i7-12700H @ 4.6GHz with 32GB RAM
##### 3. If the sender node does not have the capability to sign all the packets individually using its private key, propose a design for a technique to amortize the signatures and trades-off the computation with a delay in verification. Discuss potential issues with this approach.
The sender can batch sign packets instead of signing packets individually. This means that the sender divides the packets into batches and then sends the signature of the batch at the end. 

Receiver waits for an entire batch to arrive and then verifies them with the corresponding signature, if the batch is valid it deals with it otherwise lets the sender know and discards the batch.

This approach is computationally efficient but introduces a delay in verification as the receiver has to wait for the verification process to complete. And this tradeoff can be balanced by choosing a good batch size that works well in that context.



# Problem 2
Assume that A and B share a secret K_{AB}. Consider the following authentication protocol for mutually authenticating A with B: 
1. $A \rightarrow B:$ I am $A, R_1$
2. $B \rightarrow A: R_2 , K_{AB}\{A\}, K_{AB}\{B\}, K_{AB}\{R_1\}$
3. $A \rightarrow B: K_{AB}\{B\}, K_{AB}\{A\}, K_{AB}\{R_2\}$

#### Is this authentication protocol susceptible to attacks? Explain.
Yes, this is susceptible to attacks. One simple attack would be an initiator that challenges the other side first, waits for R2.. 
The attacker then uses the same R2 to initiate a new connection, and get the encrypted R2, sends that R2 back from Connection 1, and gets authenticated. Essentially replaying the encrypted R to authenticate themselves as A. 
![[Pasted image 20240220183639.png]]
#### If it is susceptible to attacks, propose the simplest and most elegant fix you can think of.
The simplest fix would be always ask the initiator to solve the challenge first, and authenticate themselves. In this way we can be sure that the initiator cannot attack the other side. 
>Therefore the initiator must prove his identity first.

This prevents the possiblity of an attacker T initiating two sessions with B. 


# Problem 3
#### RSA
Consider the two primes _p_ = 23; _q_ = 17; encryption exponent _e_ = 3
1. Compute the decryption component: d = ...
2. Encrypt plaintext message _M_ = 4
3. Decrypt ciphertext message _M_ = 2

![[Quick sheets - page 3.svg]]

#### DH
In the Diffie-Hellman scheme, assume that _p_ = 113, and _g_ = 3. Assume _A_'s secret is _a_ = 5 and _B_'s secret is _b_ = 2. Compute the value of the shared secret established using DH.
![[Quick sheets - page 4.png]]


# Problem 4 
Consider the following two protocols for authentication and session key establishment.
**Protocol 1:**
1. $A \rightarrow B: [ g^a \mod p ]_A$
2. $B \rightarrow A: [ g^b \mod p ]_B$
Shared session key =  $K = g^{ab} \mod p$
**Protocol 2:**
1. $A \rightarrow B: \{ C_1 \}_B$
2. $B \rightarrow A: \{ C_2 \}_A$
Shared session key = $K = C_1 \oplus C_2$
#### Explain what additional service (security guarantee) is provided by Protocol 1 in comparison with Protocol 2. 
Protocol 1 with Diffie Hellman coupled with ephemeral keys provides Perfect Forward Secrecy, ensuring that in long term even if the attacker compromises both A and B, they cannot get the Shared key for that session. implying that the attacker cannot decrypt past communication.
#### Explain the required conditions to be satisfied for this additional service to be trusted.
One of the required condition is that the keys must be ephemeral, meaning both A and B must forget a and b with the K. If they do not forget the keys, then the attacker can derive K, by using Diffie Hellman derivation. Essentially becoming equal to Protocol 2, where the attacker can get K by compromising both parties and XORing C1 and C2.
#### If this property is not needed, what would be the advantage of using Protocol 2?
If perfect forward secrecy is not needed, protocol 2 could provide some benifits:
1. Less Computation: It avoids the computation overload of calculating the individual keys and then the shared key.
2. Simpler: So easier to work with, maintain, and could also reduce errors.
3. Faster: Lesser calculations means it would run faster.


# Problem 5 

Consider the following authentication protocol. _A_ has a secret _W_ (generated from a password), and _B_ only knows $g^W\mod p$. The message exchange is as follows:
1. $A \rightarrow B: A, g^a\mod p$
2. $B \rightarrow A: g^b \mod p, c_1$  
    The common key is: $K = Hash(g^{ab} \mod p, g^{Wb} \mod p)$
3. $A \rightarrow B: K\{c_1\}, c_2$
4. $B \rightarrow A: K\{c_2\}$
Does this protocol have a vulnerability if the password _W_ is weak (i.e., is chosen from a reasonably small set)?
![[Pasted image 20240220144048.png]]

The fact that W is picked from a reasonably small space makes this even more easier for T to attack this, as T has setup two different keys with both A and B.

# Problem 6
Consider the following authentication protocol. Assume that _A_ and _B_ share a secret key _K_ of length 64 bits. $R_1$ and $R_2$ are two 64-bits numbers. Does this protocol have any major vulnerability? If yes describe it in detail.
1. $A \rightarrow B:$ I am $A$
2. $B \rightarrow A: R_1$
3. $A \rightarrow B: Hash( (K + R_1) \mod 2^{64}), R_2$
4. $B \rightarrow A: Hash( (K + R_2) \mod 2^{63})$
Assume that Hash is a cryptographically secure hashing function and that we are only interested in authenticating _A_ to _B_ (one-way authentication).

![[Pasted image 20240220183345.png]]

This attack can take place only due to the fact that the space is small i.e. $2^{64}$ . If the space were to increase, it would become difficult and computationally intensive to carry out this attack.