package generaattoriTest;

import generaattori.CorridorCell;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class CorridorCellTest {
    CorridorCell cell;
    
    @Before
    public void setUp() {
        cell = new CorridorCell(34, 45);
    }
    
    @Test
    public void getXWorks() {
        Assert.assertEquals(34, cell.getX());
    }
    
    @Test
    public void getYWorks() {
        Assert.assertEquals(45, cell.getY());
    }
}
