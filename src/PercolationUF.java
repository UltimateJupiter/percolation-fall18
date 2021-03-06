
public class PercolationUF implements IPercolate{
    
    boolean[][] myGrid;
    int myOpenCount;
    private final int VTOP = 0, VBOTTOM = 1;
    IUnionFind myFinder;
    
    // For looping over connected cells
    int[] dx = {0,0,-1,1};
    int[] dy = {1,-1,0,0};
    
    public boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }
    
    public PercolationUF(int size, IUnionFind finder) {
        //Initialize
        myGrid = new boolean[size][size];
        myFinder = finder;
        myFinder.initialize(size * size + 2);
        myOpenCount = 0;
    }
    
    public int getInd(int r, int c) {
        return r * myGrid.length + c + 2;
    }
    @Override
    public void open(int row, int col) {
        
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        
        // If it is already opened, skip this action
        if (myGrid[row][col] == true) return;
        
        // Open the cell in myGrid
        myGrid[row][col] = true;
        myOpenCount += 1;
        
        // If it is at the top row, connect to VTOP
        if (row == 0) myFinder.union(VTOP, getInd(row, col));
        
        // If it is at the bottom row, connect to VTBOTTOM
        if (row == myGrid.length - 1) myFinder.union(VBOTTOM, getInd(row, col));
        
        // check the connected cells, and connect the new cell to them if they are opened
        for (int i = 0; i < 4; i++) {
            int rn = row + dy[i], cn = col + dx[i];
            if (inBounds(rn, cn) && myGrid[rn][cn] == true) {
                myFinder.union(getInd(row, col), getInd(rn, cn));
            }
        }
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myFinder.connected(getInd(row, col), VTOP);
    }

    @Override
    public boolean percolates() {
        // If VBOTTOM is connected to VTOP, it percolates.
        return myFinder.connected(VBOTTOM, VTOP);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

}
