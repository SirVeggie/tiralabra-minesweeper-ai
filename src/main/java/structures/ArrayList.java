package structures;

public class ArrayList<E> {
    Object[] list;
    int pointer;
    
    public ArrayList() {
        this.list = new Object[50];
        this.pointer = 0;
    }
    
    public E get(int index) {
        if (index < pointer && index >= 0) {
            return (E) list[index];
        } else {
            throw new IndexOutOfBoundsException("ArrayList Index Out Of Bounds");
        }
    }
    
    public void add(E item) {
        list[pointer] = item;
        pointer++;
        checkAndExpand();
    }
    
    public void set(int index, E item) {
        if (index < pointer && index >= 0) {
            list[index] = item;
        } else {
            throw new IndexOutOfBoundsException("ArrayList Index Out Of Bounds");
        }
    }
    
    /**
     * Removes first matching item, and fills the gap
     * by shifting following items to the left.
     * 
     * @param item
     * @return Returns -1 if item not found, else 0.
     */
    public int remove(E item) {
        for (int i = 0; i < pointer; i++) {
            if (list[i] == item) {
                if (i + 1 == pointer)
                    pointer--;
                else
                    shiftLeft(i + 1);
                return 0;
            }
        }
        
        return -1;
    }
    
    /**
     * Removes item at specified index, and fills the gap
     * by shifting following items to the left.
     * 
     * @param index
     */
    public void removeAt(int index) {
        if (index < pointer && index >= 0) {
            if (index + 1 == pointer)
                pointer--;
            else
                shiftLeft(index + 1);
        } else {
            throw new IndexOutOfBoundsException("ArrayList Index Out Of Bounds");
        }
    }
    
    public int size() {
        return pointer;
    }
    
    /**
     * Check if the list contains the given item.
     * 
     * @param item
     * @return True if found, else false.
     */
    public boolean contains(E item) {
        for (int i = 0; i < pointer; i++) {
            if (list[i] == item) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Checks if list is full. If so, the size of the array is doubled
     * and items are moved to the new array.
     */
    private void checkAndExpand() {
        if (pointer == list.length) {
            Object[] old = list.clone();
            list = new Object[old.length * 2];
            for (int i = 0; i < old.length; i++) {
                list[i] = old[i];
            }
        }
    }
    
    /**
     * Shifts values to the left starting from the specified index.
     * First shifted value will be in index - 1.
     * 
     * @param index
     */
    private void shiftLeft(int index) {
        if (index >= 1 && index < pointer) {
            for (int k = index; k < pointer; k++) {
                list[k - 1] = list[k];
            }
            pointer--;
        } else {
            throw new IndexOutOfBoundsException("ArrayList Index Out Of Bounds");
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || this.getClass().getName() != obj.getClass().getName()) {
            return false;
        }
        
        ArrayList<E> e = (ArrayList<E>) obj;
        if (this.size() != e.size()) return false;
        
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(e.get(i))) return false;
        }
        
        return true;
    }
}