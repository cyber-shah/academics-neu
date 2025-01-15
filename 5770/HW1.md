# CY5770 Software Vulnerabilities and Security
## Instructor: Ziming Zhao | Homework – 1

Submit your homework on Canvas.

## Reading Materials

- [ ] Reading Task 1: Read "x86 Assembly Guide" at Intel syntax (https://www.cs.virginia.edu/~evans/cs216/guides/x86.html)
- [ ] Reading Task 2: Read "Guide to x86-64" (https://web.stanford.edu/class/cs107/guide/x86-64.html)
- [ ] Reading Task 3: Read "Setuid Program Example" (https://www.gnu.org/software/libc/manual/html_node/Setuid-Program-Example.html)
- [ ] Reading Task 4: Read blog "Anatomy of a Program in Memory" (https://manybutfinite.com/post/anatomy-of-a-program-in-memory/)
- [ ] Reading Task 5: Read Chapter 0 "Operating system interfaces" in "xv6, a simple, Unix-like teaching operating system" (https://pdos.csail.mit.edu/6.828/2014/xv6/book-rev8.pdf)

## Hands-on Tasks
Your username on cy5770-cacti.khoury.northeastern.edu: shah256

### Task 0 [0 points]
Google and read NEU academic integrity policies.

### Task 1 [6 points]
Read the syllabus. Find the secret, which is in the format of "FLAG-XXXXXXXX".

**Flag found:** FLAG-READ-SYLLABUS-0x9876

### Task 2 [6 points]
Register an account at cy5770-cacti.khoury.northeastern.edu.
1. Use the "cat" command to print the content in "/flag". Explain the results and take a screenshot.
2. Run the firstflag_32 challenge, explain the results, submit the flag, take a screenshot.

### Task 3 [6 points]
Run any challenge, and list all the set-UID or set-GID programs under /bin. Explain the commands used and take a screenshot of the results.

**Commands used and results:**

```bash
# Finding SUID files
find /usr/bin -type f -perm /4000
```

Output:
- /usr/bin/umount
- /usr/bin/mount
- /usr/bin/chsh
- /usr/bin/chfn
- /usr/bin/newgrp
- /usr/bin/su
- /usr/bin/gpasswd
- /usr/bin/passwd
- /usr/bin/sudo

```bash
# Finding SGID files
find /usr/bin -type f -perm /2000
```

Output:
- /usr/bin/chage
- /usr/bin/expiry
- /usr/bin/ssh-agent
- /usr/bin/bsd-write
- /usr/bin/crontab

### Task 4 [7 points]
Run the add_32 challenge. Source code located at /code/add directory.

**Compilation differences:**

Without -save-temps:
```
Makefile a.out add.bc add.c add.h main.bc main.c
```

With -save-temps:
```
Makefile add.bc add.h add.o main.bc main.i main.s
a.out add.c add.i add.s main.c main.o
```

Main difference: -save-temps preserves intermediate files (.i, .s, .o) instead of cleaning them up.

### Task 5
Run the ladd_32 challenge. Use the objdump -M intel -d command to disassemble the binary. Find the function ladd in the binary (Screenshot). Explain each instruction of this function. Please use the Intel syntax. Google the instruction if you do not understand its meaning. 

```nasm
000012c0 <ladd>:
12c0: f3 0f 1e fb    endbr32
12c4: 8b 44 24 04    mov eax,DWORD PTR [esp+0x4]
12c8: 8b 50 04       mov edx,DWORD PTR [eax+0x4]
12cb: 8b 00          mov eax,DWORD PTR [eax]
12cd: 03 44 24 08    add eax,DWORD PTR [esp+0x8]
12d1: 13 54 24 0c    adc edx,DWORD PTR [esp+0xc]
12d5: c3             ret
```

Instruction explanations:
- `mov eax,DWORD PTR [esp+0x4]`: Loads a 4-byte value from value at addr[esp+4] into eax
- `mov edx,DWORD PTR [eax+0x4]`: Loads a 4-byte value from value at addr[eax+4] into edx
- `mov eax,DWORD PTR [eax]`: Loads a 4-byte value from memory at addr[eax] into eax
- `add eax,DWORD PTR [esp+0x8]`: Adds the 4-byte value at addr[esp+8] to eax
- `adc edx,DWORD PTR [esp+0xc]`: Adds the 4-byte value at addr[esp+12] to edx with carry
- `ret`: Returns from the function

### Task 6 [7 points]
Run the ladd_64 challenge. Use objdump to disassemble the binary and find the ladd function. Include screenshot and explanation of instructions.

### Task 7 [6 points]
Read the Setuid Program Example documentation and run the rdsecret_64 challenge. Explain outputs, submit flag, and include screenshots.
