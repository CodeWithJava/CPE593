package syy;

import java.util.Iterator;
public class Test
{
	public static void main(String [] args)
	{
		ArrayListDouble x = new ArrayListDouble();
		x.append(1.1);
		Iterator m = x.iterator();
		if(m.hasNext())
			System.out.println(m.next());
		
		ArrayListInteger y = new ArrayListInteger();
		y.append(1);
		Iterator n = y.iterator();
		if(n.hasNext())
			System.out.println(n.next());
		

	}
}
