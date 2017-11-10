import java.util.stream.*;
public class SnowmanPlayer{		
private static int[] letterFreq5 ;
private static char[] guessSequence = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static void main(String args[])
	{	
	}
	/**	
	*@param args
	*/
	public static String getAuthor()
	{
		return "Keith, Jeffrey";
	}	
	/**	
	*/
	public static void startGame(String[] words, int minLength, int maxLength, String allowedChars)
	{
		/*count induvidual letter freqencies in the words array*/	
		int[] letterFreq5 = new int[26];
		int[] letterFreq6 = new int[26];
		int[] letterFreq7 = new int[26];
		int[] letterFreq8 = new int[26];
		int[] letterFreq9 = new int[26];
		int[] letterFreq10 = new int[26];
		int[] letterFreq11 = new int[26];
		int[] letterFreq12 = new int[26];
		int[] letterFreq13 = new int[26];
		int[] letterFreq14 = new int[26];
			
		
		//test each word
		for( String w : words){						
			/*Test each char (in each word) that appears in allowedChars.
			Make a different freqencie profile depending on word length.*/
			if(w.length() == 5){
				for(int i = 0; i < w.length(); i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					letterFreq5[letter]++;				
				}
			}
			if(w.length() == 6){
				for(int i = 0; i < w.length(); i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					letterFreq6[letter]++;				
				}
			}
			if(w.length() == 7){
				for(int i = 0; i < w.length(); i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					letterFreq7[letter]++;				
				}
			}
			if(w.length() == 8){
				for(int i = 0; i < w.length(); i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					letterFreq8[letter]++;				
				}
			}
			if(w.length() == 9){
				for(int i = 0; i < w.length(); i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					letterFreq9[letter]++;				
				}
			}
			if(w.length() == 10){
				for(int i = 0; i < w.length(); i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					letterFreq10[letter]++;				
				}
			}
			if(w.length() == 11){
				for(int i = 0; i < w.length(); i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					letterFreq11[letter]++;				
				}
			}
			if(w.length() == 12){
				for(int i = 0; i < w.length(); i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					letterFreq12[letter]++;				
				}
			}
			if(w.length() == 13){
				for(int i = 0; i < w.length(); i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					letterFreq13[letter]++;				
				}
			}
			if(w.length() == 14){
				//first letter of the word
				char firstChar = w.charAt(0);
				int letter = allowedChars.indexOf(firstChar);
				letterFreq14[letter]++;
				// for(int i = 0; i < w.length(); i++){
					// char c = w.charAt(i);
					// int letter = allowedChars.indexOf(c);
					// letterFreq14[letter]++;				
				// }
			}			
		}
		//print array and stuff to the console
		for(int i = 0; i < allowedChars.length(); i++){
			System.out.print(allowedChars.charAt(i) + " ");
		}
		System.out.print("\n");
			
		for(int i : letterFreq14){
			System.out.print(i + " ");
		}
		System.out.print("\n");
		
		int sum = IntStream.of(letterFreq14).sum();
		System.out.println("The sum is " + sum);
		
		System.out.print("\n");		
		//end of print stuff
	}	
	/**	
	*Analyses new word and applies letterFreq profile based on word length.
	*@param args
	*/
	public static void startNewWord(int length)
	{		
		if(length == 5){
			//guessSequence = letterFreq5;
		}
		if(length == 6){
		}
		if(length == 7){
		}
		if(length == 8){
		}
		if(length == 9){
		}
		if(length == 10){
		}
		if(length == 11){
		}
		if(length == 12){
		}
		if(length == 13){
		}
		if(length == 14){
		}			
	}	
	/**	
	*@param args
	*/
	public static char guessLetter(String pattern, String previousGuesses)
	{
		//guess from the letter frequency sets			
		int count = previousGuesses.length();
		char result = guessSequence[count];
		count++;		
		return result;		
	}
	private static int[] 
	
	
}
