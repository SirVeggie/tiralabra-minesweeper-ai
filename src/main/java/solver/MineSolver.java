package solver;

public class MineSolver {
    
    
    public int[] solve(int[] field) {
        int[] result = solveLocal(field);
        
        if (result == null) {
            result = solveComplex(field);
        }
        
        return result;
    }
    
    private int[] solveLocal(int[] field) {
        return null;
    }
    
    private int[] solveComplex(int[] field) {
        return null;
    }
}