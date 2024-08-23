import java.io.PrintStream;
import java.util.NoSuchElementException;

class SingleLinkedList<T> implements SingleLinkedListInterface<T>
{     
    private class Node<F>
    {
        F item;
        Node<F> next = null;    
        Node(F item)
        {
            this.item = item;
        }
    }
    Node<T> tail = null;
    int cells; 
    
    //FIRST ITEM FUNCTIONS
    @Override
    public void add(T item)
    {
        
        Node<T> tempNode = new Node<T>(item);

        if(isEmpty())
        {
            tail = tempNode;
        }
        else
        {      
            tempNode.next = tail;
            tail = tempNode;
        }
        cells++;
    }
    @Override
    public T pop() throws NoSuchElementException
    {
        if(isEmpty())
        {
            throw new NoSuchElementException();
        }
        T s = tail.item;
        if(size() == 1)
        {
            tail = null;
        }
        else
        {    
            tail = tail.next;
        }
        cells--;
        return s;
    }
    @Override
    public T getFirstItem() throws NoSuchElementException
    {
        if(isEmpty())
        {
            throw new NoSuchElementException();
        }
        return tail.item;
    }

    //MISC
    @Override
    public boolean isEmpty()    
    {
        return tail == null;
    }
    @Override
    public void printList(PrintStream stream)
    {
        if(isEmpty())
        {
            throw new NoSuchElementException();
        }
        for(Node<T> n = tail; n != null; n = n.next)
        {
            stream.print(n.item + "\n");
        }
    }
    @Override
    public int size()
    {
        return cells;
    }
}