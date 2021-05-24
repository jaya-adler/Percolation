import edu.princeton.cs.algs4.*;

public class Percolation {
    private int  len,topvirtual,bottomvirtual,lensqr;
    WeightedQuickUnionUF grid;
    private int[] opened;
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
        opened[topvirtual]=1;
        opened[bottomvirtual]=1;
        grid =new WeightedQuickUnionUF(lensqr);
    }
     public void join(int row,int col,int i){
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
                grid.union(i,down);
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
    public void open(int row, int col){
        int i=row*len+col;
     if(isOpen(row, col)){
         row = StdRandom.uniform(len);
         col = StdRandom.uniform(len);
         open(row,col);
     }
     else{
        opened[i]=1;
        join(row,col,i);
     }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        int i= row*len+col;
        return opened[i]==1;
    }

    // is the site (row, col) full?
     /**public boolean isFull(int row, int col){
       return opened[row*len+col]==row*len+col;}**/

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

        return grid.connected(topvirtual,bottomvirtual);
    }

}
