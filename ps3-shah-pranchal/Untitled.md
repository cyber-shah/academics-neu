
##### 1. Why can't the source just use any message authentication code mechanism based on symmetric key crypto (e.g., HMAC) if we assume that all the receivers share the same secret with the source (i.e., what is the threat)?
Two reasons:
- An attacker could intercept the authenticated data and later resend it to the receivers. This replayed data would be accepted by the receivers since they share the same secret key with the source. As a result, receivers would be unable to distinguish between the original and the replayed data.
- Without additional measures, the receivers have no way to verify the authenticity of the source. Even if the data integrity is protected using HMAC, there's no assurance that the data truly originates from the legitimate source. This lack of source authentication leaves the system vulnerable to impersonation or a man-in-the-middle (MITM) attacker.

##### 2. Now, let us assume that the sender uses asymmetric cryptography to sign each packet using his private key. Use `openssl` (hint: check the `speed` command) to estimate:

1. How many packets can the transmitter send per second using an RSA key of size 512, 1024, 2048, and 4096 bits keys?

``` bash
Doing 512 bits private rsa sign ops for 10s: 316073 512 bits private RSA sign ops in 9.98s
Doing 512 bits private rsa encrypt ops for 10s: 3850227 512 bits public RSA encrypt ops in 9.77s

Doing 1024 bits private rsa sign ops for 10s: 100397 1024 bits private RSA sign ops in 9.98s
Doing 1024 bits private rsa encrypt ops for 10s: 1049042 1024 bits public RSA encrypt ops in 9.92s

Doing 2048 bits private rsa sign ops for 10s: 13498 2048 bits private RSA sign ops in 10.00s
Doing 2048 bits private rsa encrypt ops for 10s: 405274 2048 bits public RSA encrypt ops in 9.94s

Doing 4096 bits private rsa sign ops for 10s: 992 4096 bits private RSA sign ops in 10.00s
Doing 4096 bits private rsa encrypt ops for 10s: 101997 4096 bits public RSA encrypt ops in 9.97s

```

2. How many packets can the receiver verify per second for RSA key size of 512, 1024, 2048, 4096 bits keys?
``` bash
Doing 512 bits private rsa decrypt ops for 10s: 269878 512 bits private RSA decrypt ops in 9.99s
Doing 512 bits public rsa verify ops for 10s: 5532639 512 bits public RSA verify ops in 9.98s

Doing 1024 bits public rsa verify ops for 10s: 1256268 1024 bits public RSA verify ops in 9.99s
Doing 1024 bits private rsa decrypt ops for 10s: 88640 1024 bits private RSA decrypt ops in 9.98s

Doing 2048 bits public rsa verify ops for 10s: 506812 2048 bits public RSA verify ops in 9.99s
Doing 2048 bits private rsa decrypt ops for 10s: 7750 2048 bits private RSA decrypt ops in 9.98s

Doing 4096 bits public rsa verify ops for 10s: 65145 4096 bits public RSA verify ops in 9.99s
Doing 4096 bits private rsa decrypt ops for 10s: 2019 4096 bits private RSA decrypt ops in 9.98s
```

3. Why is the signing time different from the verification time? Would the length of the packet matter very much in the signature/verification time?


4. Indicate the specs of the machine you used to run openssl
> 12th Gen Intel i7-12700H @ 4.6GHz with 32GB RAM - Laptop


##### 3. If the sender node does not have the capability to sign all the packets individually using its private key, propose a design for a technique to amortize the signatures and trades-off the computation with a delay in verification. Discuss potential issues with this approach.