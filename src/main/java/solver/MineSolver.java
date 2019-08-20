package solver;

public class MineSolver {
    private final int T_COVERED = 10;
    private final int T_FLAG = 9;
    private final int T_EMPTY = 0;
    
    private int N_MINES;
    private int N_COLS;
    private int N_ROWS;
    private SolverFunc sf;
    
    public MineSolver(int mines, int cols, int rows) {
        this.N_MINES = mines;
        this.N_COLS = cols;
        this.N_ROWS = rows;
        
        this.sf = new SolverFunc(mines, cols, rows);
    }
    
    public int[] solve(int[] field) {
        int[] result = solveLocal(field);
        
        if (result == null) {
            result = solveComplex(field);
        }
        
        return result;
    }
    
    public int[] chord(int[] field, int pos) {
        int[] result = new int[field.length];
        
        int count = sf.countAdjacentTiles(pos, 9, field);
                
        if (field[pos] == count) {
            result = sf.clickAdjacentTiles(pos, field, result);
            return result;
        }
        
        return null;
    }
    
    private int[] solveLocal(int[] field) {
        int[] result = new int[field.length];
        boolean progress = false;
        
        for (int i = 0; i < field.length; i++) {
            if (field[i] == 0 || field[i] >= 9) {
                continue;
            } else {
                // Flag if possible
                int count = sf.countAdjacentTiles(i, T_COVERED, field);
                count += sf.countAdjacentTiles(i, T_FLAG, field);
                
                if (field[i] == count) {
                    progress = true;
                    result = sf.flagAdjacentTiles(i, field, result);
                }
                
                // Click if possible
                count = sf.countAdjacentTiles(i, 9, field);
                
                if (field[i] == count) {
                    progress = true;
                    result = sf.clickAdjacentTiles(i, field, result);
                }
            }
        }
        
        if (progress) {
            return result;            
        } else {
            return null;
        }
    }
    
    private int[] solveComplex(int[] field) {
        return null;
    }
}