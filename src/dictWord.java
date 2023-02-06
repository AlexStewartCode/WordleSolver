import java.util.HashMap;

public class dictWord implements Comparable<Object>
{
	private int uniqueScore;
	private int yellowScore;
	private String word; 
	
	public dictWord(String word, HashMap<Character, Integer> scores)
	{
		this.word = word; 
		uniqueScore = 0; 
		yellowScore = 1;
		calculateUniqueScore(scores);
	}
	
	private void calculateUniqueScore(HashMap<Character, Integer> scores)
	{
		String uniqueLetters = ""; 
		for(int i = 0; i < word.length(); i++)
		{
			char tempChar = word.charAt(i);
			if(!uniqueLetters.contains(tempChar + ""))
			{
				uniqueLetters += tempChar;
				uniqueScore += scores.get(tempChar);
			}
		}
	}
	
	public void calculateYellowScore(String yellowLetters)
	{
		yellowScore = 1; 
		for(int i = 0; i < word.length(); i ++)
		{
			char [] yellows = yellowLetters.toCharArray(); 
			for(char letter : yellows)
			{
				if(word.contains(letter + ""))
				{
					yellowScore++; 
				}
			}
		}
	}
	
	public String toString()
	{
		return "Word: " + word + "\tScore: " + getTotalScore();
	}
	
	public String getWord()
	{
		return word;
	}
	
	public int getUniqueScore()
	{
		return uniqueScore;
	}

	@Override
	public int compareTo(Object compareWord) 
	{
		return ((dictWord) compareWord).getTotalScore() - this.getTotalScore();
	}

	public int getTotalScore() 
	{
		return yellowScore * uniqueScore;
	}
}