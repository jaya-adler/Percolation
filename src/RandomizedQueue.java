import java.util.Iterator;
import java.util.function.Consumer;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


 class Node<Item>{
    public Item i;
    public Node<Item> next;
    public int index;
}
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node<Item> first,last;
     private Node<Item>[] data_ref;
    private int arr_index;
    //private int sze;
        RandomizedQueue(){
            first=null;
            last=null;
            arr_index=0;
            data_ref=new Node[3];
        }


    // is the randomized queue empty?
    public boolean isEmpty(){
        return first==null;
    }

    // return the number of items on the randomized queue
    public int size(){
        return arr_index;
    }

    // add the item
    private void re_size(int len){
            Node<Item>[] temp = new Node[len*2];

            for(int i=0;i<len;i++){
                temp[i]=data_ref[i];
            }
            data_ref=temp;
    }
    public void enqueue(Item item){
       Node<Item> new_one=new Node<>();
       new_one.i=item;
       new_one.next=null;
       new_one.index=arr_index++;
       if (arr_index>data_ref.length) re_size(data_ref.length);
       data_ref[arr_index-1]=new_one;
       if (isEmpty()){
           first=new_one;
           last=new_one;
       }
      last.next=new_one;
       last=new_one;
    }

    // remove and return a random item
    public Item dequeue(){
            int rndm = StdRandom.uniform(arr_index);
            Item i = data_ref[rndm].i;
            if (rndm==0){ first=data_ref[rndm].next;}
            else if (rndm==arr_index-1){data_ref[rndm-1].next=null;}
            else {data_ref[rndm-1].next=data_ref[rndm].next;}
            arr_index-=1;
            return i;

    }

    // return a random item (but do not remove it)
    public Item sample(){
        Node<Item> smpl=first;
        int rndm = StdRandom.uniform(arr_index);
        while (smpl.index!=rndm){
            smpl=smpl.next;
        }
        return smpl.i;

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new random_queue();
    }

    public class random_queue implements Iterator<Item>{
        private Node<Item> temp=first;

        @Override
        public boolean hasNext() {
            return temp !=null;
        }

        @Override
        public Item next() {
            Item val= temp.i;
            temp=temp.next;
            return val;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    // unit testing (required)
    public static void main(String[] args){
            RandomizedQueue<String> R=new RandomizedQueue<>();
            R.enqueue("gh");
            R.enqueue("bn");
            R.enqueue("sdf");
            R.enqueue("wer");
        for (String i: R) {
            StdOut.println(i+"val");
        }
        StdOut.println(R.dequeue());
        for (String i :R){
            StdOut.println(i+ "val2");
        }
        }
}



