import java.util.Iterator;
public class ArrayListInteger implements interface IList, Iterable
{
	// set default capacity as 10 
	private static final int DEFAULT_CAPACITY = 10;
	
	// define characteristic
	private capacity;
	private size;
	private int [] data;
	
	// initialize with defaul capacity
	public ArrayListInteger()
	{
		this(DEFAULT_CAPACITY);
	}

	// intialize with defination of user
	public ArrayListInteger(int capacity)
	{
		this.size = 0;
		this.capacity = capacity;
		data = new int [capacity];
	}

	// clear the arraylist
	public void clear();
	{
		this(DEFAULT_CAPACITY);
	}

	// get the size of the arraylist
	public int size();
	{
		return size;
	}

	// trim the capacity into size
	public void trimToSize()
	{
		capacity = size;
	}

	public boolean isEmpty()
	{
		return size == 0;
	}
	// get element at specific index
	public int get(int index)
	{
		if(index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException();
		return data[index];
	}

	// set the value of element at specific index
	public void set(int index, int value)
	{
		if(index < 0 || index >= size)
			System.out.println("Out of ArrayBound:" + index);
		data[index] = value;
	}

	// add element at the head of arraylist
	public void addHead(int value)
	{
		add(0,value);
	}
	
	// add element at the tail of arraylist
	public void addTail(int value)
	{
		add(size,value);
	}
	
	// append element at the tail of arraylist
	public void append(int value)
	{
		add(size,value);
	}

	// add element after specific index
	public void add(int index,int value)
	{
		if(index < 0 || index > size)
			System.out.println("Out of ArrayBound:" + index);
		if(size == capacity)
			expendCapacity();
		for(int i = size;i > index;i--)
			data[i] = data[i-1];
		data[index] = value;
		size++;
	}
	
	// remove element at specific index
	public void remove(int index)
	{
		for(int i = index;i < size;i++)
			data[i] = data[i+1];
		size--;
	}

	// create an iterator for the arraylist
	public Iterator iterator()
	{
		return new ArrayListIntegerIterator();
	}

	private class ArrayListIntegerIterator implements Iterator
	{
		private int current = 0;
		
		public boolean hasNext()
		{
			return current < size;
		}

		public int next()
		{
			if(!hasNext())
				System.out.println("No Such Element");
			return data[current++];
		}
	}

	// expend the capacity of arraylist
	private void expendCapacity()
	{
		int [] t = new int [capacity*2];
		for(int i = 0;i < size;i++)
			t[i] = data[i];
		data = t;
		capacity = data.length;
	}
}