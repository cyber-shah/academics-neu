import os
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes

key = os.urandom(32)


def encrypt():
    iv = os.urandom(16)
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv))
    encryptor = cipher.encryptor()
    ct = iv + encryptor.update(b"a secret message") + encryptor.finalize()
    print(ct)
    return ct


def decrypt(ct):
    iv = ct[:16]
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv))
    decryptor = cipher.decryptor()
    final = decryptor.update(ct[16:]) + decryptor.finalize()
    print(final)


if __name__ == '__main__':
    ct = encrypt()
    decrypt(ct)
