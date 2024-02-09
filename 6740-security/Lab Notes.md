
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
