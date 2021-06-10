import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.LinkedQueue;
//import edu.princeton.cs.algs4.LinkedStack;
import java.lang.Iterable;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
    private Node first,last,sec_last;
    private int sze;

    private class Node{
        private Item i;
        private Node next;
    }
    public Deque(){
        first=null;
        last=null;
        sze=0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first==null;
    }

    // return the number of items on the deque
    public int size(){
        return sze;
    }

    // add the item to the front
    public void addFirst(Item item){
        Node create = new Node();
        create.i=item;
        create.next=null;
        if (isEmpty()){
            last=create;
        }
        else {
            create.next=first;
        }
        first=create;
        sze=sze+1;
    }

    // add the item to the back
    public void addLast(Item item){
        Node create = new Node();
        create.i=item;
        create.next=null;
        if(isEmpty()){
            first=create;
            sec_last=create;
        }
        else {
           last.next=create;
           sec_last=last;
        }
        last=create;
        sze=sze+1;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        Item temp;
        temp=first.i;
        first=first.next;
        sze-=1;
        return temp;
    }

    // remove and return the item from the back
    public Item removeLast(){
        Item temp;
        temp=last.i;
        last=sec_last;
        last.next=null;
        sze-=1;
        return temp;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
     return new Linked_deque();
    }
    private class Linked_deque implements Iterator<Item>{
        private Node curr=first;

        @Override
        public boolean hasNext() {
            return curr!=null;
        }

        @Override
        public Item next() {
            Item i = curr.i;
            curr=curr.next;
            return i;

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){
    Deque<Integer> s = new Deque<>();
    s.addFirst(5);
    s.addLast(34);
    s.addLast(234);
    s.addFirst(65);
    s.addFirst(99);
    s.addFirst(500);
    s.removeFirst();
        for (int i: s) {
            StdOut.println(i);
        }

    }
}
