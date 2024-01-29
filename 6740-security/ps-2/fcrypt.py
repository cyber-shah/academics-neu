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
from cryptography.hazmat import primitives
from cryptography.hazmat import backends

import os
import argparse

# sizes in bytes
SYMKEY_LEN = 32
AES_BLOCK_SIZE = 16
IV_LEN = 16

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
    <Encrypted Symmetric Key> <Digital Signature> <Ciphertext>
    WHY? because the cipher text is the most sensitive, and we
    won't decrypt it unless the signature is verified
    """

    def __init__(self):
        pass

    def files_to_bytes(self, in_file=None, out_file=None,
                       dest_PK=None, dest_SK=None,
                       sender_PK=None,  sender_SK=None):
        """
        converts the file paths to byte strings
        and stores them as attributes to the class
        """
        if in_file:
            self.in_file_path = in_file
            with open(in_file, 'rb') as f:
                in_file_bytes = f.read()
            self.in_file = in_file_bytes
        if dest_PK:
            with open(dest_PK, 'rb') as f:
                self.dest_PK_bytes = f.read()
            self.dest_PK = primitives.serialization.load_pem_public_key(
                self.dest_PK_bytes,
                backend=backends.default_backend()
            )
        if dest_SK:
            with open(dest_SK, 'rb') as f:
                self.dest_SK_bytes = f.read()
            self.dest_SK = primitives.serialization.load_pem_private_key(
                self.dest_SK_bytes,
                password=None,
                backend=backends.default_backend()
            )
        if sender_PK:
            with open(sender_PK, 'rb') as f:
                self.sender_PK_bytes = f.read()
            self.sender_PK = primitives.sserialization.load_pem_public_key(
                self.sender_PK_bytes,
                backend=backends.default_backend()
            )
        if sender_SK:
            with open(sender_SK, 'rb') as f:
                self.sender_SK_bytes = f.read()
            self.sender_SK = primitives.serialization.load_pem_private_key(
                self.sender_SK_bytes,
                password=None,
                backend=backends.default_backend()
            )

    def create_symmetric_key(self, sender_SK):
        # create a random salt
        salt = os.urandom(IV_LEN)
        kdf = PBKDF2HMAC(
            algorithm=SHA256(),
            length=SYMKEY_LEN,
            salt=salt,
            iterations=100000,
            backend=backends.default_backend()
        )
        # NOTE: using sender_SK as the password to make the kdf more secure
        # -- over using a PK as PK is available to everyone
        symmetric_key = kdf.derive(self.sender_SK_bytes)
        return symmetric_key

    def encrypt_symmetric_key(self, symmetric_key, dest_PK):
        """
        encrypts the symmetric key with the destination public key
        and returns the encrypted key
        """
        # done with dest PK because only the dest SK can decrypt it
        # USING OAEP padding
        return dest_PK.encrypt(
            symmetric_key,
            primitives.asymmetric.padding.OAEP(
                mgf=primitives.asymmetric.padding.MGF1(
                    algorithm=hashes.SHA256()
                ),
                algorithm=hashes.SHA256(),
                label=None
            )
        )

    def encrypt_plaintext(self, symmetric_key, in_plaintext):
        """
        Encrypts the plaintext with the symmetric key
        appends iv in front of the ciphertext
        """
        # NOTE: uses AES with CBC mode and hence requires padding

        # create a random iv
        iv = os.urandom(IV_LEN)

        # pad the plaintext
        padder = primitives.padding.PKCS7(128).padder()
        in_plaintext = padder.update(in_plaintext) + padder.finalize()

        # create a cipher
        cipher = ciphers.Cipher(
            ciphers.algorithms.AES(symmetric_key),
            ciphers.modes.CBC(iv),
            backends.default_backend()
        )
        # encrypt the plaintext
        encryptor = cipher.encryptor()
        # update proceses the data and generates the cipher
        # finalize finalizes the cipher

        # prepend IV, key to the ciphertext
        ciphertext = iv + encryptor.update(in_plaintext) + encryptor.finalize()
        return ciphertext

    def sign_plaintext(self, sender_SK, in_plaintext):
        """
        Returns a signature of the plaintext using the senders SK
        With AES, for example, we have a block size of 128 bits (16 bytes), and
        where we process these blocks to cipher and decipher.
        But the last block is unlikely to fill all the bytes in this block
        and so we add in padding.
        This padding is typically derived from the number of empty spaces
        and repeated for the number of spaces left.
        And so, “hello” is padded with 11 padding values (0b):

        h e l l o 0b 0b 0b 0b 0b 0b 0b 0b 0b 0b 0b
        """
        # size of the signature = size of the key + padding size
        #
        return sender_SK.sign(
            in_plaintext,
            primitives.asymmetric.padding.PSS(
                mgf=asymmetric.padding.MGF1(
                    algorithm=primitives.hashes.SHA256()
                ),
                salt_length=asymmetric.padding.PSS.MAX_LENGTH
            ),
            primitives.hashes.SHA256()
        )

    # ###############################################
    #                  ENCRYPTION
    # ###############################################
    def encrypt(self, out_file):
        """
        encrypts the plaintext and signs it
        """
        # NOTE: for each message/encyption session, a new symmetric key is used

        # 1. create a kdf - symmetric key
        self.symmetric_key = self.create_symmetric_key(self.sender_SK)

        # 2. encrypt the plaintext with the symmetric key
        # appends the iv in front of the ciphertext
        ciphertext = self.encrypt_plaintext(self.symmetric_key, self.in_file)

        # 3. encrypt the symmetric key with destination public key
        encrypted_symmetric_key = self.encrypt_symmetric_key(
            self.symmetric_key, self.dest_PK)

        # 4. sign the hash of the plaintext with rsa
        signature = self.sign_plaintext(self.sender_SK, self.in_file)

        # NOTE: so far each of the above is a byte string
        # when written to a file, they are written as bytes
        print("\nencrypted_symmetric_key:", encrypted_symmetric_key)
        print("\nsignature:", signature)
        print("\nciphertext:", ciphertext)

        print("\n\nlen(encrypted_symmetric_key):",
              len(encrypted_symmetric_key))
        print("\nlen(signature):", len(signature))
        print("\nlen(ciphertext):", len(ciphertext))
        # 5. output the symmetric key, ciphertext, and signature to a File
        with open(out_file, 'wb') as f:
            f.write(encrypted_symmetric_key)
            f.write(signature)
            f.write(ciphertext)

    # ###############################################
    #                 DECRYPTION
    # ###############################################
    def decrypt(self, out_file):
        # TODO : resolve this
        print("\nlen (encrypted_symmetric_key):", 128)
        print("\nlen (signature):", 128)
        # 1. get the symmetric key
        # 2. get the ciphertext
        # 3. get the signature
        with open(self.in_file_path, 'rb') as f:
            encrypted_symmetric_key = f.read(128)
            signature = f.read(128)
            ciphertext = f.read()

        print("\nencrypted_symmetric_key:", encrypted_symmetric_key)
        print("\nsignature:", signature)
        print("\nciphertext:", ciphertext)

        # 4. decrypt the symmetric key
        self.symmetric_key = self.decrypt_symmetric_key(encrypted_symmetric_key,
                                                        self.dest_SK)
        print("\nsymmetric_key:", self.symmetric_key)
        # 5. decrypt the ciphertext
        self.plaintext = self.decrypt_ciphertext(
            self.symmetric_key, self.in_file)
        print("\nplaintext:", self.plaintext)
        # 6. verify the signature
        self.verify_signature(signature, self.plaintext)
        print("\nverified signature")
        # 7. output the plaintext to a file
        with open(out_file, 'wb') as f:
            f.write(self.plaintext)
        pass

    def decrypt_symmetric_key(self, symmetric_key, dest_SK):
        return self.dest_SK.decrypt(
            symmetric_key,
            primitives.asymmetric.padding.OAEP(
                mgf=primitives.asymmetric.padding.MGF1(
                    algorithm=primitives.hashes.SHA256()
                ),
                algorithm=primitives.hashes.SHA256(),
                label=None
            )
        )

    def decrypt_ciphertext(self, symmetric_key, in_ciphertext):
        decryptor = primitives.ciphers.Cipher(
            primitives.ciphers.algorithms.AES(
                symmetric_key),
            primitives.ciphers.modes.CBC(
                in_ciphertext[:IV_LEN]),
            backends.default_backend()
        ).decryptor()
        plaintext = decryptor.update(
            in_ciphertext[IV_LEN:]) + decryptor.finalize()
        unpadder = primitives.padding.PKCS7(128).unpadder()
        plaintext = unpadder.update(plaintext) + unpadder.finalize()
        return plaintext

    def verify_signature(self, signature, in_plaintext):
        self.sender_PK.verify(
            signature,
            in_plaintext,
            primitives.asymmetric.padding.PSS(
                mgf=primitives.asymmetric.padding.MGF1(
                    algorithm=primitives.hashes.SHA256()
                ),
                salt_length=primitives.asymmetric.padding.PSS.MAX_LENGTH
            ),
            primitives.hashes.SHA256()
        )


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Encrypt and sign files")
    parser.add_argument("-e", "--encrypt", action="store_true")
    parser.add_argument("-d", "--decrypt", action="store_true")
    parser.add_argument("dest_key", help="destination public key file")
    parser.add_argument("sender_key", help="sender private key file")
    parser.add_argument("in_file", help="input plaintext file")
    parser.add_argument("out_file", help="output ciphertext file")
    args = parser.parse_args()

    cryptoer = cryptoer()
    if args.encrypt:
        cryptoer.files_to_bytes(in_file=args.in_file,
                                dest_PK=args.dest_key,
                                sender_SK=args.sender_key)
        cryptoer.encrypt(args.out_file)
    elif args.decrypt:
        # assign the keys to dest SK and sender PK
        cryptoer.files_to_bytes(in_file=args.in_file,
                                dest_SK=args.dest_key,
                                sender_PK=args.sender_key)
        cryptoer.decrypt(args.out_file)
    else:
        print("error: must specify either -e or -d")
        exit(1)
