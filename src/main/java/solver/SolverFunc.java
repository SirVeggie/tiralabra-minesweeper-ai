package solver;

public class SolverFunc {
    private final int T_CLICK = 1;
    private final int T_FLAG = 3;
    
    private int N_MINES;
    private int N_COLS;
    private int N_ROWS;
    
    public SolverFunc(int mines, int cols, int rows) {
        this.N_MINES = mines;
        this.N_COLS = cols;
        this.N_ROWS = rows;
    }
    
    /**
     * countAdjacentTiles(int pos, int type, int[] field)
     * 
     * Counts the adjacent tiles of a given position of the specific type.
     * 
     * @param pos Target point on the field.
     * @param type Which tile to search for.
     * @param field The field to search tiles from.
     * @return int Returns the amount found.
     */
    public int countAdjacentTiles(int pos, int type, int[] field) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int pointer = relativePos(i, pos);
            
            if (pointer < 0) {
                if (type == 0) count++;
            } else if (field[pointer] == type) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * flagAdjacentTiles(int pos, int[] field, int[] output)
     * 
     * Marks all adjacent covered tiles for flagging.
     * 
     * @param pos Target point on the field.
     * @param field The field to search tiles from.
     * @param output Array, where the marks are placed.
     * @return output Returns the modified output parameter.
     */
    public int[] flagAdjacentTiles(int pos, int[] field, int[] output) {
        for (int i = 0; i < 8; i++) {
            int pointer = relativePos(i, pos);
            
            if (pointer < 0) {
                continue;
            } else if (field[pointer] == 10) {
                output[pointer] = T_FLAG;
            }
        }
        
        return output;
    }
    
    /**
     * clickAdjacentTiles(int pos, int[] field, int[] output)
     * 
     * Marks all adjacent covered tiles for clicking.
     * 
     * @param pos Target point on the field.
     * @param field The field to search tiles from.
     * @param output Array, where the marks are placed.
     * @return output Returns the modified output parameter.
     */
    public int[] clickAdjacentTiles(int pos, int[] field, int[] output) {
        for (int i = 0; i < 8; i++) {
            int pointer = relativePos(i, pos);
            
            if (pointer < 0) {
                continue;
            } else if (field[pointer] == 10) {
                output[pointer] = T_CLICK;
            }
        }
        
        return output;
    }
    
    /**
     * relativePos(int pos, int truepos)
     * 
     * Gets the absolute position of the wanted relative position.
     * [0][1][2]
     * [3][x][4]
     * [5][6][7]
     * 
     * @param pos Relative position.
     * @param truepos Target point on the field.
     * @return output Returns the modified output parameter.
     */
    public int relativePos(int pos, int truepos) {
        switch (pos) {
            case 0:
                return truepos - N_COLS - 1;
            case 1:
                return truepos - N_COLS;
            case 2:
                return truepos - N_COLS + 1;
            case 3:
                return truepos - 1;
            case 4:
                return truepos + 1;
            case 5:
                return truepos + N_COLS - 1;
            case 6:
                return truepos + N_COLS;
            case 7:
                return truepos + N_COLS + 1;
        }
        
        return -1;
    }
    
    // Will probably delete this
    public int[] getAdjacentTiles(int[] field, int pos) {
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