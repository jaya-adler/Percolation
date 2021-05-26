import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int   len,lensqr;
    private final WeightedQuickUnionUF grid;
    private final int[] opened;
    private int topvirtual,bottomvirtual;
    public Percolation(int n){
        if (n <= 0) {
            throw new IllegalArgumentException("Given n <= 0");
        }
        len=n;
        lensqr=n*n;
        opened = new int[lensqr];
        topvirtual=StdRandom.uniform(0,len);
        bottomvirtual=StdRandom.uniform(lensqr-len,lensqr);
        for(int i=0;i<lensqr;i++){
            opened[i]=0;
        }
         grid =new WeightedQuickUnionUF(lensqr);
    }
     private void join(int row,int col,int i){
        int up,down,left,right;
        if(row-1 >=0){
            up=(row-1)*len+col;
            if (opened[up]==1) {
                grid.union(i,up);
            }
        }else {
            topvirtual=i;
        }
        if (row+1 < len){
            down=(row+1)*len+col;
            if (opened[down]==1) {
                grid.union(down,i);
            }
        }
        else {
            bottomvirtual=i;
        }
         if (col+1<len){
            right=row*len+col+1;
            if (opened[right]==1) {
                grid.union(i,right);
            }
        }
         if (col-1 >=0){
            left=row*len+col-1;
            if (opened[left]==1) {
                grid.union(i,left);
            }
         }

     }
    // opens the site (row, col) if it is not open already
    private void validate(int p) {
        if (p < 0 || p > len) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (len-1));
        }
    }
    public void open(int row, int col){
        int trow=row-1;
        int tcol=col-1;
        validate(trow);
        validate(tcol);
        int i=trow*len+tcol;
     if(isOpen(row, col)){
         if(!isFull(row, col)){
         join(trow,tcol,i);}
         row = StdRandom.uniform(1,len+1);
         col = StdRandom.uniform(1,len+1);
         open(row,col);
     }
     else {
        opened[i]=1;
        join(trow,tcol,i);
     }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        int trow=row-1;
        int tcol=col-1;
        validate(trow);
        validate(tcol);
        int i= trow*len+tcol;
        return opened[i]==1;
    }

    // is the site (row, col) full?
     public boolean isFull(int row, int col){
         int trow=row-1;
         int tcol=col-1;
         validate(trow);
         validate(tcol);
         int i=trow*len+tcol;
         if(opened[i]==1 ){
             if(i==topvirtual || i==bottomvirtual){ return  true;
             }
             else if (grid.find(i)!=i){
                 return true;
             }
             else{
                 if (i+1<lensqr){if(grid.find(i+1)==i) return true;}
                 if(i-1>=0){ if (grid.find(i-1)==i)return true;}
                 if(i+len<lensqr){if (grid.find(i+len)==i) return true;}
                 if (i-len>=0){ return grid.find(i - len) == i; }
             }
         }
         return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        int tem=0;
       for(int j=0;j<lensqr;j++){
           if(opened[j]==1) tem+=1;
       }
      return tem;
    }

    // does the system percolate?
    public boolean percolates(){

        return grid.find(topvirtual)==grid.find(bottomvirtual);
    }
    static public void main(String[] args){
        int n=Integer.parseInt(args[0]);
        int row, col;
        double res ;
        Percolation p = new Percolation(n);
            while (!p.percolates()) {
                row = StdRandom.uniform(1,n+1);
                col = StdRandom.uniform(1,n+1);
                p.open(row, col);
                StdOut.print(p.opened[0]+" ");
                for(int gr=1;gr< p.lensqr;gr++){
                    if(gr%n==0){
                        StdOut.print("\n"+p.opened[gr]+" ");
                    }
                    else {StdOut.print(p.opened[gr]+" ");}
                }
                StdOut.println("\n-----------");
            }
        StdOut.print(p.opened[0]+" ");
        for(int gr=1;gr< p.lensqr;gr++){
            if(gr%n==0){
                StdOut.print("\n"+p.opened[gr]+" ");
            }
            else {StdOut.print(p.opened[gr]+" ");}
        }
        StdOut.println("\n");
            res= (double) p.numberOfOpenSites() / (n * n);
        StdOut.println(res);
    }

}
