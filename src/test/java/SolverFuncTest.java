package com.vogella.junit.first;

import static org.junit.Assert.assertEquals;

import solver.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SolverFuncTest {
    
    @Test
    public void testTileCounting() {
        SolverFunc sf = new SolverFunc(60, 10, 5);
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
        SolverFunc sf = new SolverFunc(60, 10, 5);
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
}