package generaattoriTest;

import generaattori.Dungeongenerator;
import generaattori.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {

    String[][] dungeon;

    @Before
    public void setUp() {
        Dungeongenerator generator = new Dungeongenerator(15, 60);
        dungeon = generator.getDungeon();
    }

    @Test
    public void isRoomOutOfBoundsReturnsTrueWhenRoomIsOutOfBounds() {
        Room room = new Room(14, 14, 4, 4, 1);
        Assert.assertTrue(room.isRoomOutOfBounds(dungeon));

        Room r = new Room(0, 5, 5, 5, 3);
        Assert.assertTrue(r.isRoomOutOfBounds(dungeon));

        Room r2 = new Room(5, 0, 6, 6, 2);
        Assert.assertTrue(r2.isRoomOutOfBounds(dungeon));
    }

    @Test
    public void isRoomOutOfBoundsReturnsFalseWhenRoomInsideTheDungeon() {
        Room room = new Room(33, 10, 4, 4, 1);
        Assert.assertFalse(room.isRoomOutOfBounds(dungeon));
    }

    @Test
    public void whenRoomOverlapsMethodReturnsTrue() {
        dungeon[11][42] = " ";
        Room room = new Room(40, 10, 4, 4, 1);
        Assert.assertEquals(true, room.roomOverlaps(dungeon));
    }

    @Test
    public void whenRoomDoesNotOverlapMethodReturnsFalse() {
        Room room = new Room(40, 10, 4, 4, 1);
        Assert.assertEquals(false, room.roomOverlaps(this.dungeon));
    }

    @Test
    public void whenRoomIsValidMethodReturnsTrue() {
        Room room = new Room(40, 7, 2, 2, 1);
        Assert.assertEquals(true, room.checkIfRoomIsValid(this.dungeon));
    }

    @Test
    public void whenRoomIsInvalidMethodReturnsFalse() {
        dungeon[5][31] = "2";
        Room room = new Room(30, 5, 4, 2, 3);
        Assert.assertFalse(room.checkIfRoomIsValid(dungeon));

        Room r = new Room(15, 70, 2, 2, 3);
        Assert.assertFalse(room.checkIfRoomIsValid(dungeon));
    }

    @Test
    public void placeRoomWorks() {
        Room room = new Room(25, 7, 3, 3, 2);
        Assert.assertEquals(2, room.placeRoom(dungeon, 2));

        String[][] d = new String[15][60];

        for (int y = 0; y < d.length; y++) {
            for (int x = 0; x < d[0].length; x++) {
                d[y][x] = "#";
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                d[7 + i][25 + j] = "2";
            }
        }

        Assert.assertArrayEquals(d, this.dungeon);
    }

    @Test
    public void placeRoomDoesNotPlaceRoom() {
        Room room = new Room(60, 10, 4, 4, 2);
        Assert.assertEquals(0, room.placeRoom(dungeon, 2));

        String[][] d = new String[15][60];

        for (int y = 0; y < d.length; y++) {
            for (int x = 0; x < d[0].length; x++) {
                d[y][x] = "#";
            }
        }
        
        Assert.assertArrayEquals(d, this.dungeon);
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
