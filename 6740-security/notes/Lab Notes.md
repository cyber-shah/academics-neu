
`iptables -vn -L`
v: verbose
n: numeric - ports and IPs instead of hostnames
L: list chains

```
# create INPUT rule that does not match VPN traffic (incoming traffic) root@raspberry$ iptables -I INPUT 1 ! -s 10.10.192.0/18 -j ACCEPT

# create OUTPUT rule that does not match VPN traffic (outgoing traffic) 
root@raspberry$ iptables -I OUTPUT 1 ! -d 10.10.192.0/18 -j ACCEPT

# reset the counters 
root@raspberry$ iptables -Z

# view the number of transferred bytes 
root@raspberry$ iptables -vn -L

# remove all rules after you finished the lab 
root@raspberry$ iptables -F
```

All other traffic will be matched with default rules.


#### NMAP
- -sS: ==TCP SYN scan==: This scan sends SYN packets to targets and listens for SYN-ACK responses to identify open TCP ports. It does not complete connections, so it is stealthy. Useful for bypassing firewall rules.
- - sT: ==TCP connect scan:== Completes full 3-way TCP handshakes with each target port. Most reliable way to confirm TCP connectivity. But easily logged by firewalls or IDS.
- -sU: ==UDP scan== Sends UDP packets to target ports and monitors for ICMP port unreachable errors to determine if UDP ports are open or closed.
- -sP: ==Ping scan== Doesn't scan ports, but uses an ICMP ping request to each target IP to determine which hosts are online and reachable.

Some key differences:
- -sS is stealthier while -sT is more reliable for TCP.
- -sU enables scanning UDP ports which -sT and -sS don't support.
- -sP is just for host discovery, not port scanning.

Other notes: 
1. Multiple scan types can be combined like -sS -sV for SYN scan with version detection.
2. Privileged access is needed for full SYN and connect scanning in Linux.
3. Firewalls/IDS may block or flag scans, so always scan with permission.




- **Firewall Blocking ICMP:**
    - Some firewalls are configured to block ICMP traffic. ICMP is often used for diagnostic and control purposes in network communication.
    - When a host does not reply to ICMP ping requests, it might appear as if it's offline or unreachable.

- **Using Nmap to Discover the Host:**
    - The exercise instructs you to use the ICMP echo discovery probe (`-PE`) with `nmap`. This means you want to perform a ping scan to infer the presence of machines on the network by sending ICMP echo requests.
    - The idea is to identify hosts that reply to ICMP pings.
`nmap -PE [target]`

- **Configuring a Host to Not Reply to ICMP:**
    - One of the hosts in your network has been intentionally configured to not reply to ICMP ping requests. This simulates a situation where a firewall is blocking ICMP.
- **Using Nmap "No Ping" Option:**
    - After identifying the host that doesn't reply to ICMP pings, you are then instructed to use the "No Ping" option (`-Pn`) with `nmap`. This option tells `nmap` to skip the host discovery phase and scan the target regardless of whether it responds to pings.




## 1. Host Discovery
Under the hood, the `-PE` (ICMP Echo Discovery) and `-Pn` (No Ping) options in Nmap control the host discovery phase of the scanning process. Here's how they work:

1. **ICMP Echo Discovery Probe (-PE):**
    - When you use the `-PE` option, Nmap sends ICMP echo requests (ping probes) to the target hosts to determine their online status. These echo requests are part of the ICMP protocol and are commonly used for basic connectivity testing between hosts.
    - Nmap expects to receive ICMP echo replies from live hosts, indicating their online status. Hosts that respond to these echo requests are considered "up," and Nmap proceeds to scan them further.
    - If a host does not respond to ICMP echo requests, Nmap assumes the host is "down" and skips further scanning for that host. This can lead to false negatives in cases where firewalls or network configurations block ICMP traffic.
	
2. **No Ping Option (-Pn):**
    - The `-Pn` option instructs Nmap to skip the host discovery phase based on ICMP and proceed directly to the scanning phase. It essentially disables the default ICMP echo discovery probes.
    - With the "No Ping" option, Nmap assumes that all hosts in the specified target range are "up" and proceeds to scan them without prior confirmation of their online status through ICMP echo requests.
    - This option is useful in scenarios where hosts may not respond to ICMP echo requests due to firewall configurations or other reasons. It allows you to perform scans even on hosts that might be filtered or non-responsive to ICMP traffic.
        
In summary, the `-PE` option relies on ICMP echo requests to determine the online status of hosts, while the `-Pn` option skips the ICMP-based host discovery and assumes all specified hosts are "up." Both options provide flexibility in adapting Nmap to different network environments, especially when ICMP traffic may be blocked or hosts may not respond to traditional ping requests.


## 2. Port Discovery
1. **TCP Connect Scan (`-sT`):**
    - In a TCP Connect Scan, Nmap attempts to establish a full TCP connection with the target host on specified ports.
    - If the port is open, the host responds with a TCP ACK, indicating that the connection can be established.
    - If the port is closed, the host responds with a TCP RST (reset) packet.
    - This scan is less stealthy compared to other techniques because it completes the full TCP handshake.
2. **TCP SYN Scan (`-sS`):**
    - The TCP SYN Scan is one of the most common and stealthy port discovery techniques.
    - Nmap sends TCP SYN packets to the target ports.
    - If the port is open, the host responds with a TCP SYN-ACK.
    - If the port is closed, the host responds with a TCP RST packet.
    - This technique is stealthier than TCP Connect Scan because it does not complete the full TCP handshake.
3. **UDP Scan (`-sU`):**
    - In a UDP Scan, Nmap sends UDP packets to specified ports.
    - If the port is open, the host may respond with an ICMP Port Unreachable message or not respond at all.
    - UDP scanning is more challenging than TCP scanning because UDP is connectionless and does not provide the same reliable feedback.
4. **Service Version Detection (`-sV`):**
    - Once open ports are identified, Nmap can perform service version detection to determine the specific services running on those ports.
    - Nmap sends additional probes to the open ports to gather information about the services and their versions.
5. **Aggressive Scan (`-A`):**
    - The Aggressive Scan is a combination of several Nmap options, including host discovery, port scanning, service version detection, and OS fingerprinting.
    - It's a comprehensive scan that provides detailed information about the target.




# 3 MITM


1. We set up an iptables prerouting rule on the Raspberry Pi that intercepts any SSH traffic from the Server and redirects it to the Raspberry Pi's SSH port instead of the Client.
2. We use ettercap to ARP poison the connection between the Server and Client. This poisons the ARP cache of the Server, so that traffic destined for the Client will be sent to the Raspberry Pi's MAC address instead.
3. When the Server attempts an SSH connection to the Client, the poisoned ARP cache will redirect the initial packets to the Raspberry Pi.
4. The iptables prerouting rule then catches those SSH packets and redirects them to the local SSH server on the Raspberry Pi.
5. The Server thinks it is connecting to the Client but is actually connected to the Raspberry Pi.

Server generates encryption keys and sends key exchange init message:

- The server generates an ephemeral public/private key pair to use for this SSH session.
- It sends the public key to the client in a "key exchange init" message.

Raspberry Pi responds with fake key exchange response:

- The Raspberry Pi does not actually generate a real key pair.
- It simply sends a fake "key exchange reply" message with a fake public key.

Server encrypts session keys with Raspberry Pi public key:

- The server takes the fake public key from the Raspberry Pi.
- It generates symmetric encryption keys to use for the SSH session.
- It encrypts these session keys with the Raspberry Pi's fake public key.

Encrypted SSH session is established:

- The server sends the encrypted session keys to the Raspberry Pi.
- Since the Raspberry Pi knows the fake private key, it can decrypt the session keys.
- Now both sides have the symmetric keys needed to encrypt the session.
- Further communication can be encrypted using the session keys.

In summary, the Raspberry Pi is faking its side of the key exchange to convince the server the keys are securely established. This allows the Raspberry Pi to decrypt traffic sent by the server in the session.


This attack would not be possible in a normal SSH connection due to host key verification:

- In a regular SSH connection, the client verifies the server's host key on first connect. This ensures you are connecting to the real server.
- The client stores the server's public key fingerprint after first connect.
- On subsequent connections, it cross checks the host key matches the stored fingerprint.
- If the key is different, the connection is aborted.

However, this attack is possible because:

- The administrator has disabled SSH host key verification on the client (Server in this case).
- This is sometimes done on internal networks or with automated scripts to avoid host key errors.
- But it means the Server does not verify the identity of the Client (Raspberry Pi).
- The Raspberry Pi can present any fake host key, impersonating the real Client.
- Without host key verification, the Server just accepts whatever key the Raspberry Pi provides.
- This allows the Raspberry Pi to fully impersonate the Client and set up the encrypted tunnel.

So in summary, disabling host key verification removes a critical SSH security check and enables this man-in-the-middle attack.