package elninokr;

import java.util.Random;

public class Alberti { 
	private static char[] outer = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 
									'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
									'W', 'X', 'Y', 'Z', '.', ',', '?', '!' };
	private static char[] inner =  {'q', 'i', 'h', 'f', 'd', 'b', 'a', 'c', 'e', 'g', 'k', 
								    'l', 'n', 'p', 'r', 't', 'v', 'z', 'm', 'x', 'y', 's',
								    'o', 'g', 'i', 'j', '^', '*', '~', ']' };
		
	public static String Encrypt(String plainText, char[] inner, char[] outer, char key) {
		if (Search(key, inner) == -1)
			return "Invalid Key";
		String cipherText = "";
		Random r = new Random();
		int rep = r.nextInt(9-6) + 6;
		int rand = r.nextInt(outer.length);	
//		System.out.println(rand);
		
		cipherText += outer[rand];		
		for (int i = 0; i < plainText.length(); i++) {
			int outPos = binarySearch(plainText.charAt(i), outer);
			int shift = outPos - rand;
			if(outPos == -1) 
				cipherText+= plainText.charAt(i);
			else {
				int newPos = Search(key, inner) + shift;
				
				System.out.println("Shift  : " + shift  + " || " + 
								   "Outpos : " + outPos + " || " + 
								   	"newPlace " + newPos + " || " +
								   "a : " + Search(key, inner));
				
				if(newPos >= inner.length - 1) {
					newPos = newPos - (inner.length - 1);
				}else if(newPos < 0) {
					newPos = inner.length - 1 + newPos;
				}
				cipherText += inner[newPos];
			}
			
//			if(i == rep) {
//				rand = r.nextInt(outer.length);
//				rep += r.nextInt(9-6) + 6;
//				cipherText += outer[rand];
//			}
		}
		return cipherText;
	}
	
	public static String Decrypt(String cipherText, char[] inner, char[] outer, char key) {
		String plainText = "";
		int inPos = Search(key, inner);
		int outPos = 0;
		
		for (int i = 0; i < cipherText.length(); i++) {
			if(cipherText.charAt(i) == ' ') {
				plainText += cipherText.charAt(i);
			}else {
				if(Search(cipherText.charAt(i), inner) == -1 ) {
					outPos = binarySearch(cipherText.charAt(i), outer);
//				}else if(linearSearch(cipherText.charAt(i), inner) == -1) {
//					plainText += cipherText.charAt(i);				
				}else {
					int shift = Search(cipherText.charAt(i), inner) - inPos;
					int newPos = outPos + shift;
//					System.out.println(newPos + " ");
					if(newPos < 0)
						newPos = outer.length - 1 + newPos;
					else if(newPos >= outer.length - 1)
						newPos = newPos - (outer.length - 1);
										
					plainText += outer[newPos];
					
//					System.out.println(newPos + " " + plainText);
				}
			}
		}
		return plainText;
	}
	
	public static int binarySearch(char find, char[] list) {
		int low =  0;  
		int high = list.length;
		find = Character.toUpperCase(find);

	    int mid;
	    while (low <= high) {
	    	mid = (low + high) / 2;
	        if (list[mid] < find) 
	        	low = mid + 1;
	        else if (list[mid] > find) 
	             high = mid - 1;
	        else 
	             return mid;
	        }
	    return -1;
	}
	
	public static int Search(char find, char[] list) {
		for(int i = 0; i < list.length; i++)
			if (list[i] == find)
				return i;
		return -1;
	}
	
	public static void main(String[] args) {	
		for (int i = 26; i < outer.length; i++) {
			char c = Character.toLowerCase(outer[i]);
	        int ascii = c;
	        System.out.println("The ASCII value of " + c + " is: " + ascii + " @ " + i);
		}
		
		
		String ex = "I am counting my calories, yet I really want dessert.\r\n"; //+ 
//				"The sky is clear; the stars are twinkling.\r\n" + 
//				"Malls are great places to shop; I can find everything I need under one roof.\r\n" + 
//				"The river stole the gods.";
		char key = 'q';
		String test = Encrypt(ex, inner, outer, key);
		System.out.println(test);
		System.out.println(Decrypt(test, inner, outer, key));
	}

}
