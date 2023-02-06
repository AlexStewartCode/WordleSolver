import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class runner 
{
	public static Random numGen = new Random();
	public static void main(String [] args) throws IOException
	{
		HashMap<Character, Integer> letterScores = new HashMap<Character, Integer>(); 
		populateLetterScores(letterScores);
		wordleDict dict = new wordleDict();
		addWords(dict, letterScores);
		
		int [] correctGuesses = new int[10];
		Arrays.fill(correctGuesses,0);
		
		//for(int i = 0; i < 100; i++)
		{
			correctGuesses[playGame(dict)] ++;
			addWords(dict, letterScores);
		}
		
		
		for(int thing : correctGuesses)
		{
			System.out.println(thing);
		}
	}
	
	public static int playGame(wordleDict dict)
	{
		boolean stillGoing = true; 
		String wordleWord = "lemon";
		String guessResult;
		String guess; 
		int guessNum = 0; 
		while(stillGoing)
		{
			guess = dict.getBestWord().trim();
			guessResult = guessWord(guess, wordleWord);
			if(guessResult.equals("GGGGG") || guessNum == 9)
			{
				stillGoing = false;
			}
			else 
			{
				guessNum ++;
				for(int i = 0; i < guessResult.length(); i ++)
				{
					switch(guessResult.charAt(i))
					{
					case('G'):
						dict.greenCullWords(i, guess.charAt(i));
					break;
					case('E'):
						dict.yellowCullWords(-1, guess.charAt(i));
					break;
					case('Y'):
						dict.yellowCullWords(i, guess.charAt(i));
					break;
					}
				}
			}
//			
//			System.out.println("Correct word: " + wordleWord);
//			System.out.println("Guessed word: " + guess);
//			System.out.println("Result: " + guessResult);
		}
		if(guessNum == 0)
		{
			System.out.println("Correct word: " + wordleWord);
		}
		return guessNum;
	}
	
	public static String chooseWordle(wordleDict dict)
	{
		return dict.getRandomWord(numGen);
	}
	
	public static String guessWord(String guess, String correctWord)
	{
		String output = ""; 
		for(int i = 0; i < guess.length(); i++)
		{
			char c = guess.charAt(i);
			if(correctWord.charAt(i) == c)
			{
				output += "G";
			}
			else 
				if(correctWord.contains(Character.toString(c)))
				{
					output += "Y";
				}
				else
				{
					output += "E";
				}
		}
		return output;
	}
	
	public static void addWords(wordleDict dict, HashMap<Character, Integer> letterScores) throws IOException
	{
		dict.clear();
		BufferedReader bf;
		try 
		{
			boolean hasNext = true; 
			bf = new BufferedReader(new FileReader("words.txt"));
			String newWord = bf.readLine();
			while(hasNext)
			{
				dict.addWord(newWord, letterScores);
				newWord = bf.readLine();
				if(newWord == null)
					hasNext = false;
			}
			bf.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void populateLetterScores(HashMap<Character, Integer> letterScores)
	{
		BufferedReader bf;
		try 
		{
			bf = new BufferedReader(new FileReader("letterScores.txt"));
			String currentScore = bf.readLine(); 
			while(currentScore != null)
			{
				char [] letters = currentScore.substring(0, currentScore.indexOf(' ')).toCharArray();
				int score = Integer.parseInt(currentScore.substring(currentScore.indexOf(' ')).trim());
				for(int i = 0; i < letters.length; i ++)
				{
					letterScores.put(letters[i], score);
				}
				currentScore = bf.readLine();
			}
			bf.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
