package solver;

public class SolverFunc {
    private final int A_CLICK = 1;
    private final int A_FLAG = 3;
    
    private int N_MINES;
    private int N_COLS;
    private int N_ROWS;
    private int FIELD_SIZE;
    
    public SolverFunc(int mines, int cols, int rows) {
        this.N_MINES = mines;
        this.N_COLS = cols;
        this.N_ROWS = rows;
        this.FIELD_SIZE = cols*rows;
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
                if (type == -1) count++;
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
     * @return output Returns the modified output parameter.
     */
    public int[] flagAdjacentTiles(int pos, int[] field) {
        return markAdjacentTiles(pos, field, 3);
    }
    
    /**
     * clickAdjacentTiles(int pos, int[] field, int[] output)
     * 
     * Marks all adjacent covered tiles for clicking.
     * 
     * @param pos Target point on the field.
     * @param field The field to search tiles from.
     * @return output Returns the modified output parameter.
     */
    public int[] clickAdjacentTiles(int pos, int[] field) {
        return markAdjacentTiles(pos, field, 1);
    }
    
    private int[] markAdjacentTiles(int pos, int[] field, int mark) {
        int[] output = new int[field.length];
        for (int i = 0; i < 8; i++) {
            int pointer = relativePos(i, pos);
            
            if (pointer < 0) {
                continue;
            } else if (field[pointer] == 10) {
                output[pointer] = mark;
            }
        }
        
        return output;
    }
    
    /**
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
        int row = getTileRow(truepos);
        int result = -1;
        
        switch (pos) {
            case 0:
                result = truepos - N_COLS - 1;
                if (getTileRow(result) != row - 1 || result < 0) result = -1;
                break;
            case 1:
                result = truepos - N_COLS;
                if (result < 0) result = -1;
                break;
            case 2:
                result = truepos - N_COLS + 1;
                if (getTileRow(result) != row - 1 || result < 0) result = -1;
                break;
            case 3:
                result = truepos - 1;
                if (getTileRow(result) != row || result < 0) result = -1;
                break;
            case 4:
                result = truepos + 1;
                if (getTileRow(result) != row || result >= FIELD_SIZE) result = -1;
                break;
            case 5:
                result = truepos + N_COLS - 1;
                if (getTileRow(result) != row + 1 || result >= FIELD_SIZE) result = -1;
                break;
            case 6:
                result = truepos + N_COLS;
                if (result >= FIELD_SIZE) result = -1;
                break;
            case 7:
                result = truepos + N_COLS + 1;
                if (getTileRow(result) != row + 1 || result >= FIELD_SIZE) result = -1;
                break;
        }
        
        return result;
    }
    
    public int getTileRow(int pos) {
        return pos / N_COLS;
    }
    
    /**
     * Adds the values of different arrays together, if same length.
     * 
     * @param arrays Any number of int[] type arrays.
     * @return Returns the combined array.
     */
    public int[] combineArrays(int[]... arrays) {
        int length = arrays[0].length;
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i].length != length) return null;
        }
        
        int[] result = new int[length];
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < arrays.length; j++) {
                result[i] += arrays[j][i];
            }
        }
        
        return result;
    }
}