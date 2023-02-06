import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class wordleDict 
{
	ArrayList <dictWord> dict; 
	String yellowLetters; 
	
	public wordleDict()
	{
		yellowLetters = "";
		dict = new ArrayList<dictWord>();
	}
	
	public String getRandomWord(Random numGen)
	{
		return dict.get(numGen.nextInt(dict.size())).getWord();
	}
	
	public void addWord(String newWord, HashMap<Character, Integer> letterScores)
	{
		dictWord newDictWord = new dictWord(newWord, letterScores);
		dict.add(newDictWord);
	}
	
	public String getBestWord()
	{
		sortWords();
		return dict.get(0).getWord();
	}
	
	public void sortWords()
	{
		Collections.sort(dict);
	}
	
	public void calculateWordScores()
	{
		if(yellowLetters.equals(""))
			return;
		
		for(dictWord word : dict)
		{
			word.calculateYellowScore(yellowLetters);
		}
		yellowLetters = "";
	}
	
	public void greenCullWords(int index, char letter)
	{
		ArrayList<dictWord> removalList = new ArrayList<dictWord>();
		for(dictWord word : dict)
		{
			
			if(word.getWord().charAt(index) != letter)
			{
				removalList.add(word);
			}
		}
		dict.removeAll(removalList);
	}
	
	public void yellowCullWords(int index, char letter)
	{
		ArrayList<dictWord> removalList = new ArrayList<dictWord>();
		if(index > -1)
		{
			yellowLetters += letter;
			for(dictWord word : dict)
			{
				if(word.getWord().charAt(index) == letter)
				{
					removalList.add(word);
				}
			}
		}
		else
		{
			for(dictWord word : dict)
			{
				if(word.getWord().contains(letter + ""))
				{
					removalList.add(word);
				}
			}
		}
		dict.removeAll(removalList);
	}
	
	public String toString()
	{
		String output = ""; 
		for(dictWord word : dict)
		{
			output += word.toString() + "\n";
		}
		return output;
	}

	public void clear() 
	{
		dict.clear();
	}
}
