package generaattori;

import java.util.Random;
import tietorakenteet.OwnArrayList;
import tietorakenteet.Unionfind;

/**
 *
 * @author ogrousu
 */
public class Dungeongenerator {

    private int height;
    private int width;
    private String[][] dungeon;
    private int region;
    private OwnArrayList<Room> rooms;

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
        this.rooms = new OwnArrayList();

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
        this.buildCorridors();
        this.connectDungeon();
        this.removeDeadEnds();
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
    public void buildCorridors() {
        for (int y = 1; y < this.height - 1; y += 2) {
            for (int x = 1; x < this.width - 1; x += 2) {
                if (checkIfPlaceIsValidForStartingACorridor(x, y)) {
                    CorridorCell cell = new CorridorCell(x, y);
                    this.buildCorridor(cell);
                    region++;
                }
            }
        }
    }

    public void buildCorridor(CorridorCell cell) {
        Random random = new Random();
        OwnArrayList<CorridorCell> cells = new OwnArrayList<>();

        cells.add(cell);
        this.carve(cell);

        while (!cells.isEmpty()) {
            CorridorCell current = cells.get(cells.size() - 1);

            OwnArrayList<CorridorCell> neighbours = this.getCarvableNeighbours(current);

            if (!neighbours.isEmpty()) {
                CorridorCell chosen = neighbours.get(random.nextInt(neighbours.size()));
                this.carve(chosen);
                cells.add(chosen);
            } else {
                cells.remove(cells.size() - 1);
            }

        }

    }

    public OwnArrayList getCarvableNeighbours(CorridorCell cell) {
        OwnArrayList<CorridorCell> neighbours = new OwnArrayList<>();

        if (isCarvable(cell.getX(), cell.getY() - 1, "UP")) {
            neighbours.add(new CorridorCell(cell.getX(), cell.getY() - 1));
        }
        if (isCarvable(cell.getX() + 1, cell.getY(), "RIGHT")) {
            neighbours.add(new CorridorCell(cell.getX() + 1, cell.getY()));
        }
        if (isCarvable(cell.getX(), cell.getY() + 1, "DOWN")) {
            neighbours.add(new CorridorCell(cell.getX(), cell.getY() + 1));
        }
        if (isCarvable(cell.getX() - 1, cell.getY(), "LEFT")) {
            neighbours.add(new CorridorCell(cell.getX() - 1, cell.getY()));
        }

        return neighbours;
    }

    public boolean isCarvable(int x, int y, String direction) {
        if (y < 1 || x < 1 || y >= height - 1 || x >= width - 1) {
            return false;
        }

        if (!dungeon[y][x].equals("#")) {
            return false;
        }

        //this monstrosity checks if the spot we are checking is too close to a room
        
        if (isInteger(dungeon[y - 1][x - 1]) && Integer.parseInt(dungeon[y - 1][x - 1]) <= rooms.size()
                || isInteger(dungeon[y - 1][x]) && Integer.parseInt(dungeon[y - 1][x]) <= rooms.size()
                || isInteger(dungeon[y - 1][x + 1]) && Integer.parseInt(dungeon[y - 1][x + 1]) <= rooms.size()
                || isInteger(dungeon[y][x - 1]) && Integer.parseInt(dungeon[y][x - 1]) <= rooms.size()
                || isInteger(dungeon[y][x + 1]) && Integer.parseInt(dungeon[y][x + 1]) <= rooms.size()
                || isInteger(dungeon[y + 1][x - 1]) && Integer.parseInt(dungeon[y + 1][x - 1]) <= rooms.size()
                || isInteger(dungeon[y + 1][x]) && Integer.parseInt(dungeon[y + 1][x]) <= rooms.size()
                || isInteger(dungeon[y + 1][x + 1]) && Integer.parseInt(dungeon[y + 1][x + 1]) <= rooms.size()) {

            return false;
        }

        if (direction.equals(
                "UP")) {
            if (!dungeon[y - 1][x].equals("#")
                    || !dungeon[y][x - 1].equals("#")
                    || !dungeon[y][x + 1].equals("#")) {
                return false;
            }

        } else if (direction.equals(
                "LEFT")) {
            if (!dungeon[y - 1][x].equals("#")
                    || !dungeon[y][x - 1].equals("#")
                    || !dungeon[y + 1][x].equals("#")) {
                return false;
            }

        } else if (direction.equals(
                "RIGHT")) {
            if (!dungeon[y - 1][x].equals("#")
                    || !dungeon[y + 1][x].equals("#")
                    || !dungeon[y][x + 1].equals("#")) {
                return false;
            }

        } else if (direction.equals(
                "DOWN")) {
            if (!dungeon[y][x - 1].equals("#")
                    || !dungeon[y][x + 1].equals("#")
                    || !dungeon[y + 1][x].equals("#")) {
                return false;
            }
        }
        return true;
    }

    public void carve(CorridorCell cell) {
        this.dungeon[cell.getY()][cell.getX()] = Integer.toString(this.region);
    }

    /**
     * checks if a corridor can be placed in this position
     *
     * @param x x-value of the corridor
     * @param y y-value of the corridor
     * @return true, if a corridor can be placed, else false
     */
    public boolean checkIfPlaceIsValidForStartingACorridor(int x, int y) {
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
            int width = random.nextInt(7) + 2;

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
        OwnArrayList connectors = findConnectors();

        for (int i = 0; i < connectors.size(); i++) {
            System.out.println(connectors.get(i).toString());
        }
    }

    /**
     * Method connects the different regions(rooms and corridors) in the dungeon
     */
    public void connectDungeon() {
        OwnArrayList<Connector> connectors = findConnectors();

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
     * Method finds all possible connectors that can connect two different
     * regions in the dungeon
     *
     * @return List of Connector-objects
     */
    public OwnArrayList findConnectors() {
        OwnArrayList<Connector> connectors = new OwnArrayList<>();
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
     *
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
     *
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

    public void removeDeadEnds() {
        for (int y = 1; y < this.height - 1; y++) {
            for (int x = 1; x < this.width - 1; x++) {
                if (!this.dungeon[y][x].equals("#")) {
                    deadEndRemover(x, y);

                }
            }

        }
    }

    public void deadEndRemover(int x, int y) {
        if (this.dungeon[y][x].equals("#")) {
            return;
        }

        int exits = 0;
        String direction = "";

        if (!this.dungeon[y - 1][x].equals("#")) {
            exits++;
            direction = "UP";
        }
        if (!this.dungeon[y][x + 1].equals("#")) {
            exits++;
            direction = "RIGHT";
        }
        if (!this.dungeon[y + 1][x].equals("#")) {
            exits++;
            direction = "DOWN";
        }
        if (!this.dungeon[y][x - 1].equals("#")) {
            exits++;
            direction = "LEFT";
        }

        if (exits == 1) {
            this.dungeon[y][x] = "#";

            if (direction.equals("UP")) {
                deadEndRemover(x, y - 1);
            } else if (direction.equals("RIGHT")) {
                deadEndRemover(x + 1, y);
            } else if (direction.equals("DOWN")) {
                deadEndRemover(x, y + 1);
            } else if (direction.equals("LEFT")) {
                deadEndRemover(x - 1, y);
            }
        }

    }

}
