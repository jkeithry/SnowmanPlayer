import java.util.*;
import java.util.Arrays;
/**
 * @author   Jeffrey Keith <jkeith@ryerson.com>
 * 3431 errors over 2000 words. 11 mins time.
 */
public class SnowmanPlayer{	
	private static List<String> myWords = new ArrayList<String>();	
	private static String chars = "";	
	public static void main(String args[])	{	
	}
	/**	
	*Returns the class authors name.
	*@param args
	*@return 	authors name.
	*/
	public static String getAuthor(){
		return "Keith, Jeffrey";
	}	
	/**	
	*This method is run at the start of the game and gathers all the legitimate words into an ArrayList.
	*@param words the array of words to be used in the game.
	*@param minLength the minium length of any word. 
	*@param maxLength the max length of any word in the list of words. 
	*@param allowedChars the allowable characters in anyword.
	*/
	public static void startGame(String[] words, int minLength, int maxLength, String allowedChars)	{				
		for( String w : words){	//Gathers all the words into a new arraylist.
			myWords.add(w);							
		}		
	}
	private static List<String> myWorkingWords = new ArrayList<String>();//List of words currently working with.
	private static int totalGuess = 0;// counts total guesses.	
	/**	
	*Analyses new word. Set workingChars to a string with the highest occuring letter first.
	*@param length the length of the unknown word.
	*/
	public static void startNewWord(int length)	{		
		myWorkingWords = wordFilter(myWords, length);//send myWords to wordFilter to return myWorkingWords.		
		chars = occuranceMaker(myWorkingWords);//sends myWorkingWords to freqMaker.		
		totalGuess = 0;// guesses are zero for a new word.				
	}	
	private static String lastGuess = "";//reset the lastGuess
	/**	
	*Narrow myWorkingWords to words that match the pattern and do not contain previousGuesses(misses)
	*@param pattern the position of correct guesses in unknown word.
	*@param	previousGuesses all the previous guesses made.
	*@return 	char to be guessed.
	*/
	public static char guessLetter(String pattern, String previousGuesses){	
		char result ;		
		if(totalGuess == 0){
			result = chars.charAt(totalGuess);//use the most common letter if no correct guess has been made.			
		}		
		else if (pattern.indexOf(lastGuess)>0){// a new guess has been made. Refine myWorkingWords based on the guess.				
			myWorkingWords = wordFilter(myWorkingWords, lastGuess, pattern);
			chars = occuranceMaker(myWorkingWords);
			result = chars.charAt(0);			
		}
		else{//Filter words based on the incorrect guesses.				
			myWorkingWords = wordFilter(myWorkingWords, lastGuess, pattern);
			chars = occuranceMaker(myWorkingWords);	
			result = chars.charAt(0);
		}				
		//If result is in previousGuesses move to the next best guess.		
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
	*This word filter is used for filtering out words that don't match the pattern.
	*@param words ArrayList of working words.
	*@param lastGuess the last guess made by the program.
	*@param pattern the pattern of the unknown word.
	*@return 	the filtered word ArrayList.
	*/
	private static List<String> wordFilter(List<String> words, String lastGuess, String pattern){			
		List<String> filteredWords = new ArrayList<String>(words);
		List<String> addFilteredWords = new ArrayList<String>();
		String matcher = pattern.replace("*", "\\w");
		String newMatcher = matcher.replace(lastGuess, "!");		
		if(pattern.indexOf(lastGuess)>=0){			
			for(int w = 0; w < words.size();w++){				
				String str = words.get(w);
				String strMatcher = str.replace(lastGuess, "!");
				
				if(strMatcher.matches(newMatcher) == true ){addFilteredWords.add(words.get(w));}				
			}filteredWords = addFilteredWords;
			if(filteredWords.size() == 0){return words;}
		}
		else{
			for(int i = 0; i < words.size(); i++){				
				if(words.get(i).contains(lastGuess) ){filteredWords.remove(words.get(i));}//if a word contins the lastGuess which is wrong in this case, remove		
			}			
		}
		return filteredWords;	
	}
	/**
	* Filters our words from myWords list based on word length.
	*@param words words ArrayList of working words.
	*@param length the length of the current unknown word.
	*@return 	words only of a specific length.
	*/
	private static List<String> wordFilter(List<String> words, int length){			
		List<String> filteredWords = new ArrayList<String>();			
		for(int i = 0; i < words.size(); i++){
			if(words.get(i).length() == length){filteredWords.add(words.get(i));}
			else{continue;}
		}			
		return filteredWords;
	}
	/**
	*Makes the letter occurance array based on word length.
	*Counts the occurance of each letter in the list of words and retuns the count as an array
	*of 26 ints representing each letter of the alphabet.
	*@param words list of words to count through.
	*@return 	a String of letters with the most common letter at index 0 followed by the next 26.
	*/
	private static String occuranceMaker(List<String> words){		
		String allowedChars = "abcdefghijklmnopqrstuvwxyz";
		int[] ltrFrq = new int[26];
		String[] arr = words.toArray(new String[words.size()]);
		String str = Arrays.toString(arr);		
		for(int c = 0; c < str.length();c++){//checks ever allowedChars and counts it.
			char x = str.charAt(c);
			if(x == ',' || x == ' '|| x == '[' || x == ']'){continue;}
			else{
				int letter = allowedChars.indexOf(x);
				ltrFrq[letter]++;	
			}
		}				
		return occuranceToString(ltrFrq);						
	}	
	/**
	*converts array data of 26 elements into a string from most frequent to least frequent occurance.
	*@param occurance[26] a count of how many times each letter occurs in a word set.
	*@return 	A sorted String with the most frequent letter first followed by the next most frequent.
	*/
	private static String occuranceToString(int[] occurance){		
		int [] tmp = new int[occurance.length];		
		System.arraycopy( occurance, 0, tmp, 0, occurance.length );
		Arrays.sort(tmp);	
		String alpha = "abcdefghijklmnopqrstuvwxyz" ;			
		String result ;
		String reversed = "" ;	
		for(int i = 0; i < tmp.length; i++){	//this can probably be written in one loop.		
			for(int j = 0; j < occurance.length; j++){
				if(occurance[j] == 0) {continue;}
				if(tmp[i] == occurance[j]){
					reversed += alpha.charAt(j);
				}
			}
		}		
		result = reverse(reversed);			
		return result;
	}
	/**
	*Reverses the order of a string abc, cba.
	*@param str a string to be reversed
	*@return a reversed string.
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
}
