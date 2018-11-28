import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class PercolationBFS extends PercolationDFSFast {

    public PercolationBFS(int n) {
        super(n);
    }
    
    @Override
    public void dfs(int row, int col) {
        
        if (! inBounds(row,col)) return;
        if (isFull(row, col) || !isOpen(row, col)) return;
        
        Queue<int[]> q = new LinkedList<>();
        myGrid[row][col] = FULL;
        q.add(new int[] {row, col});
        int[] crd;
        
        while (q.size() > 0) {
            
            crd = q.remove();
            
            for (int i = 0; i < 4; i++) {
                int rn = crd[0] + dy[i], cn = crd[1] + dx[i];
                if (inBounds(rn, cn) && myGrid[rn][cn] == OPEN) {
                    q.add(new int[] {rn, cn});
                    myGrid[rn][cn] = FULL;
                    // System.out.println(Arrays.toString(new int[] {rn, cn}));
                    // System.out.println(q.size());
                }
            }
        }
    }
    
}
