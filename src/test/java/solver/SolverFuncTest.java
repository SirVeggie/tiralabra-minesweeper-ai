package com.vogella.junit.first;

import static org.junit.Assert.*;

import javax.sound.sampled.SourceDataLine;

import structures.*;
import solver.*;
import util.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SolverFuncTest {
    private SolverFunc sf;
    private TestHelper th;
    
    @Before
    public void setup() {
        sf = new SolverFunc(10, 5);
        th = new TestHelper();
    }
    
    @Test
    public void testTileCounting() {
        int[] field = new int[50];
        
        field[10] = 10;
        field[11] = 10;
        field[12] = 10;
        field[20] = 10;
        
        int count = sf.countAdjacentTiles(0, 10, field);
        
        assertEquals("Should find 2 tiles", 2, count);
        
        field[0] = 10;
        field[1] = 10;
        field[2] = 10;
        field[21] = 10;
        field[22] = 10;
        
        count = sf.countAdjacentTiles(11, 10, field);
        assertEquals("Surrounded by tiles", 8, count);
    }
    
    @Test
    public void testCountingOutOfBounds() {
        int[] field = new int[50];
        
        int count = sf.countAdjacentTiles(0, 0, field);
        assertEquals("Out of bounds should not be empty", 3, count);
        
        count = sf.countAdjacentTiles(35, -1, field);
        assertEquals("No walls nearby", 0, count);
        
        count = sf.countAdjacentTiles(0, -1, field);
        assertEquals("Out of bounds should be -1", 5, count);
        
        count = sf.countAdjacentTiles(45, -1, field);
        assertEquals("Lower wall nearby", 3, count);
    }
    
    @Test
    public void testMarking() {
        int[] field = {0,0,0,0,0,0,0,0,0,0,
                       0,0,0,9,10,1,0,0,0,0,
                       0,0,0,5,10,10,0,0,0,0,
                       0,0,0,10,0,10,0,0,0,0,
                       0,0,0,0,0,0,0,10,10,10};
        
        int[] expected1 = {0,0,0,0,0,0,0,0,0,0,
                          0,0,0,0,3,0,0,0,0,0,
                          0,0,0,0,0,3,0,0,0,0,
                          0,0,0,3,0,3,0,0,0,0,
                          0,0,0,0,0,0,0,0,0,0};
        
        int[] result1 = sf.markAdjacentTiles(24, field, 3);
        assertEquals(true, th.arraysMatch(expected1, result1));
        
        int[] expected2 = {0,0,0,0,0,0,0,0,0,0,
                           0,0,0,0,0,0,0,0,0,0,
                           0,0,0,0,0,0,0,0,0,0,
                           0,0,0,0,0,0,0,0,0,0,
                           0,0,0,0,0,0,0,3,0,3};

        int[] result2 = sf.markAdjacentTiles(48, field, 3);
        assertEquals(true, th.arraysMatch(expected2, result2));
    }
    
    @Test
    public void testFindUnsolved() {
        int[] field = {10, 3, 0, 0, 0, 4,10, 0, 0,10,
                        0, 0,10, 0, 0, 0, 0, 0, 1, 0,
                       10, 0, 0, 0,10, 0, 0, 0, 7, 0,
                        5, 0, 0,10, 0, 6, 0,10, 0, 5,
                        0, 0, 0, 0, 0, 0, 8, 0, 0, 2};
        
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(5);
        expected.add(18);
        expected.add(28);
        expected.add(30);
        expected.add(35);
        expected.add(46);
        
        ArrayList<Integer> result = sf.findUnsolvedNumbers(field);
        
        assertEquals(expected, result);
    }
    
    @Test
    public void testGetTileRow() {
        int[] field = new int[50];
        
        int first = sf.getTileRow(9);
        int second = sf.getTileRow(10);
        
        assertEquals(0, first);
        assertEquals(1, second);
    }
    
    @Test
    public void testRelativePos() {
        int[] field = {0,0,0,0,0,0,0,0,0,0,
                       0,0,1,2,3,0,0,0,0,0,
                       0,0,4,5,6,0,0,0,0,0,
                       0,0,7,8,9,0,0,0,0,0,
                       0,0,0,0,0,0,0,0,0,0};
        
        int leftUpper = sf.relativePos(0, 23);
        int centerUpper = sf.relativePos(1, 23);
        int rightUpper = sf.relativePos(2, 23);
        int leftMiddle = sf.relativePos(3, 23);
        int rightMiddle = sf.relativePos(4, 23);
        int leftLower = sf.relativePos(5, 23);
        int centerLower = sf.relativePos(6, 23);
        int rightLower = sf.relativePos(7, 23);
        
        assertEquals(12, leftUpper);
        assertEquals(13, centerUpper);
        assertEquals(14, rightUpper);
        assertEquals(22, leftMiddle);
        assertEquals(24, rightMiddle);
        assertEquals(32, leftLower);
        assertEquals(33, centerLower);
        assertEquals(34, rightLower);
    }
    
    @Test
    public void testCombineArrays() {
        int[] result;
        int[] one = {0,0,0,0,0};
        int[] two = {1,1,1,1,1};
        int[] three = {1,2,3,4,5};
        
        int[] expected = {2,3,4,5,6};
        
        result = sf.combineArrays(one,two,three);
        assertEquals(true, th.arraysMatch(expected, result));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCombineException() {
        
        int[] one = {1,2,3};
        int[] two = {1,2,3,4};
        
        sf.combineArrays(one, two);
    }
    
    @Test
    public void testOverwriteArray() {
        int[] result;
        int[] one = {1,1,1,1,1};
        int[] two = {0,0,5,3};
        
        int[] expected = {1,1,5,3,1};
        
        result = sf.overwriteArray(one, two);
        assertEquals(true, th.arraysMatch(expected, result));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOverwriteException() {
        
        int[] one = {1,1,1,1,1};
        int[] two = {0,0,1,1,0,0};
        
        sf.overwriteArray(one, two);
    }
    
    @Test
    public void testSubtractArrays() {
        double[] result;
        
        double[] one = {3,3,3,3,3,3};
        double[] two = {-1,0,1,2,3,4};
        
        double[] expected = {4,3,2,1,0,-1};
        
        result = sf.subtractArray(one, two);
        assertEquals(true, th.arraysMatch(expected, result));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSubtractException() {
        
        double[] one = {3,3,3,3,3,3};
        double[] two = {1,2};
        
        sf.subtractArray(one, two);
    }
}