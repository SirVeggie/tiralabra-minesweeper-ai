package solver;

import structures.*;

// General functions used across the application.
public class SolverFunc {
    private final int ACTION_CLICK = 1;
    private final int ACTION_FLAG = 3;
    
    private int COLS;
    private int ROWS;
    private int FIELD_SIZE;
    
    public SolverFunc() {}
    
    public SolverFunc(int cols, int rows) {
        this.COLS = cols;
        this.ROWS = rows;
        this.FIELD_SIZE = cols*rows;
    }
    
    /**
     * Counts the adjacent tiles of a given position of the specific type.
     * 
     * @param pos Target point on the field.
     * @param type Which tile type to search for.
     * @param field The field to search tiles from.
     * @return The amount of tiles found.
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
     * Marks all adjacent covered tiles for flagging.
     * 
     * @param pos Target point on the field
     * @param field The field to search tiles from
     * @return Instruction set marked with positions where to flag
     */
    public int[] flagAdjacentTiles(int pos, int[] field) {
        return markAdjacentTiles(pos, field, ACTION_FLAG);
    }
    
    /**
     * Marks all adjacent covered tiles for clicking.
     * 
     * @param pos Target point on the field
     * @param field The field to search tiles from
     * @return Instruction set marked with positions where to click
     */
    public int[] clickAdjacentTiles(int pos, int[] field) {
        return markAdjacentTiles(pos, field, ACTION_CLICK);
    }
    
    /**
     * Mark adjacent tiles with a number in the instruction set.
     * 
     * @param pos Target position on the field
     * @param field Current state of the minefield
     * @param mark Number to mark with
     * @return Instruction set marked with the given number
     */
    public int[] markAdjacentTiles(int pos, int[] field, int mark) {
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
     * Finds all number tiles that have unrevealed tiles as neighbours.
     * Saves tile coordinates to an ArrayList.
     * 
     * @param field Current state of the minefield
     * @return An ArrayList of found tiles' coordinates.
     */
    public ArrayList<Integer> findUnsolvedNumbers(int[] field) {
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < field.length; i++) {
            if (field[i] <= 8 && field[i] >= 1 && countAdjacentTiles(i, 10, field) >= 1) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    /**
     * Gets the absolute position of the wanted relative position.
     * [0][1][2]
     * [3][x][4]
     * [5][6][7]
     * 
     * @param pos Relative position to the target point
     * @param truepos Target point on the field
     * @return Absolute position on the minefield.
     *         -1 if position is outside of the field.
     */
    public int relativePos(int pos, int truepos) {
        int row = getTileRow(truepos);
        int result = -1;
        
        switch (pos) {
            case 0:
                result = truepos - COLS - 1;
                if (getTileRow(result) != row - 1 || result < 0) result = -1;
                break;
            case 1:
                result = truepos - COLS;
                if (result < 0) result = -1;
                break;
            case 2:
                result = truepos - COLS + 1;
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
                result = truepos + COLS - 1;
                if (getTileRow(result) != row + 1 || result >= FIELD_SIZE) result = -1;
                break;
            case 6:
                result = truepos + COLS;
                if (result >= FIELD_SIZE) result = -1;
                break;
            case 7:
                result = truepos + COLS + 1;
                if (getTileRow(result) != row + 1 || result >= FIELD_SIZE) result = -1;
                break;
        }
        
        return result;
    }
    
    /**
     * Gives the current row of this position.
     * 
     * @param pos Position on the minefield
     * @return Current row of the given position
     */
    public int getTileRow(int pos) {
        return pos / COLS;
    }
    
    /**
     * Adds the values of different arrays together,
     * if their length is the same.
     * 
     * @param arrays Any number of int[] type arrays.
     * @return Returns the combined array.
     */
    public int[] combineArrays(int[]... arrays) {
        int length = arrays[0].length;
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i].length != length) throw new IllegalArgumentException("Arrays are not of equal length.");
        }
        
        int[] result = new int[length];
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < arrays.length; j++) {
                result[i] += arrays[j][i];
            }
        }
        
        return result;
    }
    
    /**
     * Replaces the base array's value with the writer array's value
     * if writer array's value isn't 0.
     * 
     * @param base
     * @param writer
     * @return Overwritten array
     */
    public int[] overwriteArray(int[] base, int[] writer) {
        if (writer.length > base.length) throw new IllegalArgumentException("Writer array is bigger than base.");
        
        int[] result = base.clone();
        
        for (int i = 0; i < writer.length; i++) {
            if (writer[i] != 0) {
                result[i] = writer[i];
            }
        }
        
        return result;
    }
    
    /**
     * Subtract values of array2 from array1.
     * 
     * @param array1
     * @param array2
     * @return Array1 - Array2
     */
    public double[] subtractArray(double[] array1, double[] array2) {
        if (array1.length != array2.length) throw new IllegalArgumentException("Arrays are not of equal length.");
        
        double[] result = new double[array1.length];
        
        for (int i = 0; i < array1.length; i++) {
            result[i] = array1[i] - array2[i];
        }
        
        return result;
    }
}