package structures;

public class Matrix {
    private int rows;
    private int cols;
    private double[][] matrix;
    
    public Matrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];
    }
    
    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }
    
    public double[][] get() {
        return matrix;
    }
    
    public double[] get(int row) {
        return matrix[row];
    }
    
    public double get(int row, int col) {
        return matrix[row][col];
    }
    
    public void set(double[][] matrix) {
        this.matrix = matrix;
    }
    
    public Matrix clone() {
        return new Matrix(matrix);
    }
    
    // Matrix manipulation functions
    
    public void swap(int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }
    
    public void scale(int row, double scale) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] = matrix[row][i] * scale;
        }
    }
    
    public void addScaled(int targetRow, int scaledRow, double scale) {
        for (int i = 0; i < matrix[targetRow].length; i++) {
            matrix[targetRow][i] = matrix[scaledRow][i] * scale;
        }
    }
    
    
    
    public void GaussianElimination() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                
            }
        }
    }
}