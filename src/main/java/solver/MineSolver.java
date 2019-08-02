package solver;

public class MineSolver {
    private int N_MINES;
    private int N_COLS;
    private int N_ROWS;
    
    public MineSolver(int mines, int cols, int rows) {
        this.N_MINES = mines;
        this.N_COLS = cols;
        this.N_ROWS = rows;
    }
    
    public int[] solve(int[] field) {
        int[] result = solveLocal(field);
        
        if (result == null) {
            result = solveComplex(field);
        }
        
        return result;
    }
    
    private int[] solveLocal(int[] field) {
        int[] result = new int[field.length];
        boolean progress = false;
        
        for (int i = 0; i < field.length; i++) {
            int[] adjacent = getAdjacentTiles(field, i);
            if (field[i] == 0 || field[i] >= 9) {
                continue;
            } else {
                
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
    
    private int[] getAdjacentTiles(int[] field, int pos) {
        int[] tiles = new int[8];
        
        if (pos - N_COLS - 1 >= 0) {
            tiles[0] = field[pos - N_COLS - 1];
        }
        if (pos - N_COLS >= 0) {
            tiles[1] = field[pos - N_COLS];
        }
        if (pos - N_COLS + 1 >= 0) {
            tiles[2] = field[pos - N_COLS + 1];
        }
        if (pos - 1 >= 0) {
            tiles[3] = field[pos - 1];
        }
        if (pos + 1 <= field.length) {
            tiles[4] = field[pos + 1];
        }
        if (pos + N_COLS - 1 <= field.length) {
            tiles[5] = field[pos + N_COLS - 1];
        }
        if (pos + N_COLS <= field.length) {
            tiles[6] = field[pos + N_COLS];
        }
        if (pos + N_COLS + 1 <= field.length) {
            tiles[7] = field[pos + N_COLS + 1];
        }
        
        return tiles;
    }
}