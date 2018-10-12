package generaattoriTest;


import generaattori.Dungeongenerator;
import generaattori.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {

    Dungeongenerator dungeon;

    @Before
    public void setUp() {
        dungeon = new Dungeongenerator(15, 60);
    }

    @Test
    public void isRoomOutOfBoundsReturnsTrueWhenRoomIsOutOfBounds() {
        Room room = new Room(14, 14, 4, 4, 1);
        Assert.assertEquals(true, room.isRoomOutOfBounds(dungeon.getDungeon()));
    }

    @Test
    public void isRoomOutOfBoundsReturnsFalseWhenRoomInsideTheDungeon() {
        Room room = new Room(33, 10, 4, 4, 1);
        Assert.assertEquals(false, room.isRoomOutOfBounds(dungeon.getDungeon()));
    }

    @Test
    public void whenRoomOverlapsMethodReturnsTrue() {
        dungeon.getDungeon()[11][42] = " ";
        Room room = new Room(40, 10, 4, 4, 1);
        Assert.assertEquals(true, room.roomOverlaps(dungeon.getDungeon()));
    }

    @Test
    public void whenRoomDoesNotOverlapMethodReturnsFalse() {
        Room room = new Room(40, 10, 4, 4, 1);
        Assert.assertEquals(false, room.roomOverlaps(this.dungeon.getDungeon()));
    }

    @Test
    public void whenRoomIsValidMethodReturnsTrue() {
        Room room = new Room(40, 7, 2, 2, 1);
        Assert.assertEquals(true, room.checkIfRoomIsValid(this.dungeon.getDungeon()));
    }

    @Test
    public void whenRoomIsInvalidMethodReturnsFalse() {
        dungeon.getDungeon()[5][31] = "2";
        Room room = new Room(30, 5, 4, 2, 3);
        Assert.assertFalse(room.checkIfRoomIsValid(dungeon.getDungeon()));
    }

    @Test
    public void placeRoomWorks() {
        Room room = new Room(25, 7, 3, 3, 2);
        Assert.assertEquals(2, room.placeRoom(dungeon.getDungeon(), 2));
    }

    @Test
    public void placeRoomDoesNotPlaceRoom() {
        Room room = new Room(60, 10, 4, 4, 2);
        Assert.assertEquals(0, room.placeRoom(dungeon.getDungeon(), 2));
    }

    @Test
    public void getXWorks() {
        Room room = new Room(4, 5, 10, 10, 4);
        Assert.assertEquals(4, room.getX());
    }

    @Test
    public void getYWorks() {
        Room room = new Room(4, 5, 10, 10, 4);
        Assert.assertEquals(5, room.getY());
    }

    @Test
    public void getWidthWorks() {
        Room room = new Room(4, 5, 10, 10, 4);
        Assert.assertEquals(10, room.getWidth());
    }

    @Test
    public void getHeightWorks() {
        Room room = new Room(4, 5, 10, 10, 4);
        Assert.assertEquals(10, room.getHeight());
    }
}
