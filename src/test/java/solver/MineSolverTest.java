package com.vogella.junit.first;

import static org.junit.Assert.assertEquals;

import util.*;
import solver.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MineSolverTest {
    private MineSolver ms;
    private TestHelper th;
    
    @Before
    public void setup() {
        ms = new MineSolver(5, 5);
        th = new TestHelper();
    }
    
    @Test
    public void testLocalSolvingNoExtraFlagsAllMines() {
        int[] field = {
            1,10, 2, 2, 1,
            2, 3,10, 2,10,
            1,10, 4, 5, 3,
            2, 4,10,10,10,
            1,10,10,10,10
        };
        int[] expected = {
            0, 3, 0, 0, 0,
            0, 0, 3, 0, 3,
            0, 3, 0, 0, 0,
            0, 0, 3, 3, 3,
            0, 3, 3, 0, 0
        };
        int[] result = ms.solve(field);
        
        assertEquals(true, th.arraysMatch(expected, result));
    }
    
    @Test
    public void testLocalSolvingNoExtraFlagsMixed() {
        int[] field = {
            1,10, 0, 0, 0,
            0,10, 0,10, 2,
            0, 0, 0,10, 0,
            0, 0, 0, 1, 0,
            0, 0,10,10, 0
        };
        int[] expected = {
            0, 0, 0, 0, 0,
            0, 0, 0, 3, 0,
            0, 0, 0, 3, 0,
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0
        };
        int[] result = ms.solve(field);
        
        assertEquals(true, th.arraysMatch(expected, result));
    }
    
    @Test
    public void testLocalSolvingWithExtraFlags() {
        int[] field = {
            1,10, 2, 1, 1,
            2,10, 4,10, 2,
            1, 9, 3, 9, 2,
            1, 1, 2, 1, 1,
            0, 0,10,10, 0
        };
        int[] expected = {
            0, 0, 0, 0, 0,
            0, 1, 0, 3, 0,
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
            0, 0, 1, 1, 0
        };
        int[] result = ms.solve(field);
        
        assertEquals(true, th.arraysMatch(expected, result));
    }
    
    @Test
    public void testComplexSolving() {
        int[] field = {
            10, 1, 0, 0, 0,
            10, 2, 0, 0, 0,
            10, 2, 0, 0, 0,
            10, 2, 0, 0, 0,
            10, 1, 0, 0, 0
        };
        int[] expected = {
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
            3, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0
        };
        int[] result = ms.solve(field);
        
        assertEquals(true, th.arraysMatch(expected, result));
    }
    
    @Test
    public void testChording() {
        int[] field = {
            0, 0, 0, 0, 0,
            0, 0, 9, 0, 0,
            0,10, 2, 9, 0,
            0,10, 0,10, 0,
            0, 0, 0, 0, 0
        };
        int[] expected = {
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 1, 0, 1, 0,
            0, 0, 0, 0, 0
        };
        int[] result = ms.chord(field, 12);
        
        assertEquals(true, th.arraysMatch(expected, result));
    }
}