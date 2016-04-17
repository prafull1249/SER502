# main source file

.org 0x10000000

li $a0, 5
li $s1, 1
li $v0, 1

j fact
nop

fact:
	slt $s2, $a0, $s1
	beq $s2, $s1, end
	nop
	mullo $v0, $v0, $a0
	subu $a0, $a0, $s1
	j fact
	nop

end:
nop
	