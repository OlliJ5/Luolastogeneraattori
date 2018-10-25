
package generaattori;


public class Connector {
    private int x;
    private int y;
    private int region1;
    private int region2;

    /**
     * Creates a connector that can connect two regions together
     * @param x x-value of the connector
     * @param y y-value of the connector
     * @param region1 one of the regions it can connect
     * @param region2 other region it can connect
     */
    public Connector(int x, int y, int region1, int region2) {
        this.x = x;
        this.y = y;
        this.region1 = region1;
        this.region2 = region2;
    }

    /**
     *
     * @return returns the x-value of the connector
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return returns the y-value of the connector
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @return returns one of the regions it can connect
     */
    public int getRegion1() {
        return region1;
    }

    /**
     *
     * @return returns one of the regions it can connect
     */
    public int getRegion2() {
        return region2;
    }
    
    

    @Override
    public String toString() {
        return "\nx: " + x + " y: " + y + "    - connects " + region1 + " and " + region2;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Connector other = (Connector) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.region1 != other.region1) {
            return false;
        }
        if (this.region2 != other.region2) {
            return false;
        }
        return true;
    }
    
    
    
}
