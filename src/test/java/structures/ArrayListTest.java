package com.vogella.junit.first;

import static org.junit.Assert.*;

import javax.sound.sampled.SourceDataLine;

import solver.*;
import structures.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArrayListTest {
    private ArrayList<Integer> a;
    
    @Before
    public void setup() {
        a = new ArrayList<>();
    }
    
    @Test
    public void testAdding() {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(10);
        
        assertEquals(1, a.size());
        assertEquals(Integer.valueOf(10), a.get(0));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException() {
        a.get(10);
    }
    
    @Test
    public void testArrayExpansion() {
        for (int i = 0; i < 60; i++) {
            a.add(10);
        }
        
        assertEquals(60, a.size());
        assertEquals(Integer.valueOf(10), a.get(55));
    }
    
    @Test
    public void testSetting() {
        for (int i = 0; i < 10; i++) {
            a.add(10);
        }
        
        a.set(5, 2);
        
        assertEquals(Integer.valueOf(2), a.get(5));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetException() {
        a.set(10, 10);
    }
    
    @Test
    public void testRemoving() {
        for (int i = 0; i < 10; i++) {
            a.add(i);
        }
        
        a.remove(1);
        
        assertEquals(9, a.size());
        assertEquals(Integer.valueOf(2), a.get(1));
        assertEquals(Integer.valueOf(0), a.get(0));
        
        assertEquals(-1, a.remove(1000));
    }
    
    @Test
    public void testRemovingAtIndex() {
        for (int i = 0; i < 10; i++) {
            a.add(i);
        }
        
        a.removeAt(1);
        
        assertEquals(9, a.size());
        assertEquals(Integer.valueOf(2), a.get(1));
        
        int tmp = a.get(a.size()-1);
        a.removeAt(a.size()-1);
        
        assertNotEquals(Integer.valueOf(tmp), a.get(a.size()-1));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtException() {
        a.removeAt(10);
    }
    
    @Test
    public void testContains() {
        for (int i = 0; i < 10; i++) {
            a.add(i);
        }
        
        assertEquals(true, a.contains(5));
        assertEquals(false, a.contains(11));
    }
    
    @Test
    public void testEquals() {
        ArrayList<Integer> b = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(i);
        }
        for (int i = 0; i < 10; i++) {
            b.add(i);
        }
        
        assertEquals(true, a.equals(b));
        b.add(1234);
        assertEquals(false, a.equals(b));
        assertEquals(false, a.equals(10));
        assertEquals(false, a.equals(null));
        assertEquals(true, a.equals(a));
    }
}