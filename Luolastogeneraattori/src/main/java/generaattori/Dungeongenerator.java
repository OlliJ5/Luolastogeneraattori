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
    private int minRoomHeight;
    private int minRoomWidth;
    private String[][] dungeon;
    private int region;
    private OwnArrayList<Room> rooms;
    private int roomAmount;

    /**
     *
     * create a dungeon generator that creates a dungeon of some size
     *
     * @param height the desired height of the dungeon
     * @param width the desired width of the dungeon
     * @param minRoomHeight the minimum room height
     * @param minRoomWidth the minimum roo width
     */
    public Dungeongenerator(int height, int width, int minRoomHeight, int minRoomWidth) {
        this.height = height;
        this.width = width;

        this.minRoomHeight = minRoomHeight;
        this.minRoomWidth = minRoomWidth;

        this.dungeon = new String[this.height][this.width];
        this.region = 1;
        this.rooms = new OwnArrayList<>();
        this.roomAmount = 0;

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
     *
     * @return Rooms that are in the dungeon as a list
     */
    public OwnArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     *
     * @param roomAmount sets the current amount of rooms
     */
    public void setRoomAmount(int roomAmount) {
        this.roomAmount = roomAmount;
    }

    /**
     *
     * @param region sets the current region to be some integer
     */
    public void setRegion(int region) {
        this.region = region;
    }

    /**
     * The method generates a dungeon with rooms and corridors
     *
     * @param amountOfRooms amount of tries to place a room
     */
    public void generate(int amountOfRooms) {
        //1.
        this.placeRooms(amountOfRooms);
        //2.
        this.buildCorridors();
        //3.
        this.connectDungeon();
        //4
        this.removeDeadEnds();
        //5
        this.makeItPretty();
    }

    /**
     * The method returns the created ASCII-dungeon as a string
     *
     * @return Returns the dungeon as a String
     */
    @Override
    public String toString() {
        String returnString = "";
        for (int i = 0; i < this.height; i++) {
            returnString += "\n";
            for (int j = 0; j < this.width; j++) {
                returnString += this.dungeon[i][j];
            }
        }

        return returnString;
    }

    /*
    1.
    This is the first part of the algorithm. It places rooms to the dungeon
    
     */
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

            int height = random.nextInt(2) + this.minRoomHeight;
            int width = random.nextInt(7) + this.minRoomWidth;

            Room room = new Room(x, y, width, height, region);

            if (room.placeRoom(dungeon, region) != 0) {
                rooms.add(room);
                roomAmount++;
                region++;
            }

        }
    }

    /*
    2.
    This is the second part of the algorithm where it fills the maze with 
    corridors
     */
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

    /**
     * checks if a corridor can be placed in this position
     *
     * @param x x-value of the corridor
     * @param y y-value of the corridor
     * @return true, if a corridor can be started here, else false
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
     * This method builds a corridor
     *
     * @param cell The cell where we want to start carving the corridor
     */
    public void buildCorridor(CorridorCell cell) {
        Random random = new Random();
        OwnArrayList<CorridorCell> cells = new OwnArrayList<>();

        cells.add(cell);
        this.carve(cell);

        while (!cells.isEmpty()) {
            CorridorCell current = cells.get(cells.size() - 1);

            //gets the cells where we can move next
            OwnArrayList<CorridorCell> neighbours = this.getCarvableNeighbours(current);

            if (!neighbours.isEmpty()) {
                //we choose one of the cells where we can move to and carve our way there
                CorridorCell chosen = neighbours.get(random.nextInt(neighbours.size()));
                this.carve(chosen);
                cells.add(chosen);
            } else {
                cells.remove(cells.size() - 1);
            }

        }

    }

    /**
     * Checks around the cell and sees where we can carve to
     *
     * @param cell The cell the neighbours of which we want to get
     * @return returns a list of neighbours cells that we can move to next
     */
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

    /**
     * Checks if a certain spot can be carved
     *
     * @param x the x-value of the spot
     * @param y the y-value of the spot
     * @param direction the direction in which we are moving. For example if we
     * are coming from the cell that is below this spot, the direction is UP
     * @return true, if the cell can be carved to, else false
     */
    public boolean isCarvable(int x, int y, String direction) {
        if (y < 1 || x < 1 || y >= height - 1 || x >= width - 1) {
            return false;
        }

        if (!dungeon[y][x].equals("#")) {
            return false;
        }

        if (cellIsTooCloseToARoom(x, y)) {
            return false;
        }

        switch (direction) {
            case "UP":
                if (!dungeon[y - 1][x].equals("#")
                        || !dungeon[y][x - 1].equals("#")
                        || !dungeon[y][x + 1].equals("#")) {
                    return false;
                }
                break;
            case "LEFT":
                if (!dungeon[y - 1][x].equals("#")
                        || !dungeon[y][x - 1].equals("#")
                        || !dungeon[y + 1][x].equals("#")) {
                    return false;
                }
                break;
            case "RIGHT":
                if (!dungeon[y - 1][x].equals("#")
                        || !dungeon[y + 1][x].equals("#")
                        || !dungeon[y][x + 1].equals("#")) {
                    return false;
                }
                break;
            case "DOWN":
                if (!dungeon[y][x - 1].equals("#")
                        || !dungeon[y][x + 1].equals("#")
                        || !dungeon[y + 1][x].equals("#")) {
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * Methods checks if some cell is to close to a room
     *
     * @param x x-value of the spot we are checking
     * @param y y-value of the spot we are chekcing
     * @return true, if it is too close, false if not
     */
    public boolean cellIsTooCloseToARoom(int x, int y) {
        if (cellIsINaRoom(x - 1, y - 1)
                || cellIsINaRoom(x, y - 1)
                || cellIsINaRoom(x + 1, y - 1)
                || cellIsINaRoom(x - 1, y)
                || cellIsINaRoom(x + 1, y)
                || cellIsINaRoom(x - 1, y + 1)
                || cellIsINaRoom(x, y + 1)
                || cellIsINaRoom(x + 1, y + 1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a cell is in a room
     *
     * @param x x-value of the spot
     * @param y y-value of the spot
     * @return true if the cell is in a room, false if not
     */
    public boolean cellIsINaRoom(int x, int y) {
        if (isInteger(dungeon[y][x]) && Integer.parseInt(dungeon[y][x]) <= roomAmount) {
            return true;
        }
        return false;
    }

    /**
     * Carves a corridor to a certain cell or a spot in the dungeon
     *
     * @param cell Spot we want to turn into a corridor
     */
    public void carve(CorridorCell cell) {
        this.dungeon[cell.getY()][cell.getX()] = Integer.toString(this.region);
    }

    /*
    3.
    This is the third part of the algorithm where it connects the dungeon and makes
    it possible to 'move' from one region to another. For example from a room to another 
    room
    
     */
    /**
     * Method connects the different regions(rooms and corridors) in the
     * dungeon. The method uses the Union Find data structure and Kruskal's
     * Algorithm
     */
    public void connectDungeon() {
        OwnArrayList<Connector> connectors = findConnectors();

        Unionfind u = new Unionfind(region);

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
     * @return List of Connector-objects that connect two different regions
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

        OwnArrayList<String> regions = new OwnArrayList<>();

        if (!dungeon[y - 1][x].equals("#")) {
            differentRegions++;
            regions.add(dungeon[y - 1][x]);
        }

        if (!dungeon[y][x + 1].equals("#")
                && !regions.contains(dungeon[y][x + 1])) {
            differentRegions++;
            regions.add(dungeon[y][x + 1]);
        }

        if (!dungeon[y + 1][x].equals("#")
                && !regions.contains(dungeon[y + 1][x])) {
            differentRegions++;
            regions.add(dungeon[y + 1][x]);
        }

        if (!dungeon[y][x - 1].equals("#")
                && !regions.contains(dungeon[y][x - 1])) {
            differentRegions++;
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
        OwnArrayList<String> regions = new OwnArrayList<>();

        if (!dungeon[y - 1][x].equals("#")) {
            regions.add(dungeon[y - 1][x]);
        }

        if (!dungeon[y][x + 1].equals("#")
                && !regions.contains(dungeon[y][x + 1])) {
            regions.add(dungeon[y][x + 1]);
        }

        if (!dungeon[y + 1][x].equals("#")
                && !regions.contains(dungeon[y + 1][x])) {
            regions.add(dungeon[y + 1][x]);
        }

        if (!dungeon[y][x - 1].equals("#")
                && !regions.contains(dungeon[y][x - 1])) {
            regions.add(dungeon[y][x - 1]);
        }

        int[] regionsAsArray = new int[2];

        regionsAsArray[0] = Integer.parseInt(regions.get(0));
        regionsAsArray[1] = Integer.parseInt(regions.get(1));

        return regionsAsArray;
    }

    /*
    4.
    This is the fourth part of the algorithm where we remove the dead-ends from
    the corridors so that the dungeon is a lot cleaner 
     */
    /**
     * Method removes dead-ends. Loops through the dungeon and when it finds a
     * spot that could be a dead-end(not a wall) it calls the method
     * deadEndRemover
     */
    public void removeDeadEnds() {
        for (int y = 1; y < this.height - 1; y++) {
            for (int x = 1; x < this.width - 1; x++) {
                if (!this.dungeon[y][x].equals("#")) {
                    deadEndRemover(x, y);

                }
            }

        }
    }

    /**
     * First the method checks if the spot actually is a dead-end. It's a
     * dead-end when it has 0 or 1 exits. If it's not it does nothing. If it is
     * it gets rid of the dead-end by placing a wall there and then moves
     * recursivly calls itself at the spot the previous corridor cell was
     * connected to.
     *
     * @param x x-value of the spot we are checking
     * @param y y-value of the spot we are checking
     */
    public void deadEndRemover(int x, int y) {
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

        if (exits == 0) {
            this.dungeon[y][x] = "#";
        } else if (exits == 1) {
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

    /**
     * Makes the dungeon pretty and gets rid of the integers in it
     */
    public void makeItPretty() {
        for (int y = 1; y < this.height - 1; y++) {
            for (int x = 1; x < this.width - 1; x++) {
                if (isInteger(dungeon[y][x])) {
                    if (Integer.parseInt(dungeon[y][x]) <= roomAmount) {
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
