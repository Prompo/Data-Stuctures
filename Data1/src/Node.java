public class Node {
    public String item;
    public Node next;
    public Node prev;

    public Node(String item){
        this.item=item;
        next=null;
        prev=null;
    }
}
