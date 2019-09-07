package math;

import solver.SolverFunc;
import structures.*;

public class MatrixSolver {
    private static final double EPSILON = 1e-8;
    
    private double[][] matrix;
    private double[] solutions;
    private double[][] aMatrix;
    
    private BasicMath m;
    
    public MatrixSolver() {
        this(null, null);
    }
    
    public MatrixSolver(double[][] matrix, double[] solutions) {
        this.m = new BasicMath();
        this.matrix = matrix;
        this.solutions = solutions;
        if (matrix != null && solutions != null)
            if (solutions.length != matrix.length) throw new IllegalArgumentException("Matrix and solution set's dimensions do not match");
    }
    
    public double[][] getMatrix() {
        return matrix;
    }
    
    public double[] getSolutions() {
        return solutions;
    }
    
    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
    
    public void setSolutions(double[] solutions) {
        this.solutions = solutions;
    }
    
    public MatrixSolver clone() {
        return new MatrixSolver(matrix, solutions);
    }
    
    
    // Matrix manipulation functions
    
    /**
     * Swap two specified rows with each other.
     * 
     * @param row1
     * @param row2
     */
    public void swap(int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
        
        double temp2 = solutions[row1];
        solutions[row1] = solutions[row2];
        solutions[row2] = temp2;
    }
    
    /**
     * Scale the specified row by the given factor.
     * 
     * @param row Target row
     * @param factor Factor by which to scale
     */
    public void scale(int row, double factor) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] *= factor;
        }
        
        solutions[row] *= factor;
    }
    
    /**
     * Add a scaled row to the target row.
     * 
     * @param targetRow Index of target row.
     * @param scaledRow Index of scaled row.
     * @param factor Factor of scaling.
     */
    public void addScaled(int targetRow, int scaledRow, double factor) {
        for (int i = 0; i < matrix[targetRow].length; i++) {
            matrix[targetRow][i] += matrix[scaledRow][i] * factor;
        }
        
        solutions[targetRow] += solutions[scaledRow] * factor;
    }
    
    /**
     * Swap two specified rows with each other in the augmented matrix.
     * 
     * @param row1
     * @param row2
     */
    private void augSwap(int row1, int row2) {
        double[] temp = aMatrix[row1];
        aMatrix[row1] = aMatrix[row2];
        aMatrix[row2] = temp;
    }
    
    /**
     * Pivot matrix row in the augmented matrix.
     * 
     * @param p
     */
    private void pivot(int p) {
        for (int i = p+1; i < aMatrix.length; i++) {
            double alpha = aMatrix[i][p] / aMatrix[p][p];
            for (int j = p; j <= aMatrix[0].length - 1; j++) {
                aMatrix[i][j] -= alpha * aMatrix[p][j];
            }
        }
    }
    
    
    
    
    /**
     * Use Gaussian Elimination method to turn
     * the matrix into a reduced form.
     */
    public void gaussianElimination() {
        
        // Build augmented matrix
        aMatrix = new double[matrix.length][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++)
            for (int k = 0; k < matrix[0].length; k++)
                aMatrix[i][k] = matrix[i][k];
        for (int i = 0; i < matrix.length; i++)
            aMatrix[i][matrix[0].length] = solutions[i];

        
        // Solve augmented matrix
        for (int p = 0; p < m.min(aMatrix.length, aMatrix[0].length - 1); p++) {

            // Find pivot row using partial pivoting
            int max = p;
            for (int i = p + 1; i < aMatrix.length; i++) {
                if (m.abs(aMatrix[i][p]) > m.abs(aMatrix[max][p])) {
                    max = i;
                }
            }

            augSwap(p, max);

            // Check if singular
            if (m.abs(aMatrix[p][p]) <= EPSILON) {
                continue;
            }

            pivot(p);
        }
        
        // Convert back to normal
        int n = aMatrix[0].length - 1;
        for (int i = 0; i < aMatrix.length; i++) {
            for (int k = 0; k < n; k++) {
                matrix[i][k] = aMatrix[i][k];
            }
            solutions[i] = aMatrix[i][n];
        }
    }
    
    /**
     * Read the data saved in the matrix and extract
     * field positions for mines and safe spots.
     * 
     * @param indexToField Map of the matrix' index to a real position on the field
     * @param fieldsize The length of the minefield array.
     * @return Instruction array for the current minefield's state.
     */
    public int[] interpretMatrix(HashMap<Integer, Integer> indexToField, int fieldsize) {
        int[] answer = new int[fieldsize];
        SolverFunc sf = new SolverFunc();
        
        for (int i = 0; i < matrix.length; i++) {
            double[][] variationRows = new double[matrix.length][matrix[0].length];
            double[] variationSolutions = new double[solutions.length];
            
            variationRows[i] = matrix[i];
            variationSolutions[i] = solutions[i];
            
            // Include possible variations that the interpreter
            // does not consider by default.
            for (int k = 0; k < variationRows.length; k++) {
                if (k == i) continue;
                variationRows[k] = sf.subtractArray(matrix[i], matrix[k]);
                variationSolutions[k] = solutions[i] - solutions[k];
            }
            
            // Check all saved variations
            loopRows:
            for (int var = 0; var < variationRows.length; var++) {
                double[] row = variationRows[var];
                int solution = (int) variationSolutions[var];
                
                ArrayList<Integer> positives = new ArrayList<>();
                ArrayList<Integer> negatives = new ArrayList<>();
                
                // Check the row for non-zero values, and save their
                // real location on the field.
                for (int k = 0; k < row.length; k++) {
                    int value = (int) row[k];
                    
                    // If high values are found, row probably can't be interpreted.
                    // High values also cause errors in the following marking operation.
                    if (value > 1 || value < -1) continue loopRows;
                    
                    if (value > 0) {
                        positives.add(k);
                    } else if (value < 0) {
                        negatives.add(k);
                    }
                }
                
                // Mark tiles for clicking or flagging
                // depending on the matrix' information.
                if (positives.size() == solution && solution != 0) {
                    for (int z = 0; z < positives.size(); z++) {
                        answer[indexToField.get(positives.get(z))] = 3;
                    }
                    
                    for (int z = 0; z < negatives.size(); z++) {
                        answer[indexToField.get(negatives.get(z))] = 1;
                    }
                    
                } else if (negatives.size() == -solution && solution != 0) {
                    for (int z = 0; z < negatives.size(); z++) {
                        answer[indexToField.get(negatives.get(z))] = 3;
                    }
                    
                    for (int z = 0; z < positives.size(); z++) {
                        answer[indexToField.get(positives.get(z))] = 1;
                    }
                    
                } else if (negatives.size() == 0 && positives.size() != 0 && solution == 0) {
                    for (int z = 0; z < positives.size(); z++) {
                        answer[indexToField.get(positives.get(z))] = 1;
                    }
                    
                } else if (positives.size() == 0 && negatives.size() != 0 && solution == 0) {
                    for (int z = 0; z < negatives.size(); z++) {
                        answer[indexToField.get(negatives.get(z))] = 1;
                    }
                }
            }
        }
        
        return answer;
    }
}