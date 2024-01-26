# A good way to learn? Imagine you solving the problem XYZ by yourself
Like learning bitcon, what if you were to invent it by yourself. 
Like hashing - imagine yourself trying to solve the problem verifying data integrity or storing passwords on your database.

# TLS and SSL

[![Asymmetric keys and encryption methods - Coding at school](https://codingatschool.weebly.com/uploads/2/6/8/8/26889801/1521514_orig.gif)


# HTTPS
![[Pasted image 20240110114417 1.png]]

### How is the data encrypted and decrypted?
**Step 1** - The client (browser) and the server establish a TCP connection.
**Step 2** - The client sends a “client hello” to the server. The message contains a set of necessary encryption algorithms (cipher suites) and the latest TLS version it can support. The server responds with a “server hello” so the browser knows whether it can support the algorithms and TLS version.
The server then sends the SSL certificate to the client. The certificate contains the public key, hostname, expiry dates, etc. The client validates the certificate. 
**Step 3** - After validating the SSL certificate, the client generates a session key and encrypts it using the public key. The server receives the encrypted session key and decrypts it with the private key. 
**Step 4** - Now that both the client and the server hold the same session key (symmetric encryption), the encrypted data is transmitted in a secure bi-directional channel.
#### Why does HTTPS switch to symmetric encryption during data transmission? 
1. **Server resources**: The asymmetric encryption adds quite a lot of mathematical overhead. It is not suitable for data transmissions in long sessions.


# Hashing

> Hash is not encryption, anything you hash cannot be reverted/decrypted back to its source.
1. BG QUESTION -- why can't we reverse hashes?
#### 1. What is Hashing in Security?
 Hashing in security is a process of converting input data (of any size) into a fixed-size string of characters, which is typically a hash value or hash code. This process is accomplished by a mathematical function called a hash function.
#### 2. Why Do We Need Hashing?
The main answer here is security and integrity.
- **Data Integrity:** Hashing ensures data integrity by generating a unique hash value for each unique input. Any change in the input data results in a significantly different hash, allowing detection of tampering or corruption.
- **Password Storage:** Hashing is crucial for secure password storage. Instead of storing actual passwords, systems store their hashed values. This adds a layer of security, as even if the hashed values are compromised, the original passwords are not easily retrievable.
- **Data Verification:** Hashing is used to verify data authenticity. By comparing hash values before and after transmission, parties can confirm that the data has not been altered during transit.

#### 3. Why Can We Not Reverse Hashing?
- **One-Way Function:** Hashing is designed to be a one-way function. Given a hash value, it should be computationally infeasible to reverse the process and determine the original input. This adds a layer of security, especially in password storage scenarios.
- **Cryptographic Hash Functions:** Cryptographic hash functions, commonly used in security, have properties that make reversing extremely difficult. These functions include characteristics like collision resistance and preimage resistance.

#### 4. Must-Have Characteristics of Hash Functions:
- **Deterministic:** A given input will always produce the same hash value.
- **Fixed Output Length:** The hash function generates a fixed-size output, regardless of the input size.
- **Efficient Computation:** The hash function should be computationally efficient to calculate the hash value.
- **Collision Resistance:** It should be computationally infeasible to find two different inputs that produce the same hash value.
- **Preimage Resistance:** Given a hash value, it should be computationally infeasible to find any input that produces that specific hash.
- **Avalanche Effect:** A small change in the input should result in a significantly different hash value.
- **Non-reversibility:** It should be difficult or practically impossible to reverse the hashing process and obtain the original input.

# Salting
Salting is a technique used in cryptography to enhance the security of hashed passwords. It involves **adding a unique, random value** known as a "salt" to each password before hashing. The salt is then stored alongside the hashed password in a database.
Key points about salting include:
- **Uniqueness:** Each user has a unique salt.
    - **Randomness:** Salts are generated using a random or pseudorandom process.
    - **Concatenation:** The salt is combined with the user's password before hashing.
    - **Prevents precomputed attacks:** Salting makes precomputed attacks (rainbow table attacks) less effective because the attacker would need to compute tables for each unique salt.

The purpose of salting is to defend against attacks where an attacker can precompute hash values for common passwords and then compare them against hashed passwords in a database. With unique salts for each user, even if two users have the same password, their hashed values will be different due to the unique salts.

In summary, hashing is a one-way function that transforms input data into a fixed-size hash value, while salting is a technique that adds a unique and random value to passwords before hashing to enhance security, especially in the context of password storage.

# Digital Signatures

### Signing documents
Each digital signature is different, it is made up by two elements private key and the message.
So for each message it will change.
![[Pasted image 20240125174328.png]]

### Verifying signatures
![[Screenshot 2024-01-25 at 17.44.27.png]]
