
package tietorakenteet;

/**
 *
 * @author ogrousu
 */
public class Unionfind {
    private int[] next;
    private int[] size;

    /**
     *
     * @param n amount of the "vertices"
     */
    public Unionfind(int n) {
        this.next = new int[n + 1];
        this.size = new int[n + 1];
        
        for(int i = 0; i < n; i++) {
            next[i] = i;
            size[i] = 1;
        }
    }
    
    /**
     * Checks what the leading item is
     * @param x Vertex which leading item we want to find out
     * @return the leading item
     */
    public int id(int x) {
        while(x != next[x]) {
            x = next[x];
        }
        return x;
    }
    
    /**
     * Method finds out if the two vertices are connected
     * @param a A vertex
     * @param b Another vertex
     * @return true if the vertices are already unified. Otherwise false
     */
    public boolean unified(int a, int b) {
        return id(a) == id(b);
    }
    
    /**
     * Unifies two vertices
     * @param a A Vertex
     * @param b Another vertex
     */
    public void unify(int a, int b) {
        a = id(a);
        b = id(b);
        
        if(size[b] > size[a]) {
            size[b] += size[a];
            next[a] = b;
        } else {
            size[a] += size[b];
            next[b] = a;
        }
    }
    
    
}
