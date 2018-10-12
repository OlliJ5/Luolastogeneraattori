package tietorakenteetTest;

import generaattori.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tietorakenteet.OwnArrayList;

public class OwnArrayListTest {
    OwnArrayList<Room> list;
    
    @Before
    public void setUp() {
        list = new OwnArrayList();
    }
    
    @Test
    public void addWorks() {
        Room room = new Room(3, 3, 3, 3, 3);
        Assert.assertFalse(this.list.contains(room));
        
        this.list.add(room);
        Assert.assertTrue(this.list.contains(room));
    }
    
    @Test
    public void createsBiggerListWhenNeeded() {
        for(int i = 0; i < 11; i++) {
            this.list.add(new Room(5, 5, 5, 5, 5));
        }
        
        Assert.assertEquals(11, this.list.size());
    }
    
    @Test
    public void sizeReturnsCorrectSize() {
        Assert.assertEquals(0, this.list.size());
        
        this.list.add(new Room(2, 2, 2, 1, 2));
        
        Assert.assertEquals(1, this.list.size());
    }
    
    @Test
    public void getReturnsTheCorrectElement() {
        this.list.add(new Room(2, 2, 2, 2, 2));
        this.list.add(new Room(6, 6, 3, 2, 6));
        
        Room room = new Room(7, 3, 4, 2, 8);
        this.list.add(room);
        
        this.list.add(new Room(6, 2, 4, 5, 2));
        
        Assert.assertEquals(room, this.list.get(2));
    }
    
    @Test
    public void containsWorks() {
        Room room = new Room(234, 4, 2, 45, 23);
        
        Assert.assertFalse(this.list.contains(room));

        this.list.add(room);
        
        Assert.assertTrue(this.list.contains(room));
    }
    
}
