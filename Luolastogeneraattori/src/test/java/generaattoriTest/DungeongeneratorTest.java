package generaattoriTest;

import generaattori.CorridorCell;
import generaattori.Dungeongenerator;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import tietorakenteet.OwnArrayList;

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
    
    @Test
    public void ReturnsFalseWhenCorridorCannotBeStartedHere() {
        this.generator.getDungeon()[5][8] = " ";
        Assert.assertFalse(this.generator.checkIfPlaceIsValidForStartingACorridor(8, 5));
    }
    
    @Test
    public void ReturnsTrueWhenCorridorCanBeStartedHere() {
        Assert.assertTrue(this.generator.checkIfPlaceIsValidForStartingACorridor(8, 5));
    }
    
    @Test
    public void getCarvableNeighboursGetsCorrectNeighbours() {
        CorridorCell cell = new CorridorCell(23, 5);
        OwnArrayList<CorridorCell> neighbours = this.generator.getCarvableNeighbours(cell);

        OwnArrayList<CorridorCell> correctNeighbours = new OwnArrayList<>();
        correctNeighbours.add(new CorridorCell(23, 4));
        correctNeighbours.add(new CorridorCell(24, 5));
        correctNeighbours.add(new CorridorCell(23, 6));
        correctNeighbours.add(new CorridorCell(22, 5));
        
        for(int i = 0; i < correctNeighbours.size(); i++) {
            Assert.assertEquals(correctNeighbours.get(i), neighbours.get(i));
        }
        
    }
    
    @Test
    public void isCarvableWorksCorrectly() {
        
    }
    
    @Test
    public void carvingWorksCorrectly() {
        CorridorCell cell = new CorridorCell(40, 6);
        this.generator.carve(cell);
        this.generator.makeItPretty();
        
        Assert.assertEquals(".", this.generator.getDungeon()[6][40]);
    }
    
    
    
    
    
}
