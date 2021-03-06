/**
 * @author Ying Cui
 * 
 * Hash function: FNV-1 32bits
 * 
 */
package syy;
import java.io.FileInputStream;
import java.util.Scanner;

public class HashMapLinearChaining1
{
	public static class Chain
	{
		private int key;
		private String word;
		private Chain next;
		
		Chain(int key, String word)
		{
			this.key = key;
			this.word = word;
			this.next = null;
		}
		
		public String getWord()
		{
			return word;
		}
		
		public void setWord(String word)
		{
			this.word = word;
		}
		
		public int getKey()
		{
			return key;
		}
		
		public Chain getNext()
		{
			return next;
		}
		
		public void setNext(Chain next)
		{
			this.next = next;
		}
	}
	
	private Chain[] table;
	private int used; // index
	private double limit; // threshold
	private int[] hist; // collision times
	
	public HashMapLinearChaining1(int initialSize)
	{
		table = new Chain[initialSize];
		for (int i = 0; i < table.length; i++)
			table[i] = null;
		this.used = 0;
		this.limit = 0.95 * initialSize;
		hist = new int[11];
	}
	
	
	// TEST HASH FUNCTION
	public int hash(String word)
	{
		int rv = 0x811c9dc5;
		for (int i = 0; i < word.length(); i++)
		{
			rv ^= word.charAt(i);
			rv *= 0x01000193;
		}
		return Math.abs(rv);
	}
	
	public void add(String word)
	{
		this.used++;
		int code = hash(word) % table.length;
		int key = code % table.length;
		int count = 0;
		if (table[code] == null) // not exist
		{
			table[code] = new Chain(key, word);
			hist[count]++;
		}
		else // exist and collision
		{
			this.used--;
			count++;
			Chain entry = table[code];
			while (entry.getNext() != null) // exist in chain
			{
				entry = entry.getNext();
				count++;
			}
			if (count > 10)
				count = 10;
			hist[count]++;
			entry.setNext(new Chain(key, word)); // come next chain node and add in chain
		}
	}
	
	public boolean contains(String word)
	{
		int code = hash(word) % table.length;
		while (table[code] != null)
		{
			Chain entry = table[code];
			if (entry.getWord().equals(word))
				return true;
			else
				break;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("Hash function: FNV-1 32bits");
		long startTime = System.nanoTime();
		HashMapLinearChaining1 dic = new HashMapLinearChaining1(150000);
		Scanner dictionary = new Scanner(new FileInputStream("dict.txt"));
		while (dictionary.hasNext())
			dic.add(dictionary.next());
		int[] collision = dic.hist;
		for (int i = 0; i < collision.length; i++)
		{
			System.out.print("Insert time of " + (i+1) + ": " + collision[i] + "  ");
			System.out.println(String.format("%.4f", (collision[i]/213557.0)*100.0) + "%");
		}
		dictionary.close();

		long stopTime = System.nanoTime();
		System.out.println("Time: " + (stopTime - startTime) + "ns");
	}
}
