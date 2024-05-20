	.text
	.file	"file_commandLine.c"
	.globl	main                            # -- Begin function main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$304, %rsp                      # imm = 0x130
	movl	$0, -4(%rbp)
	movl	%edi, -8(%rbp)
	movq	%rsi, -16(%rbp)
	movl	-8(%rbp), %esi
	movabsq	$.L.str, %rdi
	movb	$0, %al
	callq	printf
	movl	$0, -20(%rbp)
.LBB0_1:                                # =>This Inner Loop Header: Depth=1
	movl	-20(%rbp), %eax
	cmpl	-8(%rbp), %eax
	jge	.LBB0_4
# %bb.2:                                #   in Loop: Header=BB0_1 Depth=1
	movl	-20(%rbp), %esi
	movq	-16(%rbp), %rax
	movslq	-20(%rbp), %rcx
	movq	(%rax,%rcx,8), %rdx
	movabsq	$.L.str.1, %rdi
	movb	$0, %al
	callq	printf
# %bb.3:                                #   in Loop: Header=BB0_1 Depth=1
	movl	-20(%rbp), %eax
	addl	$1, %eax
	movl	%eax, -20(%rbp)
	jmp	.LBB0_1
.LBB0_4:
	movq	-16(%rbp), %rax
	movq	8(%rax), %rdi
	movabsq	$.L.str.2, %rsi
	callq	fopen
	movq	%rax, -296(%rbp)
	movl	$256, %edi                      # imm = 0x100
	callq	malloc
	movq	%rax, -304(%rbp)
	movabsq	$.L.str.3, %rdi
	movb	$0, %al
	callq	printf
.LBB0_5:                                # =>This Inner Loop Header: Depth=1
	movq	-304(%rbp), %rdi
	movq	-296(%rbp), %rdx
	movl	$256, %esi                      # imm = 0x100
	callq	fgets
	cmpq	$0, %rax
	je	.LBB0_7
# %bb.6:                                #   in Loop: Header=BB0_5 Depth=1
	movq	-304(%rbp), %rsi
	movabsq	$.L.str.4, %rdi
	movb	$0, %al
	callq	printf
	jmp	.LBB0_5
.LBB0_7:
	movq	-304(%rbp), %rdi
	callq	free
	movq	-296(%rbp), %rdi
	callq	fclose
	movl	-4(%rbp), %eax
	addq	$304, %rsp                      # imm = 0x130
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end0:
	.size	main, .Lfunc_end0-main
	.cfi_endproc
                                        # -- End function
	.type	.L.str,@object                  # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"Number of Command Line arguments: %d\n"
	.size	.L.str, 38

	.type	.L.str.1,@object                # @.str.1
.L.str.1:
	.asciz	"Argument %d: %s \n"
	.size	.L.str.1, 18

	.type	.L.str.2,@object                # @.str.2
.L.str.2:
	.asciz	"r"
	.size	.L.str.2, 2

	.type	.L.str.3,@object                # @.str.3
.L.str.3:
	.asciz	"Contents of the file are: \n"
	.size	.L.str.3, 28

	.type	.L.str.4,@object                # @.str.4
.L.str.4:
	.asciz	"%s"
	.size	.L.str.4, 3

	.ident	"clang version 16.0.4 (Fedora 16.0.4-1.fc38)"
	.section	".note.GNU-stack","",@progbits
	.addrsig
	.addrsig_sym printf
	.addrsig_sym fopen
	.addrsig_sym malloc
	.addrsig_sym fgets
	.addrsig_sym free
	.addrsig_sym fclose
