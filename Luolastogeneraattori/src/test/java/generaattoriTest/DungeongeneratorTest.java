package generaattoriTest;

import generaattori.Connector;
import generaattori.CorridorCell;
import generaattori.Dungeongenerator;
import generaattori.Room;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import tietorakenteet.OwnArrayList;

public class DungeongeneratorTest {

    Dungeongenerator generator;

    @Before
    public void setUp() {
        generator = new Dungeongenerator(15, 60, 2, 2);
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

        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[0].length; x++) {
                array[y][x] = "#";
            }
        }

        Assert.assertArrayEquals(array, generator.getDungeon());
    }

    @Test
    public void toStringWorks() {
        String dungeonAsString = "";
        for (int i = 0; i < this.generator.getHeight(); i++) {
            dungeonAsString += "\n";
            for (int j = 0; j < generator.getWidth(); j++) {
                dungeonAsString += this.generator.getDungeon()[i][j];
            }
        }
        
        Assert.assertEquals(dungeonAsString, this.generator.toString());
    }

    //Tests for room placement start here
    @Test
    public void placeRoomsPlacesRooms() {
        this.generator.placeRooms(6);

        OwnArrayList<Room> rooms = this.generator.getRooms();

        String[][] expected = new String[15][60];

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                expected[i][j] = "#";
            }
        }

        for (int i = 0; i < rooms.size(); i++) {
            int roomX = rooms.get(i).getX();
            int roomY = rooms.get(i).getY();
            int region = rooms.get(i).getRegionNumber();
            int height = rooms.get(i).getHeight();
            int width = rooms.get(i).getWidth();

            for (int y = roomY; y < roomY + height; y++) {
                for (int x = roomX; x < roomX + width; x++) {
                    expected[y][x] = Integer.toString(region);
                }
            }
        }

        Assert.assertArrayEquals(expected, this.generator.getDungeon());

    }

    //Tests for corridor building start here
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

        for (int i = 0; i < correctNeighbours.size(); i++) {
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

    @Test
    public void buildCorridorsBuildsSomething() {
        String[][] emptyDungeon = new String[15][60];

        for (int i = 0; i < emptyDungeon.length; i++) {
            for (int j = 0; j < emptyDungeon[0].length; j++) {
                emptyDungeon[i][j] = "#";
            }
        }

        this.generator.buildCorridors();

        Assert.assertFalse(Arrays.equals(emptyDungeon, this.generator.getDungeon()));

    }

    //Tests for connecting the dungeon start here
    @Test
    public void isConnectorReturnsTrueWhenCellIsAConnector() {
        this.generator.getDungeon()[10][45] = "2";
        this.generator.getDungeon()[12][45] = "4";

        Assert.assertTrue(this.generator.isConnector(45, 11));
    }

    @Test
    public void isConnectorReturnsFalseWhenCellIsNotAConnector() {
        this.generator.getDungeon()[10][45] = "3";

        Assert.assertFalse(this.generator.isConnector(45, 11));
    }

    @Test
    public void connectsRegionsFindsCorrectRegions() {
        int[] regions = {23, 4};

        this.generator.getDungeon()[6][34] = "4";
        this.generator.getDungeon()[6][36] = "23";

        Assert.assertArrayEquals(regions, this.generator.connectsRegions(35, 6));
    }

    @Test
    public void findConnectorsFindsCorrectConnectors() {
        OwnArrayList<Connector> connectors = new OwnArrayList<>();

        this.generator.getDungeon()[4][11] = "1";
        this.generator.getDungeon()[4][9] = "2";
        connectors.add(new Connector(10, 4, 1, 2));

        this.generator.getDungeon()[6][35] = "3";
        this.generator.getDungeon()[6][33] = "5";
        connectors.add(new Connector(34, 6, 3, 5));

        this.generator.getDungeon()[8][23] = "8";
        this.generator.getDungeon()[10][23] = "5";
        connectors.add(new Connector(23, 9, 8, 5));

        OwnArrayList foundConnectors = this.generator.findConnectors();

        Assert.assertEquals(connectors.size(), foundConnectors.size());

        for (int i = 0; i < connectors.size(); i++) {
            Assert.assertEquals(connectors.get(i), foundConnectors.get(i));
        }
    }

    @Test
    public void connectDungeonConnectsDungeon() {
        this.generator.getDungeon()[4][11] = "1";
        this.generator.getDungeon()[4][9] = "2";

        this.generator.getDungeon()[6][35] = "2";
        this.generator.getDungeon()[6][33] = "3";

        this.generator.getDungeon()[3][23] = "3";
        this.generator.getDungeon()[5][23] = "4";

        this.generator.getDungeon()[8][23] = "4";
        this.generator.getDungeon()[10][23] = "2";

        this.generator.setRegion(4);
        this.generator.connectDungeon();

        String[][] correct = new String[15][60];
        for (int i = 0; i < this.generator.getHeight(); i++) {
            for (int j = 0; j < this.generator.getWidth(); j++) {
                correct[i][j] = "#";
            }
        }

        correct[4][11] = "1";
        correct[4][9] = "2";

        correct[6][35] = "2";
        correct[6][33] = "3";

        correct[3][23] = "3";
        correct[5][23] = "4";

        correct[8][23] = "4";
        correct[10][23] = "2";

        correct[4][10] = "/";
        correct[6][34] = "/";
        correct[4][23] = "/";

        Assert.assertArrayEquals(correct, this.generator.getDungeon());

    }

    //Test for removing dead-ends start here
    @Test
    public void removingDeadEndsFromAnUnconnectedDungeonRemovesEverything() {
        String[][] empty = new String[15][60];

        for (int i = 0; i < empty.length; i++) {
            for (int j = 0; j < empty[0].length; j++) {
                empty[i][j] = "#";
            }
        }

        this.generator.buildCorridors();
        this.generator.removeDeadEnds();

        Assert.assertArrayEquals(empty, this.generator.getDungeon());
    }

}
