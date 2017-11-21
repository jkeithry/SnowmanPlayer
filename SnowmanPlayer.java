import java.util.*;
import java.util.Arrays;
public class SnowmanPlayer{	
	private static List<String> myWords = new ArrayList<String>();
	
	private static String chars = "";	
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
	*	
	*/
	public static void startGame(String[] words, int minLength, int maxLength, String allowedChars)
	{				
		for( String w : words){	//Gathers all the words into a new arraylist.
			myWords.add(w);							
		}		
	}
	//current working words
	private static List<String> myWorkingWords = new ArrayList<String>();//List of words currently working with.
	private static int totalGuess = 0;// counts total guesses.
	private static int crctCnt = 0;// counts correct guesses.
	private static int prevCrctCnt = 0;
	/**	
	*Analyses new word. Set workingChars to a string with the highest occuring letter first.
	*@param args
	*/
	public static void startNewWord(int length)	
	{		
		myWorkingWords = wordFilter(myWords, length);	//send myWords to wordFilter to return myWorkingWords.
		chars = "abcdefghijklmnopqrstuvwxyz"; //testing string
		//chars = occuranceMaker(myWorkingWords, length);//sends myWorkingWords to freqMaker.
		totalGuess = 0;// guesses are zero for a new word.
		crctCnt = 0;
		prevCrctCnt = 0;
		
	}	
	private static String lastGuess = "";
	/**	
	*Narrow myWorkingWords to words that match the pattern and do not contain previousGuesses(misses)
	*@param args 
	*/
	public static char guessLetter(String pattern, String previousGuesses)
	{	
		// System.out.println(pattern);
		char result ;
		//was the last guess correct?	
		boolean crctGuess = false;
		crctCnt = correctCounter(pattern);//count correct guesses.		
		if(crctCnt > prevCrctCnt){ crctGuess = true; prevCrctCnt = crctCnt;}
				
		if(totalGuess == 0){
			result = chars.charAt(totalGuess);//use the most frequent letter if no correct guess has been made.
		}	
		else if (crctGuess){// a new guess has been made. Refine myWorkingWords based on the guess.		
			myWorkingWords = wordFilter(myWorkingWords, lastGuess, pattern, crctGuess);
			chars = occuranceMaker(myWorkingWords, pattern.length());
			result = chars.charAt(0);
			// result = chars.charAt(totalGuess);
		}
		else{//Filter words based on the correct guesses.
			myWorkingWords = wordFilter(myWorkingWords, lastGuess, pattern, crctGuess);
			chars = occuranceMaker(myWorkingWords, pattern.length());	
			result = chars.charAt(0);
		}
		// System.out.println(Arrays.toString(myWorkingWords.toArray()));		
		//Ensure the guess has not already been made;
		boolean badG = true;
		int altCnt = 0;
		while(badG){			
			if(previousGuesses.indexOf(result) > -1){			
			result = chars.charAt(altCnt++);
			}
			else{badG = false;}
		}			
		lastGuess = Character.toString(result);		
		totalGuess++;		
		return result;
		
	}
	/**	
	*This word filter is used for filtering words out that don't match the known pattern
	*@param args
	*/
	private static List<String> wordFilter(List<String> words, String lastGuess, String pattern, boolean crctGuess){			
		List<String> filteredWords = new ArrayList<String>(words);
		List<String> addFilteredWords = new ArrayList<String>();
		if(crctGuess){
			for(int i = 0; i < words.size(); i++){
				if(words.get(i).indexOf(lastGuess) == pattern.indexOf(lastGuess)){
					addFilteredWords.add(words.get(i));
				}
			}return addFilteredWords;
		}
		else{
			for(int i = 0; i < words.size(); i++){				
				if(words.get(i).contains(lastGuess)) {filteredWords.remove(words.get(i));}		
			}			
		}
		return filteredWords;	
	}
	/**	
	*@param args
	*/
	private static List<String> wordFilter(List<String> words, int length){			
		List<String> filteredWords = new ArrayList<String>();			
		for(int i = 0; i < words.size(); i++){
			if(words.get(i).length() == length){filteredWords.add(words.get(i));}
		}			
		return filteredWords;
	}
	/**
	*Makes the letter occurance array based on word length.
	*/
	private static String occuranceMaker(List<String> words, int length){		
		String allowedChars = "abcdefghijklmnopqrstuvwxyz";
		int[] ltrFrq = new int[26];
		for( String w : words){	
			//if(w.length() == length){
			for(int i = 0; i < length; i++){
				char c = w.charAt(i);
				int letter = allowedChars.indexOf(c);
				ltrFrq[letter]++;			
			}
						
		}
		//returns the array count of letters as a string in order of most common letter first.	
		//System.out.println(Arrays.toString(ltrFrq));
		return occuranceToString(ltrFrq);						
	}	
	/**
	*converts array data of 26 elements into a string from most frequent to least frequent occurance.
	*/
	private static String occuranceToString(int[] occurance){		
		int [] tmp = new int[occurance.length];
		System.arraycopy( occurance, 0, tmp, 0, occurance.length );
		Arrays.sort(tmp);		
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		String reversed = "";
		String result ;
		//this can probably be written in one loop.
		for(int i = 0; i < tmp.length; i++){			
			for(int j = 0; j < occurance.length; j++){
				if(tmp[i] == occurance[j]){
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
	private static int correctCounter(String pattern){
		String correct = "";
		for(int i = 0 ; i < pattern.length(); i++){
				if(pattern.charAt(i) != '*'){correct = correct + pattern.charAt(i);}
			}
		return correct.length();
	}	
}


