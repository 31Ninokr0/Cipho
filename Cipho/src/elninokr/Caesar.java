package elninokr;

import java.util.HashMap;

public class Caesar {
	public static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
            'w', 'x', 'y', 'z'};
	
	//http://www.oxfordmathcenter.com/drupal7/node/353
	public static double[] letterFreq = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153,
			0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 	0.05987, 0.06327, 0.09056, 0.02758, 0.00978,
			0.02360, 0.00150, 0.01974, 0.00074};
	
	public static String Encrypt(String plaintext, int key) {
		String ciphertext = new String();
		key = key%letters.length;
		for (int i = 0; i < plaintext.length(); i++) 
			if(findIndex(plaintext.charAt(i), letters) == -1)
				ciphertext += plaintext.charAt(i);
			else 
				ciphertext += letters[ (findIndex(plaintext.charAt(i), letters) + key) % letters.length];
		return ciphertext;
	}
	
	public static String Decrypt(String ciphertext, int key) {
		String plaintext = new String();
		key = key%letters.length;
		for(int i = 0; i < ciphertext.length(); i++) 
			if(findIndex(ciphertext.charAt(i), letters) == -1)
				plaintext += ciphertext.charAt(i);
			else
				plaintext += letters[Math.floorMod(findIndex(ciphertext.charAt(i), letters) - key, letters.length)];
		return plaintext;
	}
		
	public static int findIndex(char find, char[] list) {
		int low = 0;
	    int high = list.length - 1;
	    int mid;
	    while (low <= high) {
	    	mid = (low + high) / 2;
	        if (list[mid] < Character.toLowerCase(find)) 
	        	low = mid + 1;
	        else if (list[mid] > Character.toLowerCase(find)) 
	             high = mid - 1;
	        else 
	             return mid;
	        }
	    return -1;
	}
	
	public static String[] bruteForce(String plaintext) {
		String[] all = new String[letters.length];
		for (int i = 0; i < letters.length; i++) {
			all[i] = Encrypt(plaintext, i);
		}
		return all;
	}
	
	public static double letFreq(String plaintext, char a) {
		double freq = 0.0; int total = 0;
		for(int i = 0; i < plaintext.length(); i++) {
			if(findIndex(plaintext.charAt(i), letters) != -1) 
				total++; if(Character.toLowerCase(plaintext.charAt(i)) == Character.toLowerCase(a)) freq++;
		}
		return freq/total;
	} 
	
	
	public static double[] statsAttack(String ciphertext) {
		HashMap<Character, Double> map = new HashMap();
		String chars = new String();
		for(int j = 0; j < ciphertext.length(); j++) {
			if (!map.containsKey(Character.toLowerCase(ciphertext.charAt(j))) & findIndex(ciphertext.charAt(j), letters) != -1) {
				map.put(ciphertext.charAt(j), letFreq(ciphertext, ciphertext.charAt(j)));
				chars += ciphertext.charAt(j);
			}
		}
		
		//String ciphertext = new String();
		double[] phi = new double[letters.length];
		for (int i = 0; i < letters.length; i++) {
			for (int k = 0; k < chars.length(); k++) {
				phi[i] = map.get(chars.charAt(k)) * letterFreq[findIndex(chars.charAt(k), letters)]; 
			}
		}
		return phi;	
	}
	
	public static void main(String[] args) {
		String ex = "Hello World";
		String test = Encrypt(ex, 3);

		System.out.println(test);
		
		System.out.println(Decrypt(test, 3));
		
			
		for (int i = 0; i < letters.length; i++) System.out.println(statsAttack(test)[i]);
	}
}
