import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class depreciatedDict 
{
	
	HashMap<dictWord, Boolean> dict;
	HashMap<Character, Integer> letterScores; 
	public depreciatedDict()
	{		
		dict = new HashMap<dictWord, Boolean>();
		letterScores = new HashMap<Character, Integer>();
		populateLetterScores();
	}
	
	public void addWord(String newWord)
	{
		dictWord newDictWord = new dictWord(newWord, letterScores);
		dict.put(newDictWord, true);
		System.out.println("Added new word " + newWord + " with unique score of " + newDictWord.getUniqueScore());
	}
	
	public void cullWords(int index, char letter)
	{		
		for(dictWord word : dict.keySet())
		{
			if(word.getWord().charAt(index) == letter)
			{
				dict.put(word, false);
				System.out.println("Removed " + word);
			}
		}
	}
	
	public void populateLetterScores()
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
