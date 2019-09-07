package com.vogella.junit.first;

import static org.junit.Assert.*;

import javax.sound.sampled.SourceDataLine;

import math.*;
import solver.*;
import structures.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BasicMathTest {
    private BasicMath m;
    
    @Before
    public void setup() {
        m = new BasicMath();
    }
    
    @Test
    public void testAbsInt() {
        int one = m.abs(10);
        int two = m.abs(-10);
        
        assertEquals(10, one);
        assertEquals(10, two);
    }
    
    @Test
    public void testAbsDouble() {
        double one = m.abs(5.6);
        double two = m.abs(-5.6);
        
        assertEquals(5.6, one, 0.01);
        assertEquals(5.6, two, 0.01);
    }
    
    @Test
    public void testMinInt() {
        assertEquals(10, m.min(12,10));
        assertEquals(5, m.min(5,10));
    }
}