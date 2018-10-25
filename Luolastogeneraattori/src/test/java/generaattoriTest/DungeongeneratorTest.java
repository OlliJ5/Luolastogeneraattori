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
    public void isCarvableReturnsFalseWhenCellIsOnTheBorder() {
        Assert.assertFalse(this.generator.isCarvable(1, 14, "UP"));
    }
    
    @Test
    public void isCarvableReturnsFalseWhenCellIsNotWall() {
        this.generator.getDungeon()[3][36] = "3";
        Assert.assertFalse(this.generator.isCarvable(36, 3, "UP"));
    }
    
    @Test
    public void isCarvableReturnsFalseWhenCellIsTooCloseToAroom() {
        this.generator.getDungeon()[7][24] = "2";
        this.generator.setRoomAmount(2);
        
        Assert.assertFalse(this.generator.isCarvable(7, 23, "UP"));
    }
    
    @Test
    public void isCarvableReturnsTrueWhenCellIsCarvable() {
        Assert.assertTrue(this.generator.isCarvable(54, 8, "UP"));
    }
    
    @Test
    public void isCarvableReturnsFalseWhenDirectionIsUpAndItIsNotAllowed() {
        this.generator.getDungeon()[10][30] = "11";
        
        Assert.assertFalse(this.generator.isCarvable(29, 10, "UP"));
    }
    
    @Test
    public void isCarvableReturnsFalseWhenDirectionIsLEFTAndItIsNotAllowed() {
        this.generator.getDungeon()[10][30] = "14";
        
        Assert.assertFalse(this.generator.isCarvable(30, 9, "LEFT"));
    }
    
    @Test
    public void isCarvableReturnsFalseWhenDirectionIsRightAndItIsNotAllowed() {
        this.generator.getDungeon()[10][30] = "13";
        
        Assert.assertFalse(this.generator.isCarvable(30, 9, "RIGHT"));
    }
    
    @Test
    public void isCarvableReturnsFalseWhenDirectionIsDownAndItIsNotAllowed() {
        this.generator.getDungeon()[10][30] = "2";
        
        Assert.assertFalse(this.generator.isCarvable(30, 10, "DOWN"));
    }
    
    @Test
    public void cellIsTooCloseToARoomReturnsFalseWhenItIsNotTooClose() {
        Assert.assertFalse(this.generator.cellIsTooCloseToARoom(30, 10));
    }
    
    @Test
    public void cellIsTooCloseToARoomReturnsTrueWhenItIsTooClose() {
        this.generator.getDungeon()[10][30] = "2";
        this.generator.setRoomAmount(2);
        
        Assert.assertTrue(this.generator.cellIsTooCloseToARoom(29, 9));
    }
    
    @Test
    public void cellIsInaRoomReturnsTrueWhenItIs() {
        this.generator.getDungeon()[7][46] = "3";
        this.generator.setRoomAmount(3);
        
        Assert.assertTrue(this.generator.cellIsINaRoom(46, 7));
    }
    
    @Test
    public void cellIsInaRoomReturnsFalseWhenItIsNot() {
        this.generator.getDungeon()[7][46] = "3";
        
        Assert.assertFalse(this.generator.cellIsINaRoom(46, 7));
    }
    
    
    @Test
    public void carvingWorksCorrectly() {
        CorridorCell cell = new CorridorCell(40, 6);
        this.generator.carve(cell);
        this.generator.makeItPretty();
        
        Assert.assertEquals(".", this.generator.getDungeon()[6][40]);
    }
    
    
    
    
    
}
