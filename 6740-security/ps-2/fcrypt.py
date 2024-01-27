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
