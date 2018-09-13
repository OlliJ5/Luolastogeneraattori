
import generaattori.Dungeon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DungeonTest {

    Dungeon dungeon;

    @Before
    public void setUp() {
        dungeon = new Dungeon(15, 60);
    }

    @Test
    public void constructorSetsCorrectHeight() {
        Assert.assertEquals(15, dungeon.getHeight());
    }

    @Test
    public void constructorSetsCorrectWidth() {
        Assert.assertEquals(60, dungeon.getWidth());
    }

    @Test
    public void isRoomOutOfBoundsReturnsTrueWhenRoomIsOutOfBounds() {
        Assert.assertEquals(true, dungeon.isRoomOutOfBounds(14, 14, 4, 4));
    }
    
    @Test
    public void isRoomOutOfBoundsReturnsFalseWhenRoomInsideTheDungeon() {
        Assert.assertEquals(false, dungeon.isRoomOutOfBounds(33, 10, 4, 4));
    }
    
    @Test
    public void whenRoomOverlapsMethodReturnsTrue() {
        dungeon.getDungeon()[11][42] = " ";
        Assert.assertEquals(true, dungeon.roomOverlaps(40, 10, 4, 4));
    }
    
    @Test
    public void whenRoomDoesNotOverlapMethodReturnsFalse() {
        Assert.assertEquals(false, dungeon.roomOverlaps(40, 10, 4, 4));
    }
    
    @Test
    public void whenRoomIsValidMethodReturnsTrue() {
        Assert.assertEquals(true, dungeon.checkIfRoomIsValid(40, 7, 2, 2));
    }
    
    
}
