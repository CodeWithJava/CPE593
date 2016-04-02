/**
CopyRight:
Project:
Module ID:
Comment:
Course: CPE 593 Applied Data Structures and Algorithms
Title: Implement ArrayList & HashMap
JDK Version: 1.8.0_71
Author: Yabin Han
Create Date: March 13th 2016
Finish Date:  2016
Description: Implement Interface List 
*/
package syy
import java.util.Collection;

public interface InterfaceList extends Collection<E>
{
        // Query Operations
        
        /**
         * Returns the number of elements in this list.
         * If this list contains more than <tt>Integer.MAX_VALUE</tt> elements,
         * returns <tt>Integer.MAX_VALUE</tt>
         *
         * @return the number of elements in this list 
         */
        int size();
        
        /**
         * Return <tt>true</tt> if this list contains no elements.
         *
         * @return tt>true</tt> if this list contains no elements
         */
        boolean isEmpty();
        
        /**
         * Returns an array containing all of the elements in this list in proper
         * sequence (from first to last element).
         *
         * <p>The returned array will be "safe" in that no references to it are
         * maintained by this list.  (In other words, this method must
         * allocate a new array even if this list is backed by an array).
         * The caller is thus free to modify the returned array.
         *
         * <p>This method acts as bridge between array-based and collection-based
         * APIs.
         *
         * @return an array containing all of the elements in this list in proper
         *         sequence
         * @see Arrays#asList(Object[])
         */
        Iterator<E> iterator();
        
        
        Object toArray();
        
        // Modification Operations
                
        /**
         * Appends the specified element to the end of this list (optional
         * operation).
         *
         * <p>Lists that support this operation may place limitations on what
         * elements may be added to this list.  In particular, some
         * lists will refuse to add null elements, and others will impose
         * restrictions on the type of elements that may be added.  List
         * classes should clearly specify in their documentation any restrictions
         * on what elements may be added.
         *
         * @param e element to be appended to this list
         * @return <tt>true</tt> (as specified by {@link Collection#add})
         * @throws UnsupportedOperationException if the <tt>add</tt> operation
         *         is not supported by this list
         * @throws ClassCastException if the class of the specified element
         *         prevents it from being added to this list
         * @throws NullPointerException if the specified element is null and this
         *         list does not permit null elements
         * @throws IllegalArgumentException if some property of this element
         *         prevents it from being added to this list
         */
        boolean add(E e);

        /**
         * Removes the first occurrence of the specified element from this list,
         * if it is present (optional operation).  If this list does not contain
         * the element, it is unchanged.  More formally, removes the element with
         * the lowest index <tt>i</tt> such that
         * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
         * (if such an element exists).  Returns <tt>true</tt> if this list
         * contained the specified element (or equivalently, if this list changed
         * as a result of the call).
         *
         * @param o element to be removed from this list, if present
         * @return <tt>true</tt> if this list contained the specified element
         * @throws ClassCastException if the type of the specified element
         *         is incompatible with this list
         * (<a href="Collection.html#optional-restrictions">optional</a>)
         * @throws NullPointerException if the specified element is null and this
         *         list does not permit null elements
         * (<a href="Collection.html#optional-restrictions">optional</a>)
         * @throws UnsupportedOperationException if the <tt>remove</tt> operation
         *         is not supported by this list
         */
        boolean remove(Object o);
        
        // Bulk Modification Operations
        
        /**
         * Removes all of the elements from this list (optional operation).
         * The list will be empty after this call returns.
         */
        void clear();
        
        // Postional Access Operations
        
        /**
         * Returns the element at the specified postion in this list.
         */
        E get(int index);
        
        /**
         * Replaces the element at the specified postion in this list with
         * the specified element (optional operation);
         */
        void set(int index,E element);
        
         /**
         * Inserts the specified element at the specified position in this list
         * (optional operation).
         * Shifts the elements currently at that postion
         * (if any) and any subsequent elements to the right (adds one to their indices).
         */
        void add(int index,E element);
        
         /**
         * Removes the element at the specified postion in this list (optional
         * operation).
         * Shifts any subsequent elements to the left (subtracts one indices from their
         * indices).
         * Returns the element that was removed from the list.
         */
        E remove(int index);
        
         /**
         * Returns the index of the first occurrence of the specified element
         * in this list, or -1 if this list does not contain the element.
         * More formally, returns the lowest index <tt>i</tt> such that
         * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
         * or -1 if there is no such index.
         */
        int indexOf(Object o);
        
         /**
         * Returns a list iterator over the elements in this list (in proper
         * sequence), starting at the specified position in the list.
         */
        List<E> subList(int formIndex,int toIndex);
}
