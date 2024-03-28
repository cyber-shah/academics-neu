
## 1. Internet Working

![[Pasted image 20240201123759.png]]

| Protocol                                 | Location                  | Purpose                                                         | Ports                                                         |
| ---------------------------------------- | ------------------------- | --------------------------------------------------------------- | ------------------------------------------------------------- |
| Internet Protocol (IP)                   | Network Layer (Layer 3)   | Provides host-to-host communication and routing across networks | N/A                                                           |
| Transmission Control Protocol (TCP)      | Transport Layer (Layer 4) | Ensures reliable, connection-oriented communication             | HTTP: 80, HTTPS: 443, FTP: 21, SMTP: 25, POP3: 110, IMAP: 143 |
| User Datagram Protocol (UDP)             | Transport Layer (Layer 4) | Provides connectionless communication with reduced overhead     | DNS: 53, DHCP Server: 67, DHCP Client: 68, SNMP: 161          |
| Internet Control Message Protocol (ICMP) | Network Layer (Layer 3)   | Used for error reporting and diagnostics                        | N/A                                                           |
## 2. IND CPA
**IND CPA (Indistinguishability under Chosen Plaintext Attack):** In the context of IND-CPA (Indistinguishability under Chosen Plaintext Attack), the protocol involves: 
1. an adversary selecting two plaintext messages, denoted as M₀ and M₁.
2. An oracle, representing a hypothetical entity performing encryption operations, then randomly chooses a bit 'b' and encrypts the corresponding message Mb.
3. The primary objective is to ensure that, even with knowledge of the encryption of one of the messages, the adversary cannot reliably guess the value of 'b' with a probability significantly better than chance.
This is expressed formally as the adversary's inability to distinguish between the encryptions of M₀ and M₁. Mathematically, the goal is to prevent the adversary from gaining a non-negligible advantage in guessing 'b.'

**Attack Models:**
- **Ciphertext-Only Attack:** The attacker only possesses the ciphertext and aims to deduce the plaintext or key.
- **Known-Plaintext Attack:** The attacker has pairs of corresponding plaintext and ciphertext, attempting to deduce the key.
- **Chosen-Plaintext Attack:** The attacker can choose specific plaintexts and obtain corresponding ciphertexts, attempting to deduce the key or gain information about the encryption algorithm.
- **Chosen-Ciphertext Attack:** Similar to chosen-plaintext attacks, but the attacker can choose ciphertexts and obtain corresponding plaintexts, often more powerful than chosen-plaintext attacks.


## 3. Hashing
**Birthday attacks:**
>Can generalize the problem to one wanting a matching pair from any two sets, and show need $2^m/2$ in each to get a matching m-bit hash.
1. Attacker precomputes a set of hash values for various inputs. The attacker stores these hashes for future comparison.
2. As more hashes are computed, the probability of finding a collision increases.
3. The attacker checks whether a newly computed hash matches any of the precomputed values.
4. If a match is found, a collision has occurred.

| GCM                                  |                                          |
| ------------------------------------ | ---------------------------------------- |
| ![[Pasted image 20240201202439.png]] | ![[Pasted image 20240201195754.png]]<br> |
### 3.1 Modes of Operation

| ECB                                                                                                                                               | CBC                                                                                                                                                                                          | CTR (Counter):                                                                                                                                                                                             | GCM (Galois Counter Mode):                                                                                                                                                                                                      |
| ------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| ECB is a block cipher mode where each block of plaintext is independently encrypted, making it simple but susceptible to certain vulnerabilities. | CBC is a block cipher mode that enhances security by introducing feedback from the previous ciphertext block into the encryption of the current block.                                       | CTR is a block cipher mode that turns a block cipher into a stream cipher, providing parallelization and efficiency.                                                                                       | GCM is a block cipher mode that combines Counter Mode (CTR) with a Galois field multiplication for authenticated encryption.                                                                                                    |
| **Blockwise Encryption:**<br>    - Divide the plaintext into blocks.<br>    - Encrypt each block independently using the same key.                | 1. **Initialization Vector (IV):**<br>    - Use a unique IV for the first block.<br>2. **Chaining:**<br>    - XOR each plaintext block with the previous ciphertext block before encryption. | 1. **Counter Generation:**<br>Generate a unique counter for each block.<br>2. **Encryption:**<br>XOR the counter with the plaintext to create a keystream.<br>Encrypt the keystream with the block cipher. | 1. **Initialization:**<br>Similar to CTR, but with an additional authentication tag.<br>2. **Encryption and Authentication:**<br>    - Generate ciphertext using CTR.<br>    - Compute a MAC using Galois field multiplication. |
| - Vulnerable to patterns and repetitions in the plaintext.<br>- Not recommended for securing multiple blocks of data with the same key.           | - Provides confidentiality and integrity.<br>- Requires an IV, and ciphertext blocks depend on each other.<br>- Parallelization is limited due to block dependencies.                        | - Enables parallelization, as each block operates independently.<br>- Requires a unique counter for each block.<br>- Does not provide inherent integrity; additional measures may be needed.<br>           | - Provides authenticated encryption (confidentiality and integrity).<br>- Suitable for parallel processing.<br>- Requires a unique counter for each block.<br><br>                                                              |
### 3.3 **OAEP (Optimal Asymmetric Encryption Padding):**
- OAEP is a padding scheme for asymmetric encryption, enhancing security by adding randomness to plaintext before encryption.
1. **Encoding:** Encode the plaintext and add random padding.
2. **Encryption:** Encrypt the encoded message with a public key.

**Key Points:**
- Mitigates certain cryptographic attacks, such as chosen ciphertext attacks.
- Adds probabilistic encryption, enhancing security.
- Primarily used with asymmetric encryption algorithms like RSA.

### 3.4 AES and DES
![[Pasted image 20240326081002.png]]

| AES                                                                                                                                                                                                                                                                                    | DES (Data Encryption Standard) Works:                                                                                                                                                                                                             |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| AES is a symmetric encryption algorithm widely used to secure sensitive data by transforming it into ciphertext.<br><br>**Key Points:**<br>- Operates on fixed-size blocks (128 bits).<br>- Key lengths: 128, 192, or 256 bits.<br>- Provides a high level of security and efficiency. | - DES is a symmetric-key block cipher used for encrypting electronic data.<br>- It operates on fixed-size blocks (64 bits) of data using a fixed key size (56 bits).<br><br>5. **Result:**<br>   - The final 64-bit ciphertext block is obtained. |
### 3.5 SHA-2/3 (Secure Hash Algorithm):
SHA-2 and SHA-3 are cryptographic hash functions used to produce a fixed-size hash value, ensuring data integrity and authenticity.
1. **Message Padding:** Append padding bits to the message to meet a specific block size.
2. **Processing Blocks:**
    - Divide the padded message into blocks.
    - For each block, perform operations involving logical functions and constants.
3. **Output Digest:** Concatenate the final hash values from each block to produce the hash digest.

**Key Points:**
- SHA-2 (SHA-224, SHA-256, SHA-384, SHA-512) uses a Merkle–Damgård construction.
- SHA-3 uses the Keccak sponge construction.
- Resistant to collision and pre-image attacks.

### 3.6 HMAC

| HMAC                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | IMAGE                                                                                                                                                                                                                                                                                      |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| HMAC is a construction for creating a message authentication code using a cryptographic hash function and a secret key, ensuring data integrity and authenticity.<br>1. **Key Padding:**  If needed, pad the key to match the block size of the hash function.<br>2. **Inner Hash:**  Compute the hash of the XOR of the key and an inner pad concatenated with the message.<br>3. **Outer Hash:** Compute the hash of the XOR of the key and an outer pad concatenated with the result from the inner hash.<br>4. **Output:**  The final hash serves as the HMAC.<br><br> | ![[Pasted image 20240201222101.png]] **Key Points:**<br>- Combines the strengths of a cryptographic hash function and a secret key.<br>- Resistant to known types of attacks, providing a strong integrity check.<br>- Widely used in various security protocols, including TLS and IPsec. |




## 4 RSA Cryptosystem

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
##### Issues with Textbook RSA : NO PADDING : LOW ENTROPY (0 or 1)
 **Meet-in-the-Middle Attack Steps:**
1. **Preprocessing:** The attacker generates two lists of integers, $A_i​$ and $B_i$​ between 1 and $2^{128/2}$ (assuming a 128-bit AES key).
2. **Compute Lists:**  For each value in $A_i$​ the attacker computes $A_i^{−e} \mod  N$ and forms a list.
    - Similarly, for each value in $B_i$, the attacker computes $B_i^ e \mod  N$  and forms another list.
3. **Search for Matches:**
    - When the attacker intercepts an encrypted message $M^e \ mod  N$ , they compute $(M^ e \mod  N)⋅(A_i^{−e} \mod  N)=(M⋅A_i^{−1})^e \mod  N$
    - The attacker then scans this list for values that match with the list of $B_i^e \mod  N$ 
4. **Identify Potential Key:**
    - If there is a pair with $(M \cdot A_{i−1})^e \mod N = B_i^{e} \mod N$  the attacker identifies a potential value of $M=A_i⋅B_i$
5. **Search Complexity:**
    - The attacker has effectively searched through $k^2$ possible values of the message in $O(k \cdot log⁡k)$ time, where k is the size of the lists $A_i$​ and $B_i$

| Textbook | Padding |
| ---- | ---- |
| ![[Pasted image 20240202083011.png]] | ![[Pasted image 20240202083039.png]] |


## Diffie-Hellman
**Purpose:** 
Securely exchange cryptographic keys over an insecure communication channel. Allows two parties to independently generate a shared secret key over an insecure channel.

![[Pasted image 20240201205255.png]]

> However, the Diffie-Hellman Key exchange is vulnerable to an active attack called Man-in-the-Middle (MITM) attack.

**Step by Step explanation of this process:**
**Step 1:** Selected public numbers p and g, p is a prime number, called the “modulus” and g is called the base.
**Step 2:** Selecting private numbers. let Alice pick a private random number a and let Bob pick a private random number b, Malory picks 2 random numbers c and d.
**Step 3**:Intercepting public values,
Malory intercepts Alice’s public value $(g^a(\mod p))$, block it from reaching Bob, and instead sends Bob her own public value ($g^c(\mod p))$ and Malory intercepts Bob’s public value $(g^b(\mod p))$, block it from reaching Alice, and instead sends Alice her own public value $(g^d (\mod p))$
**Step 4:** Computing secret key
Alice will compute a key $S1=g^{da} (\mod p)$, and Bob will compute a different key, $S2=g^{cb}(\mod p)$
**Step 5:** If Alice uses S1 as a key to encrypt a later message to Bob, Malory can decrypt it, re-encrypt it using S2, and send it to Bob. Bob and Alice won’t notice any problem and may assume their communication is encrypted, but in reality, Malory can decrypt, read, modify, and then re-encrypt all their conversations.
