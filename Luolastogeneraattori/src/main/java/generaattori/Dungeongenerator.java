package generaattori;

import java.util.ArrayList;
import java.util.Random;
import tietorakenteet.RoomList;
import tietorakenteet.Unionfind;

/**
 *
 * @author ogrousu
 */
public class Dungeongenerator{

    private int height;
    private int width;
    private String[][] dungeon;
    private int region;
    private RoomList rooms;

    /**
     *
     * create a dungeon generator that creates a dungeon of some size
     *
     * @param height the desired height of the dungeon
     * @param width the desired width of the dungeon
     */
    public Dungeongenerator(int height, int width) {
        this.height = height;
        this.width = width;
        this.dungeon = new String[this.height][this.width];
        this.region = 1;
        this.rooms = new RoomList();

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.dungeon[i][j] = "#";
            }
        }
    }

    /**
     *
     * @return height of the dungeon
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return width of the dungeon
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return the dungeon as a two dimensional array
     */
    public String[][] getDungeon() {
        return dungeon;
    }

    /**
     * The method generates a dungeon with rooms and corridors
     *
     * @param amountOfRooms amount of tries to place a room
     */
    public void generate(int amountOfRooms) {
        this.placeRooms(amountOfRooms);
        this.placeCorridors();
        this.connectDungeon();
        this.makeItPretty();
    }

    /**
     * The method prints out the the created ASCII-dungeon
     */
    public void print() {
        for (int i = 0; i < this.height; i++) {
            System.out.print("\n");
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.dungeon[i][j]);
            }
        }

    }

    /**
     * The method places Corridors in the dungeon
     */
    public void placeCorridors() {
        for (int y = 1; y < this.height - 1; y += 2) {
            for (int x = 1; x < this.width - 1; x += 2) {
                if (checkIfPlaceIsValidForCorridor(x, y)) {
                    this.floodFill(y, x, "dunno");
                    region++;
                }
            }
        }
    }

    /**
     * Recursive method that checks if a certain point can be a part of a
     * corridor in the dungeon. Calls itself to check if the surrounding points
     * are acceptable spots as well
     *
     * @param y the y-value of the spot in the dungeon we are checking
     * @param x the x-value of the spot in the dungeon we are checking
     * @param direction the direction in which the corridor is moving
     */
    public void floodFill(int y, int x, String direction) {
        if (y < 1 || x < 1 || y >= height - 1 || x >= width - 1) {
            return;
        }

        if (dungeon[y][x].equals(".")) {
            return;
        }
        if (!dungeon[y][x].equals("#")) {
            return;
        }

        if (isInteger(dungeon[y - 1][x - 1]) && Integer.parseInt(dungeon[y - 1][x - 1]) <= rooms.size()
                || isInteger(dungeon[y - 1][x]) && Integer.parseInt(dungeon[y - 1][x]) <= rooms.size()
                || isInteger(dungeon[y - 1][x + 1]) && Integer.parseInt(dungeon[y - 1][x + 1]) <= rooms.size()
                || isInteger(dungeon[y][x - 1]) && Integer.parseInt(dungeon[y][x - 1]) <= rooms.size()
                || isInteger(dungeon[y][x + 1]) && Integer.parseInt(dungeon[y][x + 1]) <= rooms.size()
                || isInteger(dungeon[y + 1][x - 1]) && Integer.parseInt(dungeon[y + 1][x - 1]) <= rooms.size()
                || isInteger(dungeon[y + 1][x]) && Integer.parseInt(dungeon[y + 1][x]) <= rooms.size()
                || isInteger(dungeon[y + 1][x + 1]) && Integer.parseInt(dungeon[y + 1][x + 1]) <= rooms.size()) {

            return;
        }

        if (direction.equals(
                "UP")) {
            if (!dungeon[y - 1][x].equals("#")
                    || !dungeon[y][x - 1].equals("#")
                    || !dungeon[y][x + 1].equals("#")) {
                return;
            }

        } else if (direction.equals(
                "LEFT")) {
            if (!dungeon[y - 1][x].equals("#")
                    || !dungeon[y][x - 1].equals("#")
                    || !dungeon[y + 1][x].equals("#")) {
                return;
            }

        } else if (direction.equals(
                "RIGHT")) {
            if (!dungeon[y - 1][x].equals("#")
                    || !dungeon[y + 1][x].equals("#")
                    || !dungeon[y][x + 1].equals("#")) {
                return;
            }

        } else if (direction.equals(
                "DOWN")) {
            if (!dungeon[y][x - 1].equals("#")
                    || !dungeon[y][x + 1].equals("#")
                    || !dungeon[y + 1][x].equals("#")) {
                return;
            }
        }

        this.dungeon[y][x] = Integer.toString(region);

        this.floodFill(y
                - 1, x, "UP");

        this.floodFill(y, x
                - 1, "LEFT");

        this.floodFill(y, x
                + 1, "RIGHT");

        this.floodFill(y
                + 1, x, "DOWN");
    }

    /**
     * checks if a corridor can be placed in this position
     *
     * @param x x-value of the corridor
     * @param y y-value of the corridor
     * @return true, if a corridor can be placed, else false
     */
    public boolean checkIfPlaceIsValidForCorridor(int x, int y) {
        if (!dungeon[y][x].equals("#")
                || !dungeon[y - 1][x - 1].equals("#")
                || !dungeon[y - 1][x + 1].equals("#")
                || !dungeon[y + 1][x - 1].equals("#")
                || !dungeon[y + 1][x + 1].equals("#")) {
            return false;
        }
        return true;
    }

    /**
     * Places rooms in the dungeon
     *
     * @param amount amount of tries to place a room
     */
    public void placeRooms(int amount) {
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            int x = random.nextInt(this.width - 2) + 1;
            int y = random.nextInt(this.height - 2) + 1;

            int height = random.nextInt(2) + 2;
            int width = random.nextInt(4) + 2;

            Room room = new Room(x, y, width, height, region);

            if (room.placeRoom(dungeon, region) != 0) {
                rooms.add(room);
                region++;
            }

        }
    }

    /**
     * methods prints connectors, used for debugging
     */
    public void printConnectors() {
        ArrayList connectors = findConnectors();

        for (int i = 0; i < connectors.size(); i++) {
            System.out.println(connectors.get(i).toString());
        }
    }

    /**
     * Method connects the different regions(rooms and corridors) in the dungeon
     */
    public void connectDungeon() {
        ArrayList<Connector> connectors = findConnectors();

        Unionfind u = new Unionfind(connectors.size());

        for (int i = 0; i < connectors.size(); i++) {
            if (!u.unified(connectors.get(i).getRegion1(), connectors.get(i).getRegion2())) {
                u.unify(connectors.get(i).getRegion1(), connectors.get(i).getRegion2());
                int y = connectors.get(i).getY();
                int x = connectors.get(i).getX();

                dungeon[y][x] = "/";
            }
        }

    }

    /**
     * Method finds all possible connectors that can connect two different regions
     * in the dungeon
     * @return List of Connector-objects
     */
    public ArrayList findConnectors() {
        ArrayList<Connector> connectors = new ArrayList<>();
        for (int y = 1; y < this.height - 2; y++) {
            for (int x = 1; x < this.width - 2; x++) {
                if (isConnector(x, y)) {
                    int[] regions = connectsRegions(x, y);
                    connectors.add(new Connector(x, y, regions[0], regions[1]));
                }
            }
        }
        return connectors;
    }

    /**
     * Method checks if a certain point in the dungeon can be a connector
     * @param x x-value of the spot we are checking
     * @param y y-value of the spot we are checking
     * @return true, if given point can be a connector. Otherwise false
     */
    public boolean isConnector(int x, int y) {
        if (!dungeon[y][x].equals("#")) {
            return false;
        }

        int differentRegions = 0;
        String region1 = "";

        if (!dungeon[y - 1][x].equals("#")) {
            differentRegions++;
            region1 = dungeon[y - 1][x];
        }

        if (!dungeon[y][x + 1].equals("#")) {
            if (region1.equals("")) {
                differentRegions++;
                region1 = dungeon[y][x + 1];
            } else {
                if (!region1.equals(dungeon[y][x + 1])) {
                    differentRegions++;
                }
            }
        }
        if (!dungeon[y + 1][x].equals("#")) {
            if (region1.equals("")) {
                differentRegions++;
                region1 = dungeon[y + 1][x];
            } else {
                if (!region1.equals(dungeon[y + 1][x])) {
                    differentRegions++;
                }
            }
        }
        if (!dungeon[y][x - 1].equals("#")) {
            if (region1.equals("")) {
                differentRegions++;
                region1 = dungeon[y][x - 1];
            } else {
                if (!region1.equals(dungeon[y][x - 1])) {
                    differentRegions++;
                }
            }
        }

        return differentRegions >= 2;

    }

    /**
     * Method checks which two regions a connector can connect together
     * @param x x-value of the connector
     * @param y y-value of the connector
     * @return an array with two values which refer to the connectable regions
     */
    public int[] connectsRegions(int x, int y) {
        int[] regions = new int[2];

        if (!dungeon[y - 1][x].equals("#")) {
            regions[0] = Integer.parseInt(dungeon[y - 1][x]);
        }

        if (!dungeon[y][x + 1].equals("#")) {
            if (regions[0] == 0) {
                regions[0] = Integer.parseInt(dungeon[y][x + 1]);
            } else {
                if (regions[0] != Integer.parseInt(dungeon[y][x + 1])) {
                    regions[1] = Integer.parseInt(dungeon[y][x + 1]);
                }
            }
        }

        if (!dungeon[y + 1][x].equals("#")) {
            if (regions[0] == 0) {
                regions[0] = Integer.parseInt(dungeon[y + 1][x]);
            } else {
                if (regions[0] != Integer.parseInt(dungeon[y + 1][x])) {
                    regions[1] = Integer.parseInt(dungeon[y + 1][x]);
                }

            }
        }

        if (!dungeon[y][x - 1].equals("#")) {
            if (regions[0] == 0) {
                regions[0] = Integer.parseInt(dungeon[y][x - 1]);
            } else {
                if (regions[0] != Integer.parseInt(dungeon[y][x - 1])) {
                    regions[1] = Integer.parseInt(dungeon[y][x - 1]);
                }
            }
        }

        return regions;
    }

    /**
     * Makes the dungeon pretty and gets rid of the integers in it
     */
    public void makeItPretty() {
        for (int y = 1; y < this.height - 1; y++) {
            for (int x = 1; x < this.width - 1; x++) {
                if (isInteger(dungeon[y][x])) {
                    if (Integer.parseInt(dungeon[y][x]) <= rooms.size()) {
                        dungeon[y][x] = " ";
                    } else {
                        dungeon[y][x] = ".";
                    }
                }
            }
        }
    }

    private boolean isInteger(String possibleNumber) {
        try {
            Integer.parseInt(possibleNumber);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
