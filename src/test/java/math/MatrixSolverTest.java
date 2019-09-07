package com.vogella.junit.first;

import static org.junit.Assert.*;

import javax.sound.sampled.SourceDataLine;

import util.*;
import math.*;
import solver.*;
import structures.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatrixSolverTest {
    private MatrixSolver ms;
    private TestHelper th;
    
    @Before
    public void setup() {
        ms = new MatrixSolver();
        th = new TestHelper();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreation() {
        double[][] mat = {{1,2,3},
                          {0,0,0},
                          {1,1,1}};
        double[] sol = {0,1,2,3};
        
        MatrixSolver test = new MatrixSolver(mat, sol);
    }
    
    @Test
    public void testSwap() {
        double[][] mat = {{1,2,3},
                          {0,0,0},
                          {1,1,1}};
        double[] sol = {0,1,2};
        
        double[][] expectedMat = {{1,1,1},
                                  {0,0,0},
                                  {1,2,3}};
        double[] expectedSol = {2,1,0};
        
        ms.setMatrix(mat);
        ms.setSolutions(sol);
        ms.swap(0,2);
        
        assertEquals(true, th.matricesMatch(expectedMat, ms.getMatrix()));
        assertEquals(true, th.arraysMatch(expectedSol, ms.getSolutions()));
    }
    
    @Test
    public void testScale() {
        double[][] mat = {{1,2,3},
                          {0,0,0},
                          {1,1,1}};
        double[] sol = {1,2,3};
        
        double[][] expectedMat = {{2,4,6},
                                  {0,0,0},
                                  {1,1,1}};
        double[] expectedSol = {2,2,3};
        
        ms.setMatrix(mat);
        ms.setSolutions(sol);
        ms.scale(0, 2);
        
        assertEquals(true, th.matricesMatch(expectedMat, ms.getMatrix()));
        assertEquals(true, th.arraysMatch(expectedSol, ms.getSolutions()));
    }
    
    @Test
    public void testScaledAddition() {
        double[][] mat = {{1,2,3},
                          {0,0,0},
                          {1,1,1}};
        double[] sol = {1,2,3};
        
        double[][] expectedMat = {{4,5,6},
                                  {0,0,0},
                                  {1,1,1}};
        double[] expectedSol = {10,2,3};
        
        ms.setMatrix(mat);
        ms.setSolutions(sol);
        ms.addScaled(0, 2, 3);
        
        assertEquals(true, th.matricesMatch(expectedMat, ms.getMatrix()));
        assertEquals(true, th.arraysMatch(expectedSol, ms.getSolutions()));
    }
    
    @Test
    public void testGaussianWithSquareMatrix() {
        double[][] mat = {{1,1,0,0,0},
                          {1,1,1,0,0},
                          {0,1,1,1,0},
                          {0,0,1,1,1},
                          {0,0,0,1,1}};
        double[] sol = {1,2,2,2,1};
        
        double[][] expectedMat = {{1,1,0,0,0},
                                  {0,1,1,1,0},
                                  {0,0,1,0,0},
                                  {0,0,0,1,1},
                                  {0,0,0,0,0}};
        double[] expectedSol = {1,2,1,1,0};
        
        ms.setMatrix(mat);
        ms.setSolutions(sol);
        ms.gaussianElimination();
        
        assertEquals(true, th.matricesMatch(expectedMat, ms.getMatrix()));
        assertEquals(true, th.arraysMatch(expectedSol, ms.getSolutions()));
    }
    
    @Test
    public void testGaussianWithRectangleMatrix() {
        
    }
    
    @Test
    public void testInterpretation() {
        double[][] mat = {{1,2,3,0,1},
                          {0,0,0,0,0},
                          {1,1,1,0,0}};
        double[] sol = {0,0,0,0,0};
        
        int[] expected = {1,0,1,0,1,0,0,0,0,0};
        
        ms.setMatrix(mat);
        ms.setSolutions(sol);
        
        HashMap<Integer,Integer> indexToField = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            indexToField.put(i, i*2);
        }
        int[] result = ms.interpretMatrix(indexToField, 10);
        
        assertEquals(true, th.arraysMatch(expected, result));
    }
}