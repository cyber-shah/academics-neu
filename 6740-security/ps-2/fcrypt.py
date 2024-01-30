import argparse
import os

from cryptography.hazmat.primitives import ciphers, asymmetric, hashes, padding, serialization
from cryptography.hazmat import backends

from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC

# sizes in bytes
SYMKEY_LEN = 32
AES_BLOCK_SIZE = 16
IV_LEN = 16


class cryptoer:
    """
    On encryption, cipher text is of the following format:
    <Encrypted Symmetric Key> <Digital Signature> <Ciphertext>
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
            # Get the file extension
            _, file_extension = os.path.splitext(dest_PK)
            if file_extension == '.pem':
                self.dest_PK = serialization.load_pem_public_key(
                    self.dest_PK_bytes,
                    backend=backends.default_backend()
                )
            elif file_extension == '.der':
                self.dest_PK = serialization.load_der_public_key(
                    self.dest_PK_bytes,
                    backend=backends.default_backend()
                )

        if dest_SK:
            with open(dest_SK, 'rb') as f:
                self.dest_SK_bytes = f.read()
            # Get the file extension
            _, file_extension = os.path.splitext(dest_SK)
            if file_extension == '.pem':
                self.dest_SK = serialization.load_pem_private_key(
                    self.dest_SK_bytes,
                    password=None,
                    backend=backends.default_backend()
                )
            elif file_extension == '.der':
                self.dest_SK = serialization.load_der_private_key(
                    self.dest_SK_bytes,
                    password=None,
                    backend=backends.default_backend()
                )

        if sender_PK:
            with open(sender_PK, 'rb') as f:
                self.sender_PK_bytes = f.read()
            # Get the file extension
            _, file_extension = os.path.splitext(sender_PK)
            if file_extension == '.pem':
                self.sender_PK = serialization.load_pem_public_key(
                    self.sender_PK_bytes,
                    backend=backends.default_backend()
                )
            elif file_extension == '.der':
                self.sender_PK = serialization.load_der_public_key(
                    self.sender_PK_bytes,
                    backend=backends.default_backend()
                )

        if sender_SK:
            with open(sender_SK, 'rb') as f:
                self.sender_SK_bytes = f.read()
            # Get the file extension
            _, file_extension = os.path.splitext(sender_SK)
            if file_extension == '.pem':
                self.sender_SK = serialization.load_pem_private_key(
                    self.sender_SK_bytes,
                    password=None,
                    backend=backends.default_backend()
                )
            elif file_extension == '.der':
                self.sender_SK = serialization.load_der_private_key(
                    self.sender_SK_bytes,
                    password=None,
                    backend=backends.default_backend()
                )

    def create_symmetric_key(self, sender_SK):
        """
        creates a symmetric key using the sender's private key
        """
        # create a random salt
        salt = os.urandom(IV_LEN)
        kdf = PBKDF2HMAC(
            algorithm=hashes.SHA256(),
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
        return dest_PK.encrypt(
            symmetric_key,
            asymmetric.padding.OAEP(
                mgf=asymmetric.padding.MGF1(
                    algorithm=hashes.SHA256()
                ),
                algorithm=hashes.SHA256(),
                label=None
            )
        )

    def encrypt_plaintext(self, symmetric_key, in_plaintext):
        """
        encrypts the plaintext with the symmetric key
        appends iv in front of the ciphertext
        """
        # create a random iv
        iv = os.urandom(IV_LEN)

        # pad the plaintext
        padder = padding.PKCS7(128).padder()
        in_plaintext_padded = padder.update(in_plaintext) + padder.finalize()

        # create a cipher
        cipher = ciphers.Cipher(
            ciphers.algorithms.AES(symmetric_key),
            ciphers.modes.CBC(iv))
        # encrypt the plaintext
        encryptor = cipher.encryptor()
        # update proceses the data and generates the cipher
        # finalize finalizes the cipher

        # prepend iv, key to the ciphertext
        ciphertext = iv + \
            encryptor.update(in_plaintext_padded) + \
            encryptor.finalize()

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
        return sender_SK.sign(
            in_plaintext,
            asymmetric.padding.PSS(
                mgf=asymmetric.padding.MGF1(
                    algorithm=hashes.SHA256()
                ),
                salt_length=asymmetric.padding.PSS.MAX_LENGTH
            ),
            hashes.SHA256()
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
        """
        print("\nencrypted_symmetric_key:", encrypted_symmetric_key)
        print("\nsignature:", signature)
        print("\nciphertext:", ciphertext)

        print("\n\nlen(encrypted_symmetric_key):",
              len(encrypted_symmetric_key))
        print("\nlen(signature):", len(signature))
        print("\nlen(ciphertext):", len(ciphertext))
        """
        # 5. output the symmetric key, ciphertext, and signature to a File
        with open(out_file, 'wb') as f:
            f.write(encrypted_symmetric_key)
            f.write(signature)
            f.write(ciphertext)

    # ###############################################
    #                 DECRYPTION
    # ###############################################
    def decrypt(self, out_file):
        # 1. get the symmetric key, cipher and signature from the file
        with open(self.in_file_path, 'rb') as f:
            encrypted_symmetric_key = f.read(128)
            signature = f.read(128)
            ciphertext = f.read()

        # 4. decrypt the symmetric key
        decrypted_symm_key = self.decrypt_symmetric_key(
            encrypted_symmetric_key, self.dest_SK)

        # 5. decrypt the ciphertext
        self.plaintext = self.decrypt_ciphertext(
            decrypted_symm_key, ciphertext)

        # 6. verify the signature
        if self.verify_signature(signature, self.plaintext):
            print("Signature verified")
        else:
            print("Signature not verified")
            exit(1)

        # 7. output the plaintext to a file
        with open(out_file, 'wb') as f:
            f.write(self.plaintext)

    def decrypt_symmetric_key(self, symmetric_key, dest_SK):
        """
        decrypts the symmetric key with the destination private key
        and returns the decrypted key
        """
        return self.dest_SK.decrypt(
            symmetric_key,
            asymmetric.padding.OAEP(
                mgf=asymmetric.padding.MGF1(
                    algorithm=hashes.SHA256()
                ),
                algorithm=hashes.SHA256(),
                label=None
            )
        )

    def decrypt_ciphertext(self, symmetric_key, in_ciphertext):
        """
        decrypts the ciphertext with the symmetric key
        """
        # extract IV
        iv = in_ciphertext[:IV_LEN]

        # create a decryptor
        decryptor = ciphers.Cipher(ciphers.algorithms.AES(symmetric_key),
                                   ciphers.modes.CBC(iv)).decryptor()
        # decrypt the ciphertext
        decrypted_data = decryptor.update(
            in_ciphertext[IV_LEN:]) + decryptor.finalize()

        # unpad the plaintext
        unpadder = padding.PKCS7(128).unpadder()
        plaintext = unpadder.update(decrypted_data) + unpadder.finalize()

        return plaintext

    def verify_signature(self, signature, in_plaintext):
        """
        verifies the signature of the plaintext
        """
        self.sender_PK.verify(
            signature,
            in_plaintext,
            asymmetric.padding.PSS(
                mgf=asymmetric.padding.MGF1(
                    algorithm=hashes.SHA256()
                ),
                salt_length=asymmetric.padding.PSS.MAX_LENGTH
            ),
            hashes.SHA256()
        )
        if self.sender_PK.verify:
            return True


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
