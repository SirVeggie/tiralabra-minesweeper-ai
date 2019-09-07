package solver;

import structures.*;

import java.util.HashMap;

import math.*;

public class MineSolver {
    private final int T_COVERED = 10;
    private final int T_FLAG = 9;
    private final int T_EMPTY = 0;
    
    private SolverFunc sf;
    
    public MineSolver(int cols, int rows) {
        this.sf = new SolverFunc(cols, rows);
    }
    
    /**
     * Looks at the given minefield and outputs the correct instruction set on
     * how to continue this specific game state safely.
     * 
     * @param field Current state of the minefield
     * @return Instruction set as an int[] Array. 1 means "tile is safe"
     *         and 3 means "tile has a mine". 0 means "unknown".
     */
    public int[] solve(int[] field) {
        // First try the simple and fast method.
        int[] result = solveLocal(field);
        
        // If local solving failed, try the slower but more powerful method.
        if (result == null) {
            result = solveComplex(field);
        }
        
        return result;
    }
    
    /**
     * Reveals all surrounding tiles, if all surrounding mines have been flagged.
     * 
     * @param field Current state of the minefield
     * @param pos Tile location
     * @return
     */
    public int[] chord(int[] field, int pos) {
        int count = sf.countAdjacentTiles(pos, 9, field);
                
        if (field[pos] == count) {
            int[] result = sf.clickAdjacentTiles(pos, field);
            return result;
        }
        
        return null;
    }
    
    /**
     * Goes through the entire field's revealed numbers,
     * and checks if it's safe to click/flag surrounding tiles
     * based on the single tile's information.
     * 
     * @param field Current state of the minefield
     * @return Instruction set for the given field
     */
    private int[] solveLocal(int[] field) {
        int[] result = new int[field.length];
        boolean progress = false;
        
        for (int i = 0; i < field.length; i++) {
            
            // Current tile is a number
            if (field[i] <= 8 && field[i] >= 1) {
                int flags = sf.countAdjacentTiles(i, T_FLAG, field);
                int covered = sf.countAdjacentTiles(i, T_COVERED, field);
                
                // If there are 0 covered tiles, then no action is necessary.
                if (covered == 0) {
                    continue;
                }
                
                // Flag if surrounding unrevealed tiles + flags = this number
                if (field[i] == flags + covered) {
                    progress = true;
                    result = sf.overwriteArray(result, sf.flagAdjacentTiles(i, field));
                }
                
                // Click if surrounding flags = this number
                if (field[i] == flags) {
                    progress = true;
                    result = sf.overwriteArray(result, sf.clickAdjacentTiles(i, field));
                }
            }
        }
        
        if (progress) {
            return result;            
        } else {
            return null;
        }
    }
    
    /**
     * Uses constraint logic and matrix calculations to
     * find safe moves to perform in more difficult situations.
     * 
     * @param field Current state of the minefield
     * @return Instruction set for the given field
     */
    private int[] solveComplex(int[] field) {
        ArrayList<Integer> unsolvedNumbers = sf.findUnsolvedNumbers(field);
        
        HashMap<Integer, Integer> indexToField = new HashMap<>();
        HashMap<Integer, Integer> fieldToIndex = new HashMap<>();
        
        // Find all uncovered tiles next to at least one number
        for (int i = 0; i < unsolvedNumbers.size(); i++) {
            int truepos = unsolvedNumbers.get(i);
            
            for (int k = 0; k < 8; k++) {
                int pointer = sf.relativePos(k, truepos);
                
                // Pointer < 0 means the position is outside of the field
                if (pointer < 0) {
                    continue;
                } else if (field[pointer] == 10) {
                    // Map all unique uncovered tiles to matrix row positions
                    if (!fieldToIndex.containsKey(pointer)) {
                        int count = indexToField.size();
                        indexToField.put(count, pointer);
                        fieldToIndex.put(pointer, count);
                    }
                }
            }
        }
        
        // Create matrix for solving
        double[][] m = new double[unsolvedNumbers.size()][indexToField.size()];
        double[] s = new double[unsolvedNumbers.size()];
        
        for (int i = 0; i < unsolvedNumbers.size(); i++) {
            int truepos = unsolvedNumbers.get(i);
            
            // Save the number of adjacent uncovered tiles, flagged tiles are ignored
            s[i] = field[truepos] - sf.countAdjacentTiles(truepos, T_FLAG, field);
            
            for (int k = 0; k < 8; k++) {
                int pointer = sf.relativePos(k, truepos);
                
                if (pointer < 0) {
                    continue;
                } else if (field[pointer] == 10) {
                    m[i][fieldToIndex.get(pointer)] = 1;
                }
            }
        }
        
        if (m.length == 0 || s.length == 0) return null;
        
        // Solve matrix
        MatrixSolver mSolver = new MatrixSolver(m, s);
        mSolver.gaussianElimination();
        
        // Interpret and return the correct moves from the solved matrix
        return mSolver.interpretMatrix(indexToField, field.length);
    }
}