package syy;
public class Trie
{
	private static class TrieNode
	{
		TrieNode [] children;
		boolean isLeaf;

		TrieNode()
		{
			children = new TrieNode[58];
			isLeaf = false;
		}
	}

	TrieNode root;

	public boolean insert(String word)
	{
		if(word == null || word.length() == 0)
			return false;

		TrieNode node = root;
		for(char x: word.toCharArray())
		{
			if(node.children[x - 'A'] == null)
				node.children[x - 'A'] = new TrieNode();
			node = node.children[x - 'A'];
		}
		if(node.isLeaf)
			return false;
		node.isLeaf = true;
		return true;
	}

	public boolean initializeWith(String prefix)
	{
		if(prefix == null || prefix.length() == 0)
			return false;

		TrieNode node = root;
		for(char x: prefix.toCharArray())
		{
			if(node.children[x - 'A'] == null)
				return false;
			else
				node = node.children[x - 'A'];
		}
		return true;
	}

	public boolean search(String word)
	{
		if(root == null)
			return false;
		return search(root,0,word);
	}

	private boolean search(TrieNode node,int k,String word)
	{
		if(node == null)
			return false;
		else if(k == word.length())
			return node.isLeaf;

		char c = word.charAt(k);

		if(c == '.')
		{
			for(TrieNode x: node.children)
			{
				if(search(x,k+1,word))
					return true;
			}
			return false;
		}
		else
		{
			TrieNode x = node.children[c - 'A'];
			return search(x,k+1,word);
		}
	}
}
