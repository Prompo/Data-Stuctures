import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Defines the methods for a Double-ended Queue that handles items
 */

public interface SingleLinkedListInterface<T> {
	
	/**
	 * insert an item at the front of the queue
	 */
	public void add(T item);
	
	/**
	 * remove and return the item at the front of the queue
	 * @return item from the front of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public T pop() throws NoSuchElementException;
	
	
	/**
	 * return without removing the item at the front of the queue
	 * @return item from the front of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public T getFirstItem() throws NoSuchElementException;
	
	/**
	 * print the items of the queue, starting from the front, 
	 * to the print stream given as argument. For example, to 
	 * print the elements to the
	 * standard output, pass System.out as parameter. E.g.,
	 * printQueue(System.out);
	 */
	public void printList(PrintStream stream);
	
	/**
	 * return the size of the queue, 0 if empty
	 * @return number of elements in the queue
	 */
	public int size();
	
	/**
	 * @return true if the queue is empty
	 */
	public boolean isEmpty();
	//public void removefirst();
	//public void removeAt(T item);
}
