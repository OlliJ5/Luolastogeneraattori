
import generaattori.Dungeon;
import generaattori.Room;
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
        Room room = new Room(14, 14, 4, 4);
        Assert.assertEquals(true, room.isRoomOutOfBounds(dungeon.getDungeon()));
    }
    
    @Test
    public void isRoomOutOfBoundsReturnsFalseWhenRoomInsideTheDungeon() {
        Room room = new Room(33, 10, 4, 4);
        Assert.assertEquals(false, room.isRoomOutOfBounds(dungeon.getDungeon()));
    }
    
    @Test
    public void whenRoomOverlapsMethodReturnsTrue() {
        dungeon.getDungeon()[11][42] = " ";
        Room room = new Room(40, 10, 4, 4);
        Assert.assertEquals(true, room.roomOverlaps(dungeon.getDungeon()));
    }
    
    @Test
    public void whenRoomDoesNotOverlapMethodReturnsFalse() {
        Room room = new Room(40, 10, 4, 4);
        Assert.assertEquals(false, room.roomOverlaps(this.dungeon.getDungeon()));
    }
    
    @Test
    public void whenRoomIsValidMethodReturnsTrue() {
        Room room = new Room(40, 7, 2, 2);
        Assert.assertEquals(true, room.checkIfRoomIsValid(this.dungeon.getDungeon()));
    }
    
    
}
