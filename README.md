# MIPS-Disassembler
Partial disassembler for MIPS instructions

# Objective 
---

Write a partial disassembler for MIPS instructions. Input will be the 32-bit machine instructions that a compiler or assembler produces. This program then figures out what the original source instructions were that created those 32-bit machine instructions and outputs them. The possible source instructions that must be able to disassemble are: add, sub, and, or, slt, lw, sw, beq, bne. Ignore all other MIPS instructions. The specific machine instructions that will be disassembled (one after another in this exact order) are:
0x032BA020, 0x8CE90014, 0x12A90003, 0x022DA822, 0xADB30020, 0x02697824, 0xAE8FFFF4, 0x018C6020, 0x02A4A825, 0x158FFFF7, 0x8ECDFFF0. The above 32-bit instructions will be the input to your program. (Eight hex digits are 32 binary bits.) This program will then analyze a 32-bit instruction and figure out what the opcode, register operands and other fields in the instruction are and then print out the assembly language instruction that produced it. Assume that the first instruction begins at address hex 9A040 and the rest follow right after that one.
