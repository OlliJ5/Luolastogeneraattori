
package tietorakenteet;

/**
 *
 * @author ogrousu
 */
public class Unionfind {
    private int[] next;
    private int[] size;

    public Unionfind(int n) {
        this.next = new int[n + 1];
        this.size = new int[n + 1];
        
        for(int i = 0; i < n; i++) {
            next[i] = i;
            size[i] = 1;
        }
    }
    
    public int id(int x) {
        while(x != next[x]) {
            x = next[x];
        }
        return x;
    }
    
    public boolean unified(int a, int b) {
        return id(a) == id(b);
    }
    
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
