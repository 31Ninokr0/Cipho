package elninokr;

public class Keyboard {
	private static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
            'w', 'x', 'y', 'z'};
	
	private static char[] keyboard = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
			'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c',
			'v', 'b', 'n', 'm'};
	
	public static char Encrypt(char letter) {
		if(findIndex(letter, letters) == -1)
			return letter;
		return keyboard[findIndex(letter, letters)];
	}
	
	public static char Decrypt(char letter) {
		for (int i = 0; i < keyboard.length; i++)
			if (letter == keyboard[i])
				return letters[i];
		return letter;
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
	
	public static void main(String[] args) {
		String ex = "Hi Karim I love Cock";
		String nigga ="";
		for (int i = 0; i < ex.length(); i++) {
			nigga += Encrypt(ex.charAt(i));
		}
		System.out.println(nigga);
		
		

		String nig ="";
		for (int i = 0; i < ex.length(); i++) {
			nig += Decrypt(nigga.charAt(i));
		}
		
		System.out.println(nig);
	}


}
