package generaattori;


public class Room {

    private int x;
    private int y;
    private int width;
    private int height;
    private int regionNumber;

    /**
     * creates a Room
     *
     * @param x x-value of the upper left corner of the room
     * @param y y-value of the upper left corner of the room
     * @param width the width of the room
     * @param height the height of the room
     * @param region region number of the room
     */
    public Room(int x, int y, int width, int height, int region) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.regionNumber = region;
    }

    /**
     *
     * @return x-value of the upper left corner of the room
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return y-value of the upper left corner of the room
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @return width of the room
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return height of the room
     */
    public int getHeight() {
        return height;
    }

    /**
     * Places a room if it's in an acceptable spot
     *
     * @param dungeon the dungeon where you want to place the room
     * @param region region number that is used for the room
     * @return returns 0 if the room wasn't placed. the value of the "region"
     * otherwise
     */
    public int placeRoom(String[][] dungeon, int region) {
        if (checkIfRoomIsValid(dungeon)) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    dungeon[y + i][x + j] = Integer.toString(region);
                }
            }
            return region;

        }

        return 0;

    }

    /**
     * Checks if a certain room is in an acceptable spot
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
     * Checks if a room is out of bounds
     *
     * @param dungeon the dungeon which we want to check
     * @return returns true, if some part of the room is outside of the dungeon.
     */
    public boolean isRoomOutOfBounds(String[][] dungeon) {
        if (this.x < 1
                || this.x + this.width - 1 >= dungeon[0].length - 1) {
            return true;
        }
        
        if (this.y < 1
                || this.y + this.height - 1 >= dungeon.length - 1) {
            return true;
        }
        
        return false;

    }

    @Override
    public String toString() {
        return "x: " + this.x + "\ny: " + this.y + "\nwidth and height: " + this.width + "x" + this.height;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.regionNumber != other.regionNumber) {
            return false;
        }
        return true;
    }

}
