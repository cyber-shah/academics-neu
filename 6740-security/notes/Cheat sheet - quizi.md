
| TCP IP | OSI |
| ---- | ---- |
|  ![[Pasted image 20240201123759.png]] | ![[Pasted image 20240201123700.png]] |

**Internet Protocol (IP):**
- **Location:** Network Layer (Layer 3)
- **Purpose:** Provides host-to-host communication and routing across networks.
- **Port:** N/A

**Transmission Control Protocol (TCP):**
- **Location:** Transport Layer (Layer 4)
- **Purpose:** Ensures reliable, connection-oriented communication.
- **Ports:**
    - **HTTP:** 80
    - **HTTPS (Secure HTTP):** 443
    - **FTP (File Transfer Protocol):** 21
    - **SMTP (Simple Mail Transfer Protocol):** 25
    - **POP3 (Post Office Protocol):** 110
    - **IMAP (Internet Message Access Protocol):** 143

**User Datagram Protocol (UDP):**
- **Location:** Transport Layer (Layer 4)
- **Purpose:** Provides connectionless communication with reduced overhead.
- **Ports:**
    - **DNS (Domain Name System):** 53
    - **DHCP (Dynamic Host Configuration Protocol):** 67 (Server), 68 (Client)
    - **SNMP (Simple Network Management Protocol):** 161

**Internet Control Message Protocol (ICMP):**
- **Location:** Network Layer (Layer 3)
- **Purpose:** Used for error reporting and diagnostics.
- **Port:** N/A
### Encryption:
**Streams and Ciphers:** Stream ciphers operate on individual bits, encrypting one bit at a time (e.g., RC4), while block ciphers encrypt fixed-size blocks of plaintext at once (e.g., AES, DES).
**Symmetric vs Asymmetric:** Symmetric encryption, while efficient for large data, faces key management challenges. Asymmetric encryption, addressing key distribution issues, is generally slower. Common algorithms include RSA and ECC.
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


### Hashing
**Birthday attacks:**
>Can generalize the problem to one wanting a matching pair from any two sets, and show need $2^m/2$ in each to get a matching m-bit hash.
1. Attacker precomputes a set of hash values for various inputs. The attacker stores these hashes for future comparison.
2. As more hashes are computed, the probability of finding a collision increases.
3. The attacker checks whether a newly computed hash matches any of the precomputed values.
4. If a match is found, a collision has occurred.

**Message Authentication Code (MAC) Using an Encryption Algorithm**

| Two keys | CBC |
| ---- | ---- |
| **Key Usage:** Utilize two keys—one for CBC encryption and one for CBC residue computation.<br>**Enhancement:** Append a cryptographic keyed-hash to the message before CBC encryption.<br>**Objective:** Combine confidentiality and integrity measures for a comprehensive security approach. | **Goal:** Detect any modification or forgery of the content by an attacker.<br>**Approach:** Utilize Cipher Block Chaining (CBC) mode.<br>**Strategy:** Send only the last block (residue) along with the plaintext message |

| GCM |  |
| ---- | ---- |
| ![[Pasted image 20240201202439.png]] | ![[Pasted image 20240201195754.png]]<br> |


### Modes of Operation
| ECB | CBC |
| ---- | ---- |
| ECB is a block cipher mode where each block of plaintext is independently encrypted, making it simple but susceptible to certain vulnerabilities. | CBC is a block cipher mode that enhances security by introducing feedback from the previous ciphertext block into the encryption of the current block. |
| **Blockwise Encryption:**<br>    - Divide the plaintext into blocks.<br>    - Encrypt each block independently using the same key. | 1. **Initialization Vector (IV):**<br>    - Use a unique IV for the first block.<br>2. **Chaining:**<br>    - XOR each plaintext block with the previous ciphertext block before encryption. |
| - Vulnerable to patterns and repetitions in the plaintext.<br>- Not recommended for securing multiple blocks of data with the same key. | - Provides confidentiality and integrity.<br>- Requires an IV, and ciphertext blocks depend on each other.<br>- Parallelization is limited due to block dependencies. |
| Attacks against<br>**1. Pattern Recognition:** Identical blocks of plaintext produce identical ciphertext blocks, allowing attackers to identify patterns within the encrypted data.<br>2. Reordering Attack: An attacker can rearrange blocks of ciphertext to manipulate the order of corresponding plaintext blocks without knowledge of the encryption key<br>**3. **Deterministic Nature:** Repeated occurrences of the same plaintext block result in identical ciphertext blocks, revealing patterns and structure in the encrypted data. |  |


|  CTR (Counter): | GCM (Galois Counter Mode): |
| ---- | ---- |
| CTR is a block cipher mode that turns a block cipher into a stream cipher, providing parallelization and efficiency. | GCM is a block cipher mode that combines Counter Mode (CTR) with a Galois field multiplication for authenticated encryption. |
| 1. **Counter Generation:**<br>Generate a unique counter for each block.<br>2. **Encryption:**<br>XOR the counter with the plaintext to create a keystream.<br>Encrypt the keystream with the block cipher. | 1. **Initialization:**<br>Similar to CTR, but with an additional authentication tag.<br>2. **Encryption and Authentication:**<br>    - Generate ciphertext using CTR.<br>    - Compute a MAC using Galois field multiplication. |
| - Enables parallelization, as each block operates independently.<br>- Requires a unique counter for each block.<br>- Does not provide inherent integrity; additional measures may be needed.<br> | - Provides authenticated encryption (confidentiality and integrity).<br>- Suitable for parallel processing.<br>- Requires a unique counter for each block.<br><br> |

**OAEP (Optimal Asymmetric Encryption Padding):**
- OAEP is a padding scheme for asymmetric encryption, enhancing security by adding randomness to plaintext before encryption.
1. **Encoding:** Encode the plaintext and add random padding.
2. **Encryption:** Encrypt the encoded message with a public key.

**Key Points:**
- Mitigates certain cryptographic attacks, such as chosen ciphertext attacks.
- Adds probabilistic encryption, enhancing security.
- Primarily used with asymmetric encryption algorithms like RSA.

### AES
| A | B |
| ---- | ---- |
| AES is a symmetric encryption algorithm widely used to secure sensitive data by transforming it into ciphertext.<br>1. **Key Expansion:** Derive a set of round keys from the original key using a key expansion algorithm.<br>2. **Initial Round:**  AddRoundKey: XOR each byte of the state with a corresponding round key.<br>3. **Rounds:**<br>    - SubBytes: Substitute each byte with a value from an S-box.<br>    - ShiftRows: Shift rows of the state matrix.<br>    - MixColumns: Mix the data within each column.<br>    - AddRoundKey: XOR with the round key.<br>4. **Final Round:**  SubBytes, ShiftRows, AddRoundKey.<br><br>**Key Points:**<br>- Operates on fixed-size blocks (128 bits).<br>- Key lengths: 128, 192, or 256 bits.<br>- Provides a high level of security and efficiency. | ![[Pasted image 20240201222028.png]] |
 

### DES (Data Encryption Standard) Works:
- DES is a symmetric-key block cipher used for encrypting electronic data.
- It operates on fixed-size blocks (64 bits) of data using a fixed key size (56 bits).

1. **Key Generation:**
   - The 56-bit key is used to generate 16 subkeys, one for each round.
   - The subkeys are derived through a process called key permutation and shifting.
2. **Initial Permutation (IP):**
   - The 64-bit plaintext block undergoes an initial permutation.
   - Bits are rearranged according to a fixed permutation table.
3. **Feistel Network (16 Rounds):**
   - The block is divided into two halves (L and R).
   - The Feistel function is applied to the right half using the subkey for each round.
   - The output is XORed with the left half.
   - Swap the left and right halves.
4. **Final Permutation (FP):**
   - After 16 rounds, the halves are swapped again.
   - A final permutation is applied to undo the initial permutation.
5. **Result:**
   - The final 64-bit ciphertext block is obtained.

**Key Weaknesses:**
1. **Key Size:**
   - DES uses a relatively small key size of 56 bits.
   - Advances in computing power make exhaustive search attacks (brute force) more feasible.
2. **Security Concerns:**
   - DES was designed in the 1970s and is considered insecure by today's standards.
   - Vulnerabilities include susceptibility to differential and linear cryptanalysis.
3. **56-Bit Key Length:**
   - The effective key length is reduced due to the key's structure.
   - Users may opt for triple DES (3DES) for increased security, but it is slower.
4. **Limited Block Size:**
   - DES operates on 64-bit blocks, limiting its ability to securely encrypt large amounts of data.
5. **Standard Deprecation:**
   - DES has been deprecated in favor of more robust encryption algorithms like AES (Advanced Encryption Standard).

### SHA-2/3 (Secure Hash Algorithm):
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

### HMAC
HMAC is a construction for creating a message authentication code using a cryptographic hash function and a secret key, ensuring data integrity and authenticity.
1. **Key Padding:**  If needed, pad the key to match the block size of the hash function.
2. **Inner Hash:**  Compute the hash of the XOR of the key and an inner pad concatenated with the message.
3. **Outer Hash:** Compute the hash of the XOR of the key and an outer pad concatenated with the result from the inner hash.
4. **Output:**  The final hash serves as the HMAC.

**Key Points:**
- Combines the strengths of a cryptographic hash function and a secret key.
- Resistant to known types of attacks, providing a strong integrity check.
- Widely used in various security protocols, including TLS and IPsec.

![[Pasted image 20240201222101.png]]
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

##### Issues with Textbook RSA
1. **Use of Raw RSA Operation:**
    - Suppose you have a "random password," which is essentially an AES key.
    - Instead of using proper padding, you perform a raw RSA operation by zero-padding the AES key to the size of the RSA modulus and then computing $M^e \mod  N$ .

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


### Diffie-Hellman
**Purpose:** 
Securely exchange cryptographic keys over an insecure communication channel. Allows two parties to independently generate a shared secret key over an insecure channel.

![[Pasted image 20240201205255.png]]

> However, the Diffie-Hellman Key exchange is vulnerable to an active attack called Man-in-the-Middle (MITM) attack.

**Step by Step explanation of this process:**
**Step 1:** Selected public numbers p and g, p is a prime number, called the “modulus” and g is called the base.
**Step 2:** Selecting private numbers. let Alice pick a private random number a and let Bob pick a private random number b, Malory picks 2 random numbers c and d.

Step 3:Intercepting public values,
Malory intercepts Alice’s public value $(g^a(\mod p))$, block it from reaching Bob, and instead sends Bob her own public value ($g^c(\mod p))$ and Malory intercepts Bob’s public value $(g^b(\mod p))$, block it from reaching Alice, and instead sends Alice her own public value $(g^d (\modp))$

**Step 4:** Computing secret key
Alice will compute a key $S1=g^{da} (\mod p)$, and Bob will compute a different key, $S2=g^{cb}(\mod p)$



**Step 5:** If Alice uses S1 as a key to encrypt a later message to Bob, Malory can decrypt it, re-encrypt it using S2, and send it to Bob. Bob and Alice won’t notice any problem and may assume their communication is encrypted, but in reality, Malory can decrypt, read, modify, and then re-encrypt all their conversations.