"""
Here's the flow of the program:
1. get the senders SK and dest PK along with plaintext
2. generate a symmetric key using KSD (key derivation function) and encrypt the key with RSA
3. use the symmetric key to encrypt the plaintext
4. generate a signature using the senders SK and the plaintext
5. output the ciphertext and signature to a file


6. get the desk SK and senders PK along with the ciphertext
7. decrypt the symmetric key using RSA
8. use the symmetric key to decrypt the ciphertext
9. verify the signature using the senders PK and the plaintext
10. output the plaintext to a file
"""


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


import argparse
import cryptography.hazmat.primitives
if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Encrypt and sign files")
    parser.add_argument("-e", "--encrypt", action="store_true")
    parser.add_argument("-d", "--decrypt", action="store_true")
    parser.add_argument("dest_PK", help="destination public key file")
    parser.add_argument("sender_SK", help="sender private key file")
    parser.add_argument("in_plaintext", help="input plaintext file")
    parser.add_argument("out_ciphertext", help="output ciphertext file")
    args = parser.parse_args()
    if args.encrypt:
        print("encrypting")
    elif args.decrypt:
        print("decrypting")
    else:
        print("error: must specify either -e or -d")
        exit(1)
    print("dest_PK:", args.dest_PK)
    print("sender_SK:", args.sender_SK)
    print("in_plaintext:", args.in_plaintext)
    print("out_ciphertext:", args.out_ciphertext)
