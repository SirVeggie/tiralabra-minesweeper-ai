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

public class HashMapTest {
    HashMap<Integer, Integer> map;
    
    @Before
    public void setup() {
        map = new HashMap<>();
    }
    
    @Test
    public void testPutting() {
        map.put(10, 25);
        map.put(-2, -7);
        
        assertEquals(Integer.valueOf(25), map.get(10));
        assertEquals(Integer.valueOf(-7), map.get(-2));
        assertEquals(2, map.size());
        
        map.put(10, 0);
        
        assertEquals(Integer.valueOf(0), map.get(10));
        
        assertEquals(null, map.get(1234));
    }
    
    @Test
    public void testPutting2() {
        for (int i = 0; i < 100; i++) {
            map.put(i, i*i);
        }
        
        for (int i = 0; i < 100; i++) {
            assertEquals(Integer.valueOf(i*i), map.get(i));
        }
    }
    
    @Test
    public void testRemoving() {
        map.put(10, 25);
        map.put(-2, -7);
        map.remove(10);
        
        assertEquals(null, map.get(10));
        assertEquals(Integer.valueOf(-7), map.get(-2));
        assertEquals(1, map.size());
        assertEquals(false, map.remove(1234));
    }
    
    @Test
    public void testRemoving2() {
        for (int i = 0; i < 100; i++) {
            map.put(i, i*i);
        }
        
        for (int i = 0; i < 98; i++) {
            map.remove(i);
        }
        
        assertEquals(2, map.size());
    }
    
    @Test
    public void testContainsKey() {
        map.put(10, 25);
        map.put(-2, -7);
        
        assertEquals(true, map.containsKey(-2));
        assertEquals(false, map.containsKey(50));
    }
}