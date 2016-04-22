package syy;

import java.util.Iterator;
public class Test2
{
	public static void main(String [] args)
	{
		LinkedListDouble x = new LinkedListDouble();
		x.addLast(1.1);
		Iterator m = x.iterator();
		if(m.hasNext())
			System.out.println(m.next());
		else
			System.out.println("No other element.");
		
		LinkedListInteger y = new LinkedListInteger();
		y.addLast(1);
		Iterator n = y.iterator();
		if(n.hasNext())
			System.out.println(n.next());
		else
			System.out.println("No other element.");
	}
}
