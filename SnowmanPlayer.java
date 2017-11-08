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
		//count induvidual letter freqencies in the words array	
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
