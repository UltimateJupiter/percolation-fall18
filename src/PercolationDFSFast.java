
public class PercolationDFSFast extends PercolationDFS{
    
    int[] dx = {0,0,-1,1};
    int[] dy = {1,-1,0,0};
    
    public PercolationDFSFast(int n) {
        super(n);
    }
    
    @Override
    public void updateOnOpen(int row, int col) {
        
        if (! inBounds(row, col)) return;
        if (row == 0) {
            dfs(0, col);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (inBounds(row + dx[i], col + dy[i])) {
                if (myGrid[row + dx[i]][col + dy[i]] == FULL) {
                    dfs(row, col);
                    // System.out.println("YES");
                    return;
                }
            }
        }
        // System.out.println("NO");
        return;
    }
}
