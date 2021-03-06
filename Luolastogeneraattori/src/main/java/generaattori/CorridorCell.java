package generaattori;

public class CorridorCell {

    private int x;
    private int y;

    public CorridorCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return the x-value of the cell
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return the y-value of the cell
     */
    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final CorridorCell other = (CorridorCell) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

}
