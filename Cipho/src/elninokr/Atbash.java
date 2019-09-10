package elninokr;

import java.util.HashMap;

public class Atbash {
	private static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
            'w', 'x', 'y', 'z'};
	
	public static HashMap<String, String> map = new HashMap();
	
	public static void setUp(String input) {
		String[] split = input.toLowerCase().split("\\s+");
		if(split.length % 2 == 0) {
			for(int i = 0; i < split.length; i++)
				map.put(split[i], split[split.length - i - 1]);
		}else {
			System.out.println("Number of characters must be even.");
		}
	}
	
	public static String Cipher(String initialtext) {
		String finaltext = new String();
		for (int i = 0; i < initialtext.length(); i++) {
			if( !map.containsKey(Character.toString(initialtext.toLowerCase().charAt(i)))) 
				finaltext += Character.toString(initialtext.toLowerCase().charAt(i));
			else 
				finaltext += map.get(Character.toString(initialtext.toLowerCase().charAt(i)));
		}
		return finaltext;
	}
	
	public static void main(String[] args) {
		
		String temp = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
		setUp(temp);
			
		String ex = "Hi Karim I love Cockrvns ws";

		System.out.println(Cipher(ex));
		System.out.println(Cipher(Cipher(ex)));
	}

}
