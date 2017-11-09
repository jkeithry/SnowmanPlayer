public class SnowmanPlayer{
	
	public static void main(String args[])
	{
	
	}
	
	public static String getAuthor()
	{
		return "Keith, Jeffrey";
	}
	
	public static void startGame(String[] words, int minLength, int maxLength, String allowedChars)
	{
		/*count induvidual letter freqencies in the words array*/		
		int wordCount = 0;		
		int[] letterFreq = new int[26];
		//test each word
		for( String w : words){			
			wordCount++ ; 
			//test each char (in each word) that appears in allowedChars
			for(int i = 0; i < w.length(); i++){
				char c = w.charAt(i);
				int letter = allowedChars.indexOf(c);
				letterFreq[letter]++;				
			}
		}	
	}
	
	public static void startNewWord(int length)
	{
		
	}
	
	public static char guessLetter(String pattern, String previousGuesses)
	{
		//guess every letter
		String allowedChars = "abcdefghijklmnopqrstuvwxyz";		
		int count = previousGuesses.length();
		char result = allowedChars.charAt(count);
		count++;		
		return result;		
	}
}
