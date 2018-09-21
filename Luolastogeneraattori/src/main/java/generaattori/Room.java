
package generaattori;

import java.util.Random;

/**
 *
 * @author ogrousu
 */
public class Room {
    private int x;
    private int y;
    private int width;
    private int height;

    public Room(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    

    /**
     * Places a room if it's in an acceptable spot
     * @param dungeon the dungeon where you want to place the room
     */
    public void placeRoom(String[][] dungeon) {
        if (checkIfRoomIsValid(dungeon)) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    dungeon[y + i][x + j] = " ";
                }
            }

        }

    }

    /**
     * Checks if a certain room is an acceptable spot
     *
     * @param dungeon the dungeon you want to see if the room fits in
     * @return true, if the room is in an acceptable spot, else false
     */
    public boolean checkIfRoomIsValid(String[][] dungeon) {
        if (isRoomOutOfBounds(dungeon)) {
            return false;
        } else if (roomOverlaps(dungeon)) {
            return false;
        }

        return true;

    }

    /**
     * Checks if a certain room would go on top of some other room or would be
     * too close to some other room
     *
     * @param dungeon the dungeon which we want to check
     * @return true, if the room is either on top of an already existing room or
     * too close to one. Else returns false
     */
    public boolean roomOverlaps(String[][] dungeon) {
        for (int i = -1; i <= this.height; i++) {
            for (int j = -1; j <= this.width; j++) {
                if (!dungeon[y + i][x + j].equals("#")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     *
     * @param dungeon the dungeon which we want to check
     * @return returns true, if some part of the room is outside of the dungeon.
     */
    public boolean isRoomOutOfBounds(String[][] dungeon) {
        for (int i = x; i < x + width; i++) {
            if (i < 1 || i >= dungeon[1].length - 1) {
                return true;
            }
        }

        for (int i = y; i < y + height; i++) {
            if (i < 1 || i >= dungeon.length - 1) {
                return true;
            }

        }
        return false;

    }
}
