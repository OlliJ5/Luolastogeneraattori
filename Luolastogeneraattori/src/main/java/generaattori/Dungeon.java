package generaattori;

import static java.lang.Math.random;
import java.util.Random;

/**
 *
 * @author ogrousu
 */
public class Dungeon {

    private int height;
    private int width;
    private String[][] dungeon;

    /**
     *
     * create a dungeon of some size
     *
     * @param height the desired height of the dungeon
     * @param width the desired width of the dungeon
     */
    public Dungeon(int height, int width) {
        this.height = height;
        this.width = width;
        this.dungeon = new String[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.dungeon[i][j] = "#";
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

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
        for (int y = 1; y < this.height; y += 2) {
            for (int x = 1; x < this.width; x += 2) {
                this.floodFill(y, x, "dunno");
            }
        }
    }

    /**
     * Recursive method that checks if a certain point can be a part 
     * of a corridor in the dungeon. Calls itself to check if the surrounding 
     * points are acceptable spots as well
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

        if (dungeon[y - 1][x - 1].equals(" ")
                || dungeon[y - 1][x].equals(" ")
                || dungeon[y - 1][x + 1].equals(" ")
                || dungeon[y][x - 1].equals(" ")
                || dungeon[y][x + 1].equals(" ")
                || dungeon[y + 1][x - 1].equals(" ")
                || dungeon[y + 1][x].equals(" ")
                || dungeon[y + 1][x + 1].equals(" ")) {
            return;
        }

        if (direction.equals("UP")) {
            if (!dungeon[y - 1][x].equals("#")
                    || !dungeon[y][x - 1].equals("#")
                    || !dungeon[y][x + 1].equals("#")) {
                return;
            }

        } else if (direction.equals("LEFT")) {
            if (!dungeon[y - 1][x].equals("#")
                    || !dungeon[y][x - 1].equals("#")
                    || !dungeon[y + 1][x].equals("#")) {
                return;
            }

        } else if (direction.equals("RIGHT")) {
            if (!dungeon[y - 1][x].equals("#")
                    || !dungeon[y + 1][x].equals("#")
                    || !dungeon[y][x + 1].equals("#")) {
                return;
            }

        } else if (direction.equals("DOWN")) {
            if (!dungeon[y][x - 1].equals("#")
                    || !dungeon[y][x + 1].equals("#")
                    || !dungeon[y + 1][x].equals("#")) {
                return;
            }
        }

        this.dungeon[y][x] = ".";

        this.floodFill(y - 1, x, "UP");
        this.floodFill(y, x - 1, "LEFT");
        this.floodFill(y, x + 1, "RIGHT");
        this.floodFill(y + 1, x, "DOWN");
    }

    /**
     * Places rooms in the dungeon
     * @param amount amount of tries to place a room
     */
    public void placeRooms(int amount) {
        Random random = new Random();
        
        for (int i = 0; i < amount; i++) {
            int x = random.nextInt(this.width - 2) + 1;
            int y = random.nextInt(this.height - 2) + 1;
            
            int height = random.nextInt(2) + 2;
            int width = random.nextInt(4) + 2;
                    
            Room room = new Room(x, y, width, height);
            
            room.placeRoom(this.dungeon);
        }
    }

}
