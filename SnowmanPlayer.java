//191 misses ofer 20 words
import java.util.stream.*;
import java.util.Arrays;
import java.util.*;
import java.util.regex.Pattern;
public class SnowmanPlayer{	
	//Data extracted from dictionary. index 5 - 15 stores the letter occurances by word length
	private static String[] letterFreq = new String[20];	
	//words based on length
	private static String[][] wordsByLen = new String[50][5000];	
	// private static List<String> fiveLetterWords = new ArrayList<String>();
	private static String guessSequence = "";

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
		for(int i = minLength; i <= maxLength;i++){
			letterFreq[i] = wordFreqMaker(i, words);
		}		
		for(int i = minLength; i <= maxLength;i++){
			List<String> wordsLen = new ArrayList<String>();
			for( String w : words){
				if( w.length() == i){
					wordsLen.add(w);
				}				
			}
			wordsByLen[i] = wordsLen.toArray(wordsByLen[i]);			
		}
		//printing wordsByLen		
		// for(int i = minLength; i < maxLength; i++){
			// String str = wordFreqMaker(i, wordsByLen[i]);
			// System.out.println(str);	
		// }
	
	}		
	/**	
	*Analyses new word and applies letterFreq profile based on word length.
	*@param takes in and int word length
	*/
	public static void startNewWord(int length)
	{				
		guessSequence = letterFreq[length];				
	}	
	/**	
	*this is going to get messy
	*@param args
	*/
	public static char guessLetter(String pattern, String previousGuesses)
	{			
		//First guess from the letter frequency array	
		String nothing = new String(new char[pattern.length()]).replace("\0", "*");
		int count = previousGuesses.length();		
		int cnt = 0;
		char result = guessSequence.charAt(count);
		//System.out.println("GS0 : " + guessSequence);
		outer:		
		while(true){				
			if(pattern.equals(nothing)){		
				result = guessSequence.charAt(count);
				// System.out.println("GS1 : " + guessSequence);
			}
			else{
				String[] arr = wordFilter(wordsByLen[pattern.length()], pattern);			
				guessSequence = wordFreqMaker(pattern.length(), arr);
				result = guessSequence.charAt(cnt);
				System.out.println("GS2 : " + guessSequence);
			}
			if(previousGuesses.indexOf(result) != -1){cnt++; continue outer;}
			else{break;}
		}
		System.out.println(pattern);
		return result;		
	}
	/**
	*converts array data of 26 elements into a string from most frequent to least frequent occurance.
	*/
	private static String arrayToString(int[] frequency){		
		
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
	private static String wordFreqMaker(int length, String[] words){
		String allowedChars = "abcdefghijklmnopqrstuvwxyz";
		int[] arrayOfFreq = new int[26];
		for( String w : words){	
			if(w.length() == length){
				for(int i = 0; i < length; i++){
					char c = w.charAt(i);
					int letter = allowedChars.indexOf(c);
					arrayOfFreq[letter]++;			
				}
			}				
		}
		//returns the array count of letters as a string in order of most common letter first.
		return arrayToString(arrayOfFreq);						
	}	
	private static String[] wordFilter(String[] words, int length){			
		List<String> filteredWords = new ArrayList<String>();			
		//String[] result = new String[26];
		for(int i = 0; i < words.length; i++){
			if(words[i].length() == length){filteredWords.add(words[i]);}
		}		
		String[] result = filteredWords.toArray(new String[26]);
		return result;
	}
			
	private static String[] wordFilter(String[] words, String pattern){
		List<String> filteredWords = new ArrayList<String>();		
		for(int i = 0; i < words.length; i++){
			for(int j = 0; j < pattern.length(); j++){
				if(words[i].charAt(j) == pattern.charAt(j)){
					filteredWords.add(words[i]);
				}						
			}			
		}	
		String[] result = filteredWords.toArray(new String[26]);
		return result;		
	}	
}
