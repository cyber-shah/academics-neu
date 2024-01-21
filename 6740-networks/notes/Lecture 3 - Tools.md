
# Reconnaissance Attack
Reconnaissance is a process of gathering information about a system to identify vulnerabilities. Originally [an ethical hacking technique](https://www.makeuseof.com/how-ethical-hacking-can-stop-hackers/), it allowed network owners to better secure their systems after identifying their security loopholes.
[Article on recon](https://www.makeuseof.com/what-are-reconnaissance-attacks-and-how-do-they-work/)
[Adversary tactics and techniques](https://attack.mitre.org/)

## 1. Active Reconnaissance
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