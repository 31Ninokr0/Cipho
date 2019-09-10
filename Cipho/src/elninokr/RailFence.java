package elninokr;

public class RailFence {
	public static String Encrypt(String plainText1, int key) {
		String plainText = plainText1 + " ";//Bug fix for miss of last character cipher
		char mat[][]=new char[key][plainText.length()/key];
		String cipherText="";
		int temp = 0;
		
		for(int col = 0; col < plainText.length()/key; col++)
	  	   for(int row = 0; row < key; row++)
	  		   if(temp != plainText.length())
	  			   mat[row][col] = plainText.charAt(temp++);

		for(int row = 0 ; row < key; row++)
			for(int col = 0; col < plainText.length()/key; col++)
				cipherText += mat[row][col];
	   
		return cipherText;
	 }
	
	public static String Decrypt(String cipherText1, int key) {
		String cipherText = cipherText1 + " ";//Bug fix for miss of last character cipher
		char mat[][]=new char[key][cipherText.length()/key];
	  	int temp = 0;
	  	String plainText="";
	 
	  	for(int row = 0; row < key; row++)
	  		for(int col = 0; col < cipherText.length()/key; col++)
	  			mat[row][col] = cipherText.charAt(temp++);
	   
	  	for(int col=0; col < cipherText.length()/key; col++)
	  		for(int row = 0; row < key; row++)
	   			plainText += mat[row][col];
	   
	  	return plainText;
	 }
	
	public static String[] bruteForce(String ciphertext) {
		String[] all = new String[ciphertext.length()+1];
		for (int i = 1; i <= ciphertext.length(); i++) {
			all[i] = Decrypt(ciphertext, i);
		}
		return all;	
	}
	 
//	public static void main(String[] args) throws Exception {
//		String ex = "Writing is done for a number of different purposes and for different audiences. These\r\n" +
//				"different forms of writing are often known as text types at school.\r\n" + 
//				"Factual texts inform, instruct or persuade by giving facts and information. Literary texts\r\n" + 
//				"entertain or elicit an emotional response by using language to create mental images.\r\n" + 
//				"Students are often asked to present an assignment or project which may be one of these text\r\n" + 
//				"types. Below are lists of different text types, purposes and features that are included in the English\r\n" + 
//				"K-6 syllabus. This list may provide hints to help with your child’s written task. Always refer to the\r\n" + 
//				"actual task requirements sent home with your child and remember the type of text used by a writer\r\n" + 
//				"should suit the purpose and the audience"; 
//		
//		int key = ex.length();// 1 <= key <= ex.len +1
//		System.out.println(Encrypt(ex, key));
//		System.out.println(bruteForce(Encrypt(ex, key))[ex.length()]);
//
//	}
}
