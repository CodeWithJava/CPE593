package syy;
import java.util.Iterator;
import java.util.List;

public class ArrayListDouble implements InterfaceList, Iterator<Double>
{
    private int capacity;
    private int size;
    private double[] array;
    
    
    // Query Operations
    ArrayListDouble()
    {
    	this(10);
    }
    
    ArrayListDouble(int capacity)
    {
    	this.capacity = capacity;
    	this.size = 0;
    	this.array = new double[this.capacity];
    }
    
    public int size()
    {
    	return this.size;
    }
    
    public boolean isEmpty()
    {
    	return this.size == 0;
    }
    
    @Override
    public syy.Iterator<Double> iterator()
    {
    	Iterator<Double> iterator = new Iterator<Double>()
    	{
    		private int currentIndex = 0;
    		@Override
    		public boolean hasNext()
    		{
    			return currentIndex < size;
    		}
    		@Override
    		public Double next()
    		{
    			if (!hasNext())
    				throw new java.util.NoSuchElementException();
    			return array[currentIndex++];
    		}
    		@Override
    		public void remove()
    		{
    			ArrayListDouble.this.remove(--currentIndex);
    		}
    	};
    	return iterator;
    }
    
    public double[] toArray()
    {
    	double[] result = new double[size + 1];
    	for (int i = 0; i < result.length; i++)
    		result[i] = array[i];
    	return result;
    }
    
    // Modification Operations
    public boolean add(Object e)
    {
    	if (e instanceof Double)
    	{
    		double[] temp = this.array;
    		if (this.size >= this.capacity - 1)
    			this.array = new double[2 * this.capacity];
    		for (int i = 0; i <= this.size; i++)
    			this.array[i] = temp[i];
    		this.array[this.size + 1] = (double) e;
    		this.size++;
    		return true;
    	}
    	else
    		return false;
    }
    
    public boolean remove(Object e)
    {
    	if (e instanceof Double)
    	{
    		double[] temp = this.array;
    		this.array = new double[this.capacity]; // Maybe it can be deleted.
    		int indexOfe = 0;
    		for (int i = 0; i <= this.size; i++)
    		{
    			if (temp[i] == (double)e)
    			{
    				indexOfe = i;
    				break;
    			}
    		}
    		for (int i = 0; i <= this.size; i++)
    		{
    			if (i < indexOfe)
    				this.array[i] = temp[i];
    			else if (i > indexOfe)
    				this.array[i - 1] = temp[i];
    		}
    		this.size--;
    		return true;
    	}
    	else
    		return false;
    }
    
    // Bulk Modification Operations
    public void clear()
    {
    	this.array = new double [10];
    	this.size = 0;
    	this.capacity = 10;
    }
    
    public int trimToSize()
    {
    	this.capacity = this.size;
    	return this.capacity;
    }
    // Positional Access Operations
    public double get(int index)
    {
    	return this.array[index];
    }
    
    public void set(int index, double e)
    {
    	this.array[index] = e;
    }
   
    public void add(int index, double e)
    {
    	double[] temp = this.array;
		if (this.size >= this.capacity - 1)
			this.array = new double[2 * this.capacity];
		for (int i = 0; i <= this.size + 1; i++)
		{
			if (i < index)
			this.array[i] = temp[i];
			else if (i == index)
				this.array[i] = e;
			else if (i > index)
				this.array[i - 1] = temp[i];	
		}
		this.size++;
    }
    
    public double remove(int index)
    {
    	double[] temp = this.array;
    	this.array = new double[this.capacity]; // deleted??
    	double elementOfIndex;
    	for (int i = 0; i <= this.size; i++)
    	{
    		if (i < index)
    			this.array[i] = temp[i];
    		else if (i == index)
    			elementOfIndex = temp[index];
    		else if (i > index)
    			this.array[i - 1] = temp[i];
    	}
    	return elementOfIndex;
    }
	    public int indexOf(double e)
    {
    	for (int i = 0; i <= this.size; i++)
    		if (this.array[i] == e)
    			return i;
    		else
    			if (i == this.size)
    				return -1;
    }
    
    public syy.List<Double> subList(int formIndex, int toIndex)
    {
    	if (formIndex > this.size || toIndex > this.size)
    		throw new IndexOutOfBoundsException("Index out of range.");
    	if (formIndex > toIndex)
    		throw new IllegalArgumentException("Indices are illegal.");
    	double[] subList = new double[toIndex - formIndex];
    	for (int i = 0; i < subList.length; i++)
    		subList[i] = this.array[formIndex + i];
    	return subList;
    }
}