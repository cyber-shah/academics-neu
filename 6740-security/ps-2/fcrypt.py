import argparse
import os

import cryptography.primitives.ciphers as ciphers
import cryptography.primitives.asymmetric as asymmetric
import cryptography.primitives.hashes as hashes
import cryptography.primitives.padding as padding
import cryptography.primitives.serialization as serialization


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
