/**
 * @author Ying Cui
 * 
 * Hash function: Murmur 32bits
 * 
 */
package syy;
import java.io.FileInputStream;
import java.util.Scanner;

public class HashMapLinearChaining3
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
	
	public HashMapLinearChaining3(int initialSize)
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
		byte[] bytes = word.getBytes();
		int m = 0x5bd1e995;
		int r = 24;
		int h = 0x9747b28c ^ bytes.length;
		int length4 = bytes.length / 4;
		for (int i = 0; i < length4; i++)
		{
			int i4 = i * 4;
			int k = bytes[i4 + 0] & 0xff + ((bytes[i4 + 1] & 0xff) << 8)
					+ ((bytes[i4 + 2] & 0xff) << 16) + ((bytes[i4 + 3] & 0xff) << 24);
			k *= m;
			k ^= k >>> r;
			k *= m;
			h *= m;
			h ^= k;
		}
		switch(bytes.length%4)
		{
		case 3:
			h ^= (bytes[(bytes.length&~3) + 2] & 0xff) << 16;
		case 2:
			h ^= (bytes[(bytes.length&~3) + 1] & 0xff) << 8;
		case 1:
			h ^= (bytes[bytes.length&~3] & 0xff);
			h *= m;
		}
		h ^= h >>> 13;
		h *= m;
		h ^= h >>> 15;
		return Math.abs(h);
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
		System.out.println("Hash function: Murmur 32bits");
		long startTime = System.nanoTime();
		HashMapLinearChaining3 dic = new HashMapLinearChaining3(150000);
		Scanner dictionary = new Scanner(new FileInputStream("dict.txt"));
		while (dictionary.hasNext())
			dic.add(dictionary.next());
		int[] collision = dic.hist;
		for (int i = 0; i < collision.length; i++){
			System.out.print("Insert time of " + (i+1) + ": " + collision[i] + "  ");
			System.out.println(String.format("%.4f", (collision[i]/213557.0)*100.0) + "%");
		}
		dictionary.close();
		
		long stopTime = System.nanoTime();
		System.out.println("Time: " + (stopTime - startTime) + "ns");
	}
}
