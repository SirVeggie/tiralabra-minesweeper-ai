package util;

public class TestHelper {
    
    public boolean arraysMatch(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) return false;
        }
        
        return true;
    }
    
    public boolean arraysMatch(double[] array1, double[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) return false;
        }
        
        return true;
    }
    
    public boolean matricesMatch(double[][] array1, double[][] array2) {
        if (array1.length != array2.length)
            return false;
        
        for (int i = 0; i < array1.length; i++)
            if (array1[i].length != array2[i].length) return false;
        
        for (int i = 0; i < array1.length; i++) {
            for (int k = 0; k < array1[i].length; k++) {
                if (array1[i][k] != array2[i][k]) return false;
            }
        }
        
        return true;
    }
}