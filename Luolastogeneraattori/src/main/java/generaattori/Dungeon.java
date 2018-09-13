package generaattori;

import static java.lang.Math.random;
import java.util.Random;

public class Dungeon {

    private int height;
    private int width;
    private String[][] dungeon;

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

    public void generate() {
        this.placeRooms();
        this.placeCorridors();
    }

    public void print() {
        for (int i = 0; i < this.height; i++) {
            System.out.print("\n");
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.dungeon[i][j]);
            }
        }

    }

    public void placeCorridors() {
        for (int y = 1; y < this.height; y += 2) {
            for (int x = 1; x < this.width; x += 2) {
                this.floodFill(y, x, "dunno");
            }
        }
    }

    public void floodFill(int y, int x, String direction) {
        if(y < 1 || x < 1 || y >= height - 1 || x >= width - 1) {
            return;
        }
        
        if (dungeon[y][x].equals("-")) {
            return;
        }
        if (!dungeon[y][x].equals("#")) {
            return;
        }

        if (dungeon[y - 1][x - 1].equals(".")
                || dungeon[y - 1][x].equals(".")
                || dungeon[y - 1][x + 1].equals(".")
                || dungeon[y][x - 1].equals(".")
                || dungeon[y][x + 1].equals(".")
                || dungeon[y + 1][x - 1].equals(".")
                || dungeon[y + 1][x].equals(".")
                || dungeon[y + 1][x + 1].equals(".")) {
            return;
        }
        
        if(direction.equals("UP")) {
            if(!dungeon[y - 1][x].equals("#")
                    || !dungeon[y][x - 1].equals("#")
                    || !dungeon[y][x + 1].equals("#")) {
                return;
            } 
            
        } else if(direction.equals("LEFT")) {
            if(!dungeon[y - 1][x].equals("#")
                    || !dungeon[y][x - 1].equals("#")
                    || !dungeon[y + 1][x].equals("#")) {
                return;
            }
            
        } else if(direction.equals("RIGHT")) {
            if(!dungeon[y - 1][x].equals("#")
                    || !dungeon[y + 1][x].equals("#")
                    || !dungeon[y][x + 1].equals("#")) {
                return;
            } 
            
        } else if(direction.equals("DOWN")) {
            if(!dungeon[y][x - 1].equals("#")
                    || !dungeon[y][x + 1].equals("#")
                    || !dungeon[y + 1][x].equals("#")) {
                return;
            } 
        }
        
        this.dungeon[y][x] = "-";
        
        this.floodFill(y - 1, x, "UP");
        this.floodFill(y, x - 1, "LEFT");
        this.floodFill(y, x + 1, "RIGHT");
        this.floodFill(y + 1, x, "DOWN");
    }

    public void placeRooms() {
        for (int i = 0; i < 1; i++) {
            this.placeRoom();
        }
    }

    public void placeRoom() {
        Random random = new Random();
        int x = random.nextInt(this.width - 2) + 1;
        int y = random.nextInt(this.height - 2) + 1;

        int height = random.nextInt(2) + 2;
        int width = random.nextInt(4) + 2;

        if (checkIfRoomIsValid(x, y, height, width)) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    dungeon[y + i][x + j] = ".";
                }
            }

        }

    }

    public boolean checkIfRoomIsValid(int x, int y, int height, int width) {
        if (isRoomOutOfBounds(x, y, height, width)) {
            return false;
        } else if (roomOverlaps(x, y, height, width)) {
            return false;
        }

        return true;

    }

    public boolean roomOverlaps(int x, int y, int height, int width) {
        for (int i = -1; i <= height; i++) {
            for (int j = -1; j <= width; j++) {
                if (!dungeon[y + i][x + j].equals("#")) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isRoomOutOfBounds(int x, int y, int height, int width) {
        for (int i = x; i < x + width; i++) {
            if (i < 1 || i >= this.width - 1) {
                return true;
            }
        }

        for (int i = y; i < y + height; i++) {
            if (i < 1 || i >= this.height - 1) {
                return true;
            }

        }
        return false;

    }

}
