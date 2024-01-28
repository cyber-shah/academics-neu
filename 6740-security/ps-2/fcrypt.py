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
import os
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
from cryptography.hazmat.primitives.hashes import SHA256
import cryptography.hazmat.primitives
import cryptography.hazmat.backends
import cryptography.hazmat.primitives.padding
import cryptography.hazmat.primitives.serialization
import binascii

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


class cryptoer:
    """
    On encryption, cipher text is of the following format:
    <Encrypted Symmetric Key> <Ciphertext> <Digital Signature>
    """

    def __init__(self):
        pass

    def files_to_bytes(self, in_file=None,
                       dest_PK=None, dest_SK=None,
                       sender_PK=None,  sender_SK=None):
        """
        converts the file paths to byte strings
        and stores them as attributes to the class
        """
        if in_file:
            with open(in_file, 'rb') as f:
                in_file_bytes = f.read()
            self.in_file = in_file_bytes
        if dest_PK:
            with open(dest_PK, 'rb') as f:
                self.dest_PK_bytes = f.read()
            self.dest_PK = cryptography.hazmat.primitives.serialization.load_pem_public_key(
                self.dest_PK_bytes,
                backend=cryptography.hazmat.backends.default_backend()
            )
        if dest_SK:
            with open(dest_SK, 'rb') as f:
                self.dest_SK_bytes = f.read()
            self.dest_SK = cryptography.hazmat.primitives.serialization.load_pem_private_key(
                self.dest_SK_bytes,
                password=None,
                backend=cryptography.hazmat.backends.default_backend()
            )
        if sender_PK:
            with open(sender_PK, 'rb') as f:
                self.sender_PK_bytes = f.read()
            self.sender_PK = cryptography.hazmat.primitives.serialization.load_pem_public_key(
                self.sender_PK_bytes,
                backend=cryptography.hazmat.backends.default_backend()
            )
        if sender_SK:
            with open(sender_SK, 'rb') as f:
                self.sender_SK_bytes = f.read()
            self.sender_SK = cryptography.hazmat.primitives.serialization.load_pem_private_key(
                self.sender_SK_bytes,
                password=None,
                backend=cryptography.hazmat.backends.default_backend()
            )

    def encrypt(self, out_file):
        """
        encrypts the plaintext and signs it
        """
        # TODO: figure out what is the format of the final output file
        # NOTE: for each message/encyption session, a new symmetric key is used

        # 1. create a kdf - symmetric key
        # TODO: if someone uses this file can they see the symmetric key?
        self.symmetric_key = self.create_symmetric_key(self.sender_SK)
        print(f"symmetric key: {self.symmetric_key}")

        # 2. encrypt the plaintext with the symmetric key
        # appends the iv in front of the ciphertext
        ciphertext = self.encrypt_plaintext(self.symmetric_key, self.in_file)
        print(f"ciphertext: {ciphertext}")

        # 3. encrypt the symmetric key with destination public key
        encrypted_symmetric_key = self.encrypt_symmetric_key(
            self.symmetric_key, self.dest_PK)
        print(f"encrypted symmetric key: {encrypted_symmetric_key}")

        # 4. sign the hash of the plaintext with rsa
        signature = self.sign_plaintext(self.sender_SK, self.in_file)

        # 5. output the symmetric key, ciphertext, and signature to a File
        with open(out_file, 'wb') as f:
            f.write(encrypted_symmetric_key)
            f.write(ciphertext)
            f.write(signature)

    def decrypt(self, dest_SK, sender_PK, in_file, out_file):
        pass

    def create_symmetric_key(self, sender_SK):
        # create a random salt
        salt = os.urandom(16)
        kdf = PBKDF2HMAC(
            algorithm=SHA256(),
            length=32,
            salt=salt,
            iterations=100000,
            backend=cryptography.hazmat.backends.default_backend()
        )
        # NOTE: using sender_SK as the password to make the kdf more secure
        # -- over using a PK
        symmetric_key = kdf.derive(self.sender_SK_bytes)
        return symmetric_key

    def encrypt_symmetric_key(self, symmetric_key, dest_PK):
        """
        encrypts the symmetric key with the destination public key
        and returns the encrypted key
        """
        # done with dest PK because only the dest SK can decrypt it
        return dest_PK.encrypt(
            symmetric_key,
            cryptography.hazmat.primitives.asymmetric.padding.OAEP(
                mgf=cryptography.hazmat.primitives.asymmetric.padding.MGF1(
                    algorithm=cryptography.hazmat.primitives.hashes.SHA256()
                ),
                algorithm=cryptography.hazmat.primitives.hashes.SHA256(),
                label=None
            )
        )

    def decrypt_symmetric_key(self, symmetric_key, dest_SK):
        pass

    def encrypt_plaintext(self, symmetric_key, in_plaintext):
        """
        Encrypts the plaintext with the symmetric key
        appends iv in front of the ciphertext
        """
        # create a random iv
        iv = os.urandom(16)

        # pad the plaintext
        padder = cryptography.hazmat.primitives.padding.PKCS7(128).padder()
        in_plaintext = padder.update(in_plaintext) + padder.finalize()

        # create a cipher
        cipher = cryptography.hazmat.primitives.ciphers.Cipher(
            cryptography.hazmat.primitives.ciphers.algorithms.AES(
                symmetric_key),
            cryptography.hazmat.primitives.ciphers.modes.CBC(iv),
            cryptography.hazmat.backends.default_backend()
        )
        # encrypt the plaintext
        encryptor = cipher.encryptor()
        # update proceses the data and generates the cipher
        # finalize finalizes the cipher

        # prepend IV, key to the ciphertext
        ciphertext = iv + encryptor.update(in_plaintext) + encryptor.finalize()
        return ciphertext

    def decrypt_ciphertext(self, symmetric_key, in_ciphertext):
        pass

    def sign_plaintext(self, sender_SK, in_plaintext):
        """
        Returns a signature of the plaintext using the senders SK
        """
        return sender_SK.sign(
            in_plaintext,
            cryptography.hazmat.primitives.asymmetric.padding.PSS(
                mgf=cryptography.hazmat.primitives.asymmetric.padding.MGF1(
                    algorithm=cryptography.hazmat.primitives.hashes.SHA256()
                ),
                salt_length=cryptography.hazmat.primitives.asymmetric.padding.PSS.MAX_LENGTH
            ),
            cryptography.hazmat.primitives.hashes.SHA256()
        )


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Encrypt and sign files")
    parser.add_argument("-e", "--encrypt", action="store_true")
    parser.add_argument("-d", "--decrypt", action="store_true")
    parser.add_argument("dest_PK", help="destination public key file")
    parser.add_argument("sender_SK", help="sender private key file")
    parser.add_argument("in_file", help="input plaintext file")
    parser.add_argument("out_file", help="output ciphertext file")
    args = parser.parse_args()

    cryptoer = cryptoer()
    if args.encrypt:
        cryptoer.files_to_bytes(in_file=args.in_file,
                                dest_PK=args.dest_PK,
                                sender_SK=args.sender_SK)
        cryptoer.encrypt(args.out_file)
    elif args.decrypt:
        cryptoer.decrypt(args.dest_SK, args.sender_PK,
                         args.in_file, args.out_file)
    else:
        print("error: must specify either -e or -d")
        exit(1)
