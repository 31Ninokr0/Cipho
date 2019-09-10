package elninokr;

import java.util.ArrayList;

public class Vigenere {
	private static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	private static double[] letterFreq = {8.12, 1.49, 2.71, 4.32, 12.02, 2.30, 2.03, 5.92, 7.31, 0.10, 
			0.69, 3.98, 2.61, 6.95, 7.68, 1.82, 0.11, 6.02, 6.28, 9.10, 2.88, 1.11, 2.09, 0.17, 2.11, 0.07};
	//http://pi.math.cornell.edu/~mec/2003-2004/cryptography/subs/frequencies.html
	
	
	public static char letterEncrypt(char letter, int key) {
		if (key > letters.length) key = key%letters.length;
		if(Caesar.findIndex(letter, letters) == -1)
			return letter;
		else if (Caesar.findIndex(letter, letters) + key < letters.length)
			return letters[Caesar.findIndex(letter, letters) + key];
		else
			return letters[(Caesar.findIndex(letter, letters) + key) - letters.length];
	
	}
	
	public static char letterDecrypt(char letter, int key) {
		if (key > letters.length) key = key%letters.length;
		if(Caesar.findIndex(letter, letters) == -1)
			return letter;
		else if(Caesar.findIndex(letter, letters) < key) 
			return letters[letters.length + (Caesar.findIndex(letter, letters) - key)];
		else
			return letters[Caesar.findIndex(letter, letters) - key];
	}
	
	public static String Encrypt(String plainText, String key){
		String cipherText = "";
		int j = 0;
		for(int i = 0; i < plainText.length(); i++) {
			if(Caesar.findIndex(plainText.charAt(i), letters) == -1) { cipherText += plainText.charAt(i); }
			else {
				if (j >= key.length()) { j = 0; }
				cipherText += letterEncrypt(plainText.charAt(i), Caesar.findIndex(key.charAt(j), letters));
				j++;
			}
		}
		return cipherText;
	}

	public static String Decrypt(String cipherText, String key) {
		String plainText = "";
		int j = 0;
		for(int i = 0; i < cipherText.length(); i++) {
			if(Caesar.findIndex(cipherText.charAt(i), letters) == -1) { plainText += cipherText.charAt(i); }
			else {
				if (j >= key.length()) { j = 0; }
				plainText += letterDecrypt(cipherText.charAt(i), Caesar.findIndex(key.charAt(j), letters));
				j++;
			}
		}
		return plainText;
	}
	
	public static ArrayList<Integer> findCoincidence(String cipherText) {
		int spaceCount = 1;
		cipherText = cipherText.replaceAll("\\s+","");
		ArrayList<ArrayList<Character>> A = new ArrayList<ArrayList<Character>>();
		ArrayList<Integer> coincidences = new ArrayList<Integer>();
		for (int i = 1; i < cipherText.length(); i++) {
			ArrayList<Character> a = new ArrayList<Character>();
			int temp = 0;
			for  (int j = 0; j < spaceCount; j++) { a.add(' '); }
			for (int k = 0; k < (cipherText.length() - spaceCount); k++) { a.add(cipherText.charAt(k)); }
			for (int h = spaceCount; h < (cipherText.length()); h++) {
				if (Character.toLowerCase(a.get(h)) == Character.toLowerCase(cipherText.charAt(h))) { temp++; }
			}
			coincidences.add(temp);
			A.add(a);
			spaceCount++;
		}	
		return coincidences;
	}
	
	public static int averageNoZero(ArrayList<Integer> a) {
		int total = 0;
		for (int i = 0; i < a.size(); i++) { 
			if (a.get(i) != 0)
				total += a.get(i); 
		}
		return total/a.size();
	}
	
	public static int keyLength() {
		int keylen = 0;
		
		return keylen;
	}
	
	public static void main(String[] args) {
		String ex = "Attack at dawnAttack at dawn hey hi I love you";
		String key = "lemonkkovjlwnvnoweiasnvp";
		
		System.out.println(Encrypt(ex, key));
		
		System.out.println(Decrypt(Encrypt(ex, key), key));
		
		ArrayList<Integer> k = findCoincidence(Decrypt(Encrypt(ex, key), key));
		
		for (int i = 0; i < k.size(); i++) {
			System.out.print(k.get(i) + " || ");
		}
		System.out.print("\n");
		System.out.println(averageNoZero(k));
	}

}
