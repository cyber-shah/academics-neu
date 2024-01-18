# TLS and SSL

[![Asymmetric keys and encryption methods - Coding at school](https://codingatschool.weebly.com/uploads/2/6/8/8/26889801/1521514_orig.gif)


# HTTPS
![[Pasted image 20240110114417 1.png]]

### How is the data encrypted and decrypted?
**Step 1** - The client (browser) and the server establish a TCP connection.
**Step 2** - The client sends a “client hello” to the server. The message contains a set of necessary encryption algorithms (cipher suites) and the latest TLS version it can support. The server responds with a “server hello” so the browser knows whether it can support the algorithms and TLS version.
The server then sends the SSL certificate to the client. The certificate contains the public key, hostname, expiry dates, etc. The client validates the certificate. 
**Step 3** - After validating the SSL certificate, the client generates a session key and encrypts it using the public key. The server receives the encrypted session key and decrypts it with the private key. 
**Step 4** - Now that both the client and the server hold the same session key (symmetric encryption), the encrypted data is transmitted in a secure bi-directional channel.
#### Why does HTTPS switch to symmetric encryption during data transmission? 
1. **Server resources**: The asymmetric encryption adds quite a lot of mathematical overhead. It is not suitable for data transmissions in long sessions.