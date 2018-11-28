import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class PercolationBFS extends PercolationDFSFast {

    public PercolationBFS(int n) {
        super(n);
    }
    
    @Override
    protected void dfs(int row, int col) {
        
        if (! inBounds(row,col)) return;
        if (isFull(row, col) || !isOpen(row, col)) return;
        
        // Initialize the Queue
        Queue<int[]> q = new LinkedList<>();
        myGrid[row][col] = FULL;
        
        // Add the current cell as the first element of the queue
        q.add(new int[] {row, col});
        int[] crd;
        
        while (q.size() > 0) {
            
            // dequeue the first cell
            crd = q.remove();
            
            for (int i = 0; i < 4; i++) {
                
                // check surrounding cells
                int rn = crd[0] + dy[i], cn = crd[1] + dx[i];
                if (inBounds(rn, cn) && myGrid[rn][cn] == OPEN) {
                    
                    // If there is a surrounding cell that is opened, make it full and add it to the queue.
                    q.add(new int[] {rn, cn});
                    myGrid[rn][cn] = FULL;
                }
            }
        }
    }
    
}
