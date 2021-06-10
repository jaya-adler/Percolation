import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
     public static void main(String[] args){
     String input_strings;
     RandomizedQueue<String> R= new RandomizedQueue<>();
     while (!StdIn.isEmpty()){
          input_strings=StdIn.readString();
          R.enqueue(input_strings);
     }
     for (int i=0;i<Integer.parseInt(args[0]);i++){
          StdOut.println(R.dequeue());
     }
     }
}
