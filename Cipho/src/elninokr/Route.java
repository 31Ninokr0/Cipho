package elninokr;

import java.util.ArrayList;

public class Route {

	public static ArrayList<ArrayList<Character>> setBlock(String plainText, int r) {
		int count = 0;
		ArrayList<ArrayList<Character>> matrix = new ArrayList<ArrayList<Character>>();
		for(int k = 0; k < r; k++) {
			ArrayList<Character> mat = new ArrayList<Character>(); 
			matrix.add(mat);
		}
		int size = r * (int) Math.ceil(plainText.length()*1.0 / r);
		for (int i = 0; i < size; i++) {
			if(i >= plainText.length() || plainText.charAt(i) == ' ') {
				matrix.get(count).add(' ');
			}else {
				matrix.get(count).add(Character.toUpperCase(plainText.charAt(i)));
			}
			count++;
			if (count == r) { count = 0; }
		}
		return matrix;
	}
	
	public static String unblock(ArrayList<ArrayList<Character>> matrix) {
		String plainText = "";
		for(int c = 0; c < matrix.get(0).size(); c++) 
			for(int r = 0; r < matrix.size(); r++) 
				plainText += matrix.get(r).get(c);
		return plainText;
	}
	
	public static String Encrypt(String plainText, int r) {
		String cipherText = "";
		ArrayList<ArrayList<Character>> matrix = setBlock(plainText, r);
		for (int i = r-1; i >= 0; i--) {
			for (int j = 0; j < matrix.get(0).size(); j++) { cipherText += matrix.get(i).get(j); }
		}
		return cipherText;
	}

	public static String Decrypt(String cipherText, int r) {
		ArrayList<ArrayList<Character>> matrix = new ArrayList<ArrayList<Character>>();
		for(int k = 0; k < r; k++) {
			ArrayList<Character> mat = new ArrayList<Character>(); 
			matrix.add(mat);
		}
		int p = 0;
		int b = 0;
		int f = (int) Math.ceil(cipherText.length()*1.0 / r);
		for(int i = r - 1; i >= 0; i--) {
			for(int j = 0; j < cipherText.substring(b, f).length(); j++) {
				matrix.get(i).add(cipherText.charAt(p));
				p++;
			}
			b += (int) Math.ceil(cipherText.length()*1.0 / r);
			f += (int) Math.ceil(cipherText.length()*1.0 / r);
		}
		return unblock(matrix);
	}
	
	public static ArrayList<String> bruteForce(String ciphertext) {
		ArrayList<String> all = new ArrayList<String>();
		for (int i = 1; i <= ciphertext.length(); i++) {
			try {
				System.out.println(i);
				all.add(Decrypt(ciphertext, i));
			}catch (StringIndexOutOfBoundsException ex) {
				all.add("Impossible Key");
			}
			
		}
		
		return all;
	}
	
//	public static void main(String[] args) {
//		String test = "Writing is done for a number of different purposes and for different audiences. These\r\n" +
//				"different forms of writing are often known as text types at school.\r\n" + 
//				"Factual texts inform, instruct or persuade by giving facts and information. Literary texts\r\n" + 
//				"entertain or elicit an emotional response by using language to create mental images.\r\n" + 
//				"Students are often asked to present an assignment or project which may be one of these text\r\n" + 
//				"types. Below are lists of different text types, purposes and features that are included in the English\r\n" + 
//				"K-6 syllabus. This list may provide hints to help with your child’s written task. Always refer to the\r\n" + 
//				"actual task requirements sent home with your child and remember the type of text used by a writer\r\n" + 
//				"should suit the purpose and the audience";
//		int e = test.length(); // 0 < key <= length
//		System.out.println( bruteForce(Encrypt(test, 2)).get(e)  );
//		
//	}

}
