import java.util.stream.*;
import java.util.Arrays;
public class SnowmanPlayer{		
	private static  int[] letterFreq5 = new int[26];
	private static  int[] letterFreq6 = new int[26];
	private static  int[] letterFreq7 = new int[26];
	private static  int[] letterFreq8 = new int[26];
	private static  int[] letterFreq9 = new int[26];
	private static  int[] letterFreq10 = new int[26];
	private static  int[] letterFreq11 = new int[26];
	private static  int[] letterFreq12 = new int[26];
	private static  int[] letterFreq13 = new int[26];
	private static  int[] letterFreq14 = new int[26];
	private static  int[] letterFreq15 = new int[26];
	private static String guessSequence = "abcdefghijklmnopqrstuvwxyz";
	

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
		letterFreq5 = wordFreqMaker(5, words);
		letterFreq6 = wordFreqMaker(6, words);
		letterFreq7 = wordFreqMaker(7, words);
		letterFreq8 = wordFreqMaker(8, words);
		letterFreq9 = wordFreqMaker(9, words);
		letterFreq10 = wordFreqMaker(10, words);
		letterFreq11 = wordFreqMaker(11, words);
		letterFreq12 = wordFreqMaker(12, words);
		letterFreq13 = wordFreqMaker(13, words);
		letterFreq14 = wordFreqMaker(14, words);
		letterFreq15 = wordFreqMaker(15, words);		
	}	
	/**	
	*Analyses new word and applies letterFreq profile based on word length.
	*@param args
	*/
	public static void startNewWord(int length)
	{		
		if(length == 5){		
			guessSequence = arrayToString(letterFreq5);		
		}
		if(length == 6){
			guessSequence = arrayToString(letterFreq6);
		}
		if(length == 7){
			guessSequence = arrayToString(letterFreq7);
		}
		if(length == 8){ 
			guessSequence = arrayToString(letterFreq8);
		}
		if(length == 9){
			guessSequence = arrayToString(letterFreq9);
		}
		if(length == 10){
			guessSequence = arrayToString(letterFreq10);
		}
		if(length == 11){
			guessSequence = arrayToString(letterFreq11);
		}
		if(length == 12){
			guessSequence = arrayToString(letterFreq12);
		}
		if(length == 13){
			guessSequence = arrayToString(letterFreq13);
		}
		if(length == 14){
			guessSequence = arrayToString(letterFreq14);
		}
		if(length == 15){			
			guessSequence = arrayToString(letterFreq15);
		}
			
	}	
	/**	
	*@param args
	*/
	public static char guessLetter(String pattern, String previousGuesses)
	{
		//make guess based on first letter, second letter etc.
		
		//guess from the letter frequency sets			
		int count = previousGuesses.length();
		char result = guessSequence.charAt(count);
		count++;		
		return result;		
	}
	/**
	*converts array data to a usable string for guessLetter
	*/
	private static String arrayToString(int[] frequency){		
		//didnt sort properly without arraycopy method???????????????????
		int [] tmp = new int[frequency.length];
		System.arraycopy( frequency, 0, tmp, 0, frequency.length );
		Arrays.sort(tmp);		
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		String reversed = "";
		String result ;
		for(int i = 0; i < tmp.length; i++){			
			for(int j = 0; j < frequency.length; j++){
				if(tmp[i] == frequency[j]){
					reversed += alpha.charAt(j);
				}
			}
		}
		result = reverse(reversed);
		
		return result;
	}
	/**
	*Reverses the string given by arrayToString in order to get the proper order. I can probably just guess backwards?
	*/
	private static String reverse(String str){
		String reversedStr;
		if(str.length() == 1){	    
			return str;
		}
		char first = str.charAt(0);
		String remain = str.substring(1);
		reversedStr = reverse(remain) + first;	
		return reversedStr;	
	}
	/**
	*Makes the letter occurance array based on word length.
	*/
	private static int[] wordFreqMaker(int length, String[] words){
		String allowedChars = "abcdefghijklmnopqrstuvwxyz";
		int[] result = new int[26];
		for( String w : words){	
			if(w.length() == length){
				for(int i = 0; i < length; i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					result[letter]++;			
				}
			}				
		}	
		return result;						
	}	
}

