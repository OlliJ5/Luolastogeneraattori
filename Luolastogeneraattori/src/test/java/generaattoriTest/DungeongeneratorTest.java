package generaattoriTest;

import generaattori.Dungeongenerator;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class DungeongeneratorTest {

    Dungeongenerator generator;
    
    
    @Before
    public void setUp() {
        generator = new Dungeongenerator(15, 60);
    }
    
    @Test
    public void getHeightWorks() {
        Assert.assertEquals(15, generator.getHeight());
    }
    
    @Test
    public void getWidthWorks() {
        Assert.assertEquals(60, generator.getWidth());
    }
    
    @Test
    public void getDungeonWorks() {
        String[][] array = new String[15][60];
        
        for(int y = 0; y < array.length; y++) {
            for(int x = 0; x < array[0].length; x++) {
                array[y][x] = "#";
            }
        }
        
        Assert.assertArrayEquals(array, generator.getDungeon());
    }
    
    
    
    
    
}
