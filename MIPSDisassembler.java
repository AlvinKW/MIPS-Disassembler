/*
 * Alvin Kwan
 * CS472 - Computer Architecture
 * Professor: Dave Hendrickson
 * Project 1: MIPS Disassembler
 * 
 */

public class MIPSDisassembler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcode, rs, rt, rd, funct, offset, num;
		
		//the first instruction begins at address hex 0x9A040 
		int address_begins = 0x9A040;
		
		/*
		 *using a 16-bit variable (a short) for the I-format offset
		 *so that if the number is negative, it will be accurately handled
		 */
		short handler = 0;
		
		//Machine instruction to be disassembled 
		int instruction[] = { 0x032BA020, 0x8CE90014, 0x12A90003, 0x022DA822, 0xADB30020, 
				0x02697824, 0xAE8FFFF4, 0x018C6020, 0x02A4A825, 0x158FFFF7, 0x8ECDFFF0 };
		
		for(int machine_instruction : instruction) {
			
			//opcode bits shift right by 26 bits (unsigned)
			opcode = bits_31_26(machine_instruction) >>> 26; 
			
			//Opcode = 0 -> R-Format
			if(opcode == 0) {
				System.out.printf("%02x", address_begins);
			
				rs = bits_25_21(machine_instruction) >>> 21; 
				rt = bits_20_16(machine_instruction) >>> 16;
				rd = bits_15_11(machine_instruction) >>> 11;
				funct = bits_5_0(machine_instruction);
				
				if (funct == 32){
					System.out.printf(" add ");
				}
				else if (funct == 34){
					System.out.printf(" sub ");
				}
				else if(funct == 36) {
					System.out.printf(" and ");
				}
				else if(funct == 37) {
					System.out.printf(" or  ");
				}
				else if(funct == 42) {
					System.out.printf(" slt ");
					
				}
				else {
					System.out.println("Error: Instruction was not found.");
				}

				
				System.out.print(" $" + rd +", $" + rs  + ", $" +  rt);
				
			 }else if (opcode == 4 || opcode == 5) {
					System.out.printf("%02x", address_begins);
					rs = bits_25_21(machine_instruction); 
					rt = bits_20_16(machine_instruction);
					rd = bits_15_11(machine_instruction) ;
					rs = (rs >> 21);
					rt = (rt >> 16);
					offset = bits_15_0(machine_instruction);
					handler = (short) (offset);
					
					if(opcode == 4) {
						System.out.printf(" beq ");				
					}
					
					else if(opcode == 5) {
						System.out.printf(" bne ");
					}
					
					
					System.out.print(" $" + rs +", $" + rt );
					System.out.printf(", address %02x   ", offset);
			}
			 
				else {
					System.out.printf("%02x", address_begins);
					rs = bits_25_21(machine_instruction) >>> 21; 
					rt = bits_20_16(machine_instruction) >>> 16;
					offset = bits_15_0(machine_instruction);
					handler = (short) offset;
					
					//opcode for I format
					if(opcode == 4) {
						System.out.printf(" beq ");				
					}
					else if(opcode == 5) {
						System.out.printf(" bne ");
					}
					else if(opcode == 35) {
						System.out.printf(" lw ");
					}
					else if(opcode == 43) {
						System.out.printf(" sw ");				
					}
					else {
						System.out.println("Error: Instruction was not found.");
					}
		
					System.out.print(" $" + rt +", " + handler  + " ($" +  rs + ")");
				}
			
				address_begins += 4;
				System.out.println();

				
			}
			
			
		}

	
	
	/*
	 * to partition each parts of machine instructions 
	 */
	
	//opcode
	public static int bits_31_26(int instruction) {
		return instruction & 0xFC000000;
	}
	
	//rs (first source register)
	public static int bits_25_21(int instruction) {
		return instruction & 0x3E00000;
	}
	
	//rt (second source register)
	public static int bits_20_16(int instruction) {
		return instruction & 0x1F0000;
	}
	
	//rd  (destination register)
	public static int bits_15_11(int instruction) {
		return instruction & 0xF800;
	}
	
	//funct
	public static int bits_5_0(int instruction) {
		return instruction & 0x3F;
	}
	
	//offset
	public static int bits_15_0(int instruction) {
		return instruction & 0xFFFF;
	}
	

}
