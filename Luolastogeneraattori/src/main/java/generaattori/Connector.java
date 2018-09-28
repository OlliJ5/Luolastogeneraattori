
package generaattori;

/**
 *
 * @author ogrousu
 */
public class Connector {
    private int x;
    private int y;
    private int region1;
    private int region2;

    public Connector(int x, int y, int region1, int region2) {
        this.x = x;
        this.y = y;
        this.region1 = region1;
        this.region2 = region2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRegion1() {
        return region1;
    }

    public int getRegion2() {
        return region2;
    }
    
    

    @Override
    public String toString() {
        return "\nx: " + x + " y: " + y + "    - connects " + region1 + " and " + region2;
    }
    
    
}
