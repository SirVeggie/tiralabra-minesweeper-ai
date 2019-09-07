package structures;

// Not ready
public class HashMap<K, V> {
    private Entry<K, V>[] entryTable;
    private static final int capacity = 16;
    private int size = 0;
    
    public HashMap() {
        this(capacity);
    }
    
    public HashMap(int capacity) {
        this.entryTable = new Entry[capacity];
    }
    
    public void put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value, null);
        //int bucket = getHash(key) % getBucketSize();
        int bucket = 0;
        Entry<K, V> existing = entryTable[bucket];
        
        if (existing == null) {
            entryTable[bucket] = entry;
            size++;
        } else {
            while (existing.next != null) {
                if (existing.key.equals(key)) {
                    existing.value = value;
                    return;
                }
                existing = existing.next;
            }
            
            if (existing.key.equals(key)) {
                existing.value = value;
            } else {
                existing.next = entry;
                size++;
            }
        }
    }
    
    public V get(K key) {
        //Entry<K, V> bucket = entryTable[getHash(key) % getBucketSize()];
        Entry<K, V> bucket = null;
        while (bucket != null) {
            if (bucket.key.equals(key)) {
                return bucket.value;
            }
            
            bucket = bucket.next;
        }
        
        return null;
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