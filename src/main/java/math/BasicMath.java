package math;

public class BasicMath {
    
    /**
     * Returns the absolute value of given integer.
     */
    public int abs(int x) {
        return (x <= 0) ? -x : x;
    }
    
    /**
     * Returns the absolute value of given double.
     */
    public double abs(double x) {
        return (x <= 0.0D) ? 0.0D - x : x;
    }
    
    /**
     * Returns the smaller value between a and b.
     */
    public int min(int a, int b) {
        return a < b ? a : b;
    }
}