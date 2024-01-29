CS 6740 Network Security
Pranchal Shah

## PS 2 Crypto


### Design:
The design of the program is as follows:
1. The program takes two keys as an input, and two locations of the files to be encrypted/decrypted.
2. Keys are loaded in with serialization, using load_format_private_key and load_format_public_key.
3. File paths are read and the files are opened as byte strings.

# TODO check PEM and DER format
# TODO check larger files
#### Creating symmetric key:
Symmetric key is dervied using KDF function implemented using PBKDF2HMAC. Password Based Key Derivation Function 2
The key is derived using the following parameters:
Because this is a password based key derivation function - making it ideal for creating a symmetric key from a password (sender's private key)

4. Hash function: SHA256
3. Key length: 32 bytes
6. Salt: A random 16 byte string
7. Iterations: 100000
this is later used to derive the key from sender's private key.

#### Encrypting symmetric key:
The symmetric key is encrypted using the receiver's public key. The key is encrypted using RSA OAEP padding. The key is encrypted using the following parameters:
1. Hash function: SHA256
2. Mask generation function: MGF1
3. Padding: OAEP
4. Label: None
5. Final key size: 128 bytes

OEAP is used here because it allows for creating keys based on password which makes it ideal for this use case, where the symmetric key is derived from the sender's private key.



Justification of design and usage of algorithms, key sizes and modes (15 pts)