package structures;

import math.*;

// Not ready
public class HashMap<K, V> {
    private Entry<K, V>[] entryTable;
    private static final int initialCapacity = 16;
    private int size = 0;
    
    public HashMap() {
        this(initialCapacity);
    }
    
    public HashMap(int capacity) {
        this.entryTable = new Entry[capacity];
    }
    
    /**
     * Returns the number of key-value pairs.
     */
    public int size() {
        return size;
    }
    
    /**
     * Save a key-value pair.
     * 
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Null key is not valid");
        
        int hash = getHash(key);
        Entry<K, V> entry = new Entry<K, V>(key, value, null);
        
        Entry<K, V> current = entryTable[hash];
        if (current == null) {
            entryTable[hash] = entry;
            size++;
        } else {
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                
                current = current.next;
            }
            
            if (current.key.equals(key)) {
                current.value = value;
            } else {
                current.next = entry;
                size++;
            }
        }
    }
    
    /**
     * Returns the saved value under the specified key.
     * 
     * @param key
     * @return Saved value
     */
    public V get(K key) {
        if (key == null) return null;
        
        int hash = getHash(key);
        if (entryTable[hash] == null) {
            return null;
        } else {
            Entry<K, V> temp = entryTable[hash];
            
            while (temp != null) {
                if (temp.key.equals(key)) {
                    return temp.value;
                }
                temp = temp.next;
            }
        }
        
        return null;
    }
    
    /**
     * Remove a key-value pair saved under the specified key.
     * 
     * @param key
     * @return True if key was found and removed, else false.
     */
    public boolean remove(K key) {
        if (key == null) return false;
        
        int hash = getHash(key);
        if (entryTable[hash] == null) {
            return false;
        } else {
            Entry<K, V> current = entryTable[hash];
            if (current.key.equals(key)) {
                entryTable[hash] = current.next;
                size--;
                return true;
            }
            
            while (current.next != null) {
                if (current.next.key.equals(key)) {
                    current.next = current.next.next;
                    size--;
                    return true;
                }
                
                current = current.next;
            }
        }
        
        return false;
    }
    
    /**
     * Checks if a key-value pair by the specified key exists.
     * 
     * @param key
     * @return True if pair exists, else false.
     */
    public boolean containsKey(K key) {
        if (key == null) return false;
        
        int hash = getHash(key);
        if (entryTable[hash] == null) {
            return false;
        } else {
            Entry<K, V> current = entryTable[hash];
            while (current != null) {
                if (current.key.equals(key)) {
                    return true;
                }
                
                current = current.next;
            }
        }
        
        return false;
    }
    
    private int getHash(K key) {
        BasicMath m = new BasicMath();
        return m.abs(key.hashCode()) % initialCapacity;
    }
}

class Entry<K, V> {
    final K key;
    V value;
    Entry<K, V> next;
    
    public Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
    
    /**
     * @return the key
     */
    public K getKey() {
        return key;
    }
    
    /**
     * @return the value
     */
    public V getValue() {
        return value;
    }
    
    /**
     * @param value the value to set
     */
    public void setValue(V value) {
        this.value = value;
    }
    
    /**
     * @return the next
     */
    public Entry<K, V> getNext() {
        return next;
    }
    
    /**
     * @param next the next to set
     */
    public void setNext(Entry<K, V> next) {
        this.next = next;
    }
    
    @Override
    public int hashCode() {
        int prime = 13;
        int mul = 11;
        if (key != null) {
            int hashCode = prime * mul + key.hashCode();
            return hashCode;
        }
        return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || this.getClass().getName() != obj.getClass().getName()) {
            return false;
        }
        
        Entry e = (Entry) obj;
        if (this.key == e.key) {
            return true;
        }
        
        return false;
    }
}