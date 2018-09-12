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

    public void print() {
        for (int i = 0; i < this.height; i++) {
            System.out.print("\n");
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.dungeon[i][j]);
            }
        }

    }

    public void placeRooms() {
        for (int i = 0; i < 10; i++) {
            this.placeRoom();
        }
    }

    public void placeRoom() {
        Random random = new Random();
        int x = random.nextInt(this.width - 2) + 1;
        int y = random.nextInt(this.height - 2) + 1;
        
        if (checkIfRoomIsValid(x, y, 2, 2)) {
            this.dungeon[y][x] = ".";
            this.dungeon[y][x + 1] = ".";
            this.dungeon[y + 1][x] = ".";
            this.dungeon[y + 1][x + 1] = ".";
        }

    }

    public boolean checkIfRoomIsValid(int x, int y, int height, int width) {
        for (int i = x; i < x + width; i++) {
            if (i < 1 || i >= this.width - 1) {
                return false;
            }
        }

        for (int i = y; i < y + height; i++) {
            if (i < 1 || i >= this.height - 1) {
                return false;
            }

        }
        return true;

    }

}
