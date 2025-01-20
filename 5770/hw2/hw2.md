# **CY5770 Software Vulnerabilities and Security**

**Instructor: Ziming Zhao\
Homework -- 2**

**Submit your homework on Canvas.**

**Reading. Read the following materials.**

\[ \] Reading Task 1: Read chapter "Interlude: Process API" in
"Operating Systems: Three Easy Pieces" at
[https://pages.cs.wisc.edu/\~remzi/OSTEP/cpu-api.pdf]{.underline}

**Hands-on Tasks.**

Your username on http://cy5770-cacti.khoury.northeastern.edu/p-shah256

### \[5 points\] Task 1: Use **pmap** to show the memory maps of **processmap_32** and **processmap_64**. Take screenshots. Briefly explain the outputs.
![alt text](image.png)
![alt text](image-2.png) 

We have diff parts of misc_processmap in the code, each 4K
there are 6 areas of libc that are demand loaded into memory (signifying we use these c libraries in our code)
there is also multiple parts of linker (ld) that are loaded, and similar to libc they are demand loaded into memory
A 132K (rw) stack is also present, does not need (x) permission


### \[5 points\] Task 2: Use **strace** on a program you choose, e.g., **firstflag**, **ls**. Take a screenshot. Explain the (1) purpose, (2) parameters, and (3) return value of at least 3 system calls from the output.
![alt text](image-3.png)

Strace records all the system calls made by a program 

1. ```access("/etc/suid-debug", F_OK)         = -1 ENOENT (No such file or directory)```
- Purpose: checks if file exits or not
- Parameters: (filename, check if file OK(exists))
- Return Value: -1 ENOENT(Error NO ENTry)
2. ```close(3)                                = 0```
- Purpose: closes a file descriptor
- Parameter: (file descriptor to close)
- Return Value: 0 (done) or -1 would be error
3. ```mprotect(0xf7ffc000, 4096, PROT_READ)   = 0```
- Purpose: modify access permision of memory page in virutal memory
- Parameters: (addr, length of memory region in bytes, Memory protection flag(PROT_READ = readable only))
- Return Value: 0 (success) or -1 (error)

### \[5 points\] Task 3: Analyze the program **re_1_32**. You don\'t have access to the source code. You can use any reverse engineering tools, e.g., objdump, GDB, Ghidra, IDA Pro free, or binary ninja cloud, etc. Use ltrace, strace, strings, and any tools you can think of to get a feeling what this program does. Hint: You are supposed to find a secret. Describe what are the expected input for this program. Find the main function, describe what it does.
![binary ninja decompiler](image-4.png)

Here's what the main function does: 
1. checks if argc == 2 and strlen(argv[1]) == "csesecurity"
2. cretes an infinite loop, where: 
    1. if var_24_1 == length of 'csesecurity':
        1. print flag, break and return 
    2. if value of command line input at (var_24_1)th location == value of "csesecurity" at (var_24_1)th location + 1 ... 
        - which basically means: if command line input is "abcedefghi"... and var_24_1 is 0, check if "a" == "c" + 1 in ASCII
        - var_24_1 is now 1, check if "b" == "s" + 1 in ASCII
        1. if true, increment var_24_1 by 1 and check again
        2. else break and return 0 

### \[5 points\] Task 4: Based on what you find on Task 3, get the flag by exploiting **re_1_32**. You are supposed to find a secret. Briefly describe how you find the secret of this program and what is the secret? Take screenshots.
![secret](image-5.png)

Found the secret by looking at the ascii table, 
- "c" + 1 in ascii is "d"
- "s" + 1 in ascii is "t"
- ... similarly for each character

### \[5 points\] Task 5: Replicate what the instructor did in class. Exploit **overflowlocal1_32** and **overflowlocal1_64**. Take screenshots and explain why your exploit works.
![alt text](image-6.png)

The exploit works as follows: 
1. code currently checks for value of j, if its 0 (default) it prints I pity the fool!
2. there is a buffer of size 6, if we put a value more than 6 bytes, the buffer overflows and we can overwrite the value in j to something else other than 0

### \[5 points\] Task 6: Replicate what the instructor did in class. Exploit **overflowlocal2_32** and **overflowlocal2_64**. Take screenshots and explain why your exploit works.
- As we can see from the code here, instead of anything except 0, `j` expects a specific HEX VALUE = 0x12345678... not string "12345678" or "0x12345678".
- If we lookup the actual value for 0x12345678 its "4Vx" in ASCII... so that's expected for `j`
![alt text](image-7.png)

- To get the exact value for `j`, we know that its four bytes, so we can start with `\x\x\x\x`
- We also know that this system follows little endian meaning least significant byte first
- `\x12 \x34 \x56 \x78` becomes `\x78 \x56 \x34 \x12`

### \[5 points\] Task 7: Capture the flag by exploiting **overflowlocal3_32**. Take screenshots and explain why your exploit works.

### \[5 points\] Task 8: Capture the flag by exploiting **overflowlocal4_32**. Take screenshots and explain why your exploit works.

### \[5 points\] Task 9: Capture the flag by exploiting **overflowlocal5_32**. Take screenshots and explain why your exploit works.
