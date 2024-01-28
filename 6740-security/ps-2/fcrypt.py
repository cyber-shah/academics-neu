"""
Here's the flow of the program:
1. get the senders SK and dest PK along with plaintext
2. generate a symmetric key using KDF (key derivation function)
2.2 and encrypt the key with RSA
3. use the symmetric key to encrypt the plaintext
4. generate a signature using the senders SK and the plaintext
5. output the ciphertext and signature to a file


6. get the desk SK and senders PK along with the ciphertext
7. decrypt the symmetric key using RSA
8. use the symmetric key to decrypt the ciphertext
9. verify the signature using the senders PK and the plaintext
10. output the plaintext to a file
"""


import argparse
import cryptography.hazmat.primitives
import os
"""
For encryption and signatures generating ciphertext_file: ALL MUST BE FILES
python fcrypt.py -e <dest_PK> <sender_SK> <in_plaintext> <out_ciphertext>

for decryption and signature verification generating output_plaintext_file:
python fcrypt.py -d <dest_SK> <sender_PK> <in_plaintext> <out_ciphertext>

Three modules:
1. encrypt/decrypting
2. signing/verifying
3. key handling


1. support DER and PEM formats for keys
2. test the program
"""


class encrypter:
    def __init__(self):
        pass

    def files_to_bytes(self, in_file=None,
                       dest_PK=None, dest_SK=None,
                       sender_PK=None,  sender_SK=None):
        if in_file:
            with open(in_file, 'rb') as f:
                in_file_bytes = f.read()
            self.in_file = in_file_bytes
        if dest_PK:
            with open(dest_PK, 'rb') as f:
                dest_PK_bytes = f.read()
        if sender_SK:
            with open(sender_SK, 'rb') as f:
                sender_SK_bytes = f.read()

    def encrypt(self, dest_PK, sender_SK, in_plaintext, out_file):
        # 0. take the paths and convert them into byte strings
        self.dest_PK = dest_PK
        self.sender_SK = sender_SK
        self.in_file = in_plaintext
        self.out_file = out_file
        # 1. create a kdf - symmetric key
        # TODO: if someone uses this file can they see the symmetric key?
        self.symmetric_key = self.create_symmetric_key(sender_SK)
        # 2. encrypt the plaintext with the symmetric key
        # NOTE: for each message/encyption session, a new symmetric key is used
        ciphertext = self.encrypt_plaintext(self.symmetric_key, self.in_file)
        # 3. encrypt the symmetric key with rsa
        self.encrypt_symmetric_key(self.symmetric_key, dest_PK)
        # 4. sign the hash of the plaintext with rsa

        pass

    def decrypt(self, dest_SK, sender_PK, in_file, out_file):
        self.dest_SK = dest_SK
        self.sender_PK = sender_PK
        self.in_file = in_file
        self.out_file = out_file
        pass

    def create_symmetric_key(self, sender_SK):
        # create a random salt
        salt = os.urandom(16)
        kdf = cryptography.hazmat.primitives.kdf.pbkdf2.PBKDF2HMAC(
            algorithm=cryptography.hazmat.primitives.hashes.SHA256(),
            length=32,
            salt=salt,
            iterations=100000,
            backend=cryptography.hazmat.backends.default_backend()
        )
        symmetric_key = kdf.derive(sender_SK)
        return symmetric_key

    def encrypt_symmetric_key(self, symmetric_key, dest_PK):
        pass

    def decrypt_symmetric_key(self, symmetric_key, dest_SK):
        pass

    def encrypt_plaintext(self, symmetric_key, in_plaintext):
        pass

    def decrypt_ciphertext(self, symmetric_key, in_ciphertext):
        pass


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Encrypt and sign files")
    parser.add_argument("-e", "--encrypt", action="store_true")
    parser.add_argument("-d", "--decrypt", action="store_true")
    parser.add_argument("dest_PK", help="destination public key file")
    parser.add_argument("sender_SK", help="sender private key file")
    parser.add_argument("in_file", help="input plaintext file")
    parser.add_argument("out_file", help="output ciphertext file")
    args = parser.parse_args()

    encrypt = encrypter()
    if args.encrypt:
        encrypter.encrypt(args.dest_PK, args.sender_SK,
                          args.in_file, args.out_file)
    elif args.decrypt:
        encrypter.decrypt(args.dest_SK, args.sender_PK,
                          args.in_file, args.out_file)
    else:
        print("error: must specify either -e or -d")
        exit(1)
