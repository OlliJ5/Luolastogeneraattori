
package tietorakenteet;

import generaattori.Room;


public class RoomList {
    private Room[] list;
    private int size;
    
    public RoomList() {
        this.list = new Room[10];
        this.size = 0;
    }
    
    public void add(Room room) {
        if(this.size == list.length) {
            createBiggerList();
        }
        
        this.list[this.size] = room;
        this.size++;
        
    }
    
    public void createBiggerList() {
        double newSizeAsDouble = this.list.length * 1.5;
        int newSize = (int) newSizeAsDouble;
        
        Room[] newList = new Room[newSize];
        
        for(int i = 0; i < this.list.length; i++) {
            newList[i] = this.list[i];
        }
        
        this.list = newList;
        
    }
    
    public int size() {
        return this.size;
    }
    
    public Object get(int i) {
        return this.list[i];
    }
    
}
