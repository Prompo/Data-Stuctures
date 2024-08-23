import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringDoubleEndedQueuelmpl implements StringDoubleEndedQueue{
    private Node header;
    private Node lastNode;
    private int size;


    public StringDoubleEndedQueuelmpl(){
        header=new Node(null);
        lastNode=header;
    }
    @Override
    public boolean isEmpty(){
        return header==null;
    }

    @Override
    public void addFirst(String item){
        Node n=new Node(item);
        if (isEmpty()){
            header.next=n;
            lastNode=n;
            size++;
        }else{
            n.next=header.next;
            n.prev=null;
            header.next=n;
            n.next.prev=n;
            size++;
        }
    }

    @Override
    public void addLast(String item){
        Node n=new Node(item);
        if (isEmpty()){
            header.next=n;
            lastNode=n;
            size++;
        }
        else{
            lastNode.next=n;
            n.prev=lastNode;
            lastNode=n;
            size++;
        }
    }

    @Override
    public String removeFirst() throws NoSuchElementException {
        if (!isEmpty()){
            String goback="";
            Node temp=header.next;
            header.next.prev=header;
            header.next=header.next.next;
            size--;
            goback+=temp.item;
            return goback;
        }
        else
            throw new NoSuchElementException();
    }

    @Override
    public String removeLast() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        else if (size==1){
            Node temp=header;
            String goback="";
            header.next=null;
            lastNode=header;
            size--;
            goback+=temp.item;
            return goback;
        }
        else{
            Node temp=lastNode;
            String goback="";
            lastNode=lastNode.prev;
            lastNode.next=null;
            size--;
            goback+=temp.item;
            return goback;
        }
    }

    @Override
    public String getFirst() throws NoSuchElementException{
        if (isEmpty())
            throw new NoSuchElementException();
        else{
            Node temp=header.next;
            String goback="";
            goback+=temp.item;
            return goback;
        }
    }

    @Override
    public String getLast() throws NoSuchElementException{
        if (isEmpty())
            throw new NoSuchElementException();
        else{
            Node temp=lastNode;
            String goback="";
            goback+=temp.item;
            return goback;
        }
    }

    @Override
    public void printQueue(PrintStream stream){
        Node n=header.next;
        String queue="";
        while (n!=null){
            queue= queue + n.item+" ";
            n=n.next;
        }
        System.out.println(queue);
    }

   @Override
    public int size(){
        return size;
   }
}
