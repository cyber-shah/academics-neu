
### Reconnaissance Attack
Reconnaissance is a process of gathering information about a system to identify vulnerabilities. Originally [an ethical hacking technique](https://www.makeuseof.com/how-ethical-hacking-can-stop-hackers/), it allowed network owners to better secure their systems after identifying their security loopholes.
[Article on recon](https://www.makeuseof.com/what-are-reconnaissance-attacks-and-how-do-they-work/)
[Adversary tactics and techniques](https://attack.mitre.org/)

###  Reconnaissance
In active reconnaissance, the attacker **engages with the target actively**. They communicate with you just to get information about your system. Active reconnaissance is quite effective as it gives the attacker valuable information about your system.
### Social Engineering
Social engineering is a process where a cyber threat actor [manipulates targets to reveal confidential information](https://www.makeuseof.com/tag/social-engineering-makeuseof-explains/) to them. They may contact you online via instant chats, emails, and other interactive means to build a connection with you. 
Once they win you over, they will make you divulge sensitive information about your system or lure you to open a malware-infected file that will compromise your network.
### Active Footprinting
Active footprinting is a method that involves an intruder taking deliberate steps to gather information about your system, its security infrastructure and user engagement. They retrieve your IP addresses, active email addresses, domain name system (DNS) information, etc.

>Active footprinting means performing footprinting by getting in direct touch with the target machine.

**Different kinds of information that can be gathered from Footprinting are as follows:**
- The operating system of the target machine
- Firewall
- IP address
- Network map
- Security configurations of the target machine
- Email id, password
- Server configurations
- URLs
- VPN

### Port Scanning
Ports are **areas through which information passes from one computer program** or device to another. In port scanning, the threat actor [scans the ports within your network](https://www.makeuseof.com/what-is-port-scanning/) to identify the open ones. They use a port scanner to detect the active services on your network such as the hosts and IP addresses and then break in through the open ports.
#### What information can Port Scanning leak?
The information acquired doesn't merely reveal the services the device is running; it also sheds light on the overall purpose of the device. By analyzing the accessible ports and the services linked with them, a hacker can deduce the device's role and, in a way, fashion a unique fingerprint that could serve as an exploitable element in a future attack.
1. **Service Identification:**
    - _Example:_ A port scan reveals that port 22 is open, indicating that an SSH (Secure Shell) service is running. Attackers may exploit known vulnerabilities in SSH to gain unauthorized access to the system.
2. **Operating System Detection:**
    - _Example:_ Through OS fingerprinting during port scanning, attackers determine that the target system is running a specific version of Windows. They can then tailor their attacks to exploit vulnerabilities commonly associated with that Windows version.
3. **Network Topology Mapping:**
    - _Example:_ Port scanning identifies multiple active devices and open ports in a network. Attackers use this information to map the network topology and identify critical servers, allowing for a more targeted and potentially devastating attack.
4. **Firewall and Security Device Detection:**
    - _Example:_ A port scan reveals that only ports 80 and 443 are open on a web server, indicating the presence of a firewall. Attackers might attempt to find ways to bypass or exploit the firewall to gain access to additional services.
5. **Security Weakness Identification:**
    - _Example:_ Port scanning identifies an outdated version of a web server software with known vulnerabilities. Attackers exploit these vulnerabilities to launch attacks, such as SQL injection or remote code execution, compromising the web server.
6. **Data Exfiltration:**
    - _Example:_ Port scanning may precede a larger attack aimed at exfiltrating sensitive data. Once potential vulnerabilities are identified, attackers may use other techniques to compromise and extract valuable information.
7. **Denial of Service (DoS) Planning:**   
    - _Example:_ Port scanning can be part of reconnaissance for a Denial of Service attack. Attackers identify critical services and attempt to overwhelm them with traffic, causing disruption or downtime.






Onion servers are very hard to find. WHY?

2015 defcon talk about cars

buffer overflow used to exploit

google project zero

side channel attacks


1. Social Engineering
	1. check links
2. Physical security

dig 
nmap
echo

#### How does it work?
1. The port scanner sends requests to a range of port numbers and waits for responses, classifying each port based on the response it receives. 
2. Open ports will acknowledge the request, closed ports will reject it, and a filtered port won't send any response at all. 
3. When executed with malicious intent, this becomes a port scanning attack.

### Whois 
	- The Whois database via Internic (now managed by ICANN) is a publicly-available starting point for obtaining information about domain registrations, including contacts, name servers, and more.
    - Detailed information can be queried from the registrar, providing entries such as contacts, postal addresses, name servers, and email details.

#####  1. What can be extracted?
    - Contacts associated with domain registration.
    - Name servers and their details.
    - Email addresses and their formats.
##### 2. How can it be exploited or why must it be protected?
    - Email addresses may be targeted for phishing attacks.
    - Contact details could be used for social engineering.
    - Understanding name servers may aid in identifying potential vulnerabilities.


### Intrusive Scans and Probes
#### From Insecure Modems to Insecure Access Points:
- **Historical Perspective:**
    - In the past, techniques like War Dialers (ToneLoc, THC-Scan), Demon Dialers, and Rogue RAS were used to exploit insecure modems.
    - Today, the focus has shifted to War Driving, where rogue and insecure Wireless Access Points are identified and exploited using tools like NetStumbler, Wellenreiter, kismet, and ESSID-Jack.
- **Tools and Techniques:**
    - Detection tools such as high-gain antennas, NetStumbler, Wellenreiter, and ESSID-Jack are employed to identify rogue wireless access points.
- **Examples:**
    - War Driving involves detecting RF signals up to 2 km away using high-gain antennas and specialized tools.
#### Determine if a Networked Host is Alive:
- **Techniques:**
    - ICMP (Ping, Echo Request/Reply) Sweeps.
    - TCP/UDP Packet Sweeps (commonly known as "TCP Ping").
- **Defenses:**
    - Configure firewalls and border routers to limit ICMP and UDP traffic to specific systems.
    - Implement monitoring with Intrusion Detection Systems (IDS).
#### Why do we need to defend against it?
1. **Prevent Unauthorized Reconnaissance:**
    - Attackers often perform host-alive checks as part of reconnaissance to identify potential targets. Defending against these sweeps helps thwart unauthorized information gathering about the network.
2. **Security through Obscurity:**
    - Limiting the information exposed to ICMP and UDP/TCP sweeps follows the principle of "Security through Obscurity." By restricting the visibility of networked hosts, organizations reduce the likelihood of being targeted based on known information.
3. **Avoid Potential Exploitation:**
    - If attackers can successfully determine which hosts are alive, they may focus their efforts on exploiting vulnerabilities in those systems. Defending against these sweeps helps minimize the risk of targeted attacks.
4. **Prevent Network Mapping:**
    - ICMP and TCP/UDP sweeps are often used to map network topologies. By limiting the response to these sweeps, organizations make it more challenging for attackers to create an accurate map of the network structure.






# concepts to learn
learn what is syn scan
what is fingerprinting
scanning networks using syn packets -- if you get an ack, if you get an ack the port is open. then what?
why do we need to stop tcp connections from hackers, what could they do?
firewalls- stop network discovery, they don't let other people know you are connected into a network
ZMAP and shodan
at what layer is encryption done? what happens when it is encrypted at the lowest layer, or the application layer?
if encryption is done at the application layer -- PGP -- email, I'm leaking who am I sending to.
lets say we envrypt everything above TCP - then the IP Header, Port is , and xxxx all visible except for the contents
how does arp poisoning work, poisoning arp table
switch converts into a hub
ADSB - for aircrafts
messages can be spoofed - show an airplane when there isn't one
sticky mac addresses
remote IP sniffing
spoofing -- used by mitnick shimomura 
buffer overflow attacks
sql injection


# Web based attcaks
steal cookies
cross site scritping XSS
salting
hashing  -- memory hard vs sha
adobe securtiy breach 
how does hashing work in databases to store passwords
SYNFloods
Smurf attacks
