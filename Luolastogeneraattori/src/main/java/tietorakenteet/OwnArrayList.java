
package tietorakenteet;



public class OwnArrayList<E> {
    private E[] list;
    private int size;
    
    public OwnArrayList() {
        this.list = (E[])new Object[10];
        this.size = 0;
    }
    
    public void add(E e) {
        if(this.size == list.length) {
            createBiggerList();
        }
        
        this.list[this.size] = e;
        this.size++;
        
    }
    
    public void createBiggerList() {
        double newSizeAsDouble = this.list.length * 1.5;
        int newSize = (int) newSizeAsDouble;
        
        E[] newList = (E[])new Object[newSize];
        
        for(int i = 0; i < this.list.length; i++) {
            newList[i] = this.list[i];
        }
        
        this.list = newList;
        
    }
    
    public int size() {
        return this.size;
    }
    
    public boolean isEmpty() {
        if(this.size == 0) {
            return true;
        }
        return false;
    }
    
    public E get(int i) {
        return this.list[i];
    }
    
    public boolean contains(E e) {
        for(int i = 0; i < this.size; i++) {
            if(this.list[i].equals(e)) {
                return true;
            }
        }
        return false;
    }
    
    public void remove(int index) {
        for(int i = index; i < this.size; i++) {
            this.list[i] = this.list[i + 1];
        }
        
        this.size--;
    }
    
}
